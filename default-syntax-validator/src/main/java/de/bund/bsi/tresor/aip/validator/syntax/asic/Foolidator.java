package de.bund.bsi.tresor.aip.validator.syntax.asic;

import static java.util.stream.Collectors.toList;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.xml.bind.JAXB;

import org.apache.commons.io.FileUtils;
import org.etsi.uri._02918.v1_2.ASiCManifestType;

import de.bund.bsi.tr_esor.xaip.XAIPType;
import eu.europa.esig.dss.asic.cades.validation.ASiCContainerWithCAdESValidator;
import eu.europa.esig.dss.asic.cades.validation.ASiCContainerWithCAdESValidatorFactory;
import eu.europa.esig.dss.asic.common.validation.AbstractASiCContainerValidator;
import eu.europa.esig.dss.asic.xades.validation.ASiCContainerWithXAdESValidator;
import eu.europa.esig.dss.asic.xades.validation.ASiCContainerWithXAdESValidatorFactory;
import eu.europa.esig.dss.model.CommonDocument;
import eu.europa.esig.dss.model.DSSDocument;
import eu.europa.esig.dss.model.FileDocument;

/**
 * @author wolffs
 */
public class Foolidator
{
    /**
     * Allgemein: <br/>
     * - C14N Methode aus XAIP muss mit einer Extension aus dem Manifest verglichen werden und identisch sein<br/>
     * - aoid = containerID-POID aus manifest<br/>
     * - version = versionID aus manifest<br/>
     * - optional: transform <br/>
     * - XAIP im root verzeichnis <br/>
     * - pro version ein manifest
     * 
     * ASIC-S<br/>
     * - Klärung: dataObjectRef<br/>
     * 
     * ASIC-E<br/>
     * 
     * XAIP<br/>
     * LXAIP<br/>
     * - keine kein transformationselement notwendig<br/>
     * - aoid, version & c14n müssen nicht verstanden werden (vermutl. bei aisc prüfung?)
     * 
     */
    
    private static ASiCContainerWithCAdESValidatorFactory cadesASiCValidator = new ASiCContainerWithCAdESValidatorFactory();
    private static ASiCContainerWithXAdESValidatorFactory xadesASiCValidator = new ASiCContainerWithXAdESValidatorFactory();
    
    /**
     * Analysing the proper validator for the document and returning the asicValidator implementation or an empty optional if input is not
     * asic
     * 
     * @param input
     *            the dss document
     * @return the proper asic validator or empty optional if input is not asic
     */
    public static Optional<AbstractASiCContainerValidator> findValidatorImpl( DSSDocument input )
    {
        return Optional.ofNullable( cadesASiCValidator.isSupported( input ) ? new ASiCContainerWithCAdESValidator( input )
                : xadesASiCValidator.isSupported( input ) ? new ASiCContainerWithXAdESValidator( input ) : null );
    }
    
    /**
     * Checking if the zipped archive contains an aip candidate
     * 
     * @param zippedData
     *            the zipped data
     * @return the aip candidate
     * @throws IOException
     *             when an exception occurs
     */
    public byte[] findAIPCandidate( byte[] zippedData ) throws IOException
    {
        Optional<File> unzipped = Optional.empty();
        List<File> validAIPFiles = new ArrayList<>();
        
        ziptest: try
        {
            unzipped = Optional.ofNullable( unzip( zippedData ) );
            if ( !unzipped.isPresent() )
            {
                break ziptest;
            }
            
            File asicAip = unzipped.get();
            
            for ( File file : asicAip.listFiles() )
            {
                if ( file.isFile() )
                {
                    try ( FileInputStream in = new FileInputStream( file ) )
                    {
                        if ( isAIP( in ) )
                        {
                            validAIPFiles.add( file );
                        }
                    }
                }
            }
            
            if ( validAIPFiles.size() > 1 )
            {
                throw new IllegalStateException( "asicAip contains " + validAIPFiles.size() + " aip files, allowed is one file" );
            }
            else if ( validAIPFiles.size() == 1 )
            {
                zippedData = FileUtils.readFileToByteArray( validAIPFiles.get( 0 ) );
                
                // TODO enable when asic validation is implemented
                unzipped
                        .map( FileDocument::new )
                        .ifPresent( this::validateASiCAIP );
            }
        }
        finally
        {
            unzipped.ifPresent( FileUtils::deleteQuietly );
        }
        
        return zippedData;
    }
    
