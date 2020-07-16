package de.bund.bsi.tresor.xaip.validator.dispatcher;

import static java.lang.StackWalker.Option.RETAIN_CLASS_REFERENCE;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import lombok.AllArgsConstructor;

/**
 * @author wolffs
 */
@AllArgsConstructor
// TODO move to api and add functionality for verbose logging over all modules
public class VerboseLogger
{
    private OutputStream output;
    private boolean      isVerbose;
    
    public void log( String message )
    {
        try
        {
            if ( isVerbose )
            {
                StackWalker walker = StackWalker.getInstance( RETAIN_CLASS_REFERENCE );
                String callerName = walker.getCallerClass().getName();
                String logMessage = "[" + callerName + "] " + message + System.lineSeparator();
                
                output.write( logMessage.getBytes( StandardCharsets.UTF_8 ) );
            }
        }
        catch ( InterruptedIOException iioe )
        {
            Thread.currentThread().interrupt();
        }
        catch ( IOException ioe )
        {
            ioe.printStackTrace();
        }
    }
}
