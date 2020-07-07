
package oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java-Klasse für CRLContentType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="CRLContentType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Version" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="Signature" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}VerificationResultType"/&gt;
 *         &lt;element name="Issuer" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="ThisUpdate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="NextUpdate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="RevokedCertificates" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence maxOccurs="unbounded" minOccurs="0"&gt;
 *                   &lt;element name="UserCertificate" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *                   &lt;element name="RevocationDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *                   &lt;element name="CrlEntryExtensions" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}ExtensionsType" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="CrlExtensions" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}ExtensionsType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CRLContentType", propOrder = {
    "version",
    "signature",
    "issuer",
    "thisUpdate",
    "nextUpdate",
    "revokedCertificates",
    "crlExtensions"
})
public class CRLContentType {

    @XmlElement(name = "Version")
    protected BigInteger version;
    @XmlElement(name = "Signature", required = true)
    protected VerificationResultType signature;
    @XmlElement(name = "Issuer", required = true)
    protected String issuer;
    @XmlElement(name = "ThisUpdate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar thisUpdate;
    @XmlElement(name = "NextUpdate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar nextUpdate;
    @XmlElement(name = "RevokedCertificates")
    protected CRLContentType.RevokedCertificates revokedCertificates;
    @XmlElement(name = "CrlExtensions")
    protected ExtensionsType crlExtensions;

    /**
     * Ruft den Wert der version-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getVersion() {
        return version;
    }

    /**
     * Legt den Wert der version-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setVersion(BigInteger value) {
        this.version = value;
    }

    /**
     * Ruft den Wert der signature-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link VerificationResultType }
     *     
     */
    public VerificationResultType getSignature() {
        return signature;
    }

    /**
     * Legt den Wert der signature-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link VerificationResultType }
     *     
     */
    public void setSignature(VerificationResultType value) {
        this.signature = value;
    }

    /**
     * Ruft den Wert der issuer-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIssuer() {
        return issuer;
    }

    /**
     * Legt den Wert der issuer-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIssuer(String value) {
        this.issuer = value;
    }

    /**
     * Ruft den Wert der thisUpdate-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getThisUpdate() {
        return thisUpdate;
    }

    /**
     * Legt den Wert der thisUpdate-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setThisUpdate(XMLGregorianCalendar value) {
        this.thisUpdate = value;
    }

    /**
     * Ruft den Wert der nextUpdate-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getNextUpdate() {
        return nextUpdate;
    }

    /**
     * Legt den Wert der nextUpdate-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setNextUpdate(XMLGregorianCalendar value) {
        this.nextUpdate = value;
    }

    /**
     * Ruft den Wert der revokedCertificates-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link CRLContentType.RevokedCertificates }
     *     
     */
    public CRLContentType.RevokedCertificates getRevokedCertificates() {
        return revokedCertificates;
    }

    /**
     * Legt den Wert der revokedCertificates-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link CRLContentType.RevokedCertificates }
     *     
     */
    public void setRevokedCertificates(CRLContentType.RevokedCertificates value) {
        this.revokedCertificates = value;
    }

    /**
     * Ruft den Wert der crlExtensions-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link ExtensionsType }
     *     
     */
    public ExtensionsType getCrlExtensions() {
        return crlExtensions;
    }

    /**
     * Legt den Wert der crlExtensions-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link ExtensionsType }
     *     
     */
    public void setCrlExtensions(ExtensionsType value) {
        this.crlExtensions = value;
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
     *       &lt;sequence maxOccurs="unbounded" minOccurs="0"&gt;
     *         &lt;element name="UserCertificate" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
     *         &lt;element name="RevocationDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
     *         &lt;element name="CrlEntryExtensions" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}ExtensionsType" minOccurs="0"/&gt;
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
        "userCertificateAndRevocationDateAndCrlEntryExtensions"
    })
    public static class RevokedCertificates {

        @XmlElements({
            @XmlElement(name = "UserCertificate", type = BigInteger.class),
            @XmlElement(name = "RevocationDate", type = XMLGregorianCalendar.class),
            @XmlElement(name = "CrlEntryExtensions", type = ExtensionsType.class)
        })
        protected List<Object> userCertificateAndRevocationDateAndCrlEntryExtensions;

        /**
         * Gets the value of the userCertificateAndRevocationDateAndCrlEntryExtensions property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the userCertificateAndRevocationDateAndCrlEntryExtensions property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getUserCertificateAndRevocationDateAndCrlEntryExtensions().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link BigInteger }
         * {@link XMLGregorianCalendar }
         * {@link ExtensionsType }
         * 
         * 
         */
        public List<Object> getUserCertificateAndRevocationDateAndCrlEntryExtensions() {
            if (userCertificateAndRevocationDateAndCrlEntryExtensions == null) {
                userCertificateAndRevocationDateAndCrlEntryExtensions = new ArrayList<Object>();
            }
            return this.userCertificateAndRevocationDateAndCrlEntryExtensions;
        }

    }

}
