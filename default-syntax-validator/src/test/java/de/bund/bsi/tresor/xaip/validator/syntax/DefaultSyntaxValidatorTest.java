package de.bund.bsi.tresor.xaip.validator.syntax;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import de.bund.bsi.tresor.xaip.validator.api.control.ModuleLogger;
import de.bund.bsi.tresor.xaip.validator.api.entity.DefaultResult;
import de.bund.bsi.tresor.xaip.validator.api.entity.SyntaxValidationResult;

/**
 * @author bendlera
 */
class DefaultSyntaxValidatorTest
{
    
    /**
     * Test method for {@link de.bund.bsi.tresor.xaip.validator.syntax.DefaultSyntaxValidator#validateSyntax(java.io.InputStream)}.
     */
    @Test
    void testValidateSyntax()
    {
        ModuleLogger.initConfig( true, new ByteArrayOutputStream() );
        
        Map<String, String> properties = new HashMap<>();
        properties.put( "validator.schemaDir",
                Paths.get( "src/main/resources/definitions" ).toAbsolutePath().toString() );
        
        DefaultSyntaxValidator defaultSyntaxValidator = new DefaultSyntaxValidator();
        defaultSyntaxValidator.configure( properties );
        
        try ( InputStream inputStream = Files
                .newInputStream( Paths.get( "src/test/resources/LXAIP.xml" ).toAbsolutePath() ) )
        {
            SyntaxValidationResult actual = defaultSyntaxValidator.validateSyntax( inputStream );
            
            assertEquals( DefaultResult.Major.OK.getURI(), actual.getSyntaxReport().getResult().getResultMajor() );
        }
        catch ( IOException e )
        {
            fail( "test file could not readed", e );
        }
    }
    
}