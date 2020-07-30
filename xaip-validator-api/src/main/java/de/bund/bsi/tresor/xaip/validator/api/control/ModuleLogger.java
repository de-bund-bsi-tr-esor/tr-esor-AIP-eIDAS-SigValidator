package de.bund.bsi.tresor.xaip.validator.api.control;

import static java.lang.StackWalker.Option.RETAIN_CLASS_REFERENCE;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.StackWalker.StackFrame;
import java.util.Optional;

import de.bund.bsi.tresor.xaip.validator.api.entity.LoggerConfig;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

/**
 * Global logger which can be used by all modules of the XAIPValidator.
 * 
 * @author wolffs
 */
@AllArgsConstructor( access = AccessLevel.PRIVATE )
public class ModuleLogger
{
    private static LoggerConfig conf;
    
    /**
     * Initialising the global logging configuraton for this logger. This can and has to be done only once. Any further calls will do
     * nothing.
     * 
     * @param verbose
     *            when the logger should log on verbose level
     * @param out
     *            the outputStream for the log entries
     */
    public static void initConfig( boolean verbose, OutputStream out )
    {
        if ( conf == null )
        {
            conf = new LoggerConfig( out, verbose );
        }
    }
    
    /**
     * Logging a message on verbose level.
     * 
     * @param message
     *            the message
     */
    public static void verbose( String message )
    {
        verbose( message, null );
    }
    
    /**
     * Logging an error on verbose level.
     * 
     * @param message
     *            the error message
     * @param e
     *            the cause
     */
    public static void verbose( String message, Throwable e )
    {
        if ( conf.isVerbose() )
        {
            log( message, e );
        }
    }
    
    /**
     * Logging a message.
     * 
     * @param message
     *            the message
     */
    public static void log( String message )
    {
        log( message, null );
    }
    
    /**
     * Logging an error.
     * 
     * @param message
     *            the error message
     * @param e
     *            the cause
     */
    public static void log( String message, Throwable e )
    {
        Optional<OutputStream> optOut = Optional.ofNullable( conf ).map( LoggerConfig::getOutput );
        
        if ( optOut.isPresent() )
        {
            String loggername = ModuleLogger.class.getName();
            StackWalker walker = StackWalker.getInstance( RETAIN_CLASS_REFERENCE );
            String callerName = walker.walk( stream -> stream.filter( frame -> !frame.getClassName().equals( loggername ) )
                    .findFirst()
                    .map( StackFrame::getClassName )
                    .orElse( loggername ) );
            
            PrintWriter writer = new PrintWriter( optOut.get() );
            writer.format( "[%s] %s", callerName, message );
            writer.append( System.lineSeparator() );
            if ( e != null )
            {
                e.printStackTrace( writer );
            }
            
            writer.flush();
        }
    }
}
