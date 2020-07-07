
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für anonymous complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:iso:std:iso-iec:24727:tech:schema}ResponseType"&gt;
 *       &lt;sequence minOccurs="0"&gt;
 *         &lt;element name="IFDCapabilities" type="{urn:iso:std:iso-iec:24727:tech:schema}IFDCapabilitiesType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "ifdCapabilities"
})
@XmlRootElement(name = "GetIFDCapabilitiesResponse")
public class GetIFDCapabilitiesResponse
    extends ResponseType
{

    @XmlElement(name = "IFDCapabilities")
    protected IFDCapabilitiesType ifdCapabilities;

    /**
     * Ruft den Wert der ifdCapabilities-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link IFDCapabilitiesType }
     *     
     */
    public IFDCapabilitiesType getIFDCapabilities() {
        return ifdCapabilities;
    }

    /**
     * Legt den Wert der ifdCapabilities-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link IFDCapabilitiesType }
     *     
     */
    public void setIFDCapabilities(IFDCapabilitiesType value) {
        this.ifdCapabilities = value;
    }

}
