package de.bund.bsi.tresor.xaip.validator.cli.arguments;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import com.beust.jcommander.DynamicParameter;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import de.bund.bsi.tresor.xaip.validator.cli.MessageBundle;
import de.bund.bsi.tresor.xaip.validator.cli.converter.InputStreamConverter;
import de.bund.bsi.tresor.xaip.validator.cli.converter.OutputStreamConverter;
import de.bund.bsi.tresor.xaip.validator.dispatcher.DispatcherArguments;
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
    // providing description for the application itself and catching all unparsed remaining arguments
    @DynamicParameter( names = "-M", descriptionKey = MessageBundle.CLI_INFO )
    private Map<String, String> moduleConfig = new HashMap<>();
    
    @Parameter( order = 1, names = { "-i", "--in", "--input" },
            descriptionKey = MessageBundle.CLI_USAGE_INPUT, converter = InputStreamConverter.class )
    private InputStream         input        = System.in;
    
    @Parameter( order = 2, names = { "-o", "--out", "--output" },
            descriptionKey = MessageBundle.CLI_USAGE_OUTPUT, converter = OutputStreamConverter.class )
    private OutputStream        output       = System.out;
    
    @Parameter( order = 3, names = { "-v", "--verify" }, descriptionKey = MessageBundle.CLI_USAGE_VERIFY )
    private boolean             verify;
    
    @Parameter( order = 4, names = { "-d", "--debug", "--verbose" }, descriptionKey = MessageBundle.CLI_USAGE_VERBOSE )
    private boolean             verbose;
    
    @Parameter( order = 5, names = { "-l", "--log" },
            descriptionKey = MessageBundle.CLI_USAGE_LOG, converter = OutputStreamConverter.class )
    private OutputStream        log          = System.err;
    
    @Parameter( order = 6, names = { "-h", "--help" }, help = true, descriptionKey = MessageBundle.CLI_USAGE_HELP )
    private boolean             help;
}