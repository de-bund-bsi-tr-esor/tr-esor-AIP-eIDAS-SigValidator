
package iso.std.iso_iec._24727.tech.schema;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java-Klasse für PSSParameterType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="PSSParameterType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="HashAlgorithm" type="{urn:iso:std:iso-iec:24727:tech:schema}AlgorithmIdentifierType" minOccurs="0"/&gt;
 *         &lt;element name="MaskGenAlgorithm" type="{urn:iso:std:iso-iec:24727:tech:schema}AlgorithmIdentifierType" minOccurs="0"/&gt;
 *         &lt;element name="SaltLength" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="TrailerField" type="{http://www.w3.org/2001/XMLSchema}hexBinary" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PSSParameterType", propOrder = {
    "hashAlgorithm",
    "maskGenAlgorithm",
    "saltLength",
    "trailerField"
})
public class PSSParameterType {

    @XmlElement(name = "HashAlgorithm")
    protected AlgorithmIdentifierType hashAlgorithm;
    @XmlElement(name = "MaskGenAlgorithm")
    protected AlgorithmIdentifierType maskGenAlgorithm;
    @XmlElement(name = "SaltLength")
    protected BigInteger saltLength;
    @XmlElement(name = "TrailerField", type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] trailerField;

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
     * Ruft den Wert der maskGenAlgorithm-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link AlgorithmIdentifierType }
     *     
     */
    public AlgorithmIdentifierType getMaskGenAlgorithm() {
        return maskGenAlgorithm;
    }

    /**
     * Legt den Wert der maskGenAlgorithm-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link AlgorithmIdentifierType }
     *     
     */
    public void setMaskGenAlgorithm(AlgorithmIdentifierType value) {
        this.maskGenAlgorithm = value;
    }

    /**
     * Ruft den Wert der saltLength-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSaltLength() {
        return saltLength;
    }

    /**
     * Legt den Wert der saltLength-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSaltLength(BigInteger value) {
        this.saltLength = value;
    }

    /**
     * Ruft den Wert der trailerField-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getTrailerField() {
        return trailerField;
    }

    /**
     * Legt den Wert der trailerField-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrailerField(byte[] value) {
        this.trailerField = value;
    }

}
