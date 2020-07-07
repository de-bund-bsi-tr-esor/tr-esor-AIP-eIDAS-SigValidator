package de.bund.bsi.tresor.xaip.validator.api;

import java.util.Collection;

import oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_.VerificationReportType;

/**
 * @author wolffs
 */
public interface ProtocolAssembler
{
    VerificationReportType assembleProtocols( Collection<VerificationReportType> protocols );
}
