package de.bund.bsi.tresor.xaip.validator.signature;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * @author bendlera
 *
 */
class DefaultSignatureFinderTest
{
    
    // private static byte[] signedPdfFile;
    //
    // /**
    // * Loads test data.
    // *
    // * @throws java.lang.Exception
    // * loading fails
    // */
    // @BeforeAll
    // static void setUpBeforeClass() throws Exception
    // {
    // signedPdfFile = Files
    // .readAllBytes( Paths.get( DefaultSignatureFinderTest.class.getResource( "/signed.pdf" ).toURI() ) );
    // }
    //
    // /**
    // * Test method for
    // * {@link de.bund.bsi.tresor.xaip.validator.signature.DefaultSignatureFinder#findSignatures(de.bund.bsi.tr_esor.xaip._1.XAIPType)}.
    // */
    // @Test
    // void testFindSignatures()
    // {
    // String credentialsSectionId = "01234";
    // String dataObjectsSectionId = "56789";
    //
    // ByteArrayOutputStream loggerOutput = new ByteArrayOutputStream();
    // ModuleLogger.initConfig( true, loggerOutput );
    //
    // DataObjectsSectionType dataObjectsSectionType = null;
    // try
    // {
    // dataObjectsSectionType = createDataObjectsSectionType( dataObjectsSectionId,
    // Optional.ofNullable( Paths.get( DefaultSignatureFinderTest.class.getResource( "/signed.pdf" ).toURI() ).toFile() ) );
    // }
    // catch ( URISyntaxException e )
    // {
    // fail( "data could not loaded", e );
    // }
    //
    // XAIPType xaipType = new XAIPType();
    // xaipType.setCredentialsSection( createCredentialsSectionType( credentialsSectionId ) );
    // xaipType.setDataObjectsSection( dataObjectsSectionType );
    //
    // DefaultSignatureFinder defaultSignatureFinder = new DefaultSignatureFinder();
    //
    // List<SignatureObject> signatures = defaultSignatureFinder.findSignatures( xaipType );
    //
    // assertEquals( 2, signatures.size() );
    // assertEquals( credentialsSectionId, signatures.get( 0 ).getSignature().getId() );
    // assertArrayEquals( signedPdfFile, (byte[]) signatures.get( 1 ).getSignaturePtr().getWhichDocument() );
    // }
    //
    // /**
    // * Test method for
    // * {@link
    // de.bund.bsi.tresor.xaip.validator.signature.DefaultSignatureFinder#fromCredentialSection(de.bund.bsi.tr_esor.xaip._1.CredentialsSectionType)}.
    // */
    // @Test
    // void testFromCredentialSection()
    // {
    // String signatureId = "12345";
    // Timestamp timestamp = new Timestamp();
    // timestamp.setRFC3161TimeStampToken( "timestamp".getBytes() );
    //
    // DefaultSignatureFinder defaultSignatureFinder = new DefaultSignatureFinder();
    //
    // CredentialsSectionType credentialsSectionType = createCredentialsSectionType( signatureId );
    // credentialsSectionType.getCredential().add( createCredentialTypeWithTimestamp( timestamp ) );
    //
    // List<SignatureObject> result = defaultSignatureFinder
    // .fromCredentialSection( credentialsSectionType );
    //
    // assertEquals( 2, result.size() );
    // assertEquals( signatureId, result.get( 0 ).getSignature().getId() );
    // assertEquals( timestamp, result.get( 1 ).getTimestamp() );
    // }
    //
    // /**
    // * Creates {@link CredentialsSectionType}.
    // *
    // * @param signatureId
    // * SignatureType-ID
    // * @return new instance
    // */
    // private CredentialsSectionType createCredentialsSectionType( String signatureId )
    // {
    // SignatureType signatureType = new SignatureType();
    // signatureType.setId( signatureId );
    // SignatureObject signatureObject = new SignatureObject();
    // signatureObject.setSignature( signatureType );
    // CredentialType signatureCredentialType = new CredentialType();
    // signatureCredentialType.setSignatureObject( signatureObject );
    //
    // CredentialsSectionType credentialsSectionType = new CredentialsSectionType();
    // credentialsSectionType.getCredential().add( signatureCredentialType );
    //
    // return credentialsSectionType;
    // }
    //
    // /**
    // * Creates {@link CredentialType} with {@link Timestamp} .
    // *
    // * @param timestamp
    // * timestamp
    // * @return new instance
    // */
    // private CredentialType createCredentialTypeWithTimestamp( Timestamp timestamp )
    // {
    // SignatureObject timestampSignatureObject = new SignatureObject();
    // timestampSignatureObject.setTimestamp( timestamp );
    // CredentialType timestampCredentialType = new CredentialType();
    // timestampCredentialType.setSignatureObject( timestampSignatureObject );
    //
    // return timestampCredentialType;
    // }
    //
    // /**
    // * Test method for
    // * {@link
    // de.bund.bsi.tresor.xaip.validator.signature.DefaultSignatureFinder#fromDataObjectsSection(de.bund.bsi.tr_esor.xaip._1.DataObjectsSectionType)}.
    // */
    // @Test
    // void testFromDataObjectsSection()
    // {
    // String dataObjectId = "12345";
    //
    // ByteArrayOutputStream loggerOutput = new ByteArrayOutputStream();
    // ModuleLogger.initConfig( true, loggerOutput );
    //
    // DefaultSignatureFinder defaultSignatureFinder = new DefaultSignatureFinder();
    //
    // assertEquals( Collections.emptyList(), defaultSignatureFinder.fromDataObjectsSection( null, null ) );
    //
    // assertEquals( Collections.emptyList(), defaultSignatureFinder.fromDataObjectsSection( new DataObjectsSectionType(), null ) );
    //
    // DataObjectsSectionType dataObjectsSectionType = new DataObjectsSectionType();
    // try
    // {
    // dataObjectsSectionType = createDataObjectsSectionType( dataObjectId,
    // Optional.ofNullable( Paths.get( DefaultSignatureFinderTest.class.getResource( "/text.txt" ).toURI() ).toFile() ) );
    // }
    // catch ( URISyntaxException e )
    // {
    // fail( "test data could not loaded", e );
    // }
    //
    // assertEquals( 1, defaultSignatureFinder.fromDataObjectsSection( dataObjectsSectionType, null ).size() );
    //
    // try
    // {
    // dataObjectsSectionType = createDataObjectsSectionType( dataObjectId,
    // Optional.ofNullable( Paths.get( DefaultSignatureFinderTest.class.getResource( "/signed.pdf" ).toURI() ).toFile() ) );
    // }
    // catch ( URISyntaxException e )
    // {
    // fail( "data could not loaded", e );
    // }
    //
    // assertEquals( 1, defaultSignatureFinder.fromDataObjectsSection( dataObjectsSectionType, null ).size() );
    // }
    //
    // /**
    // * Creates {@link DataObjectsSectionType}.
    // *
    // * @param dataObjectId
    // * id
    // * @param data
    // * data
    // * @return new instance
    // */
    // private DataObjectsSectionType createDataObjectsSectionType( String dataObjectId, Optional<File> data )
    // {
    // DataObjectsSectionType dataObjectsSectionType = new DataObjectsSectionType();
    //
    // BinaryData signedPdf = new BinaryData();
    //
    // if ( data.isPresent() )
    // {
    // signedPdf.setMimeType( "application/octet-stream" );
    // signedPdf.setValue( new DataHandler( new FileDataSource( data.get() ) ) );
    // }
    //
    // DataObjectType dataObjectType;
    // dataObjectType = new DataObjectType();
    // dataObjectType.setDataObjectID( dataObjectId );
    // dataObjectType.setBinaryData( signedPdf );
    //
    // dataObjectsSectionType.getDataObject().add( dataObjectType );
    //
    // return dataObjectsSectionType;
    // }
    //
    // /**
    // * Test method for {@link de.bund.bsi.tresor.xaip.validator.signature.DefaultSignatureFinder#dataToSignatureObject(byte[])}.
    // */
    // @Test
    // void testConvert()
    // {
    // byte[] testData = "data".getBytes();
    // DefaultSignatureFinder defaultSignatureFinder = new DefaultSignatureFinder();
    // SignatureObject signatureObject = defaultSignatureFinder.dataToSignatureObject( testData );
    //
    // assertEquals( testData, signatureObject.getSignaturePtr().getWhichDocument() );
    // }
    //
    // /**
    // * Test method for {@link de.bund.bsi.tresor.xaip.validator.signature.DefaultSignatureFinder#asData(javax.activation.DataHandler)}.
    // */
    // @Test
    // void testAsData()
    // {
    // DataHandler dataHandler = null;
    // try
    // {
    // dataHandler = new DataHandler( new FileDataSource(
    // Paths.get( DefaultSignatureFinderTest.class.getResource( "/text.txt" ).toURI() ).toFile() ) );
    // }
    // catch ( URISyntaxException e )
    // {
    // fail( "data could not loaded", e );
    // }
    //
    // DefaultSignatureFinder defaultSignatureFinder = new DefaultSignatureFinder();
    //
    // assertArrayEquals( "content".getBytes(), defaultSignatureFinder.asData( dataHandler ) );
    // }
    
    /**
     * Test method for {@link de.bund.bsi.tresor.xaip.validator.signature.DefaultSignatureFinder#getVendor()}.
     */
    @Test
    void testGetVendor()
    {
        assertEquals( "BSI", new DefaultSignatureFinder().getVendor() );
    }
    
    /**
     * Test method for {@link de.bund.bsi.tresor.xaip.validator.signature.DefaultSignatureFinder#getVersion()}.
     */
    @Test
    void testGetVersion()
    {
        assertEquals( "1.0.0", new DefaultSignatureFinder().getVersion() );
    }
    
}
