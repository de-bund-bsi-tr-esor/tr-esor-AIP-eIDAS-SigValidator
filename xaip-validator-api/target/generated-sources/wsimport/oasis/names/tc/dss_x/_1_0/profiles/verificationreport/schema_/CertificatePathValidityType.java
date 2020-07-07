
package oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.w3._2000._09.xmldsig_.X509IssuerSerialType;


/**
 * <p>Java-Klasse für CertificatePathValidityType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="CertificatePathValidityType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="PathValiditySummary" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}VerificationResultType"/&gt;
 *         &lt;element name="CertificateIdentifier" type="{http://www.w3.org/2000/09/xmldsig#}X509IssuerSerialType"/&gt;
 *         &lt;element name="PathValidityDetail" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}CertificatePathValidityVerificationDetailType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CertificatePathValidityType", propOrder = {
    "pathValiditySummary",
    "certificateIdentifier",
    "pathValidityDetail"
})
public class CertificatePathValidityType {

    @XmlElement(name = "PathValiditySummary", required = true)
    protected VerificationResultType pathValiditySummary;
    @XmlElement(name = "CertificateIdentifier", required = true)
    protected X509IssuerSerialType certificateIdentifier;
    @XmlElement(name = "PathValidityDetail")
    protected CertificatePathValidityVerificationDetailType pathValidityDetail;

    /**
     * Ruft den Wert der pathValiditySummary-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link VerificationResultType }
     *     
     */
    public VerificationResultType getPathValiditySummary() {
        return pathValiditySummary;
    }

    /**
     * Legt den Wert der pathValiditySummary-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link VerificationResultType }
     *     
     */
    public void setPathValiditySummary(VerificationResultType value) {
        this.pathValiditySummary = value;
    }

    /**
     * Ruft den Wert der certificateIdentifier-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link X509IssuerSerialType }
     *     
     */
    public X509IssuerSerialType getCertificateIdentifier() {
        return certificateIdentifier;
    }

    /**
     * Legt den Wert der certificateIdentifier-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link X509IssuerSerialType }
     *     
     */
    public void setCertificateIdentifier(X509IssuerSerialType value) {
        this.certificateIdentifier = value;
    }

    /**
     * Ruft den Wert der pathValidityDetail-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link CertificatePathValidityVerificationDetailType }
     *     
     */
    public CertificatePathValidityVerificationDetailType getPathValidityDetail() {
        return pathValidityDetail;
    }

    /**
     * Legt den Wert der pathValidityDetail-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link CertificatePathValidityVerificationDetailType }
     *     
     */
    public void setPathValidityDetail(CertificatePathValidityVerificationDetailType value) {
        this.pathValidityDetail = value;
    }

}
