
package de.bund.bsi.tr_esor.xaip._1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java-Klasse für versionManifestType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="versionManifestType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="versionInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="preservationInfo" type="{http://www.bsi.bund.de/tr-esor/xaip/1.2}preservationInfoType"/&gt;
 *         &lt;element name="submissionInfo" type="{http://www.bsi.bund.de/tr-esor/xaip/1.2}submissionInfoType" minOccurs="0"/&gt;
 *         &lt;element name="packageInfoUnit" type="{http://www.bsi.bund.de/tr-esor/xaip/1.2}packageInfoUnitType" maxOccurs="unbounded"/&gt;
 *         &lt;element name="extension" type="{http://www.bsi.bund.de/tr-esor/xaip/1.2}extensionType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="VersionID" use="required" type="{http://www.w3.org/2001/XMLSchema}ID" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "versionManifestType", propOrder = {
    "versionInfo",
    "preservationInfo",
    "submissionInfo",
    "packageInfoUnit",
    "extension"
})
public class VersionManifestType {

    protected String versionInfo;
    @XmlElement(required = true)
    protected PreservationInfoType preservationInfo;
    protected SubmissionInfoType submissionInfo;
    @XmlElement(required = true)
    protected List<PackageInfoUnitType> packageInfoUnit;
    protected ExtensionType extension;
    @XmlAttribute(name = "VersionID", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String versionID;

    /**
     * Ruft den Wert der versionInfo-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersionInfo() {
        return versionInfo;
    }

    /**
     * Legt den Wert der versionInfo-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersionInfo(String value) {
        this.versionInfo = value;
    }

    /**
     * Ruft den Wert der preservationInfo-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PreservationInfoType }
     *     
     */
    public PreservationInfoType getPreservationInfo() {
        return preservationInfo;
    }

    /**
     * Legt den Wert der preservationInfo-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PreservationInfoType }
     *     
     */
    public void setPreservationInfo(PreservationInfoType value) {
        this.preservationInfo = value;
    }

    /**
     * Ruft den Wert der submissionInfo-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link SubmissionInfoType }
     *     
     */
    public SubmissionInfoType getSubmissionInfo() {
        return submissionInfo;
    }

    /**
     * Legt den Wert der submissionInfo-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link SubmissionInfoType }
     *     
     */
    public void setSubmissionInfo(SubmissionInfoType value) {
        this.submissionInfo = value;
    }

    /**
     * Gets the value of the packageInfoUnit property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the packageInfoUnit property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPackageInfoUnit().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PackageInfoUnitType }
     * 
     * 
     */
    public List<PackageInfoUnitType> getPackageInfoUnit() {
        if (packageInfoUnit == null) {
            packageInfoUnit = new ArrayList<PackageInfoUnitType>();
        }
        return this.packageInfoUnit;
    }

    /**
     * Ruft den Wert der extension-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link ExtensionType }
     *     
     */
    public ExtensionType getExtension() {
        return extension;
    }

    /**
     * Legt den Wert der extension-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link ExtensionType }
     *     
     */
    public void setExtension(ExtensionType value) {
        this.extension = value;
    }

    /**
     * Ruft den Wert der versionID-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersionID() {
        return versionID;
    }

    /**
     * Legt den Wert der versionID-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersionID(String value) {
        this.versionID = value;
    }

}
