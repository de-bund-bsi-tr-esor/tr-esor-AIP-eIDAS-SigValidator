package de.bund.bsi.tresor.xaip.validator.signature;

import static java.util.stream.Collectors.toMap;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import de.bund.bsi.tr_esor.xaip._1.CredentialType;
import de.bund.bsi.tr_esor.xaip._1.CredentialsSectionType;
import de.bund.bsi.tr_esor.xaip._1.DataObjectType;
import de.bund.bsi.tr_esor.xaip._1.DataObjectsSectionType;
import de.bund.bsi.tr_esor.xaip._1.XAIPType;
import de.bund.bsi.tresor.xaip.validator.api.boundary.SignatureFinder;
import de.bund.bsi.tresor.xaip.validator.api.control.ModuleLogger;
import de.bund.bsi.tresor.xaip.validator.api.control.XAIPUtil;
import de.bund.bsi.tresor.xaip.validator.signature.checker.CAdESChecker;
import de.bund.bsi.tresor.xaip.validator.signature.checker.PAdESChecker;
import de.bund.bsi.tresor.xaip.validator.signature.checker.XAdESChecker;
import lombok.Getter;
import oasis.names.tc.dss._1_0.core.schema.SignatureObject;
import oasis.names.tc.dss._1_0.core.schema.SignaturePtr;

/**
 * Implementation of the SignatureFindermodule from the XAIPValidator.
 * 
 * @author wolffs
 * @author bendlera
 */
@Getter
public class DefaultSignatureFinder implements SignatureFinder
{
    private final String vendor  = "BSI";
    private final String version = "1.0.0";
    
    @Override
    public Map<String, SignatureObject> findSignatures( XAIPType xaip )
    {
        Map<String, SignatureObject> credentialSection = fromCredentialSection( xaip.getCredentialsSection() );
        Map<String, SignatureObject> dataObjectsSection = fromDataObjectsSection( xaip.getDataObjectsSection() );
        
        Map<String, SignatureObject> resultMap = new HashMap<>();
        resultMap.putAll( credentialSection );
        resultMap.putAll( dataObjectsSection );
        
        return resultMap;
    }
    
    /**
     * Selecting the signatures from the XAIP-CredentialsSection.
     * 
     * @param credentialsSection
     *            the credentialsSection
     * @return the signature objects
     */
    Map<String, SignatureObject> fromCredentialSection( CredentialsSectionType credentialsSection )
    {
        return Optional.ofNullable( credentialsSection )
                .map( CredentialsSectionType::getCredential )
                .stream()
                .flatMap( List::stream )
                .collect( toMap( CredentialType::getCredentialID, CredentialType::getSignatureObject ) );
    }
    
    /**
     * Selecting data from the DataObjectsSection.
     * 
     * @param dataSection
     *            the dataSection
     * @return the data
     */
    Map<String, SignatureObject> fromDataObjectsSection( DataObjectsSectionType dataSection )
    {
        Map<String, SignatureObject> results = new HashMap<>();
        if ( dataSection != null && dataSection.getDataObject() != null )
        {
            for ( DataObjectType dataObject : dataSection.getDataObject() )
            {
                String id = dataObject.getDataObjectID();
                try ( InputStream dataStream = XAIPUtil.retrieveContent( dataObject ) )
                {
                    byte[] data = dataStream.readAllBytes();
                    ModuleLogger.verbose( "checking dataObject " + id );
                    
                    if ( PAdESChecker.INSTANCE.isPAdES( data )
                            || CAdESChecker.INSTANCE.isCAdES( data )
                            || XAdESChecker.INSTANCE.isXAdES( data ) )
                    {
                        results.put( id, dataToSignatureObject( new ByteArrayInputStream( data ) ) );
                    }
                }
                catch ( IOException e )
                {
                    ModuleLogger.verbose( "error on dataObject " + id, e );
                }
            }
        }
        
        return results;
    }
    
    /**
     * Converting raw binary data into a {@link SignatureObject}.
     * 
     * @param contentStream
     *            the binary data containing a signature
     * @return the signature object
     */
    SignatureObject dataToSignatureObject( InputStream contentStream )
    {
        SignaturePtr signaturePtr = new SignaturePtr();
        signaturePtr.setWhichDocument( contentStream );
        
        SignatureObject signature = new SignatureObject();
        signature.setSignaturePtr( signaturePtr );
        
        return signature;
    }
    
}
