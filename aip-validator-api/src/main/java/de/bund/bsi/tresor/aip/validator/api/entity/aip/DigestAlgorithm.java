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
package de.bund.bsi.tresor.aip.validator.api.entity.aip;

import java.util.Arrays;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Digest algorithm mapping for xml uri to Java Security Standard Algorithm Name.
 * 
 * @author bendlera
 */
@Getter
@AllArgsConstructor
public enum DigestAlgorithm
{
    SHA224( "SHA-224", "http://www.w3.org/2001/04/xmldsig-more#sha224" ),
    
    SHA256( "SHA-256", "http://www.w3.org/2001/04/xmlenc#sha256" ),
    
    SHA384( "SHA-384", "http://www.w3.org/2001/04/xmldsig-more#sha384" ),
    
    SHA512( "SHA-512", "http://www.w3.org/2001/04/xmlenc#sha512" ),
    
    SHA3_256( "SHA3-256", "http://www.w3.org/2007/05/xmldsig-more#sha3-256" ),
    
    SHA3_384( "SHA3-384", "http://www.w3.org/2007/05/xmldsig-more#sha3-384" ),
    
    SHA3_512( "SHA3-512", "http://www.w3.org/2007/05/xmldsig-more#sha3-512" );
    
    private final String javaName;
    private final String xmlUri;
    
    /**
     * Finding the {@link DigestAlgorithm} of the xml uri.
     * 
     * @param xmlUri
     *            the xml uri
     * @return the algorithm
     */
    public static Optional<DigestAlgorithm> fromXmlSyntax( String xmlUri )
    {
        return Arrays.stream( values() )
                .filter( alg -> alg.getXmlUri().equals( xmlUri ) )
                .findAny();
    }
}
