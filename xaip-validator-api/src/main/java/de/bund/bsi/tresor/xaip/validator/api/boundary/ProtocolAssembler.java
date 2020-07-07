package de.bund.bsi.tresor.xaip.validator.api.boundary;

import java.util.Collection;

import oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_.IndividualReportType;
import oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_.VerificationReportType;

/**
 * @author wolffs
 */
public interface ProtocolAssembler extends ValidatorModule
{
    VerificationReportType assembleProtocols( Collection<IndividualReportType> protocols );
}
