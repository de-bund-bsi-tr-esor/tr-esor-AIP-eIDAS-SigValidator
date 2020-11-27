package de.bund.bsi.tresor.xaip.validator.api.control;

import static java.util.stream.Collectors.toSet;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
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

import de.bund.bsi.tr_esor.vr._1.XAIPValidityType;
import de.bund.bsi.tr_esor.xaip._1.CredentialType;
import de.bund.bsi.tr_esor.xaip._1.DataObjectType;
import de.bund.bsi.tr_esor.xaip._1.DataObjectType.BinaryData;
import de.bund.bsi.tr_esor.xaip._1.DataObjectsSectionType;
import de.bund.bsi.tr_esor.xaip._1.MetaDataObjectType;
import de.bund.bsi.tr_esor.xaip._1.PackageHeaderType;
import de.bund.bsi.tr_esor.xaip._1.PackageInfoUnitType;
import de.bund.bsi.tr_esor.xaip._1.VersionManifestType;
import de.bund.bsi.tr_esor.xaip._1.XAIPType;
import oasis.names.tc.dss._1_0.core.schema.AnyType;

/**
 * Util class for XAIP
 * 
 * @author wolffs
 */
public class XAIPUtil
{
    /**
     * Namespace and name of the XAIP element
     */
    public static final QName XML_DATA_QNAME    = new QName( "http://www.bsi.bund.de/tr-esor/xaip/1.2", "xmlData" );
    public static final QName XML_ANY_QNAME     = new QName( "oasis.names.tc.dss._1_0.core.schema.AnyType", "xmlAny" );
    
    public static final QName XAIP_QNAME        = new QName( "http://www.bsi.bund.de/tr-esor/xaip/1.2", "XAIP" );
    public static final QName XAIP_REPORT_QNAME = new QName( "http://www.bsi.bund.de/tr-esor/vr/1.2", "XAIPReport" );
    
    /**
     * Creating a proper JAXBElement of the xaipType which can be used for marshalling since the generated wsdl types do not contain a
     * <code>@XMLRootElement</code> annotation.
     * 
     * @param xaip
     *            the xaip
     * @return the jaxbElement
     */
    public static JAXBElement<XAIPType> asElement( XAIPType xaip )
    {
        return new JAXBElement<XAIPType>( XAIP_QNAME, XAIPType.class, xaip );
    }
    
    /**
     * Creating a proper JAXBElement of the xaipType which can be used for marshalling since the generated wsdl types do not contain a
     * <code>@XMLRootElement</code> annotation.
     * 
     * @param xaipReport
     *            the xaipReport
     * @return the jaxbElement
     */
    public static JAXBElement<XAIPValidityType> asElement( XAIPValidityType xaipReport )
    {
        return new JAXBElement<XAIPValidityType>( XAIP_REPORT_QNAME, XAIPValidityType.class, xaipReport );
    }
    
    /**
     * Searching for any {@link DataObjectReferenceType} which is present on lxaip's
     * 
     * @param dataObject
     *            the dataObject
     * @return the optional dataObjectReference
     */
    public static Optional<DataObjectReferenceType> findDataReferences( DataObjectType dataObject )
    {
        return Optional.ofNullable( dataObject )
                .map( DataObjectType::getXmlData )
                .map( AnyType::getAny )
                .stream()
                .flatMap( List::stream )
                .filter( JAXBElement.class::isInstance )
                .map( JAXBElement.class::cast )
                .map( JAXBElement::getValue )
                .filter( DataObjectReferenceType.class::isInstance )
                .map( DataObjectReferenceType.class::cast )
                .findAny();
    }
    
    /**
     * Searching for an {@link DataObjectType} by a dataObjectId
     * 
     * @param dataSection
     *            the dataObjectsSection
     * @param dataObjectId
     *            the dataObjectId
     * @return the dataObject if found
     */
    public static Optional<DataObjectType> findDataObjectById( DataObjectsSectionType dataSection, String dataObjectId )
    {
        return Optional.ofNullable( dataSection )
                .map( DataObjectsSectionType::getDataObject )
                .orElse( new ArrayList<>() )
                .stream()
                .filter( obj -> obj.getDataObjectID().equals( dataObjectId ) )
                .findAny();
    }
    
