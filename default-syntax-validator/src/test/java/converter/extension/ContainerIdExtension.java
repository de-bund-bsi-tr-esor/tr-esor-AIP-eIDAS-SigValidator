package converter.extension;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import eu.europa.esig.dss.DomUtils;
import eu.europa.esig.dss.definition.DSSNamespace;

/**
 * @author wolffs
 */
public class ContainerIdExtension extends ASiCExtension
{
    private String                   poId;
    private String                   versionId;
    
    public static final DSSNamespace PRES = new DSSNamespace( "http://uri.etsi.org/019512/v1.0#", "pres" );
    
    /**
     * @param critical
     */
    public ContainerIdExtension( boolean critical, String poId, String versionId )
    {
        super( critical );
        this.poId = poId;
        this.versionId = versionId;
    }
    
    /**
     * Creating a new containerIdExtension with critical set to false
     * 
     * @param poId
     *            the aoid of the xaip
     * @param versionId
     *            the version of the xaip
     */
    public ContainerIdExtension( String poId, String versionId )
    {
        this( false, poId, versionId );
    }
    
    @Override
    protected void addTo( Document dom, Element extensionRoot )
    {
        final Element containerId = DomUtils.addElement( dom, extensionRoot, PRES, ASiCExt.CONTAINERID );
        
        DomUtils.addTextElement( dom, containerId, PRES, ASiCExt.POID, poId );
        DomUtils.addTextElement( dom, containerId, PRES, ASiCExt.VERSIONID, versionId );
    }
    
}
