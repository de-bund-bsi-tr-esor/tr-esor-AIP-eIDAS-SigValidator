package de.bund.bsi.tresor.xaip.validator.syntax;

import java.io.InputStream;
import java.util.Optional;

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
import de.bund.bsi.tresor.xaip.validator.api.boundary.SyntaxValidator;
import de.bund.bsi.tresor.xaip.validator.api.entity.DefaultResult;
import de.bund.bsi.tresor.xaip.validator.api.entity.DefaultResult.ResultLanguage;
import de.bund.bsi.tresor.xaip.validator.api.entity.SyntaxValidationResult;
import lombok.Getter;
import oasis.names.tc.dss._1_0.core.schema.Result;
import oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_.IndividualReportType;

/**
 * @author wolffs
 */
@Getter
public class DefaultSyntaxValidator implements SyntaxValidator
{
    private static final String XSD_FILE = "tr-esor-xaip-v1.2.xsd";
    
    private final String        vendor   = "BSI";
    private final String        version  = "1.0.0";
    
    @Override
    public SyntaxValidationResult validateSyntax( InputStream xaipCandidate )
    {
        Result result = DefaultResult.ok().build();
        Optional<XAIPType> xaip = Optional.empty();
        try
        {
            JAXBContext jaxbContext = JAXBContext.newInstance( XAIPType.class );
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            
            SchemaFactory schemaFactory = SchemaFactory.newInstance( XMLConstants.W3C_XML_SCHEMA_NS_URI );
            Source source = new StreamSource( DefaultSyntaxValidator.class.getResourceAsStream( XSD_FILE ) );
            Schema schema = schemaFactory.newSchema( source );
            
            jaxbUnmarshaller.setSchema( schema );
            
            xaip = Optional.ofNullable( (XAIPType) jaxbUnmarshaller.unmarshal( xaipCandidate ) );
        }
        catch ( JAXBException | SAXException e )
        {
            e.printStackTrace();
            result = DefaultResult.error()
                    .message( e.getMessage(), ResultLanguage.ENGLISH )
                    .build();
        }
        
        IndividualReportType syntaxReport = new IndividualReportType();
        syntaxReport.setResult( result );
        
        return new SyntaxValidationResult( xaip, syntaxReport );
    }
    
}
