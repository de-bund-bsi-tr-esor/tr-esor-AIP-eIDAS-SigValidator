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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.io.IOUtils;

import de.bund.bsi.tr_esor.xaip.CredentialType;
import de.bund.bsi.tr_esor.xaip.DataObjectType;
import de.bund.bsi.tr_esor.xaip.DataObjectsSectionType;
import de.bund.bsi.tr_esor.xaip.MetaDataObjectType;
import de.bund.bsi.tr_esor.xaip.MetaDataSectionType;
import de.bund.bsi.tresor.aip.validator.api.control.AIPUtil;
import de.bund.bsi.tresor.aip.validator.api.control.ModuleLogger;
import de.bund.bsi.tresor.aip.validator.signature.entity.FinderResult;
import de.bund.bsi.tresor.aip.validator.signature.entity.SignaturePresence;
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
     * @param dataSection
     *            the dataObjectsSection containing the dataObjects
     * @param sectionResults
     *            the result of the dataSectionAnalyzer
     * @return an entry where the credential is being used as a key and possible signature are the value
     */
    public static Map.Entry<CredentialType, Set<FinderResult<DataObjectType>>> analyzeCredential( CredentialType credential,
            DataObjectsSectionType dataSection, Set<FinderResult<DataObjectType>> sectionResults )
    {
        Set<FinderResult<DataObjectType>> sigResults = new HashSet<>();
        Set<DataObjectType> relatedData = AIPUtil.resolveRelatedDataObjects( dataSection, DataObjectsSectionType::getDataObject,
                credential.getRelatedObjects() );
        
        sigResults.addAll( analyzeCredential( credential, relatedData, sectionResults ) );
        
        return new AbstractMap.SimpleEntry<>( credential, sigResults );
    }
    
    /**
     * Analyzing the credential and returning an entry of the credential with all possible signatures which can be verified
     * 
     * @param credential
     *            the credential type
     * @param metaDataSection
     *            the dataObjectsSection containing the dataObjects
     * @param sectionResults
     *            the result of the dataSectionAnalyzer
     * @return an entry where the credential is being used as a key and possible signature are the value
     */
    public static Map.Entry<CredentialType, Set<FinderResult<MetaDataObjectType>>> analyzeCredential( CredentialType credential,
            MetaDataSectionType metaDataSection, Set<FinderResult<MetaDataObjectType>> sectionResults )
    {
        Set<FinderResult<MetaDataObjectType>> sigResults = new HashSet<>();
        Set<MetaDataObjectType> relatedData = AIPUtil.resolveRelatedDataObjects( metaDataSection, MetaDataSectionType::getMetaDataObject,
                credential.getRelatedObjects() );
        
        sigResults.addAll( analyzeCredential( credential, relatedData, sectionResults ) );
        
        return new AbstractMap.SimpleEntry<>( credential, sigResults );
    }
    
    static <T> Set<FinderResult<T>> analyzeCredential( CredentialType credential, Collection<T> relatedData,
            Set<FinderResult<T>> anyDataSectionResults )
    {
        Set<FinderResult<T>> sigResults = new HashSet<>();
        SignatureObject signObj = credential.getSignatureObject();
        SignaturePtr signaturePtr = signObj.getSignaturePtr();
        Optional<byte[]> b64Signature = Optional.ofNullable( signObj.getBase64Signature() ).map( Base64Signature::getValue );
        Optional<org.w3._2000._09.xmldsig_.SignatureType> signType = Optional.ofNullable( signObj.getSignature() );
        
        if ( relatedData.isEmpty() )
        {
            // should check signatureObject/signature
            b64Signature.flatMap( data -> DataAnalyzer.findSignatures( null, Optional.of( data ) ) )
                    .ifPresent( r -> sigResults.add( new FinderResult<T>( null, r.getPresence(), r.getData() ) ) );
            
            if ( signType.map( org.w3._2000._09.xmldsig_.SignatureType::getObject ).map( l -> !l.isEmpty() ).orElse( false ) )
            {
                sigResults.add( new FinderResult<T>( null, SignaturePresence.PRESENT, Optional.empty() ) );
            }
        }
        
        for ( T dataObject : relatedData )
        {
            String oid = AIPUtil.idFromObject( dataObject );
            
            Optional<byte[]> optDataBlob = anyDataSectionResults.stream()
                    .filter( r -> oid.equals( AIPUtil.idFromObject( r.getDataContainer() ) ) )
                    .findAny()
                    .flatMap( FinderResult::getData )
                    .map( CredentialSectionAnalyzer::dataContent )
                    .or( () -> dataSupplier( dataObject ) );
            
            Optional<InputStream> optData = optDataBlob.map( ByteArrayInputStream::new );
            
            // TODO: only when optData present; is this correct?
            if ( optData.isPresent() && (signType.isPresent() || signObj.getTimestamp() != null) )
            {
                sigResults.add( new FinderResult<T>( dataObject, SignaturePresence.PRESENT, optData ) );
            }
            else if ( b64Signature.isPresent() )
            {
                b64Signature.flatMap( data -> DataAnalyzer.findSignatures( dataObject, Optional.of( data ) ) )
                        .ifPresent( sigResults::add );
            }
            else if ( signaturePtr != null )
            {
                sigResults.add( analyzePointer( signaturePtr, dataObject, optData ) );
            }
            else
            {
                sigResults.add( new FinderResult<T>( dataObject, SignaturePresence.UNKNOWN, optData ) );
            }
        }
        
        return sigResults;
    }
    
    static <T> Optional<byte[]> dataSupplier( T anyDataObj )
    {
        Optional<byte[]> data = Optional.empty();
        if ( anyDataObj instanceof MetaDataObjectType )
        {
            MetaDataObjectType metaData = (MetaDataObjectType) anyDataObj;
            data = AIPUtil.extractData( AIPUtil.binaryDataSupplier( metaData ), metaData::getXmlMetaData );
        }
        else if ( anyDataObj instanceof DataObjectType )
        {
            DataObjectType metaData = (DataObjectType) anyDataObj;
            data = AIPUtil.extractData( AIPUtil.binaryDataSupplier( metaData ), metaData::getXmlData );
        }
        
        return data;
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
    
    // anyDataObject can be either metaDataObject or dataObject
    static <T> FinderResult<T> analyzePointer( SignaturePtr pointer, T anyDataObject, Optional<InputStream> optData )
    {
        Object document = pointer.getWhichDocument();
        String oid = AIPUtil.idFromObject( document );
        
        if ( AIPUtil.idFromObject( anyDataObject ).equals( oid ) )
        {
            return new FinderResult<T>( anyDataObject, SignaturePresence.PRESENT, optData );
        }
        
        // TODO might convert document to byte[] in another form
        if ( document instanceof byte[] )
        {
            return DataAnalyzer.analyzeBinData( anyDataObject, (byte[]) document );
        }
        
        return new FinderResult<T>( anyDataObject, SignaturePresence.MISSING, optData );
    }
}
