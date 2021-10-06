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
import java.util.List;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.xml.bind.JAXB;

import org.apache.commons.io.FileUtils;

import de.bund.bsi.tr_esor.xaip.XAIPType;
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
                zippedData = FileUtils.readFileToByteArray( validAIPFiles.get( 0 ) );
            }
        }
        finally
        {
            unzipped.ifPresent( FileUtils::deleteQuietly );
        }
        
        return zippedData;
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