    /**
     * Resolving the relatedObjects and returning the resolved dataObjects
     * 
     * @param dataSection
     *            the dataSection containing the dataObjects
     * @param relatedObjects
     *            the relatedObjects
     * @return a set of resolved dataObjects
     */
    public static Set<DataObjectType> resolveRelatedDataObjects( DataObjectsSectionType dataSection, List<Object> relatedObjects )
    {
        Set<String> ids = relatedObjects.stream()
                .map( ref -> {
                    if ( ref instanceof DataObjectType )
                    {
                        return ((DataObjectType) ref).getDataObjectID();
                    }
                    else if ( ref instanceof String )
                    {
                        return (String) ref;
                    }
                    
                    return null;
                } )
                .filter( Objects::nonNull )
                .collect( toSet() );
        
        return Optional.ofNullable( dataSection )
                .map( DataObjectsSectionType::getDataObject )
                .orElse( new ArrayList<>() )
                .stream()
                .filter( obj -> ids.contains( obj.getDataObjectID() ) )
                .collect( toSet() );
    }
    
    /**
     * Retrieving the id of the respective object
     * 
     * @param object
     *            the object
     * @return the id of the object
     */
    public static String idFromObject( Object object )
    {
        if ( object instanceof String )
        {
            return (String) object;
        }
        else if ( object instanceof DataObjectType )
        {
            return ((DataObjectType) object).getDataObjectID();
        }
        else if ( object instanceof CredentialType )
        {
            return ((CredentialType) object).getCredentialID();
        }
        else if ( object instanceof PackageHeaderType )
        {
            return ((PackageHeaderType) object).getPackageID();
        }
        else if ( object instanceof PackageInfoUnitType )
        {
            return ((PackageInfoUnitType) object).getPackageUnitID();
        }
        else if ( object instanceof VersionManifestType )
        {
            return ((VersionManifestType) object).getVersionID();
        }
        else if ( object instanceof MetaDataObjectType )
        {
            return ((MetaDataObjectType) object).getMetaDataID();
        }
        
        ModuleLogger.verbose( "could not find id of object " + object.getClass() );
        
        return null;
    }
    
    /**
     * Extracting the complete xml data provided by the anyType which can contain any data or not
     * 
     * @param anyType
     *            anyType from the dataObjectType containing any xmlData
     * @return the xml content
     */
    public static Optional<byte[]> extractXmlData( AnyType anyType )
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
                JAXBElement<AnyType> xml = new JAXBElement<AnyType>( XML_DATA_QNAME, AnyType.class, anyType );
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
    
    /**
     * Checking if the provided xmlData is an lxaip reference and extracting the referenced data if any were found.
     * 
     * @param xmlData
     *            the xmlData to check if the data contains an lxaip reference
     * @return the lxaip content if any lxaip reference was found
     */
    public static Optional<byte[]> extractLxaipData( byte[] xmlData )
    {
        Optional<byte[]> result = Optional.empty();
        try ( ByteArrayInputStream xmlIn = new ByteArrayInputStream( xmlData ) )
        {
            DataObjectReferenceType dataRef = JAXB.unmarshal( xmlIn, DataObjectReferenceType.class );
            Optional<Path> optPath = Optional.ofNullable( dataRef.getURI() ).map( Paths::get );
            
            if ( optPath.isPresent() )
            {
                result = Optional.of( Files.readAllBytes( optPath.get() ) );
            }
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
    
    /**
     * Extracting the content of the binary data if any data is present
     * 
     * @param data
     *            the binary data
     * @return the binary data content if present
     * @throws IllegalArgumentException
     *             when content is not base64 encoded
     */
    public static Optional<byte[]> extractBinData( BinaryData data )
    {
        return Optional.ofNullable( data )
                .map( BinaryData::getValue )
                .map( dh -> {
                    try
                    {
                        return IOUtils.toByteArray( dh.getInputStream() );
                        // return Base64.getDecoder().decode( b64Data );
                    }
                    catch ( IOException e )
                    {
                        ModuleLogger.log( "could not extract binaryData", e );
                        return null;
                    }
                } );
    }
    
    /**
     * Extracting the data from a dataObject. The data source can be any of:<br/>
     * <ul>
     * <li>BinaryData
     * <ul>
     * <li>xmlData
     * <li>lxaipData
     * </ul>
     * <li>XmlData
     * <ul>
     * <li>lxaipData
     * </ul>
     * </ul>
     * 
     * @param dataObject
     *            the dataObject to retrieve the data from
     * @return data from the dataObject if any is present
     */
    public static Optional<byte[]> extractData( DataObjectType dataObject )
    {
        Optional<byte[]> binData = XAIPUtil.extractBinData( dataObject.getBinaryData() );
        Optional<byte[]> xmlData = XAIPUtil.extractXmlData( dataObject.getXmlData() )
                .or( () -> binData.filter( XAIPUtil::isXml ) );
        
        return binData.or( () -> xmlData );
    }
    
    /**
     * Checking if the binary data is xml data
     * 
     * @param data
     *            the binary data to check
     * @return if the binary data is xml data
     */
    public static boolean isXml( byte[] data )
    {
        // TODO init sax parser instead
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
}
