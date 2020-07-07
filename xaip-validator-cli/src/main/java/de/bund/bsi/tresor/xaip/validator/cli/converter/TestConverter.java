package de.bund.bsi.tresor.xaip.validator.cli.converter;

import java.util.HashSet;
import java.util.Set;

import com.beust.jcommander.IStringConverter;

import de.bund.bsi.tresor.xaip.validator.cli.arguments.ValidationTarget;

/**
 * @author wolffs
 */
public class TestConverter implements IStringConverter<Set<ValidationTarget>>
{
    @Override
    public Set<ValidationTarget> convert( String value )
    {
        Set<ValidationTarget> targets = new HashSet<>();
        for ( char target : value.toCharArray() )
        {
            targets.add( ValidationTarget.typeOf( target ) );
        }
        
        return targets;
    }
}
