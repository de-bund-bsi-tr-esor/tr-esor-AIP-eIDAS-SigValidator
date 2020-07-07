
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für ActionNameType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="ActionNameType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice&gt;
 *         &lt;element name="APIAccessEntryPoint" type="{urn:iso:std:iso-iec:24727:tech:schema}APIAccessEntryPointName"/&gt;
 *         &lt;element name="ConnectionServiceAction" type="{urn:iso:std:iso-iec:24727:tech:schema}ConnectionServiceActionName"/&gt;
 *         &lt;element name="CardApplicationServiceAction" type="{urn:iso:std:iso-iec:24727:tech:schema}CardApplicationServiceActionName"/&gt;
 *         &lt;element name="NamedDataServiceAction" type="{urn:iso:std:iso-iec:24727:tech:schema}NamedDataServiceActionName"/&gt;
 *         &lt;element name="CryptographicServiceAction" type="{urn:iso:std:iso-iec:24727:tech:schema}CryptographicServiceActionName"/&gt;
 *         &lt;element name="DifferentialIdentityServiceAction" type="{urn:iso:std:iso-iec:24727:tech:schema}DifferentialIdentityServiceActionName"/&gt;
 *         &lt;element name="AuthorizationServiceAction" type="{urn:iso:std:iso-iec:24727:tech:schema}AuthorizationServiceActionName"/&gt;
 *         &lt;element name="LoadedAction" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/choice&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ActionNameType", propOrder = {
    "apiAccessEntryPoint",
    "connectionServiceAction",
    "cardApplicationServiceAction",
    "namedDataServiceAction",
    "cryptographicServiceAction",
    "differentialIdentityServiceAction",
    "authorizationServiceAction",
    "loadedAction"
})
public class ActionNameType {

    @XmlElement(name = "APIAccessEntryPoint")
    @XmlSchemaType(name = "string")
    protected APIAccessEntryPointName apiAccessEntryPoint;
    @XmlElement(name = "ConnectionServiceAction")
    @XmlSchemaType(name = "string")
    protected ConnectionServiceActionName connectionServiceAction;
    @XmlElement(name = "CardApplicationServiceAction")
    @XmlSchemaType(name = "string")
    protected CardApplicationServiceActionName cardApplicationServiceAction;
    @XmlElement(name = "NamedDataServiceAction")
    @XmlSchemaType(name = "string")
    protected NamedDataServiceActionName namedDataServiceAction;
    @XmlElement(name = "CryptographicServiceAction")
    @XmlSchemaType(name = "string")
    protected CryptographicServiceActionName cryptographicServiceAction;
    @XmlElement(name = "DifferentialIdentityServiceAction")
    @XmlSchemaType(name = "string")
    protected DifferentialIdentityServiceActionName differentialIdentityServiceAction;
    @XmlElement(name = "AuthorizationServiceAction")
    @XmlSchemaType(name = "string")
    protected AuthorizationServiceActionName authorizationServiceAction;
    @XmlElement(name = "LoadedAction")
    protected String loadedAction;

    /**
     * Ruft den Wert der apiAccessEntryPoint-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link APIAccessEntryPointName }
     *     
     */
    public APIAccessEntryPointName getAPIAccessEntryPoint() {
        return apiAccessEntryPoint;
    }

    /**
     * Legt den Wert der apiAccessEntryPoint-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link APIAccessEntryPointName }
     *     
     */
    public void setAPIAccessEntryPoint(APIAccessEntryPointName value) {
        this.apiAccessEntryPoint = value;
    }

    /**
     * Ruft den Wert der connectionServiceAction-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link ConnectionServiceActionName }
     *     
     */
    public ConnectionServiceActionName getConnectionServiceAction() {
        return connectionServiceAction;
    }

    /**
     * Legt den Wert der connectionServiceAction-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link ConnectionServiceActionName }
     *     
     */
    public void setConnectionServiceAction(ConnectionServiceActionName value) {
        this.connectionServiceAction = value;
    }

    /**
     * Ruft den Wert der cardApplicationServiceAction-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link CardApplicationServiceActionName }
     *     
     */
    public CardApplicationServiceActionName getCardApplicationServiceAction() {
        return cardApplicationServiceAction;
    }

    /**
     * Legt den Wert der cardApplicationServiceAction-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link CardApplicationServiceActionName }
     *     
     */
    public void setCardApplicationServiceAction(CardApplicationServiceActionName value) {
        this.cardApplicationServiceAction = value;
    }

    /**
     * Ruft den Wert der namedDataServiceAction-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link NamedDataServiceActionName }
     *     
     */
    public NamedDataServiceActionName getNamedDataServiceAction() {
        return namedDataServiceAction;
    }

    /**
     * Legt den Wert der namedDataServiceAction-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link NamedDataServiceActionName }
     *     
     */
    public void setNamedDataServiceAction(NamedDataServiceActionName value) {
        this.namedDataServiceAction = value;
    }

    /**
     * Ruft den Wert der cryptographicServiceAction-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link CryptographicServiceActionName }
     *     
     */
    public CryptographicServiceActionName getCryptographicServiceAction() {
        return cryptographicServiceAction;
    }

    /**
     * Legt den Wert der cryptographicServiceAction-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link CryptographicServiceActionName }
     *     
     */
    public void setCryptographicServiceAction(CryptographicServiceActionName value) {
        this.cryptographicServiceAction = value;
    }

    /**
     * Ruft den Wert der differentialIdentityServiceAction-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link DifferentialIdentityServiceActionName }
     *     
     */
    public DifferentialIdentityServiceActionName getDifferentialIdentityServiceAction() {
        return differentialIdentityServiceAction;
    }

    /**
     * Legt den Wert der differentialIdentityServiceAction-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link DifferentialIdentityServiceActionName }
     *     
     */
    public void setDifferentialIdentityServiceAction(DifferentialIdentityServiceActionName value) {
        this.differentialIdentityServiceAction = value;
    }

    /**
     * Ruft den Wert der authorizationServiceAction-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link AuthorizationServiceActionName }
     *     
     */
    public AuthorizationServiceActionName getAuthorizationServiceAction() {
        return authorizationServiceAction;
    }

    /**
     * Legt den Wert der authorizationServiceAction-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link AuthorizationServiceActionName }
     *     
     */
    public void setAuthorizationServiceAction(AuthorizationServiceActionName value) {
        this.authorizationServiceAction = value;
    }

    /**
     * Ruft den Wert der loadedAction-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoadedAction() {
        return loadedAction;
    }

    /**
     * Legt den Wert der loadedAction-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoadedAction(String value) {
        this.loadedAction = value;
    }

}
