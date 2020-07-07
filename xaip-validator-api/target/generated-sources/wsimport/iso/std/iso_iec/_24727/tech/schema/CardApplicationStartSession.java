
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


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
 *         &lt;element name="ConnectionHandle" type="{urn:iso:std:iso-iec:24727:tech:schema}ConnectionHandleType"/&gt;
 *         &lt;element name="DIDScope" type="{urn:iso:std:iso-iec:24727:tech:schema}DIDScopeType" minOccurs="0"/&gt;
 *         &lt;element name="DIDName" type="{urn:iso:std:iso-iec:24727:tech:schema}NameType"/&gt;
 *         &lt;element name="AuthenticationProtocolData" type="{urn:iso:std:iso-iec:24727:tech:schema}DIDAuthenticationDataType"/&gt;
 *         &lt;element name="SAMConnectionHandle" type="{urn:iso:std:iso-iec:24727:tech:schema}ConnectionHandleType" minOccurs="0"/&gt;
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
    "connectionHandle",
    "didScope",
    "didName",
    "authenticationProtocolData",
    "samConnectionHandle"
})
@XmlRootElement(name = "CardApplicationStartSession")
public class CardApplicationStartSession
    extends RequestType
{

    @XmlElement(name = "ConnectionHandle", required = true)
    protected ConnectionHandleType connectionHandle;
    @XmlElement(name = "DIDScope")
    @XmlSchemaType(name = "string")
    protected DIDScopeType didScope;
    @XmlElement(name = "DIDName", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String didName;
    @XmlElement(name = "AuthenticationProtocolData", required = true)
    protected DIDAuthenticationDataType authenticationProtocolData;
    @XmlElement(name = "SAMConnectionHandle")
    protected ConnectionHandleType samConnectionHandle;

    /**
     * Ruft den Wert der connectionHandle-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link ConnectionHandleType }
     *     
     */
    public ConnectionHandleType getConnectionHandle() {
        return connectionHandle;
    }

    /**
     * Legt den Wert der connectionHandle-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link ConnectionHandleType }
     *     
     */
    public void setConnectionHandle(ConnectionHandleType value) {
        this.connectionHandle = value;
    }

    /**
     * Ruft den Wert der didScope-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link DIDScopeType }
     *     
     */
    public DIDScopeType getDIDScope() {
        return didScope;
    }

    /**
     * Legt den Wert der didScope-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link DIDScopeType }
     *     
     */
    public void setDIDScope(DIDScopeType value) {
        this.didScope = value;
    }

    /**
     * Ruft den Wert der didName-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDIDName() {
        return didName;
    }

    /**
     * Legt den Wert der didName-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDIDName(String value) {
        this.didName = value;
    }

    /**
     * Ruft den Wert der authenticationProtocolData-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link DIDAuthenticationDataType }
     *     
     */
    public DIDAuthenticationDataType getAuthenticationProtocolData() {
        return authenticationProtocolData;
    }

    /**
     * Legt den Wert der authenticationProtocolData-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link DIDAuthenticationDataType }
     *     
     */
    public void setAuthenticationProtocolData(DIDAuthenticationDataType value) {
        this.authenticationProtocolData = value;
    }

    /**
     * Ruft den Wert der samConnectionHandle-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link ConnectionHandleType }
     *     
     */
    public ConnectionHandleType getSAMConnectionHandle() {
        return samConnectionHandle;
    }

    /**
     * Legt den Wert der samConnectionHandle-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link ConnectionHandleType }
     *     
     */
    public void setSAMConnectionHandle(ConnectionHandleType value) {
        this.samConnectionHandle = value;
    }

}
