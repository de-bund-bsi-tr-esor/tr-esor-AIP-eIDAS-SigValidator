package de.bund.bsi.tresor.xaip.validator.api.boundary;

import java.util.List;

import de.bund.bsi.tr_esor.xaip._1.XAIPType;
import oasis.names.tc.dss._1_0.core.schema.SignatureObject;

/**
 * @author wolffs
 */
public interface SignatureFinder extends ValidatorModule
{
    public List<SignatureObject> findSignatures( XAIPType xaip );
}
