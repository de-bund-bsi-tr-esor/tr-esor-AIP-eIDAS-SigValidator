package de.bund.bsi.tresor.xaip.validator.cli;

import java.util.ResourceBundle;

import lombok.Getter;

/**
 * Abstract class for managing the messagebundle. The messagebundle is being used to get informations over the usage of the CLI.
 * 
 * @author wolffs
 */
@Getter
public abstract class MessageBundle
{
    public static final ResourceBundle RESOURCE            = ResourceBundle.getBundle( "MessageBundle" );
    
    public static final String         CLI_INFO            = "cli.info";
    
    public static final String         CLI_NAME            = "cli.name";
    
    public static final String         CLI_USAGE_CONFIG    = "cli.usage.config";
    
    public static final String         CLI_USAGE_INPUT     = "cli.usage.input";
    
    public static final String         CLI_USAGE_OUTPUT    = "cli.usage.output";
    
    public static final String         CLI_USAGE_ECARD_URL = "cli.usage.ecard.url";
    
    public static final String         CLI_USAGE_VERIFY    = "cli.usage.verify";
    
    public static final String         CLI_USAGE_VERBOSE   = "cli.usage.verbose";
    
    public static final String         CLI_USAGE_LOG       = "cli.usage.log";
    
    public static final String         CLI_USAGE_HELP      = "cli.usage.help";
    
    public static final String         ARG_DEFAULT_LOG     = "arguments.default.log";
    
    public static final String         ARG_DEFAULT_OUTPUT  = "arguments.default.output";
    
}
