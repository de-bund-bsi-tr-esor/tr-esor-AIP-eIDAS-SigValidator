package de.bund.bsi.tresor.xaip.validator.api;

import oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_.VerificationReportType;

/**
 * @author wolffs
 */
public interface SignatureVerifier
{
    VerificationReportType verifyASiC();
    
    VerificationReportType verifyCAdES();
    
    VerificationReportType verifyPAdES();
    
    VerificationReportType verifyTSP();
    
    VerificationReportType verifyXAdES();
}
