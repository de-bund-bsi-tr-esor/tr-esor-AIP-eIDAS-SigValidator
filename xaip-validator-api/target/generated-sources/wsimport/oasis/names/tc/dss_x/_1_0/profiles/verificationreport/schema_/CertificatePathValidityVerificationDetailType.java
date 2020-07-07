
package oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import oasis.names.tc.dss._1_0.core.schema.AnyType;


/**
 * <p>Java-Klasse für CertificatePathValidityVerificationDetailType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="CertificatePathValidityVerificationDetailType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CertificateValidity" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}CertificateValidityType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="TSLValidity" type="{urn:oasis:names:tc:dss:1.0:core:schema}AnyType" minOccurs="0"/&gt;
 *         &lt;element name="TrustAnchor" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}VerificationResultType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CertificatePathValidityVerificationDetailType", propOrder = {
    "certificateValidity",
    "tslValidity",
    "trustAnchor"
})
public class CertificatePathValidityVerificationDetailType {

    @XmlElement(name = "CertificateValidity")
    protected List<CertificateValidityType> certificateValidity;
    @XmlElement(name = "TSLValidity")
    protected AnyType tslValidity;
    @XmlElement(name = "TrustAnchor", required = true)
    protected VerificationResultType trustAnchor;

    /**
     * Gets the value of the certificateValidity property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the certificateValidity property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCertificateValidity().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CertificateValidityType }
     * 
     * 
     */
    public List<CertificateValidityType> getCertificateValidity() {
        if (certificateValidity == null) {
            certificateValidity = new ArrayList<CertificateValidityType>();
        }
        return this.certificateValidity;
    }

    /**
     * Ruft den Wert der tslValidity-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link AnyType }
     *     
     */
    public AnyType getTSLValidity() {
        return tslValidity;
    }

    /**
     * Legt den Wert der tslValidity-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link AnyType }
     *     
     */
    public void setTSLValidity(AnyType value) {
        this.tslValidity = value;
    }

    /**
     * Ruft den Wert der trustAnchor-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link VerificationResultType }
     *     
     */
    public VerificationResultType getTrustAnchor() {
        return trustAnchor;
    }

    /**
     * Legt den Wert der trustAnchor-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link VerificationResultType }
     *     
     */
    public void setTrustAnchor(VerificationResultType value) {
        this.trustAnchor = value;
    }

}
