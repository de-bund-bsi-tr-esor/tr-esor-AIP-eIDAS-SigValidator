
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
 *     &lt;extension base="{urn:iso:std:iso-iec:24727:tech:schema}RequestType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CardAppPathRequest" type="{urn:iso:std:iso-iec:24727:tech:schema}CardApplicationPathType"/&gt;
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
    "cardAppPathRequest"
})
@XmlRootElement(name = "CardApplicationPath")
public class CardApplicationPath
    extends RequestType
{

    @XmlElement(name = "CardAppPathRequest", required = true)
    protected CardApplicationPathType cardAppPathRequest;

    /**
     * Ruft den Wert der cardAppPathRequest-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link CardApplicationPathType }
     *     
     */
    public CardApplicationPathType getCardAppPathRequest() {
        return cardAppPathRequest;
    }

    /**
     * Legt den Wert der cardAppPathRequest-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link CardApplicationPathType }
     *     
     */
    public void setCardAppPathRequest(CardApplicationPathType value) {
        this.cardAppPathRequest = value;
    }

}
