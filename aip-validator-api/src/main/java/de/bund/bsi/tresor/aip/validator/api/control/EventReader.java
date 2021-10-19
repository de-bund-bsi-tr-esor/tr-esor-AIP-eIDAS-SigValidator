package de.bund.bsi.tresor.aip.validator.api.control;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import de.bund.bsi.tr_esor.xaip.DataObjectType;
import de.bund.bsi.tr_esor.xaip.MetaDataObjectType;
import de.bund.bsi.tr_esor.xaip.ObjectFactory;
import lombok.Getter;

/**
 * @author wolffs
 */
public class EventReader
{
    private static final String xmlDataLocalName          = "xmlData";
    private static final String dataObjectIdAttribute     = "dataObjectID";
    private static final String metaDataObjectIdAttribute = "metaDataID";
    
    private QName               dataObjName;
    private QName               metaDataObjName;
    
    private String              currentOid                = null;
    private Writer              writer;
    
    @Getter
    private Map<String, File>   files                     = new HashMap<>();
    
    public EventReader()
    {
        ObjectFactory factory = new ObjectFactory();
        JAXBElement<DataObjectType> dataObjXmlElem = factory.createDataObject( null );
        JAXBElement<MetaDataObjectType> metaDataObjXmlElem = factory.createMetaDataObject( null );
        
        this.dataObjName = dataObjXmlElem.getName();
        this.metaDataObjName = metaDataObjXmlElem.getName();
    }
    
    // keep reading after endEvent and before startEvent since this will exclude the xmlData tag itself
    public void read( XMLEventReader reader ) throws Exception
    {
        while ( reader.hasNext() )
        {
            XMLEvent event = reader.nextEvent();
            endEvent( event );
            
            if ( currentOid != null && writer != null )
            {
                event.writeAsEncodedUnicode( writer );
            }
            
            startEvent( event );
        }
    }
    
    void startEvent( XMLEvent event ) throws IOException
    {
        if ( !event.isStartElement() )
            return;
        
        StartElement startElement = event.asStartElement();
        QName qName = startElement.getName();
        
        if ( isDataObjectElement( qName ) || isMetaDataObjectElement( qName ) )
        {
            currentOid = startElement.getAttributeByName( new QName( dataObjectIdAttribute ) ).getValue();
        }
        
        if ( currentOid != null && isXmlDataElement( qName ) )
        {
            File tmpFile = Files.createTempFile( currentOid, null ).toFile();
            files.put( currentOid, tmpFile );
            writer = new PrintWriter( tmpFile );
        }
        
    }
    
    void endEvent( XMLEvent event ) throws Exception
    {
        if ( !event.isEndElement() )
            return;
        
        EndElement endElement = event.asEndElement();
        QName qName = endElement.getName();
        
        if ( isDataObjectElement( qName ) || isMetaDataObjectElement( qName ) )
        {
            currentOid = null;
        }
        
        if ( isXmlDataElement( qName ) )
        {
            writer.close();
            writer = null;
        }
    }
    
    boolean isMetaDataObjectElement( QName qName )
    {
        String uri = qName.getNamespaceURI();
        String localName = qName.getLocalPart();
        
        return metaDataObjName.getNamespaceURI().equals( uri ) && metaDataObjName.getLocalPart().equals( localName );
    }
    
    boolean isDataObjectElement( QName qName )
    {
        String uri = qName.getNamespaceURI();
        String localName = qName.getLocalPart();
        
        return dataObjName.getNamespaceURI().equals( uri ) && dataObjName.getLocalPart().equals( localName );
    }
    
    boolean isXmlDataElement( QName qName )
    {
        String uri = qName.getNamespaceURI();
        String localName = qName.getLocalPart();
        
        return dataObjName.getNamespaceURI().equals( uri ) && xmlDataLocalName.equals( localName );
    }
}
