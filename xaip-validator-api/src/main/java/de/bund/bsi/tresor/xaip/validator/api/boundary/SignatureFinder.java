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
     * Scanning the provided {@link XAIPType} for signatures.<br>
     * Normally a {@link CredentialType} holds a signature to a related {@link DataObjectType}. In cases for embedded signatures there can
     * be any permutation of a {@link CredentialType} holding only a pointer to the {@link DataObjectType} which contains the embedded
     * signature, the {@link CredentialType} not having any {@link DataObjectType} bound to it and holding the embedded signature itself or
     * the {@link DataObjectType} containing the embedded signature without any related {@link CredentialType}.<br>
     * <br>
     * Therefore multiple {@link CredentialType}s can be bound to an {@link DataObjectType} and in cases for embedded unbound
     * {@link CredentialType}s they relate to an empty {@link DataObjectType} which is represented in the map as <code>null</code> value.
     * 
     * @param xaip
     *            the xaip to analyze
     * @return a set of credentialIds as value mapped to the related dataObjectId as the key
     */
    public Map<String, Set<String>> findSignatures( XAIPType xaip );
}
