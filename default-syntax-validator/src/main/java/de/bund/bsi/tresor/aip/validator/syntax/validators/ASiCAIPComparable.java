package de.bund.bsi.tresor.aip.validator.syntax.validators;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import javax.xml.transform.dom.DOMSource;

import org.bouncycastle.util.Objects;
import org.etsi.uri._02918.v1_2.ASiCManifestType;
import org.etsi.uri._02918.v1_2.DataObjectReferenceType;
import org.etsi.uri._02918.v1_2.ExtensionType;
import org.etsi.uri._02918.v1_2.ExtensionsListType;
import org.etsi.uri._19512.v1_1.ContainerIDType;
import org.w3._2000._09.xmldsig_.CanonicalizationMethodType;
import org.w3c.dom.Element;

import de.bund.bsi.tr_esor.xaip.DataObjectType;
import de.bund.bsi.tr_esor.xaip.PackageHeaderType;
import de.bund.bsi.tr_esor.xaip.VersionManifestType;
import de.bund.bsi.tr_esor.xaip.XAIPType;
import de.bund.bsi.tresor.aip.validator.api.control.AIPUtil;
import de.bund.bsi.tresor.aip.validator.api.control.Canonicalization;
import eu.europa.esig.dss.model.DSSDocument;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Extracted information from the xaip which should be validated
 * 
 * @author wolffs
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
class ASiCAIPComparable
{
    // optional: transform
    // dataObjects in this model?
    
    private String                                               c14n;
    private String                                               aoid;
    private String                                               version;
    private Set<DataObjectReferenceType>                         doRefs       = new HashSet<>();
    
    private static final JAXBElement<ContainerIDType>            CONTAINER_ID = new org.etsi.uri._19512.v1_1.ObjectFactory()
            .createContainerID( null );
    
    private static final JAXBElement<CanonicalizationMethodType> C14NMETHOD   = new org.w3._2000._09.xmldsig_.ObjectFactory()
            .createCanonicalizationMethod( null );
    
    public static List<ASiCAIPComparable> fromAIP( XAIPType xaip )
    {
        List<ASiCAIPComparable> results = new ArrayList<>();
        
        String c14nMethod = Canonicalization.getC14nMethod();
        String aoid = AIPUtil.findAoid( xaip ).orElseThrow( () -> new IllegalStateException( "missing aoid" ) );
        List<VersionManifestType> versions = Optional.ofNullable( xaip )
                .map( XAIPType::getPackageHeader )
                .map( PackageHeaderType::getVersionManifest )
                .orElseThrow( () -> new IllegalStateException( "missing version manifest in xaip" ) );
        
        for ( VersionManifestType versionManifest : versions )
        {
            String versionID = versionManifest.getVersionID();
            
            // TODO LXAIP/XAIP content & ref from dataObject
            Set<String> dataObjectIds = AIPUtil.versionPointer( versionManifest ).stream()
                    .map( JAXBElement::getValue )
                    .filter( DataObjectType.class::isInstance )
                    .map( DataObjectType.class::cast )
                    .map( DataObjectType::getDataObjectID )
                    .collect( toSet() );
            
            ASiCAIPComparable comparable = new ASiCAIPComparable();
            comparable.setAoid( aoid );
            comparable.setC14n( c14nMethod );
            comparable.setVersion( versionID );
            // comparable.setDataObjectHashes( hashes );
            
            results.add( comparable );
        }
        
        return results;
    }
    
    public static Optional<ASiCAIPComparable> fromManifest( ASiCManifestType manifest, List<DSSDocument> rootDocuments )
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
            ASiCAIPComparable comparable = new ASiCAIPComparable();
            ContainerIDType containerId = containerIds.get( 0 );
            comparable.setC14n( c14nMethod );
            comparable.setAoid( containerId.getPOID() );
            comparable.setVersion( containerId.getVersionID() );
            comparable.setDoRefs( new HashSet<>( manifest.getDataObjectReference() ) );
            
            return Optional.of( comparable );
        }
        
        return Optional.empty();
    }
    
    public boolean matches( ASiCAIPComparable compare )
    {
        // Set<DataObjectReferenceType> remaining = new HashSet<>( doRefs );
        // remaining.stream()
        // .dropWhile( ref -> {
        // return compare.getDoRefs().stream()
        // .anyMatch( cmpRef -> {
        // // check uri?
        // return Objects.areEqual( ref.getDigestMethod(), cmpRef.getDigestMethod() )
        // && Arrays.areEqual( ref.getDigestValue(), cmpRef.getDigestValue() );
        // } );
        // } );
        
        return Objects.areEqual( c14n, compare.getC14n() )
                && Objects.areEqual( aoid, compare.getAoid() )
                && Objects.areEqual( version, compare.getVersion() );
        // && (doRefs.size() == compare.getDoRefs().size() && remaining.isEmpty());
    }
    
    static <T> List<T> extractExtensionElements( List<Element> extensions, JAXBElement<T> type )
    {
        QName qname = type.getName();
        return extensions.stream()
                .filter( e -> qname.getNamespaceURI().equals( e.getNamespaceURI() )
                        && qname.getLocalPart().equals( e.getLocalName() ) )
                .map( e -> JAXB.unmarshal( new DOMSource( e ), type.getClass() ) )
                .collect( toList() );
    }
}