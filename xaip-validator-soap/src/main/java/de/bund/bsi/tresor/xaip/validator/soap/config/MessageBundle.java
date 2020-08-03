package de.bund.bsi.tresor.xaip.validator.soap.config;

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
    public static final ResourceBundle RESOURCE        = ResourceBundle.getBundle( "MessageBundle" );
    
    public static final String         SERVER_INFO     = "server.info";
    
    public static final String         SERVER_NAME     = "server.name";
    
    public static final String         SERVER_HELP     = "server.help";
    
    public static final String         SERVER_HOST     = "server.host";
    
    public static final String         SERVER_PORT     = "server.port";
    
    public static final String         SERVER_PATH     = "server.path";
    
    public static final String         SERVER_PROTOCOL = "server.protocol";
    
}