    public void validateASiCAIP( CommonDocument asicAip )
    {
        Optional<AbstractASiCContainerValidator> optValidator = findValidatorImpl( asicAip );
        if ( optValidator.isEmpty() )
            return;
        
        AbstractASiCContainerValidator validator = optValidator.get();
        
        List<CompareableAIP> sources = extractAIP( validator );
        List<CompareableManifest> manifests = extractArchiveManifest( validator );
        
        int sourceSize = sources.size();
        int manifestSize = manifests.size();
        if ( sourceSize != manifestSize )
        {
            throw new RuntimeException(
                    "aip contains " + sourceSize + " versions which does not match number of archiveManifests which is " + manifestSize );
        }
        
        for ( CompareableAIP source : sources )
        {
            if ( !manifests.stream().anyMatch( source::matches ) )
            {
                throw new RuntimeException( "an aip version and manifest declaration do not match" );
            }
        }
    }
    
    List<CompareableAIP> extractAIP( AbstractASiCContainerValidator validator )
    {
        List<XAIPType> aips = validator.getAllDocuments().stream()
                .filter( doc -> !doc.getName().contains( File.separator ) )
                .map( doc -> {
                    try
                    {
                        return JAXB.unmarshal( doc.openStream(), XAIPType.class );
                    }
                    catch ( Exception e )
                    {
                        return null;
                    }
                } )
                .filter( Objects::nonNull )
                .collect( toList() );
        
        if ( aips.size() != 1 )
        {
            throw new RuntimeException( "asic-aip does not contain exactly 1" );
        }
        
        return CompareableAIP.fromAIP( aips.get( 0 ) );
    }
    
    public List<CompareableManifest> extractArchiveManifest( AbstractASiCContainerValidator validator )
    {
        // TODO don't use getManifestDocuments since evidenceRecordManifests are also valid
        return validator.getManifestDocuments()
                .stream()
                .map( doc -> {
                    try
                    {
                        return JAXB.unmarshal( doc.openStream(), ASiCManifestType.class );
                    }
                    catch ( Exception e )
                    {
                        return null;
                    }
                } )
                .filter( Objects::nonNull )
                .map( manifest -> CompareableManifest.fromManifest( manifest ) )
                .filter( Optional::isPresent )
                .map( Optional::get )
                .collect( toList() );
    }
    
    /**
     * Unzipping the file
     * 
     * @param zip
     *            the zipped data
     * @return the unzipped file
     * @throws IOException
     *             when an error occurs
     */
    File unzip( byte[] zip ) throws IOException
    {
        File unzipped = Files.createTempDirectory( "xVal" ).toFile();
        try ( ZipInputStream stream = new ZipInputStream( new ByteArrayInputStream( zip ) ) )
        {
            ZipEntry entry = null;
            while ( (entry = stream.getNextEntry()) != null )
            {
                File file = new File( unzipped, entry.getName() );
                Path parent = file.toPath().getParent();
                
                if ( !entry.isDirectory() )
                {
                    if ( !Files.exists( parent ) )
                    {
                        Files.createDirectories( parent );
                    }
                    
                    try ( FileOutputStream out = new FileOutputStream( file ) )
                    {
                        for ( int c = stream.read(); c != -1; c = stream.read() )
                        {
                            out.write( c );
                        }
                    }
                }
            }
        }
        
        return unzipped;
    }
    
    /**
     * Checking if the inputStream contains an aip
     * 
     * @param in
     *            the inputStream
     * @return if the inputStream contains an aip
     */
    boolean isAIP( InputStream in )
    {
        try
        {
            JAXB.unmarshal( in, XAIPType.class );
            return true;
        }
        catch ( Exception e )
        {
            return false;
        }
    }
    
}
