
package oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import oasis.names.tc.dss._1_0.core.schema.AnyType;


/**
 * <p>Java-Klasse für RevocationValuesType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="RevocationValuesType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CRLValues" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence maxOccurs="unbounded"&gt;
 *                   &lt;element name="VerifiedCRL" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}CRLValidityType"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="OCSPValues" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence maxOccurs="unbounded"&gt;
 *                   &lt;element name="VerifiedOCSPResponse" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}OCSPValidityType"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="OtherValues" type="{urn:oasis:names:tc:dss:1.0:core:schema}AnyType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="Id" type="{http://www.w3.org/2001/XMLSchema}ID" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RevocationValuesType", propOrder = {
    "crlValues",
    "ocspValues",
    "otherValues"
})
public class RevocationValuesType {

    @XmlElement(name = "CRLValues")
    protected RevocationValuesType.CRLValues crlValues;
    @XmlElement(name = "OCSPValues")
    protected RevocationValuesType.OCSPValues ocspValues;
    @XmlElement(name = "OtherValues")
    protected AnyType otherValues;
    @XmlAttribute(name = "Id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Ruft den Wert der crlValues-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link RevocationValuesType.CRLValues }
     *     
     */
    public RevocationValuesType.CRLValues getCRLValues() {
        return crlValues;
    }

    /**
     * Legt den Wert der crlValues-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link RevocationValuesType.CRLValues }
     *     
     */
    public void setCRLValues(RevocationValuesType.CRLValues value) {
        this.crlValues = value;
    }

    /**
     * Ruft den Wert der ocspValues-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link RevocationValuesType.OCSPValues }
     *     
     */
    public RevocationValuesType.OCSPValues getOCSPValues() {
        return ocspValues;
    }

    /**
     * Legt den Wert der ocspValues-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link RevocationValuesType.OCSPValues }
     *     
     */
    public void setOCSPValues(RevocationValuesType.OCSPValues value) {
        this.ocspValues = value;
    }

    /**
     * Ruft den Wert der otherValues-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link AnyType }
     *     
     */
    public AnyType getOtherValues() {
        return otherValues;
    }

    /**
     * Legt den Wert der otherValues-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link AnyType }
     *     
     */
    public void setOtherValues(AnyType value) {
        this.otherValues = value;
    }

    /**
     * Ruft den Wert der id-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Legt den Wert der id-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
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
     *       &lt;sequence maxOccurs="unbounded"&gt;
     *         &lt;element name="VerifiedCRL" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}CRLValidityType"/&gt;
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
        "verifiedCRL"
    })
    public static class CRLValues {

        @XmlElement(name = "VerifiedCRL", required = true)
        protected List<CRLValidityType> verifiedCRL;

        /**
         * Gets the value of the verifiedCRL property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the verifiedCRL property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getVerifiedCRL().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link CRLValidityType }
         * 
         * 
         */
        public List<CRLValidityType> getVerifiedCRL() {
            if (verifiedCRL == null) {
                verifiedCRL = new ArrayList<CRLValidityType>();
            }
            return this.verifiedCRL;
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
     *       &lt;sequence maxOccurs="unbounded"&gt;
     *         &lt;element name="VerifiedOCSPResponse" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}OCSPValidityType"/&gt;
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
        "verifiedOCSPResponse"
    })
    public static class OCSPValues {

        @XmlElement(name = "VerifiedOCSPResponse", required = true)
        protected List<OCSPValidityType> verifiedOCSPResponse;

        /**
         * Gets the value of the verifiedOCSPResponse property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the verifiedOCSPResponse property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getVerifiedOCSPResponse().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link OCSPValidityType }
         * 
         * 
         */
        public List<OCSPValidityType> getVerifiedOCSPResponse() {
            if (verifiedOCSPResponse == null) {
                verifiedOCSPResponse = new ArrayList<OCSPValidityType>();
            }
            return this.verifiedOCSPResponse;
        }

    }

}
