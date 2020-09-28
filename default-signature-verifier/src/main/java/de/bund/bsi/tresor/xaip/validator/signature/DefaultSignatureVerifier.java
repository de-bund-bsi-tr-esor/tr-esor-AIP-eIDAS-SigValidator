package de.bund.bsi.tresor.xaip.validator.signature;

import static java.util.stream.Collectors.toList;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.w3c.dom.Node;

import de.bund.bsi.ecard.api._1.VerifyResponse;
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
 * Implementation of the SignatureVerifier module from the XAIPValidator.
 * 
 * @author wolffs
 */
@Getter
public class DefaultSignatureVerifier implements SignatureVerifier
{
    private final String          vendor  = "BSI";
    private final String          version = "1.0.0";
    
    private S4_Service            service;
    private DefaultVerifierConfig config;
    
    /**
     * Configuring this module by using the provided property map
     * 
     * @param config
     *            the property map
     */
    public void configure( Map<String, String> config )
    {
        this.config = DefaultVerifierConfig.fromArguments( config );
    }
    
    @Override
    public List<IndividualReportType> verify( List<SignatureObject> signatures )
    {
        List<ResponseBaseType> results = signatures.stream()
                .map( this::createRequest )
                .map( this::requestVerification )
                .collect( toList() );
        
        int missingSignatures = signatures.size() - results.size();
        if ( missingSignatures > 0 )
        {
            ModuleLogger.log( "lost " + missingSignatures + " signature documents on verification" );
        }
        
        return results.stream()
                .map( this::convertResponse )
                .flatMap( List::stream )
                .collect( toList() );
    }
    
    /**
     * Parsing the {@link VerifyResponse} from the {@link #requestVerification(VerifyRequest)} and extracting the
     * {@link IndividualReportType}
     * 
     * @param response
     *            the base response type of the {@link VerifyResponse}
     * @return list of {@link IndividualReportType} retrieved from the response
     */
    List<IndividualReportType> convertResponse( ResponseBaseType response )
    {
        List<IndividualReportType> resultList = new ArrayList<>();
        
        try
        {
            JAXBContext context = JAXBContext.newInstance( IndividualReportType.class );
            Unmarshaller unmarshaller = context.createUnmarshaller();
            for ( Object obj : response.getOptionalOutputs().getAny() )
            {
                if ( obj instanceof Node )
                {
                    JAXBElement<IndividualReportType> element = unmarshaller.unmarshal( (Node) obj, IndividualReportType.class );
                    resultList.add( element.getValue() );
                }
            }
        }
        catch ( JAXBException e )
        {
            ModuleLogger.verbose( "could not process response of signature verification for requestId " + response.getRequestID(), e );
        }
        
        return resultList;
    }
    
    VerifyRequest createRequest( SignatureObject signatureObject )
    {
        VerifyRequest request = new VerifyRequest();
        Optional<Object> obj = Optional.ofNullable( signatureObject.getSignaturePtr() )
                .map( SignaturePtr::getWhichDocument );
        
        if ( obj.isPresent() )
        {
            DataObjectType dataObject = parseDocument( obj.get() ).orElseThrow(); // TODO
            
            SignaturePtr ptr = new SignaturePtr();
            ptr.setWhichDocument( dataObject );
            
            SignatureObject sigObj = new SignatureObject();
            sigObj.setSignaturePtr( ptr );
            
            Base64Data b64Data = new Base64Data();
            b64Data.setValue( dataObject.getBinaryData().getValue() );
            
            DocumentType document = new DocumentType();
            document.setBase64Data( b64Data );
            document.setID( dataObject.getDataObjectID() );
            
            InputDocuments inputDocuments = new InputDocuments();
            inputDocuments.getDocumentOrTransformedDataOrDocumentHash().add( document );
            
            request.setSignatureObject( sigObj );
            request.setInputDocuments( inputDocuments );
        }
        else
        {
            request.setSignatureObject( signatureObject );
        }
        
        return request;
        
    }
    
    Optional<DataObjectType> parseDocument( Object document )
    {
        Optional<DataObjectType> dataObject = Optional.empty();
        if ( document instanceof byte[] )
        {
            dataObject = parseBinaryData( (byte[]) document );
        }
        else if ( document instanceof DataObjectType )
        {
            dataObject = Optional.of( (DataObjectType) document );
        }
        
        return dataObject;
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
    
    /**
     * Requesting the verification service by using the configured {@link DefaultVerifierConfig#wsdlUrl} and returning the result of this
     * request. The service client will be created on the first call and reused after. When no wsdl was being provided an exception will be
     * thrown instead.
     * 
     * @param request
     *            the verification request
     * @return the verification response
     */
    ResponseBaseType requestVerification( VerifyRequest request )
    {
        if ( service == null )
        {
            String wsdlLocation = config.getWsdlUrl().orElseThrow( () -> new XAIPValidatorException( "missing wsdl location" ) );
            try
            {
                URL wsdlUrl = new URL( wsdlLocation );
                service = TokenSupplier.supplyToken( config )
                        .map( token -> new S4_Service( wsdlUrl, new IdentityTokenHeaderFeature( token ) ) )
                        .orElseGet( () -> new S4_Service( wsdlUrl ) );
            }
            catch ( MalformedURLException e )
            {
                throw new XAIPValidatorException( "could not read verifyConnectorUrl " + wsdlLocation, e );
            }
        }
        
        return service.getS4().verify( request );
    }
}
