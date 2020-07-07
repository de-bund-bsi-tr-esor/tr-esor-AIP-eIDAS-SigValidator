
package oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import oasis.names.tc.dss._1_0.core.schema.AnyType;
import oasis.names.tc.dss._1_0.core.schema.Result;


/**
 * <p>Java-Klasse für IndividualReportType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="IndividualReportType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SignedObjectIdentifier" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}SignedObjectIdentifierType"/&gt;
 *         &lt;element ref="{urn:oasis:names:tc:dss:1.0:core:schema}Result"/&gt;
 *         &lt;element name="Details" type="{urn:oasis:names:tc:dss:1.0:core:schema}AnyType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IndividualReportType", propOrder = {
    "signedObjectIdentifier",
    "result",
    "details"
})
public class IndividualReportType {

    @XmlElement(name = "SignedObjectIdentifier", required = true)
    protected SignedObjectIdentifierType signedObjectIdentifier;
    @XmlElement(name = "Result", namespace = "urn:oasis:names:tc:dss:1.0:core:schema", required = true)
    protected Result result;
    @XmlElement(name = "Details")
    protected AnyType details;

    /**
     * Ruft den Wert der signedObjectIdentifier-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link SignedObjectIdentifierType }
     *     
     */
    public SignedObjectIdentifierType getSignedObjectIdentifier() {
        return signedObjectIdentifier;
    }

    /**
     * Legt den Wert der signedObjectIdentifier-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link SignedObjectIdentifierType }
     *     
     */
    public void setSignedObjectIdentifier(SignedObjectIdentifierType value) {
        this.signedObjectIdentifier = value;
    }

    /**
     * Ruft den Wert der result-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Result }
     *     
     */
    public Result getResult() {
        return result;
    }

    /**
     * Legt den Wert der result-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Result }
     *     
     */
    public void setResult(Result value) {
        this.result = value;
    }

    /**
     * Ruft den Wert der details-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link AnyType }
     *     
     */
    public AnyType getDetails() {
        return details;
    }

    /**
     * Legt den Wert der details-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link AnyType }
     *     
     */
    public void setDetails(AnyType value) {
        this.details = value;
    }

}
