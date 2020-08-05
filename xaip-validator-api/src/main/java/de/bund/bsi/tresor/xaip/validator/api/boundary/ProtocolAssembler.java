package de.bund.bsi.tresor.xaip.validator.api.boundary;

import java.util.Collection;

import oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_.IndividualReportType;
import oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_.VerificationReportType;

/**
 * API for the ProtocolAssemblerModule.
 * 
 * @author wolffs
 */
public interface ProtocolAssembler extends ValidatorModule
{
    /**
     * Assembling the {@link IndividualReportType} created by the other modules into a single {@link VerificationReportType}. The resulting
     * {@link VerificationReportType} will represent the overall status of the XAIP validaton.
     * 
     * @param protocols
     *            the individual reports from the validation modules
     * @return a reports representing the overall result of the validation and the intermediate reports
     */
    public VerificationReportType assembleProtocols( Collection<IndividualReportType> protocols );
}
