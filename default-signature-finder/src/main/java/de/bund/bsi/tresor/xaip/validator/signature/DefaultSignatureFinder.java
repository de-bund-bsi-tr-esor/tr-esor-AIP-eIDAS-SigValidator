package de.bund.bsi.tresor.xaip.validator.signature;

import static java.util.stream.Collectors.toList;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.activation.DataHandler;

import org.w3._2000._09.xmldsig_.SignatureType;
import org.w3._2000._09.xmldsig_.SignatureValueType;

import de.bund.bsi.tr_esor.xaip._1.CredentialType;
import de.bund.bsi.tr_esor.xaip._1.CredentialsSectionType;
import de.bund.bsi.tr_esor.xaip._1.DataObjectType;
import de.bund.bsi.tr_esor.xaip._1.DataObjectType.BinaryData;
import de.bund.bsi.tr_esor.xaip._1.XAIPType;
import de.bund.bsi.tresor.xaip.validator.api.boundary.SignatureFinder;
import de.bund.bsi.tresor.xaip.validator.api.entity.SignatureCredential;
import de.bund.bsi.tresor.xaip.validator.api.entity.XAIPValidatorException;
import lombok.Getter;
import oasis.names.tc.dss._1_0.core.schema.Base64Signature;
import oasis.names.tc.dss._1_0.core.schema.SignatureObject;
import oasis.names.tc.dss._1_0.core.schema.SignaturePtr;
import oasis.names.tc.dss._1_0.core.schema.Timestamp;

/**
 * @author wolffs
 */
@Getter
public class DefaultSignatureFinder implements SignatureFinder
{
    private final String vendor  = "BSI";
    private final String version = "1.0.0";
    
    @Override
    public List<InputStream> findTimestamps( XAIPType xaip )
    {
        CredentialsSectionType credentialsSection = xaip.getCredentialsSection();
        
        return credentialsSection.getCredential().stream()
                .map( CredentialType::getSignatureObject )
                .map( this::findTimestamp )
                .collect( toList() );
    }
    
    @Override
    public List<SignatureCredential> findSignatures( XAIPType xaip )
    {
        CredentialsSectionType credentialsSection = xaip.getCredentialsSection();
        
        return credentialsSection.getCredential().stream()
                .map( this::findSignatureCredentials )
                .reduce( new ArrayList<>(), ( a, b ) -> {
                    a.addAll( b );
                    return a;
                } );
    }
    
    InputStream findTimestamp( SignatureObject signatureObject )
    {
        return Optional.ofNullable( signatureObject.getTimestamp() )
                .map( Timestamp::getRFC3161TimeStampToken )
                .map( ByteArrayInputStream::new )
                .orElse( null );
    }
    
    List<SignatureCredential> findSignatureCredentials( CredentialType credentialType )
    {
        SignatureObject signatureObject = credentialType.getSignatureObject();
        List<SignatureCredential> resultList = new ArrayList<>();
        
        findXAdES( signatureObject ).ifPresent( resultList::add );
        findOtherSignatures( credentialType ).ifPresent( resultList::add );
        
        return resultList;
    }
    
    Optional<SignatureCredential> findXAdES( SignatureObject signatureObject )
    {
        return Optional.ofNullable( signatureObject.getSignature() )
                .map( SignatureType::getSignatureValue )
                .map( SignatureValueType::getValue )
                .map( ByteArrayInputStream::new )
                .map( SignatureCredential::embedded );
        
    }
    
    Optional<SignatureCredential> findOtherSignatures( CredentialType signatureCredential )
    {
        SignatureObject signatureObject = signatureCredential.getSignatureObject();
        
        if ( signatureObject.getBase64Signature() != null )
        {
            InputStream detached = Optional.of( signatureObject.getBase64Signature() )
                    .map( Base64Signature::getValue )
                    .map( ByteArrayInputStream::new )
                    .orElseThrow( () -> new XAIPValidatorException( "TODO" ) );
            
            return findOriginalDocument( signatureCredential )
                    .map( this::streamDataObject )
                    .map( in -> SignatureCredential.detached( in, detached ) );
        }
        
        return Optional.ofNullable( signatureObject.getSignaturePtr() )
                .map( SignaturePtr::getWhichDocument )
                .filter( DataObjectType.class::isInstance )
                .map( DataObjectType.class::cast )
                .map( this::streamDataObject )
                .map( SignatureCredential::embedded );
    }
    
    Optional<DataObjectType> findOriginalDocument( CredentialType signatureCredential )
    {
        List<Object> relatedObjects = signatureCredential.getRelatedObjects();
        if ( relatedObjects.size() != 1 )
        {
            throw new XAIPValidatorException(
                    "Only one related object in credential type supported - found: " + relatedObjects.size() );
        }
        
        return relatedObjects.stream().findAny()
                .filter( DataObjectType.class::isInstance )
                .map( DataObjectType.class::cast );
    }
    
    InputStream streamDataObject( DataObjectType data )
    {
        try
        {
            BinaryData binaryData = data.getBinaryData();
            DataHandler dataHandler = binaryData.getValue();
            
            return dataHandler.getInputStream();
        }
        catch ( NullPointerException | IOException e )
        {
            // TODO verbose logger?
            throw new XAIPValidatorException( "TODO", e );
        }
    }
}
