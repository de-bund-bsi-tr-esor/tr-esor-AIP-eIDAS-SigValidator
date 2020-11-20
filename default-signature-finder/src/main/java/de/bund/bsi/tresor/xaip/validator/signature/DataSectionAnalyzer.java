package de.bund.bsi.tresor.xaip.validator.signature;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
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

import de.bund.bsi.tr_esor.xaip._1.DataObjectType;
import de.bund.bsi.tr_esor.xaip._1.DataObjectType.BinaryData;
import de.bund.bsi.tresor.xaip.validator.api.control.ModuleLogger;
import de.bund.bsi.tresor.xaip.validator.signature.checker.CAdESChecker;
import de.bund.bsi.tresor.xaip.validator.signature.checker.PAdESChecker;
import de.bund.bsi.tresor.xaip.validator.signature.checker.XAdESChecker;
import de.bund.bsi.tresor.xaip.validator.signature.entity.FinderResult;
import de.bund.bsi.tresor.xaip.validator.signature.entity.SignaturePresence;
import oasis.names.tc.dss._1_0.core.schema.AnyType;

/**
 * @author wolffs
 */
public class DataSectionAnalyzer
{
    static public Optional<FinderResult> findSignatures( DataObjectType dataObject, Optional<byte[]> document )
    {
        return document.map( data -> isXml( data ) ? analyzeXmlData( dataObject, data ) : analyzeBinData( dataObject, data ) );
    }
    
    static public Optional<FinderResult> findSignatures( DataObjectType dataObject )
    {
        // TODO ask: xaip is invalid when both are present
        Optional<byte[]> binData = extractBinData( dataObject.getBinaryData() );
        Optional<byte[]> xmlData = extractXmlData( dataObject.getXmlData() )
                .or( () -> binData.filter( DataSectionAnalyzer::isXml ) );
        
        return binData.map( data -> analyzeBinData( dataObject, data ) )
                .or( () -> xmlData.map( data -> analyzeXmlData( dataObject, data ) ) );
    }
    
    static boolean isXml( byte[] data )
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
    
    static FinderResult analyzeBinData( DataObjectType dataObject, byte[] data )
    {
        SignaturePresence presence = SignaturePresence
                .fromBoolean( PAdESChecker.INSTANCE.isPAdES( data ) || CAdESChecker.INSTANCE.isCAdES( data ) );
        
        return new FinderResult( dataObject, presence, new ByteArrayInputStream( data ) );
    }
    
    static FinderResult analyzeXmlData( DataObjectType dataObject, byte[] data )
    {
        SignaturePresence presence = extractLxaipData( data )
                .map( d -> SignaturePresence.UNKNOWN )
                .orElseGet( () -> SignaturePresence.fromBoolean( XAdESChecker.INSTANCE.isXAdES( data ) ) );
        
        return new FinderResult( dataObject, presence, new ByteArrayInputStream( data ) );
    }
    
    static Optional<byte[]> extractXmlData( AnyType anyType )
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
    
    static Optional<byte[]> extractLxaipData( byte[] xmlData )
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
    
    static Optional<byte[]> extractBinData( BinaryData data )
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
