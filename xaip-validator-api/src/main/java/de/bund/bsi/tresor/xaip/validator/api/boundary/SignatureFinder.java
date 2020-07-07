package de.bund.bsi.tresor.xaip.validator.api.boundary;

import java.util.List;
import java.util.Map;
import java.util.Set;

import de.bund.bsi.tresor.xaip.validator.api.entity.ValidationTarget;

/**
 * @author wolffs
 */
public interface SignatureFinder extends ValidatorModule
{
    // TODO change list type
    Map<ValidationTarget, List<?>> find( Set<ValidationTarget> targets );
}
