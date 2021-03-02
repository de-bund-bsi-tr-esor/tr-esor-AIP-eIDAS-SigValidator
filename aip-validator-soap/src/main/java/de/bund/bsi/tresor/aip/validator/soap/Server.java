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
package de.bund.bsi.tresor.aip.validator.soap;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.Map;
import java.util.Properties;

import javax.xml.ws.Endpoint;

import com.beust.jcommander.JCommander;

import de.bund.bsi.tresor.aip.validator.api.control.ModuleLogger;
import de.bund.bsi.tresor.aip.validator.soap.config.MessageBundle;
import de.bund.bsi.tresor.aip.validator.soap.config.ServerConfig;

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
            mergeConfig( config );
            
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
        Endpoint.publish( address, new AIPValidator( config ) );
        
        ModuleLogger.log( "published server on address: " + address );
    }
    
    @SuppressWarnings( { "rawtypes", "unchecked" } )
    private static void mergeConfig( ServerConfig args ) throws IOException
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
