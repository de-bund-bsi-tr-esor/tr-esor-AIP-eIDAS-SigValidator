package de.bund.bsi.tresor.xaip.validator.signature;

import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import javax.activation.DataHandler;
import javax.xml.bind.JAXBElement;

import org.etsi.uri._02918.v1_2.DataObjectReferenceType;

import de.bund.bsi.tr_esor.xaip._1.CredentialType;
import de.bund.bsi.tr_esor.xaip._1.CredentialsSectionType;
import de.bund.bsi.tr_esor.xaip._1.DataObjectType;
import de.bund.bsi.tr_esor.xaip._1.DataObjectType.BinaryData;
import de.bund.bsi.tr_esor.xaip._1.DataObjectsSectionType;
import de.bund.bsi.tr_esor.xaip._1.XAIPType;
import de.bund.bsi.tresor.xaip.validator.api.boundary.SignatureFinder;
import de.bund.bsi.tresor.xaip.validator.api.control.ModuleLogger;
import de.bund.bsi.tresor.xaip.validator.signature.checker.LXAIPChecker;
import de.bund.bsi.tresor.xaip.validator.signature.entity.DataReference;
import lombok.Getter;
import oasis.names.tc.dss._1_0.core.schema.AnyType;
import oasis.names.tc.dss._1_0.core.schema.SignatureObject;
import oasis.names.tc.dss._1_0.core.schema.SignaturePtr;
import oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_.IndividualReportType;

/**
 * Implementation of the SignatureFindermodule from the XAIPValidator.
 * 
 * @author wolffs
 * @author bendlera
 */
@Getter
public class DefaultSignatureFinder implements SignatureFinder
{
    private final String                               vendor              = "BSI";
    private final String                               version             = "1.0.0";
    
    private final Map<String, DataObjectReferenceType> foundDataReferences = new LinkedHashMap<>();
    
    @Override
    public List<SignatureObject> findSignatures( XAIPType xaip )
    {
        List<SignatureObject> credentialSection = fromCredentialSection( xaip.getCredentialsSection() );
        List<SignatureObject> dataObjectsSection = fromDataObjectsSection( xaip.getDataObjectsSection(), foundDataReferences );
        
        List<SignatureObject> resultList = new ArrayList<>();
        resultList.addAll( credentialSection );
        resultList.addAll( dataObjectsSection );
        
        return resultList;
    }
    
    @Override
    public List<IndividualReportType> verifyDataReference( XAIPType xaip )
    {
        List<IndividualReportType> results = new LinkedList<>();
        if ( xaip.getDataObjectsSection() != null && !xaip.getDataObjectsSection().getDataObject().isEmpty() )
        {
            Map<DataObjectType, DataObjectReferenceType> dataReferences = findDataReferences( xaip.getDataObjectsSection() );
            for ( Iterator<Map.Entry<DataObjectType, DataObjectReferenceType>> iterator = dataReferences.entrySet().iterator(); iterator
                    .hasNext(); )
            {
                Entry<DataObjectType, DataObjectReferenceType> entry = iterator.next();
                
                DataReference dataReference = LXAIPChecker.INSTANCE.verify( entry.getKey().getDataObjectID(), entry.getValue() );
                results.add( dataReference.getIndividualReportType() );
                
                if ( dataReference.getDataObjectReference().isPresent() )
                {
                    foundDataReferences.put( entry.getKey().getDataObjectID(), dataReference.getDataObjectReference().get() );
                }
            }
        }
        
        return results;
    }
    
    /**
     * Selecting the signatures from the XAIP-CredentialsSection.
     * 
     * @param credentialsSection
     *            the credentialsSection
     * @return the signature objects
     */
    List<SignatureObject> fromCredentialSection( CredentialsSectionType credentialsSection )
    {
        return Optional.ofNullable( credentialsSection )
                .map( CredentialsSectionType::getCredential )
                .orElseGet( ArrayList::new )
                .stream()
                .map( CredentialType::getSignatureObject )
                .collect( toList() );
    }
    
    /**
     * Selecting data from the DataObjectsSection.
     * 
     * @param dataSection
     *            the dataSection
     * @param dataReferences
     *            dataReferences
     * @return the data
     */
    List<SignatureObject> fromDataObjectsSection( DataObjectsSectionType dataSection, Map<String, DataObjectReferenceType> dataReferences )
    {
        List<SignatureObject> results = new ArrayList<>();
        
        if ( dataSection != null && dataSection.getDataObject() != null )
        {
            for ( DataObjectType dataObject : dataSection.getDataObject() )
            {
                String id = dataObject.getDataObjectID();
                byte[] data = Optional.ofNullable( dataObject.getBinaryData() )
                        .map( BinaryData::getValue )
                        .map( this::asData )
                        .orElse( new byte[0] );
                ModuleLogger.verbose( "checking dataObject " + id );
                results.add( convert( data ) );
            }
        }
        
        if ( dataReferences != null )
        {
            for ( Iterator<Entry<String, DataObjectReferenceType>> iterator = dataReferences.entrySet().iterator(); iterator.hasNext(); )
            {
                Entry<String, DataObjectReferenceType> entry = iterator.next();
                
                try
                {
                    ModuleLogger.verbose( "checking dataObjectReference " + entry.getKey() );
                    byte[] data = Files.readAllBytes( Paths.get( entry.getValue().getURI() ) );
                    results.add( convert( data ) );
                }
                catch ( IOException e )
                {
                    ModuleLogger.verbose( "reading dataObjectReference " + entry.getKey() + " failed", e );
                }
            }
        }
        
        return results;
    }
    
    /**
     * Converting raw binary data into a {@link SignatureObject}.
     * 
     * @param data
     *            the binary data containing a signature
     * @return the signature object
     */
    SignatureObject convert( byte[] data )
    {
        SignaturePtr signaturePtr = new SignaturePtr();
        signaturePtr.setWhichDocument( data );
        
        SignatureObject signature = new SignatureObject();
        signature.setSignaturePtr( signaturePtr );
        
        return signature;
    }
    
    /**
     * Retrieving the binary data from a data handler.
     * 
     * @param handler
     *            the data handler
     * @return the binary data
     */
    byte[] asData( DataHandler handler )
    {
        try
        {
            return handler.getInputStream().readAllBytes();
        }
        catch ( IOException e )
        {
            ModuleLogger.verbose( "could not retrieve data from dataObject", e );
        }
        
        return new byte[0];
    }
    
    /**
     * Extracts the DataObjectReference elements.
     * 
     * @param dataSection
     *            the dataSection
     * @return DataObjectReferences
     */
    Map<DataObjectType, DataObjectReferenceType> findDataReferences( DataObjectsSectionType dataSection )
    {
        Map<DataObjectType, DataObjectReferenceType> result = new LinkedHashMap<>();
        for ( DataObjectType dataObject : dataSection.getDataObject() )
        {
            Optional<AnyType> xmlData = Optional.ofNullable( dataObject.getXmlData() );
            if ( xmlData.isPresent() && !xmlData.get().getAny().isEmpty() )
            {
                JAXBElement<?> xmlDataContent = (JAXBElement<?>) xmlData.get().getAny().get( 0 );
                
                if ( xmlDataContent.getValue() instanceof DataObjectReferenceType )
                {
                    result.put( dataObject, (DataObjectReferenceType) xmlDataContent.getValue() );
                }
            }
        }
        
        return result;
    }
    
}
