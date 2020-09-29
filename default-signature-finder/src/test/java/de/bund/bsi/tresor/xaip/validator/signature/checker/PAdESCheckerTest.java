package de.bund.bsi.tresor.xaip.validator.signature.checker;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import de.bund.bsi.tresor.xaip.validator.api.control.ModuleLogger;

/**
 * @author bendlera
 *
 */
class PAdESCheckerTest
{
    
    private static ByteArrayOutputStream loggerOutput = new ByteArrayOutputStream();
    private static byte[]                signedPdfFile;
    private static byte[]                unsignedPdfFile;
    
    /**
     * Loads test data.
     * 
     * @throws java.lang.Exception
     *             loading fails
     */
    @BeforeAll
    static void setUpBeforeClass() throws Exception
    {
        ModuleLogger.initConfig( true, loggerOutput );
        
        signedPdfFile = Files
                .readAllBytes( Paths.get( PAdESCheckerTest.class.getResource( "/signed.pdf" ).toURI() ) );
        unsignedPdfFile = Files
                .readAllBytes( Paths.get( PAdESCheckerTest.class.getResource( "/unsigned.pdf" ).toURI() ) );
    }
    
    /**
     * Test method for {@link de.bund.bsi.tresor.xaip.validator.signature.checker.PAdESChecker#isPAdES(byte[])}.
     */
    @Test
    void testIsPAdES()
    {
        assertTrue( PAdESChecker.INSTANCE.isPAdES( signedPdfFile ) );
    }
    
    /**
     * Test method for {@link de.bund.bsi.tresor.xaip.validator.signature.checker.PAdESChecker#isPAdES(byte[])}.
     */
    @Test
    void testIsPAdESCaseNoSignature()
    {
        assertFalse( PAdESChecker.INSTANCE.isPAdES( unsignedPdfFile ) );
    }
    
    /**
     * Test method for {@link de.bund.bsi.tresor.xaip.validator.signature.checker.PAdESChecker#isPAdES(byte[])}.
     */
    @Test
    void testIsPAdESCaseNoPdf()
    {
        assertFalse( PAdESChecker.INSTANCE.isPAdES( "filecontent".getBytes( StandardCharsets.UTF_8 ) ) );
    }
    
    /**
     * Test method for {@link de.bund.bsi.tresor.xaip.validator.signature.checker.PAdESChecker#hasPAdESRequirements(byte[])}.
     */
    @Test
    void testHasPAdESRequirementsCaseNoPdf()
    {
        assertFalse( PAdESChecker.INSTANCE.hasPAdESRequirements( "%PDF-1.7".getBytes( StandardCharsets.UTF_8 ) ) );
        String errorMessage = new String( loggerOutput.toByteArray(), StandardCharsets.UTF_8 );
        assertTrue( errorMessage.contains( "data is not PAdES" ) );
    }
    
}
