/*-
 * Copyright (c) 2020
 * Federal Office for Information Security (BSI),
 * Godesberger Allee 185-189,
 * 53175 Bonn, Germany,
 * phone: +49 228 99 9582-0,
 * fax: +49 228 99 9582-5400,
 * e-mail: bsi@bsi.bund.de
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.bund.bsi.tresor.aip.validator.api.control;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.activation.DataHandler;
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
import org.apache.commons.lang3.StringUtils;
import org.etsi.uri._02918.v1_2.DataObjectReferenceType;
import org.w3c.dom.Node;

import de.bund.bsi.tr_esor.vr.XAIPValidityType;
import de.bund.bsi.tr_esor.xaip.BinaryDataType;
import de.bund.bsi.tr_esor.xaip.BinaryMetaDataType;
import de.bund.bsi.tr_esor.xaip.CredentialType;
import de.bund.bsi.tr_esor.xaip.DataObjectType;
import de.bund.bsi.tr_esor.xaip.DataObjectsSectionType;
import de.bund.bsi.tr_esor.xaip.MetaDataObjectType;
import de.bund.bsi.tr_esor.xaip.PackageHeaderType;
import de.bund.bsi.tr_esor.xaip.PackageInfoUnitType;
import de.bund.bsi.tr_esor.xaip.VersionManifestType;
import de.bund.bsi.tr_esor.xaip.XAIPType;
import oasis.names.tc.dss._1_0.core.schema.AnyType;

/**
 * Util class for XAIP operations
 * 
 * @author wolffs
 */
public class AIPUtil
{
    /**
     * Namespace and name of the XAIP element
     */
    public static final QName XML_DATA_QNAME    = new QName( "http://www.bsi.bund.de/tr-esor/xaip", "xmlData" );
    public static final QName XML_ANY_QNAME     = new QName( "oasis.names.tc.dss._1_0.core.schema.AnyType", "xmlAny" );
    
    public static final QName XAIP_QNAME        = new QName( "http://www.bsi.bund.de/tr-esor/xaip", "XAIP" );
    public static final QName XAIP_REPORT_QNAME = new QName( "http://www.bsi.bund.de/tr-esor/vr", "XAIPReport" );
    
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
     * Searching the aoid of the xaip
     * 
     * @param xaip
     *            the xaip
     * @return the aoid
     */
    public static Optional<String> findAoid( XAIPType xaip )
    {
        return Optional.ofNullable( xaip )
                .map( XAIPType::getPackageHeader )
                .map( PackageHeaderType::getAOID );
    }
    
    /**
     * Searching for any {@link DataObjectReferenceType} which is being used in lxaip's
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
     * Searching for a {@link DataObjectType} by a dataObjectId
     * 
     * @param dataSection
     *            the dataObjectsSection
     * @param dataObjectId
     *            the dataObjectId
     * @return the dataObject if found or an empty optional
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
     * @param <R>
     *            the dataType of the section
     * @param <T>
     *            the section type
     * @param section
     *            section containing the data
     * @param sectionType
     *            function to extract the dataType from the section, e.g. DataObjectsSection::getDataObject
     * @param relatedObjects
     *            the relatedObjects
     * @return a set of resolved objects from the section
     */
    public static <R, T> Set<R> resolveRelatedDataObjects( T section, Function<T, Collection<R>> sectionType,
            List<Object> relatedObjects )
    {
        Set<String> ids = relatedObjects.stream()
                .map( AIPUtil::idFromObject )
                .filter( StringUtils::isNotBlank )
                .collect( toSet() );
        
        return Optional.ofNullable( section )
                .map( sectionType::apply )
                .orElse( new ArrayList<>() )
                .stream()
                .filter( obj -> ids.contains( AIPUtil.idFromObject( obj ) ) )
                .collect( toSet() );
    }
    
    /**
     * Retrieving the id of the respective XAIP-Component (e.g. {@link CredentialType} or {@link MetaDataObjectType})
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
     * Returns an xPath pointing to the element where the id matches.<br/>
     * <br/>
     * Detailed explaination:<br/>
     * Selecting every element which has an attributeName ending with 'ID' matching the provided id value.<br/>
     * 
     * @param id
     *            the *objectId
     * @return the xPath
     */
    public static String xPathForObjectId( String id )
    {
        // select every element which has an attributeName ending with 'ID' matching the provided value
        return "//*[@*[ends-with(local-name(), 'ID')]='" + id + "']";
    }
    
