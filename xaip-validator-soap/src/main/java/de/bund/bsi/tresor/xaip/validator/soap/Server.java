package de.bund.bsi.tresor.xaip.validator.soap;

import java.net.URI;
import java.net.URISyntaxException;

import javax.xml.ws.Endpoint;

import com.beust.jcommander.JCommander;

import de.bund.bsi.tresor.xaip.validator.api.control.ModuleLogger;
import de.bund.bsi.tresor.xaip.validator.soap.config.MessageBundle;
import de.bund.bsi.tresor.xaip.validator.soap.config.ServerConfig;

/**
 * @author wolffs
 */
public class Server
{
    /**
     * Main method for starting the local soap server.
     * 
     * @param args
     *            the server arguments
     * @throws Exception
     *             when anything happens
     */
    public static void main( String[] args ) throws Exception
    {
        ServerConfig config = new ServerConfig();
        JCommander jCommander = JCommander.newBuilder()
                .addObject( config )
                .resourceBundle( MessageBundle.RESOURCE )
                .programName( MessageBundle.RESOURCE.getString( MessageBundle.SERVER_NAME ) )
                .build();
        
        try
        {
            jCommander.parse( args );
            
            ModuleLogger.initConfig( config.isVerbose(), config.getLog() );
            if ( config.isHelp() )
            {
                jCommander.usage();
            }
            else
            {
                startServer( config );
            }
        }
        catch ( Exception e )
        {
            ModuleLogger.initConfig( config.isVerbose(), config.getLog() );
            ModuleLogger.log( "could not start server", e );
        }
    }
    
    /**
     * Starting the server by using the provided configuration
     * 
     * @param config
     *            the configuration
     * @throws URISyntaxException
     *             if any part of the config does not provide a valid URI part
     */
    public static void startServer( ServerConfig config ) throws URISyntaxException
    {
        URI uri = new URI( config.getProtocol(), null, config.getHost(), config.getPort(), config.getPath(), null, null );
        
        String address = uri.toString();
        Endpoint.publish( address, new XAIPValidator( config ) );
        
        ModuleLogger.log( "published server on address: " + address );
    }
}
