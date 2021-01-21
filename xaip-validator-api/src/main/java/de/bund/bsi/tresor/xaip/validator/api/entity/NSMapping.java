package de.bund.bsi.tresor.xaip.validator.api.entity;

import java.util.HashMap;
import java.util.Iterator;

import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;

/**
 * @author wolffs
 */
public class NSMapping implements NamespaceContext
{
    
    private final HashMap<String, String> nsContext = new HashMap<>();
    
    public void putNamespace( String prefix, String uri )
    {
        nsContext.put( prefix, uri );
    }
    
    @Override
    public String getNamespaceURI( String prefix )
    {
        if ( prefix == null )
        {
            return XMLConstants.NULL_NS_URI;
        }
        if ( nsContext.containsKey( prefix ) )
        {
            return nsContext.get( prefix );
        }
        else
        {
            throw new IllegalArgumentException( "Unknown prefix: " + prefix );
        }
    }
    
    @Override
    public String getPrefix( String namespaceURI )
    {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public Iterator<String> getPrefixes( String namespaceURI )
    {
        throw new UnsupportedOperationException();
    }
    
}
