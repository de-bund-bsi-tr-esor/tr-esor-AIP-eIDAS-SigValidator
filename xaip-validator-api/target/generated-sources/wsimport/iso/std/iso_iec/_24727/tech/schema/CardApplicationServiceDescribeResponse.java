
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
 *       &lt;sequence&gt;
 *         &lt;element name="ServiceDescription" type="{urn:iso:std:iso-iec:24727:tech:schema}CardApplicationServiceDescriptionType"/&gt;
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
    "serviceDescription"
})
@XmlRootElement(name = "CardApplicationServiceDescribeResponse")
public class CardApplicationServiceDescribeResponse
    extends ResponseType
{

    @XmlElement(name = "ServiceDescription", required = true)
    protected CardApplicationServiceDescriptionType serviceDescription;

    /**
     * Ruft den Wert der serviceDescription-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link CardApplicationServiceDescriptionType }
     *     
     */
    public CardApplicationServiceDescriptionType getServiceDescription() {
        return serviceDescription;
    }

    /**
     * Legt den Wert der serviceDescription-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link CardApplicationServiceDescriptionType }
     *     
     */
    public void setServiceDescription(CardApplicationServiceDescriptionType value) {
        this.serviceDescription = value;
    }

}
