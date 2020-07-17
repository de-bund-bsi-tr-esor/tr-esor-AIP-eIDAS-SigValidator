package de.bund.bsi.tresor.xaip.validator.cli;

import com.beust.jcommander.JCommander;

import de.bund.bsi.tresor.xaip.validator.cli.arguments.Arguments;
import de.bund.bsi.tresor.xaip.validator.dispatcher.Dispatcher;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author wolffs
 */
@NoArgsConstructor( access = AccessLevel.PRIVATE )
public final class CLI
{
    public static void main( String[] argv )
    {
        Arguments args = new Arguments();
        JCommander jCommander = JCommander.newBuilder()
                .addObject( args )
                .resourceBundle( MessageBundle.RESOURCE )
                .programName( MessageBundle.RESOURCE.getString( MessageBundle.CLI_NAME ) )
                .build();
        
        // TODO remove this test
        argv = new String[] { "-d", "-v", "-i", "/home/wolffs/Dokumente/XAIP-Validator/validator/CAdES.xaip" };
        
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
            }
        }
        catch ( Exception e )
        {
            e.printStackTrace();
            jCommander.usage();
        }
    }
}