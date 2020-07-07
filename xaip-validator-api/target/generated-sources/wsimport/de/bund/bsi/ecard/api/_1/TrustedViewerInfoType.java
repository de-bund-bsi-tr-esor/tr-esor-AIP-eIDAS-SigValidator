
package de.bund.bsi.ecard.api._1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für TrustedViewerInfoType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="TrustedViewerInfoType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="TrustedViewerId" type="{http://www.bsi.bund.de/ecard/api/1.1}TrustedViewerIdType" minOccurs="0"/&gt;
 *         &lt;element name="StyleSheet" type="{http://www.bsi.bund.de/ecard/api/1.1}StyleSheetType" minOccurs="0"/&gt;
 *         &lt;element name="IncludeViewerManifest" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TrustedViewerInfoType", propOrder = {
    "trustedViewerId",
    "styleSheet",
    "includeViewerManifest"
})
@XmlSeeAlso({
    TrustedViewerInfo.class
})
public class TrustedViewerInfoType {

    @XmlElement(name = "TrustedViewerId")
    protected String trustedViewerId;
    @XmlElement(name = "StyleSheet")
    protected StyleSheetType styleSheet;
    @XmlElement(name = "IncludeViewerManifest")
    protected Boolean includeViewerManifest;

    /**
     * Ruft den Wert der trustedViewerId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrustedViewerId() {
        return trustedViewerId;
    }

    /**
     * Legt den Wert der trustedViewerId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrustedViewerId(String value) {
        this.trustedViewerId = value;
    }

    /**
     * Ruft den Wert der styleSheet-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link StyleSheetType }
     *     
     */
    public StyleSheetType getStyleSheet() {
        return styleSheet;
    }

    /**
     * Legt den Wert der styleSheet-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link StyleSheetType }
     *     
     */
    public void setStyleSheet(StyleSheetType value) {
        this.styleSheet = value;
    }

    /**
     * Ruft den Wert der includeViewerManifest-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIncludeViewerManifest() {
        return includeViewerManifest;
    }

    /**
     * Legt den Wert der includeViewerManifest-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIncludeViewerManifest(Boolean value) {
        this.includeViewerManifest = value;
    }

}
