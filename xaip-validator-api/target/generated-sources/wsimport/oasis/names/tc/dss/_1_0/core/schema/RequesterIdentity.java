
package oasis.names.tc.dss._1_0.core.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import oasis.names.tc.saml._1_0.assertion.NameIdentifierType;


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
 *         &lt;element name="Name" type="{urn:oasis:names:tc:SAML:1.0:assertion}NameIdentifierType"/&gt;
 *         &lt;element name="SupportingInfo" type="{urn:oasis:names:tc:dss:1.0:core:schema}AnyType" minOccurs="0"/&gt;
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
    "name",
    "supportingInfo"
})
@XmlRootElement(name = "RequesterIdentity")
public class RequesterIdentity {

    @XmlElement(name = "Name", required = true)
    protected NameIdentifierType name;
    @XmlElement(name = "SupportingInfo")
    protected AnyType supportingInfo;

    /**
     * Ruft den Wert der name-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link NameIdentifierType }
     *     
     */
    public NameIdentifierType getName() {
        return name;
    }

    /**
     * Legt den Wert der name-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link NameIdentifierType }
     *     
     */
    public void setName(NameIdentifierType value) {
        this.name = value;
    }

    /**
     * Ruft den Wert der supportingInfo-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link AnyType }
     *     
     */
    public AnyType getSupportingInfo() {
        return supportingInfo;
    }

    /**
     * Legt den Wert der supportingInfo-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link AnyType }
     *     
     */
    public void setSupportingInfo(AnyType value) {
        this.supportingInfo = value;
    }

}
