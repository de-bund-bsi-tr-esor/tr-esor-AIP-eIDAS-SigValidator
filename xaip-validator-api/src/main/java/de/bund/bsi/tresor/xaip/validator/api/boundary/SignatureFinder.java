package de.bund.bsi.tresor.xaip.validator.api.boundary;

import java.util.List;
import java.util.Map;

import de.bund.bsi.tr_esor.xaip._1.CredentialType;
import de.bund.bsi.tr_esor.xaip._1.DataObjectType;
import de.bund.bsi.tr_esor.xaip._1.XAIPType;
import oasis.names.tc.dss._1_0.core.schema.SignatureObject;

/**
 * API for the SignatureFinderModule.
 * 
 * @author wolffs
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
    public Map<DataObjectType, List<CredentialType>> findSignatures( XAIPType xaip );
}
