package de.bund.bsi.tresor.xaip.validator.api.entity.xaip;

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
