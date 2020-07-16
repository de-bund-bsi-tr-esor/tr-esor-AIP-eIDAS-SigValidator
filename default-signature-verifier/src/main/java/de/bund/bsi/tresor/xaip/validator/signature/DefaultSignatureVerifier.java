package de.bund.bsi.tresor.xaip.validator.signature;

import java.util.List;

import de.bund.bsi.ecard.api._1.VerifyRequest;
import de.bund.bsi.tresor.xaip.validator.api.boundary.SignatureVerifier;
import lombok.Getter;
import oasis.names.tc.dss._1_0.core.schema.SignatureObject;
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
    public List<IndividualReportType> verify( List<SignatureObject> signatures )
    {
        VerifyRequest req = new VerifyRequest();
        de.bund.bsi.ecard.api._1.SignatureObject so;
        req.getSignatureObject().add( so );
        return null;
    }
}
