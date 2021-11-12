package converter;

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
import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
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

import de.bund.bsi.tr_esor.xaip.DataObjectType;
import de.bund.bsi.tr_esor.xaip.DataObjectsSectionType;
import de.bund.bsi.tr_esor.xaip.PackageHeaderType;
import de.bund.bsi.tr_esor.xaip.VersionManifestType;
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
    
    public static void main( String[] args ) throws Exception
    {
        String pw = "kennwort";
        String entry = "wolffs";
        String ksPath = "/home/wolffs/wolffs-test";
        File outputFile = new File( "/tmp/resultAsic" );
        File inputXaip = new File( "/home/wolffs/Dokumente/13.xaip" );
        
        System.out.println( "> starting converstion" );
        
        XAIPType xaip = JAXB.unmarshal( inputXaip, XAIPType.class );
        
        ASiCAIPConverter converter = new ASiCAIPConverter( ksPath, pw, entry );
        converter.convert( xaip, "v0", outputFile );
        
        System.out.println( "> finished conversion" );
    }
    
    public void convert( XAIPType xaip, String version, File output ) throws Exception
    {
        FileUtils.deleteQuietly( output );
        output.mkdir();
        
        String aoid = findAOID( xaip );
        createContainerFile( output, xaip, aoid );
        createMetaInf( output, xaip, aoid, version );
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
    
    // DONE
    void createContainerFile( File asic, XAIPType xaip, String aoid )
    {
        File xaipFile = new File( asic, aoid + ".xaip" );
        JAXB.marshal( new de.bund.bsi.tr_esor.xaip.ObjectFactory().createXAIP( xaip ), xaipFile );
    }
    
    // DONE
    void createMimeFile( File asic, String mimeType ) throws IOException
    {
        File mimetypeFile = new File( asic, "mimetype" );
        try ( FileOutputStream out = new FileOutputStream( mimetypeFile ) )
        {
            out.write( mimeType.getBytes( StandardCharsets.UTF_8 ) );
        }
    }
    
    // DONE
    void createMetaInf( File asic, XAIPType xaip, String aoid, String version ) throws Exception
    {
        checkVersion( xaip, version );
        
        File metaInf = new File( asic, "META-INF" );
        metaInf.mkdir();
        
        Set<String> oids = Optional.ofNullable( xaip )
                .map( XAIPType::getDataObjectsSection )
                .map( DataObjectsSectionType::getDataObject )
                .orElse( new ArrayList<>() ).stream()
                .map( DataObjectType::getDataObjectID )
                .collect( toSet() );
        
        File sigFile = new File( metaInf, aoid + ".p7s" );
        File manifest = createASiCManifest( metaInf, sigFile, aoid, version, oids );
        
        createSig( manifest, sigFile );
    }
    
    File createASiCManifest( File metaInf, File sigFile, String aoid, String version, Set<String> oids ) throws JAXBException
    {
        File manifestFile = new File( metaInf, "ASiCManifest-" + aoid + ".xml" );
        
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
    
    // DONE
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
    
    // DONE
    String findAOID( XAIPType xaip )
    {
        return Optional.ofNullable( xaip )
                .map( XAIPType::getPackageHeader )
                .map( PackageHeaderType::getAOID )
                .orElseThrow( () -> new IllegalStateException( "missing aoid which is not specified for this format" ) );
    }
    
    // DONE
    void checkVersion( XAIPType xaip, String version )
    {
        Optional.ofNullable( xaip )
                .map( XAIPType::getPackageHeader )
                .map( PackageHeaderType::getVersionManifest )
                .orElse( new ArrayList<>() ).stream()
                .map( VersionManifestType::getVersionID )
                .filter( version::equals )
                .findAny()
                .orElseThrow( () -> new IllegalArgumentException( "version " + version + " does not exist" ) );
    }
}
