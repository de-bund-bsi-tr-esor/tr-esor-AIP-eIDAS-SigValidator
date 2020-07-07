package de.bund.bsi.tresor.xaip.validator.cli.converter;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.beust.jcommander.IStringConverter;

import de.bund.bsi.tresor.xaip.validator.cli.arguments.ValidationTarget;

/**
 * @author wolffs
 */
public class ValidationTargetConverter implements IStringConverter<Optional<Set<ValidationTarget>>>
{
    @Override
    public Optional<Set<ValidationTarget>> convert( String value )
    {
        if ( !value.isBlank() )
        {
            Set<ValidationTarget> targets = new HashSet<>();
            for ( char target : value.toCharArray() )
            {
                targets.add( ValidationTarget.typeOf( target ) );
            }
            
            return Optional.of( targets );
        }
        
        return Optional.empty();
    }
}
