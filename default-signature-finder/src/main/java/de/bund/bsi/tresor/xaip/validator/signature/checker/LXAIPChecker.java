package de.bund.bsi.tresor.xaip.validator.signature.checker;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.etsi.uri._02918.v1_2.DataObjectReferenceType;
import org.w3._2000._09.xmldsig_.DigestMethodType;

import de.bund.bsi.tresor.xaip.validator.api.entity.DefaultResult;
import de.bund.bsi.tresor.xaip.validator.api.entity.DefaultResult.Minor;
import de.bund.bsi.tresor.xaip.validator.api.entity.DefaultResult.ResultLanguage;
import de.bund.bsi.tresor.xaip.validator.signature.entity.DataReference;
import de.bund.bsi.tresor.xaip.validator.signature.entity.DigestAlgorithm;
import de.bund.bsi.tresor.xaip.validator.signature.entity.LXAIPCheckerException;
import oasis.names.tc.dss._1_0.core.schema.Result;
import oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_.IndividualReportType;

/**
 * Searchs for DataObjectReference element and verifies the digest of the referenced file.<br>
 * Only local files supported.
 * 
 * @author bendlera
 *
 */
public enum LXAIPChecker
{
    INSTANCE;
    
    /**
     * Searches and verifies digest.
     * 
     * @param id
     *            ID
     * @param dataObjectReference
     *            the dataObjectReference
     * @return the verification result
     */
    public DataReference verify( String id, DataObjectReferenceType dataObjectReference )
    {
        try
        {
            URI uri = parseUri( dataObjectReference );
            DigestAlgorithm algorithm = parseAlgorithm( dataObjectReference );
            Path path = convertUri( uri );
            
            try ( InputStream inputStream = Files.newInputStream( path ) )
            {
                MessageDigest messageDigest = MessageDigest.getInstance( algorithm.getJavaName(), new BouncyCastleProvider() );
                
                int readCount = 0;
                byte[] buffer = new byte[8192];
                while ( (readCount = inputStream.read( buffer )) != -1 )
                {
                    messageDigest.update( buffer, 0, readCount );
                }
                
                byte[] digest = messageDigest.digest();
                if ( Arrays.equals( digest, dataObjectReference.getDigestValue() ) )
                {
                    IndividualReportType individualReport = new IndividualReportType();
                    individualReport.setResult( DefaultResult.ok().build() );
                    
                    return new DataReference( Optional.ofNullable( dataObjectReference ), individualReport );
                }
                else
                {
                    return createDataReference( id + ": Digest differ." );
                }
            }
        }
        catch ( NoSuchAlgorithmException e )
        {
            return createDataReference( id + ": Algorithm is not supported by JVM: " + e.getMessage() );
        }
        catch ( IOException e )
        {
            return createDataReference( id + ": File could not readed: " + e.getMessage() );
        }
        catch ( LXAIPCheckerException e )
        {
            return createDataReference( id + ": " + e.getMessage() );
        }
    }
    
    /**
     * Parse URI to referenced file and checks that scheme is file.
     * 
     * @param dataObjectReference
     *            the dataObjectReference
     * @return parsed URI
     * @throws LXAIPCheckerException
     *             if URI could not parsed or the scheme ist unsupported
     */
    URI parseUri( DataObjectReferenceType dataObjectReference ) throws LXAIPCheckerException
    {
        if ( StringUtils.isNotBlank( dataObjectReference.getURI() ) )
        {
            try
            {
                URI uri = URI.create( dataObjectReference.getURI() );
                if ( uri.getScheme().startsWith( "file" ) )
                {
                    return uri;
                }
                else
                {
                    throw new LXAIPCheckerException( "URI scheme " + uri.getScheme() + " not supported" );
                }
            }
            catch ( IllegalArgumentException e )
            {
                throw new LXAIPCheckerException( "URI of DataObjectReference could not parsed: " + e.getMessage() );
            }
        }
        else
        {
            throw new LXAIPCheckerException( "Attribute uri has no value." );
        }
    }
    
    /**
     * Parse algorithm URI to {@link DigestAlgorithm}.
     * 
     * @param dataObjectReference
     *            the dataObjectReference
     * @return parsed algorithm
     * @throws LXAIPCheckerException
     *             if algorithm is not supported
     */
    DigestAlgorithm parseAlgorithm( DataObjectReferenceType dataObjectReference ) throws LXAIPCheckerException
    {
        String algorithmUri = Optional.ofNullable( dataObjectReference )
                .map( DataObjectReferenceType::getDigestMethod )
                .map( DigestMethodType::getAlgorithm )
                .orElseThrow( () -> new LXAIPCheckerException( "No element DigestMethod found." ) );
        
        return DigestAlgorithm.fromXmlSyntax( algorithmUri )
                .orElseThrow( () -> new LXAIPCheckerException( "Algorithm '" + algorithmUri + "' is not supported." ) );
    }
    
    /**
     * Converts {@link URI} to {@link Path}.
     * 
     * @param uri
     *            URI
     * @return path
     * @throws LXAIPCheckerException
     *             if conversion failed or file dose not exists
     */
    Path convertUri( URI uri ) throws LXAIPCheckerException
    {
        try
        {
            Path path = Paths.get( uri );
            if ( Files.exists( path ) )
            {
                return path;
            }
            
            throw new LXAIPCheckerException( "File " + path.toAbsolutePath().toString() + " does not exists." );
        }
        catch ( IllegalArgumentException e )
        {
            throw new LXAIPCheckerException( "URI could not converted to path." );
        }
    }
    
    /**
     * Creates {@link DataReference}.
     * 
     * @param messageText
     *            message text
     * @return instance
     */
    DataReference createDataReference( String messageText )
    {
        Result result = DefaultResult.error()
                .minor( Minor.NO_DATA_ACCESS_WARNING )
                .message( messageText, ResultLanguage.ENGLISH )
                .build();
        
        IndividualReportType individualReport = new IndividualReportType();
        individualReport.setResult( result );
        
        return new DataReference( Optional.empty(), individualReport );
    }
    
}
