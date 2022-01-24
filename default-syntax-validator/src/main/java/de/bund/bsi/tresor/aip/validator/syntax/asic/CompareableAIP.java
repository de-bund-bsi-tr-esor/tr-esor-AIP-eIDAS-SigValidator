package de.bund.bsi.tresor.aip.validator.syntax.asic;

import static java.util.stream.Collectors.toSet;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.xml.bind.DataBindingException;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBElement;

import org.etsi.uri._02918.v1_2.DataObjectReferenceType;

import de.bund.bsi.tr_esor.xaip.DataObjectType;
import de.bund.bsi.tr_esor.xaip.PackageHeaderType;
import de.bund.bsi.tr_esor.xaip.VersionManifestType;
import de.bund.bsi.tr_esor.xaip.XAIPType;
import de.bund.bsi.tresor.aip.validator.api.control.AIPUtil;
import de.bund.bsi.tresor.aip.validator.api.control.Canonicalization;
import de.bund.bsi.tresor.aip.validator.api.control.ModuleLogger;
import de.bund.bsi.tresor.aip.validator.api.entity.AIPValidatorException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author wolffs
 */
@Getter
@ToString
@AllArgsConstructor
public class CompareableAIP
{
    private ASiCCompare         compare;
    
    private Set<DataObjectType> dataObjects = new HashSet<>();
    
    public static List<CompareableAIP> fromAIP( XAIPType xaip )
    {
        List<CompareableAIP> results = new ArrayList<>();
        
        String c14nMethod = Canonicalization.getC14nMethod();
        String aoid = AIPUtil.findAoid( xaip ).orElseThrow( () -> new IllegalStateException( "missing aoid" ) );
        List<VersionManifestType> versions = Optional.ofNullable( xaip )
                .map( XAIPType::getPackageHeader )
                .map( PackageHeaderType::getVersionManifest )
                .orElseThrow( () -> new IllegalStateException( "missing version manifest in xaip" ) );
        
        for ( VersionManifestType versionManifest : versions )
        {
            String versionID = versionManifest.getVersionID();
            Set<DataObjectType> dataObjects = AIPUtil.versionPointer( versionManifest ).stream()
                    .map( JAXBElement::getValue )
                    .filter( DataObjectType.class::isInstance )
                    .map( DataObjectType.class::cast )
                    .collect( toSet() );
            
            ASiCCompare comparable = new ASiCCompare( c14nMethod, aoid, versionID );
            results.add( new CompareableAIP( comparable, dataObjects ) );
        }
        
        return results;
    }
    
    public boolean matches( CompareableManifest manifest )
    {
        if ( compare.matches( manifest.getCompare() ) )
        {
            int nonMatching = new HashSet<>( manifest.getDoRefs() ).stream()
                    .dropWhile( this::dropMatchingDigest )
                    .collect( toSet() )
                    .size();
            
            return nonMatching == 0;
        }
        
        return false;
    }
    
    boolean dropMatchingDigest( DataObjectReferenceType ref )
    {
        String uri = ref.getURI();
        String algorithm = ref.getDigestMethod().getAlgorithm();
        
        return dataObjects.stream()
                .filter( obj -> uriFilter( obj, uri ) )
                .findAny()
                .map( obj -> digest( obj, algorithm ) )
                .map( digest -> Arrays.equals( digest, ref.getDigestValue() ) )
                .orElse( false );
    }
    
    //////////// TODO //////////////
    
    byte[] digest( DataObjectType dataObject, String algorithm )
    {
        return AIPUtil.dataSupplier( dataObject )
                .map( data -> AIPUtil.extractLxaipData( data ).orElse( data ) )
                .map( data -> {
                    try
                    {
                        MessageDigest digester = MessageDigest.getInstance( algorithm );
                        digester.update( data );
                        
                        return digester.digest();
                    }
                    catch ( NoSuchAlgorithmException e )
                    {
                        throw new AIPValidatorException( "algorithm " + algorithm + " is not supported", e );
                    }
                } )
                .orElse( new byte[0] );
    }
    
    boolean uriFilter( DataObjectType dataObject, String uri )
    {
        return AIPUtil.findDataReferences( dataObject )
                .map( DataObjectReferenceType::getURI )
                .map( uri::equals ) // TODO currently not working
                .orElseGet( () -> {
                    // TODO fix following for case xaip: does uri match this objectId
                    String oid = dataObject.getDataObjectID();
                    return uri.contains( oid );
                } );
    }
    
    /// LXAIP
    
    public static Optional<byte[]> extractLxaipData( byte[] xmlData )
    {
        Optional<byte[]> result = Optional.empty();
        try ( ByteArrayInputStream xmlIn = new ByteArrayInputStream( xmlData ) )
        {
            DataObjectReferenceType dataRef = JAXB.unmarshal( xmlIn, DataObjectReferenceType.class );
            Optional<Path> optPath = Optional.ofNullable( dataRef.getURI() ).map( Paths::get );
            
            if ( optPath.isPresent() )
            {
                result = Optional.of( Files.readAllBytes( optPath.get() ) );
            }
        }
        catch ( DataBindingException e )
        {
            // no lxaip
        }
        catch ( IOException e )
        {
            // could not read lxaip data
            ModuleLogger.verbose( "could not retrieve lxaip data from dataObject", e );
        }
        
        return result;
    }
    
}
