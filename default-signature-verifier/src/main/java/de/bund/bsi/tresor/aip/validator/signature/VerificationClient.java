/*-
 * Copyright (c) 2020
 * Federal Office for Information Security (BSI),
 * Godesberger Allee 185-189,
 * 53175 Bonn, Germany,
 * phone: +49 228 99 9582-0,
 * fax: +49 228 99 9582-5400,
 * e-mail: bsi@bsi.bund.de
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.bund.bsi.tresor.aip.validator.signature;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.ws.BindingProvider;

import org.w3c.dom.Node;

import com.sun.xml.ws.client.BindingProviderProperties;

import de.bund.bsi.ecard.api._1.ECard_Service;
import de.bund.bsi.ecard.api._1.VerifyRequest;
import de.bund.bsi.ecard.api._1.VerifyResponse;
import de.bund.bsi.tr_esor.vr.CredentialValidityType;
import de.bund.bsi.tresor.aip.validator.api.control.AIPUtil;
import de.bund.bsi.tresor.aip.validator.api.control.ModuleLogger;
import de.bund.bsi.tresor.aip.validator.api.control.VerificationUtil;
import de.bund.bsi.tresor.aip.validator.api.entity.AIPValidatorException;
import de.bund.bsi.tresor.aip.validator.api.entity.DefaultResult;
import de.bund.bsi.tresor.aip.validator.api.entity.DefaultResult.ResultLanguage;
import de.bund.bsi.tresor.aip.validator.signature.token.IdentityTokenHeaderFeature;
import de.bund.bsi.tresor.aip.validator.signature.token.TokenSupplier;
import oasis.names.tc.dss._1_0.core.schema.AnyType;
import oasis.names.tc.dss._1_0.core.schema.Base64Data;
import oasis.names.tc.dss._1_0.core.schema.DocumentType;
import oasis.names.tc.dss._1_0.core.schema.InputDocuments;
import oasis.names.tc.dss._1_0.core.schema.ResponseBaseType;
import oasis.names.tc.dss._1_0.core.schema.Result;
import oasis.names.tc.dss._1_0.core.schema.SignatureObject;
import oasis.names.tc.dss._1_0.core.schema.SignaturePtr;
import oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_.DetailedSignatureReportType;
import oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_.EvidenceRecordValidityType;
import oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_.EvidenceRecordValidityType.ArchiveTimeStampSequence;
import oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_.IndividualReportType;
import oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_.TimeStampValidityType;

/**
 * @author wolffs
 */
public class VerificationClient
{
    private ECard_Service         service;
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
        String wsdlLocation = config.getWsdlUrl().orElseThrow( () -> new AIPValidatorException( "missing wsdl location" ) );
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
                        .map( token -> new ECard_Service( wsdlUrl, new IdentityTokenHeaderFeature( token ) ) )
                        .orElseGet( () -> new ECard_Service( wsdlUrl ) );
                
