package de.bund.bsi.tresor.xaip.validator.soap;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import javax.xml.ws.Endpoint;
import javax.xml.ws.Service;

import com.beust.jcommander.JCommander;

import de.bund.bsi.tr_esor.api._1.S4;
import de.bund.bsi.tr_esor.xaip._1.XAIPType;
import de.bund.bsi.tresor.xaip.validator.api.control.ModuleLogger;
import de.bund.bsi.tresor.xaip.validator.api.control.XAIPMarshaller;
import de.bund.bsi.tresor.xaip.validator.soap.config.MessageBundle;
import de.bund.bsi.tresor.xaip.validator.soap.config.ServerConfig;
import oasis.names.tc.dss._1_0.core.schema.DocumentType;
import oasis.names.tc.dss._1_0.core.schema.InlineXMLType;
import oasis.names.tc.dss._1_0.core.schema.InputDocuments;
import oasis.names.tc.dss._1_0.core.schema.ResponseBaseType;
import oasis.names.tc.dss._1_0.core.schema.VerifyRequest;

/**
 * @author wolffs
 */
public class Server
{
    /**
     * Main method for starting the local soap server.
     * 
     * @param args
     *            the server arguments
     * @throws Exception
     *             when anything happens
     */
    public static void main( String[] args ) throws Exception
    {
        ServerConfig config = new ServerConfig();
        JCommander jCommander = JCommander.newBuilder()
                .addObject( config )
                .resourceBundle( MessageBundle.RESOURCE )
                .programName( MessageBundle.RESOURCE.getString( MessageBundle.SERVER_NAME ) )
                .build();
        
        // TODO remove this test
        // args = new String[] { "-Mverifier.bsi.wsdlUrl=http://10.3.141.126:8080/VerificationService/S4?wsdl" };
        
        try
        {
            jCommander.parse( args );
            
            ModuleLogger.initConfig( config.isVerbose(), config.getLog() );
            if ( config.isHelp() )
            {
                jCommander.usage();
            }
            else
            {
                startServer( config );
            }
        }
        catch ( Exception e )
        {
            ModuleLogger.initConfig( config.isVerbose(), config.getLog() );
            ModuleLogger.log( "could not start server", e );
        }
        
        clientTest();
    }
    
    /**
     * Starting the server by using the provided configuration
     * 
     * @param config
     *            the configuration
     * @throws URISyntaxException
     *             if any part of the config does not provide a valid URI part
     */
    public static void startServer( ServerConfig config ) throws URISyntaxException
    {
        URI uri = new URI( config.getProtocol(), null, config.getHost(), config.getPort(), config.getPath(), null, null );
        
        String address = uri.toString();
        Endpoint.publish( address, new XAIPValidator( config ) );
        
        ModuleLogger.log( "published server on address: " + address );
    }
    
    /**
     * TODO remove
     */
    public static void clientTest()
    {
        try
        {
            XAIPType xaip = JAXB.unmarshal( new File( "/home/wolffs/Dokumente/XAIP-Validator/validator/PAdES.xaip" ), XAIPType.class );
            System.out.println( "start client test" );
            
            Service service = Service.create(
                    new URL( "http://localhost:8080/xaip-validate?wsdl" ),
                    new QName( "http://www.bsi.bund.de/tr-esor/api/1.2", "S4" ) );
            
            S4 client = service.getPort(
                    new QName( "http://www.bsi.bund.de/tr-esor/api/1.2", "S4" ),
                    S4.class );
            
            JAXBElement<XAIPType> xaipInline = XAIPMarshaller.element( xaip );
            
            InlineXMLType inline = new InlineXMLType();
            inline.setAny( xaipInline );
            
            DocumentType document = new DocumentType();
            document.setInlineXML( inline );
            
            InputDocuments documents = new InputDocuments();
            documents.getDocumentOrTransformedDataOrDocumentHash().add( document );
            
            VerifyRequest verifyRequest = new VerifyRequest();
            verifyRequest.setInputDocuments( documents );
            
            ResponseBaseType response = client.verify( verifyRequest );
            JAXB.marshal( response, System.out );
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
    }
}
