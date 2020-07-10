package de.bund.bsi.tresor.xaip.validator.syntax;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

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
    private static final String DEFINITION_DIRECTORY = "definitions";
    
    private final String        vendor               = "BSI";
    private final String        version              = "1.0.0";
    
    @Override
    public SyntaxValidationResult validateSyntax( InputStream xaipCandidate )
    {
        Optional<XAIPType> xaip = Optional.empty();
        Result result = DefaultResult.ok()
                .message( "synta validation", ResultLanguage.ENGLISH )
                .build();
        
        try
        {
            JAXBContext jaxbContext = JAXBContext.newInstance( XAIPType.class );
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            
            File schemaDirectory = new File( DefaultSyntaxValidator.class.getClassLoader().getResource( DEFINITION_DIRECTORY ).getFile() );
            
            SchemaFactory schemaFactory = SchemaFactory.newInstance( XMLConstants.W3C_XML_SCHEMA_NS_URI );
            Schema schema = schemaFactory.newSchema( sources( schemaDirectory ).stream().toArray( Source[]::new ) );
            
            jaxbUnmarshaller.setSchema( schema );
            JAXBElement<XAIPType> element = jaxbUnmarshaller.unmarshal( new StreamSource( xaipCandidate ), XAIPType.class );
            xaip = Optional.ofNullable( element.getValue() );
        }
        catch ( Exception e )
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
    
    List<Source> sources( File schemaDirectory )
    {
        List<Source> schemas = new ArrayList<>();
        for ( File file : schemaDirectory.listFiles() )
        {
            if ( file.isDirectory() )
            {
                schemas.addAll( sources( file ) );
            }
            else
            {
                schemas.add( new StreamSource( file ) );
            }
        }
        
        return schemas;
    }
}
