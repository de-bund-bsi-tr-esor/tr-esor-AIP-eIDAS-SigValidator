
package iso.std.iso_iec._24727.tech.schema;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


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
 *         &lt;element name="SlotHandle" type="{urn:iso:std:iso-iec:24727:tech:schema}SlotHandleType"/&gt;
 *         &lt;element name="InputUnit" type="{urn:iso:std:iso-iec:24727:tech:schema}InputUnitType"/&gt;
 *         &lt;element name="DisplayIndex" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" minOccurs="0"/&gt;
 *         &lt;element name="AltVUMessages" type="{urn:iso:std:iso-iec:24727:tech:schema}AltVUMessagesType" minOccurs="0"/&gt;
 *         &lt;element name="TimeoutUntilFirstKey" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/&gt;
 *         &lt;element name="TimeoutAfterFirstKey" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/&gt;
 *         &lt;element name="Template" type="{http://www.w3.org/2001/XMLSchema}hexBinary"/&gt;
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
    "slotHandle",
    "inputUnit",
    "displayIndex",
    "altVUMessages",
    "timeoutUntilFirstKey",
    "timeoutAfterFirstKey",
    "template"
})
@XmlRootElement(name = "VerifyUser")
public class VerifyUser
    extends RequestType
{

    @XmlElement(name = "SlotHandle", required = true, type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] slotHandle;
    @XmlElement(name = "InputUnit", required = true)
    protected InputUnitType inputUnit;
    @XmlElement(name = "DisplayIndex")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger displayIndex;
    @XmlElement(name = "AltVUMessages")
    protected AltVUMessagesType altVUMessages;
    @XmlElement(name = "TimeoutUntilFirstKey")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger timeoutUntilFirstKey;
    @XmlElement(name = "TimeoutAfterFirstKey")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger timeoutAfterFirstKey;
    @XmlElement(name = "Template", required = true, type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] template;

    /**
     * Ruft den Wert der slotHandle-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getSlotHandle() {
        return slotHandle;
    }

    /**
     * Legt den Wert der slotHandle-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSlotHandle(byte[] value) {
        this.slotHandle = value;
    }

    /**
     * Ruft den Wert der inputUnit-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link InputUnitType }
     *     
     */
    public InputUnitType getInputUnit() {
        return inputUnit;
    }

    /**
     * Legt den Wert der inputUnit-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link InputUnitType }
     *     
     */
    public void setInputUnit(InputUnitType value) {
        this.inputUnit = value;
    }

    /**
     * Ruft den Wert der displayIndex-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDisplayIndex() {
        return displayIndex;
    }

    /**
     * Legt den Wert der displayIndex-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDisplayIndex(BigInteger value) {
        this.displayIndex = value;
    }

    /**
     * Ruft den Wert der altVUMessages-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link AltVUMessagesType }
     *     
     */
    public AltVUMessagesType getAltVUMessages() {
        return altVUMessages;
    }

    /**
     * Legt den Wert der altVUMessages-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link AltVUMessagesType }
     *     
     */
    public void setAltVUMessages(AltVUMessagesType value) {
        this.altVUMessages = value;
    }

    /**
     * Ruft den Wert der timeoutUntilFirstKey-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTimeoutUntilFirstKey() {
        return timeoutUntilFirstKey;
    }

    /**
     * Legt den Wert der timeoutUntilFirstKey-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTimeoutUntilFirstKey(BigInteger value) {
        this.timeoutUntilFirstKey = value;
    }

    /**
     * Ruft den Wert der timeoutAfterFirstKey-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTimeoutAfterFirstKey() {
        return timeoutAfterFirstKey;
    }

    /**
     * Legt den Wert der timeoutAfterFirstKey-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTimeoutAfterFirstKey(BigInteger value) {
        this.timeoutAfterFirstKey = value;
    }

    /**
     * Ruft den Wert der template-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getTemplate() {
        return template;
    }

    /**
     * Legt den Wert der template-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTemplate(byte[] value) {
        this.template = value;
    }

}
