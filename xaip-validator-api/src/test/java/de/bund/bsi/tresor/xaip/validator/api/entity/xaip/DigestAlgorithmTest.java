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
package de.bund.bsi.tresor.xaip.validator.api.entity.xaip;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * @author bendlera
 */
class DigestAlgorithmTest
{
    
    /**
     * Test method for {@link de.bund.bsi.tresor.xaip.validator.api.entity.xaip.DigestAlgorithm#fromXmlSyntax(java.lang.String)}.
     */
    @ParameterizedTest
    @MethodSource( "algorithmMap" )
    void testFromXmlSyntax( String javaName, String xmlUri )
    {
        Optional<DigestAlgorithm> digestAlgorithm = DigestAlgorithm.fromXmlSyntax( xmlUri );
        assertTrue( digestAlgorithm.isPresent() );
        assertEquals( javaName, digestAlgorithm.get().getJavaName() );
    }
    
    static Stream<Arguments> algorithmMap()
    {
        return Stream.of( Arguments.of( "SHA-224", "http://www.w3.org/2001/04/xmldsig-more#sha224" ),
                Arguments.of( "SHA-256", "http://www.w3.org/2001/04/xmlenc#sha256" ),
                Arguments.of( "SHA-384", "http://www.w3.org/2001/04/xmldsig-more#sha384" ),
                Arguments.of( "SHA-512", "http://www.w3.org/2001/04/xmlenc#sha512" ),
                Arguments.of( "SHA3-256", "http://www.w3.org/2007/05/xmldsig-more#sha3-256" ),
                Arguments.of( "SHA3-384", "http://www.w3.org/2007/05/xmldsig-more#sha3-384" ),
                Arguments.of( "SHA3-512", "http://www.w3.org/2007/05/xmldsig-more#sha3-512" ) );
    }
}
