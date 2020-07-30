package de.bund.bsi.tresor.xaip.validator.api.control;

import static java.lang.StackWalker.Option.RETAIN_CLASS_REFERENCE;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.StackWalker.StackFrame;

import de.bund.bsi.tresor.xaip.validator.api.entity.LoggerConfig;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

/**
 * @author wolffs
 */
@AllArgsConstructor( access = AccessLevel.PRIVATE )
public class ModuleLogger
{
    private static LoggerConfig conf;
    
    public static void initConfig( boolean verbose, OutputStream out )
    {
        if ( conf == null )
        {
            conf = new LoggerConfig( out, verbose );
        }
    }
    
    public static void verbose( String message )
    {
        verbose( message, null );
    }
    
    public static void verbose( String message, Throwable e )
    {
        if ( conf.isVerbose() )
        {
            log( message, e );
        }
    }
    
    public static void log( String message )
    {
        log( message, null );
    }
    
    public static void log( String message, Throwable e )
    {
        OutputStream out = conf.getOutput();
        if ( out != null )
        {
            String loggername = ModuleLogger.class.getName();
            StackWalker walker = StackWalker.getInstance( RETAIN_CLASS_REFERENCE );
            String callerName = walker.walk( stream -> stream.filter( frame -> !frame.getClassName().equals( loggername ) )
                    .findFirst()
                    .map( StackFrame::getClassName )
                    .orElse( loggername ) );
            
            PrintWriter writer = new PrintWriter( out );
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
