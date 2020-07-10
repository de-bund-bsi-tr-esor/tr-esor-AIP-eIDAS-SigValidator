package de.bund.bsi.tresor.xaip.validator.api.boundary;

import java.util.List;

import de.bund.bsi.tresor.xaip.validator.api.entity.SignatureCredential;
import oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_.IndividualReportType;

/**
 * @author wolffs
 */
public interface SignatureVerifier extends ValidatorModule
{
    List<IndividualReportType> verify( List<SignatureCredential> signatures );
}
