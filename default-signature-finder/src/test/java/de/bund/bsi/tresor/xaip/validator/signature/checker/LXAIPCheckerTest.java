package de.bund.bsi.tresor.xaip.validator.signature.checker;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;

import org.bouncycastle.util.encoders.Hex;
import org.etsi.uri._02918.v1_2.DataObjectReferenceType;
import org.junit.jupiter.api.Test;
import org.w3._2000._09.xmldsig_.DigestMethodType;

import de.bund.bsi.tresor.xaip.validator.api.entity.DefaultResult;
import de.bund.bsi.tresor.xaip.validator.signature.entity.DataReference;
import de.bund.bsi.tresor.xaip.validator.signature.entity.DigestAlgorithm;
import de.bund.bsi.tresor.xaip.validator.signature.entity.LXAIPCheckerException;

/**
 * @author bendlera
 *
 */
class LXAIPCheckerTest
{
    private LXAIPChecker uut = LXAIPChecker.INSTANCE;
    
    /**
     * Test method for
     * {@link de.bund.bsi.tresor.xaip.validator.signature.checker.LXAIPChecker#calculateDigest(java.lang.String, org.etsi.uri._02918.v1_2.DataObjectReferenceType)}.
     */
    @Test
    void testVerify()
    {
        String workingDir = System.getProperty( "user.dir" );
        String uri = "file:///" + workingDir + "/src/test/resources/text.txt";
        
        DigestMethodType digestMethod = new DigestMethodType();
        digestMethod.setAlgorithm( DigestAlgorithm.SHA256.getXmlUri() );
        
        DataObjectReferenceType dataObjectReference = new DataObjectReferenceType();
        dataObjectReference.setURI( uri );
        dataObjectReference.setDigestMethod( digestMethod );
        dataObjectReference.setDigestValue( Hex.decode( "ed7002b439e9ac845f22357d822bac1444730fbdb6016d3ec9432297b9ec9f73" ) );
        
        DataReference actual = uut.verify( "ID", dataObjectReference );
        
        assertTrue( actual.getDataObjectReference().isPresent() );
        assertEquals( dataObjectReference, actual.getDataObjectReference().get() );
        assertEquals( DefaultResult.Major.OK.getURI(), actual.getIndividualReportType().getResult().getResultMajor() );
    }
    
    /**
     * Test method for
     * {@link de.bund.bsi.tresor.xaip.validator.signature.checker.LXAIPChecker#calculateDigest(java.lang.String, org.etsi.uri._02918.v1_2.DataObjectReferenceType)}.
     */
    @Test
    void testVerifyDiffer()
    {
        String workingDir = System.getProperty( "user.dir" );
        String uri = "file:///" + workingDir + "/src/test/resources/text.txt";
        
        DigestMethodType digestMethod = new DigestMethodType();
        digestMethod.setAlgorithm( DigestAlgorithm.SHA256.getXmlUri() );
        
        DataObjectReferenceType dataObjectReference = new DataObjectReferenceType();
        dataObjectReference.setURI( uri );
        dataObjectReference.setDigestMethod( digestMethod );
        dataObjectReference.setDigestValue( Hex.decode( "ed7002b439e9ac845f22357d833bac1555830fbdb6016d3ec9432297b9ec9f73" ) );
        
        DataReference actual = uut.verify( "ID", dataObjectReference );
        
        assertTrue( actual.getDataObjectReference().isEmpty() );
        assertEquals( DefaultResult.Major.ERROR.getURI(), actual.getIndividualReportType().getResult().getResultMajor() );
        assertEquals( "ID: Digest differ.", actual.getIndividualReportType().getResult().getResultMessage().getValue() );
    }
    
    /**
     * Test method for
     * {@link de.bund.bsi.tresor.xaip.validator.signature.checker.LXAIPChecker#parseUri(org.etsi.uri._02918.v1_2.DataObjectReferenceType)}.
     */
    @Test
    void testParseUri()
    {
        String workingDir = System.getProperty( "user.dir" );
        String uri = "file:///" + workingDir + "/src/test/resources/text.txt";
        
        DataObjectReferenceType dataObjectReference = new DataObjectReferenceType();
        dataObjectReference.setURI( uri );
        
        URI actual = assertDoesNotThrow( () -> uut.parseUri( dataObjectReference ) );
        
        assertEquals( URI.create( uri ), actual );
    }
    
