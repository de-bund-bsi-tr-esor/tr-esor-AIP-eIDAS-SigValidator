
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;
import de.bund.bsi.ecard.api._1.ConnectionHandle;


/**
 * <p>Java-Klasse für ConnectionHandleType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="ConnectionHandleType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:iso:std:iso-iec:24727:tech:schema}CardApplicationPathType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SlotHandle" type="{urn:iso:std:iso-iec:24727:tech:schema}SlotHandleType" minOccurs="0"/&gt;
 *         &lt;element name="RecognitionInfo" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="CardType" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/&gt;
 *                   &lt;element name="CardIdentifier" type="{http://www.w3.org/2001/XMLSchema}hexBinary" minOccurs="0"/&gt;
 *                   &lt;element name="CaptureTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConnectionHandleType", propOrder = {
    "slotHandle",
    "recognitionInfo"
})
@XmlSeeAlso({
    ConnectionHandle.class
})
public class ConnectionHandleType
    extends CardApplicationPathType
{

    @XmlElement(name = "SlotHandle", type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] slotHandle;
    @XmlElement(name = "RecognitionInfo")
    protected ConnectionHandleType.RecognitionInfo recognitionInfo;

    /**
     * Ruft den Wert der slotHandle-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getSlotHandle() {
        return slotHandle;
    }

    /**
     * Legt den Wert der slotHandle-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSlotHandle(byte[] value) {
        this.slotHandle = value;
    }

    /**
     * Ruft den Wert der recognitionInfo-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link ConnectionHandleType.RecognitionInfo }
     *     
     */
    public ConnectionHandleType.RecognitionInfo getRecognitionInfo() {
        return recognitionInfo;
    }

    /**
     * Legt den Wert der recognitionInfo-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link ConnectionHandleType.RecognitionInfo }
     *     
     */
    public void setRecognitionInfo(ConnectionHandleType.RecognitionInfo value) {
        this.recognitionInfo = value;
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
     *         &lt;element name="CardType" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/&gt;
     *         &lt;element name="CardIdentifier" type="{http://www.w3.org/2001/XMLSchema}hexBinary" minOccurs="0"/&gt;
     *         &lt;element name="CaptureTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
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
        "cardType",
        "cardIdentifier",
        "captureTime"
    })
    public static class RecognitionInfo {

        @XmlElement(name = "CardType")
        @XmlSchemaType(name = "anyURI")
        protected String cardType;
        @XmlElement(name = "CardIdentifier", type = String.class)
        @XmlJavaTypeAdapter(HexBinaryAdapter.class)
        @XmlSchemaType(name = "hexBinary")
        protected byte[] cardIdentifier;
        @XmlElement(name = "CaptureTime")
        @XmlSchemaType(name = "dateTime")
        protected XMLGregorianCalendar captureTime;

        /**
         * Ruft den Wert der cardType-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCardType() {
            return cardType;
        }

        /**
         * Legt den Wert der cardType-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCardType(String value) {
            this.cardType = value;
        }

        /**
         * Ruft den Wert der cardIdentifier-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public byte[] getCardIdentifier() {
            return cardIdentifier;
        }

        /**
         * Legt den Wert der cardIdentifier-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCardIdentifier(byte[] value) {
            this.cardIdentifier = value;
        }

        /**
         * Ruft den Wert der captureTime-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getCaptureTime() {
            return captureTime;
        }

        /**
         * Legt den Wert der captureTime-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setCaptureTime(XMLGregorianCalendar value) {
            this.captureTime = value;
        }

    }

}
