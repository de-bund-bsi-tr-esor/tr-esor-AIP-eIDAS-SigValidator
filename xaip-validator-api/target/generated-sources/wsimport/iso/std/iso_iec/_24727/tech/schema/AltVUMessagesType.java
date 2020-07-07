
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für AltVUMessagesType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="AltVUMessagesType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="AuthenticationRequestMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="SuccessMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="AuthenticationFailedMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="RequestConfirmationMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CancelMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AltVUMessagesType", propOrder = {
    "authenticationRequestMessage",
    "successMessage",
    "authenticationFailedMessage",
    "requestConfirmationMessage",
    "cancelMessage"
})
public class AltVUMessagesType {

    @XmlElement(name = "AuthenticationRequestMessage")
    protected String authenticationRequestMessage;
    @XmlElement(name = "SuccessMessage")
    protected String successMessage;
    @XmlElement(name = "AuthenticationFailedMessage")
    protected String authenticationFailedMessage;
    @XmlElement(name = "RequestConfirmationMessage")
    protected String requestConfirmationMessage;
    @XmlElement(name = "CancelMessage")
    protected String cancelMessage;

    /**
     * Ruft den Wert der authenticationRequestMessage-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthenticationRequestMessage() {
        return authenticationRequestMessage;
    }

    /**
     * Legt den Wert der authenticationRequestMessage-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthenticationRequestMessage(String value) {
        this.authenticationRequestMessage = value;
    }

    /**
     * Ruft den Wert der successMessage-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSuccessMessage() {
        return successMessage;
    }

    /**
     * Legt den Wert der successMessage-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSuccessMessage(String value) {
        this.successMessage = value;
    }

    /**
     * Ruft den Wert der authenticationFailedMessage-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthenticationFailedMessage() {
        return authenticationFailedMessage;
    }

    /**
     * Legt den Wert der authenticationFailedMessage-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthenticationFailedMessage(String value) {
        this.authenticationFailedMessage = value;
    }

    /**
     * Ruft den Wert der requestConfirmationMessage-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestConfirmationMessage() {
        return requestConfirmationMessage;
    }

    /**
     * Legt den Wert der requestConfirmationMessage-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestConfirmationMessage(String value) {
        this.requestConfirmationMessage = value;
    }

    /**
     * Ruft den Wert der cancelMessage-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCancelMessage() {
        return cancelMessage;
    }

    /**
     * Legt den Wert der cancelMessage-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCancelMessage(String value) {
        this.cancelMessage = value;
    }

}
