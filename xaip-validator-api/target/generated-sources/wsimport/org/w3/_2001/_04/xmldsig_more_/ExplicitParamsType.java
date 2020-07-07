
package org.w3._2001._04.xmldsig_more_;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für ExplicitParamsType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="ExplicitParamsType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="FieldParams" type="{http://www.w3.org/2001/04/xmldsig-more#}FieldParamsType"/&gt;
 *         &lt;element name="CurveParams" type="{http://www.w3.org/2001/04/xmldsig-more#}CurveParamsType"/&gt;
 *         &lt;element name="BasePointParams" type="{http://www.w3.org/2001/04/xmldsig-more#}BasePointParamsType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExplicitParamsType", propOrder = {
    "fieldParams",
    "curveParams",
    "basePointParams"
})
public class ExplicitParamsType {

    @XmlElement(name = "FieldParams", required = true)
    protected FieldParamsType fieldParams;
    @XmlElement(name = "CurveParams", required = true)
    protected CurveParamsType curveParams;
    @XmlElement(name = "BasePointParams", required = true)
    protected BasePointParamsType basePointParams;

    /**
     * Ruft den Wert der fieldParams-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link FieldParamsType }
     *     
     */
    public FieldParamsType getFieldParams() {
        return fieldParams;
    }

    /**
     * Legt den Wert der fieldParams-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link FieldParamsType }
     *     
     */
    public void setFieldParams(FieldParamsType value) {
        this.fieldParams = value;
    }

    /**
     * Ruft den Wert der curveParams-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link CurveParamsType }
     *     
     */
    public CurveParamsType getCurveParams() {
        return curveParams;
    }

    /**
     * Legt den Wert der curveParams-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link CurveParamsType }
     *     
     */
    public void setCurveParams(CurveParamsType value) {
        this.curveParams = value;
    }

    /**
     * Ruft den Wert der basePointParams-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BasePointParamsType }
     *     
     */
    public BasePointParamsType getBasePointParams() {
        return basePointParams;
    }

    /**
     * Legt den Wert der basePointParams-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BasePointParamsType }
     *     
     */
    public void setBasePointParams(BasePointParamsType value) {
        this.basePointParams = value;
    }

}
