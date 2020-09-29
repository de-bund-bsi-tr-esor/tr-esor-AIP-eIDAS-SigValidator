package de.bund.bsi.tresor.xaip.validator.signature.lxaip;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

/**
 * @author bendlera
 *
 */
class DigestAlgorithmTest
{
    
    /**
     * Test method for {@link de.bund.bsi.tresor.xaip.validator.signature.lxaip.DigestAlgorithm#fromXmlSyntax(java.lang.String)}.
     */
    @Test
    void testFromXmlSyntax()
    {
        Optional<DigestAlgorithm> digestAlgorithm = DigestAlgorithm.fromXmlSyntax( "http://www.w3.org/2001/04/xmldsig-more#sha224" );
        assertTrue( digestAlgorithm.isPresent() );
        assertEquals( "SHA-224", digestAlgorithm.get().getJavaName() );
        
        digestAlgorithm = DigestAlgorithm.fromXmlSyntax( "http://www.w3.org/2001/04/xmlenc#sha256" );
        assertTrue( digestAlgorithm.isPresent() );
        assertEquals( "SHA-256", digestAlgorithm.get().getJavaName() );
        
        digestAlgorithm = DigestAlgorithm.fromXmlSyntax( "http://www.w3.org/2001/04/xmldsig-more#sha384" );
        assertTrue( digestAlgorithm.isPresent() );
        assertEquals( "SHA-384", digestAlgorithm.get().getJavaName() );
        
        digestAlgorithm = DigestAlgorithm.fromXmlSyntax( "http://www.w3.org/2001/04/xmlenc#sha512" );
        assertTrue( digestAlgorithm.isPresent() );
        assertEquals( "SHA-512", digestAlgorithm.get().getJavaName() );
        
        digestAlgorithm = DigestAlgorithm.fromXmlSyntax( "http://www.w3.org/2007/05/xmldsig-more#sha3-256" );
        assertTrue( digestAlgorithm.isPresent() );
        assertEquals( "SHA3-256", digestAlgorithm.get().getJavaName() );
        
        digestAlgorithm = DigestAlgorithm.fromXmlSyntax( "http://www.w3.org/2007/05/xmldsig-more#sha3-384" );
        assertTrue( digestAlgorithm.isPresent() );
        assertEquals( "SHA3-384", digestAlgorithm.get().getJavaName() );
        
        digestAlgorithm = DigestAlgorithm.fromXmlSyntax( "http://www.w3.org/2007/05/xmldsig-more#sha3-512" );
        assertTrue( digestAlgorithm.isPresent() );
        assertEquals( "SHA3-512", digestAlgorithm.get().getJavaName() );
    }
    
}
