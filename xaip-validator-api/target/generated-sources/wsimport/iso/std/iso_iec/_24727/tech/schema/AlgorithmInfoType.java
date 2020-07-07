
package iso.std.iso_iec._24727.tech.schema;

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


/**
 * <p>Java-Klasse für AlgorithmInfoType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="AlgorithmInfoType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Algorithm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="AlgorithmIdentifier" type="{urn:iso:std:iso-iec:24727:tech:schema}AlgorithmIdentifierType" minOccurs="0"/&gt;
 *         &lt;element name="SupportedOperations" type="{urn:iso:std:iso-iec:24727:tech:schema}SupportedOperationsType"/&gt;
 *         &lt;element name="CardAlgRef" type="{http://www.w3.org/2001/XMLSchema}hexBinary" minOccurs="0"/&gt;
 *         &lt;element name="HashAlgRef" type="{http://www.w3.org/2001/XMLSchema}hexBinary" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AlgorithmInfoType", propOrder = {
    "algorithm",
    "algorithmIdentifier",
    "supportedOperations",
    "cardAlgRef",
    "hashAlgRef"
})
public class AlgorithmInfoType {

    @XmlElement(name = "Algorithm")
    protected String algorithm;
    @XmlElement(name = "AlgorithmIdentifier")
    protected AlgorithmIdentifierType algorithmIdentifier;
    @XmlList
    @XmlElement(name = "SupportedOperations", required = true)
    protected List<String> supportedOperations;
    @XmlElement(name = "CardAlgRef", type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] cardAlgRef;
    @XmlElement(name = "HashAlgRef", type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] hashAlgRef;

    /**
     * Ruft den Wert der algorithm-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlgorithm() {
        return algorithm;
    }

    /**
     * Legt den Wert der algorithm-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlgorithm(String value) {
        this.algorithm = value;
    }

    /**
     * Ruft den Wert der algorithmIdentifier-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link AlgorithmIdentifierType }
     *     
     */
    public AlgorithmIdentifierType getAlgorithmIdentifier() {
        return algorithmIdentifier;
    }

    /**
     * Legt den Wert der algorithmIdentifier-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link AlgorithmIdentifierType }
     *     
     */
    public void setAlgorithmIdentifier(AlgorithmIdentifierType value) {
        this.algorithmIdentifier = value;
    }

    /**
     * Gets the value of the supportedOperations property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the supportedOperations property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSupportedOperations().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getSupportedOperations() {
        if (supportedOperations == null) {
            supportedOperations = new ArrayList<String>();
        }
        return this.supportedOperations;
    }

    /**
     * Ruft den Wert der cardAlgRef-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getCardAlgRef() {
        return cardAlgRef;
    }

    /**
     * Legt den Wert der cardAlgRef-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardAlgRef(byte[] value) {
        this.cardAlgRef = value;
    }

    /**
     * Ruft den Wert der hashAlgRef-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getHashAlgRef() {
        return hashAlgRef;
    }

    /**
     * Legt den Wert der hashAlgRef-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHashAlgRef(byte[] value) {
        this.hashAlgRef = value;
    }

}
