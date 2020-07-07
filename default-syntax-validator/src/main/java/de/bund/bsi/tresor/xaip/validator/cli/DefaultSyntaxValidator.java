package de.bund.bsi.tresor.xaip.validator.cli;

import java.io.ByteArrayInputStream;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import de.bund.bsi.tr_esor.xaip._1.XAIPType;
import de.bund.bsi.tresor.xaip.validator.api.SyntaxValidator;
import oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_.IndividualReportType;
import oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_.VerificationReportType;

/**
 * @author wolffs
 */
public class DefaultSyntaxValidator implements SyntaxValidator
{
    
    private static final String XSD_FILE = "tr-esor-xaip-v1.2.xsd";
    
    @Override
    public VerificationReportType validateSyntax( ByteArrayInputStream xaipCandidate )
    {
        try
        {
            JAXBContext jaxbContext = JAXBContext.newInstance( XAIPType.class );
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            
            SchemaFactory schemaFactory = SchemaFactory.newInstance( XMLConstants.W3C_XML_SCHEMA_NS_URI );
            Source source = new StreamSource( DefaultSyntaxValidator.class.getResourceAsStream( XSD_FILE ) );
            Schema schema = schemaFactory.newSchema( source );
            
            jaxbUnmarshaller.setSchema( schema );
            
            XAIPType result = (XAIPType) jaxbUnmarshaller.unmarshal( xaipCandidate );
        }
        catch ( JAXBException | SAXException e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return validationResult();
    }
    
    VerificationReportType validationResult()
    {
        IndividualReportType syntaxReport = new IndividualReportType();
        
        return null;
    }
    
}
