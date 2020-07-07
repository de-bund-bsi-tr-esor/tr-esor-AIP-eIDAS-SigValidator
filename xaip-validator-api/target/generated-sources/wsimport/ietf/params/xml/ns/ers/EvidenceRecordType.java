
package ietf.params.xml.ns.ers;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für EvidenceRecordType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="EvidenceRecordType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="EncryptionInformation" type="{urn:ietf:params:xml:ns:ers}EncryptionInfo" minOccurs="0"/&gt;
 *         &lt;element name="SupportingInformationList" type="{urn:ietf:params:xml:ns:ers}SupportingInformationType" minOccurs="0"/&gt;
 *         &lt;element name="ArchiveTimeStampSequence" type="{urn:ietf:params:xml:ns:ers}ArchiveTimeStampSequenceType"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="Version" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" fixed="1.0" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EvidenceRecordType", propOrder = {
    "encryptionInformation",
    "supportingInformationList",
    "archiveTimeStampSequence"
})
public class EvidenceRecordType {

    @XmlElement(name = "EncryptionInformation")
    protected EncryptionInfo encryptionInformation;
    @XmlElement(name = "SupportingInformationList")
    protected SupportingInformationType supportingInformationList;
    @XmlElement(name = "ArchiveTimeStampSequence", required = true)
    protected ArchiveTimeStampSequenceType archiveTimeStampSequence;
    @XmlAttribute(name = "Version", required = true)
    protected BigDecimal version;

    /**
     * Ruft den Wert der encryptionInformation-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link EncryptionInfo }
     *     
     */
    public EncryptionInfo getEncryptionInformation() {
        return encryptionInformation;
    }

    /**
     * Legt den Wert der encryptionInformation-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link EncryptionInfo }
     *     
     */
    public void setEncryptionInformation(EncryptionInfo value) {
        this.encryptionInformation = value;
    }

    /**
     * Ruft den Wert der supportingInformationList-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link SupportingInformationType }
     *     
     */
    public SupportingInformationType getSupportingInformationList() {
        return supportingInformationList;
    }

    /**
     * Legt den Wert der supportingInformationList-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link SupportingInformationType }
     *     
     */
    public void setSupportingInformationList(SupportingInformationType value) {
        this.supportingInformationList = value;
    }

    /**
     * Ruft den Wert der archiveTimeStampSequence-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link ArchiveTimeStampSequenceType }
     *     
     */
    public ArchiveTimeStampSequenceType getArchiveTimeStampSequence() {
        return archiveTimeStampSequence;
    }

    /**
     * Legt den Wert der archiveTimeStampSequence-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link ArchiveTimeStampSequenceType }
     *     
     */
    public void setArchiveTimeStampSequence(ArchiveTimeStampSequenceType value) {
        this.archiveTimeStampSequence = value;
    }

    /**
     * Ruft den Wert der version-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getVersion() {
        if (version == null) {
            return new BigDecimal("1.0");
        } else {
            return version;
        }
    }

    /**
     * Legt den Wert der version-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setVersion(BigDecimal value) {
        this.version = value;
    }

}
