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
package de.bund.bsi.tresor.xaip.validator.signature;

import java.util.Map;
import java.util.Optional;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Configuration class of the {@link DefaultSignatureVerifier}
 * 
 * @author wolffs
 */
@Getter
@Setter( value = AccessLevel.PRIVATE )
@NoArgsConstructor( access = AccessLevel.PRIVATE )
public class DefaultVerifierConfig
{
    public Optional<String> wsdlUrl        = Optional.empty();
    
    public int              requestTimeout = 3000;
    public int              connectTimeout = 3000;
    
    // support for ecm identityToken
    public Optional<String> umUrl          = Optional.empty();
    public Optional<String> idpUrl         = Optional.empty();
    public Optional<String> user           = Optional.empty();
    public Optional<String> password       = Optional.empty();
    
    /**
     * Creating a {@link DefaultVerifierConfig} by parsing a property map
     * 
     * @param arguments
     *            the arguments for this module
     * @return the parsed configuration
     */
    public static DefaultVerifierConfig fromArguments( Map<String, String> arguments )
    {
        var config = new DefaultVerifierConfig();
        config.setWsdlUrl( Optional.ofNullable( arguments.get( "verifier.wsdlUrl" ) ) );
        Optional.ofNullable( arguments.get( "verifier.requestTimeout" ) )
                .map( Integer::parseInt )
                .ifPresent( config::setRequestTimeout );
        
        Optional.ofNullable( arguments.get( "verifier.connectTimeout" ) )
                .map( Integer::parseInt )
                .ifPresent( config::setConnectTimeout );
        
        config.setUser( Optional.ofNullable( arguments.get( "verifier.user" ) ) );
        config.setPassword( Optional.ofNullable( arguments.get( "verifier.pass" ) ) );
        config.setUmUrl( Optional.ofNullable( arguments.get( "verifier.umUrl" ) ) );
        config.setIdpUrl( Optional.ofNullable( arguments.get( "verifier.idpUrl" ) ) );
        
        return config;
    }
}
