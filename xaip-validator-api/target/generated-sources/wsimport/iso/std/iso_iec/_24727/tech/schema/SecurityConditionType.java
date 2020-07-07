
package iso.std.iso_iec._24727.tech.schema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für SecurityConditionType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="SecurityConditionType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice&gt;
 *         &lt;element name="DIDAuthentication" type="{urn:iso:std:iso-iec:24727:tech:schema}DIDAuthenticationStateType"/&gt;
 *         &lt;element name="always" type="{urn:iso:std:iso-iec:24727:tech:schema}TrueType"/&gt;
 *         &lt;element name="never" type="{urn:iso:std:iso-iec:24727:tech:schema}FalseType"/&gt;
 *         &lt;element name="not" type="{urn:iso:std:iso-iec:24727:tech:schema}SecurityConditionType"/&gt;
 *         &lt;element name="and"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence maxOccurs="255"&gt;
 *                   &lt;element name="SecurityCondition" type="{urn:iso:std:iso-iec:24727:tech:schema}SecurityConditionType"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="or"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence maxOccurs="255"&gt;
 *                   &lt;element name="SecurityCondition" type="{urn:iso:std:iso-iec:24727:tech:schema}SecurityConditionType"/&gt;
 *                 &lt;/sequence&gt;
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
@XmlType(name = "SecurityConditionType", propOrder = {
    "didAuthentication",
    "always",
    "never",
    "not",
    "and",
    "or"
})
public class SecurityConditionType {

    @XmlElement(name = "DIDAuthentication")
    protected DIDAuthenticationStateType didAuthentication;
    protected Boolean always;
    protected Boolean never;
    protected SecurityConditionType not;
    protected SecurityConditionType.And and;
    protected SecurityConditionType.Or or;

    /**
     * Ruft den Wert der didAuthentication-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link DIDAuthenticationStateType }
     *     
     */
    public DIDAuthenticationStateType getDIDAuthentication() {
        return didAuthentication;
    }

    /**
     * Legt den Wert der didAuthentication-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link DIDAuthenticationStateType }
     *     
     */
    public void setDIDAuthentication(DIDAuthenticationStateType value) {
        this.didAuthentication = value;
    }

    /**
     * Ruft den Wert der always-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAlways() {
        return always;
    }

    /**
     * Legt den Wert der always-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAlways(Boolean value) {
        this.always = value;
    }

    /**
     * Ruft den Wert der never-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isNever() {
        return never;
    }

    /**
     * Legt den Wert der never-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setNever(Boolean value) {
        this.never = value;
    }

    /**
     * Ruft den Wert der not-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link SecurityConditionType }
     *     
     */
    public SecurityConditionType getNot() {
        return not;
    }

    /**
     * Legt den Wert der not-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link SecurityConditionType }
     *     
     */
    public void setNot(SecurityConditionType value) {
        this.not = value;
    }

    /**
     * Ruft den Wert der and-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link SecurityConditionType.And }
     *     
     */
    public SecurityConditionType.And getAnd() {
        return and;
    }

    /**
     * Legt den Wert der and-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link SecurityConditionType.And }
     *     
     */
    public void setAnd(SecurityConditionType.And value) {
        this.and = value;
    }

    /**
     * Ruft den Wert der or-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link SecurityConditionType.Or }
     *     
     */
    public SecurityConditionType.Or getOr() {
        return or;
    }

    /**
     * Legt den Wert der or-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link SecurityConditionType.Or }
     *     
     */
    public void setOr(SecurityConditionType.Or value) {
        this.or = value;
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
     *       &lt;sequence maxOccurs="255"&gt;
     *         &lt;element name="SecurityCondition" type="{urn:iso:std:iso-iec:24727:tech:schema}SecurityConditionType"/&gt;
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
        "securityCondition"
    })
    public static class And {

        @XmlElement(name = "SecurityCondition", required = true)
        protected List<SecurityConditionType> securityCondition;

        /**
         * Gets the value of the securityCondition property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the securityCondition property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getSecurityCondition().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link SecurityConditionType }
         * 
         * 
         */
        public List<SecurityConditionType> getSecurityCondition() {
            if (securityCondition == null) {
                securityCondition = new ArrayList<SecurityConditionType>();
            }
            return this.securityCondition;
        }

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
     *       &lt;sequence maxOccurs="255"&gt;
     *         &lt;element name="SecurityCondition" type="{urn:iso:std:iso-iec:24727:tech:schema}SecurityConditionType"/&gt;
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
        "securityCondition"
    })
    public static class Or {

        @XmlElement(name = "SecurityCondition", required = true)
        protected List<SecurityConditionType> securityCondition;

        /**
         * Gets the value of the securityCondition property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the securityCondition property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getSecurityCondition().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link SecurityConditionType }
         * 
         * 
         */
        public List<SecurityConditionType> getSecurityCondition() {
            if (securityCondition == null) {
                securityCondition = new ArrayList<SecurityConditionType>();
            }
            return this.securityCondition;
        }

    }

}
