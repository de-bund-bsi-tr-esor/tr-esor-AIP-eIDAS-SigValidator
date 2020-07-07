
package de.procilon.tresor._1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import iso.std.iso_iec._24727.tech.schema.AlgorithmIdentifierType;


/**
 * <p>Java-Klasse für MessageImprint complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="MessageImprint"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="hashAlgorithm" type="{urn:iso:std:iso-iec:24727:tech:schema}AlgorithmIdentifierType"/&gt;
 *         &lt;element name="hashedMessage" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MessageImprint", propOrder = {
    "hashAlgorithm",
    "hashedMessage"
})
public class MessageImprint {

    @XmlElement(required = true)
    protected AlgorithmIdentifierType hashAlgorithm;
    @XmlElement(required = true)
    protected byte[] hashedMessage;

    /**
     * Ruft den Wert der hashAlgorithm-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link AlgorithmIdentifierType }
     *     
     */
    public AlgorithmIdentifierType getHashAlgorithm() {
        return hashAlgorithm;
    }

    /**
     * Legt den Wert der hashAlgorithm-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link AlgorithmIdentifierType }
     *     
     */
    public void setHashAlgorithm(AlgorithmIdentifierType value) {
        this.hashAlgorithm = value;
    }

    /**
     * Ruft den Wert der hashedMessage-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getHashedMessage() {
        return hashedMessage;
    }

    /**
     * Legt den Wert der hashedMessage-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setHashedMessage(byte[] value) {
        this.hashedMessage = value;
    }

}
