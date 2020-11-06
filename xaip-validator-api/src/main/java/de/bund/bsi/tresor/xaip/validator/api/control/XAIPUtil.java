package de.bund.bsi.tresor.xaip.validator.api.control;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import org.etsi.uri._02918.v1_2.DataObjectReferenceType;

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
        
        ModuleLogger.verbose( "could id of object " + object.getClass() );
        
        return null;
    }
    
    /**
     * Returns the inputStream of a normal dataObject containing the document content
     * 
     * @param dataObject
     *            the dataObject
     * @return inputstream with content or empty data
     */
    public static InputStream retrieveContent( DataObjectType dataObject )
    {
        return Optional.ofNullable( dataObject )
                .map( DataObjectType::getBinaryData )
                .map( BinaryData::getValue )
                .map( t -> {
                    try
                    {
                        return t.getInputStream();
                    }
                    catch ( IOException e )
                    {
                        ModuleLogger.verbose( "could not retrieve data from dataObject", e );
                    }
                    
                    return new ByteArrayInputStream( new byte[0] );
                } )
                .orElse( findLxaipData( dataObject ) );
    }
    
    /**
     * Returns the inputStream of an lxaip dataObject which is being attached externally and therefore not obtainable by using the
     * binaryData object.
     * 
     * @param dataObject
     *            the dataObject
     * @return inputstream with content or empty data
     */
    static InputStream findLxaipData( DataObjectType dataObject )
    {
        return findDataReferences( dataObject )
                .map( DataObjectReferenceType::getURI )
                .map( Paths::get )
                .map( path -> {
                    try
                    {
                        return new FileInputStream( path.toFile() );
                    }
                    catch ( IOException e )
                    {
                        ModuleLogger.verbose( "could not retrieve lxaip data from dataObject", e );
                    }
                    
                    return new ByteArrayInputStream( new byte[0] );
                } )
                .orElse( new ByteArrayInputStream( new byte[0] ) );
    }
}