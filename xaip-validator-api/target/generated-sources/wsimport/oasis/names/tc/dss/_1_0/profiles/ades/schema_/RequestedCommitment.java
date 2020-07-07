
package oasis.names.tc.dss._1_0.profiles.ades.schema_;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.etsi.uri._01903.v1_3.CommitmentTypeIndicationType;


/**
 * <p>Java-Klasse für anonymous complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice&gt;
 *         &lt;element ref="{http://uri.etsi.org/01903/v1.3.2#}CommitmentTypeIndication"/&gt;
 *         &lt;element name="BinaryValue" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
 *       &lt;/choice&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "commitmentTypeIndication",
    "binaryValue"
})
@XmlRootElement(name = "RequestedCommitment")
public class RequestedCommitment {

    @XmlElement(name = "CommitmentTypeIndication", namespace = "http://uri.etsi.org/01903/v1.3.2#")
    protected CommitmentTypeIndicationType commitmentTypeIndication;
    @XmlElement(name = "BinaryValue")
    protected byte[] binaryValue;

    /**
     * Ruft den Wert der commitmentTypeIndication-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link CommitmentTypeIndicationType }
     *     
     */
    public CommitmentTypeIndicationType getCommitmentTypeIndication() {
        return commitmentTypeIndication;
    }

    /**
     * Legt den Wert der commitmentTypeIndication-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link CommitmentTypeIndicationType }
     *     
     */
    public void setCommitmentTypeIndication(CommitmentTypeIndicationType value) {
        this.commitmentTypeIndication = value;
    }

    /**
     * Ruft den Wert der binaryValue-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getBinaryValue() {
        return binaryValue;
    }

    /**
     * Legt den Wert der binaryValue-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setBinaryValue(byte[] value) {
        this.binaryValue = value;
    }

}
