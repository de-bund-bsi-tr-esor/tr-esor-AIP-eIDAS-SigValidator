
package org.w3._2001._04.xmldsig_more_;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für ECDSAKeyValueType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="ECDSAKeyValueType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="DomainParameters" type="{http://www.w3.org/2001/04/xmldsig-more#}DomainParamsType" minOccurs="0"/&gt;
 *         &lt;element name="PublicKey" type="{http://www.w3.org/2001/04/xmldsig-more#}ECPointType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ECDSAKeyValueType", propOrder = {
    "domainParameters",
    "publicKey"
})
@XmlSeeAlso({
    ECDSAKeyValue.class
})
public class ECDSAKeyValueType {

    @XmlElement(name = "DomainParameters")
    protected DomainParamsType domainParameters;
    @XmlElement(name = "PublicKey", required = true)
    protected ECPointType publicKey;

    /**
     * Ruft den Wert der domainParameters-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link DomainParamsType }
     *     
     */
    public DomainParamsType getDomainParameters() {
        return domainParameters;
    }

    /**
     * Legt den Wert der domainParameters-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link DomainParamsType }
     *     
     */
    public void setDomainParameters(DomainParamsType value) {
        this.domainParameters = value;
    }

    /**
     * Ruft den Wert der publicKey-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link ECPointType }
     *     
     */
    public ECPointType getPublicKey() {
        return publicKey;
    }

    /**
     * Legt den Wert der publicKey-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link ECPointType }
     *     
     */
    public void setPublicKey(ECPointType value) {
        this.publicKey = value;
    }

}
