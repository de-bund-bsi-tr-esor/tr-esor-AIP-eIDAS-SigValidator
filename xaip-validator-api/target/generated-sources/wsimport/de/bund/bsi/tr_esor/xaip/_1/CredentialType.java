
package de.bund.bsi.tr_esor.xaip._1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import oasis.names.tc.dss._1_0.core.schema.SignatureObject;
import oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_.VerificationReportType;
import org.etsi.uri._01903.v1_3.CertificateValuesType;
import org.etsi.uri._01903.v1_3.RevocationValuesType;


/**
 * <p>Java-Klasse für credentialType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="credentialType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice&gt;
 *         &lt;element ref="{urn:oasis:names:tc:dss:1.0:core:schema}SignatureObject"/&gt;
 *         &lt;element name="certificateValues" type="{http://uri.etsi.org/01903/v1.3.2#}CertificateValuesType"/&gt;
 *         &lt;element name="revocationValues" type="{http://uri.etsi.org/01903/v1.3.2#}RevocationValuesType"/&gt;
 *         &lt;element ref="{http://www.bsi.bund.de/tr-esor/xaip/1.2}evidenceRecord"/&gt;
 *         &lt;element ref="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}VerificationReport"/&gt;
 *         &lt;element name="other" type="{http://www.bsi.bund.de/tr-esor/xaip/1.2}extensionType"/&gt;
 *       &lt;/choice&gt;
 *       &lt;attribute name="relatedObjects" type="{http://www.w3.org/2001/XMLSchema}IDREFS" /&gt;
 *       &lt;attribute name="credentialID" use="required" type="{http://www.w3.org/2001/XMLSchema}ID" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "credentialType", propOrder = {
    "signatureObject",
    "certificateValues",
    "revocationValues",
    "evidenceRecord",
    "verificationReport",
    "other"
})
public class CredentialType {

    @XmlElement(name = "SignatureObject", namespace = "urn:oasis:names:tc:dss:1.0:core:schema")
    protected SignatureObject signatureObject;
    protected CertificateValuesType certificateValues;
    protected RevocationValuesType revocationValues;
    protected EvidenceRecordType evidenceRecord;
    @XmlElement(name = "VerificationReport", namespace = "urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#")
    protected VerificationReportType verificationReport;
    protected ExtensionType other;
    @XmlAttribute(name = "relatedObjects")
    @XmlIDREF
    @XmlSchemaType(name = "IDREFS")
    protected List<Object> relatedObjects;
    @XmlAttribute(name = "credentialID", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String credentialID;

    /**
     * Ruft den Wert der signatureObject-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link SignatureObject }
     *     
     */
    public SignatureObject getSignatureObject() {
        return signatureObject;
    }

    /**
     * Legt den Wert der signatureObject-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link SignatureObject }
     *     
     */
    public void setSignatureObject(SignatureObject value) {
        this.signatureObject = value;
    }

    /**
     * Ruft den Wert der certificateValues-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link CertificateValuesType }
     *     
     */
    public CertificateValuesType getCertificateValues() {
        return certificateValues;
    }

    /**
     * Legt den Wert der certificateValues-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link CertificateValuesType }
     *     
     */
    public void setCertificateValues(CertificateValuesType value) {
        this.certificateValues = value;
    }

    /**
     * Ruft den Wert der revocationValues-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link RevocationValuesType }
     *     
     */
    public RevocationValuesType getRevocationValues() {
        return revocationValues;
    }

    /**
     * Legt den Wert der revocationValues-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link RevocationValuesType }
     *     
     */
    public void setRevocationValues(RevocationValuesType value) {
        this.revocationValues = value;
    }

    /**
     * Ruft den Wert der evidenceRecord-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link EvidenceRecordType }
     *     
     */
    public EvidenceRecordType getEvidenceRecord() {
        return evidenceRecord;
    }

    /**
     * Legt den Wert der evidenceRecord-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link EvidenceRecordType }
     *     
     */
    public void setEvidenceRecord(EvidenceRecordType value) {
        this.evidenceRecord = value;
    }

    /**
     * Ruft den Wert der verificationReport-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link VerificationReportType }
     *     
     */
    public VerificationReportType getVerificationReport() {
        return verificationReport;
    }

    /**
     * Legt den Wert der verificationReport-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link VerificationReportType }
     *     
     */
    public void setVerificationReport(VerificationReportType value) {
        this.verificationReport = value;
    }

    /**
     * Ruft den Wert der other-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link ExtensionType }
     *     
     */
    public ExtensionType getOther() {
        return other;
    }

    /**
     * Legt den Wert der other-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link ExtensionType }
     *     
     */
    public void setOther(ExtensionType value) {
        this.other = value;
    }

    /**
     * Gets the value of the relatedObjects property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the relatedObjects property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRelatedObjects().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Object }
     * 
     * 
     */
    public List<Object> getRelatedObjects() {
        if (relatedObjects == null) {
            relatedObjects = new ArrayList<Object>();
        }
        return this.relatedObjects;
    }

    /**
     * Ruft den Wert der credentialID-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCredentialID() {
        return credentialID;
    }

    /**
     * Legt den Wert der credentialID-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCredentialID(String value) {
        this.credentialID = value;
    }

}
