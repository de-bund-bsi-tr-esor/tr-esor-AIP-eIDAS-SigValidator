
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
 *         &lt;element name="CardApplicationPath" type="{urn:iso:std:iso-iec:24727:tech:schema}CardApplicationPathType"/&gt;
 *         &lt;element name="Output" type="{urn:iso:std:iso-iec:24727:tech:schema}OutputInfoType" minOccurs="0"/&gt;
 *         &lt;element name="ExclusiveUse" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
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
    "cardApplicationPath",
    "output",
    "exclusiveUse"
})
@XmlRootElement(name = "CardApplicationConnect")
public class CardApplicationConnect
    extends RequestType
{

    @XmlElement(name = "CardApplicationPath", required = true)
    protected CardApplicationPathType cardApplicationPath;
    @XmlElement(name = "Output")
    protected OutputInfoType output;
    @XmlElement(name = "ExclusiveUse", defaultValue = "false")
    protected Boolean exclusiveUse;

    /**
     * Ruft den Wert der cardApplicationPath-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link CardApplicationPathType }
     *     
     */
    public CardApplicationPathType getCardApplicationPath() {
        return cardApplicationPath;
    }

    /**
     * Legt den Wert der cardApplicationPath-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link CardApplicationPathType }
     *     
     */
    public void setCardApplicationPath(CardApplicationPathType value) {
        this.cardApplicationPath = value;
    }

    /**
     * Ruft den Wert der output-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link OutputInfoType }
     *     
     */
    public OutputInfoType getOutput() {
        return output;
    }

    /**
     * Legt den Wert der output-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link OutputInfoType }
     *     
     */
    public void setOutput(OutputInfoType value) {
        this.output = value;
    }

    /**
     * Ruft den Wert der exclusiveUse-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isExclusiveUse() {
        return exclusiveUse;
    }

    /**
     * Legt den Wert der exclusiveUse-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setExclusiveUse(Boolean value) {
        this.exclusiveUse = value;
    }

}
