package de.bund.bsi.tresor.xaip.validator.soap;

import static java.util.stream.Collectors.toList;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import javax.jws.WebService;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.ws.soap.MTOM;

import org.apache.cxf.annotations.SchemaValidation;
import org.w3c.dom.Node;

import de.bund.bsi.tr_esor.api._1.S4;
import de.bund.bsi.tr_esor.xaip._1.XAIPType;
import de.bund.bsi.tresor.xaip.validator.api.entity.XAIPValidatorException;
import de.bund.bsi.tresor.xaip.validator.dispatcher.Dispatcher;
import de.bund.bsi.tresor.xaip.validator.soap.config.DispatcherArgs;
import lombok.NoArgsConstructor;
import oasis.names.tc.dss._1_0.core.schema.AnyType;
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
@NoArgsConstructor( force = true, onConstructor_ = { @Deprecated } )
@WebService( endpointInterface = "de.bund.bsi.tr_esor.api._1.S4",
        serviceName = "S4",
        portName = "S4",
        targetNamespace = "http://www.bsi.bund.de/tr-esor/api/1.2",
        wsdlLocation = "/wsdl/tr-esor-S-4-v1.2.wsdl" )
public class XAIPValidator implements S4
{
    private static final String MAJOR_OK    = "http://www.bsi.bund.de/tr-esor/api/1.2/resultmajor#ok";
    private static final String MAJOR_ERROR = "http://www.bsi.bund.de/tr-esor/api/1.2/resultmajor#error";
    
    @Override
    public ResponseBaseType verify( VerifyRequest parameters )
    {
        Result result = new Result();
        result.setResultMajor( MAJOR_ERROR );
        
        List<VerificationReportType> reports = new ArrayList<>();
        try
        {
            List<Object> documents = Optional.ofNullable( parameters )
                    .map( VerifyRequest::getInputDocuments )
                    .map( InputDocuments::getDocumentOrTransformedDataOrDocumentHash )
                    .orElse( new ArrayList<>() );
            
            List<XAIPType> xaips = findXAIPs( documents );
            if ( xaips.isEmpty() )
            {
                throw new XAIPValidatorException( "no xaip provided in the input documents" );
            }
            
            reports = xaips.stream()
                    .map( this::dispatch )
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
        
        return response;
    }
    
    VerificationReportType dispatch( XAIPType xaip )
    {
        try
        {
            ByteArrayOutputStream xaipProvider = new ByteArrayOutputStream();
            JAXB.marshal( xaip, xaipProvider );
            
            ByteArrayOutputStream resultOutput = new ByteArrayOutputStream();
            DispatcherArgs args = DispatcherArgs.builder()
                    .input( StreamConverter.fromOutput( xaipProvider ) )
                    .output( resultOutput )
                    .verbose( false )
                    .log( System.out )
                    .verify( true )
                    .build();
            
            Dispatcher.INSTANCE.dispatch( args );
            
            return JAXB.unmarshal( StreamConverter.fromOutput( resultOutput ), VerificationReportType.class );
        }
        catch ( IOException e )
        {
            throw new XAIPValidatorException( "io error on dispatcher", e );
        }
    }
    
    List<XAIPType> findXAIPs( List<Object> objects ) throws JAXBException
    {
        JAXBContext context = JAXBContext.newInstance( XAIPType.class );
        Unmarshaller unmarshaller = context.createUnmarshaller();
        
        return objects.stream()
                .filter( DocumentType.class::isInstance )
                .map( DocumentType.class::cast )
                .map( DocumentType::getInlineXML )
                .map( InlineXMLType::getAny )
                .peek( foo -> System.out.println( foo.getClass() ) )
                .filter( Node.class::isInstance )
                .map( Node.class::cast )
                .map( node -> {
                    try
                    {
                        return (XAIPType) unmarshaller.unmarshal( node );
                    }
                    catch ( Exception e )
                    {
                        throw new XAIPValidatorException( "", e );
                    }
                } )
                // .filter( JAXBElement.class::isInstance )
                // .map( JAXBElement.class::cast )
                // .map( JAXBElement::getValue )
                // .filter( XAIPType.class::isInstance )
                // .map( XAIPType.class::cast )
                .collect( toList() );
    }
}
