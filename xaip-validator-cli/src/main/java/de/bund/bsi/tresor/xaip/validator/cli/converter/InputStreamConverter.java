package de.bund.bsi.tresor.xaip.validator.cli.converter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Path;

import com.beust.jcommander.IStringConverter;

import de.bund.bsi.tresor.xaip.validator.api.entity.XAIPValidatorException;
import de.bund.bsi.tresor.xaip.validator.cli.arguments.Arguments;

/**
 * A converter which is being used to parse the input {@link Arguments} from the CLI. When this argument is being used, the provided value
 * will be parsed as a {@link Path} to read the XAIP from this file.
 * 
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
