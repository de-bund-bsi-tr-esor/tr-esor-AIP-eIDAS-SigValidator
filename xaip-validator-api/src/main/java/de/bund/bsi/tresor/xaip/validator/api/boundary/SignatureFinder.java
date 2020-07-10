package de.bund.bsi.tresor.xaip.validator.api.boundary;

import java.io.InputStream;
import java.util.List;

import de.bund.bsi.tr_esor.xaip._1.XAIPType;
import de.bund.bsi.tresor.xaip.validator.api.entity.SignatureCredential;

/**
 * @author wolffs
 */
public interface SignatureFinder extends ValidatorModule
{
    List<InputStream> findTimestamps( XAIPType xaip );
    
    List<SignatureCredential> findSignatures( XAIPType xaip );
}
