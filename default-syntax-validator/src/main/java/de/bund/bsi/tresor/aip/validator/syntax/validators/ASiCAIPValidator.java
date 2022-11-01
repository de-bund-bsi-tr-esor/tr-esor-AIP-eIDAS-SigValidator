package de.bund.bsi.tresor.aip.validator.syntax.validators;

import static java.util.Collections.emptyList;

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
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.dom.DOMSource;

import org.apache.commons.io.FileUtils;
import org.etsi.uri._02918.v1_2.ASiCManifestType;
import org.etsi.uri._02918.v1_2.DataObjectReferenceType;
import org.etsi.uri._02918.v1_2.ExtensionType;
import org.etsi.uri._02918.v1_2.ExtensionsListType;
import org.etsi.uri._02918.v1_2.SigReferenceType;
import org.etsi.uri._19512.v1_1.ContainerIDType;
import org.w3c.dom.Element;

import de.bund.bsi.tr_esor.xaip.XAIPType;
import de.bund.bsi.tresor.aip.validator.api.control.AIPUtil;
import de.bund.bsi.tresor.aip.validator.api.control.ModuleLogger;
import de.bund.bsi.tresor.aip.validator.syntax.context.DefaultSyntaxValidatorContext;
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
                EvidenceRecordFinder.findEvidenceRecord( syntaxContext, zippedData );
                // do not change the order of the line above and below
                zippedData = FileUtils.readFileToByteArray( xaipFile );
                unzipped.ifPresent( asicDir -> {
                    validateASiCAIPStructure( asicDir, xaipFile, syntaxContext );
                } );
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
     * @param syntaxContext
     *            context of the syntaxValidator
     */
    public void validateASiCAIPStructure( File asicDir, File xaipFile, DefaultSyntaxValidatorContext syntaxContext )
    {
        XAIPType xaip = JAXB.unmarshal( xaipFile, XAIPType.class );
        File metaInf = Arrays.stream( asicDir.listFiles() )
                .filter( file -> file.isDirectory() )
                .filter( file -> file.getName().equals( "META-INF" ) )
                .findAny()
                .orElseThrow( () -> new IllegalArgumentException( "missing asic META-INF directory" ) );
        
        validateMetaInf( xaip, metaInf );
    }
    
    /**
     * Validating the metaInf
     * 
     * @param xaip
     *            the xaip from the asic container
     * @param metaInf
     *            the asic metaInf folder
     */
    void validateMetaInf( XAIPType xaip, File metaInf )
    {
        String aoid = AIPUtil.findAoid( xaip ).orElseThrow( () -> new IllegalArgumentException( "missing aoid in xaip" ) );
        Map<String, Set<String>> oidsByVersion = AIPUtil.oidsByVersion( xaip );
        
        try
        {
            JAXBContext context = JAXBContext.newInstance( ASiCManifestType.class );
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Map<String, Set<String>> errorsByManifest = new HashMap<>();
            for ( File file : metaInf.listFiles() )
            {
                try
                {
                    ASiCManifestType manifest = (ASiCManifestType) unmarshaller.unmarshal( file );
                    Set<String> errors = validateManifest( metaInf, manifest, aoid, oidsByVersion );
                    
                    if ( !errors.isEmpty() )
                    {
                        errorsByManifest.put( file.getName(), errors );
                    }
                }
                catch ( ClassCastException | JAXBException | DataBindingException e )
                {
                    ModuleLogger.log( "manifest search: skipping " + file.getName() );
                    ModuleLogger.verbose( "file is not an asicManifest: " + file.getName(), e );
                }
            }
            
            if ( !oidsByVersion.isEmpty() )
            {
                ModuleLogger.log( "[WARN] missing optional aisc-manifest containerID for versions: " + oidsByVersion.keySet().toString() );
            }
            
            if ( !errorsByManifest.isEmpty() )
            {
                throw new IllegalArgumentException( "invalid asic-aip structure: " + errorsByManifest.toString() );
            }
        }
        catch ( JAXBException e )
        {
            throw new IllegalStateException( "could not create unmarshaller for asicManifest", e );
        }
    }
    
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
    Set<String> validateManifest( File metaInf, ASiCManifestType manifest, String aoid, Map<String, Set<String>> oidsByVersion )
    {
        Set<String> errors = new HashSet<>();
        SigReferenceType sigReference = manifest.getSigReference();
        String uri = sigReference.getURI();
        String substring = uri.substring( uri.lastIndexOf( File.separatorChar ) + 1 );
        
        List<ExtensionType> extensions = Optional.ofNullable( manifest.getASiCManifestExtensions() )
                .map( ExtensionsListType::getExtension )
                .orElse( emptyList() );
        
        Optional<ContainerIDType> optContainer = extensions.stream()
                .map( f -> f.getContent() )
                .flatMap( List::stream )
                .filter( Element.class::isInstance )
                .map( Element.class::cast )
                // .map( JAXBElement.class::cast )
                // .map( JAXBElement::getValue )
                .map( e -> JAXB.unmarshal( new DOMSource( e ), ContainerIDType.class ) )
                .filter( ContainerIDType.class::isInstance )
                .map( ContainerIDType.class::cast )
                .findAny();
        
        if ( !Arrays.stream( metaInf.list() )
                .anyMatch( name -> name.equals( substring ) ) )
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
