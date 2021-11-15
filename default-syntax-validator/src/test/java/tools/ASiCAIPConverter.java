package tools;

import static java.util.stream.Collectors.toSet;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyStore;
import java.security.KeyStore.PasswordProtection;
import java.security.KeyStore.PrivateKeyEntry;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.commons.io.FileUtils;
import org.etsi.uri._02918.v1_2.ASiCManifestType;
import org.etsi.uri._02918.v1_2.DataObjectReferenceType;
import org.etsi.uri._02918.v1_2.ExtensionType;
import org.etsi.uri._02918.v1_2.ExtensionsListType;
import org.etsi.uri._02918.v1_2.ObjectFactory;
import org.etsi.uri._02918.v1_2.SigReferenceType;
import org.etsi.uri._19512.v1_1.ContainerIDType;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import de.bund.bsi.tr_esor.xaip.DataObjectType;
import de.bund.bsi.tr_esor.xaip.PackageHeaderType;
import de.bund.bsi.tr_esor.xaip.XAIPType;
import eu.europa.esig.dss.cades.CAdESSignatureParameters;
import eu.europa.esig.dss.cades.signature.CAdESService;
import eu.europa.esig.dss.enumerations.DigestAlgorithm;
import eu.europa.esig.dss.enumerations.SignatureLevel;
import eu.europa.esig.dss.enumerations.SignaturePackaging;
import eu.europa.esig.dss.model.DSSDocument;
import eu.europa.esig.dss.model.FileDocument;
import eu.europa.esig.dss.model.SignatureValue;
import eu.europa.esig.dss.model.ToBeSigned;
import eu.europa.esig.dss.token.KSPrivateKeyEntry;
import eu.europa.esig.dss.token.Pkcs12SignatureToken;
import eu.europa.esig.dss.validation.CommonCertificateVerifier;

/**
 * @author wolffs
 */
@Disabled
public class ASiCAIPConverter
{
    private final byte[] ksBytes;
    private final char[] password;
    private final String privKeyEntry;
    
    public ASiCAIPConverter( String ksPath, String password, String pkEntry ) throws IOException
    {
        this.privKeyEntry = pkEntry;
        this.ksBytes = Files.readAllBytes( new File( ksPath ).toPath() );
        this.password = Optional.ofNullable( password ).orElse( "" ).toCharArray();
    }
    
    @Test
    public void execute() throws Exception
    {
        // Configuration
        String pw = "kennwort";
        String entry = "wolffs";
        String ksPath = "/home/wolffs/wolffs-test";
        File outputFile = new File( "/tmp/resultAsic" );
        // File inputXaip = new File( "/home/wolffs/Dokumente/13.xaip" );
        File inputXaip = new File( "/tmp/sample.xaip" );
        //////////////////////////////////////////////////////////////////////
        
        System.out.println( "> starting converstion" );
        
        XAIPType xaip = JAXB.unmarshal( inputXaip, XAIPType.class );
        
        ASiCAIPConverter converter = new ASiCAIPConverter( ksPath, pw, entry );
        converter.convert( xaip, outputFile );
        
        System.out.println( "> finished conversion" );
    }
    
    /**
     * Converting a normal xaip into asic formatted aip
     * 
     * @param xaip
     *            the normal xaip
     * @param output
     *            the output file
     * @throws Exception
     */
    public void convert( XAIPType xaip, File output ) throws Exception
    {
        FileUtils.deleteQuietly( output );
        output.mkdir();
        
        String aoid = findAOID( xaip );
        createContainerFile( output, xaip, aoid );
        createMetaInf( output, xaip, aoid );
        createMimeFile( output, "application/vnd.etsi.asic-e+zip" );
        
        File file = new File( output.getParent(), aoid + ".asic" );
        zip( output.toPath(), file.toPath() );
        FileUtils.deleteQuietly( output );
    }
    
