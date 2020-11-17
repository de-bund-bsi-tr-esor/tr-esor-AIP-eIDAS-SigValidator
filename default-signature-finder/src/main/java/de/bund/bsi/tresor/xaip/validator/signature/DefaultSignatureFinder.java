package de.bund.bsi.tresor.xaip.validator.signature;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toMap;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.AbstractMap;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.IntStream;

import javax.xml.bind.DataBindingException;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.IOUtils;
import org.etsi.uri._02918.v1_2.DataObjectReferenceType;
import org.w3c.dom.Node;

import de.bund.bsi.tr_esor.xaip._1.CredentialType;
import de.bund.bsi.tr_esor.xaip._1.CredentialsSectionType;
import de.bund.bsi.tr_esor.xaip._1.DataObjectType;
import de.bund.bsi.tr_esor.xaip._1.DataObjectType.BinaryData;
import de.bund.bsi.tr_esor.xaip._1.DataObjectsSectionType;
import de.bund.bsi.tr_esor.xaip._1.XAIPType;
import de.bund.bsi.tresor.xaip.validator.api.boundary.SignatureFinder;
import de.bund.bsi.tresor.xaip.validator.api.control.ModuleLogger;
import de.bund.bsi.tresor.xaip.validator.signature.checker.CAdESChecker;
import de.bund.bsi.tresor.xaip.validator.signature.checker.PAdESChecker;
import de.bund.bsi.tresor.xaip.validator.signature.checker.XAdESChecker;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;
import oasis.names.tc.dss._1_0.core.schema.AnyType;

/**
 * Implementation of the SignatureFindermodule from the XAIPValidator.
 * 
 * @author wolffs
 */
@Getter
public class DefaultSignatureFinder implements SignatureFinder
{
    private final String vendor  = "BSI";
    private final String version = "1.0.0";
    
    @Override
    public Map<DataObjectType, List<CredentialType>> findSignatures( XAIPType xaip )
    {
        Map<DataObjectType, List<CredentialType>> results = new HashMap<>();
        CredentialsSectionType credentialSection = xaip.getCredentialsSection();
        
        if ( credentialSection != null && credentialSection.getCredential() != null )
        {
            
            for ( CredentialType credential : credentialSection.getCredential() )
            {
                String cid = credential.getCredentialID();
                ModuleLogger.verbose( "checking credential " + cid );
                credential.getRelatedObjects()
                        .stream()
                        .filter( DataObjectType.class::isInstance )
                        .map( DataObjectType.class::cast )
                        .map( dataObject -> new AbstractMap.SimpleEntry<DataObjectType, List<CredentialType>>( dataObject,
                                asList( credential ) ) )
                        .forEach( entry -> results.merge( entry.getKey(), entry.getValue(), this::remap ) );
                
                if ( credential.getRelatedObjects().isEmpty() )
                {
                    results.merge( null, asList( credential ), this::remap );
                }
            }
        }
        
        return results;
    }
    
