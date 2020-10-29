package de.bund.bsi.tresor.xaip.validator.api.boundary;

import java.util.List;
import java.util.Map;

import de.bund.bsi.tr_esor.vr._1.CredentialValidityType;
import de.bund.bsi.tr_esor.xaip._1.CredentialType;
import de.bund.bsi.tr_esor.xaip._1.DataObjectType;
import oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_.IndividualReportType;

/**
 * API for the SignatureVerificationModule.<br>
 * This module is being used to verify all signatures found by the {@link SignatureFinder} implementation.
 * 
 * @author wolffs
 */
public interface SignatureVerifier extends ValidatorModule
{
    /**
     * Verifying the provided signatures which were found by the {@link SignatureFinder} module. Each signature should result into an
     * {@link CredentialValidityType}.
     * 
     * @param signatures
     *            any xaip signature which can be provided the {@link SignatureFinder}
     * @return the verification result in form of an {@link IndividualReportType}s
     */
    public List<CredentialValidityType> verify( Map<DataObjectType, List<CredentialType>> signatures );
}