    void zip( Path source, Path target ) throws IOException
    {
        try ( ZipOutputStream zip = new ZipOutputStream( Files.newOutputStream( target ) ) )
        {
            Files.walk( source )
                    .filter( path -> !Files.isDirectory( path ) )
                    .forEach( path -> {
                        ZipEntry zipEntry = new ZipEntry( source.relativize( path ).toString() );
                        try
                        {
                            zip.putNextEntry( zipEntry );
                            Files.copy( path, zip );
                            zip.closeEntry();
                        }
                        catch ( IOException e )
                        {
                            throw new IllegalStateException( "could not create zip", e );
                        }
                    } );
        }
    }
    
    /**
     * Creating the xaip file
     * 
     * @param asic
     *            the asic parent dir
     * @param xaip
     *            the xaip
     * @param aoid
     *            the xaip aoid
     */
    void createContainerFile( File asic, XAIPType xaip, String aoid )
    {
        File xaipFile = new File( asic, aoid + ".xaip" );
        JAXB.marshal( new de.bund.bsi.tr_esor.xaip.ObjectFactory().createXAIP( xaip ), xaipFile );
    }
    
    /**
     * Creating the mimetype file
     * 
     * @param asic
     *            the asic parent dir
     * @param mimeType
     *            the mimetype
     * @throws IOException
     */
    void createMimeFile( File asic, String mimeType ) throws IOException
    {
        File mimetypeFile = new File( asic, "mimetype" );
        try ( FileOutputStream out = new FileOutputStream( mimetypeFile ) )
        {
            out.write( mimeType.getBytes( StandardCharsets.UTF_8 ) );
        }
    }
    
    /**
     * Creating the META-INF directory and it's content
     * 
     * @param asic
     *            the asic parent dir
     * @param xaip
     *            the xaip
     * @param aoid
     *            the xaip aoid
     * @throws Exception
     */
    void createMetaInf( File asic, XAIPType xaip, String aoid ) throws Exception
    {
        File metaInf = new File( asic, "META-INF" );
        metaInf.mkdir();
        
        Map<String, Set<String>> oidsByVersion = oidsByVersion( xaip );
        
        for ( Entry<String, Set<String>> entry : oidsByVersion.entrySet() )
        {
            String version = entry.getKey();
            Set<String> oids = entry.getValue();
            
            File sigFile = new File( metaInf, version + ".p7s" );
            File manifest = createASiCManifest( metaInf, sigFile, aoid, version, oids );
            
            createSig( manifest, sigFile );
        }
    }
    
    /**
     * Mapping the oids by their version
     * 
     * @param xaip
     *            the xaip
     * @return the map
     */
    Map<String, Set<String>> oidsByVersion( XAIPType xaip )
    {
        Map<String, Set<String>> oidsByVersion = new HashMap<>();
        
        Optional.ofNullable( xaip )
                .map( XAIPType::getPackageHeader )
                .map( PackageHeaderType::getVersionManifest )
                .orElseThrow( () -> new IllegalStateException( "missing versions in xaip" ) )
                .stream()
                .forEach( manifest -> {
                    String version = manifest.getVersionID();
                    Set<String> oids = manifest.getPackageInfoUnit().stream()
                            .flatMap( info -> {
                                return Stream.concat(
                                        info.getProtectedObjectPointer().stream(),
                                        info.getUnprotectedObjectPointer().stream() )
                                        .map( JAXBElement::getValue )
                                        .filter( DataObjectType.class::isInstance )
                                        .map( DataObjectType.class::cast )
                                        .map( DataObjectType::getDataObjectID );
                                
                            } )
                            .collect( toSet() );
                            
                    oidsByVersion.put( version, oids );
                } );
        
        return oidsByVersion;
    }
    
