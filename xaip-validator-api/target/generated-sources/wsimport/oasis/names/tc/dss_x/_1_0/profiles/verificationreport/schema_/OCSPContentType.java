
package oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java-Klasse für OCSPContentType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="OCSPContentType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Version" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="ResponderID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="producedAt" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="Responses"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence maxOccurs="unbounded" minOccurs="0"&gt;
 *                   &lt;element name="SingleResponse" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}SingleResponseType"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="ResponseExtensions" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}ExtensionsType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OCSPContentType", propOrder = {
    "version",
    "responderID",
    "producedAt",
    "responses",
    "responseExtensions"
})
public class OCSPContentType {

    @XmlElement(name = "Version", required = true)
    protected BigInteger version;
    @XmlElement(name = "ResponderID", required = true)
    protected String responderID;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar producedAt;
    @XmlElement(name = "Responses", required = true)
    protected OCSPContentType.Responses responses;
    @XmlElement(name = "ResponseExtensions")
    protected ExtensionsType responseExtensions;

    /**
     * Ruft den Wert der version-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getVersion() {
        return version;
    }

    /**
     * Legt den Wert der version-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setVersion(BigInteger value) {
        this.version = value;
    }

    /**
     * Ruft den Wert der responderID-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponderID() {
        return responderID;
    }

    /**
     * Legt den Wert der responderID-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponderID(String value) {
        this.responderID = value;
    }

    /**
     * Ruft den Wert der producedAt-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getProducedAt() {
        return producedAt;
    }

    /**
     * Legt den Wert der producedAt-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setProducedAt(XMLGregorianCalendar value) {
        this.producedAt = value;
    }

    /**
     * Ruft den Wert der responses-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link OCSPContentType.Responses }
     *     
     */
    public OCSPContentType.Responses getResponses() {
        return responses;
    }

    /**
     * Legt den Wert der responses-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link OCSPContentType.Responses }
     *     
     */
    public void setResponses(OCSPContentType.Responses value) {
        this.responses = value;
    }

    /**
     * Ruft den Wert der responseExtensions-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link ExtensionsType }
     *     
     */
    public ExtensionsType getResponseExtensions() {
        return responseExtensions;
    }

    /**
     * Legt den Wert der responseExtensions-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link ExtensionsType }
     *     
     */
    public void setResponseExtensions(ExtensionsType value) {
        this.responseExtensions = value;
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
     *       &lt;sequence maxOccurs="unbounded" minOccurs="0"&gt;
     *         &lt;element name="SingleResponse" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}SingleResponseType"/&gt;
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
        "singleResponse"
    })
    public static class Responses {

        @XmlElement(name = "SingleResponse")
        protected List<SingleResponseType> singleResponse;

        /**
         * Gets the value of the singleResponse property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the singleResponse property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getSingleResponse().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link SingleResponseType }
         * 
         * 
         */
        public List<SingleResponseType> getSingleResponse() {
            if (singleResponse == null) {
                singleResponse = new ArrayList<SingleResponseType>();
            }
            return this.singleResponse;
        }

    }

}
