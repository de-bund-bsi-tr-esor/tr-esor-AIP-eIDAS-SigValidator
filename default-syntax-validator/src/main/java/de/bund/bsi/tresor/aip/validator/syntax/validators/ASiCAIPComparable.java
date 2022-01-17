package de.bund.bsi.tresor.aip.validator.syntax.validators;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.xml.bind.JAXBElement;

import org.etsi.uri._02918.v1_2.ASiCManifestType;
import org.etsi.uri._02918.v1_2.DataObjectReferenceType;
import org.etsi.uri._02918.v1_2.ExtensionsListType;
import org.etsi.uri._19512.v1_1.ContainerIDType;

import de.bund.bsi.tr_esor.xaip.DataObjectType;
import de.bund.bsi.tr_esor.xaip.PackageHeaderType;
import de.bund.bsi.tr_esor.xaip.VersionManifestType;
import de.bund.bsi.tr_esor.xaip.XAIPType;
import de.bund.bsi.tresor.aip.validator.api.control.AIPUtil;
import de.bund.bsi.tresor.aip.validator.api.control.Canonicalization;
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
    // transform?
    // dataObjects in this model?
    
    private String      c14n;
    private String      aoid;
    private String      version;
    private Set<String> dataObjectIds = new HashSet<>();
    
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
            Set<String> dataObjectIds = AIPUtil.versionPointer( versionManifest ).stream()
                    .map( JAXBElement::getValue )
                    .filter( DataObjectType.class::isInstance )
                    .map( DataObjectType.class::cast )
                    .map( DataObjectType::getDataObjectID )
                    .collect( toSet() );
            
            ASiCAIPComparable comparable = new ASiCAIPComparable();
            comparable.setAoid( aoid );
            comparable.setVersion( versionID );
            comparable.setDataObjectIds( dataObjectIds );
            comparable.setC14n( c14nMethod );
            
            results.add( comparable );
        }
        
        return results;
    }
    
    public static Optional<ASiCAIPComparable> fromManifest( ASiCManifestType manifest )
    {
        List<DataObjectReferenceType> doRef = manifest.getDataObjectReference();
        
        // filter critical extensions
        List<ContainerIDType> containerIds = Optional.ofNullable( manifest.getASiCManifestExtensions() )
                .map( ExtensionsListType::getExtension ).orElse( emptyList() ).stream()
                .filter( ContainerIDType.class::isInstance )
                .map( ContainerIDType.class::cast )
                .collect( toList() );
        
        // when critical
        if ( containerIds.size() != 1 )
        {
            throw new IllegalStateException( "only one containerId extension per manifest is allowed" );
        }
        else if ( containerIds.size() >= 1 )
        {
            ASiCAIPComparable comparable = new ASiCAIPComparable();
            ContainerIDType containerId = containerIds.get( 0 );
            comparable.setAoid( containerId.getPOID() );
            comparable.setVersion( containerId.getVersionID() );
            
            if ( true )
            {
                throw new IllegalStateException( "TODO: dataObjectRef" );
            }
            
            return Optional.of( comparable );
        }
        
        return Optional.empty();
    }
    
    public void compareWith( ASiCAIPComparable compare )
    {
        // TODO create some kind of diff
        Set<String> errors = new HashSet<>();
        
        if ( !errors.isEmpty() )
        {
            
        }
    }
}