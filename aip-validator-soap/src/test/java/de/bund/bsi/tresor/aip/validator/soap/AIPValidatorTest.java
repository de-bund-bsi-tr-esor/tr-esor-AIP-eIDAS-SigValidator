package de.bund.bsi.tresor.aip.validator.soap;

import static de.bund.bsi.tresor.aip.validator.soap.StreamingMatcher.matchesStream;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.mock;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBException;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.bund.bsi.tr_esor.xaip.ObjectFactory;
import de.bund.bsi.tr_esor.xaip.XAIPType;
import de.bund.bsi.tresor.aip.validator.soap.config.ServerConfig;
import oasis.names.tc.dss._1_0.core.schema.Base64Data;
import oasis.names.tc.dss._1_0.core.schema.DocumentType;
import oasis.names.tc.dss._1_0.core.schema.InlineXMLType;

/**
 * @author wolffs
 */
public class AIPValidatorTest
{
    private AIPValidator               uut;
    
    private ServerConfig               config;
    private static final ObjectFactory FACT = new ObjectFactory();
    
    @BeforeEach
    public void setup()
    {
        config = mock( ServerConfig.class );
        uut = new AIPValidator( config );
    }
    
    @Test
    @DisplayName( "should find base64Data" )
    public void findBase64DataTest()
    {
        Base64Data b64Data = new Base64Data();
        b64Data.setValue( "foo".getBytes() );
        
        DocumentType type = new DocumentType();
        type.setBase64Data( b64Data );
        
        Optional<ByteArrayInputStream> result = uut.base64Data( type );
        assertThat( result, is( not( nullValue() ) ) );
        assertThat( result.isPresent(), is( true ) );
    }
    
    @Test
    @DisplayName( "should not find any base64Data" )
    public void noBase64DataTest()
    {
        Base64Data b64Data = new Base64Data();
        
        DocumentType type = new DocumentType();
        type.setBase64Data( b64Data );
        
        Optional<ByteArrayInputStream> result = uut.base64Data( type );
        assertThat( result, is( not( nullValue() ) ) );
        assertThat( result.isPresent(), is( false ) );
    }
    
    @Test
    @DisplayName( "should find base64Xml" )
    public void findBase64XmlTest()
    {
        DocumentType type = new DocumentType();
        type.setBase64XML( "foo".getBytes() );
        
        Optional<ByteArrayInputStream> result = uut.base64Xml( type );
        assertThat( result, is( not( nullValue() ) ) );
        assertThat( result.isPresent(), is( true ) );
    }
    
    @Test
    @DisplayName( "should not find any base64Xml" )
    public void noBase64XmlTest()
    {
        DocumentType type = new DocumentType();
        
        Optional<ByteArrayInputStream> result = uut.base64Xml( type );
        assertThat( result, is( not( nullValue() ) ) );
        assertThat( result.isPresent(), is( false ) );
    }
    
    @Test
    @DisplayName( "should find inlineXml" )
    public void findInlineXmlTest()
    {
        InputStream sampleXaip = getClass().getResourceAsStream( "/samples/XAIP13-cades-det-single.xml" );
        XAIPType sample = JAXB.unmarshal( sampleXaip, XAIPType.class );
        
        InlineXMLType inlineXml = new InlineXMLType();
        inlineXml.setAny( FACT.createXAIP( sample ) );
        
        DocumentType type = new DocumentType();
        type.setInlineXML( inlineXml );
        
        Optional<ByteArrayInputStream> result = uut.inlineXml( type );
        assertThat( result, is( not( nullValue() ) ) );
        assertThat( result.isPresent(), is( true ) );
    }
    
    @Test
    @DisplayName( "should not find any inlineXml" )
    public void noInlineXmlTest()
    {
        InlineXMLType inlineXml = new InlineXMLType();
        inlineXml.setAny( "noXaip" );
        
        DocumentType type = new DocumentType();
        type.setInlineXML( inlineXml );
        
        Optional<ByteArrayInputStream> result = uut.inlineXml( type );
        assertThat( result, is( not( nullValue() ) ) );
        assertThat( result.isPresent(), is( false ) );
    }
    
    @Test
    @DisplayName( "should priorize base64Data" )
    public void priorizeTest() throws JAXBException, IOException
    {
        var data = IOUtils.toByteArray( getClass().getResourceAsStream( "/samples/XAIP13-cades-det-single.xml" ) );
        
        XAIPType sample = JAXB.unmarshal( new ByteArrayInputStream( data ), XAIPType.class );
        
        InlineXMLType inlineXml = new InlineXMLType();
        inlineXml.setAny( FACT.createXAIP( sample ) );
        
        Base64Data b64Data = new Base64Data();
        b64Data.setValue( data );
        
        DocumentType type = new DocumentType();
        type.setBase64XML( "base64Xml".getBytes() );
        type.setBase64Data( b64Data );
        
        List<ByteArrayInputStream> result = uut.findXAIPCandidate( List.of( type ) );
        assertThat( result, is( not( nullValue() ) ) );
        assertThat( result.isEmpty(), is( false ) );
        assertThat( result.size(), is( equalTo( 1 ) ) );
        assertThat( result, hasItem( matchesStream( data ) ) );
    }
}