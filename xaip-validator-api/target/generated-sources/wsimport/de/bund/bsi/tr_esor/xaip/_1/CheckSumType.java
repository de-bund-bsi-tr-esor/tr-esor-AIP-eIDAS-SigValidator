
package de.bund.bsi.tr_esor.xaip._1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java-Klasse für checkSumType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="checkSumType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="checkSumAlgorithm" type="{http://www.w3.org/2001/XMLSchema}anyURI"/&gt;
 *         &lt;element name="checkSum" type="{http://www.w3.org/2001/XMLSchema}hexBinary"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "checkSumType", propOrder = {
    "checkSumAlgorithm",
    "checkSum"
})
public class CheckSumType {

    @XmlElement(required = true)
    @XmlSchemaType(name = "anyURI")
    protected String checkSumAlgorithm;
    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] checkSum;

    /**
     * Ruft den Wert der checkSumAlgorithm-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCheckSumAlgorithm() {
        return checkSumAlgorithm;
    }

    /**
     * Legt den Wert der checkSumAlgorithm-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCheckSumAlgorithm(String value) {
        this.checkSumAlgorithm = value;
    }

    /**
     * Ruft den Wert der checkSum-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getCheckSum() {
        return checkSum;
    }

    /**
     * Legt den Wert der checkSum-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCheckSum(byte[] value) {
        this.checkSum = value;
    }

}
