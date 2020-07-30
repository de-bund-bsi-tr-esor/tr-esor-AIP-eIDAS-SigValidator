package de.bund.bsi.tresor.xaip.validator.cli.converter;

import java.io.OutputStream;

import com.beust.jcommander.IStringConverter;

import de.bund.bsi.tresor.xaip.validator.cli.MessageBundle;
import de.bund.bsi.tresor.xaip.validator.cli.arguments.Arguments;

/**
 * A converter which is being used to parse the log {@link Arguments} from the CLI. The log entries will be printed to the retrieved
 * outputStream. When no argument is being provided, the value for the key {@link MessageBundle#ARG_DEFAULT_LOG} from the
 * {@link MessageBundle#RESOURCE} will be used to create a new file in the current directory. The system outputstream can be used by using
 * {@value #SYS_OUT} as the argument parameter.
 * 
 * @author wolffs
 */
public class LogOutputStreamConverter implements IStringConverter<OutputStream>
{
    
    @Override
    public OutputStream convert( String value )
    {
        String defaultOutputFile = MessageBundle.RESOURCE.getString( MessageBundle.ARG_DEFAULT_LOG );
        
        return OutputStreamConverter.systemOutput( value )
                .orElse( OutputStreamConverter.outputFile( defaultOutputFile, value ) );
    }
}
