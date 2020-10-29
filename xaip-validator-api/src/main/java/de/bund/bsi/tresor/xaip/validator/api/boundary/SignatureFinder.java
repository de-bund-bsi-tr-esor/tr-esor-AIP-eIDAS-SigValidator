package de.bund.bsi.tresor.xaip.validator.api.boundary;

import java.util.List;
import java.util.Map;

import de.bund.bsi.tr_esor.xaip._1.CredentialType;
import de.bund.bsi.tr_esor.xaip._1.DataObjectType;
import de.bund.bsi.tr_esor.xaip._1.XAIPType;

/**
 * API for the SignatureFinderModule.
 * 
 * @author wolffs
 */
public interface SignatureFinder extends ValidatorModule
{
    /**
     * Scanning the provided {@link XAIPType} for the signatures.<br>
     * Normally a {@link CredentialType} holds a signature to a related {@link DataObjectType}. In cases for embedded signatures there can
     * be a {@link CredentialType} only holding a pointer to the {@link DataObjectType} which contains the embedded signature or the
     * {@link CredentialType} does not have any {@link DataObjectType} bound to it and holds the embedded signature itself.<br>
     * <br>
     * Therefore multiple {@link CredentialType}s can be bound to an {@link DataObjectType} and in cases for embedded unbound
     * {@link CredentialType}s they relate to an empty {@link DataObjectType} which is represented in the map as <code>null</code> value.
     * 
     * @param xaip
     *            the xaip to scan for signatures
     * @return a map where containing the {@link DataObjectType} and the related {@link CredentialType}s, unbound {@link CredentialType}s
     *         are bound to the null value key
     */
    public Map<DataObjectType, List<CredentialType>> findSignatures( XAIPType xaip );
}
