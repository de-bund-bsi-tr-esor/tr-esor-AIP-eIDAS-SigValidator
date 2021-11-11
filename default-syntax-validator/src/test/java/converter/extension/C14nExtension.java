package converter.extension;

import java.net.URL;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import eu.europa.esig.dss.DomUtils;
import eu.europa.esig.dss.definition.DSSNamespace;

/**
 * @author wolffs
 */
public class C14nExtension extends ASiCExtension
{
    private URL                      c14nMethod;
    
    public static final DSSNamespace DS = new DSSNamespace( "http://www.w3.org/2000/09/xmldsig#", "ds" );
    
    /**
     * @param critical
     */
    public C14nExtension( boolean critical, URL c14nMethod )
    {
        super( critical );
        this.c14nMethod = c14nMethod;
    }
    
    /**
     * Creating a new c14nExtension with critical set to false
     * 
     * @param uri
     *            the c14nMethod
     * 
     */
    public C14nExtension( URL c14nMethod )
    {
        this( false, c14nMethod );
    }
    
    @Override
    protected void addTo( Document dom, Element extensionRoot )
    {
        DomUtils.addTextElement( dom, extensionRoot, DS, ASiCExt.C14NMETHOD, c14nMethod.toString() );
    }
    
}
