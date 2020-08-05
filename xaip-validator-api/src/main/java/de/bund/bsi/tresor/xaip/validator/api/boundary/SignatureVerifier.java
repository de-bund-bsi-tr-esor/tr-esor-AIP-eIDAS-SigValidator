package de.bund.bsi.tresor.xaip.validator.api.boundary;

import java.util.List;

import oasis.names.tc.dss._1_0.core.schema.SignatureObject;
import oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_.IndividualReportType;

/**
 * API for the SignatureVerificationModule.<br>
 * This module is being used to verify all signatures found by the {@link SignatureFinder} implementation. Those signatures can be any type
 * of:<br>
 * <li>ASiC
 * <li>CAdES
 * <li>PAdES
 * <li>XAdES
 * <li>Timestamp
 * 
 * @author wolffs
 */
public interface SignatureVerifier extends ValidatorModule
{
    /**
     * Verifying the provided signatures which were found by the {@link SignatureFinder} module. Each signature should result into an
     * {@link IndividualReportType}.
     * 
     * @param signatures
     *            any xaip signature which can be provided the {@link SignatureFinder}
     * @return the verification result in form of an {@link IndividualReportType}s
     */
    public List<IndividualReportType> verify( List<SignatureObject> signatures );
}
