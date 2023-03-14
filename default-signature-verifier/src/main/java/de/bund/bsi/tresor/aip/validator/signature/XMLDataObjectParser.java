package de.bund.bsi.tresor.aip.validator.signature;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import lombok.RequiredArgsConstructor;

/**
 * @author wolffs
 */
@RequiredArgsConstructor
public class XMLDataObjectParser extends DefaultHandler
{
    private static final String    DATA_OBJECT  = "dataObject";
    private static final String    META_OBJECT  = "metaDataObject";
    private static final String    XML_DATA     = "xmlData";
    private static final String    XML_META     = "xmlMetaData";
    
    private static final String    DO_ID        = "dataObjectID";
    private static final String    MO_ID        = "metaDataID";
    private static final String    TRESOR_URI   = "http://www.bsi.bund.de/tr-esor/xaip";
    
    private final String           oid;
    
    private boolean                elementFound = false;
    private boolean                elementValue = false;
    
    private Locator                locator      = null;
    
    private Pair<Integer, Integer> end;
    private Pair<Integer, Integer> start;
    
    @Override
    public void setDocumentLocator( Locator locator )
    {
        this.locator = locator;
    }
    
    /**
     * Returns the position of the xmlData or xmlMetaData startElement after parsing. The position returned is AFTER the tag.
     * 
     * @return lineNumber as key, columnNumber as value
     */
    public Pair<Integer, Integer> getStart()
    {
        return start;
    };
    
    /**
     * Returns the position of the xmlData or xmlMetaData endElement after parsing. The position returned is AFTER the tag.
     * 
     * @return lineNumber as key, columnNumber as value
     */
    public Pair<Integer, Integer> getEnd()
    {
        return end;
    };
    
    @Override
    public void startElement( String uri, String localName, String qName, Attributes attributes ) throws SAXException
    {
        if ( !hasTresorNamespace( uri, localName ) )
        {
            return;
        }
        
        if ( !elementFound &&
                (localName.equals( DATA_OBJECT ) || localName.equals( META_OBJECT ))
                && hasId( attributes ) )
        {
            elementFound = true;
        }
        else if ( !elementValue && elementFound &&
                (localName.equals( XML_DATA ) || localName.equals( XML_META )) )
        {
            elementValue = true;
            
            if ( locator != null )
            {
                printLocation();
                start = Pair.of( locator.getLineNumber(), locator.getColumnNumber() );
            }
        }
    }
    
    void printLocation()
    {
        boolean debug = false;// can be enabled for debugging
        if ( debug )
        {
            String location = "location: ";
            location += " line " + locator.getLineNumber();
            location += ", column " + locator.getColumnNumber();
            location += ": ";
            System.out.println( location );
        }
    }
    
    boolean hasTresorNamespace( String uri, String localName )
    {
        return StringUtils.isNoneBlank( uri ) && StringUtils.isNoneBlank( localName )
                && uri.trim().equals( TRESOR_URI );
    }
    
    boolean hasId( Attributes attributes )
    {
        String doId = attributes.getValue( "", DO_ID );
        String moId = attributes.getValue( "", MO_ID );
        
        return Optional.ofNullable( oid )
                .map( id -> id.equals( doId ) || id.equals( moId ) )
                .orElse( false );
    }
    
    @Override
    public void endElement( String uri, String localName, String qName ) throws SAXException
    {
        if ( !hasTresorNamespace( uri, localName ) )
        {
            return;
        }
        
        if ( elementFound && (localName.equals( XML_DATA ) || localName.equals( XML_META )) )
        {
            elementValue = false;
            if ( locator != null )
            {
                printLocation();
                end = Pair.of( locator.getLineNumber(), locator.getColumnNumber() );
            }
        }
        else if ( elementFound &&
                (localName.equals( DATA_OBJECT ) || localName.equals( META_OBJECT )) )
        {
            elementFound = false;
        }
    }
}