    List<CredentialType> remap( List<CredentialType> a, List<CredentialType> b )
    {
        a.addAll( b );
        return a;
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    
    enum SignaturePresence
    {
        UNKNOWN, PRESENT, MISSING;
        
        public static SignaturePresence fromBoolean( Boolean value )
        {
            return Optional.ofNullable( value )
                    .map( val -> val ? PRESENT : MISSING )
                    .orElse( UNKNOWN );
        }
    }
    
    @Value
    @AllArgsConstructor
    class FinderResult
    {
        private DataObjectType        dataObject;
        private SignaturePresence     presence;
        private Optional<InputStream> data;
    }
    
    Map<DataObjectType, List<CredentialType>> validate( XAIPType xaip )
    {
        return Optional.ofNullable( xaip.getDataObjectsSection() )
                .map( DataObjectsSectionType::getDataObject )
                .orElse( emptyList() )
                .stream()
                .map( this::findSignatures )
                .collect( toMap( Entry::getKey, Entry::getValue ) );
    }
    
    Optional<FinderResult> findSignatures( DataObjectType dataObject )
    {
        // TODO ask: xaip is invalid when both are present
        Optional<byte[]> binData = extractBinData( dataObject.getBinaryData() );
        Optional<byte[]> xmlData = extractXmlData( dataObject.getXmlData() )
                .or( () -> binData.filter( this::isXml ) );
        
        return binData.map( data -> analyzeBinData( dataObject, data ) )
                .or( () -> xmlData.map( data -> analyzeXmlData( dataObject, data ) ) );
    }
    
    FinderResult analyzeBinData( DataObjectType dataObject, byte[] data )
    {
        SignaturePresence presence = SignaturePresence
                .fromBoolean( PAdESChecker.INSTANCE.isPAdES( data ) || CAdESChecker.INSTANCE.isCAdES( data ) );
        
        return new FinderResult( dataObject, presence, Optional.of( new ByteArrayInputStream( data ) ) );
    }
    
    FinderResult analyzeXmlData( DataObjectType dataObject, byte[] data )
    {
        SignaturePresence presence = extractLxaipData( data )
                .map( d -> SignaturePresence.UNKNOWN )
                .orElseGet( () -> SignaturePresence.fromBoolean( XAdESChecker.INSTANCE.isXAdES( data ) ) );
        
        return new FinderResult( dataObject, presence, Optional.of( new ByteArrayInputStream( data ) ) );
    }
    
    boolean isXml( byte[] data )
    {
        byte[] xmlStart = "<".getBytes( StandardCharsets.UTF_8 );
        byte[] xmlEnd = ">".getBytes( StandardCharsets.UTF_8 );
        
        if ( data.length > xmlStart.length + xmlEnd.length )
        {
            boolean hasXmlStart = IntStream.range( 0, xmlStart.length ).allMatch( i -> data[i] == xmlStart[i] );
            boolean hasXmlEnd = IntStream.range( data.length - xmlEnd.length, xmlEnd.length ).allMatch( i -> data[i] == xmlEnd[i] );
            
            return hasXmlStart && hasXmlEnd;
        }
        else
        {
            return false;
        }
    }
    
    Optional<byte[]> extractXmlData( AnyType anyType )
    {
        boolean isEmpty = Optional.ofNullable( anyType )
                .map( AnyType::getAny )
                .map( List::isEmpty )
                .orElse( true );
        
        Optional<byte[]> xmlData = Optional.empty();
        if ( !isEmpty )
        {
            try
            {
                DOMResult result = new DOMResult();
                QName qName = new QName( "http://www.bsi.bund.de/tr-esor/xaip/1.2", "xmlData" );
                
                JAXBElement<AnyType> xml = new JAXBElement<AnyType>( qName, AnyType.class, anyType );
                JAXBContext context = JAXBContext.newInstance( AnyType.class );
                context.createMarshaller().marshal( xml, result );
                
                Node xmlContent = result.getNode().getFirstChild();
                
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                transformer.transform( new DOMSource( xmlContent ), new StreamResult( bos ) );
                
                xmlData = Optional.of( bos.toByteArray() );
            }
            catch ( JAXBException | TransformerException e )
            {
                ModuleLogger.verbose( "could not retrieve lxaip data from dataObject", e );
            }
        }
        
        return xmlData;
    }
    
    Optional<byte[]> extractLxaipData( byte[] xmlData )
    {
        Optional<byte[]> result = Optional.empty();
        try ( ByteArrayInputStream xmlIn = new ByteArrayInputStream( xmlData ) )
        {
            DataObjectReferenceType dataRef = JAXB.unmarshal( xmlIn, DataObjectReferenceType.class );
            
            result = Optional.of( Files.readAllBytes( Paths.get( dataRef.getURI() ) ) );
        }
        catch ( DataBindingException e )
        {
            // no lxaip
        }
        catch ( IOException e )
        {
            // could not read lxaip data
            ModuleLogger.verbose( "could not retrieve lxaip data from dataObject", e );
        }
        
        return result;
    }
    
    Optional<byte[]> extractBinData( BinaryData data )
    {
        return Optional.ofNullable( data )
                .map( BinaryData::getValue )
                .map( dh -> {
                    try
                    {
                        byte[] b64Data = IOUtils.toByteArray( dh.getInputStream() );
                        return Base64.getDecoder().decode( b64Data );
                    }
                    catch ( IOException e )
                    {
                        ModuleLogger.log( "could not extract binaryData", e );
                        return null;
                    }
                } );
    }
    
}
