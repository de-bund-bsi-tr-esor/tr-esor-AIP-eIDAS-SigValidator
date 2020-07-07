
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java-Klasse für MatchingDataType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="MatchingDataType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Offset" type="{http://www.w3.org/2001/XMLSchema}hexBinary" minOccurs="0"/&gt;
 *         &lt;element name="Length" type="{http://www.w3.org/2001/XMLSchema}hexBinary" minOccurs="0"/&gt;
 *         &lt;element name="MatchingValue" type="{http://www.w3.org/2001/XMLSchema}hexBinary"/&gt;
 *         &lt;element name="Mask" type="{http://www.w3.org/2001/XMLSchema}hexBinary" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="MatchingRule" type="{urn:iso:std:iso-iec:24727:tech:schema}MatchingRuleType" default="Equals" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MatchingDataType", propOrder = {
    "offset",
    "length",
    "matchingValue",
    "mask"
})
public class MatchingDataType {

    @XmlElement(name = "Offset", type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] offset;
    @XmlElement(name = "Length", type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] length;
    @XmlElement(name = "MatchingValue", required = true, type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] matchingValue;
    @XmlElement(name = "Mask", type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] mask;
    @XmlAttribute(name = "MatchingRule")
    protected MatchingRuleType matchingRule;

    /**
     * Ruft den Wert der offset-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getOffset() {
        return offset;
    }

    /**
     * Legt den Wert der offset-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOffset(byte[] value) {
        this.offset = value;
    }

    /**
     * Ruft den Wert der length-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getLength() {
        return length;
    }

    /**
     * Legt den Wert der length-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLength(byte[] value) {
        this.length = value;
    }

    /**
     * Ruft den Wert der matchingValue-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getMatchingValue() {
        return matchingValue;
    }

    /**
     * Legt den Wert der matchingValue-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMatchingValue(byte[] value) {
        this.matchingValue = value;
    }

    /**
     * Ruft den Wert der mask-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getMask() {
        return mask;
    }

    /**
     * Legt den Wert der mask-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMask(byte[] value) {
        this.mask = value;
    }

    /**
     * Ruft den Wert der matchingRule-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link MatchingRuleType }
     *     
     */
    public MatchingRuleType getMatchingRule() {
        if (matchingRule == null) {
            return MatchingRuleType.EQUALS;
        } else {
            return matchingRule;
        }
    }

    /**
     * Legt den Wert der matchingRule-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link MatchingRuleType }
     *     
     */
    public void setMatchingRule(MatchingRuleType value) {
        this.matchingRule = value;
    }

}
