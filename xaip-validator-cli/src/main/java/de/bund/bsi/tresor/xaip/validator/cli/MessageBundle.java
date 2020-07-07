package de.bund.bsi.tresor.xaip.validator.cli;

import java.util.ResourceBundle;

import lombok.Getter;

/**
 * @author wolffs
 */
@Getter
public abstract class MessageBundle
{
    public static final ResourceBundle RESOURCE                = ResourceBundle.getBundle( "MessageBundle" );
    
    public static final String         CLI_INFO                = "cli.info";
    
    public static final String         CLI_NAME                = "cli.name";
    
    public static final String         CLI_USAGE_INPUT         = "cli.usage.input";
    
    public static final String         CLI_USAGE_OUTPUT        = "cli.usage.output";
    
    public static final String         CLI_USAGE_ECARD_URL     = "cli.usage.ecard.url";
    
    public static final String         CLI_USAGE_FIND          = "cli.usage.find";
    
    public static final String         CLI_USAGE_VERIFY        = "cli.usage.verify";
    
    public static final String         CLI_USAGE_TARGET = "cli.usage.target";
    
    public static final String         CLI_USAGE_HELP          = "cli.usage.help";
}
