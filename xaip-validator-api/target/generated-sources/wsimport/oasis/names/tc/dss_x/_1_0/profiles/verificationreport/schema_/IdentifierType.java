
package oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import oasis.names.tc.dss._1_0.core.schema.AnyType;
import oasis.names.tc.saml._1_0.assertion.NameIdentifierType;
import oasis.names.tc.saml._2_0.assertion.NameIDType;
import org.w3._2000._09.xmldsig_.X509DataType;


/**
 * <p>Java-Klasse für IdentifierType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="IdentifierType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://www.w3.org/2000/09/xmldsig#}X509Data" minOccurs="0"/&gt;
 *         &lt;element name="SAMLv1Identifier" type="{urn:oasis:names:tc:SAML:1.0:assertion}NameIdentifierType" minOccurs="0"/&gt;
 *         &lt;element name="SAMLv2Identifier" type="{urn:oasis:names:tc:SAML:2.0:assertion}NameIDType" minOccurs="0"/&gt;
 *         &lt;element name="Other" type="{urn:oasis:names:tc:dss:1.0:core:schema}AnyType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IdentifierType", propOrder = {
    "x509Data",
    "samLv1Identifier",
    "samLv2Identifier",
    "other"
})
public class IdentifierType {

    @XmlElement(name = "X509Data", namespace = "http://www.w3.org/2000/09/xmldsig#")
    protected X509DataType x509Data;
    @XmlElement(name = "SAMLv1Identifier")
    protected NameIdentifierType samLv1Identifier;
    @XmlElement(name = "SAMLv2Identifier")
    protected NameIDType samLv2Identifier;
    @XmlElement(name = "Other")
    protected AnyType other;

    /**
     * Ruft den Wert der x509Data-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link X509DataType }
     *     
     */
    public X509DataType getX509Data() {
        return x509Data;
    }

    /**
     * Legt den Wert der x509Data-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link X509DataType }
     *     
     */
    public void setX509Data(X509DataType value) {
        this.x509Data = value;
    }

    /**
     * Ruft den Wert der samLv1Identifier-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link NameIdentifierType }
     *     
     */
    public NameIdentifierType getSAMLv1Identifier() {
        return samLv1Identifier;
    }

    /**
     * Legt den Wert der samLv1Identifier-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link NameIdentifierType }
     *     
     */
    public void setSAMLv1Identifier(NameIdentifierType value) {
        this.samLv1Identifier = value;
    }

    /**
     * Ruft den Wert der samLv2Identifier-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link NameIDType }
     *     
     */
    public NameIDType getSAMLv2Identifier() {
        return samLv2Identifier;
    }

    /**
     * Legt den Wert der samLv2Identifier-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link NameIDType }
     *     
     */
    public void setSAMLv2Identifier(NameIDType value) {
        this.samLv2Identifier = value;
    }

    /**
     * Ruft den Wert der other-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link AnyType }
     *     
     */
    public AnyType getOther() {
        return other;
    }

    /**
     * Legt den Wert der other-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link AnyType }
     *     
     */
    public void setOther(AnyType value) {
        this.other = value;
    }

}
