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
package de.bund.bsi.tresor.aip.validator.cli;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.LogManager;

import com.beust.jcommander.JCommander;

import de.bund.bsi.tresor.aip.validator.api.control.ModuleLogger;
import de.bund.bsi.tresor.aip.validator.cli.arguments.Arguments;
import de.bund.bsi.tresor.aip.validator.dispatcher.Dispatcher;
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
        // disable no optimization to prevent warning output
        System.setProperty( "com.sun.xml.bind.v2.bytecode.ClassTailor.noOptimize", "true" );
        Optional.ofNullable( LogManager.getLogManager().getLogger( "" ) ).ifPresent( logger -> logger.setLevel( Level.WARNING ) );
        Optional.ofNullable( LogManager.getLogManager().getLogger( "com.sun.xml.bind.Utils" ) )
                .ifPresent( logger -> logger.setLevel( Level.WARNING ) );
        
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
                if ( args.getInput().equals( System.in ) )
                {
                    ModuleLogger.log( "no input source provided using stdIn" );
                    ModuleLogger.log( "use ctrl+D to close or ctrl+C to interrupt, reading stdIn..." );
                }
                
                Dispatcher.INSTANCE.dispatch( args );
                ModuleLogger.log( "finished validation" );
            }
        }
        catch ( Exception e )
        {
            ModuleLogger.log( "\nfinished validation with errors", e );
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