    /**
     * Extracting the complete xml data provided by the {@link AnyType} which can contain any data or none. The data is not canonicalized
     * and will not be returned with the same bytes which are inside the actual xmlData element since it will go through a dom parser
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
                JAXBContext context = JAXBContext.newInstance( AnyType.class, DataObjectReferenceType.class );
                context.createMarshaller().marshal( xml, result );
                
                Node xmlContent = result.getNode()
                        .getFirstChild() // removes <anyType>
                        .getFirstChild();// removes <xmlData>
                
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
     * @param dataHandler
     *            the binary dataHandler
     * @return the binary data content if present
     */
    public static Optional<byte[]> extractBinData( DataHandler dataHandler )
    {
        return Optional.ofNullable( dataHandler )
                .map( dh -> {
                    try
                    {
                        return IOUtils.toByteArray( dh.getInputStream() );
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
     * <li>xmlData as b64 data
     * <li>lxaipData as b64 data
     * </ul>
     * <li>XmlData
     * <ul>
     * <li>lxaipData
     * </ul>
     * </ul>
     * 
     * @param binarySupplier
     *            supplier for the binaryData of an objectType
     * @param xmlSupplier
     *            supplier for the xmData of an objectType
     * @return the optional data
     */
    public static Optional<byte[]> extractData( Supplier<DataHandler> binarySupplier, Supplier<AnyType> xmlSupplier )
    {
        Optional<byte[]> binData = AIPUtil.extractBinData( binarySupplier.get() );
        Optional<byte[]> xmlData = AIPUtil.extractXmlData( xmlSupplier.get() )
                .or( () -> binData.filter( AIPUtil::isXml ) );
        
        return binData.or( () -> xmlData );
    }
    
    public static <T> Optional<byte[]> dataSupplier( T anyDataObj )
    {
        Optional<byte[]> data = Optional.empty();
        if ( anyDataObj instanceof MetaDataObjectType )
        {
            MetaDataObjectType metaData = (MetaDataObjectType) anyDataObj;
            data = AIPUtil.extractData( AIPUtil.binaryDataSupplier( metaData ), metaData::getXmlMetaData );
        }
        else if ( anyDataObj instanceof DataObjectType )
        {
            DataObjectType dataObj = (DataObjectType) anyDataObj;
            data = AIPUtil.extractData( AIPUtil.binaryDataSupplier( dataObj ), dataObj::getXmlData );
        }
        
        return data;
    }
    
    /**
     * Providing a supplier for the binaryData of a {@link MetaDataObjectType}
     * 
     * @param metaDataObject
     *            the metaDataObject
     * @return the supplier
     */
    public static Supplier<DataHandler> binaryDataSupplier( MetaDataObjectType metaDataObject )
    {
        return () -> Optional.ofNullable( metaDataObject.getBinaryMetaData() )
                .map( BinaryMetaDataType::getValue )
                .orElse( null );
    }
    
    /**
     * Providing a supplier for the binaryData of a {@link DataObjectType}
     * 
     * @param dataObject
     *            the dataObject
     * @return the supplier
     */
    public static Supplier<DataHandler> binaryDataSupplier( DataObjectType dataObject )
    {
        return () -> Optional.ofNullable( dataObject.getBinaryData() )
                .map( BinaryDataType::getValue )
                .orElse( null );
    }
    
    /**
     * Mapping the oids by their version
     * 
     * @param xaip
     *            the xaip
     * @return the map
     */
    public static Map<String, Set<String>> oidsByVersion( XAIPType xaip )
    {
        Map<String, Set<String>> oidsByVersion = new HashMap<>();
        
        Optional.ofNullable( xaip )
                .map( XAIPType::getPackageHeader )
                .map( PackageHeaderType::getVersionManifest )
                .orElseThrow( () -> new IllegalStateException( "missing versions in xaip" ) )
                .stream()
                .forEach( manifest -> {
                    String version = manifest.getVersionID();
                    Set<String> oids = manifest.getPackageInfoUnit().stream()
                            .flatMap( info -> {
                                return Stream.concat(
                                        info.getProtectedObjectPointer().stream(),
                                        info.getUnprotectedObjectPointer().stream() )
                                        .map( JAXBElement::getValue )
                                        .filter( DataObjectType.class::isInstance )
                                        .map( DataObjectType.class::cast )
                                        .map( DataObjectType::getDataObjectID );
                                
                            } )
                            .collect( toSet() );
                            
                    oidsByVersion.put( version, oids );
                } );
        
        return oidsByVersion;
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
    
    public static List<JAXBElement<Object>> versionPointer( VersionManifestType version )
    {
        return Optional.ofNullable( version.getPackageInfoUnit() ).orElse( new ArrayList<>() ).stream()
                .flatMap( e -> {
                    List<JAXBElement<Object>> list = new ArrayList<>();
                    Optional.ofNullable( e.getProtectedObjectPointer() ).ifPresent( list::addAll );
                    Optional.ofNullable( e.getUnprotectedObjectPointer() ).ifPresent( list::addAll );
                    
                    return list.stream();
                } ).collect( toList() );
        
    }
    
}
