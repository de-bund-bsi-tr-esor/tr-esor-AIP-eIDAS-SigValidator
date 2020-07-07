
package oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für SignatureValidityType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="SignatureValidityType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SigMathOK" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}VerificationResultType"/&gt;
 *         &lt;element name="SignatureAlgorithm" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}AlgorithmValidityType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SignatureValidityType", propOrder = {
    "sigMathOK",
    "signatureAlgorithm"
})
public class SignatureValidityType {

    @XmlElement(name = "SigMathOK", required = true)
    protected VerificationResultType sigMathOK;
    @XmlElement(name = "SignatureAlgorithm")
    protected AlgorithmValidityType signatureAlgorithm;

    /**
     * Ruft den Wert der sigMathOK-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link VerificationResultType }
     *     
     */
    public VerificationResultType getSigMathOK() {
        return sigMathOK;
    }

    /**
     * Legt den Wert der sigMathOK-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link VerificationResultType }
     *     
     */
    public void setSigMathOK(VerificationResultType value) {
        this.sigMathOK = value;
    }

    /**
     * Ruft den Wert der signatureAlgorithm-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link AlgorithmValidityType }
     *     
     */
    public AlgorithmValidityType getSignatureAlgorithm() {
        return signatureAlgorithm;
    }

    /**
     * Legt den Wert der signatureAlgorithm-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link AlgorithmValidityType }
     *     
     */
    public void setSignatureAlgorithm(AlgorithmValidityType value) {
        this.signatureAlgorithm = value;
    }

}
