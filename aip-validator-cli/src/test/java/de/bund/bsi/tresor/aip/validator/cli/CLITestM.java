/*-*Copyright(c)2020*Federal Office for Information Security(BSI),*Godesberger Allee 185-189,*53175 Bonn,Germany,*phone:+49 228 99 9582-0,*fax:+49 228 99 9582-5400,*e-mail:bsi @bsi.bund.de**Licensed under the Apache License,Version 2.0(the"License");*you may not use this file except in compliance with the License.*You may obtain a copy of the License at**http://www.apache.org/licenses/LICENSE-2.0
**Unless required by applicable law or agreed to in writing,software*distributed under the License is distributed on an"AS IS"BASIS,*WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,either express or implied.*See the License for the specific language governing permissions and*limitations under the License.*/
package de.bund.bsi.tresor.aip.validator.cli;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Stream;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import org.etsi.uri._02918.v1_2.DataObjectReferenceType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.w3._2000._09.xmldsig_.DigestMethodType;

import de.bund.bsi.tr_esor.xaip.XAIPType;
import oasis.names.tc.dss._1_0.core.schema.AnyType;

/***
 * Test cases which are made to be executed manually**
 * 
 * @author wolffs
 */
// @Disabled
public class CLITestM
{
    
    /**
     * Configuration of the wsdl url location if the parameter -v is being used
     */
    private static final String WSDL_URL              = "https://tresortest.protectr.de/VerificationService/eCard?wsdl";
    // private static final String WSDL_URL = "http://localhost:8080/VerificationService/eCard?wsdl";
    // private static final String WSDL_URL = "http://10.3.141.126:8080/VerificationService/S4?wsdl";
    
    private static final String SCHEMA_DIR            = ".././default-syntax-validator/src/main/resources/definitions";
    
    // private static final String BATCH_TEST_DIR = "src/test/resources/samples";
    
    private static final String BATCH_TEST_DIR        = "/home/wolffs/Downloads/asic/xaip";
    private static final String BATCH_TEST_RESULT_DIR = "/tmp/reports";
    
    // private static final String TEST_FILE = "/tmp/cli/TST-24-xades-att-enveloped-xml-single/XAIP13-xades-att-enveloped-xml-single.xml";
    // private static final String TEST_FILE = "/tmp/cli/TST-93-ASIC-AIP_LXAIP_OK_SIG_OK/ASIC_AIP_LXAIP_OK_SIG_OK.asice";
    
    private static final String TEST_FILE             = "/home/wolffs/Downloads/230124_an_Procilon/TST-95-ASIC_AIP_XAIP_OK_SIG_OK_ER_OK_nsfix.asice";
    private static final String TEST_RESULT_FILE      = "/tmp/report.xml";
    
    @Test
    public void help()
    {
        CLI.main( new String[] { "-h" } );
    }
    
    /**
     * Executing the xaip validator with a single test file
     */
    @Test
    public void validateIntegrationTestManually()
    {
        System.out.println( "start" );
        String[] args = {
                "-i",
                TEST_FILE,
                "-d",
                "-v",
                "-Mverifier.wsdlUrl=" + WSDL_URL,
                "-o",
                TEST_RESULT_FILE,
                "-Mvalidator.schemaDir=" + SCHEMA_DIR
        };
        
        CLI.main( args );
        System.out.println( "finished" );
    }
    
    @Test
    public void asicTest() throws Exception
    {
        String baseXaip = "/home/wolffs/Dokumente/asic/base.xaip";
        
        QName xmlDtaQname = new QName( "http://www.bsi.bund.de/tr-esor/xaip", "XAIP" );
        QName etsiQname = new QName( "http://uri.etsi.org/02918/v1.2.1#", "DataObjectReference" );
        JAXBContext context = JAXBContext.newInstance( XAIPType.class, DataObjectReferenceType.class );
        Marshaller marshaller = context.createMarshaller();
        
        XAIPType xaip = JAXB.unmarshal( new File( baseXaip ), XAIPType.class );
        
        File dir = new File( "/home/wolffs/Downloads/asic" );
        for ( File file : dir.listFiles() )
        {
            String name = file.getName();
            if ( !name.contains( "asic" ) )
            {
                continue;
            }
            
            DigestMethodType digestMethodType = new DigestMethodType();
            digestMethodType.setAlgorithm( "http://www.w3.org/2007/05/xmldsig-more#sha3-256" );
            
            DataObjectReferenceType ref = new DataObjectReferenceType();
            ref.setURI( "file://" + file.getAbsolutePath() );
            ref.setRootfile( true );
            ref.setDigestValue( "meh".getBytes() );
            ref.setDigestMethod( digestMethodType );
            
            JAXBElement<DataObjectReferenceType> anyType = new JAXBElement<DataObjectReferenceType>( etsiQname,
                    DataObjectReferenceType.class, ref );
            
            AnyType xmlData = new AnyType();
            xmlData.getAny().add( anyType );
            
            xaip.getDataObjectsSection().getDataObject().get( 0 ).setXmlData( xmlData );
            JAXBElement<XAIPType> xaipElem = new JAXBElement<XAIPType>( xmlDtaQname, XAIPType.class, xaip );
            marshaller.marshal( xaipElem, new File( "/home/wolffs/Downloads/asic/xaip/" + name + ".xaip" ) );
            // JAXB.marshal( xaip, new File( "/home/wolffs/Downloads/asic/xaip/" + name + ".xaip" ) );
        }
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
                BATCH_TEST_RESULT_DIR + File.separator + file.getName() + "-report.xml",
                "-Mvalidator.schemaDir=" + SCHEMA_DIR
        };
        
        CLI.main( args );
    }
    
    static Stream<Arguments> paramProvider()
    {
        File file = new File( BATCH_TEST_DIR );
        
        return Arrays.stream( file.listFiles() )
                // .filter( File::isDirectory )
                // .map( File::listFiles )
                // .flatMap( Arrays::stream )
                .filter( File::isFile )
                .map( Arguments::arguments );
    }
    
}
