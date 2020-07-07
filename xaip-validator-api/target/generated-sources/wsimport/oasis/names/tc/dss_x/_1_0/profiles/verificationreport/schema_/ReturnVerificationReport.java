
package oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für anonymous complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="IncludeVerifier" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="IncludeCertificateValues" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="IncludeRevocationValues" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="ExpandBinaryValues" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="ReportDetailLevel" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "includeVerifier",
    "includeCertificateValues",
    "includeRevocationValues",
    "expandBinaryValues",
    "reportDetailLevel"
})
@XmlRootElement(name = "ReturnVerificationReport")
public class ReturnVerificationReport {

    @XmlElement(name = "IncludeVerifier", defaultValue = "true")
    protected Boolean includeVerifier;
    @XmlElement(name = "IncludeCertificateValues", defaultValue = "false")
    protected Boolean includeCertificateValues;
    @XmlElement(name = "IncludeRevocationValues", defaultValue = "false")
    protected Boolean includeRevocationValues;
    @XmlElement(name = "ExpandBinaryValues", defaultValue = "false")
    protected Boolean expandBinaryValues;
    @XmlElement(name = "ReportDetailLevel", defaultValue = "urn:oasis:names:tc:dss:1.0:profiles:verificationreport:reportdetail:allDetails")
    @XmlSchemaType(name = "anyURI")
    protected String reportDetailLevel;

    /**
     * Ruft den Wert der includeVerifier-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIncludeVerifier() {
        return includeVerifier;
    }

    /**
     * Legt den Wert der includeVerifier-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIncludeVerifier(Boolean value) {
        this.includeVerifier = value;
    }

    /**
     * Ruft den Wert der includeCertificateValues-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIncludeCertificateValues() {
        return includeCertificateValues;
    }

    /**
     * Legt den Wert der includeCertificateValues-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIncludeCertificateValues(Boolean value) {
        this.includeCertificateValues = value;
    }

    /**
     * Ruft den Wert der includeRevocationValues-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIncludeRevocationValues() {
        return includeRevocationValues;
    }

    /**
     * Legt den Wert der includeRevocationValues-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIncludeRevocationValues(Boolean value) {
        this.includeRevocationValues = value;
    }

    /**
     * Ruft den Wert der expandBinaryValues-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isExpandBinaryValues() {
        return expandBinaryValues;
    }

    /**
     * Legt den Wert der expandBinaryValues-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setExpandBinaryValues(Boolean value) {
        this.expandBinaryValues = value;
    }

    /**
     * Ruft den Wert der reportDetailLevel-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReportDetailLevel() {
        return reportDetailLevel;
    }

    /**
     * Legt den Wert der reportDetailLevel-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReportDetailLevel(String value) {
        this.reportDetailLevel = value;
    }

}
