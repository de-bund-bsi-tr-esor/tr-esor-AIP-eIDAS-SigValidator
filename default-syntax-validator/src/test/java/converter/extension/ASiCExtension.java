package converter.extension;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wolffs
 */
@Getter
@AllArgsConstructor
public abstract class ASiCExtension
{
    private boolean critical;
    
    protected abstract void addTo( Document dom, Element extensionRoot );
    
}
