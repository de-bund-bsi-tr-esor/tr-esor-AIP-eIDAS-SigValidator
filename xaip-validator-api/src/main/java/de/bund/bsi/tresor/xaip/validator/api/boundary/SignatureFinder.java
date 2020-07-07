package de.bund.bsi.tresor.xaip.validator.api.boundary;

import java.util.List;

import de.bund.bsi.tr_esor.xaip._1.XAIPType;
import de.bund.bsi.tresor.xaip.validator.api.entity.ValidationTarget;
import lombok.Value;

/**
 * @author wolffs
 */
public interface SignatureFinder extends ValidatorModule
{
    List<Signature> find( XAIPType xaip );
    
    @Value
    class Signature
    {
        ValidationTarget type;
        // TODO value type
    }
}
