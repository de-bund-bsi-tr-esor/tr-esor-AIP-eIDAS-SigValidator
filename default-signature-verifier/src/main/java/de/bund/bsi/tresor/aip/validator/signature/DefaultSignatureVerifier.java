/*-
 * Copyright (c) 2020
 * Federal Office for Information Security (BSI),
 * Godesberger Allee 185-189,
 * 53175 Bonn, Germany,
 * phone: +49 228 99 9582-0,
 * fax: +49 228 99 9582-5400,
 * e-mail: bsi@bsi.bund.de
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.bund.bsi.tresor.aip.validator.signature;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;

import de.bund.bsi.tr_esor.vr.CredentialValidityType;
import de.bund.bsi.tr_esor.vr.RelatedObjectsType;
import de.bund.bsi.tr_esor.xaip.CredentialType;
import de.bund.bsi.tr_esor.xaip.CredentialsSectionType;
import de.bund.bsi.tr_esor.xaip.DataObjectsSectionType;
import de.bund.bsi.tr_esor.xaip.MetaDataSectionType;
import de.bund.bsi.tr_esor.xaip.XAIPType;
import de.bund.bsi.tresor.aip.validator.api.boundary.SignatureVerifier;
import de.bund.bsi.tresor.aip.validator.api.control.AIPUtil;
import de.bund.bsi.tresor.aip.validator.api.control.ModuleLogger;
import de.bund.bsi.tresor.aip.validator.api.entity.ModuleContext;
import de.bund.bsi.tresor.aip.validator.syntax.context.DefaultSyntaxValidatorContext;
import lombok.Getter;
import oasis.names.tc.dss._1_0.core.schema.SignatureObject;

/**
 * Implementation of the SignatureVerifier module from the XAIPValidator.
 * 
 * @author wolffs
 */
@Getter
public class DefaultSignatureVerifier implements SignatureVerifier
{
    private final String          vendor  = "BSI";
    private final String          version = "1.0.0";
    
    private DefaultVerifierConfig config;
    private VerificationClient    client;
    
    /**
     * Configuring this module by using the provided property map
     * 
     * @param config
     *            the property map
     */
    public void configure( Map<String, String> config )
    {
        this.config = DefaultVerifierConfig.fromArguments( config );
    }
    
    @Override
    public List<CredentialValidityType> verify( ModuleContext context, XAIPType xaip, Map<String, Set<String>> credIdsByDataId )
    {
        client = new VerificationClient( this.config );
        Optional<DefaultSyntaxValidatorContext> syntaxContext = context.find( DefaultSyntaxValidatorContext.class );
        
        List<CredentialValidityType> resultList = new ArrayList<>();
        for ( Entry<String, Set<String>> entry : credIdsByDataId.entrySet() )
        {
            Optional<String> oid = Optional.ofNullable( entry.getKey() );
            Optional<byte[]> data = binaryDataFromObject( xaip, oid );
            
            Set<String> credIds = entry.getValue();
            if ( credIds.isEmpty() && data.isPresent() )
            {
                List<CredentialValidityType> result = verifySignature( oid.get(), null, Optional.empty(), data, syntaxContext );
                resultList.addAll( addMissingRelations( oid, result ) );
            }
            else
            {
                for ( String credId : credIds )
                {
                    Optional<SignatureObject> signObj = Optional.ofNullable( xaip.getCredentialsSection() ).stream()
                            .map( CredentialsSectionType::getCredential )
                            .flatMap( List::stream )
                            .filter( credObj -> credObj.getCredentialID().equals( credId ) )
                            .map( CredentialType::getSignatureObject )
                            .findAny();
                    
                    List<CredentialValidityType> result = verifySignature( oid.orElse( null ), credId, signObj, data, syntaxContext );
                    resultList.addAll( addMissingRelations( oid, result ) );
                }
            }
        }
        
        return resultList;
    }
    
