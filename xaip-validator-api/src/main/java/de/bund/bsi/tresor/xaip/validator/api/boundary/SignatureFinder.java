package de.bund.bsi.tresor.xaip.validator.api.boundary;

import static java.util.Collections.emptyList;

import java.util.List;

import de.bund.bsi.tr_esor.xaip._1.XAIPType;
import oasis.names.tc.dss._1_0.core.schema.SignatureObject;
import oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_.IndividualReportType;

/**
 * API for the SignatureFinderModule.
 * 
 * @author wolffs
 * @author bendlera
 */
public interface SignatureFinder extends ValidatorModule
{
    /**
     * Scanning the provided {@link XAIPType} for signatures and parsing those into a {@link SignatureObject} which can be used by the
     * {@link SignatureVerifier} to verify those signatures. Which signatures are being retrieved from the XAIP is specified by the
     * implementing method.
     * 
     * @param xaip
     *            the xaip to scan for signatures
     * @return signatureObjects created from found signatures of a type specified by the implementor
     */
    public List<SignatureObject> findSignatures( XAIPType xaip );
    
    /**
     * Verifying data references in a LXAIP. Each data reference should result into an {@link IndividualReportType}.
     * 
     * @param xaip
     *            the xaip to scan for data references
     * @return the verification result in form of an {@link IndividualReportType}s
     */
    default public List<IndividualReportType> verifyDataReference( XAIPType xaip )
    {
        return emptyList();
    };
}