    /**
     * Creating the asic manifest
     * 
     * @param metaInf
     *            the parent metaInf directory
     * @param sigFile
     *            the sigFile
     * @param aoid
     *            the xaip aoid
     * @param version
     *            the xaip version
     * @param oids
     *            the objectIds for the provided version
     * @return the created manifest file
     * @throws JAXBException
     */
    File createASiCManifest( File metaInf, File sigFile, String aoid, String version, Set<String> oids ) throws JAXBException
    {
        File manifestFile = new File( metaInf, "ASiCManifest-" + version + ".xml" );
        
        SigReferenceType sigRef = new SigReferenceType();
        sigRef.setURI( "file://META-INF/" + sigFile.getName() );
        sigRef.setMimeType( "application/pkcs7-signature" );
        
        ContainerIDType containerId = new ContainerIDType();
        containerId.setPOID( aoid );
        containerId.setVersionID( version );
        ExtensionType extension = new ExtensionType();
        extension.setCritical( false );
        extension.getContent().add( new org.etsi.uri._19512.v1_1.ObjectFactory().createContainerID( containerId ) );
        
        ExtensionsListType extensionList = new ExtensionsListType();
        extensionList.getExtension().add( extension );
        
        ASiCManifestType manifest = new ASiCManifestType();
        manifest.setSigReference( sigRef );
        manifest.setASiCManifestExtensions( extensionList );
        
        for ( String oid : oids )
        {
            DataObjectReferenceType dataRef = new DataObjectReferenceType();
            dataRef.setMimeType( "application/octet-stream" );
            dataRef.setURI( "xaip://data/" + oid ); // TODO something like this?
            
            manifest.getDataObjectReference().add( dataRef );
        }
        
        ObjectFactory factory = new ObjectFactory();
        JAXBContext context = JAXBContext.newInstance( ASiCManifestType.class, ContainerIDType.class );
        Marshaller marshaller = context.createMarshaller();
        marshaller.marshal( factory.createASiCManifest( manifest ), manifestFile );
        
        return manifestFile;
    }
    
    /**
     * Creating the signature file
     * 
     * @param manifest
     *            the parent manifest dir
     * @param sigFile
     *            the output file
     * @throws Exception
     */
    void createSig( File manifest, File sigFile ) throws Exception
    {
        DSSDocument toSignDocument = new FileDocument( manifest );
        KeyStore ks = KeyStore.getInstance( "PKCS12" );
        ks.load( new ByteArrayInputStream( ksBytes ), password );
        PrivateKeyEntry privateKeyEntry = (PrivateKeyEntry) ks.getEntry( privKeyEntry, new PasswordProtection( password ) );
        
        KSPrivateKeyEntry privateKey = new KSPrivateKeyEntry( privKeyEntry, privateKeyEntry );
        Pkcs12SignatureToken signingToken = new Pkcs12SignatureToken( ksBytes, new PasswordProtection( password ) );
        
        CAdESSignatureParameters parameters = new CAdESSignatureParameters();
        parameters.setSignatureLevel( SignatureLevel.CAdES_BASELINE_B );
        parameters.setSignaturePackaging( SignaturePackaging.ENVELOPING );
        parameters.setDigestAlgorithm( DigestAlgorithm.SHA256 );
        
        parameters.setSigningCertificate( privateKey.getCertificate() );
        parameters.setCertificateChain( privateKey.getCertificateChain() );
        
        CommonCertificateVerifier commonCertificateVerifier = new CommonCertificateVerifier();
        CAdESService service = new CAdESService( commonCertificateVerifier );
        
        ToBeSigned dataToSign = service.getDataToSign( toSignDocument, parameters );
        
        DigestAlgorithm digestAlgorithm = parameters.getDigestAlgorithm();
        SignatureValue signatureValue = signingToken.sign( dataToSign, digestAlgorithm, privateKey );
        
        DSSDocument signedDocument = service.signDocument( toSignDocument, parameters, signatureValue );
        signedDocument.writeTo( new FileOutputStream( sigFile ) );
    }
    
    /**
     * Searching and extracting the aoid
     * 
     * @param xaip
     *            the xaip
     * @return the aoid
     */
    String findAOID( XAIPType xaip )
    {
        return Optional.ofNullable( xaip )
                .map( XAIPType::getPackageHeader )
                .map( PackageHeaderType::getAOID )
                .orElseThrow( () -> new IllegalStateException( "missing aoid which is not specified for this format" ) );
    }
}