    Optional<byte[]> binaryDataFromObject( XAIPType xaip, Optional<String> oid )
    {
        Optional<byte[]> data = Optional.ofNullable( xaip.getDataObjectsSection() ).stream()
                .map( DataObjectsSectionType::getDataObject )
                .flatMap( List::stream )
                .filter( dataObj -> oid.map( dataObj.getDataObjectID()::equals ).orElse( false ) )
                .findAny()
                .flatMap( dataObj -> AIPUtil.extractData( AIPUtil.binaryDataSupplier( dataObj ), dataObj::getXmlData ) );
        
        if ( data.isEmpty() )
        {
            data = Optional.ofNullable( xaip.getMetaDataSection() ).stream()
                    .map( MetaDataSectionType::getMetaDataObject )
                    .flatMap( List::stream )
                    .filter( metaObj -> oid.map( metaObj.getMetaDataID()::equals ).orElse( false ) )
                    .findAny()
                    .flatMap( metaObj -> AIPUtil.extractData( AIPUtil.binaryDataSupplier( metaObj ), metaObj::getXmlMetaData ) );
        }
        
        return data;
    }
    
    List<CredentialValidityType> verifySignature( String dataId, String credId, Optional<SignatureObject> signatureObject,
            Optional<byte[]> dataObjContent, Optional<DefaultSyntaxValidatorContext> ctx )
    {
        boolean encodeDataObj = dataObjContent.map( AIPUtil::isXml ).orElse( false );
        boolean encodeCredObj = signatureObject.map( SignatureObject::getSignature ).isPresent();
        String reqId = Optional.ofNullable( credId ).orElse( dataId );
        
        if ( encodeDataObj || encodeCredObj )
        {
            Optional<byte[]> encodedXmlData = Optional.empty();
            Optional<SignatureObject> encodedSignatureObj = Optional.empty();
            Optional<InputStream> optRawXaip = ctx.map( DefaultSyntaxValidatorContext::rawXaipInput );
            if ( optRawXaip.isPresent() )
            {
                try ( InputStream rawXaip = optRawXaip.get() )
                {
                    Document document = new SAXReader().read( rawXaip );
                    if ( encodeDataObj )
                    {
                        encodedXmlData = XmlSignatureEncoder.b64EncodeDataObjectPlainXml( document, dataId );
                    }
                    
                    if ( encodeCredObj )
                    {
                        encodedSignatureObj = XmlSignatureEncoder.b64EncodeCredentialXmlSignatureObject( document, credId );
                    }
                    
                    encodedXmlData = chooseData( dataObjContent, encodedXmlData );
                    encodedSignatureObj = chooseData( signatureObject, encodedSignatureObj );
                    
                    return client.request( reqId, encodedSignatureObj, encodedXmlData );
                }
                catch ( Exception e )
                {
                    ModuleLogger.verbose( "error retrieving xml related data for id " + reqId, e );
                    // since no request is being send to the verificationServic the code below is being executed to return alternative
                    // results with a warning
                }
            }
            
            ModuleLogger.log( "[ WARN ]xml data found bound to id " + reqId
                    + " but could not parse raw xaip input which can result into an invalid signature verification" );
            
            return client.request( reqId, signatureObject, dataObjContent );
        }
        else
        {
            
            return client.request( reqId, signatureObject, dataObjContent );
        }
    }
    
    List<CredentialValidityType> addMissingRelations( Optional<String> dataId, List<CredentialValidityType> req )
    {
        if ( dataId.isPresent() )
        {
            for ( CredentialValidityType credential : req )
            {
                String id = dataId.get();
                RelatedObjectsType relatedObjects = Optional.ofNullable( credential.getRelatedObjects() )
                        .orElse( new RelatedObjectsType() );
                List<String> xPath = Optional.ofNullable( relatedObjects.getXPath() ).orElse( new ArrayList<>() );
                if ( !xPath.contains( id ) )
                {
                    relatedObjects.getXPath().add( AIPUtil.xPathForObjectId( id ) );
                    credential.setRelatedObjects( relatedObjects );
                }
            }
        }
        
        return req;
    }
    
    <T> Optional<T> chooseData( Optional<T> optional, Optional<T> alternativeFavorite )
    {
        boolean opt = optional.isPresent();
        boolean optFavor = alternativeFavorite.isPresent();
        
        if ( opt && !optFavor )
        {
            return optional;
        }
        else
        {
            return optFavor ? alternativeFavorite : optional;
        }
    }
    
}
