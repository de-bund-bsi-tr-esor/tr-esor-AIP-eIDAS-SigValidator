
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für ChannelHandleType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="ChannelHandleType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ProtocolTerminationPoint" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/&gt;
 *         &lt;element name="SessionIdentifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Binding" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/&gt;
 *         &lt;element name="PathSecurity" type="{urn:iso:std:iso-iec:24727:tech:schema}PathSecurityType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ChannelHandleType", propOrder = {
    "protocolTerminationPoint",
    "sessionIdentifier",
    "binding",
    "pathSecurity"
})
public class ChannelHandleType {

    @XmlElement(name = "ProtocolTerminationPoint")
    @XmlSchemaType(name = "anyURI")
    protected String protocolTerminationPoint;
    @XmlElement(name = "SessionIdentifier")
    protected String sessionIdentifier;
    @XmlElement(name = "Binding", defaultValue = "http://schemas.xmlsoap.org/soap/http")
    @XmlSchemaType(name = "anyURI")
    protected String binding;
    @XmlElement(name = "PathSecurity")
    protected PathSecurityType pathSecurity;

    /**
     * Ruft den Wert der protocolTerminationPoint-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProtocolTerminationPoint() {
        return protocolTerminationPoint;
    }

    /**
     * Legt den Wert der protocolTerminationPoint-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProtocolTerminationPoint(String value) {
        this.protocolTerminationPoint = value;
    }

    /**
     * Ruft den Wert der sessionIdentifier-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSessionIdentifier() {
        return sessionIdentifier;
    }

    /**
     * Legt den Wert der sessionIdentifier-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSessionIdentifier(String value) {
        this.sessionIdentifier = value;
    }

    /**
     * Ruft den Wert der binding-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBinding() {
        return binding;
    }

    /**
     * Legt den Wert der binding-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBinding(String value) {
        this.binding = value;
    }

    /**
     * Ruft den Wert der pathSecurity-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PathSecurityType }
     *     
     */
    public PathSecurityType getPathSecurity() {
        return pathSecurity;
    }

    /**
     * Legt den Wert der pathSecurity-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PathSecurityType }
     *     
     */
    public void setPathSecurity(PathSecurityType value) {
        this.pathSecurity = value;
    }

}
