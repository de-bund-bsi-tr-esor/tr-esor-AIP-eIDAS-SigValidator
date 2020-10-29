package de.bund.bsi.tresor.xaip.validator.signature;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * @author bendlera
 *
 */
class DefaultSignatureFinderTest
{
    /**
     * Test method for {@link de.bund.bsi.tresor.xaip.validator.signature.DefaultSignatureFinder#getVendor()}.
     */
    @Test
    void testGetVendor()
    {
        assertEquals( "BSI", new DefaultSignatureFinder().getVendor() );
    }
    
    /**
     * Test method for {@link de.bund.bsi.tresor.xaip.validator.signature.DefaultSignatureFinder#getVersion()}.
     */
    @Test
    void testGetVersion()
    {
        assertEquals( "1.0.0", new DefaultSignatureFinder().getVersion() );
    }
}
