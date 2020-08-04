package de.bund.bsi.tresor.xaip.validator.soap.config;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import com.beust.jcommander.DynamicParameter;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wolffs
 */
@Data
@NoArgsConstructor
@Parameters( commandDescriptionKey = "cli" )
public class ServerConfig
{
    
    // providing description for the application itself and catching all unparsed remaining arguments
    @DynamicParameter( names = "-M", descriptionKey = MessageBundle.SERVER_INFO )
    private Map<String, String> moduleConfig = new HashMap<>();
    
    @Parameter( order = 1, names = { "-p", "--port" },
            descriptionKey = MessageBundle.SERVER_PORT )
    private int                 port         = 8080;
    
    @Parameter( order = 2, names = { "-P", "--protocol" },
            descriptionKey = MessageBundle.SERVER_PROTOCOL )
    private String              protocol     = "http";
    
    @Parameter( order = 3, names = { "-H", "--host" },
            descriptionKey = MessageBundle.SERVER_HOST )
    private String              host         = "localhost";
    
    @Parameter( order = 4, names = { "--path" },
            descriptionKey = MessageBundle.SERVER_PATH )
    private String              path         = "/xaip-validate";
    
    @Parameter( order = 5, names = { "-d", "--debug", "--verbose" }, descriptionKey = MessageBundle.SERVER_VERBOSE )
    private boolean             verbose      = false;
    
    @Parameter( order = 6, names = { "-h", "--help" }, help = true, descriptionKey = MessageBundle.SERVER_HELP )
    private boolean             help;
    
    // -- Following values are default parameter for the server
    private final OutputStream  log          = System.out;
    
    private final boolean       verify       = true;
}
