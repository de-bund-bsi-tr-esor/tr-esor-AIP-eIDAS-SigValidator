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
import de.bund.bsi.tresor.xaip.validator.signature.checker.CAdESChecker;
import de.bund.bsi.tresor.xaip.validator.signature.checker.PAdESChecker;
import de.bund.bsi.tresor.xaip.validator.signature.checker.XAdESChecker;
import lombok.Getter;
import oasis.names.tc.dss._1_0.core.schema.SignatureObject;

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
    
    List<SignatureObject> fromCredentialSection( CredentialsSectionType credentialSection )
    {
        return Optional.ofNullable( credentialSection )
                .map( CredentialsSectionType::getCredential )
                .orElseGet( ArrayList::new )
                .stream()
                .map( CredentialType::getSignatureObject )
                .collect( toList() );
    }
    
    List<SignatureObject> fromDataObjectsSection( DataObjectsSectionType dataSection )
    {
        return dataSection.getDataObject().stream()
                .map( DataObjectType::getBinaryData )
                .map( BinaryData::getValue )
                .map( this::asData )
                .filter( data -> PAdESChecker.INSTANCE.isPAdES( data )
                        || CAdESChecker.INSTANCE.isCAdES( data )
                        || XAdESChecker.INSTANCE.isXAdES( data ) )
                .map( this::convert )
                .collect( toList() );
    }
    
    public SignatureObject convert( byte[] data )
    {
        return null;
    }
    
    byte[] asData( DataHandler handler )
    {
        try
        {
            return handler.getInputStream().readAllBytes();
        }
        catch ( IOException e )
        {
            // TODO exceptionhandling
            e.printStackTrace();
        }
        return new byte[0];
    }
    
}
