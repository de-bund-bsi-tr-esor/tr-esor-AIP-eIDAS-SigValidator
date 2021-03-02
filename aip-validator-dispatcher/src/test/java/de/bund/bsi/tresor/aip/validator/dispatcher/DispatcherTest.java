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
package de.bund.bsi.tresor.aip.validator.dispatcher;

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

import de.bund.bsi.tresor.aip.validator.dispatcher.Dispatcher;
import oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_.VerificationReportType;

/**
 * @author bendlera
 *
 */
class DispatcherTest
{
    
    /**
     * Test method for {@link de.bund.bsi.tresor.aip.validator.dispatcher.Dispatcher#writeReport(VerificationReportType, OutputStream)}.
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