    /**
     * Test method for
     * {@link de.bund.bsi.tresor.xaip.validator.signature.checker.LXAIPChecker#parseUri(org.etsi.uri._02918.v1_2.DataObjectReferenceType)}.
     */
    @Test
    void testParseUriCaseUnsuportedScheme()
    {
        String uri = "https://localhost/src/test/resources/text.txt";
        
        DataObjectReferenceType dataObjectReference = new DataObjectReferenceType();
        dataObjectReference.setURI( uri );
        
        assertThrows( LXAIPCheckerException.class, () -> uut.parseUri( dataObjectReference ),
                "URI scheme https not supported" );
    }
    
    /**
     * Test method for
     * {@link de.bund.bsi.tresor.xaip.validator.signature.checker.LXAIPChecker#parseUri(org.etsi.uri._02918.v1_2.DataObjectReferenceType)}.
     */
    @Test
    void testParseUriCaseNoValue()
    {
        DataObjectReferenceType dataObjectReference = new DataObjectReferenceType();
        
        assertThrows( LXAIPCheckerException.class, () -> uut.parseUri( dataObjectReference ), "Attribute uri has no value." );
    }
    
    /**
     * Test method for
     * {@link de.bund.bsi.tresor.xaip.validator.signature.checker.LXAIPChecker#parseUri(org.etsi.uri._02918.v1_2.DataObjectReferenceType)}.
     */
    @Test
    void testParseUriCaseParserError()
    {
        DataObjectReferenceType dataObjectReference = new DataObjectReferenceType();
        dataObjectReference.setURI( "#file<:>//../file" );
        
        assertThrows( LXAIPCheckerException.class, () -> uut.parseUri( dataObjectReference ) );
    }
    
    /**
     * Test method for
     * {@link de.bund.bsi.tresor.xaip.validator.signature.checker.LXAIPChecker#parseAlgorithm(org.etsi.uri._02918.v1_2.DataObjectReferenceType)}.
     */
    @Test
    void testParseAlgorithm()
    {
        
    }
    
    /**
     * Test method for {@link de.bund.bsi.tresor.xaip.validator.signature.checker.LXAIPChecker#convertUri(java.net.URI)}.
     */
    @Test
    void testConvertUri()
    {
        String workingDir = System.getProperty( "user.dir" );
        URI uri = URI.create( "file:///" + workingDir + "/src/test/resources/text.txt" );
        
        assertDoesNotThrow( () -> uut.convertUri( uri ) );
    }
    
    /**
     * Test method for {@link de.bund.bsi.tresor.xaip.validator.signature.checker.LXAIPChecker#convertUri(java.net.URI)}.
     */
    @Test
    void testConvertUriCaseInvalidUri()
    {
        URI uri = URI.create( "file://src/test/resources/text.txt" );
        
        assertThrows( LXAIPCheckerException.class, () -> uut.convertUri( uri ), "URI could not converted to path." );
    }
    
    /**
     * Test method for {@link de.bund.bsi.tresor.xaip.validator.signature.checker.LXAIPChecker#convertUri(java.net.URI)}.
     */
    @Test
    void testConvertUriCaseFileNotExists()
    {
        String workingDir = System.getProperty( "user.dir" );
        URI uri = URI.create( "file:///" + workingDir + "/src/test/resources/notExistingfile.extension" );
        
        assertThrows( LXAIPCheckerException.class, () -> uut.convertUri( uri ),
                "File " + workingDir + "/src/test/resources/notExistingfile.extension does not exists." );
    }
    
    /**
     * Test method for {@link de.bund.bsi.tresor.xaip.validator.signature.checker.LXAIPChecker#createDataReference(java.lang.String)}.
     */
    @Test
    void testCreateDataReference()
    {
        String messageText = "error";
        
        DataReference dataReference = uut.createDataReference( messageText );
        
        assertTrue( dataReference.getDataObjectReference().isEmpty() );
        assertEquals( DefaultResult.Major.ERROR.getURI(), dataReference.getIndividualReportType().getResult().getResultMajor() );
        assertEquals( DefaultResult.Minor.NO_DATA_ACCESS_WARNING.getURI(),
                dataReference.getIndividualReportType().getResult().getResultMinor() );
        assertEquals( messageText,
                dataReference.getIndividualReportType().getResult().getResultMessage().getValue() );
    }
    
}
