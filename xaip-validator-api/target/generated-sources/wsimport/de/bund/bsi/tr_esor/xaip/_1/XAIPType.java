
package de.bund.bsi.tr_esor.xaip._1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für XAIPType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="XAIPType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="packageHeader" type="{http://www.bsi.bund.de/tr-esor/xaip/1.2}packageHeaderType"/&gt;
 *         &lt;element name="metaDataSection" type="{http://www.bsi.bund.de/tr-esor/xaip/1.2}metaDataSectionType" minOccurs="0"/&gt;
 *         &lt;element name="dataObjectsSection" type="{http://www.bsi.bund.de/tr-esor/xaip/1.2}dataObjectsSectionType" minOccurs="0"/&gt;
 *         &lt;element name="credentialsSection" type="{http://www.bsi.bund.de/tr-esor/xaip/1.2}credentialsSectionType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "XAIPType", propOrder = {
    "packageHeader",
    "metaDataSection",
    "dataObjectsSection",
    "credentialsSection"
})
@XmlSeeAlso({
    DXAIPType.class
})
public class XAIPType {

    @XmlElement(required = true)
    protected PackageHeaderType packageHeader;
    protected MetaDataSectionType metaDataSection;
    protected DataObjectsSectionType dataObjectsSection;
    protected CredentialsSectionType credentialsSection;

    /**
     * Ruft den Wert der packageHeader-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PackageHeaderType }
     *     
     */
    public PackageHeaderType getPackageHeader() {
        return packageHeader;
    }

    /**
     * Legt den Wert der packageHeader-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PackageHeaderType }
     *     
     */
    public void setPackageHeader(PackageHeaderType value) {
        this.packageHeader = value;
    }

    /**
     * Ruft den Wert der metaDataSection-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link MetaDataSectionType }
     *     
     */
    public MetaDataSectionType getMetaDataSection() {
        return metaDataSection;
    }

    /**
     * Legt den Wert der metaDataSection-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link MetaDataSectionType }
     *     
     */
    public void setMetaDataSection(MetaDataSectionType value) {
        this.metaDataSection = value;
    }

    /**
     * Ruft den Wert der dataObjectsSection-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link DataObjectsSectionType }
     *     
     */
    public DataObjectsSectionType getDataObjectsSection() {
        return dataObjectsSection;
    }

    /**
     * Legt den Wert der dataObjectsSection-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link DataObjectsSectionType }
     *     
     */
    public void setDataObjectsSection(DataObjectsSectionType value) {
        this.dataObjectsSection = value;
    }

    /**
     * Ruft den Wert der credentialsSection-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link CredentialsSectionType }
     *     
     */
    public CredentialsSectionType getCredentialsSection() {
        return credentialsSection;
    }

    /**
     * Legt den Wert der credentialsSection-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link CredentialsSectionType }
     *     
     */
    public void setCredentialsSection(CredentialsSectionType value) {
        this.credentialsSection = value;
    }

}
