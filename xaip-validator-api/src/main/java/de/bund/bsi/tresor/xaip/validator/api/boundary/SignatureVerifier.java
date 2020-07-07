package de.bund.bsi.tresor.xaip.validator.api.boundary;

import java.util.List;
import java.util.Map;

import de.bund.bsi.tresor.xaip.validator.api.entity.ValidationTarget;
import oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_.IndividualReportType;

/**
 * @author wolffs
 */
public interface SignatureVerifier extends ValidatorModule
{
    // TODO change list type
    List<IndividualReportType> verify( Map<ValidationTarget, List<?>> signatureLocations );
}
