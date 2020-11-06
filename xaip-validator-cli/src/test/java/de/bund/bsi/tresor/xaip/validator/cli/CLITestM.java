package de.bund.bsi.tresor.xaip.validator.cli;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Test cases which are made to be executed manually
 * 
 * @author wolffs
 */
public class CLITestM
{
    
    /**
     * Configuration of the wsdl url location if the parameter -v is being used
     */
    private static final String WSDL_URL              = "http://10.3.141.126:8080/VerificationService/S4?wsdl";
    private static final String SCHEMA_DIR            = ".././default-syntax-validator/src/main/resources/definitions";
    
    private static final String BATCH_TEST_DIR        = "src/test/resources/samples";
    private static final String BATCH_TEST_RESULT_DIR = "/tmp/reports";
    
    // private static final String TEST_FILE = "src/test/resources/samples/TST-01-cades-det-single/XAIP-cades-det-single.xml";
    // private static final String TEST_FILE = "src/test/resources/samples/TST-03-cades-att-single/XAIP-cades-att-single.xml";
    // private static final String TEST_FILE = "src/test/resources/samples/TST-07-pdf-att-inv-single/XAIP-pdf-att-inv-single.xml";
    // private static final String TEST_FILE = "src/test/resources/samples/TST-09-pdf-att-vis-single/XAIP-pdf-att-vis-single.xml";
    // private static final String TEST_FILE = "src/test/resources/samples/TST-11-xades-det-txt-single/XAIP-xades-det-single.xml";
    private static final String TEST_FILE             = "src/test/resources/samples/TST-13-xades-att-enveloping-txt-single/XAIP-xades-att-env-txt-single.xml";
    private static final String TEST_RESULT__FILE     = "/tmp/report.xml";
    
    /**
     * Executing the xaip validator with a single test file
     */
    @Test
    public void validateIntegrationTestManually()
    {
        String[] args = {
                "-i",
                TEST_FILE,
                "-d",
                "-v",
                "-Mverifier.wsdlUrl=" + WSDL_URL,
                "-o",
                TEST_RESULT__FILE,
                "-Mvalidator.schemaDir=" + SCHEMA_DIR
        };
        
        CLI.main( args );
    }
    
    /**
     * Executing the xaip validator with the configured arguments for every file found in the
     * 
     * @param file
     *            the file provided by the paramProvider method
     */
    @ParameterizedTest
    @MethodSource( "paramProvider" )
    public void batchTest( File file )
    {
        String[] args = {
                "-i",
                file.getAbsolutePath(),
                "-d",
                "-v",
                "-Mverifier.wsdlUrl=" + WSDL_URL,
                "-o",
                BATCH_TEST_RESULT_DIR + File.pathSeparatorChar + file.getName() + "-report.xml",
                "-Mvalidator.schemaDir=" + SCHEMA_DIR
        };
        
        CLI.main( args );
    }
    
    static Stream<Arguments> paramProvider()
    {
        File file = new File( BATCH_TEST_DIR );
        
        return Arrays.stream( file.listFiles() )
                .filter( File::isDirectory )
                .map( File::listFiles )
                .flatMap( Arrays::stream )
                .filter( File::isFile )
                .map( Arguments::arguments );
    }
}