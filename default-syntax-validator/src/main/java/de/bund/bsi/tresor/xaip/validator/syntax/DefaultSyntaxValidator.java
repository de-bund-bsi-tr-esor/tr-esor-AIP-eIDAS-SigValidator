package de.bund.bsi.tresor.xaip.validator.syntax;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
import de.bund.bsi.tresor.xaip.validator.api.entity.XAIPValidatorException;
import lombok.Getter;
import oasis.names.tc.dss._1_0.core.schema.Result;
import oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_.IndividualReportType;

/**
 * Implementation of the SyntaxValidator module from the XAIPValidator.
 * 
 * @author wolffs
 */
@Getter
public class DefaultSyntaxValidator implements SyntaxValidator
{
    private static final String SCHEMA_DIR_PROPERTY = "schemaDir";
    
    private final String        vendor              = "BSI";
    private final String        version             = "1.0.0";
    private String              schemaDir;
    
    @Override
    public void configure( Map<String, String> properties )
    {
        schemaDir = Optional.ofNullable( properties.get( "validator.schemaDir" ) )
                .orElseThrow( () -> new XAIPValidatorException( "missing property " + SCHEMA_DIR_PROPERTY ) );
    }
    
    @Override
    public SyntaxValidationResult validateSyntax( InputStream xaipCandidate )
    {
        Optional<XAIPType> xaip = Optional.empty();
        Result result = DefaultResult.ok()
                .message( "xaip is schema conform", ResultLanguage.ENGLISH )
                .build();
        
        try
        {
            JAXBContext jaxbContext = JAXBContext.newInstance( XAIPType.class );
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            
            SchemaFactory schemaFactory = SchemaFactory.newInstance( XMLConstants.W3C_XML_SCHEMA_NS_URI );
            Schema schema = schemaFactory.newSchema( sources( new File( schemaDir ) ).stream().toArray( Source[]::new ) );
            
            jaxbUnmarshaller.setSchema( schema );
            JAXBElement<XAIPType> element = jaxbUnmarshaller.unmarshal( new StreamSource( xaipCandidate ), XAIPType.class );
            xaip = Optional.ofNullable( element.getValue() );
        }
        catch ( Exception e )
        {
            e.printStackTrace();
            result = DefaultResult.error()
                    .message( "xaip is not schema conform: " + e.getMessage(), ResultLanguage.ENGLISH )
                    .build();
        }
        
        IndividualReportType syntaxReport = new IndividualReportType();
        syntaxReport.setResult( result );
        
        return new SyntaxValidationResult( xaip, syntaxReport );
    }
    
    /**
     * Traversing through the directory and converting every file to a {@link StreamSource}
     * 
     * @param schemaDirectory
     *            the directory containing all schema files
     *            
     * @return the schema sources
     */
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
