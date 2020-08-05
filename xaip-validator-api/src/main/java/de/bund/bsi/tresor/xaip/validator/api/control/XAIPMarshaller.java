package de.bund.bsi.tresor.xaip.validator.api.control;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import de.bund.bsi.tr_esor.xaip._1.XAIPType;

/**
 * Util class for XAIP marshalling since the wsdl generated type does not contain a <code>@XMLRootElement</code> annotation.
 * 
 * @author wolffs
 */
public class XAIPMarshaller
{
    /**
     * Namespace and name of the XAIP element
     */
    public static final QName XAIP_QNAME = new QName( "http://www.bsi.bund.de/tr-esor/xaip/1.2", "XAIP" );
    
    /**
     * Creating a proper JAXBElement of the xaipType which can be used for marshalling
     * 
     * @param xaip
     *            the xaip
     * @return the jaxbElement
     */
    public static JAXBElement<XAIPType> element( XAIPType xaip )
    {
        return new JAXBElement<XAIPType>( XAIP_QNAME, XAIPType.class, xaip );
    }
}
