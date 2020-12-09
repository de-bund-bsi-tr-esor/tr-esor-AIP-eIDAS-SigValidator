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
package de.bund.bsi.tresor.xaip.validator.signature;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;

import de.bund.bsi.tr_esor.vr._1.CredentialValidityType;
import de.bund.bsi.tr_esor.xaip._1.CredentialType;
import de.bund.bsi.tr_esor.xaip._1.CredentialsSectionType;
import de.bund.bsi.tr_esor.xaip._1.DataObjectsSectionType;
import de.bund.bsi.tr_esor.xaip._1.XAIPType;
import de.bund.bsi.tresor.xaip.validator.api.boundary.SignatureVerifier;
import de.bund.bsi.tresor.xaip.validator.api.control.ModuleLogger;
import de.bund.bsi.tresor.xaip.validator.api.control.XAIPUtil;
import de.bund.bsi.tresor.xaip.validator.api.entity.ModuleContext;
import de.bund.bsi.tresor.xaip.validator.signature.context.DefaultSignatureFinderContext;
import de.bund.bsi.tresor.xaip.validator.syntax.context.DefaultSyntaxValidatorContext;
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
        this.client = new VerificationClient( this.config );
    }
    
    @Override
    public List<CredentialValidityType> verify( ModuleContext context, XAIPType xaip, Map<String, Set<String>> credIdsByDataId )
    {
        Optional<DefaultSyntaxValidatorContext> syntaxContext = context.find( DefaultSyntaxValidatorContext.class );
        Set<String> xmlContextIds = context.find( DefaultSignatureFinderContext.class )
                .map( DefaultSignatureFinderContext::getXmlContextIds )
                .orElse( new HashSet<>() );
        
        List<CredentialValidityType> resultList = new ArrayList<>();
        for ( Entry<String, Set<String>> entry : credIdsByDataId.entrySet() )
        {
            Optional<String> dataId = Optional.ofNullable( entry.getKey() );
            Optional<byte[]> data = Optional.ofNullable( xaip.getDataObjectsSection() ).stream()
                    .map( DataObjectsSectionType::getDataObject )
                    .flatMap( List::stream )
                    .filter( dataObj -> dataId.map( dataObj.getDataObjectID()::equals ).orElse( false ) )
                    .findAny()
                    .flatMap( XAIPUtil::extractData );
            
            Set<String> credIds = entry.getValue();
            if ( credIds.isEmpty() && data.isPresent() )
            {
                resultList.addAll( verifySignature( dataId.get(), xmlContextIds, Optional.empty(), data, syntaxContext ) );
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
                    
                    resultList.addAll( verifySignature( credId, xmlContextIds, signObj, data, syntaxContext ) );
                }
            }
        }
        
        return resultList;
    }
    
    List<CredentialValidityType> verifySignature( String id, Set<String> xmlContextIds, Optional<SignatureObject> signatureObject,
            Optional<byte[]> dataObjContent, Optional<DefaultSyntaxValidatorContext> ctx )
    {
        if ( xmlContextIds.contains( id ) || signatureObject.map( SignatureObject::getSignature ).isPresent() )
        {
            Optional<byte[]> encodedXmlData = Optional.empty();
            Optional<SignatureObject> encodedSignatureObj = Optional.empty();
            Optional<InputStream> optRawXaip = ctx.map( DefaultSyntaxValidatorContext::rawXaipInput );
            if ( optRawXaip.isPresent() )
            {
                try ( InputStream rawXaip = optRawXaip.get() )
                {
                    Document document = new SAXReader().read( rawXaip );
                    encodedXmlData = XmlSignatureEncoder.b64EncodeDataObjectPlainXml( document, id );
                    
                    if ( signatureObject.isPresent() )
                    {
                        encodedSignatureObj = XmlSignatureEncoder.b64EncodeCredentialXmlSignatureObject( document, id );
                    }
                    
                    encodedXmlData = chooseData( dataObjContent, encodedXmlData );
                    encodedSignatureObj = chooseData( signatureObject, encodedSignatureObj );
                    
                    return client.request( id, encodedSignatureObj, encodedXmlData );
                }
                catch ( Exception e )
                {
                    ModuleLogger.verbose( "error retrieving xml related data for id " + id, e );
                    // since no request is being send to the verificationServic the code below is being executed to return alternative
                    // results with a warning
                }
            }
            
            ModuleLogger.log( "[ WARN ]xml data found bound to id " + id
                    + " but could not parse raw xaip input which can result into an invalid signature verification" );
            
            return client.request( id, signatureObject, dataObjContent );
        }
        else
        {
            return client.request( id, signatureObject, dataObjContent );
        }
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
