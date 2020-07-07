
package oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für AttributeCertificateValidityType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="AttributeCertificateValidityType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="AttributeCertificateIdentifier" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}AttrCertIDType"/&gt;
 *         &lt;element name="AttributeCertificateValue" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/&gt;
 *         &lt;element name="AttributeCertificateContent" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}AttributeCertificateContentType" minOccurs="0"/&gt;
 *         &lt;element name="SignatureOK" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}SignatureValidityType"/&gt;
 *         &lt;element name="CertificatePathValidity" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}CertificatePathValidityType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AttributeCertificateValidityType", propOrder = {
    "attributeCertificateIdentifier",
    "attributeCertificateValue",
    "attributeCertificateContent",
    "signatureOK",
    "certificatePathValidity"
})
public class AttributeCertificateValidityType {

    @XmlElement(name = "AttributeCertificateIdentifier", required = true)
    protected AttrCertIDType attributeCertificateIdentifier;
    @XmlElement(name = "AttributeCertificateValue")
    protected byte[] attributeCertificateValue;
    @XmlElement(name = "AttributeCertificateContent")
    protected AttributeCertificateContentType attributeCertificateContent;
    @XmlElement(name = "SignatureOK", required = true)
    protected SignatureValidityType signatureOK;
    @XmlElement(name = "CertificatePathValidity", required = true)
    protected CertificatePathValidityType certificatePathValidity;

    /**
     * Ruft den Wert der attributeCertificateIdentifier-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link AttrCertIDType }
     *     
     */
    public AttrCertIDType getAttributeCertificateIdentifier() {
        return attributeCertificateIdentifier;
    }

    /**
     * Legt den Wert der attributeCertificateIdentifier-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link AttrCertIDType }
     *     
     */
    public void setAttributeCertificateIdentifier(AttrCertIDType value) {
        this.attributeCertificateIdentifier = value;
    }

    /**
     * Ruft den Wert der attributeCertificateValue-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getAttributeCertificateValue() {
        return attributeCertificateValue;
    }

    /**
     * Legt den Wert der attributeCertificateValue-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setAttributeCertificateValue(byte[] value) {
        this.attributeCertificateValue = value;
    }

    /**
     * Ruft den Wert der attributeCertificateContent-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link AttributeCertificateContentType }
     *     
     */
    public AttributeCertificateContentType getAttributeCertificateContent() {
        return attributeCertificateContent;
    }

    /**
     * Legt den Wert der attributeCertificateContent-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link AttributeCertificateContentType }
     *     
     */
    public void setAttributeCertificateContent(AttributeCertificateContentType value) {
        this.attributeCertificateContent = value;
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
     * Ruft den Wert der certificatePathValidity-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link CertificatePathValidityType }
     *     
     */
    public CertificatePathValidityType getCertificatePathValidity() {
        return certificatePathValidity;
    }

    /**
     * Legt den Wert der certificatePathValidity-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link CertificatePathValidityType }
     *     
     */
    public void setCertificatePathValidity(CertificatePathValidityType value) {
        this.certificatePathValidity = value;
    }

}
