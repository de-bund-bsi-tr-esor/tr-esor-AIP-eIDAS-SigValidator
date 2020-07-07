
package oasis.names.tc.dss._1_0.core.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für DocumentType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="DocumentType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:oasis:names:tc:dss:1.0:core:schema}DocumentBaseType"&gt;
 *       &lt;choice&gt;
 *         &lt;element name="InlineXML" type="{urn:oasis:names:tc:dss:1.0:core:schema}InlineXMLType"/&gt;
 *         &lt;element name="Base64XML" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
 *         &lt;element name="EscapedXML" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element ref="{urn:oasis:names:tc:dss:1.0:core:schema}Base64Data"/&gt;
 *         &lt;element ref="{urn:oasis:names:tc:dss:1.0:core:schema}AttachmentReference"/&gt;
 *       &lt;/choice&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DocumentType", propOrder = {
    "inlineXML",
    "base64XML",
    "escapedXML",
    "base64Data",
    "attachmentReference"
})
public class DocumentType
    extends DocumentBaseType
{

    @XmlElement(name = "InlineXML")
    protected InlineXMLType inlineXML;
    @XmlElement(name = "Base64XML")
    protected byte[] base64XML;
    @XmlElement(name = "EscapedXML")
    protected String escapedXML;
    @XmlElement(name = "Base64Data")
    protected Base64Data base64Data;
    @XmlElement(name = "AttachmentReference")
    protected AttachmentReferenceType attachmentReference;

    /**
     * Ruft den Wert der inlineXML-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link InlineXMLType }
     *     
     */
    public InlineXMLType getInlineXML() {
        return inlineXML;
    }

    /**
     * Legt den Wert der inlineXML-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link InlineXMLType }
     *     
     */
    public void setInlineXML(InlineXMLType value) {
        this.inlineXML = value;
    }

    /**
     * Ruft den Wert der base64XML-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getBase64XML() {
        return base64XML;
    }

    /**
     * Legt den Wert der base64XML-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setBase64XML(byte[] value) {
        this.base64XML = value;
    }

    /**
     * Ruft den Wert der escapedXML-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEscapedXML() {
        return escapedXML;
    }

    /**
     * Legt den Wert der escapedXML-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEscapedXML(String value) {
        this.escapedXML = value;
    }

    /**
     * Ruft den Wert der base64Data-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Base64Data }
     *     
     */
    public Base64Data getBase64Data() {
        return base64Data;
    }

    /**
     * Legt den Wert der base64Data-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Base64Data }
     *     
     */
    public void setBase64Data(Base64Data value) {
        this.base64Data = value;
    }

    /**
     * Ruft den Wert der attachmentReference-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link AttachmentReferenceType }
     *     
     */
    public AttachmentReferenceType getAttachmentReference() {
        return attachmentReference;
    }

    /**
     * Legt den Wert der attachmentReference-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link AttachmentReferenceType }
     *     
     */
    public void setAttachmentReference(AttachmentReferenceType value) {
        this.attachmentReference = value;
    }

}
