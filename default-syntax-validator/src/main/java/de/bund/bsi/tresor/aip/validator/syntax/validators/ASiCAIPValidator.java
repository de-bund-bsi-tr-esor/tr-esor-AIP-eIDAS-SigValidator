package de.bund.bsi.tresor.aip.validator.syntax.validators;

import static java.util.Collections.emptySet;
import static java.util.stream.Collectors.toList;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
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
import javax.xml.transform.dom.DOMSource;

import org.apache.commons.io.FileUtils;
import org.etsi.uri._02918.v1_2.ASiCManifestType;
import org.etsi.uri._02918.v1_2.DataObjectReferenceType;
import org.etsi.uri._02918.v1_2.ExtensionsListType;
import org.etsi.uri._02918.v1_2.SigReferenceType;
import org.etsi.uri._19512.v1_1.ContainerIDType;
import org.w3c.dom.Element;

import de.bund.bsi.tr_esor.xaip.XAIPType;
import de.bund.bsi.tresor.aip.validator.api.control.AIPUtil;
import de.bund.bsi.tresor.aip.validator.api.control.AipUriResolver;
import de.bund.bsi.tresor.aip.validator.api.control.ModuleLogger;
import de.bund.bsi.tresor.aip.validator.syntax.context.DefaultSyntaxValidatorContext;
import eu.europa.esig.dss.asic.cades.validation.ASiCContainerWithCAdESValidatorFactory;
import eu.europa.esig.dss.asic.common.ASiCUtils;
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
            
            boolean isASiCE = ASiCUtils.isASiCEContainer( document );
            boolean isCAdESASiC = cadesASiCValidator.isSupported( document );
            boolean isXAdESASiC = xadesASiCValidator.isSupported( document );
            
            boolean isASiC = isASiCE && (isCAdESASiC || isXAdESASiC);
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
     * @param syntaxContext
     *            the context
     * @return the aip candidate
     * @throws IOException
     *             when an exception occurs
     */
    public byte[] findAIPCandidate( byte[] zippedData, DefaultSyntaxValidatorContext syntaxContext ) throws IOException
    {
        Optional<File> unzipped = Optional.empty();
        List<File> validAIPFiles = new ArrayList<>();
        
        try
        {
            unzipped = Optional.ofNullable( unzip( zippedData ) );
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
                
                syntaxContext.setTempPath( unzipped.get().getAbsolutePath() );
                System.setProperty( AIPUtil.TEMP_FOLDER_PATH, unzipped.get().getAbsolutePath() );
            }
            
            if ( validAIPFiles.size() > 1 )
            {
                throw new IllegalStateException( "asicAip contains " + validAIPFiles.size() + " aip files, allowed is one file" );
            }
            else if ( validAIPFiles.size() == 1 )
            {
                File xaipFile = validAIPFiles.get( 0 );
                syntaxContext.setAsicAIPContainer( zippedData );
                // do not change the order of the line above and below
                zippedData = FileUtils.readFileToByteArray( xaipFile );
                unzipped.ifPresent( asicDir -> {
                    validateASiCAIPStructure( asicDir, xaipFile );
                } );
            }
            else
            {
                throw new IllegalStateException( "asic container does not contain a valid aip file" );
            }
        }
        finally
        {
            // needs to be done later to be able to load the referenced files
            // unzipped.ifPresent( FileUtils::deleteQuietly );
        }
        
        return zippedData;
    }
    
    /**
     * Validating the asicAIP container structure
     * 
     * @param asicDir
     *            the unzipped asic directory
     * @param xaipFile
     *            the file
     */
    public void validateASiCAIPStructure( File asicDir, File xaipFile )
    {
        XAIPType xaip = JAXB.unmarshal( xaipFile, XAIPType.class );
        Arrays.stream( asicDir.listFiles() )
                .filter( file -> file.isDirectory() )
                .filter( file -> file.getName().equals( "META-INF" ) )
                .findAny()
                .ifPresentOrElse( metaInf -> validateMetaInf( xaip, asicDir, metaInf ),
                        () -> {
                            throw new IllegalArgumentException( "missing asic META-INF directory" );
                        } );
    }
    
    /**
     * Validating the metaInf
     * 
     * @param xaip
     *            the xaip from the asic container
     * @param metaInf
     *            the asic metaInf folder
     */
    void validateMetaInf( XAIPType xaip, File asicDir, File metaInf )
    {
        String aoid = AIPUtil.findAoid( xaip ).orElseThrow( () -> new IllegalArgumentException( "missing aoid in xaip" ) );
        Map<String, Set<String>> oidsByVersion = AIPUtil.oidsByVersion( xaip );
        
        boolean hasManifest = false;
        Map<String, Set<String>> errorsByManifest = new HashMap<>();
        for ( File file : metaInf.listFiles() )
        {
            try
            {
                ASiCManifestType manifest = JAXB.unmarshal( file, ASiCManifestType.class );
                Set<String> errors = validateManifest( asicDir, metaInf, manifest, xaip, aoid, oidsByVersion );
                
                hasManifest = true;
                if ( !errors.isEmpty() )
                {
                    errorsByManifest.put( file.getName(), errors );
                }
            }
            catch ( ClassCastException | DataBindingException e )
            {
                ModuleLogger.log( "manifest search: skipping " + file.getName() );
                ModuleLogger.verbose( "file is not an asicManifest: " + file.getName(), e );
            }
        }
        
        if ( !oidsByVersion.isEmpty() )
        {
            ModuleLogger.log( "[WARN] missing optional aisc-manifest containerID for versions: " + oidsByVersion.keySet().toString() );
        }
        
        if ( !hasManifest )
        {
            throw new IllegalArgumentException( "container is missing asicManifest" );
        }
        else if ( !errorsByManifest.isEmpty() )
        {
            throw new IllegalArgumentException( errorsByManifest.toString() );
        }
    }
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////
    
    Optional<ContainerIDType> extractContainer( ASiCManifestType manifest )
    {
        return Optional.ofNullable( manifest.getASiCManifestExtensions() )
                .map( ExtensionsListType::getExtension ).stream()
                .flatMap( List::stream )
                .map( f -> f.getContent() )
                .flatMap( List::stream )
                .filter( Element.class::isInstance )
                .map( Element.class::cast )
                .peek( e -> e.getNodeValue() )
                // .map( JAXBElement.class::cast )
                // .map( JAXBElement::getValue )
                .map( e -> JAXB.unmarshal( new DOMSource( e ), ContainerIDType.class ) )
                .filter( ContainerIDType.class::isInstance )
                .map( ContainerIDType.class::cast )
                .findAny();
    }
    
    Set<String> validateContainerExtension( ASiCManifestType manifest, String aoid, Map<String, Set<String>> oidsByVersion )
    {
        return extractContainer( manifest ).map( container -> {
            Set<String> errors = new HashSet<>();
            String manifestAoid = container.getPOID();
            String manifestVersion = container.getVersionID();
            
            Set<String> oids = new HashSet<>();
            if ( !aoid.equals( manifestAoid ) )
            {
                errors.add( "aoid mismatch" );
            }
            
            Optional.ofNullable( oidsByVersion.remove( manifestVersion ) )
                    .ifPresentOrElse( s -> {}, () -> errors.add( "referencing non existing version" ) );
            
            if ( !oids.stream().allMatch( oid -> manifest.getDataObjectReference().stream()
                    .map( DataObjectReferenceType::getURI )
                    .anyMatch( ref -> ref.contains( oid ) ) ) )
            {
                errors.add( "missing or invalid oid-ref" );
            }
            
            return errors;
        } ).orElse( emptySet() );
    }
    
    Set<String> validateSigRef( File metaInf, SigReferenceType sigReference )
    {
        Set<String> errors = new HashSet<>();
        String uri = sigReference.getURI();
        String substring = uri.substring( uri.lastIndexOf( File.separatorChar ) + 1 );
        
        if ( !Arrays.stream( metaInf.list() ).anyMatch( name -> name.equals( substring ) ) )
        {
            errors.add( "missing sigRef" );
        }
        
        return errors;
    }
    
    Set<String> validateDataRef( File asicDir, XAIPType xaip, List<DataObjectReferenceType> references )
    {
        Set<String> errors = new HashSet<>();
        List<File> rootFiles = Arrays.asList( asicDir.listFiles() ).stream().filter( File::isFile ).collect( toList() );
        
        for ( DataObjectReferenceType dataRef : references )
        {
            String uriRef = dataRef.getURI();
            try
            {
                URI uri = new URI( uriRef );
                String scheme = uri.getScheme();
                String authority = uri.getAuthority();
                
                boolean isFileScheme = Optional.ofNullable( scheme )
                        .map( f -> f.startsWith( "file" ) )
                        .orElse( false );
                
                if ( scheme == null || isFileScheme )
                {
                    String fileName = isFileScheme ? authority : uriRef;
                    if ( !findInRoot( rootFiles, fileName ).isPresent() )
                    {
                        errors.add( "file " + fileName + " is not in root" );
                    }
                }
                else if ( scheme.startsWith( "xaip" ) )
                {
                    findInRoot( rootFiles, authority )
                            .filter( xaipFile -> !isValidXaipRef( uri, xaipFile ) )
                            .ifPresent( file -> errors.add( "invalid pointer for uri " + uriRef ) );
                }
            }
            catch ( URISyntaxException e )
            {
                errors.add( "invalid uri scheme in " + uriRef );
            }
        }
        
        return errors;
    }
    
    Optional<File> findInRoot( List<File> rootFiles, String name )
    {
        return rootFiles.stream()
                .filter( f -> f.getName().equals( name ) )
                .findAny();
    }
    
    boolean isValidXaipRef( URI uri, File xaipFile )
    {
        AipUriResolver r = new AipUriResolver();
        try ( ByteArrayOutputStream os = r.resolve( uri, xaipFile ) )
        {
            boolean hasContent = os.size() > 0;
            os.reset();
            
            return hasContent;
        }
        catch ( Exception e )
        {
            return false;
        }
    }
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////
    
    /**
     * Validating the asic manifest against the provided xaip informations
     * 
     * @param metaInf
     *            the asic metaInf dir
     * @param manifest
     *            the asic manifest
     * @param aoid
     *            the aoid of the asic-xaip
     * @param oidsByVersion
     *            the oids of the asic-xaip by the versions
     * @return a set of errorMessages
     */
    Set<String> validateManifest( File asicDir, File metaInf, ASiCManifestType manifest, XAIPType xaip, String aoid,
            Map<String, Set<String>> oidsByVersion )
    {
        Set<String> errors = new HashSet<>();
        errors.addAll( validateContainerExtension( manifest, aoid, oidsByVersion ) );
        errors.addAll( validateSigRef( metaInf, manifest.getSigReference() ) );
        errors.addAll( validateDataRef( asicDir, xaip, manifest.getDataObjectReference() ) );
        
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
            XAIPType type = JAXB.unmarshal( in, XAIPType.class );
            return Optional.ofNullable( type ).map( XAIPType::getPackageHeader ).isPresent();
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
