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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Optional;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import de.bund.bsi.tresor.aip.validator.api.control.ModuleLogger;

/**
 * Singleton to check data for possible XAdES.
 * 
 * @author wolffs
 */
public enum XAdESChecker
{
    INSTANCE;
    
    private final String signatureNodesXPathExpression = "//*[local-name() = 'Signature']";
    
    /**
     * Checking if the data could be a XAdES by parsing the data and executing an xPathExpression to check if any &lt;Signature&gt; element
     * is present. There is no need to check the namespace since the xmldsig specification states the namespace as optional. Also omitting
     * the magic number check hence an xml is also valid without having the xml preamble.
     * 
     * @param data
     *            the document data
     * @return if the data could contain a XAdES
     */
    public boolean isXAdES( byte[] data )
    {
        try
        {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse( new ByteArrayInputStream( data ) );
            
            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();
            XPathExpression expr = xpath.compile( signatureNodesXPathExpression );
            
            NodeList result = (NodeList) expr.evaluate( doc, XPathConstants.NODESET );
            
            boolean isXAdES = Optional.ofNullable( result )
                    .map( NodeList::getLength )
                    .map( size -> size > 0 )
                    .orElse( false );
            
            ModuleLogger.verbose( isXAdES ? "data is XAdES" : "data is not XAdES" );
            
            return isXAdES;
        }
        catch ( IOException | ParserConfigurationException | SAXException | XPathExpressionException e )
        {
            ModuleLogger.verbose( "data is not XAdES", e );
        }
        
        return false;
    }
}
