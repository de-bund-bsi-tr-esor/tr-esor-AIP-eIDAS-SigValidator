
package iso.std.iso_iec._24727.tech.schema;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java-Klasse für PasswordAttributesType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="PasswordAttributesType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="pwdFlags" type="{urn:iso:std:iso-iec:24727:tech:schema}PasswordFlagsType"/&gt;
 *         &lt;element name="pwdType" type="{urn:iso:std:iso-iec:24727:tech:schema}PasswordTypeType"/&gt;
 *         &lt;element name="minLength" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger"/&gt;
 *         &lt;element name="storedLength" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger"/&gt;
 *         &lt;element name="maxLength" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" minOccurs="0"/&gt;
 *         &lt;element name="padChar" type="{urn:iso:std:iso-iec:24727:tech:schema}PadCharType" minOccurs="0"/&gt;
 *         &lt;element name="lastPasswordChange" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PasswordAttributesType", propOrder = {
    "pwdFlags",
    "pwdType",
    "minLength",
    "storedLength",
    "maxLength",
    "padChar",
    "lastPasswordChange"
})
public class PasswordAttributesType {

    @XmlList
    @XmlElement(required = true)
    protected List<String> pwdFlags;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected PasswordTypeType pwdType;
    @XmlElement(required = true)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger minLength;
    @XmlElement(required = true)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger storedLength;
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger maxLength;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] padChar;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastPasswordChange;

    /**
     * Gets the value of the pwdFlags property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pwdFlags property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPwdFlags().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getPwdFlags() {
        if (pwdFlags == null) {
            pwdFlags = new ArrayList<String>();
        }
        return this.pwdFlags;
    }

    /**
     * Ruft den Wert der pwdType-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PasswordTypeType }
     *     
     */
    public PasswordTypeType getPwdType() {
        return pwdType;
    }

    /**
     * Legt den Wert der pwdType-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PasswordTypeType }
     *     
     */
    public void setPwdType(PasswordTypeType value) {
        this.pwdType = value;
    }

    /**
     * Ruft den Wert der minLength-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMinLength() {
        return minLength;
    }

    /**
     * Legt den Wert der minLength-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMinLength(BigInteger value) {
        this.minLength = value;
    }

    /**
     * Ruft den Wert der storedLength-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getStoredLength() {
        return storedLength;
    }

    /**
     * Legt den Wert der storedLength-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setStoredLength(BigInteger value) {
        this.storedLength = value;
    }

    /**
     * Ruft den Wert der maxLength-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMaxLength() {
        return maxLength;
    }

    /**
     * Legt den Wert der maxLength-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMaxLength(BigInteger value) {
        this.maxLength = value;
    }

    /**
     * Ruft den Wert der padChar-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getPadChar() {
        return padChar;
    }

    /**
     * Legt den Wert der padChar-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPadChar(byte[] value) {
        this.padChar = value;
    }

    /**
     * Ruft den Wert der lastPasswordChange-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastPasswordChange() {
        return lastPasswordChange;
    }

    /**
     * Legt den Wert der lastPasswordChange-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastPasswordChange(XMLGregorianCalendar value) {
        this.lastPasswordChange = value;
    }

}
