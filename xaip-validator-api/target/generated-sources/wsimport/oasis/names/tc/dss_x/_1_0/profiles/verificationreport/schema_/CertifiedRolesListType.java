
package oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für CertifiedRolesListType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="CertifiedRolesListType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="AttributeCertificateValidity" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}AttributeCertificateValidityType" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CertifiedRolesListType", propOrder = {
    "attributeCertificateValidity"
})
public class CertifiedRolesListType {

    @XmlElement(name = "AttributeCertificateValidity", required = true)
    protected List<AttributeCertificateValidityType> attributeCertificateValidity;

    /**
     * Gets the value of the attributeCertificateValidity property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the attributeCertificateValidity property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAttributeCertificateValidity().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AttributeCertificateValidityType }
     * 
     * 
     */
    public List<AttributeCertificateValidityType> getAttributeCertificateValidity() {
        if (attributeCertificateValidity == null) {
            attributeCertificateValidity = new ArrayList<AttributeCertificateValidityType>();
        }
        return this.attributeCertificateValidity;
    }

}
