package de.bund.bsi.tresor.xaip.validator.signature;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.activation.DataHandler;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.ws.BindingProvider;

import org.w3c.dom.Node;

import com.sun.xml.ws.client.BindingProviderProperties;
import com.sun.xml.ws.util.ByteArrayDataSource;

import de.bund.bsi.ecard.api._1.VerifyResponse;
import de.bund.bsi.tr_esor.api._1.S4_Service;
import de.bund.bsi.tr_esor.vr._1.CredentialValidityType;
import de.bund.bsi.tresor.xaip.validator.api.control.ModuleLogger;
import de.bund.bsi.tresor.xaip.validator.api.control.VerificationUtil;
import de.bund.bsi.tresor.xaip.validator.api.control.XAIPUtil;
import de.bund.bsi.tresor.xaip.validator.api.entity.DefaultResult;
import de.bund.bsi.tresor.xaip.validator.api.entity.DefaultResult.ResultLanguage;
import de.bund.bsi.tresor.xaip.validator.api.entity.XAIPValidatorException;
import de.bund.bsi.tresor.xaip.validator.signature.token.IdentityTokenHeaderFeature;
import de.bund.bsi.tresor.xaip.validator.signature.token.TokenSupplier;
import oasis.names.tc.dss._1_0.core.schema.AnyType;
import oasis.names.tc.dss._1_0.core.schema.Base64Data;
import oasis.names.tc.dss._1_0.core.schema.DocumentType;
import oasis.names.tc.dss._1_0.core.schema.InputDocuments;
import oasis.names.tc.dss._1_0.core.schema.ResponseBaseType;
import oasis.names.tc.dss._1_0.core.schema.Result;
import oasis.names.tc.dss._1_0.core.schema.SignatureObject;
import oasis.names.tc.dss._1_0.core.schema.SignaturePtr;
import oasis.names.tc.dss._1_0.core.schema.VerifyRequest;
import oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_.DetailedSignatureReportType;
import oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_.IndividualReportType;

/**
 * @author wolffs
 */
public class VerificationClient
{
    private S4_Service            service;
    private DefaultVerifierConfig config;
    
    /**
     * Creating a new client for the verificationService
     * 
     * @param config
     *            the defaultVerifier config
     */
    public VerificationClient( DefaultVerifierConfig config )
    {
        this.config = config;
        initializeService();
    }
    
    /**
     * Initializing the s4 service
     */
    private void initializeService()
    {
        String wsdlLocation = config.getWsdlUrl().orElseThrow( () -> new XAIPValidatorException( "missing wsdl location" ) );
        Optional<HttpURLConnection> openConnection = Optional.empty();
        try
        {
            URL wsdlUrl = new URL( wsdlLocation );
            
            HttpURLConnection connection = (HttpURLConnection) wsdlUrl.openConnection();
            connection.setRequestProperty( "Connection", "close" );
            connection.setConnectTimeout( config.getConnectTimeout() );
            
            openConnection = Optional.of( connection );
            connection.connect();
            
            if ( connection.getResponseCode() == 200 )
            {
                service = TokenSupplier.supplyToken( config )
                        .map( token -> new S4_Service( wsdlUrl, new IdentityTokenHeaderFeature( token ) ) )
                        .orElseGet( () -> new S4_Service( wsdlUrl ) );
                
                Map<String, Object> requestContext = ((BindingProvider) service.getS4()).getRequestContext();
                requestContext.put( BindingProviderProperties.CONNECT_TIMEOUT, config.getConnectTimeout() );
                requestContext.put( BindingProviderProperties.REQUEST_TIMEOUT, config.getRequestTimeout() );
            }
            
            connection.disconnect();
        }
        catch ( SocketTimeoutException e )
        {
            throw new XAIPValidatorException( "could not connect to " + wsdlLocation, e );
        }
        catch ( IOException e )
        {
            throw new XAIPValidatorException( "could not read verifyConnectorUrl " + wsdlLocation, e );
        }
        finally
        {
            openConnection.ifPresent( HttpURLConnection::disconnect );
        }
    }
    
