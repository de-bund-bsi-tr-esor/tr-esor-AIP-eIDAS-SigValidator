
package iso.std.iso_iec._24727.tech.schema;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für anonymous complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:iso:std:iso-iec:24727:tech:schema}RequestType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SessionIdentifier" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="ConnectionHandle" type="{urn:iso:std:iso-iec:24727:tech:schema}ConnectionHandleType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="UserAgent" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="VersionMajor" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *                   &lt;element name="VersionMinor" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *                   &lt;element name="VersionSubminor" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="SupportedAPIVersions" maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Major" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *                   &lt;element name="Minor" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *                   &lt;element name="Subminor" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="SupportedDIDProtocols" type="{http://www.w3.org/2001/XMLSchema}anyURI" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "sessionIdentifier",
    "connectionHandle",
    "userAgent",
    "supportedAPIVersions",
    "supportedDIDProtocols"
})
@XmlRootElement(name = "StartPAOS")
public class StartPAOS
    extends RequestType
{

    @XmlElement(name = "SessionIdentifier", required = true)
    protected String sessionIdentifier;
    @XmlElement(name = "ConnectionHandle")
    protected List<ConnectionHandleType> connectionHandle;
    @XmlElement(name = "UserAgent")
    protected StartPAOS.UserAgent userAgent;
    @XmlElement(name = "SupportedAPIVersions")
    protected List<StartPAOS.SupportedAPIVersions> supportedAPIVersions;
    @XmlElement(name = "SupportedDIDProtocols")
    @XmlSchemaType(name = "anyURI")
    protected List<String> supportedDIDProtocols;

    /**
     * Ruft den Wert der sessionIdentifier-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSessionIdentifier() {
        return sessionIdentifier;
    }

    /**
     * Legt den Wert der sessionIdentifier-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSessionIdentifier(String value) {
        this.sessionIdentifier = value;
    }

    /**
     * Gets the value of the connectionHandle property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the connectionHandle property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConnectionHandle().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ConnectionHandleType }
     * 
     * 
     */
    public List<ConnectionHandleType> getConnectionHandle() {
        if (connectionHandle == null) {
            connectionHandle = new ArrayList<ConnectionHandleType>();
        }
        return this.connectionHandle;
    }

    /**
     * Ruft den Wert der userAgent-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link StartPAOS.UserAgent }
     *     
     */
    public StartPAOS.UserAgent getUserAgent() {
        return userAgent;
    }

    /**
     * Legt den Wert der userAgent-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link StartPAOS.UserAgent }
     *     
     */
    public void setUserAgent(StartPAOS.UserAgent value) {
        this.userAgent = value;
    }

    /**
     * Gets the value of the supportedAPIVersions property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the supportedAPIVersions property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSupportedAPIVersions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StartPAOS.SupportedAPIVersions }
     * 
     * 
     */
    public List<StartPAOS.SupportedAPIVersions> getSupportedAPIVersions() {
        if (supportedAPIVersions == null) {
            supportedAPIVersions = new ArrayList<StartPAOS.SupportedAPIVersions>();
        }
        return this.supportedAPIVersions;
    }

    /**
     * Gets the value of the supportedDIDProtocols property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the supportedDIDProtocols property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSupportedDIDProtocols().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getSupportedDIDProtocols() {
        if (supportedDIDProtocols == null) {
            supportedDIDProtocols = new ArrayList<String>();
        }
        return this.supportedDIDProtocols;
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
     *         &lt;element name="Major" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
     *         &lt;element name="Minor" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
     *         &lt;element name="Subminor" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
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
        "major",
        "minor",
        "subminor"
    })
    public static class SupportedAPIVersions {

        @XmlElement(name = "Major", required = true)
        protected BigInteger major;
        @XmlElement(name = "Minor")
        protected BigInteger minor;
        @XmlElement(name = "Subminor")
        protected BigInteger subminor;

        /**
         * Ruft den Wert der major-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getMajor() {
            return major;
        }

        /**
         * Legt den Wert der major-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setMajor(BigInteger value) {
            this.major = value;
        }

        /**
         * Ruft den Wert der minor-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getMinor() {
            return minor;
        }

        /**
         * Legt den Wert der minor-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setMinor(BigInteger value) {
            this.minor = value;
        }

        /**
         * Ruft den Wert der subminor-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getSubminor() {
            return subminor;
        }

        /**
         * Legt den Wert der subminor-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setSubminor(BigInteger value) {
            this.subminor = value;
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
     *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="VersionMajor" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
     *         &lt;element name="VersionMinor" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
     *         &lt;element name="VersionSubminor" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
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
        "name",
        "versionMajor",
        "versionMinor",
        "versionSubminor"
    })
    public static class UserAgent {

        @XmlElement(name = "Name", required = true)
        protected String name;
        @XmlElement(name = "VersionMajor", required = true)
        protected BigInteger versionMajor;
        @XmlElement(name = "VersionMinor", required = true)
        protected BigInteger versionMinor;
        @XmlElement(name = "VersionSubminor")
        protected BigInteger versionSubminor;

        /**
         * Ruft den Wert der name-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getName() {
            return name;
        }

        /**
         * Legt den Wert der name-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setName(String value) {
            this.name = value;
        }

        /**
         * Ruft den Wert der versionMajor-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getVersionMajor() {
            return versionMajor;
        }

        /**
         * Legt den Wert der versionMajor-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setVersionMajor(BigInteger value) {
            this.versionMajor = value;
        }

        /**
         * Ruft den Wert der versionMinor-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getVersionMinor() {
            return versionMinor;
        }

        /**
         * Legt den Wert der versionMinor-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setVersionMinor(BigInteger value) {
            this.versionMinor = value;
        }

        /**
         * Ruft den Wert der versionSubminor-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getVersionSubminor() {
            return versionSubminor;
        }

        /**
         * Legt den Wert der versionSubminor-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setVersionSubminor(BigInteger value) {
            this.versionSubminor = value;
        }

    }

}
