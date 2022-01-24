package de.bund.bsi.tresor.aip.validator.syntax.asic;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import javax.xml.transform.dom.DOMSource;

import org.etsi.uri._02918.v1_2.ASiCManifestType;
import org.etsi.uri._02918.v1_2.DataObjectReferenceType;
import org.etsi.uri._02918.v1_2.ExtensionType;
import org.etsi.uri._02918.v1_2.ExtensionsListType;
import org.etsi.uri._19512.v1_1.ContainerIDType;
import org.w3._2000._09.xmldsig_.CanonicalizationMethodType;
import org.w3c.dom.Element;

import de.bund.bsi.tresor.aip.validator.api.control.Canonicalization;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author wolffs
 */
@Getter
@ToString
@AllArgsConstructor
public class CompareableManifest
{
    private static final JAXBElement<ContainerIDType>            CONTAINER_ID = new org.etsi.uri._19512.v1_1.ObjectFactory()
            .createContainerID( null );
    
    private static final JAXBElement<CanonicalizationMethodType> C14NMETHOD   = new org.w3._2000._09.xmldsig_.ObjectFactory()
            .createCanonicalizationMethod( null );
    
    private ASiCCompare                                          compare;
    private Set<DataObjectReferenceType>                         doRefs       = new HashSet<>();
    
    public static Optional<CompareableManifest> fromManifest( ASiCManifestType manifest )
    {
        // TODO filter critical extensions
        List<Element> extensions = Optional.ofNullable( manifest.getASiCManifestExtensions() )
                .map( ExtensionsListType::getExtension )
                .orElse( emptyList() ).stream()
                .map( ExtensionType::getContent )
                .flatMap( List::stream )
                .filter( Element.class::isInstance )
                .map( Element.class::cast )
                .collect( toList() );
        
        List<ContainerIDType> containerIds = extractExtensionElements( extensions, CONTAINER_ID );
        String c14nMethod = extractExtensionElements( extensions, C14NMETHOD ).stream()
                .map( CanonicalizationMethodType::getAlgorithm )
                .findAny()
                .orElse( Canonicalization.defaultC14nMethod() );
        
        // when critical
        if ( containerIds.size() != 1 )
        {
            throw new IllegalStateException( "only one containerId extension per manifest is allowed" );
        }
        else if ( containerIds.size() >= 1 )
        {
            ContainerIDType containerId = containerIds.get( 0 );
            ASiCCompare comparable = new ASiCCompare( c14nMethod, containerId.getPOID(), containerId.getVersionID() );
            
            return Optional.of( new CompareableManifest( comparable, new HashSet<>( manifest.getDataObjectReference() ) ) );
        }
        
        return Optional.empty();
    }
    
    static <T> List<T> extractExtensionElements( List<Element> extensions, JAXBElement<T> type )
    {
        QName qname = type.getName();
        return extensions.stream()
                .filter( e -> qname.getNamespaceURI().equals( e.getNamespaceURI() )
                        && qname.getLocalPart().equals( e.getLocalName() ) )
                .map( e -> JAXB.unmarshal( new DOMSource( e ), type.getDeclaredType() ) )
                .collect( toList() );
    }
}
