package converter.extension;

import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import eu.europa.esig.dss.DomUtils;
import eu.europa.esig.dss.asic.common.definition.ASiCAttribute;
import eu.europa.esig.dss.asic.common.definition.ASiCElement;
import eu.europa.esig.dss.asic.common.definition.ASiCNamespace;
import lombok.Getter;

/**
 * @author wolffs
 */
@Getter
public class ExtensionPatcher
{
    
    private Document dom;
    private Element  extensionsRoot;
    
    public ExtensionPatcher( Document document, Element parent )
    {
        this.dom = document;
        this.extensionsRoot = DomUtils.addElement( document, parent, ASiCNamespace.NS, ASiCElement.ASIC_MANIFEST_EXTENSIONS );
    }
    
    public void patch( List<? extends ASiCExtension> extensions )
    {
        // currently only supporting ASiCManifestExtension (see constructor)
        // TODO FIXME: support dataObjectReferenceExtensions which are bound to a dataObjectRef
        for ( ASiCExtension extension : extensions )
        {
            Element extensionContainer = addExtension( dom, extensionsRoot, extension.isCritical() );
            extension.addTo( dom, extensionContainer );
        }
    }
    
    private Element addExtension( final Document document, final Element parent, boolean critical )
    {
        final Element extension = DomUtils.addElement( document, parent, ASiCNamespace.NS, ASiCElement.EXTENSION );
        extension.setAttribute( ASiCAttribute.CRITICAL.getAttributeName(), String.valueOf( critical ) );
        
        return extension;
    }
}
