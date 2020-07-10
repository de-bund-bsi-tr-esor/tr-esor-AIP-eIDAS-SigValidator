package de.bund.bsi.tresor.xaip.validator.signature;

import java.util.List;

import de.bund.bsi.tresor.xaip.validator.api.boundary.SignatureVerifier;
import de.bund.bsi.tresor.xaip.validator.api.entity.SignatureCredential;
import lombok.Getter;
import oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_.IndividualReportType;

/**
 * @author wolffs
 */
@Getter
public class DefaultSignatureVerifier implements SignatureVerifier
{
    private final String vendor  = "BSI";
    private final String version = "1.0.0";
    
    @Override
    public List<IndividualReportType> verify( List<SignatureCredential> signatures )
    {
        // TODO Auto-generated method stub
        return null;
    }
    
}
