package converter;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStore.PasswordProtection;
import java.security.KeyStore.PrivateKeyEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.xml.bind.JAXB;

import de.bund.bsi.tr_esor.xaip.DataObjectsSectionType;
import de.bund.bsi.tr_esor.xaip.XAIPType;
import de.bund.bsi.tresor.aip.validator.api.control.AIPUtil;
import eu.europa.esig.dss.asic.cades.ASiCWithCAdESSignatureParameters;
import eu.europa.esig.dss.asic.cades.signature.ASiCWithCAdESService;
import eu.europa.esig.dss.enumerations.ASiCContainerType;
import eu.europa.esig.dss.enumerations.DigestAlgorithm;
import eu.europa.esig.dss.enumerations.SignatureLevel;
import eu.europa.esig.dss.model.DSSDocument;
import eu.europa.esig.dss.model.InMemoryDocument;
import eu.europa.esig.dss.model.SignatureValue;
import eu.europa.esig.dss.model.ToBeSigned;
import eu.europa.esig.dss.token.KSPrivateKeyEntry;
import eu.europa.esig.dss.token.Pkcs12SignatureToken;
import eu.europa.esig.dss.validation.CommonCertificateVerifier;
import lombok.AllArgsConstructor;

/**
 * @author wolffs
 */
@AllArgsConstructor
public class Converter
{
    
    private final String ksPath;
    private final String password;
    private final String privKeyEntry;
    
    public static void main( String[] args ) throws Exception
    {
        XAIPType xaip = JAXB.unmarshal( new File( "/tmp/tst/m0" ), XAIPType.class );
        
        String pw = "kennwort";
        String entry = "wolffs";
        String ksPath = "/home/wolffs/wolffs-test";
        
        System.out.println( "> starting converstion" );
        
        Converter converter = new Converter( ksPath, pw, entry );
        converter.convert( xaip, new File( "/tmp/result.asic" ) );
        
        System.out.println( "> finished conversion" );
    }
    
    public void convert( XAIPType xaip, File output ) throws Exception
    {
        List<InputStream> dataStreams = findData( xaip );
        
        DSSDocument asic = createASIC( dataStreams );
        asic.writeTo( new FileOutputStream( output ) );
    }
    
    List<InputStream> findData( XAIPType xaip )
    {
        List<InputStream> in = new ArrayList<>();
        
        // currently only dataObjects
        Optional.ofNullable( xaip )
                .map( XAIPType::getDataObjectsSection )
                .map( DataObjectsSectionType::getDataObject )
                .orElse( new ArrayList<>() ).stream()
                .forEach( dataObject -> {
                    AIPUtil.extractData( AIPUtil.binaryDataSupplier( dataObject ), dataObject::getXmlData )
                            .map( ByteArrayInputStream::new )
                            .ifPresent( in::add );
                } );
        
        System.out.println( in.size() );
        
        return in;
    }
    
    DSSDocument createASIC( List<InputStream> dataStreams ) throws Exception
    {
        try ( InputStream in = new FileInputStream( ksPath ) )
        {
            char[] pw = password.toCharArray();
            byte[] ksBytes = in.readAllBytes();
            
            KeyStore ks = KeyStore.getInstance( "PKCS12" );
            ks.load( new ByteArrayInputStream( ksBytes ), pw );
            PrivateKeyEntry privateKeyEntry = (PrivateKeyEntry) ks.getEntry( privKeyEntry, new PasswordProtection( pw ) );
            
            KSPrivateKeyEntry privateKey = new KSPrivateKeyEntry( privKeyEntry, privateKeyEntry );
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
            dataStreams.stream().map( InMemoryDocument::new ).forEach( documentsToBeSigned::add );
            ToBeSigned dataToSign = service.getDataToSign( documentsToBeSigned, parameters );
            
            DigestAlgorithm digestAlgorithm = parameters.getDigestAlgorithm();
            SignatureValue signatureValue = signingToken.sign( dataToSign, digestAlgorithm, privateKey );
            
            return service.signDocument( documentsToBeSigned, parameters, signatureValue );
        }
    }
}