                Map<String, Object> requestContext = ((BindingProvider) service.getECard()).getRequestContext();
                requestContext.put( BindingProviderProperties.CONNECT_TIMEOUT, config.getConnectTimeout() );
                requestContext.put( BindingProviderProperties.REQUEST_TIMEOUT, config.getRequestTimeout() );
            }
            
            connection.disconnect();
        }
        catch ( SocketTimeoutException e )
        {
            throw new AIPValidatorException( "could not connect to " + wsdlLocation, e );
        }
        catch ( IOException e )
        {
            throw new AIPValidatorException( "could not read verifyConnectorUrl " + wsdlLocation, e );
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
                return convertResponse( reqId, service.getECard().verifyRequest( request ) );
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
            Result errorResult = DefaultResult.invalid().message( e.getMessage(), ResultLanguage.ENGLISH ).build();
            
            CredentialValidityType verificationError = new CredentialValidityType();
            verificationError.setCredentialID( reqId );
            verificationError.setOther( VerificationUtil.verificationResult( errorResult ) );
            
            return asList( verificationError );
        }
    }
    
    VerifyRequest createRequest( String id, Optional<SignatureObject> signatureObject, Optional<byte[]> data )
    {
        VerifyRequest request = new VerifyRequest();
        request.setRequestID( id );
        signatureObject.ifPresent( obj -> request.getSignatureObject().add( convert( obj ) ) );
        
        String documentId = signatureObject.map( SignatureObject::getSignaturePtr )
                .map( SignaturePtr::getWhichDocument )
                .map( AIPUtil::idFromObject )
                .orElse( id );
        
        data.ifPresent( binary -> {
            Base64Data b64Data = new Base64Data();
            b64Data.setValue( binary );
            
            DocumentType document = new DocumentType();
            document.setBase64Data( b64Data );
            document.setID( documentId );
            
            InputDocuments inputDocuments = new InputDocuments();
            inputDocuments.getDocumentOrTransformedDataOrDocumentHash().add( document );
            request.setInputDocuments( inputDocuments );
        } );
        
        return request;
    }
    
    de.bund.bsi.ecard.api._1.SignatureObject convert( SignatureObject obj )
    {
        de.bund.bsi.ecard.api._1.SignatureObject sigObj = new de.bund.bsi.ecard.api._1.SignatureObject();
        sigObj.setBase64Signature( obj.getBase64Signature() );
        sigObj.setOther( obj.getOther() );
        sigObj.setSignature( obj.getSignature() );
        sigObj.setSignaturePtr( obj.getSignaturePtr() );
        sigObj.setTimestamp( obj.getTimestamp() );
        sigObj.getSchemaRefs().addAll( obj.getSchemaRefs() );
        
        return sigObj;
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
                        else if ( detail instanceof TimeStampValidityType )
                        {
                            result.setIndividualTimeStampReport( (TimeStampValidityType) detail );
                        }
                        else if ( detail instanceof EvidenceRecordValidityType )
                        {
                            result.setEvidenceRecordReport( convert( (EvidenceRecordValidityType) detail ) );
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
    
    de.bund.bsi.tr_esor.vr.EvidenceRecordValidityType convert( EvidenceRecordValidityType report ) throws JAXBException
    {
        var result = new de.bund.bsi.tr_esor.vr.EvidenceRecordValidityType();
        
        result.setFormatOK( report.getFormatOK() );
        result.setId( report.getId() );
        result.setReportVersion( "1.3.0" );
        result.setVersion( report.getVersion().toString() );
        convertTimestampNamespace( report ).ifPresent( result::setArchiveTimeStampSequence );
        convertCryptoInfosNamespace( report ).ifPresent( result::setCryptoInfos );
        convertEncryptionInfoNamespace( report ).ifPresent( result::setEncryptionInfo );
        
        return result;
    }
    
    Optional<de.bund.bsi.tr_esor.vr.EvidenceRecordValidityType.EncryptionInfo> convertEncryptionInfoNamespace(
            EvidenceRecordValidityType report )
    {
        return Optional.ofNullable( report.getEncryptionInfo() )
                .map( ei -> {
                    var info = new de.bund.bsi.tr_esor.vr.EvidenceRecordValidityType.EncryptionInfo();
                    info.setEncryptionInfoType( ei.getEncryptionInfoType() );
                    info.setEncryptionInfoValue( ei.getEncryptionInfoValue() );
                    
                    return info;
                } );
    }
    
    Optional<de.bund.bsi.tr_esor.vr.EvidenceRecordValidityType.CryptoInfos> convertCryptoInfosNamespace(
            EvidenceRecordValidityType report )
    {
        return Optional.ofNullable( report.getCryptoInfos() )
                .map( ci -> {
                    var info = new de.bund.bsi.tr_esor.vr.EvidenceRecordValidityType.CryptoInfos();
                    info.getAttribute().addAll( ci.getAttribute() );
                    
                    return info;
                } );
    }
    
    Optional<de.bund.bsi.tr_esor.vr.EvidenceRecordValidityType.ArchiveTimeStampSequence> convertTimestampNamespace(
            EvidenceRecordValidityType report )
    {
        return Optional.ofNullable( report.getArchiveTimeStampSequence() )
                .map( ArchiveTimeStampSequence::getArchiveTimeStampChain )
                .map( list -> {
                    return list.stream()
                            .map( c -> {
                                var chain = new de.bund.bsi.tr_esor.vr.EvidenceRecordValidityType.ArchiveTimeStampSequence.ArchiveTimeStampChain();
                                chain.getArchiveTimeStamp().addAll( c.getArchiveTimeStamp() );
                                
                                return chain;
                            } ).collect( toList() );
                } )
                .map( list -> {
                    var seq = new de.bund.bsi.tr_esor.vr.EvidenceRecordValidityType.ArchiveTimeStampSequence();
                    seq.getArchiveTimeStampChain().addAll( list );
                    
                    return seq;
                } );
    }
    
}
