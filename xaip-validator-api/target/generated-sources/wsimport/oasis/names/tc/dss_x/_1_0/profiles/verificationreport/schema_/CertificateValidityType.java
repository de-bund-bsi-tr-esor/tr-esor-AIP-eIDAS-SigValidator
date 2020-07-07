
package oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.w3._2000._09.xmldsig_.X509IssuerSerialType;


/**
 * <p>Java-Klasse für CertificateValidityType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="CertificateValidityType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CertificateIdentifier" type="{http://www.w3.org/2000/09/xmldsig#}X509IssuerSerialType"/&gt;
 *         &lt;element name="Subject" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="ChainingOK" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}VerificationResultType"/&gt;
 *         &lt;element name="ValidityPeriodOK" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}VerificationResultType"/&gt;
 *         &lt;element name="ExtensionsOK" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}VerificationResultType"/&gt;
 *         &lt;element name="CertificateValue" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/&gt;
 *         &lt;element name="CertificateContent" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}CertificateContentType" minOccurs="0"/&gt;
 *         &lt;element name="SignatureOK" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}SignatureValidityType"/&gt;
 *         &lt;element name="CertificateStatus" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}CertificateStatusType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CertificateValidityType", propOrder = {
    "certificateIdentifier",
    "subject",
    "chainingOK",
    "validityPeriodOK",
    "extensionsOK",
    "certificateValue",
    "certificateContent",
    "signatureOK",
    "certificateStatus"
})
public class CertificateValidityType {

    @XmlElement(name = "CertificateIdentifier", required = true)
    protected X509IssuerSerialType certificateIdentifier;
    @XmlElement(name = "Subject", required = true)
    protected String subject;
    @XmlElement(name = "ChainingOK", required = true)
    protected VerificationResultType chainingOK;
    @XmlElement(name = "ValidityPeriodOK", required = true)
    protected VerificationResultType validityPeriodOK;
    @XmlElement(name = "ExtensionsOK", required = true)
    protected VerificationResultType extensionsOK;
    @XmlElement(name = "CertificateValue")
    protected byte[] certificateValue;
    @XmlElement(name = "CertificateContent")
    protected CertificateContentType certificateContent;
    @XmlElement(name = "SignatureOK", required = true)
    protected SignatureValidityType signatureOK;
    @XmlElement(name = "CertificateStatus", required = true)
    protected CertificateStatusType certificateStatus;

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
     * Ruft den Wert der subject-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Legt den Wert der subject-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubject(String value) {
        this.subject = value;
    }

    /**
     * Ruft den Wert der chainingOK-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link VerificationResultType }
     *     
     */
    public VerificationResultType getChainingOK() {
        return chainingOK;
    }

    /**
     * Legt den Wert der chainingOK-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link VerificationResultType }
     *     
     */
    public void setChainingOK(VerificationResultType value) {
        this.chainingOK = value;
    }

    /**
     * Ruft den Wert der validityPeriodOK-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link VerificationResultType }
     *     
     */
    public VerificationResultType getValidityPeriodOK() {
        return validityPeriodOK;
    }

    /**
     * Legt den Wert der validityPeriodOK-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link VerificationResultType }
     *     
     */
    public void setValidityPeriodOK(VerificationResultType value) {
        this.validityPeriodOK = value;
    }

    /**
     * Ruft den Wert der extensionsOK-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link VerificationResultType }
     *     
     */
    public VerificationResultType getExtensionsOK() {
        return extensionsOK;
    }

    /**
     * Legt den Wert der extensionsOK-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link VerificationResultType }
     *     
     */
    public void setExtensionsOK(VerificationResultType value) {
        this.extensionsOK = value;
    }

    /**
     * Ruft den Wert der certificateValue-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getCertificateValue() {
        return certificateValue;
    }

    /**
     * Legt den Wert der certificateValue-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setCertificateValue(byte[] value) {
        this.certificateValue = value;
    }

    /**
     * Ruft den Wert der certificateContent-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link CertificateContentType }
     *     
     */
    public CertificateContentType getCertificateContent() {
        return certificateContent;
    }

    /**
     * Legt den Wert der certificateContent-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link CertificateContentType }
     *     
     */
    public void setCertificateContent(CertificateContentType value) {
        this.certificateContent = value;
    }

    /**
     * Ruft den Wert der signatureOK-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link SignatureValidityType }
     *     
     */
    public SignatureValidityType getSignatureOK() {
        return signatureOK;
    }

    /**
     * Legt den Wert der signatureOK-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link SignatureValidityType }
     *     
     */
    public void setSignatureOK(SignatureValidityType value) {
        this.signatureOK = value;
    }

    /**
     * Ruft den Wert der certificateStatus-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link CertificateStatusType }
     *     
     */
    public CertificateStatusType getCertificateStatus() {
        return certificateStatus;
    }

    /**
     * Legt den Wert der certificateStatus-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link CertificateStatusType }
     *     
     */
    public void setCertificateStatus(CertificateStatusType value) {
        this.certificateStatus = value;
    }

}
