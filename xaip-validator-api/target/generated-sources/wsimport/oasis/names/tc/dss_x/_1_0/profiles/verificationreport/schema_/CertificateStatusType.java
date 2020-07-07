
package oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import oasis.names.tc.dss._1_0.core.schema.AnyType;
import org.etsi.uri._01903.v1_3.CRLIdentifierType;
import org.etsi.uri._01903.v1_3.OCSPIdentifierType;


/**
 * <p>Java-Klasse für CertificateStatusType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="CertificateStatusType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CertStatusOK" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}VerificationResultType"/&gt;
 *         &lt;element name="RevocationInfo" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="RevocationDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *                   &lt;element name="RevocationReason" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}VerificationResultType"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="RevocationEvidence" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;choice&gt;
 *                   &lt;element name="CRLValidity" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}CRLValidityType"/&gt;
 *                   &lt;element name="CRLReference" type="{http://uri.etsi.org/01903/v1.3.2#}CRLIdentifierType"/&gt;
 *                   &lt;element name="OCSPValidity" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}OCSPValidityType"/&gt;
 *                   &lt;element name="OCSPReference" type="{http://uri.etsi.org/01903/v1.3.2#}OCSPIdentifierType"/&gt;
 *                   &lt;element name="Other" type="{urn:oasis:names:tc:dss:1.0:core:schema}AnyType"/&gt;
 *                 &lt;/choice&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CertificateStatusType", propOrder = {
    "certStatusOK",
    "revocationInfo",
    "revocationEvidence"
})
public class CertificateStatusType {

    @XmlElement(name = "CertStatusOK", required = true)
    protected VerificationResultType certStatusOK;
    @XmlElement(name = "RevocationInfo")
    protected CertificateStatusType.RevocationInfo revocationInfo;
    @XmlElement(name = "RevocationEvidence")
    protected CertificateStatusType.RevocationEvidence revocationEvidence;

    /**
     * Ruft den Wert der certStatusOK-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link VerificationResultType }
     *     
     */
    public VerificationResultType getCertStatusOK() {
        return certStatusOK;
    }

    /**
     * Legt den Wert der certStatusOK-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link VerificationResultType }
     *     
     */
    public void setCertStatusOK(VerificationResultType value) {
        this.certStatusOK = value;
    }

    /**
     * Ruft den Wert der revocationInfo-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link CertificateStatusType.RevocationInfo }
     *     
     */
    public CertificateStatusType.RevocationInfo getRevocationInfo() {
        return revocationInfo;
    }

    /**
     * Legt den Wert der revocationInfo-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link CertificateStatusType.RevocationInfo }
     *     
     */
    public void setRevocationInfo(CertificateStatusType.RevocationInfo value) {
        this.revocationInfo = value;
    }

    /**
     * Ruft den Wert der revocationEvidence-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link CertificateStatusType.RevocationEvidence }
     *     
     */
    public CertificateStatusType.RevocationEvidence getRevocationEvidence() {
        return revocationEvidence;
    }

    /**
     * Legt den Wert der revocationEvidence-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link CertificateStatusType.RevocationEvidence }
     *     
     */
    public void setRevocationEvidence(CertificateStatusType.RevocationEvidence value) {
        this.revocationEvidence = value;
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
     *       &lt;choice&gt;
     *         &lt;element name="CRLValidity" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}CRLValidityType"/&gt;
     *         &lt;element name="CRLReference" type="{http://uri.etsi.org/01903/v1.3.2#}CRLIdentifierType"/&gt;
     *         &lt;element name="OCSPValidity" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}OCSPValidityType"/&gt;
     *         &lt;element name="OCSPReference" type="{http://uri.etsi.org/01903/v1.3.2#}OCSPIdentifierType"/&gt;
     *         &lt;element name="Other" type="{urn:oasis:names:tc:dss:1.0:core:schema}AnyType"/&gt;
     *       &lt;/choice&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "crlValidity",
        "crlReference",
        "ocspValidity",
        "ocspReference",
        "other"
    })
    public static class RevocationEvidence {

        @XmlElement(name = "CRLValidity")
        protected CRLValidityType crlValidity;
        @XmlElement(name = "CRLReference")
        protected CRLIdentifierType crlReference;
        @XmlElement(name = "OCSPValidity")
        protected OCSPValidityType ocspValidity;
        @XmlElement(name = "OCSPReference")
        protected OCSPIdentifierType ocspReference;
        @XmlElement(name = "Other")
        protected AnyType other;

        /**
         * Ruft den Wert der crlValidity-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link CRLValidityType }
         *     
         */
        public CRLValidityType getCRLValidity() {
            return crlValidity;
        }

        /**
         * Legt den Wert der crlValidity-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link CRLValidityType }
         *     
         */
        public void setCRLValidity(CRLValidityType value) {
            this.crlValidity = value;
        }

        /**
         * Ruft den Wert der crlReference-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link CRLIdentifierType }
         *     
         */
        public CRLIdentifierType getCRLReference() {
            return crlReference;
        }

        /**
         * Legt den Wert der crlReference-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link CRLIdentifierType }
         *     
         */
        public void setCRLReference(CRLIdentifierType value) {
            this.crlReference = value;
        }

        /**
         * Ruft den Wert der ocspValidity-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link OCSPValidityType }
         *     
         */
        public OCSPValidityType getOCSPValidity() {
            return ocspValidity;
        }

        /**
         * Legt den Wert der ocspValidity-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link OCSPValidityType }
         *     
         */
        public void setOCSPValidity(OCSPValidityType value) {
            this.ocspValidity = value;
        }

        /**
         * Ruft den Wert der ocspReference-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link OCSPIdentifierType }
         *     
         */
        public OCSPIdentifierType getOCSPReference() {
            return ocspReference;
        }

        /**
         * Legt den Wert der ocspReference-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link OCSPIdentifierType }
         *     
         */
        public void setOCSPReference(OCSPIdentifierType value) {
            this.ocspReference = value;
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
     *         &lt;element name="RevocationDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
     *         &lt;element name="RevocationReason" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}VerificationResultType"/&gt;
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
        "revocationDate",
        "revocationReason"
    })
    public static class RevocationInfo {

        @XmlElement(name = "RevocationDate", required = true)
        @XmlSchemaType(name = "dateTime")
        protected XMLGregorianCalendar revocationDate;
        @XmlElement(name = "RevocationReason", required = true)
        protected VerificationResultType revocationReason;

        /**
         * Ruft den Wert der revocationDate-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getRevocationDate() {
            return revocationDate;
        }

        /**
         * Legt den Wert der revocationDate-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setRevocationDate(XMLGregorianCalendar value) {
            this.revocationDate = value;
        }

        /**
         * Ruft den Wert der revocationReason-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link VerificationResultType }
         *     
         */
        public VerificationResultType getRevocationReason() {
            return revocationReason;
        }

        /**
         * Legt den Wert der revocationReason-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link VerificationResultType }
         *     
         */
        public void setRevocationReason(VerificationResultType value) {
            this.revocationReason = value;
        }

    }

}
