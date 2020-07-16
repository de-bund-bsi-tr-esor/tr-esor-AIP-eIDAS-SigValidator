package de.bund.bsi.tresor.xaip.validator.cli.converter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.beust.jcommander.IStringConverter;

import de.bund.bsi.tresor.xaip.validator.api.entity.XAIPValidatorException;

/**
 * @author wolffs
 */
public class InputStreamConverter implements IStringConverter<InputStream>
{
    @Override
    public InputStream convert( String value )
    {
        try
        {
            return new FileInputStream( value );
        }
        catch ( FileNotFoundException e )
        {
            throw new XAIPValidatorException( "invalid input param " + value, e );
        }
    }
}
