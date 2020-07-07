
package oasis.names.tc.dss_x._1_0.profiles.signaturepolicy.schema_;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_.SignedObjectIdentifierType;


/**
 * <p>Java-Klasse für PolicySignaturePairType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="PolicySignaturePairType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{urn:oasis:names:tc:dss-x:1.0:profiles:SignaturePolicy:schema#}SignatureIdentifier"/&gt;
 *         &lt;element ref="{urn:oasis:names:tc:dss-x:1.0:profiles:SignaturePolicy:schema#}SignaturePolicy"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PolicySignaturePairType", propOrder = {
    "signatureIdentifier",
    "signaturePolicy"
})
public class PolicySignaturePairType {

    @XmlElement(name = "SignatureIdentifier", required = true)
    protected SignedObjectIdentifierType signatureIdentifier;
    @XmlElement(name = "SignaturePolicy", required = true)
    protected SignaturePolicyDetailsType signaturePolicy;

    /**
     * Ruft den Wert der signatureIdentifier-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link SignedObjectIdentifierType }
     *     
     */
    public SignedObjectIdentifierType getSignatureIdentifier() {
        return signatureIdentifier;
    }

    /**
     * Legt den Wert der signatureIdentifier-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link SignedObjectIdentifierType }
     *     
     */
    public void setSignatureIdentifier(SignedObjectIdentifierType value) {
        this.signatureIdentifier = value;
    }

    /**
     * Ruft den Wert der signaturePolicy-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link SignaturePolicyDetailsType }
     *     
     */
    public SignaturePolicyDetailsType getSignaturePolicy() {
        return signaturePolicy;
    }

    /**
     * Legt den Wert der signaturePolicy-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link SignaturePolicyDetailsType }
     *     
     */
    public void setSignaturePolicy(SignaturePolicyDetailsType value) {
        this.signaturePolicy = value;
    }

}
