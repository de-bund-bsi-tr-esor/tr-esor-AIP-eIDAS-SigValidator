package de.bund.bsi.tresor.xaip.validator.signature.checker;

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

import de.bund.bsi.tresor.xaip.validator.api.control.ModuleLogger;

/**
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
            ModuleLogger.log( "data is not XAdES", e );
        }
        
        return false;
    }
}
