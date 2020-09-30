package de.bund.bsi.tresor.xaip.validator.signature.lxaip;

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

/**
 * @author bendlera
 *
 */
class LXAIPCheckerTest
{
    
    /**
     * Test method for
     * {@link de.bund.bsi.tresor.xaip.validator.signature.lxaip.LXAIPChecker#calculateDigest(java.lang.String, org.etsi.uri._02918.v1_2.DataObjectReferenceType)}.
     */
    @Test
    void testCalculateDigest()
    {
        String workingDir = System.getProperty( "user.dir" );
        String uri = "file:///" + workingDir + "/src/test/resources/text.txt";
        
        DigestMethodType digestMethod = new DigestMethodType();
        digestMethod.setAlgorithm( DigestAlgorithm.SHA256.getXMLURI() );
        
        DataObjectReferenceType dataObjectReference = new DataObjectReferenceType();
        dataObjectReference.setURI( uri );
        dataObjectReference.setDigestMethod( digestMethod );
        dataObjectReference.setDigestValue( Hex.decode( "ed7002b439e9ac845f22357d822bac1444730fbdb6016d3ec9432297b9ec9f73" ) );
        
        LXAIPChecker lxaipChecker = new LXAIPChecker();
        
        DataReference actual = lxaipChecker.calculateDigest( "ID", dataObjectReference );
        
        assertTrue( actual.getDataObjectReference().isPresent() );
        assertEquals( dataObjectReference, actual.getDataObjectReference().get() );
        assertEquals( DefaultResult.Major.OK.getURI(), actual.getIndividualReportType().getResult().getResultMajor() );
    }
    
    /**
     * Test method for
     * {@link de.bund.bsi.tresor.xaip.validator.signature.lxaip.LXAIPChecker#calculateDigest(java.lang.String, org.etsi.uri._02918.v1_2.DataObjectReferenceType)}.
     */
    @Test
    void testCalculateDigestCaseDigestDiffer()
    {
        String workingDir = System.getProperty( "user.dir" );
        String uri = "file:///" + workingDir + "/src/test/resources/text.txt";
        
        DigestMethodType digestMethod = new DigestMethodType();
        digestMethod.setAlgorithm( DigestAlgorithm.SHA256.getXMLURI() );
        
        DataObjectReferenceType dataObjectReference = new DataObjectReferenceType();
        dataObjectReference.setURI( uri );
        dataObjectReference.setDigestMethod( digestMethod );
        dataObjectReference.setDigestValue( Hex.decode( "ed7002b439e9ac845f22357d833bac1555830fbdb6016d3ec9432297b9ec9f73" ) );
        
        LXAIPChecker lxaipChecker = new LXAIPChecker();
        
        DataReference actual = lxaipChecker.calculateDigest( "ID", dataObjectReference );
        
        assertTrue( actual.getDataObjectReference().isEmpty() );
        assertEquals( DefaultResult.Major.ERROR.getURI(), actual.getIndividualReportType().getResult().getResultMajor() );
        assertEquals( "ID: Digest differ.", actual.getIndividualReportType().getResult().getResultMessage().getValue() );
    }
    
    /**
     * Test method for
     * {@link de.bund.bsi.tresor.xaip.validator.signature.lxaip.LXAIPChecker#parseUri(org.etsi.uri._02918.v1_2.DataObjectReferenceType)}.
     */
    @Test
    void testParseUri()
    {
        String workingDir = System.getProperty( "user.dir" );
        String uri = "file:///" + workingDir + "/src/test/resources/text.txt";
        
        DataObjectReferenceType dataObjectReference = new DataObjectReferenceType();
        dataObjectReference.setURI( uri );
        
        LXAIPChecker lxaipChecker = new LXAIPChecker();
        
        URI actual = assertDoesNotThrow( () -> lxaipChecker.parseUri( dataObjectReference ) );
        
        assertEquals( URI.create( uri ), actual );
    }
    
    /**
     * Test method for
     * {@link de.bund.bsi.tresor.xaip.validator.signature.lxaip.LXAIPChecker#parseUri(org.etsi.uri._02918.v1_2.DataObjectReferenceType)}.
     */
    @Test
    void testParseUriCaseUnsuportedScheme()
    {
        String uri = "https://localhost/src/test/resources/text.txt";
        
        DataObjectReferenceType dataObjectReference = new DataObjectReferenceType();
        dataObjectReference.setURI( uri );
        
        LXAIPChecker lxaipChecker = new LXAIPChecker();
        
        assertThrows( LXAIPCheckerException.class, () -> lxaipChecker.parseUri( dataObjectReference ), "URI scheme https not supported" );
    }
    
