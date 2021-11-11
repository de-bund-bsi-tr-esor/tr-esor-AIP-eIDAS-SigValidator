package converter;

import static java.util.Arrays.asList;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStore.PasswordProtection;
import java.security.KeyStore.PrivateKeyEntry;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;

import converter.extension.ASiCExtension;
import converter.extension.ContainerIdExtension;
import converter.extension.ExtensionPatcher;
import eu.europa.esig.dss.DomUtils;
import eu.europa.esig.dss.asic.cades.ASiCWithCAdESSignatureParameters;
import eu.europa.esig.dss.asic.cades.signature.ASiCWithCAdESService;
import eu.europa.esig.dss.asic.common.SecureContainerHandler;
import eu.europa.esig.dss.asic.common.ZipContainerHandler;
import eu.europa.esig.dss.enumerations.ASiCContainerType;
import eu.europa.esig.dss.enumerations.DigestAlgorithm;
import eu.europa.esig.dss.enumerations.SignatureLevel;
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
public class ASICAIPFactory
{
    public static void main( String[] args ) throws Exception
    {
        // Input Arguments
        String aoid = "asicSample";
        String version = "1.0.0";
        String lxaipFile = "/home/wolffs/Downloads/asic/sample.lxaip";
        
        List<String> sigFiles = asList( "/home/wolffs/Downloads/asic/sample.txt" );
        
        // Output Arguments
        String outputFile = "/tmp/aisc-lxaip.asice";
        
        // ---------------------------------------------------------------------------
        
        System.out.println( "starting\n" );
        // ASiCFinder.validateASIC( "/tmp/aisc-lxaip.asice" );
        // ASiCFinder.validateASIC( "/home/wolffs/Downloads/asic" );
        // ASiCFinder.validateASIC( "/home/wolffs/Dokumente/asic" );
        
        DSSDocument asic = creatingASIC( sigFiles );
        if ( StringUtils.isNoneBlank( lxaipFile, aoid, version ) )
        {
            asic = lxaipAsic( asic, lxaipFile, aoid, version );
        }
        
        asic.writeTo( new FileOutputStream( outputFile ) );
        System.out.println( "output written to " + outputFile );
        
        System.out.println( "\nfinished" );
    }
    
    public static DSSDocument lxaipAsic( DSSDocument document, String lxaipContainer, String aoid, String version )
            throws FileNotFoundException, IOException
    {
        ZipContainerHandler zipper = new SecureContainerHandler();
        List<DSSDocument> contents = zipper.extractContainerContent( document );
        contents.add( new FileDocument( lxaipContainer ) );
        Optional<DSSDocument> manifest = contents.stream()
                .filter( doc -> doc.getName().equals( "META-INF/ASiCManifest.xml" ) )
                .findAny();
        
        if ( manifest.isPresent() )
        {
            DSSDocument dssManifest = manifest.get();
            contents.remove( dssManifest );
            
            Document dom = DomUtils.buildDOM( dssManifest );
            
            List<ASiCExtension> extensions = asList( new ContainerIdExtension( aoid, version ) );
            
            ExtensionPatcher patcher = new ExtensionPatcher( dom, dom.getDocumentElement() );
            patcher.patch( extensions );
            
            dssManifest = DomUtils.createDssDocumentFromDomDocument( dom, dssManifest.getName() );
            contents.add( dssManifest );
        }
        
        return zipper.createZipArchive( contents, Date.from( Instant.now() ), null );
    }
    
    public static DSSDocument creatingASIC( List<String> files ) throws Exception
    {
        try ( InputStream in = new FileInputStream( "/home/wolffs/wolffs-test" ) )
        {
            char[] pw = "kennwort".toCharArray();
            byte[] ksBytes = in.readAllBytes();
            
            KeyStore ks = KeyStore.getInstance( "PKCS12" );
            ks.load( new ByteArrayInputStream( ksBytes ), pw );
            PrivateKeyEntry privateKeyEntry = (PrivateKeyEntry) ks.getEntry( "wolffs", new PasswordProtection( pw ) );
            
            KSPrivateKeyEntry privateKey = new KSPrivateKeyEntry( "wolffs", privateKeyEntry );
            Pkcs12SignatureToken signingToken = new Pkcs12SignatureToken( ksBytes, new PasswordProtection( pw ) );
            
            CommonCertificateVerifier commonCertificateVerifier = new CommonCertificateVerifier();
            
            ASiCWithCAdESService service = new ASiCWithCAdESService( commonCertificateVerifier );
            ASiCWithCAdESSignatureParameters parameters = new ASiCWithCAdESSignatureParameters();
            parameters.setSignatureLevel( SignatureLevel.CAdES_BASELINE_B );
            
            // ASiCWithXAdESService service = new ASiCWithXAdESService( commonCertificateVerifier );
            // ASiCWithXAdESSignatureParameters parameters = new ASiCWithXAdESSignatureParameters();
            // parameters.setSignatureLevel( SignatureLevel.XAdES_BASELINE_B );
            
            parameters.aSiC().setContainerType( ASiCContainerType.ASiC_E );
            parameters.setDigestAlgorithm( DigestAlgorithm.SHA256 );
            parameters.setSigningCertificate( privateKey.getCertificate() );
            parameters.setCertificateChain( privateKey.getCertificateChain() );
            
            List<DSSDocument> documentsToBeSigned = new ArrayList<>();
            files.stream().forEach( path -> documentsToBeSigned.add( new FileDocument( path ) ) );
            ToBeSigned dataToSign = service.getDataToSign( documentsToBeSigned, parameters );
            
            DigestAlgorithm digestAlgorithm = parameters.getDigestAlgorithm();
            SignatureValue signatureValue = signingToken.sign( dataToSign, digestAlgorithm, privateKey );
            
            return service.signDocument( documentsToBeSigned, parameters, signatureValue );
        }
    }
}
