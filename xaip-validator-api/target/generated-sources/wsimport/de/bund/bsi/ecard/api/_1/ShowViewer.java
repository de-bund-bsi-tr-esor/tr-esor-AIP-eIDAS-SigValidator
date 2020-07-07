
package de.bund.bsi.ecard.api._1;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import iso.std.iso_iec._24727.tech.schema.ChannelHandleType;
import iso.std.iso_iec._24727.tech.schema.RequestType;
import oasis.names.tc.dss._1_0.core.schema.DocumentType;


/**
 * <p>Java-Klasse für anonymous complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:iso:std:iso-iec:24727:tech:schema}RequestType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ChannelHandle" type="{urn:iso:std:iso-iec:24727:tech:schema}ChannelHandleType" minOccurs="0"/&gt;
 *         &lt;element name="TrustedViewerId" type="{http://www.bsi.bund.de/ecard/api/1.1}TrustedViewerIdType" minOccurs="0"/&gt;
 *         &lt;element name="Document" type="{urn:oasis:names:tc:dss:1.0:core:schema}DocumentType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="StyleSheetContent" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/&gt;
 *         &lt;element name="ViewerMessage" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="FrameMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="BodyMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Timeout" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "channelHandle",
    "trustedViewerId",
    "document",
    "styleSheetContent",
    "viewerMessage",
    "timeout"
})
@XmlRootElement(name = "ShowViewer")
public class ShowViewer
    extends RequestType
{

    @XmlElement(name = "ChannelHandle")
    protected ChannelHandleType channelHandle;
    @XmlElement(name = "TrustedViewerId")
    protected String trustedViewerId;
    @XmlElement(name = "Document")
    protected List<DocumentType> document;
    @XmlElement(name = "StyleSheetContent")
    protected byte[] styleSheetContent;
    @XmlElement(name = "ViewerMessage")
    protected ShowViewer.ViewerMessage viewerMessage;
    @XmlElement(name = "Timeout")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger timeout;

    /**
     * Ruft den Wert der channelHandle-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link ChannelHandleType }
     *     
     */
    public ChannelHandleType getChannelHandle() {
        return channelHandle;
    }

    /**
     * Legt den Wert der channelHandle-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link ChannelHandleType }
     *     
     */
    public void setChannelHandle(ChannelHandleType value) {
        this.channelHandle = value;
    }

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
     * Gets the value of the document property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the document property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDocument().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DocumentType }
     * 
     * 
     */
    public List<DocumentType> getDocument() {
        if (document == null) {
            document = new ArrayList<DocumentType>();
        }
        return this.document;
    }

    /**
     * Ruft den Wert der styleSheetContent-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getStyleSheetContent() {
        return styleSheetContent;
    }

    /**
     * Legt den Wert der styleSheetContent-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setStyleSheetContent(byte[] value) {
        this.styleSheetContent = value;
    }

    /**
     * Ruft den Wert der viewerMessage-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link ShowViewer.ViewerMessage }
     *     
     */
    public ShowViewer.ViewerMessage getViewerMessage() {
        return viewerMessage;
    }

    /**
     * Legt den Wert der viewerMessage-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link ShowViewer.ViewerMessage }
     *     
     */
    public void setViewerMessage(ShowViewer.ViewerMessage value) {
        this.viewerMessage = value;
    }

    /**
     * Ruft den Wert der timeout-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTimeout() {
        return timeout;
    }

    /**
     * Legt den Wert der timeout-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTimeout(BigInteger value) {
        this.timeout = value;
    }


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
     *         &lt;element name="FrameMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="BodyMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
        "frameMessage",
        "bodyMessage"
    })
    public static class ViewerMessage {

        @XmlElement(name = "FrameMessage")
        protected String frameMessage;
        @XmlElement(name = "BodyMessage")
        protected String bodyMessage;

        /**
         * Ruft den Wert der frameMessage-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFrameMessage() {
            return frameMessage;
        }

        /**
         * Legt den Wert der frameMessage-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFrameMessage(String value) {
            this.frameMessage = value;
        }

        /**
         * Ruft den Wert der bodyMessage-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getBodyMessage() {
            return bodyMessage;
        }

        /**
         * Legt den Wert der bodyMessage-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setBodyMessage(String value) {
            this.bodyMessage = value;
        }

    }

}
