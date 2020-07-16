package de.bund.bsi.tresor.xaip.validator.api.boundary;

import java.util.List;

import oasis.names.tc.dss._1_0.core.schema.SignatureObject;
import oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_.IndividualReportType;

/**
 * @author wolffs
 */
public interface SignatureVerifier extends ValidatorModule
{
    public List<IndividualReportType> verify( List<SignatureObject> signatures );
}
