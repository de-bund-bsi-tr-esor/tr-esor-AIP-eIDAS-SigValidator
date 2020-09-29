package de.bund.bsi.tresor.xaip.validator.protocol;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.junit.jupiter.api.Test;

import oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_.IndividualReportType;
import oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_.VerificationReportType;

/**
 * @author bendlera
 *
 */
class DefaultProtocolAssemblerTest
{
    
    /**
     * Test method for {@link de.bund.bsi.tresor.xaip.validator.protocol.DefaultProtocolAssembler#assembleProtocols(java.util.Collection)}.
     */
    @Test
    void testAssembleProtocols()
    {
        XMLGregorianCalendar expected = null;
        try
        {
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.setTime( new Date() );
            expected = DatatypeFactory.newInstance().newXMLGregorianCalendar( gregorianCalendar );
        }
        catch ( DatatypeConfigurationException e )
        {
            fail( "test data could not created", e );
        }
        
        IndividualReportType individualReport1 = new IndividualReportType();
        List<IndividualReportType> individualReports = new LinkedList<>();
        individualReports.add( individualReport1 );
        
        DefaultProtocolAssembler defaultProtocolAssembler = new DefaultProtocolAssembler();
        VerificationReportType actual = defaultProtocolAssembler.assembleProtocols( individualReports );
        
        assertEquals( individualReport1, actual.getIndividualReport().get( 0 ) );
        
        assertTrue( actual.getVerificationTimeInfo().getVerificationTime().compare( expected ) == DatatypeConstants.GREATER
                || actual.getVerificationTimeInfo().getVerificationTime().compare( expected ) == DatatypeConstants.EQUAL );
    }
    
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
