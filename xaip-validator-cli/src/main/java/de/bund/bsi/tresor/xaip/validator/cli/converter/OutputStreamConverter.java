package de.bund.bsi.tresor.xaip.validator.cli.converter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.beust.jcommander.IStringConverter;

import de.bund.bsi.tresor.xaip.validator.api.XAIPValidatorException;

/**
 * @author wolffs
 */
public class OutputStreamConverter implements IStringConverter<OutputStream>
{
    @Override
    public OutputStream convert( String value )
    {
        try
        {
            return new FileOutputStream( value == null ? System.getProperty( "user.dir" ) : value );
        }
        catch ( FileNotFoundException e )
        {
            throw new XAIPValidatorException( "invalid output param " + value, e );
        }
    }
}
