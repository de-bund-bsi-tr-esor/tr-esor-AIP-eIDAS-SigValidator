
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für AltMVDMessagesType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="AltMVDMessagesType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="AuthenticationRequestMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="SuccessMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="AuthenticationFailedMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="EnterNewAuthenticationDataMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="RepeatInputMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ComparisonOfRepeatedDataFailed" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
@XmlType(name = "AltMVDMessagesType", propOrder = {
    "authenticationRequestMessage",
    "successMessage",
    "authenticationFailedMessage",
    "enterNewAuthenticationDataMessage",
    "repeatInputMessage",
    "comparisonOfRepeatedDataFailed",
    "requestConfirmationMessage",
    "cancelMessage"
})
public class AltMVDMessagesType {

    @XmlElement(name = "AuthenticationRequestMessage")
    protected String authenticationRequestMessage;
    @XmlElement(name = "SuccessMessage")
    protected String successMessage;
    @XmlElement(name = "AuthenticationFailedMessage")
    protected String authenticationFailedMessage;
    @XmlElement(name = "EnterNewAuthenticationDataMessage")
    protected String enterNewAuthenticationDataMessage;
    @XmlElement(name = "RepeatInputMessage")
    protected String repeatInputMessage;
    @XmlElement(name = "ComparisonOfRepeatedDataFailed")
    protected String comparisonOfRepeatedDataFailed;
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
     * Ruft den Wert der enterNewAuthenticationDataMessage-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnterNewAuthenticationDataMessage() {
        return enterNewAuthenticationDataMessage;
    }

    /**
     * Legt den Wert der enterNewAuthenticationDataMessage-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnterNewAuthenticationDataMessage(String value) {
        this.enterNewAuthenticationDataMessage = value;
    }

    /**
     * Ruft den Wert der repeatInputMessage-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRepeatInputMessage() {
        return repeatInputMessage;
    }

    /**
     * Legt den Wert der repeatInputMessage-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRepeatInputMessage(String value) {
        this.repeatInputMessage = value;
    }

    /**
     * Ruft den Wert der comparisonOfRepeatedDataFailed-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComparisonOfRepeatedDataFailed() {
        return comparisonOfRepeatedDataFailed;
    }

    /**
     * Legt den Wert der comparisonOfRepeatedDataFailed-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComparisonOfRepeatedDataFailed(String value) {
        this.comparisonOfRepeatedDataFailed = value;
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
