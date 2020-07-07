
package org.setcce.schemas.ers;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java-Klasse für EncryptionInfo complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="EncryptionInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="EncryptionInfoType" type="{http://www.setcce.org/schemas/ers}ObjectIdentifier"/&gt;
 *         &lt;element name="EncryptionInfoValue" type="{http://www.setcce.org/schemas/ers}OpenType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EncryptionInfo", propOrder = {
    "encryptionInfoType",
    "encryptionInfoValue"
})
public class EncryptionInfo {

    @XmlElement(name = "EncryptionInfoType", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String encryptionInfoType;
    @XmlElement(name = "EncryptionInfoValue", required = true)
    protected OpenType encryptionInfoValue;

    /**
     * Ruft den Wert der encryptionInfoType-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEncryptionInfoType() {
        return encryptionInfoType;
    }

    /**
     * Legt den Wert der encryptionInfoType-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEncryptionInfoType(String value) {
        this.encryptionInfoType = value;
    }

    /**
     * Ruft den Wert der encryptionInfoValue-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link OpenType }
     *     
     */
    public OpenType getEncryptionInfoValue() {
        return encryptionInfoValue;
    }

    /**
     * Legt den Wert der encryptionInfoValue-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link OpenType }
     *     
     */
    public void setEncryptionInfoValue(OpenType value) {
        this.encryptionInfoValue = value;
    }

}
