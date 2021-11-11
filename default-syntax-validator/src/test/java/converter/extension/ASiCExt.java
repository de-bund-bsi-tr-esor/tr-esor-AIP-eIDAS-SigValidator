package converter.extension;

import eu.europa.esig.dss.definition.DSSElement;
import eu.europa.esig.dss.definition.DSSNamespace;
import lombok.Getter;

/**
 * @author wolffs
 */
@Getter
public enum ASiCExt implements DSSElement
{
    C14NMETHOD( C14nExtension.DS, "CanonicalizationMethod" ),
    
    CONTAINERID( ContainerIdExtension.PRES, "ContainerID" ),
    
    POID( ContainerIdExtension.PRES, "POID" ),
    
    VERSIONID( ContainerIdExtension.PRES, "VersionID" );
    
    private final DSSNamespace namespace;
    private final String       tagName;
    
    ASiCExt( DSSNamespace namespace, String tagName )
    {
        this.tagName = tagName;
        this.namespace = namespace;
    }
    
    @Override
    public String getURI()
    {
        return namespace.getUri();
    }
    
    @Override
    public boolean isSameTagName( String value )
    {
        return tagName.equals( value );
    }
    
}
