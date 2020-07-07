
package org.w3._2001._04.xmldsig_more_;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für BasePointParamsType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="BasePointParamsType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="BasePoint" type="{http://www.w3.org/2001/04/xmldsig-more#}ECPointType"/&gt;
 *         &lt;element name="Order" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/&gt;
 *         &lt;element name="Cofactor" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BasePointParamsType", propOrder = {
    "basePoint",
    "order",
    "cofactor"
})
public class BasePointParamsType {

    @XmlElement(name = "BasePoint", required = true)
    protected ECPointType basePoint;
    @XmlElement(name = "Order", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger order;
    @XmlElement(name = "Cofactor")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger cofactor;

    /**
     * Ruft den Wert der basePoint-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link ECPointType }
     *     
     */
    public ECPointType getBasePoint() {
        return basePoint;
    }

    /**
     * Legt den Wert der basePoint-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link ECPointType }
     *     
     */
    public void setBasePoint(ECPointType value) {
        this.basePoint = value;
    }

    /**
     * Ruft den Wert der order-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getOrder() {
        return order;
    }

    /**
     * Legt den Wert der order-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setOrder(BigInteger value) {
        this.order = value;
    }

    /**
     * Ruft den Wert der cofactor-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCofactor() {
        return cofactor;
    }

    /**
     * Legt den Wert der cofactor-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCofactor(BigInteger value) {
        this.cofactor = value;
    }

}
