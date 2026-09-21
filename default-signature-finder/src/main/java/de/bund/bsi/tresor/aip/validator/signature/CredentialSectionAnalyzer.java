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
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.etsi.uri._02918.v1_2.DataObjectReferenceType;
import org.jose4j.json.JsonUtil;

import de.bund.bsi.tr_esor.xaip.CredentialType;
import de.bund.bsi.tr_esor.xaip.DataObjectType;
import de.bund.bsi.tr_esor.xaip.DataObjectsSectionType;
import de.bund.bsi.tr_esor.xaip.ExtensionType;
import de.bund.bsi.tr_esor.xaip.MetaDataObjectType;
import de.bund.bsi.tr_esor.xaip.MetaDataSectionType;
import de.bund.bsi.tresor.aip.validator.api.control.AIPUtil;
import de.bund.bsi.tresor.aip.validator.api.control.ModuleLogger;
import de.bund.bsi.tresor.aip.validator.signature.entity.FinderResult;
import de.bund.bsi.tresor.aip.validator.signature.entity.SignaturePresence;
import eu.europa.esig.dss.jades.DSSJsonUtils;
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
    
    /**
     * Analyzing the credential and searching for any type of signatures
     * 
     * @param <T>
     *            the related data type
     * @param credential
     *            the credential
     * @param relatedData
     *            the related data
     * @param anyDataSectionResults
     *            related data results
     *            
     * @return a set of results
     */
    static <T> Set<FinderResult<T>> analyzeCredential( CredentialType credential, Collection<T> relatedData,
            Set<FinderResult<T>> anyDataSectionResults )
    {
        Set<FinderResult<T>> sigResults = new HashSet<>();
        Optional<byte[]> lxaipData = Optional.ofNullable( credential.getOther() )
                .map( ExtensionType::getAny )
                .flatMap( AIPUtil::findDataReferences )
                .map( DataObjectReferenceType::getURI )
                .map( CredentialSectionAnalyzer::dataFromURI );
        
        Optional<SignatureObject> signObj = Optional.ofNullable( credential.getSignatureObject() );
        Optional<SignaturePtr> optPtr = signObj.map( SignatureObject::getSignaturePtr );
        Optional<byte[]> b64Signature = signObj.map( SignatureObject::getBase64Signature ).map( Base64Signature::getValue );
        Optional<org.w3._2000._09.xmldsig_.SignatureType> signType = signObj.map( SignatureObject::getSignature );
        
        if ( relatedData.isEmpty() ) // should check signatureObject/signature
        {
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
            if ( lxaipData.isPresent() )
            {
                sigResults.add( new FinderResult<T>( dataObject, SignaturePresence.PRESENT, lxaipData.map( ByteArrayInputStream::new ) ) );
            }
            // TODO: only when optData present; is this correct?
            else if ( optData.isPresent() && (signType.isPresent() || signObj.map( SignatureObject::getTimestamp ).isPresent()) )
            {
                sigResults.add( new FinderResult<T>( dataObject, SignaturePresence.PRESENT, optData ) );
            }
            else if ( b64Signature.isPresent() )
            {
                findSignatureInPossiblyDetachedJws( b64Signature.get(), optDataBlob, dataObject ).ifPresent( sigResults::add );
            }
            else if ( optPtr.isPresent() )
            {
                sigResults.add( analyzePointer( optPtr.get(), dataObject, optData ) );
            }
            else
            {
                sigResults.add( new FinderResult<T>( dataObject, SignaturePresence.UNKNOWN, optData ) );
            }
        }
        
        return sigResults;
    }
    
    /**
     * Analyzing a credential's base64 signature, substituting a detached JWS payload from the related dataObject first if applicable
     *
     * @param <T>
     *            the related data type
     * @param signature
     *            the (potentially detached) signature bytes
     * @param payload
     *            the related dataObject's content, if any
     * @param dataObject
     *            the dataObject related to the signature
     * @return the finder result
     */
    static <T> Optional<FinderResult<T>> findSignatureInPossiblyDetachedJws( byte[] signature, Optional<byte[]> payload, T dataObject )
    {
        Optional<byte[]> effectiveSignature = payload.flatMap( p -> reconstructDetachedJws( signature, p ) )
                .or( () -> Optional.of( signature ) );

        return effectiveSignature.flatMap( data -> DataAnalyzer.findSignatures( dataObject, Optional.of( data ) ) );
    }

    /**
     * Reconstructing a detached JWS compact serialization (RFC 7797 style: {@code header..signature}, empty payload segment because the
     * payload is transported separately as its own dataObject instead of being embedded) by substituting the related dataObject's
     * content into the empty payload segment. The dataObject's content is already the base64url-encoded payload segment text (that is
     * how the test corpus stores it), so it is inserted as-is, without re-encoding. Any disclosure suffix ({@code ~<disclosure>~...}) of
     * an SD-JWT is preserved as-is. Returns {@link Optional#empty()} for anything that does not match this exact pattern (i.e. every
     * non-JWS signature and every JWS which already carries its own payload), leaving those callers to use the original signature bytes
     * unchanged.
     *
     * @param signature
     *            the (potentially detached) signature bytes
     * @param payload
     *            the related dataObject's content (the base64url-encoded JWS payload segment)
     * @return the reconstructed compact serialization, or empty if the signature does not look like a detached JWS
     */
    static Optional<byte[]> reconstructDetachedJws( byte[] signature, byte[] payload )
    {
        String text = new String( signature, StandardCharsets.US_ASCII );
        int tilde = text.indexOf( '~' );
        String jwsPart = tilde >= 0 ? text.substring( 0, tilde ) : text;
        String disclosureSuffix = tilde >= 0 ? text.substring( tilde ) : "";

        String[] segments = jwsPart.split( "\\.", -1 );
        if ( segments.length != 3 || !segments[1].isEmpty() || !isJwsHeader( segments[0] ) )
        {
            return Optional.empty();
        }

        String encodedPayload = new String( payload, StandardCharsets.US_ASCII );
        String reconstructed = segments[0] + "." + encodedPayload + "." + segments[2] + disclosureSuffix;

        return Optional.of( reconstructed.getBytes( StandardCharsets.US_ASCII ) );
    }

    /**
     * Checking whether a base64url segment decodes to a JWS/JAdES protected header, i.e. a JSON object carrying an {@code alg} claim
     * (RFC 7515 4.1.1). This distinguishes an actual detached JWS header from an unrelated binary signature (e.g. CAdES/PAdES/XAdES DER)
     * that merely happens to contain two adjacent {@code '.'} bytes.
     *
     * @param headerSegment
     *            the first compact-serialization segment
     * @return {@code true} if the segment is a valid JWS protected header
     */
    private static boolean isJwsHeader( String headerSegment )
    {
        try
        {
            Map<String, Object> header = JsonUtil.parseJson( new String( DSSJsonUtils.fromBase64Url( headerSegment ), StandardCharsets.UTF_8 ) );
            return header.containsKey( "alg" );
        }
        catch ( Exception e )
        {
            return false;
        }
    }

    static byte[] dataFromURI( String url )
    {
        try
        {
            return Files.readAllBytes( Paths.get( URI.create( url ) ) );
        }
        catch( IllegalArgumentException e )
        {
            return AIPUtil.loadFileFromRelativeURI( e, url );
        }
        catch ( IOException e )
        {
            // could not read lxaip data
            ModuleLogger.verbose( "could not retrieve lxaip data from dataObject", e );
        }
        
        return null;
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
