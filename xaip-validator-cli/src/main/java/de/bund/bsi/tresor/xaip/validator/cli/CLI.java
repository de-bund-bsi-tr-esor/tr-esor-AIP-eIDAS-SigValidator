package de.bund.bsi.tresor.xaip.validator.cli;

import java.util.Optional;

import com.beust.jcommander.JCommander;

import de.bund.bsi.tresor.xaip.validator.cli.arguments.Arguments;
import de.bund.bsi.tresor.xaip.validator.cli.arguments.VerifyCommand;
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
                .addCommand( VerifyCommand.NAME, args.getVerify(), VerifyCommand.ALIAS )
                .resourceBundle( MessageBundle.RESOURCE )
                .programName( MessageBundle.RESOURCE.getString( MessageBundle.CLI_NAME ) )
                .build();
        
        try
        {
            jCommander.parse( argv );
            Dispatcher.INSTANCE.dispatch( Optional.ofNullable( jCommander.getParsedCommand() ), args );
        }
        catch ( Exception e )
        {
            e.printStackTrace();
            jCommander.usage();
        }
    }
}