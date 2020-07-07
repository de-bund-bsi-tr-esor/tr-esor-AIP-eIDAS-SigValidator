
package de.bund.bsi.tr_esor.xaip._1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import oasis.names.tc.saml._2_0.assertion.NameIDType;


/**
 * <p>Java-Klasse für submissionInfoType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="submissionInfoType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="clientID" type="{urn:oasis:names:tc:SAML:2.0:assertion}NameIDType"/&gt;
 *         &lt;element name="submissionUnit" type="{urn:oasis:names:tc:SAML:2.0:assertion}NameIDType" minOccurs="0"/&gt;
 *         &lt;element name="submissionAuthor" type="{urn:oasis:names:tc:SAML:2.0:assertion}NameIDType" minOccurs="0"/&gt;
 *         &lt;element name="submissionTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="extension" type="{http://www.bsi.bund.de/tr-esor/xaip/1.2}extensionType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "submissionInfoType", propOrder = {
    "clientID",
    "submissionUnit",
    "submissionAuthor",
    "submissionTime",
    "extension"
})
public class SubmissionInfoType {

    @XmlElement(required = true)
    protected NameIDType clientID;
    protected NameIDType submissionUnit;
    protected NameIDType submissionAuthor;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar submissionTime;
    protected ExtensionType extension;

    /**
     * Ruft den Wert der clientID-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link NameIDType }
     *     
     */
    public NameIDType getClientID() {
        return clientID;
    }

    /**
     * Legt den Wert der clientID-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link NameIDType }
     *     
     */
    public void setClientID(NameIDType value) {
        this.clientID = value;
    }

    /**
     * Ruft den Wert der submissionUnit-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link NameIDType }
     *     
     */
    public NameIDType getSubmissionUnit() {
        return submissionUnit;
    }

    /**
     * Legt den Wert der submissionUnit-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link NameIDType }
     *     
     */
    public void setSubmissionUnit(NameIDType value) {
        this.submissionUnit = value;
    }

    /**
     * Ruft den Wert der submissionAuthor-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link NameIDType }
     *     
     */
    public NameIDType getSubmissionAuthor() {
        return submissionAuthor;
    }

    /**
     * Legt den Wert der submissionAuthor-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link NameIDType }
     *     
     */
    public void setSubmissionAuthor(NameIDType value) {
        this.submissionAuthor = value;
    }

    /**
     * Ruft den Wert der submissionTime-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSubmissionTime() {
        return submissionTime;
    }

    /**
     * Legt den Wert der submissionTime-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSubmissionTime(XMLGregorianCalendar value) {
        this.submissionTime = value;
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

}
