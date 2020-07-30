package de.bund.bsi.tresor.xaip.validator.cli;

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
        
        // TODO remove this test
        argv = new String[] { "-d", "-v", "-i", "/home/wolffs/Dokumente/XAIP-Validator/validator/PAdES.xaip" };
        
        try
        {
            jCommander.parse( argv );
            
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
}