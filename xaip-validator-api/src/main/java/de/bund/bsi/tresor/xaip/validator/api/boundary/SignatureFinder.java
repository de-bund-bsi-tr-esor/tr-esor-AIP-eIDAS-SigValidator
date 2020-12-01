package de.bund.bsi.tresor.xaip.validator.api.boundary;

import java.util.Map;
import java.util.Set;

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
     * @return a set of credentialIds mapped to the related dataObjectId
     */
    public Map<String, Set<String>> findSignatures( XAIPType xaip );
}
