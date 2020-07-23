package de.bund.bsi.tresor.xaip.validator.signature;

import static java.util.stream.Collectors.toList;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.xml.bind.JAXB;

import de.bund.bsi.tr_esor.api._1.S4_Service;
import de.bund.bsi.tr_esor.xaip._1.DataObjectType;
import de.bund.bsi.tr_esor.xaip._1.DataObjectType.BinaryData;
import de.bund.bsi.tresor.xaip.validator.api.boundary.SignatureVerifier;
import de.bund.bsi.tresor.xaip.validator.api.control.ModuleLogger;
import de.bund.bsi.tresor.xaip.validator.api.entity.XAIPValidatorException;
import lombok.Getter;
import oasis.names.tc.dss._1_0.core.schema.Base64Data;
import oasis.names.tc.dss._1_0.core.schema.DocumentType;
import oasis.names.tc.dss._1_0.core.schema.InputDocuments;
import oasis.names.tc.dss._1_0.core.schema.ResponseBaseType;
import oasis.names.tc.dss._1_0.core.schema.SignatureObject;
import oasis.names.tc.dss._1_0.core.schema.SignaturePtr;
import oasis.names.tc.dss._1_0.core.schema.VerifyRequest;
import oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_.IndividualReportType;

/**
 * @author wolffs
 */
@Getter
public class DefaultSignatureVerifier implements SignatureVerifier
{
    private final String          vendor  = "BSI";
    private final String          version = "1.0.0";
    
    private S4_Service            service;
    private DefaultVerifierConfig config  = new DefaultVerifierConfig();
    
    @Override
    public List<IndividualReportType> verify( List<SignatureObject> signatures )
    {
        List<ResponseBaseType> results = signatures.stream()
                .map( this::createRequest )
                .peek( this::debug )
                .map( this::verify )
                .collect( toList() );
        
        int missingSignatures = signatures.size() - results.size();
        if ( missingSignatures > 0 )
        {
            ModuleLogger.log( "lost " + missingSignatures + " signature documents on verification" );
        }
        
        return null;
    }
    
    private VerifyRequest createRequest( SignatureObject signatureObject )
    {
        VerifyRequest request = new VerifyRequest();
        InputDocuments inputDocuments = new InputDocuments();
        
        Optional.ofNullable( signatureObject.getSignaturePtr() )
                .map( SignaturePtr::getWhichDocument )
                .ifPresentOrElse( sigDocument -> {
                    DataObjectType dataObject = new DataObjectType();
                    if ( sigDocument instanceof byte[] )
                    {
                        dataObject = parseBinaryData( (byte[]) sigDocument )
                                .orElseThrow();
                    }
                    else if ( sigDocument instanceof DataObjectType )
                    {
                        dataObject = (DataObjectType) sigDocument;
                    }
                    
                    SignaturePtr ptr = new SignaturePtr();
                    ptr.setWhichDocument( dataObject );
                    
                    SignatureObject sigObj = new SignatureObject();
                    sigObj.setSignaturePtr( ptr );
                    
                    Base64Data b64Data = new Base64Data();
                    b64Data.setValue( dataObject.getBinaryData().getValue() );
                    
                    DocumentType document = new DocumentType();
                    document.setBase64Data( b64Data );
                    document.setID( dataObject.getDataObjectID() );
                    
                    inputDocuments.getDocumentOrTransformedDataOrDocumentHash().add( document );
                    
                    request.setSignatureObject( sigObj );
                }, () -> request.setSignatureObject( signatureObject ) );
        
        request.setInputDocuments( inputDocuments );
        
        return request;
        
    }
    
    Optional<DataObjectType> parseBinaryData( byte[] binary )
    {
        Optional<DataObjectType> result = Optional.empty();
        try
        {
            File tmpFile = File.createTempFile( "document-", ".tmp" );
            tmpFile.deleteOnExit();
            
            try ( FileOutputStream out = new FileOutputStream( tmpFile ) )
            {
                out.write( binary );
            }
            
            BinaryData binaryData = new BinaryData();
            binaryData.setValue( new DataHandler( new FileDataSource( tmpFile ) ) );
            
            DataObjectType dataObject = new DataObjectType();
            dataObject.setBinaryData( binaryData );
            dataObject.setDataObjectID( "data-" + UUID.randomUUID() );
            
            result = Optional.of( dataObject );
        }
        catch ( IOException e )
        {
            ModuleLogger.verbose( "could not read binary data", e );
        }
        
        return result;
    }
    
    ResponseBaseType verify( VerifyRequest request )
    {
        if ( service == null )
        {
            // String wsdlLocation = Optional.ofNullable( config.getTresorUrl()).orElseThrow();
            String wsdlLocation = "http://10.3.141.126:8080/archisafe/S4?wsdl";
            try
            {
                // service = new VerifyConnector_Service( new URL( wsdlLocation ), new MTOMFeature() );
                service = new S4_Service( new URL( wsdlLocation ) );
            }
            catch ( MalformedURLException e )
            {
                throw new XAIPValidatorException( "could not read verifyConnectorUrl " + wsdlLocation, e );
            }
        }
        
        return service.getS4().verify( request );
    }
    
    // TODO remove
    void debug( VerifyRequest req )
    {
        try ( OutputStream out = new FileOutputStream( "/tmp/req" + UUID.randomUUID() + ".xml" ) )
        {
            JAXB.marshal( req, out );
        }
        catch ( IOException e )
        {
            
        }
    }
}
