package de.bund.bsi.tresor.xaip.validator.signature;

import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.activation.DataHandler;

import de.bund.bsi.tr_esor.xaip._1.CredentialType;
import de.bund.bsi.tr_esor.xaip._1.CredentialsSectionType;
import de.bund.bsi.tr_esor.xaip._1.DataObjectType;
import de.bund.bsi.tr_esor.xaip._1.DataObjectType.BinaryData;
import de.bund.bsi.tr_esor.xaip._1.DataObjectsSectionType;
import de.bund.bsi.tr_esor.xaip._1.XAIPType;
import de.bund.bsi.tresor.xaip.validator.api.boundary.SignatureFinder;
import de.bund.bsi.tresor.xaip.validator.api.control.ModuleLogger;
import de.bund.bsi.tresor.xaip.validator.signature.checker.CAdESChecker;
import de.bund.bsi.tresor.xaip.validator.signature.checker.PAdESChecker;
import de.bund.bsi.tresor.xaip.validator.signature.checker.XAdESChecker;
import lombok.Getter;
import oasis.names.tc.dss._1_0.core.schema.SignatureObject;
import oasis.names.tc.dss._1_0.core.schema.SignaturePtr;

/**
 * @author wolffs
 */
@Getter
public class DefaultSignatureFinder implements SignatureFinder
{
    private final String vendor  = "BSI";
    private final String version = "1.0.0";
    
    @Override
    public List<SignatureObject> findSignatures( XAIPType xaip )
    {
        List<SignatureObject> credentialSection = fromCredentialSection( xaip.getCredentialsSection() );
        List<SignatureObject> dataObjectsSection = fromDataObjectsSection( xaip.getDataObjectsSection() );
        
        List<SignatureObject> resultList = new ArrayList<>();
        resultList.addAll( credentialSection );
        resultList.addAll( dataObjectsSection );
        
        return resultList;
    }
    
    /**
     * Selecting the signatures from the XAIP-CredentialsSection.
     * 
     * @param credentialsSection
     *            the credentialsSection
     * @return the signature objects
     */
    List<SignatureObject> fromCredentialSection( CredentialsSectionType credentialsSection )
    {
        return Optional.ofNullable( credentialsSection )
                .map( CredentialsSectionType::getCredential )
                .orElseGet( ArrayList::new )
                .stream()
                .map( CredentialType::getSignatureObject )
                .collect( toList() );
    }
    
    /**
     * Selecting possible signatures from the XAIP-DataObjectsSection.
     * 
     * @param dataSection
     *            the dataSection
     * @return the signature objects by converting potential signature data
     */
    List<SignatureObject> fromDataObjectsSection( DataObjectsSectionType dataSection )
    {
        List<SignatureObject> results = new ArrayList<>();
        for ( DataObjectType dataObject : dataSection.getDataObject() )
        {
            String id = dataObject.getDataObjectID();
            byte[] data = Optional.ofNullable( dataObject.getBinaryData() )
                    .map( BinaryData::getValue )
                    .map( this::asData )
                    .orElse( new byte[0] );
            
            ModuleLogger.verbose( "checking dataObject " + id );
            if ( PAdESChecker.INSTANCE.isPAdES( data )
                    || CAdESChecker.INSTANCE.isCAdES( data )
                    || XAdESChecker.INSTANCE.isXAdES( data ) )
            {
                results.add( convert( data ) );
            }
        }
        
        return results;
    }
    
    /**
     * Converting raw binary data into a {@link SignatureObject}.
     * 
     * @param data
     *            the binary data containing a signature
     * @return the signature object
     */
    SignatureObject convert( byte[] data )
    {
        SignaturePtr signaturePtr = new SignaturePtr();
        signaturePtr.setWhichDocument( data );
        
        SignatureObject signature = new SignatureObject();
        signature.setSignaturePtr( signaturePtr );
        
        return signature;
    }
    
    /**
     * Retrieving the binary data from a data handler.
     * 
     * @param handler
     *            the data handler
     * @return the binary data
     */
    byte[] asData( DataHandler handler )
    {
        try
        {
            return handler.getInputStream().readAllBytes();
        }
        catch ( IOException e )
        {
            ModuleLogger.verbose( "could not retrieve data from dataObject", e );
        }
        
        return new byte[0];
    }
    
}
