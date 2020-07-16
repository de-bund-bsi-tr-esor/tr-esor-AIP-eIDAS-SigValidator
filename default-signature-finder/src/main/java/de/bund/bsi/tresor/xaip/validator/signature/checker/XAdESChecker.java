package de.bund.bsi.tresor.xaip.validator.signature.checker;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
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

/**
 * @author wolffs
 */
public enum XAdESChecker
{
    INSTANCE;
    
    private final byte[] xmlMagicNumber                = "<?xml".getBytes( StandardCharsets.US_ASCII );
    private final String signatureNodesXPathExpression = "//Signature";
    
    public boolean isXAdES( byte[] data )
    {
        return false;
    }
    
    boolean hasXmlMagicNumber( byte[] data )
    {
        byte[] slice = Arrays.copyOfRange( data, 0, xmlMagicNumber.length );
        
        return Arrays.equals( slice, xmlMagicNumber );
    }
    
    // the specification states that the xmldsig namespace is optional
    boolean hasXAdESRequirements( byte[] data )
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
            
            return Optional.ofNullable( result )
                    .map( NodeList::getLength )
                    .map( size -> size > 0 )
                    .orElse( false );
        }
        catch ( IOException | ParserConfigurationException | SAXException | XPathExpressionException e )
        {
            // TODO verbose logging
        }
        
        return false;
    }
}
