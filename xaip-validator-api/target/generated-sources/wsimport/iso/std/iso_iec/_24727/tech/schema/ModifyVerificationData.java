
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
 *         &lt;element name="AltMVDMessages" type="{urn:iso:std:iso-iec:24727:tech:schema}AltMVDMessagesType" minOccurs="0"/&gt;
 *         &lt;element name="OldReferenceData" type="{http://www.w3.org/2001/XMLSchema}hexBinary" minOccurs="0"/&gt;
 *         &lt;element name="TimeoutUntilFirstKey" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/&gt;
 *         &lt;element name="TimeoutAfterFirstKey" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/&gt;
 *         &lt;element name="RepeatInput" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
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
    "altMVDMessages",
    "oldReferenceData",
    "timeoutUntilFirstKey",
    "timeoutAfterFirstKey",
    "repeatInput",
    "template"
})
@XmlRootElement(name = "ModifyVerificationData")
public class ModifyVerificationData
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
    @XmlElement(name = "AltMVDMessages")
    protected AltMVDMessagesType altMVDMessages;
    @XmlElement(name = "OldReferenceData", type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] oldReferenceData;
    @XmlElement(name = "TimeoutUntilFirstKey")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger timeoutUntilFirstKey;
    @XmlElement(name = "TimeoutAfterFirstKey")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger timeoutAfterFirstKey;
    @XmlElement(name = "RepeatInput")
    protected Boolean repeatInput;
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
     * Ruft den Wert der altMVDMessages-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link AltMVDMessagesType }
     *     
     */
    public AltMVDMessagesType getAltMVDMessages() {
        return altMVDMessages;
    }

    /**
     * Legt den Wert der altMVDMessages-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link AltMVDMessagesType }
     *     
     */
    public void setAltMVDMessages(AltMVDMessagesType value) {
        this.altMVDMessages = value;
    }

    /**
     * Ruft den Wert der oldReferenceData-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getOldReferenceData() {
        return oldReferenceData;
    }

    /**
     * Legt den Wert der oldReferenceData-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOldReferenceData(byte[] value) {
        this.oldReferenceData = value;
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
     * Ruft den Wert der repeatInput-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRepeatInput() {
        return repeatInput;
    }

    /**
     * Legt den Wert der repeatInput-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRepeatInput(Boolean value) {
        this.repeatInput = value;
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
