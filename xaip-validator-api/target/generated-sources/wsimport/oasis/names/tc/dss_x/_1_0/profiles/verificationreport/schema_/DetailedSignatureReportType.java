
package oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import oasis.names.tc.dss._1_0.core.schema.VerifyManifestResultsType;


/**
 * <p>Java-Klasse für DetailedSignatureReportType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="DetailedSignatureReportType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="FormatOK" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}VerificationResultType"/&gt;
 *         &lt;element name="Properties" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}PropertiesType" minOccurs="0"/&gt;
 *         &lt;element ref="{urn:oasis:names:tc:dss:1.0:core:schema}VerifyManifestResults" minOccurs="0"/&gt;
 *         &lt;element name="SignatureHasVisibleContent" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
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
@XmlType(name = "DetailedSignatureReportType", propOrder = {
    "formatOK",
    "properties",
    "verifyManifestResults",
    "signatureHasVisibleContent",
    "signatureOK",
    "certificatePathValidity"
})
public class DetailedSignatureReportType {

    @XmlElement(name = "FormatOK", required = true)
    protected VerificationResultType formatOK;
    @XmlElement(name = "Properties")
    protected PropertiesType properties;
    @XmlElement(name = "VerifyManifestResults", namespace = "urn:oasis:names:tc:dss:1.0:core:schema")
    protected VerifyManifestResultsType verifyManifestResults;
    @XmlElement(name = "SignatureHasVisibleContent")
    protected Boolean signatureHasVisibleContent;
    @XmlElement(name = "SignatureOK", required = true)
    protected SignatureValidityType signatureOK;
    @XmlElement(name = "CertificatePathValidity", required = true)
    protected CertificatePathValidityType certificatePathValidity;

    /**
     * Ruft den Wert der formatOK-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link VerificationResultType }
     *     
     */
    public VerificationResultType getFormatOK() {
        return formatOK;
    }

    /**
     * Legt den Wert der formatOK-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link VerificationResultType }
     *     
     */
    public void setFormatOK(VerificationResultType value) {
        this.formatOK = value;
    }

    /**
     * Ruft den Wert der properties-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PropertiesType }
     *     
     */
    public PropertiesType getProperties() {
        return properties;
    }

    /**
     * Legt den Wert der properties-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PropertiesType }
     *     
     */
    public void setProperties(PropertiesType value) {
        this.properties = value;
    }

    /**
     * Ruft den Wert der verifyManifestResults-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link VerifyManifestResultsType }
     *     
     */
    public VerifyManifestResultsType getVerifyManifestResults() {
        return verifyManifestResults;
    }

    /**
     * Legt den Wert der verifyManifestResults-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link VerifyManifestResultsType }
     *     
     */
    public void setVerifyManifestResults(VerifyManifestResultsType value) {
        this.verifyManifestResults = value;
    }

    /**
     * Ruft den Wert der signatureHasVisibleContent-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSignatureHasVisibleContent() {
        return signatureHasVisibleContent;
    }

    /**
     * Legt den Wert der signatureHasVisibleContent-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSignatureHasVisibleContent(Boolean value) {
        this.signatureHasVisibleContent = value;
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
