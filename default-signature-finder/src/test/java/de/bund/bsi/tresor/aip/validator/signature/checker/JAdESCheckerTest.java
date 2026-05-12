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
package de.bund.bsi.tresor.aip.validator.signature.checker;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JAdESCheckerTest
{
    
    private JAdESChecker jAdESChecker = JAdESChecker.INSTANCE;
    
    // JWS Compact Serialization -> <base64url-header>.<base64url-payload>.<base64url-signature>
    // JWS JSON Serialization -> JSON
    @ParameterizedTest( name = "test file: {0}" )
    @ValueSource( strings = {
            "jades-detached-by-uri-encoded-pars.json",
            "jades-level-b-full-type.json",
            "simple-detached.json",
            "simple-detached-wrong-algo.json",
            "jades-detached-by-uri-hash-encoded-pars.json"
    } )
    void shouldDetectJadesCompactSerialization( String testFile ) throws IOException
    {
        byte[] jades = getClass()
                .getResourceAsStream( "/jades/" + testFile ).readAllBytes();
        
        boolean result = jAdESChecker.isJAdES( jades );
        
        assertTrue( result, "Expected JAdES for " + testFile );
    }
    
    @ParameterizedTest( name = "test file: {0}" )
    @ValueSource( strings = {
            "jades-b-level-with-etsiu-in-crit.json",
            "altered-jws.json",
            "jades-t-level-with-etsiu-in-crit.json",
            "jades-flattened-BpB-detached-objectByURIHash.json",
            "jades-t-clear-etsiu.json",
            "jades-with-counter-signature.json"
    } )
    void shouldDetectJadesJsonSerialization( String testFile ) throws IOException
    {
        byte[] jades = getClass()
                .getResourceAsStream( "/jades/" + testFile ).readAllBytes();
        
        boolean result = jAdESChecker.isJAdES( jades );
        
        assertTrue( result, "JAdES should be detected." );
    }
    
    @ParameterizedTest( name = "test file: {0}" )
    @ValueSource( strings = {
            "jades-wrong-etsiu-type.json",
            "jades-wrong-x5c-header.json",
            "jws-serialization-no-signatures.json",
            "jades-with-crit-with-wrong-entry-type.json",
            "malformed-jades-serialization.json",
            "serialization-extra-element.json",
            "jades-with-EtsiHeader-but-NoCertificate.json",
            "JadesWithCertificateReferenceButNoEtsiHeader.json",
            "JadesWithEmptyEtsiUIs.json",
            "JadesWithEmptyProtected.json"
    } )
    void shouldNotDetectJades( String testFile ) throws IOException
    {
        byte[] jades = getClass()
                .getResourceAsStream( "/jades/" + testFile ).readAllBytes();
        
        boolean result = jAdESChecker.isJAdES( jades );
        
        assertFalse( result, "Expected NOT JAdES for " + testFile );
    }
    
    @Test
    void shouldNotDetectJsonAsJades()
    {
        byte[] json = "{ \"title\": \"Hello World!\" }".getBytes( StandardCharsets.UTF_8 );
        
        boolean result = jAdESChecker.isJAdES( json );
        
        assertFalse( result, "Plain JSON should not be detected as JAdES." );
    }

}
