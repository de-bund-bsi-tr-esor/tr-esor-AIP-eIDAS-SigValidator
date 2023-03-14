package de.bund.bsi.tresor.aip.validator.api.control;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.util.Arrays;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Resolving xaip uri's which are defined by following format:<br/>
 * 
 * <code>xaip://file.name?q=xPathExpr</code>
 * 
 * @author wolffs
 */
public class AipUriResolver
{
    
    private static final String                 QUERY_IDENTIFIER = "q=";
    private static final String                 QUERY_SEPARATOR  = "&";
    
    private static final XPathFactory           xPathfactory     = XPathFactory.newInstance();
    private static final TransformerFactory     transFactory     = TransformerFactory.newInstance();
    private static final DocumentBuilderFactory docFactory       = DocumentBuilderFactory.newInstance();
    
    /**
     * Resolving the uri and returning the content. This uri can be a relative uri, a file uri or an xaip uri
     * <code>xaip://file.name?q=xPathExpr</code>
     * 
     * @param uri
     *            the uri
     * @param doc
     *            the potential document to load
     * @return the content or null when filename does not match the document
     */
    public ByteArrayOutputStream resolve( URI uri, File doc )
    {
        try
        {
            String scheme = uri.getScheme(); // scheme: //host?query
            String filename = uri.getAuthority(); // file-name
            
            if ( !scheme.equals( "xaip" ) || !filename.equals( doc.getName() ) )
            {
                throw new IllegalArgumentException( "scheme is not xaip" );
            }
            
            String xPaths = uri.getQuery(); // xpath-query
            String expression = extractExpressions( xPaths );
            
            XPath xPath = xPathfactory.newXPath();
            
            try ( InputStream in = new FileInputStream( doc ) )
            {
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                Document document = docFactory.newDocumentBuilder().parse( in );
                
                NodeList list = (NodeList) xPath.compile( expression ).evaluate( document, XPathConstants.NODESET );
                for ( int i = 0; i < list.getLength(); i++ )
                {
                    Node node = list.item( i );
                    os.write( valueOf( node ) );
                }
                
                return os;
            }
        }
        catch ( Exception e )
        {
            throw new IllegalStateException( "could not load xaip", e );
        }
    }
    
    /**
     * Extracting the node value which can be either a node value or a complete node
     * 
     * @param node
     *            the node
     * @return node as text
     * @throws Exception
     */
    byte[] valueOf( Node node ) throws Exception
    {
        switch ( node.getNodeType() )
        {
        case Node.ATTRIBUTE_NODE:
        case Node.TEXT_NODE:
        case Node.ELEMENT_NODE:
            Transformer transformer = transFactory.newTransformer();
            transformer.setOutputProperty( OutputKeys.OMIT_XML_DECLARATION, "yes" );
            
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            transformer.transform( new DOMSource( node ), new StreamResult( os ) );
            
            return os.toByteArray();
        default:
            throw new UnsupportedOperationException( "unsupported node type" + node.getNodeType() );
        }
    }
    
    /**
     * Extracting the xpath expression from the query
     * 
     * @param query
     *            query part of the uri
     * @return the xpath expression
     */
    String extractExpressions( String query )
    {
        return Arrays.stream( query.split( QUERY_SEPARATOR ) )
                .map( q -> {
                    if ( q.startsWith( QUERY_IDENTIFIER ) )
                    {
                        return q.substring( QUERY_IDENTIFIER.length() );
                    }
                    
                    return null;
                } )
                .filter( StringUtils::isNoneBlank )
                .findFirst()
                .orElseThrow( () -> new IllegalArgumentException( "query part of xaip uri is missing" ) );
    }
}
