package de.bund.bsi.tresor.aip.validator.syntax.validators;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.xml.bind.DataBindingException;
import javax.xml.bind.JAXB;

import org.apache.commons.io.FileUtils;
import org.etsi.uri._02918.v1_2.ASiCManifestType;
import org.etsi.uri._02918.v1_2.DataObjectReferenceType;
import org.etsi.uri._02918.v1_2.ExtensionsListType;
import org.etsi.uri._02918.v1_2.SigReferenceType;
import org.etsi.uri._19512.v1_1.ContainerIDType;

import de.bund.bsi.tr_esor.xaip.XAIPType;
import de.bund.bsi.tresor.aip.validator.api.control.AIPUtil;
import de.bund.bsi.tresor.aip.validator.api.control.ModuleLogger;
import eu.europa.esig.dss.asic.cades.validation.ASiCContainerWithCAdESValidatorFactory;
import eu.europa.esig.dss.asic.xades.validation.ASiCContainerWithXAdESValidatorFactory;
import eu.europa.esig.dss.model.DSSDocument;
import eu.europa.esig.dss.model.InMemoryDocument;

/**
 * Looks like duplicate implementation of the ASiCChecker but is required to use custom modules
 * 
 * @author wolffs
 */
public enum ASiCAIPValidator
{
    INSTANCE;
    
    private static ASiCContainerWithCAdESValidatorFactory cadesASiCValidator = new ASiCContainerWithCAdESValidatorFactory();
    private static ASiCContainerWithXAdESValidatorFactory xadesASiCValidator = new ASiCContainerWithXAdESValidatorFactory();
    
    /**
     * Checking if the data contains a valid ASiC container
     * 
     * @param data
     *            the document data
     * @return if the data could contain an ASiC container
     */
    public boolean isASiC( byte[] data )
    {
        try
        {
            DSSDocument document = new InMemoryDocument( new ByteArrayInputStream( data ) );
            
            boolean isCAdESASiC = cadesASiCValidator.isSupported( document );
            boolean isXAdESASiC = xadesASiCValidator.isSupported( document );
            
            boolean isASiC = isCAdESASiC || isXAdESASiC;
            ModuleLogger.verbose( isASiC ? "data is ASiC-AIP" : "data is not ASiC-AIP" );
            
            return isASiC;
        }
        catch ( Exception e )
        {
            ModuleLogger.verbose( "data is not ASiC-AIP", e );
        }
        
        return false;
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
        
        try
        {
            unzipped = Optional.ofNullable( unzip( zippedData ) );
            
            // do ifPresent?
            if ( unzipped.isPresent() )
            {
                File asicAip = unzipped.get();
                for ( File candidate : asicAip.listFiles() )
                {
                    if ( candidate.isFile() )
                    {
                        try ( FileInputStream in = new FileInputStream( candidate ) )
                        {
                            if ( isAIP( in ) )
                            {
                                validAIPFiles.add( candidate );
                            }
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
                File xaipFile = validAIPFiles.get( 0 );
                zippedData = FileUtils.readFileToByteArray( xaipFile );
                
                unzipped.ifPresent( asicDir -> {
                    validateASiCAIPStructure( asicDir, xaipFile );
                } );
            }
        }
        finally
        {
            unzipped.ifPresent( FileUtils::deleteQuietly );
        }
        
        return zippedData;
    }
    
    public void validateASiCAIPStructure( File asicDir, File xaipFile )
    {
        XAIPType xaip = JAXB.unmarshal( xaipFile, XAIPType.class );
        File metaInf = Arrays.stream( asicDir.listFiles() )
                .filter( file -> file.isDirectory() )
                .filter( file -> file.getName().equals( "META-INF" ) )
                .findAny()
                .orElseThrow( () -> new IllegalArgumentException( "missing asic META-INF directory" ) );
        
        validateMetaInf( xaip, metaInf );
    }
    
    void validateMetaInf( XAIPType xaip, File metaInf )
    {
        String aoid = AIPUtil.findAoid( xaip ).orElseThrow( () -> new IllegalArgumentException( "missing aoid in xaip" ) );
        Map<String, Set<String>> oidsByVersion = AIPUtil.oidsByVersion( xaip );
        
        Map<String, Set<String>> errorsByManifest = new HashMap<>();
        for ( File file : metaInf.listFiles() )
        {
            try
            {
                ASiCManifestType manifest = JAXB.unmarshal( file, ASiCManifestType.class );
                Set<String> errors = validateManifest( metaInf, manifest, aoid, oidsByVersion );
                
                if ( !errors.isEmpty() )
                {
                    errorsByManifest.put( file.getName(), errors );
                }
            }
            catch ( DataBindingException e )
            {
                // ignore if file is not manifest
            }
        }
        
        if ( !oidsByVersion.isEmpty() )
        {
            throw new IllegalArgumentException( "missing aisc-manifest for versions: " + oidsByVersion.keySet().toString() );
        }
        
        if ( !errorsByManifest.isEmpty() )
        {
            throw new IllegalArgumentException( "invalid asic-aip structure: " + errorsByManifest.toString() );
        }
    }
    
    Set<String> validateManifest( File metaInf, ASiCManifestType manifest, String aoid, Map<String, Set<String>> oidsByVersion )
    {
        Set<String> errors = new HashSet<>();
        SigReferenceType sigReference = manifest.getSigReference();
        String uri = sigReference.getURI();
        
        ExtensionsListType extensions = manifest.getASiCManifestExtensions();
        Optional<ContainerIDType> optContainer = extensions.getExtension().stream()
                .filter( ContainerIDType.class::isInstance )
                .map( ContainerIDType.class::cast )
                .findAny();
        
        if ( !Arrays.stream( metaInf.list() )
                .anyMatch( name -> name.equals( uri.substring( 0, uri.lastIndexOf( File.separatorChar ) ) ) ) )
        {
            errors.add( "missing sigRef" );
        }
        
        if ( optContainer.isPresent() )
        {
            ContainerIDType container = optContainer.get();
            String manifestAoid = container.getPOID();
            String manifestVersion = container.getVersionID();
            
            Set<String> oids = new HashSet<>();
            if ( !aoid.equals( manifestAoid ) )
            {
                errors.add( "aoid mismatch" );
            }
            
            if ( oidsByVersion.containsKey( manifestVersion ) )
            {
                oids = oidsByVersion.remove( manifestVersion );
            }
            else
            {
                errors.add( "referencing non existing version" );
            }
            
            if ( !oids.stream()
                    .allMatch( oid -> manifest.getDataObjectReference().stream()
                            .map( DataObjectReferenceType::getURI )
                            .anyMatch( ref -> ref.contains( oid ) ) ) )
            {
                errors.add( "missing or invalid oid-ref" );
            }
        }
        
        return errors;
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
}
