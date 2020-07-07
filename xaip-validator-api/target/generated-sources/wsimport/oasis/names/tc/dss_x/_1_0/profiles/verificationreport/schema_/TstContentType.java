
package oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import oasis.names.tc.dss._1_0.core.schema.AnyType;
import oasis.names.tc.dss._1_0.core.schema.TstInfo;


/**
 * <p>Java-Klasse für TstContentType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="TstContentType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{urn:oasis:names:tc:dss:1.0:core:schema}TstInfo" minOccurs="0"/&gt;
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
@XmlType(name = "TstContentType", propOrder = {
    "tstInfo",
    "other"
})
public class TstContentType {

    @XmlElement(name = "TstInfo", namespace = "urn:oasis:names:tc:dss:1.0:core:schema")
    protected TstInfo tstInfo;
    @XmlElement(name = "Other")
    protected AnyType other;

    /**
     * Ruft den Wert der tstInfo-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link TstInfo }
     *     
     */
    public TstInfo getTstInfo() {
        return tstInfo;
    }

    /**
     * Legt den Wert der tstInfo-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link TstInfo }
     *     
     */
    public void setTstInfo(TstInfo value) {
        this.tstInfo = value;
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
