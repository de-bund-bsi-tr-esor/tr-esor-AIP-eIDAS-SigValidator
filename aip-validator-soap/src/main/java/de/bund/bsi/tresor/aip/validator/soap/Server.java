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

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import javax.xml.ws.Endpoint;

import org.apache.commons.lang3.StringUtils;

import com.beust.jcommander.JCommander;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsServer;

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
                String address = Objects.isNull( config.getKeyStore() ) ? startServer( config ) : startServerTLS( config );
                ModuleLogger.log( "published server on address: " + address );
            }
        }
        catch ( Exception e )
        {
            ModuleLogger.initConfig( config.isVerbose(), config.getLog() );
            ModuleLogger.log( "could not start server", e );
        }
    }
    
    /**
     * Starting the http server by using the provided configuration
     * 
     * @param config
     *            the configuration
     * @return the published server address
     * @throws URISyntaxException
     *             if any part of the config does not provide a valid URI part
     */
    public static String startServer( ServerConfig config ) throws URISyntaxException
    {
        URI uri = new URI( "http", null, config.getHost(), config.getPort(), config.getPath(), null, null );
        String address = uri.toString();
        
        Endpoint.publish( address, new AIPValidator( config ) );
        
        return address;
    }
    
    /**
     * Starting the https server by using the provided configuration
     * 
     * @param config
     *            the configuration
     * @return the published server address
     * @throws Exception
     *             when an error occurs
     */
    public static String startServerTLS( ServerConfig config ) throws Exception
    {
        Path keyStore = config.getKeyStore();
        String keyPass = config.getKeyPass();
        if ( StringUtils.isAllBlank( keyPass ) )
        {
            ModuleLogger.log( "missing keypass for keystore" );
            System.exit( -1 );
        }
        
        try ( InputStream in = new FileInputStream( keyStore.toFile() ) )
        {
            KeyStore store = KeyStore.getInstance( "JKS" );
            store.load( in, keyPass.toCharArray() );
            
            KeyManagerFactory keyFactory = KeyManagerFactory.getInstance( KeyManagerFactory.getDefaultAlgorithm() );
            keyFactory.init( store, keyPass.toCharArray() );
            
            TrustManagerFactory trustFactory = TrustManagerFactory.getInstance( TrustManagerFactory.getDefaultAlgorithm() );
            trustFactory.init( store );
            
            SSLContext ssl = SSLContext.getInstance( "TLS" );
            ssl.init( keyFactory.getKeyManagers(), trustFactory.getTrustManagers(), new SecureRandom() );
            
            HttpsConfigurator configurator = new HttpsConfigurator( ssl );
            
            HttpsServer httpsServer = HttpsServer.create(
                    new InetSocketAddress( config.getHost(), config.getPort() ), config.getPort() );
            HttpContext httpContext = httpsServer.createContext( config.getPath() );
            
            httpsServer.setHttpsConfigurator( configurator );
            httpsServer.start();
            
            Endpoint endpoint = Endpoint.create( new AIPValidator( config ) );
            endpoint.publish( httpContext );
            
            URI uri = new URI( "https", null, config.getHost(), config.getPort(), config.getPath(), null, null );
            
            return uri.toString();
        }
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
