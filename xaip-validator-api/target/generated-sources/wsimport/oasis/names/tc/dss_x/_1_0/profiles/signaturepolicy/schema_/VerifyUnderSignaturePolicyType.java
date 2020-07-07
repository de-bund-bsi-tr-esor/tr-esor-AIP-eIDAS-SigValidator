
package oasis.names.tc.dss_x._1_0.profiles.signaturepolicy.schema_;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für VerifyUnderSignaturePolicyType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="VerifyUnderSignaturePolicyType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="DefaultPolicy" type="{urn:oasis:names:tc:dss-x:1.0:profiles:SignaturePolicy:schema#}SignaturePolicyDetailsType" minOccurs="0"/&gt;
 *         &lt;element ref="{urn:oasis:names:tc:dss-x:1.0:profiles:SignaturePolicy:schema#}ExplicitPolicies" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VerifyUnderSignaturePolicyType", propOrder = {
    "defaultPolicy",
    "explicitPolicies"
})
public class VerifyUnderSignaturePolicyType {

    @XmlElement(name = "DefaultPolicy")
    protected SignaturePolicyDetailsType defaultPolicy;
    @XmlElement(name = "ExplicitPolicies")
    protected PolicySignaturePairsType explicitPolicies;

    /**
     * Ruft den Wert der defaultPolicy-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link SignaturePolicyDetailsType }
     *     
     */
    public SignaturePolicyDetailsType getDefaultPolicy() {
        return defaultPolicy;
    }

    /**
     * Legt den Wert der defaultPolicy-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link SignaturePolicyDetailsType }
     *     
     */
    public void setDefaultPolicy(SignaturePolicyDetailsType value) {
        this.defaultPolicy = value;
    }

    /**
     * Ruft den Wert der explicitPolicies-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PolicySignaturePairsType }
     *     
     */
    public PolicySignaturePairsType getExplicitPolicies() {
        return explicitPolicies;
    }

    /**
     * Legt den Wert der explicitPolicies-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PolicySignaturePairsType }
     *     
     */
    public void setExplicitPolicies(PolicySignaturePairsType value) {
        this.explicitPolicies = value;
    }

}
