package de.bund.bsi.tresor.xaip.validator.cli;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Map;
import java.util.Properties;

import com.beust.jcommander.JCommander;

import de.bund.bsi.tresor.xaip.validator.api.control.ModuleLogger;
import de.bund.bsi.tresor.xaip.validator.cli.arguments.Arguments;
import de.bund.bsi.tresor.xaip.validator.dispatcher.Dispatcher;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Entrypoint for the CommandLineInterface of the XAIPValidator.
 * 
 * @author wolffs
 */
@NoArgsConstructor( access = AccessLevel.PRIVATE )
public final class CLI
{
    /**
     * Starting the application and using the arguments to configure the validator which is being done by passing them to the dispatcher.
     * The command with the least amount of informations provided is the schema validation of an XAIP, which is being retrieved from an
     * inputstream.
     * 
     * @param argv
     *            the arguments and values
     */
    public static void main( String[] argv )
    {
        Arguments args = new Arguments();
        JCommander jCommander = JCommander.newBuilder()
                .addObject( args )
                .resourceBundle( MessageBundle.RESOURCE )
                .programName( MessageBundle.RESOURCE.getString( MessageBundle.CLI_NAME ) )
                .build();
        
        try
        {
            jCommander.parse( argv );
            mergeConfig( args );
            
            if ( args.isHelp() )
            {
                jCommander.usage();
            }
            else
            {
                Dispatcher.INSTANCE.dispatch( args );
                ModuleLogger.log( "finished validation without errors" );
            }
        }
        catch ( Exception e )
        {
            ModuleLogger.log( "finished validation with errors", e );
        }
    }
    
    @SuppressWarnings( { "rawtypes", "unchecked" } )
    private static void mergeConfig( Arguments args ) throws IOException
    {
        if ( args.getConfig() != null && Files.isReadable( args.getConfig() ) )
        {
            Properties config = new Properties();
            try ( InputStream configData = Files.newInputStream( args.getConfig() ) )
            {
                config.load( configData );
                config.putAll( args.getModuleConfig() );
                args.setModuleConfig( (Map) config );
            }
        }
    }
}