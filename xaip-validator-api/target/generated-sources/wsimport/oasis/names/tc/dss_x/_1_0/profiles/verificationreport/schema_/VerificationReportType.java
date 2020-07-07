
package oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import oasis.names.tc.dss._1_0.core.schema.VerificationTimeInfoType;


/**
 * <p>Java-Klasse für VerificationReportType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="VerificationReportType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{urn:oasis:names:tc:dss:1.0:core:schema}VerificationTimeInfo" minOccurs="0"/&gt;
 *         &lt;element name="VerifierIdentity" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}IdentifierType" minOccurs="0"/&gt;
 *         &lt;element name="IndividualReport" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}IndividualReportType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VerificationReportType", propOrder = {
    "verificationTimeInfo",
    "verifierIdentity",
    "individualReport"
})
public class VerificationReportType {

    @XmlElement(name = "VerificationTimeInfo", namespace = "urn:oasis:names:tc:dss:1.0:core:schema")
    protected VerificationTimeInfoType verificationTimeInfo;
    @XmlElement(name = "VerifierIdentity")
    protected IdentifierType verifierIdentity;
    @XmlElement(name = "IndividualReport")
    protected List<IndividualReportType> individualReport;

    /**
     * Ruft den Wert der verificationTimeInfo-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link VerificationTimeInfoType }
     *     
     */
    public VerificationTimeInfoType getVerificationTimeInfo() {
        return verificationTimeInfo;
    }

    /**
     * Legt den Wert der verificationTimeInfo-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link VerificationTimeInfoType }
     *     
     */
    public void setVerificationTimeInfo(VerificationTimeInfoType value) {
        this.verificationTimeInfo = value;
    }

    /**
     * Ruft den Wert der verifierIdentity-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link IdentifierType }
     *     
     */
    public IdentifierType getVerifierIdentity() {
        return verifierIdentity;
    }

    /**
     * Legt den Wert der verifierIdentity-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link IdentifierType }
     *     
     */
    public void setVerifierIdentity(IdentifierType value) {
        this.verifierIdentity = value;
    }

    /**
     * Gets the value of the individualReport property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the individualReport property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIndividualReport().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IndividualReportType }
     * 
     * 
     */
    public List<IndividualReportType> getIndividualReport() {
        if (individualReport == null) {
            individualReport = new ArrayList<IndividualReportType>();
        }
        return this.individualReport;
    }

}
