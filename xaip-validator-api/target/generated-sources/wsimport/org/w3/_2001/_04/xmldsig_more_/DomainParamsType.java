
package org.w3._2001._04.xmldsig_more_;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für DomainParamsType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="DomainParamsType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice&gt;
 *         &lt;element name="ExplicitParams" type="{http://www.w3.org/2001/04/xmldsig-more#}ExplicitParamsType"/&gt;
 *         &lt;element name="NamedCurve"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;attribute name="URN" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" /&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/choice&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DomainParamsType", propOrder = {
    "explicitParams",
    "namedCurve"
})
public class DomainParamsType {

    @XmlElement(name = "ExplicitParams")
    protected ExplicitParamsType explicitParams;
    @XmlElement(name = "NamedCurve")
    protected DomainParamsType.NamedCurve namedCurve;

    /**
     * Ruft den Wert der explicitParams-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link ExplicitParamsType }
     *     
     */
    public ExplicitParamsType getExplicitParams() {
        return explicitParams;
    }

    /**
     * Legt den Wert der explicitParams-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link ExplicitParamsType }
     *     
     */
    public void setExplicitParams(ExplicitParamsType value) {
        this.explicitParams = value;
    }

    /**
     * Ruft den Wert der namedCurve-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link DomainParamsType.NamedCurve }
     *     
     */
    public DomainParamsType.NamedCurve getNamedCurve() {
        return namedCurve;
    }

    /**
     * Legt den Wert der namedCurve-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link DomainParamsType.NamedCurve }
     *     
     */
    public void setNamedCurve(DomainParamsType.NamedCurve value) {
        this.namedCurve = value;
    }


    /**
     * <p>Java-Klasse für anonymous complex type.
     * 
     * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;attribute name="URN" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" /&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class NamedCurve {

        @XmlAttribute(name = "URN", required = true)
        @XmlSchemaType(name = "anyURI")
        protected String urn;

        /**
         * Ruft den Wert der urn-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getURN() {
            return urn;
        }

        /**
         * Legt den Wert der urn-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setURN(String value) {
            this.urn = value;
        }

    }

}
