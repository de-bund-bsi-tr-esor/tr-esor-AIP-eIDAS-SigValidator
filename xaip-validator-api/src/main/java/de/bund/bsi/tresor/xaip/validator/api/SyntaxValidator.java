package de.bund.bsi.tresor.xaip.validator.api;

import java.io.ByteArrayInputStream;

import oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_.VerificationReportType;

/**
 * @author wolffs
 */
public interface SyntaxValidator
{
    VerificationReportType validateSyntax( ByteArrayInputStream xaipCandidate );
}
