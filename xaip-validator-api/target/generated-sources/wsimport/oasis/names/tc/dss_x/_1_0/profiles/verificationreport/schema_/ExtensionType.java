
package oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import oasis.names.tc.dss._1_0.core.schema.AnyType;
import org.etsi.uri._01903.v1_3.ObjectIdentifierType;


/**
 * <p>Java-Klasse für ExtensionType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="ExtensionType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ExtnId" type="{http://uri.etsi.org/01903/v1.3.2#}ObjectIdentifierType"/&gt;
 *         &lt;element name="Critical" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="ExtnValue" type="{urn:oasis:names:tc:dss:1.0:core:schema}AnyType" minOccurs="0"/&gt;
 *         &lt;element name="ExtensionOK" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}VerificationResultType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExtensionType", propOrder = {
    "extnId",
    "critical",
    "extnValue",
    "extensionOK"
})
public class ExtensionType {

    @XmlElement(name = "ExtnId", required = true)
    protected ObjectIdentifierType extnId;
    @XmlElement(name = "Critical")
    protected boolean critical;
    @XmlElement(name = "ExtnValue")
    protected AnyType extnValue;
    @XmlElement(name = "ExtensionOK", required = true)
    protected VerificationResultType extensionOK;

    /**
     * Ruft den Wert der extnId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link ObjectIdentifierType }
     *     
     */
    public ObjectIdentifierType getExtnId() {
        return extnId;
    }

    /**
     * Legt den Wert der extnId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectIdentifierType }
     *     
     */
    public void setExtnId(ObjectIdentifierType value) {
        this.extnId = value;
    }

    /**
     * Ruft den Wert der critical-Eigenschaft ab.
     * 
     */
    public boolean isCritical() {
        return critical;
    }

    /**
     * Legt den Wert der critical-Eigenschaft fest.
     * 
     */
    public void setCritical(boolean value) {
        this.critical = value;
    }

    /**
     * Ruft den Wert der extnValue-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link AnyType }
     *     
     */
    public AnyType getExtnValue() {
        return extnValue;
    }

    /**
     * Legt den Wert der extnValue-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link AnyType }
     *     
     */
    public void setExtnValue(AnyType value) {
        this.extnValue = value;
    }

    /**
     * Ruft den Wert der extensionOK-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link VerificationResultType }
     *     
     */
    public VerificationResultType getExtensionOK() {
        return extensionOK;
    }

    /**
     * Legt den Wert der extensionOK-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link VerificationResultType }
     *     
     */
    public void setExtensionOK(VerificationResultType value) {
        this.extensionOK = value;
    }

}
