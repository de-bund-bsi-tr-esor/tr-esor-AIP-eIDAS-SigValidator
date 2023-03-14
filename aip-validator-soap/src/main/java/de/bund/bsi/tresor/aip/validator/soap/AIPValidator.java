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
package de.bund.bsi.tresor.aip.validator.soap;

import static java.util.stream.Collectors.toList;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

import javax.jws.WebService;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;
import javax.xml.transform.dom.DOMSource;
import javax.xml.ws.soap.MTOM;

import org.apache.cxf.annotations.SchemaValidation;
import org.w3c.dom.Node;

import de.bund.bsi.tr_esor.api._1.S4;
import de.bund.bsi.tr_esor.xaip.XAIPType;
import de.bund.bsi.tresor.aip.validator.api.control.AIPUtil;
import de.bund.bsi.tresor.aip.validator.api.entity.AIPValidatorException;
import de.bund.bsi.tresor.aip.validator.dispatcher.Dispatcher;
import de.bund.bsi.tresor.aip.validator.soap.config.DispatcherArgs;
import de.bund.bsi.tresor.aip.validator.soap.config.ServerConfig;
import lombok.AllArgsConstructor;
import oasis.names.tc.dss._1_0.core.schema.AnyType;
import oasis.names.tc.dss._1_0.core.schema.Base64Data;
import oasis.names.tc.dss._1_0.core.schema.DocumentType;
import oasis.names.tc.dss._1_0.core.schema.InlineXMLType;
import oasis.names.tc.dss._1_0.core.schema.InputDocuments;
import oasis.names.tc.dss._1_0.core.schema.InternationalStringType;
import oasis.names.tc.dss._1_0.core.schema.ResponseBaseType;
import oasis.names.tc.dss._1_0.core.schema.Result;
import oasis.names.tc.dss._1_0.core.schema.VerifyRequest;
import oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_.VerificationReportType;

/**
 * @author wolffs
 */
@MTOM
@SchemaValidation
@AllArgsConstructor
@WebService( endpointInterface = "de.bund.bsi.tr_esor.api._1.S4",
        serviceName = "S4",
        portName = "S4",
        targetNamespace = "http://www.bsi.bund.de/tr-esor/api/1.3",
        wsdlLocation = "/wsdl/tr-esor-S-4-v1.3.wsdl" )
public class AIPValidator implements S4
{
    private static final String MAJOR_OK            = "http://www.bsi.bund.de/tr-esor/api/1.3/resultmajor#ok";
    private static final String MAJOR_ERROR         = "http://www.bsi.bund.de/tr-esor/api/1.3/resultmajor#error";
    
    private static final QName  VERIFICATION_REPORT = new QName(
            "urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#",
            "VerificationReport" );
    
    private ServerConfig        config;
    
    VerificationReportType dispatch( ByteArrayInputStream xaipCandidate )
    {
        ByteArrayOutputStream resultOutput = new ByteArrayOutputStream();
        DispatcherArgs args = DispatcherArgs.builder()
                .input( xaipCandidate )
                .output( resultOutput )
                .verbose( config.isVerbose() )
                .log( config.getLog() )
                .verify( config.isVerify() )
                .moduleConfig( config.getModuleConfig() )
                .build();
        
        Dispatcher.INSTANCE.dispatch( args );
        
        return JAXB.unmarshal( new ByteArrayInputStream( resultOutput.toByteArray() ), VerificationReportType.class );
    }
    
    List<ByteArrayInputStream> findXAIPCandidate( List<Object> objects ) throws JAXBException
    {
        return objects.stream()
                .filter( DocumentType.class::isInstance )
                .map( DocumentType.class::cast )
                .map( type -> base64Data( type )
                        .or( () -> inlineXml( type ) )
                        .or( () -> base64Xml( type ) )
                        .orElse( null ) )
                .filter( Objects::nonNull )
                .collect( toList() );
    }
    
    Optional<ByteArrayInputStream> base64Data( DocumentType type )
    {
        return Optional.ofNullable( type )
                .map( DocumentType::getBase64Data )
                .map( Base64Data::getValue )
                .map( ByteArrayInputStream::new );
    }
    
    Optional<ByteArrayInputStream> base64Xml( DocumentType type )
    {
        return Optional.ofNullable( type )
                .map( DocumentType::getBase64XML )
                .map( ByteArrayInputStream::new );
    }
    
    Optional<ByteArrayInputStream> inlineXml( DocumentType type )
    {
        return Optional.ofNullable( type )
                .map( DocumentType::getInlineXML )
                .map( InlineXMLType::getAny )
                .map( obj -> {
                    Optional<XAIPType> optXaip = Optional.empty();
                    if ( obj instanceof JAXBElement )
                    {
                        JAXBElement<XAIPType> elem = (JAXBElement) obj;
                        optXaip = Optional.ofNullable( elem.getValue() );
                    }
                    
                    if ( optXaip.isEmpty() && obj instanceof Node )
                    {
                        Node node = (Node) obj;
                        XAIPType xaip = JAXB.unmarshal( new DOMSource( node ), XAIPType.class );
                        optXaip = Optional.ofNullable( xaip );
                    }
                    
                    return optXaip.map( xaip -> {
                        ByteArrayOutputStream xaipProvider = new ByteArrayOutputStream();
                        JAXB.marshal( AIPUtil.asElement( xaip ), xaipProvider );
                        
                        return new ByteArrayInputStream( xaipProvider.toByteArray() );
                    } ).orElse( null );
                } );
    }
    
    @Override
    public ResponseBaseType verify( VerifyRequest parameters )
    {
        Result result = new Result();
        result.setResultMajor( MAJOR_ERROR );
        
        List<JAXBElement<VerificationReportType>> reports = new ArrayList<>();
        try
        {
            List<Object> documents = Optional.ofNullable( parameters )
                    .map( VerifyRequest::getInputDocuments )
                    .map( InputDocuments::getDocumentOrTransformedDataOrDocumentHash )
                    .orElse( new ArrayList<>() );
            
            var candidates = findXAIPCandidate( documents );
            if ( candidates.isEmpty() )
            {
                throw new AIPValidatorException( "no xaip provided in the input documents" );
            }
            
            reports = candidates.stream()
                    .map( this::dispatch )
                    .map( vr -> new JAXBElement<>( VERIFICATION_REPORT, VerificationReportType.class, vr ) )
                    .collect( toList() );
            
            result.setResultMajor( MAJOR_OK );
        }
        catch ( Exception e )
        {
            e.printStackTrace();
            
            InternationalStringType message = new InternationalStringType();
            message.setLang( Locale.ENGLISH.getLanguage() );
            message.setValue( e.getMessage() );
            
            result.setResultMessage( message );
        }
        
        AnyType outputs = new AnyType();
        outputs.getAny().addAll( reports );
        
        ResponseBaseType response = new ResponseBaseType();
        response.setResult( result );
        response.setOptionalOutputs( outputs );
        response.setProfile( getClass().getCanonicalName() );
        
        return response;
    }
}
