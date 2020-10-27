package de.bund.bsi.tresor.xaip.validator.protocol;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * @author bendlera
 *
 */
class DefaultProtocolAssemblerTest
{
    
    /**
     * Test method for {@link de.bund.bsi.tresor.xaip.validator.protocol.DefaultProtocolAssembler#getVendor()}.
     */
    @Test
    void testGetVendor()
    {
        assertEquals( "BSI", new DefaultProtocolAssembler().getVendor() );
    }
    
    /**
     * Test method for {@link de.bund.bsi.tresor.xaip.validator.protocol.DefaultProtocolAssembler#getVersion()}.
     */
    @Test
    void testGetVersion()
    {
        assertEquals( "1.0.0", new DefaultProtocolAssembler().getVersion() );
    }
    
}
