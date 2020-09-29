package de.bund.bsi.tresor.xaip.validator.dispatcher;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_.VerificationReportType;

/**
 * @author bendlera
 *
 */
class DispatcherTest
{
    
    /**
     * Test method for {@link de.bund.bsi.tresor.xaip.validator.dispatcher.Dispatcher#writeReport(VerificationReportType, OutputStream)}.
     */
    @Test
    void testWriteReport()
    {
        ByteArrayOutputStream xml = new ByteArrayOutputStream();
        Dispatcher.INSTANCE.writeReport( new VerificationReportType(), xml );
        
        try
        {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilderFactory.setNamespaceAware( true );
            Document parsedXml = documentBuilderFactory.newDocumentBuilder().parse( new ByteArrayInputStream( xml.toByteArray() ) );
            assertEquals( "VerificationReport", parsedXml.getDocumentElement().getLocalName() );
        }
        catch ( SAXException | IOException | ParserConfigurationException e )
        {
            fail( "test fails", e );
        }
    }
    
}
