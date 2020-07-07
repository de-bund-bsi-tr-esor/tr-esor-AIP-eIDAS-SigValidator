
package de.bund.bsi.tr_esor.api._1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import oasis.names.tc.saml._2_0.assertion.NameIDType;


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
 *         &lt;element name="RequestorName" type="{urn:oasis:names:tc:SAML:2.0:assertion}NameIDType"/&gt;
 *         &lt;element name="RequestInfo" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
    "requestorName",
    "requestInfo"
})
@XmlRootElement(name = "ReasonOfDeletion")
public class ReasonOfDeletion {

    @XmlElement(name = "RequestorName", required = true)
    protected NameIDType requestorName;
    @XmlElement(name = "RequestInfo", required = true)
    protected String requestInfo;

    /**
     * Ruft den Wert der requestorName-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link NameIDType }
     *     
     */
    public NameIDType getRequestorName() {
        return requestorName;
    }

    /**
     * Legt den Wert der requestorName-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link NameIDType }
     *     
     */
    public void setRequestorName(NameIDType value) {
        this.requestorName = value;
    }

    /**
     * Ruft den Wert der requestInfo-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestInfo() {
        return requestInfo;
    }

    /**
     * Legt den Wert der requestInfo-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestInfo(String value) {
        this.requestInfo = value;
    }

}
