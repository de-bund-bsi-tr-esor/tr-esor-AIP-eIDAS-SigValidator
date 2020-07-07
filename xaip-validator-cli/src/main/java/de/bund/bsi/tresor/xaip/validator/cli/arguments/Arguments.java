package de.bund.bsi.tresor.xaip.validator.cli.arguments;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.beust.jcommander.converters.URIConverter;

import de.bund.bsi.tresor.xaip.validator.cli.DispatcherArguments;
import de.bund.bsi.tresor.xaip.validator.cli.MessageBundle;
import de.bund.bsi.tresor.xaip.validator.cli.converter.InputStreamConverter;
import de.bund.bsi.tresor.xaip.validator.cli.converter.OutputStreamConverter;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents all possible CLI arguments.
 * 
 * @author wolffs
 */
@Data
@NoArgsConstructor
@Parameters( commandDescriptionKey = "cli" )
public class Arguments implements DispatcherArguments
{
    @Parameter( descriptionKey = MessageBundle.CLI_INFO )
    private String       cliInfo;
    
    @Parameter( order = 1, names = { "-in", "--input" },
            descriptionKey = MessageBundle.CLI_USAGE_INPUT, converter = InputStreamConverter.class )
    private InputStream  input;
    
    @Parameter( order = 2, names = { "-out", "--output" },
            descriptionKey = MessageBundle.CLI_USAGE_OUTPUT, converter = OutputStreamConverter.class )
    private OutputStream output;
    
    @Parameter( order = 3, names = { "-e", "--eCardUrl" },
            descriptionKey = MessageBundle.CLI_USAGE_ECARD_URL, converter = URIConverter.class )
    private URI          eCardUrl;
    
    @Parameter( order = 4, names = { "-h", "--help" }, descriptionKey = MessageBundle.CLI_USAGE_VERIFY )
    private boolean      verify;
    
    @Parameter( order = 5, names = { "-h", "--help" }, help = true, descriptionKey = MessageBundle.CLI_USAGE_HELP )
    private boolean      help = false;
    
}
