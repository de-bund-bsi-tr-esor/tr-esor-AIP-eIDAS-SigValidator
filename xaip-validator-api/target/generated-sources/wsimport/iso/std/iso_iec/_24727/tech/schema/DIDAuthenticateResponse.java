
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für anonymous complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:iso:std:iso-iec:24727:tech:schema}ResponseType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="AuthenticationProtocolData" type="{urn:iso:std:iso-iec:24727:tech:schema}DIDAuthenticationDataType"/&gt;
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
    "authenticationProtocolData"
})
@XmlRootElement(name = "DIDAuthenticateResponse")
public class DIDAuthenticateResponse
    extends ResponseType
{

    @XmlElement(name = "AuthenticationProtocolData", required = true)
    protected DIDAuthenticationDataType authenticationProtocolData;

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

}
