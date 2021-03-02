/*-
 * Copyright (c) 2020
 * Federal Office for Information Security (BSI),
 * Godesberger Allee 185-189,
 * 53175 Bonn, Germany,
 * phone: +49 228 99 9582-0,
 * fax: +49 228 99 9582-5400,
 * e-mail: bsi@bsi.bund.de
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.bund.bsi.tresor.aip.validator.api.control;

import static java.lang.StackWalker.Option.RETAIN_CLASS_REFERENCE;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.StackWalker.StackFrame;
import java.util.Optional;

import de.bund.bsi.tresor.aip.validator.api.entity.LoggerConfig;
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
    private static Optional<LoggerConfig> conf    = Optional.empty();
    private static final LoggerConfig     DEFAULT_CONF = new LoggerConfig( System.err, true );
    
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
        if ( conf.isEmpty() )
        {
            conf = Optional.of( new LoggerConfig( out, verbose ) );
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
        if ( conf.orElse( DEFAULT_CONF ).isVerbose() )
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
        OutputStream out = conf.orElse( DEFAULT_CONF ).getOutput();
        
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
