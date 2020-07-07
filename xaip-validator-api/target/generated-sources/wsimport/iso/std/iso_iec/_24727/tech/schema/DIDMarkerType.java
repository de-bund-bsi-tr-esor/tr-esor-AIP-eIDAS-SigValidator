
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für DIDMarkerType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="DIDMarkerType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice&gt;
 *         &lt;element name="PinCompareMarker" type="{urn:iso:std:iso-iec:24727:tech:schema}PinCompareMarkerType"/&gt;
 *         &lt;element name="MutualAuthMarker" type="{urn:iso:std:iso-iec:24727:tech:schema}MutualAuthMarkerType"/&gt;
 *         &lt;element name="RSAAuthMarker" type="{urn:iso:std:iso-iec:24727:tech:schema}RSAAuthMarkerType"/&gt;
 *         &lt;element name="CryptoMarker" type="{urn:iso:std:iso-iec:24727:tech:schema}CryptoMarkerType"/&gt;
 *         &lt;element name="EACMarker" type="{urn:iso:std:iso-iec:24727:tech:schema}EACMarkerType"/&gt;
 *         &lt;element name="PACEMarker" type="{urn:iso:std:iso-iec:24727:tech:schema}PACEMarkerType"/&gt;
 *         &lt;element name="TAMarker" type="{urn:iso:std:iso-iec:24727:tech:schema}TAMarkerType"/&gt;
 *         &lt;element name="CAMarker" type="{urn:iso:std:iso-iec:24727:tech:schema}CAMarkerType"/&gt;
 *         &lt;element name="RIMarker" type="{urn:iso:std:iso-iec:24727:tech:schema}RIMarkerType"/&gt;
 *       &lt;/choice&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DIDMarkerType", propOrder = {
    "pinCompareMarker",
    "mutualAuthMarker",
    "rsaAuthMarker",
    "cryptoMarker",
    "eacMarker",
    "paceMarker",
    "taMarker",
    "caMarker",
    "riMarker"
})
public class DIDMarkerType {

    @XmlElement(name = "PinCompareMarker")
    protected PinCompareMarkerType pinCompareMarker;
    @XmlElement(name = "MutualAuthMarker")
    protected MutualAuthMarkerType mutualAuthMarker;
    @XmlElement(name = "RSAAuthMarker")
    protected RSAAuthMarkerType rsaAuthMarker;
    @XmlElement(name = "CryptoMarker")
    protected CryptoMarkerType cryptoMarker;
    @XmlElement(name = "EACMarker")
    protected EACMarkerType eacMarker;
    @XmlElement(name = "PACEMarker")
    protected PACEMarkerType paceMarker;
    @XmlElement(name = "TAMarker")
    protected TAMarkerType taMarker;
    @XmlElement(name = "CAMarker")
    protected CAMarkerType caMarker;
    @XmlElement(name = "RIMarker")
    protected RIMarkerType riMarker;

    /**
     * Ruft den Wert der pinCompareMarker-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PinCompareMarkerType }
     *     
     */
    public PinCompareMarkerType getPinCompareMarker() {
        return pinCompareMarker;
    }

    /**
     * Legt den Wert der pinCompareMarker-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PinCompareMarkerType }
     *     
     */
    public void setPinCompareMarker(PinCompareMarkerType value) {
        this.pinCompareMarker = value;
    }

    /**
     * Ruft den Wert der mutualAuthMarker-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link MutualAuthMarkerType }
     *     
     */
    public MutualAuthMarkerType getMutualAuthMarker() {
        return mutualAuthMarker;
    }

    /**
     * Legt den Wert der mutualAuthMarker-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link MutualAuthMarkerType }
     *     
     */
    public void setMutualAuthMarker(MutualAuthMarkerType value) {
        this.mutualAuthMarker = value;
    }

    /**
     * Ruft den Wert der rsaAuthMarker-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link RSAAuthMarkerType }
     *     
     */
    public RSAAuthMarkerType getRSAAuthMarker() {
        return rsaAuthMarker;
    }

    /**
     * Legt den Wert der rsaAuthMarker-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link RSAAuthMarkerType }
     *     
     */
    public void setRSAAuthMarker(RSAAuthMarkerType value) {
        this.rsaAuthMarker = value;
    }

    /**
     * Ruft den Wert der cryptoMarker-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link CryptoMarkerType }
     *     
     */
    public CryptoMarkerType getCryptoMarker() {
        return cryptoMarker;
    }

    /**
     * Legt den Wert der cryptoMarker-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link CryptoMarkerType }
     *     
     */
    public void setCryptoMarker(CryptoMarkerType value) {
        this.cryptoMarker = value;
    }

    /**
     * Ruft den Wert der eacMarker-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link EACMarkerType }
     *     
     */
    public EACMarkerType getEACMarker() {
        return eacMarker;
    }

    /**
     * Legt den Wert der eacMarker-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link EACMarkerType }
     *     
     */
    public void setEACMarker(EACMarkerType value) {
        this.eacMarker = value;
    }

    /**
     * Ruft den Wert der paceMarker-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PACEMarkerType }
     *     
     */
    public PACEMarkerType getPACEMarker() {
        return paceMarker;
    }

    /**
     * Legt den Wert der paceMarker-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PACEMarkerType }
     *     
     */
    public void setPACEMarker(PACEMarkerType value) {
        this.paceMarker = value;
    }

    /**
     * Ruft den Wert der taMarker-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link TAMarkerType }
     *     
     */
    public TAMarkerType getTAMarker() {
        return taMarker;
    }

    /**
     * Legt den Wert der taMarker-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link TAMarkerType }
     *     
     */
    public void setTAMarker(TAMarkerType value) {
        this.taMarker = value;
    }

    /**
     * Ruft den Wert der caMarker-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link CAMarkerType }
     *     
     */
    public CAMarkerType getCAMarker() {
        return caMarker;
    }

    /**
     * Legt den Wert der caMarker-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link CAMarkerType }
     *     
     */
    public void setCAMarker(CAMarkerType value) {
        this.caMarker = value;
    }

    /**
     * Ruft den Wert der riMarker-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link RIMarkerType }
     *     
     */
    public RIMarkerType getRIMarker() {
        return riMarker;
    }

    /**
     * Legt den Wert der riMarker-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link RIMarkerType }
     *     
     */
    public void setRIMarker(RIMarkerType value) {
        this.riMarker = value;
    }

}
