
package iso.std.iso_iec._24727.tech.schema;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für CryptoKeyInfoType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="CryptoKeyInfoType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="KeyRef" type="{urn:iso:std:iso-iec:24727:tech:schema}KeyRefType" minOccurs="0"/&gt;
 *         &lt;element name="KeySize" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/&gt;
 *         &lt;element name="NonceSize" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/&gt;
 *         &lt;choice minOccurs="0"&gt;
 *           &lt;element name="SecretKeyValue" type="{urn:iso:std:iso-iec:24727:tech:schema}KeyValueType"/&gt;
 *           &lt;sequence&gt;
 *             &lt;element name="PrivateKeyValue" type="{urn:iso:std:iso-iec:24727:tech:schema}KeyValueType" minOccurs="0"/&gt;
 *             &lt;element name="PublicKeyValue" type="{urn:iso:std:iso-iec:24727:tech:schema}KeyValueType"/&gt;
 *           &lt;/sequence&gt;
 *           &lt;element name="generateFlag" type="{urn:iso:std:iso-iec:24727:tech:schema}NULL"/&gt;
 *         &lt;/choice&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CryptoKeyInfoType", propOrder = {
    "keyRef",
    "keySize",
    "nonceSize",
    "secretKeyValue",
    "privateKeyValue",
    "publicKeyValue",
    "generateFlag"
})
public class CryptoKeyInfoType {

    @XmlElement(name = "KeyRef")
    protected KeyRefType keyRef;
    @XmlElement(name = "KeySize")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger keySize;
    @XmlElement(name = "NonceSize")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger nonceSize;
    @XmlElement(name = "SecretKeyValue")
    protected KeyValueType secretKeyValue;
    @XmlElement(name = "PrivateKeyValue")
    protected KeyValueType privateKeyValue;
    @XmlElement(name = "PublicKeyValue")
    protected KeyValueType publicKeyValue;
    protected NULL generateFlag;

    /**
     * Ruft den Wert der keyRef-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link KeyRefType }
     *     
     */
    public KeyRefType getKeyRef() {
        return keyRef;
    }

    /**
     * Legt den Wert der keyRef-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link KeyRefType }
     *     
     */
    public void setKeyRef(KeyRefType value) {
        this.keyRef = value;
    }

    /**
     * Ruft den Wert der keySize-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getKeySize() {
        return keySize;
    }

    /**
     * Legt den Wert der keySize-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setKeySize(BigInteger value) {
        this.keySize = value;
    }

    /**
     * Ruft den Wert der nonceSize-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNonceSize() {
        return nonceSize;
    }

    /**
     * Legt den Wert der nonceSize-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNonceSize(BigInteger value) {
        this.nonceSize = value;
    }

    /**
     * Ruft den Wert der secretKeyValue-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link KeyValueType }
     *     
     */
    public KeyValueType getSecretKeyValue() {
        return secretKeyValue;
    }

    /**
     * Legt den Wert der secretKeyValue-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link KeyValueType }
     *     
     */
    public void setSecretKeyValue(KeyValueType value) {
        this.secretKeyValue = value;
    }

    /**
     * Ruft den Wert der privateKeyValue-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link KeyValueType }
     *     
     */
    public KeyValueType getPrivateKeyValue() {
        return privateKeyValue;
    }

    /**
     * Legt den Wert der privateKeyValue-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link KeyValueType }
     *     
     */
    public void setPrivateKeyValue(KeyValueType value) {
        this.privateKeyValue = value;
    }

    /**
     * Ruft den Wert der publicKeyValue-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link KeyValueType }
     *     
     */
    public KeyValueType getPublicKeyValue() {
        return publicKeyValue;
    }

    /**
     * Legt den Wert der publicKeyValue-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link KeyValueType }
     *     
     */
    public void setPublicKeyValue(KeyValueType value) {
        this.publicKeyValue = value;
    }

    /**
     * Ruft den Wert der generateFlag-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link NULL }
     *     
     */
    public NULL getGenerateFlag() {
        return generateFlag;
    }

    /**
     * Legt den Wert der generateFlag-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link NULL }
     *     
     */
    public void setGenerateFlag(NULL value) {
        this.generateFlag = value;
    }

}
