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
package de.bund.bsi.tresor.xaip.validator.signature.token;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;

import de.bund.bsi.tresor.xaip.validator.signature.DefaultVerifierConfig;

/**
 * Supplier implementation of the optional identityToken which is being used as an additional header for the ECM requests.
 * 
 * @author wolffs
 */
public class TokenSupplier
{
    private static final String HEADER_ACCEPT        = "Accept";
    private static final String HEADER_AUTHORIZATION = "Authorization";
    private static final String HEADER_CONTENT_TYPE  = "Content-Type";
    
    private static final String TYPE_PAOS_XML        = "application/vnd.paos+xml";
    
    /**
     * Supplies an identityToken when the optional configuration for the provider is present in the configuration.
     * 
     * @param config
     *            the configuration
     * @return the identityToken if the requirements are met
     */
    public static Optional<String> supplyToken( DefaultVerifierConfig config )
    {
        if ( config.getUser().isPresent()
                && config.getPassword().isPresent()
                && config.getIdpUrl().isPresent()
                && config.getUmUrl().isPresent() )
        {
            try
            {
                URI umUrl = URI.create( config.getUmUrl().get() );
                URI idpUrl = URI.create( config.getIdpUrl().get() );
                String userPass = config.getUser().get() + ":" + config.getPassword().get();
                String basic = Base64.getEncoder().encodeToString( userPass.getBytes( StandardCharsets.UTF_8 ) );
                
                HttpRequest authnRequest = HttpRequest.newBuilder()
                        .uri( umUrl )
                        .header( HEADER_ACCEPT, TYPE_PAOS_XML )
                        .GET()
                        .build();
                
                HttpClient client = HttpClient.newHttpClient();
                HttpResponse<String> authnXml = client.send( authnRequest, BodyHandlers.ofString() );
                HttpRequest samlResponse = HttpRequest.newBuilder()
                        .uri( idpUrl )
                        .header( HEADER_CONTENT_TYPE, TYPE_PAOS_XML )
                        .header( HEADER_AUTHORIZATION, "Basic " + basic )
                        .POST( BodyPublishers.ofString( authnXml.body() ) )
                        .build();
                
                HttpResponse<String> samlResp = client.send( samlResponse, BodyHandlers.ofString() );
                HttpRequest login = HttpRequest.newBuilder()
                        .uri( umUrl )
                        .header( HEADER_CONTENT_TYPE, TYPE_PAOS_XML )
                        .POST( BodyPublishers.ofString( samlResp.body() ) )
                        .build();
                
                return Optional.ofNullable( client.send( login, BodyHandlers.ofString() ).body() );
            }
            catch ( Exception e )
            {
                throw new RuntimeException( "error on idToken retrieval", e );
            }
        }
        else
        {
            return Optional.empty();
        }
    }
}
