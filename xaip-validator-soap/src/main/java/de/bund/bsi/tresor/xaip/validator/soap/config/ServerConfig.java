package de.bund.bsi.tresor.xaip.validator.soap.config;

import java.util.HashMap;
import java.util.Map;

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
    @Parameter( descriptionKey = MessageBundle.SERVER_INFO )
    private Map<String, String> moduleConfig = new HashMap<>();
    
    @Parameter( order = 1, names = { "-p", "--port" },
            descriptionKey = MessageBundle.SERVER_PORT )
    private int                 port         = 8080;
    
    @Parameter( order = 1, names = { "-P", "--protocol" },
            descriptionKey = MessageBundle.SERVER_PROTOCOL )
    private String              protocol     = "http";
    
    @Parameter( order = 1, names = { "-H", "--host" },
            descriptionKey = MessageBundle.SERVER_HOST )
    private String              host         = "localhost";
    
    @Parameter( order = 1, names = { "--path" },
            descriptionKey = MessageBundle.SERVER_PATH )
    private String              path         = "/xaip-validate";
    
    @Parameter( order = 2, names = { "-h", "--help" }, help = true, descriptionKey = MessageBundle.SERVER_HELP )
    private boolean             help;
}