    /**
     * Test method for
     * {@link de.bund.bsi.tresor.xaip.validator.signature.lxaip.LXAIPChecker#parseUri(org.etsi.uri._02918.v1_2.DataObjectReferenceType)}.
     */
    @Test
    void testParseUriCaseNoValue()
    {
        DataObjectReferenceType dataObjectReference = new DataObjectReferenceType();
        
        LXAIPChecker lxaipChecker = new LXAIPChecker();
        
        assertThrows( LXAIPCheckerException.class, () -> lxaipChecker.parseUri( dataObjectReference ), "Attribute uri has no value." );
    }
    
    /**
     * Test method for
     * {@link de.bund.bsi.tresor.xaip.validator.signature.lxaip.LXAIPChecker#parseUri(org.etsi.uri._02918.v1_2.DataObjectReferenceType)}.
     */
    @Test
    void testParseUriCaseParserError()
    {
        DataObjectReferenceType dataObjectReference = new DataObjectReferenceType();
        dataObjectReference.setURI( "#file<:>//../file" );
        
        LXAIPChecker lxaipChecker = new LXAIPChecker();
        
        assertThrows( LXAIPCheckerException.class, () -> lxaipChecker.parseUri( dataObjectReference ) );
    }
    
    /**
     * Test method for
     * {@link de.bund.bsi.tresor.xaip.validator.signature.lxaip.LXAIPChecker#parseAlgorithm(org.etsi.uri._02918.v1_2.DataObjectReferenceType)}.
     */
    @Test
    void testParseAlgorithm()
    {
        
    }
    
    /**
     * Test method for {@link de.bund.bsi.tresor.xaip.validator.signature.lxaip.LXAIPChecker#convertUri(java.net.URI)}.
     */
    @Test
    void testConvertUri()
    {
        LXAIPChecker lxaipChecker = new LXAIPChecker();
        
        String workingDir = System.getProperty( "user.dir" );
        URI uri = URI.create( "file:///" + workingDir + "/src/test/resources/text.txt" );
        
        assertDoesNotThrow( () -> lxaipChecker.convertUri( uri ) );
    }
    
    /**
     * Test method for {@link de.bund.bsi.tresor.xaip.validator.signature.lxaip.LXAIPChecker#convertUri(java.net.URI)}.
     */
    @Test
    void testConvertUriCaseInvalidUri()
    {
        LXAIPChecker lxaipChecker = new LXAIPChecker();
        
        URI uri = URI.create( "file://src/test/resources/text.txt" );
        
        assertThrows( LXAIPCheckerException.class, () -> lxaipChecker.convertUri( uri ), "URI could not converted to path." );
    }
    
    /**
     * Test method for {@link de.bund.bsi.tresor.xaip.validator.signature.lxaip.LXAIPChecker#convertUri(java.net.URI)}.
     */
    @Test
    void testConvertUriCaseFileNotExists()
    {
        LXAIPChecker lxaipChecker = new LXAIPChecker();
        
        String workingDir = System.getProperty( "user.dir" );
        URI uri = URI.create( "file:///" + workingDir + "/src/test/resources/notExistingfile.extension" );
        
        assertThrows( LXAIPCheckerException.class, () -> lxaipChecker.convertUri( uri ),
                "File " + workingDir + "/src/test/resources/notExistingfile.extension does not exists." );
    }
    
    /**
     * Test method for {@link de.bund.bsi.tresor.xaip.validator.signature.lxaip.LXAIPChecker#createDataReference(java.lang.String)}.
     */
    @Test
    void testCreateDataReference()
    {
        String messageText = "error";
        
        LXAIPChecker lxaipChecker = new LXAIPChecker();
        DataReference dataReference = lxaipChecker.createDataReference( messageText );
        
        assertTrue( dataReference.getDataObjectReference().isEmpty() );
        assertEquals( DefaultResult.Major.ERROR.getURI(), dataReference.getIndividualReportType().getResult().getResultMajor() );
        assertEquals( DefaultResult.Minor.NO_DATA_ACCESS_WARNING.getURI(),
                dataReference.getIndividualReportType().getResult().getResultMinor() );
        assertEquals( messageText,
                dataReference.getIndividualReportType().getResult().getResultMessage().getValue() );
    }
    
}
