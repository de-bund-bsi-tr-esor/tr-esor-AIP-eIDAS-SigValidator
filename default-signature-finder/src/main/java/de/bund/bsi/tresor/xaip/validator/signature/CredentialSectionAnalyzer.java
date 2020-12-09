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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.AbstractMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.io.IOUtils;

import de.bund.bsi.tr_esor.xaip._1.CredentialType;
import de.bund.bsi.tr_esor.xaip._1.DataObjectType;
import de.bund.bsi.tr_esor.xaip._1.DataObjectsSectionType;
import de.bund.bsi.tresor.xaip.validator.api.control.ModuleLogger;
import de.bund.bsi.tresor.xaip.validator.api.control.XAIPUtil;
import de.bund.bsi.tresor.xaip.validator.signature.entity.FinderResult;
import de.bund.bsi.tresor.xaip.validator.signature.entity.SignaturePresence;
import oasis.names.tc.dss._1_0.core.schema.Base64Signature;
import oasis.names.tc.dss._1_0.core.schema.SignatureObject;
import oasis.names.tc.dss._1_0.core.schema.SignaturePtr;

/**
 * @author wolffs
 */
public class CredentialSectionAnalyzer
{
    
    /**
     * Analyzing the credential and returning an entry of the credential with all possible signatures which can be verified
     * 
     * @param credential
     *            the credential type
     * @param dataObjects
     *            the dataObjectsSection containing the dataObjects
     * @param dataSectionResults
     *            the result of the dataSectionAnalyzer
     * @return an entry where the credential is being used as a key and possible signature are the value
     */
    public static Map.Entry<CredentialType, Set<FinderResult>> analyzeCredential( CredentialType credential,
            DataObjectsSectionType dataObjects, Set<FinderResult> dataSectionResults )
    {
        Set<FinderResult> sigResults = new HashSet<>();
        SignatureObject signObj = credential.getSignatureObject();
        Set<DataObjectType> relatedData = XAIPUtil.resolveRelatedDataObjects( dataObjects, credential.getRelatedObjects() );
        
        SignaturePtr signaturePtr = signObj.getSignaturePtr();
        Optional<byte[]> b64Signature = Optional.ofNullable( signObj.getBase64Signature() ).map( Base64Signature::getValue );
        Optional<org.w3._2000._09.xmldsig_.SignatureType> signType = Optional.ofNullable( signObj.getSignature() );
        
        if ( relatedData.isEmpty() )
        {
            // should check signatureObject/signature
            b64Signature.flatMap( data -> DataSectionAnalyzer.findSignatures( null, Optional.of( data ) ) )
                    .ifPresent( sigResults::add );
            
            if ( signType.map( org.w3._2000._09.xmldsig_.SignatureType::getObject ).map( l -> !l.isEmpty() ).orElse( false ) )
            {
                sigResults.add( new FinderResult( null, SignaturePresence.PRESENT, Optional.empty(), false ) );
            }
        }
        
        for ( DataObjectType dataObject : relatedData )
        {
            Optional<byte[]> optDataBlob = dataSectionResults.stream()
                    .filter( r -> dataObject.getDataObjectID().equals( r.getDataObject().getDataObjectID() ) )
                    .findAny()
                    .flatMap( FinderResult::getData )
                    .map( CredentialSectionAnalyzer::dataContent )
                    .or( () -> XAIPUtil.extractData( dataObject ) );
            
            boolean isXml = optDataBlob.map( XAIPUtil::isXml ).orElse( false );
            Optional<InputStream> optData = optDataBlob.map( ByteArrayInputStream::new );
            
            // TODO: only when optData present; is this correct?
            if ( optData.isPresent() && (signType.isPresent() || signObj.getTimestamp() != null) )
            {
                sigResults.add( new FinderResult( dataObject, SignaturePresence.PRESENT, optData, isXml ) );
            }
            else if ( b64Signature.isPresent() )
            {
                b64Signature.flatMap( data -> DataSectionAnalyzer.findSignatures( dataObject, Optional.of( data ) ) )
                        .ifPresent( sigResults::add );
            }
            else if ( signaturePtr != null )
            {
                sigResults.add( analyzePointer( signaturePtr, dataObject, optData, isXml ) );
            }
            else
            {
                sigResults.add( new FinderResult( dataObject, SignaturePresence.UNKNOWN, optData, isXml ) );
            }
        }
        
        return new AbstractMap.SimpleEntry<>( credential, sigResults );
    }
    
    static byte[] dataContent( InputStream stream )
    {
        try
        {
            return IOUtils.toByteArray( stream );
        }
        catch ( IOException e )
        {
            ModuleLogger.log( "credential anlyzer could not retrieve data", e );
            
            return null;
        }
    }
    
    static FinderResult analyzePointer( SignaturePtr pointer, DataObjectType dataObj, Optional<InputStream> optData, boolean isXml )
    {
        Object document = pointer.getWhichDocument();
        String oid = XAIPUtil.idFromObject( document );
        if ( dataObj.getDataObjectID().equals( oid ) )
        {
            return new FinderResult( dataObj, SignaturePresence.PRESENT, optData, isXml );
        }
        
        // TODO might convert document to byte[] in another form
        if ( document instanceof byte[] )
        {
            return DataSectionAnalyzer.analyzeBinData( dataObj, (byte[]) document );
        }
        
        return new FinderResult( dataObj, SignaturePresence.MISSING, optData, isXml );
    }
}
