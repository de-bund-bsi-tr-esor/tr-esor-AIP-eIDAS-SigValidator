package de.bund.bsi.tresor.xaip.validator.api.boundary;

import java.util.Collection;

import de.bund.bsi.tr_esor.vr._1.CredentialValidityType;
import de.bund.bsi.tr_esor.vr._1.XAIPValidityType;
import oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_.VerificationReportType;

/**
 * API for the ProtocolAssemblerModule.
 * 
 * @author wolffs
 */
public interface ProtocolAssembler extends ValidatorModule
{
    /**
     * Using the {@link XAIPValidityType} created by the {@link SyntaxValidator} and adding the informations retrieved by the
     * {@link SignatureVerifier} module
     * 
     * @param xaipReport
     *            the xaipReport of the syntax validation module
     * @param credentialReports
     *            the individual reports from the verification module
     * @return a reports representing the overall result of the validation and the intermediate reports
     */
    public VerificationReportType assembleProtocols( XAIPValidityType xaipReport, Collection<CredentialValidityType> credentialReports );
}
