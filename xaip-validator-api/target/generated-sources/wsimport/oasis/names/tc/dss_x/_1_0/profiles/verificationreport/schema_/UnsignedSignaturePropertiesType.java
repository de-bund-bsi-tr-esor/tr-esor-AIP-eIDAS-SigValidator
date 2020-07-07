
package oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.etsi.uri._01903.v1_3.CompleteCertificateRefsType;
import org.etsi.uri._01903.v1_3.CompleteRevocationRefsType;


/**
 * <p>Java-Klasse für UnsignedSignaturePropertiesType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="UnsignedSignaturePropertiesType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice maxOccurs="unbounded"&gt;
 *         &lt;element name="CounterSignature" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}SignatureValidityType"/&gt;
 *         &lt;element name="SignatureTimeStamp" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}TimeStampValidityType"/&gt;
 *         &lt;element ref="{http://uri.etsi.org/01903/v1.3.2#}CompleteCertificateRefs"/&gt;
 *         &lt;element ref="{http://uri.etsi.org/01903/v1.3.2#}CompleteRevocationRefs"/&gt;
 *         &lt;element ref="{http://uri.etsi.org/01903/v1.3.2#}AttributeCertificateRefs"/&gt;
 *         &lt;element ref="{http://uri.etsi.org/01903/v1.3.2#}AttributeRevocationRefs"/&gt;
 *         &lt;element name="SigAndRefsTimeStamp" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}TimeStampValidityType"/&gt;
 *         &lt;element name="RefsOnlyTimeStamp" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}TimeStampValidityType"/&gt;
 *         &lt;element name="CertificateValues" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}CertificateValuesType"/&gt;
 *         &lt;element name="RevocationValues" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}RevocationValuesType"/&gt;
 *         &lt;element name="AttrAuthoritiesCertValues" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}CertificateValuesType"/&gt;
 *         &lt;element name="AttributeRevocationValues" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}RevocationValuesType"/&gt;
 *         &lt;element name="ArchiveTimeStamp" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}TimeStampValidityType"/&gt;
 *       &lt;/choice&gt;
 *       &lt;attribute name="Id" type="{http://www.w3.org/2001/XMLSchema}ID" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UnsignedSignaturePropertiesType", propOrder = {
    "counterSignatureOrSignatureTimeStampOrCompleteCertificateRefs"
})
public class UnsignedSignaturePropertiesType {

    @XmlElementRefs({
        @XmlElementRef(name = "CounterSignature", namespace = "urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "SignatureTimeStamp", namespace = "urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "CompleteCertificateRefs", namespace = "http://uri.etsi.org/01903/v1.3.2#", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "CompleteRevocationRefs", namespace = "http://uri.etsi.org/01903/v1.3.2#", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "AttributeCertificateRefs", namespace = "http://uri.etsi.org/01903/v1.3.2#", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "AttributeRevocationRefs", namespace = "http://uri.etsi.org/01903/v1.3.2#", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "SigAndRefsTimeStamp", namespace = "urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "RefsOnlyTimeStamp", namespace = "urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "CertificateValues", namespace = "urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "RevocationValues", namespace = "urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "AttrAuthoritiesCertValues", namespace = "urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "AttributeRevocationValues", namespace = "urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "ArchiveTimeStamp", namespace = "urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#", type = JAXBElement.class, required = false)
    })
    protected List<JAXBElement<?>> counterSignatureOrSignatureTimeStampOrCompleteCertificateRefs;
    @XmlAttribute(name = "Id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Gets the value of the counterSignatureOrSignatureTimeStampOrCompleteCertificateRefs property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the counterSignatureOrSignatureTimeStampOrCompleteCertificateRefs property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCounterSignatureOrSignatureTimeStampOrCompleteCertificateRefs().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link SignatureValidityType }{@code >}
     * {@link JAXBElement }{@code <}{@link TimeStampValidityType }{@code >}
     * {@link JAXBElement }{@code <}{@link CompleteCertificateRefsType }{@code >}
     * {@link JAXBElement }{@code <}{@link CompleteRevocationRefsType }{@code >}
     * {@link JAXBElement }{@code <}{@link CompleteCertificateRefsType }{@code >}
     * {@link JAXBElement }{@code <}{@link CompleteRevocationRefsType }{@code >}
     * {@link JAXBElement }{@code <}{@link TimeStampValidityType }{@code >}
     * {@link JAXBElement }{@code <}{@link TimeStampValidityType }{@code >}
     * {@link JAXBElement }{@code <}{@link CertificateValuesType }{@code >}
     * {@link JAXBElement }{@code <}{@link RevocationValuesType }{@code >}
     * {@link JAXBElement }{@code <}{@link CertificateValuesType }{@code >}
     * {@link JAXBElement }{@code <}{@link RevocationValuesType }{@code >}
     * {@link JAXBElement }{@code <}{@link TimeStampValidityType }{@code >}
     * 
     * 
     */
    public List<JAXBElement<?>> getCounterSignatureOrSignatureTimeStampOrCompleteCertificateRefs() {
        if (counterSignatureOrSignatureTimeStampOrCompleteCertificateRefs == null) {
            counterSignatureOrSignatureTimeStampOrCompleteCertificateRefs = new ArrayList<JAXBElement<?>>();
        }
        return this.counterSignatureOrSignatureTimeStampOrCompleteCertificateRefs;
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

}
