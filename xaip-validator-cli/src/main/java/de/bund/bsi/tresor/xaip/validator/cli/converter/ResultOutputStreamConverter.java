package de.bund.bsi.tresor.xaip.validator.cli.converter;

import java.io.OutputStream;

import com.beust.jcommander.IStringConverter;

import de.bund.bsi.tresor.xaip.validator.cli.MessageBundle;
import de.bund.bsi.tresor.xaip.validator.cli.arguments.Arguments;

/**
 * A converter which is being used to parse the output {@link Arguments} from the CLI. The result of the validator will be written to this
 * outputStream. The system outputstream can be used by using {@value #SYS_OUT} as the argument parameter.
 * 
 * @author wolffs
 */
public class ResultOutputStreamConverter implements IStringConverter<OutputStream>
{
    @Override
    public OutputStream convert( String value )
    {
        String defaultOutputFile = MessageBundle.RESOURCE.getString( MessageBundle.ARG_DEFAULT_OUTPUT );
        
        return OutputStreamConverter.systemOutput( value )
                .orElse( OutputStreamConverter.outputFile( defaultOutputFile, value ) );
    }
}