    /**
     * Requesting the verification service by using the configured {@link DefaultVerifierConfig#wsdlUrl} and returning the result of this
     * request. The service client will be created on the first call and reused after. When no wsdl was being provided an exception will be
     * thrown instead.
     * 
     * @param reqId
     *            the requestId
     * @param signatureObject
     *            the signatureObject
     * @param dataObjContent
     *            the dataObjectContent
     * @return the verification response
     */
    public List<CredentialValidityType> request( String reqId, Optional<SignatureObject> signatureObject, Optional<byte[]> dataObjContent )
    {
        try
        {
            VerifyRequest request = createRequest( reqId, signatureObject, dataObjContent );
            
            boolean hasInputDocument = Optional.ofNullable( request.getInputDocuments() )
                    .map( InputDocuments::getDocumentOrTransformedDataOrDocumentHash )
                    .map( list -> !list.isEmpty() )
                    .orElse( false );
            
            if ( request.getSignatureObject() != null || hasInputDocument )
            {
                return convertResponse( reqId, service.getS4().verify( request ) );
            }
            else
            {
                ModuleLogger.verbose( "neither inputDocument or signatureObject found for credential or data " + reqId );
                
                return emptyList();
            }
        }
        catch ( Exception e )
        {
            ModuleLogger.verbose( "verification error for credential or data " + reqId, e );
            Result errorResult = DefaultResult.error().message( e.getMessage(), ResultLanguage.ENGLISH ).build();
            
            CredentialValidityType verificationError = new CredentialValidityType();
            verificationError.setCredentialID( reqId );
            verificationError.setOther( VerificationUtil.verificationResult( errorResult ) );
            
            return asList( verificationError );
        }
    }
    
    VerifyRequest createRequest( String id, Optional<SignatureObject> signatureObject, Optional<byte[]> data )
    {
        VerifyRequest request = new VerifyRequest();
        signatureObject.ifPresent( request::setSignatureObject );
        
        String documentId = signatureObject.map( SignatureObject::getSignaturePtr )
                .map( SignaturePtr::getWhichDocument )
                .map( XAIPUtil::idFromObject )
                .orElse( id );
        
        data.ifPresent( binary -> {
            Base64Data b64Data = new Base64Data();
            b64Data.setValue( new DataHandler( new ByteArrayDataSource( binary, "application/octet-stream" ) ) );
            
            DocumentType document = new DocumentType();
            document.setBase64Data( b64Data );
            document.setID( documentId );
            
            InputDocuments inputDocuments = new InputDocuments();
            inputDocuments.getDocumentOrTransformedDataOrDocumentHash().add( document );
            request.setInputDocuments( inputDocuments );
        } );
        
        return request;
    }
    
    /**
     * Parsing the {@link VerifyResponse} from the {@link #requestVerification(VerifyRequest)} and extracting the
     * {@link IndividualReportType}
     * 
     * @param response
     *            the base response type of the {@link VerifyResponse}
     * @return list of {@link IndividualReportType} retrieved from the response
     */
    List<CredentialValidityType> convertResponse( String credId, ResponseBaseType response )
    {
        List<CredentialValidityType> resultList = new ArrayList<>();
        try
        {
            JAXBContext context = JAXBContext.newInstance( IndividualReportType.class );
            Unmarshaller unmarshaller = context.createUnmarshaller();
            for ( Object obj : response.getOptionalOutputs().getAny() )
            {
                if ( obj instanceof Node )
                {
                    JAXBElement<IndividualReportType> element = unmarshaller.unmarshal( (Node) obj, IndividualReportType.class );
                    IndividualReportType report = element.getValue();
                    
                    List<Object> details = Optional.ofNullable( report.getDetails() )
                            .map( AnyType::getAny )
                            .orElse( new ArrayList<>() );
                    
                    for ( Object detail : details )
                    {
                        CredentialValidityType result = new CredentialValidityType();
                        result.setCredentialID( credId );
                        
                        if ( detail instanceof JAXBElement )
                        {
                            detail = ((JAXBElement<?>) detail).getValue();
                        }
                        
                        if ( detail instanceof DetailedSignatureReportType )
                        {
                            result.setDetailedSignatureReport( (DetailedSignatureReportType) detail );
                        }
                        else
                        {
                            result.setOther( VerificationUtil.verificationResult( report.getResult() ) );
                        }
                        
                        resultList.add( result );
                    }
                }
            }
        }
        catch ( JAXBException e )
        {
            ModuleLogger.verbose( "could not process response of signature verification for requestId " + response.getRequestID(), e );
        }
        
        return resultList;
    }
}
