
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java-Klasse für DIDStructureType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="DIDStructureType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="DIDName" type="{urn:iso:std:iso-iec:24727:tech:schema}DIDNameType"/&gt;
 *         &lt;element name="DIDScope" type="{urn:iso:std:iso-iec:24727:tech:schema}DIDScopeType"/&gt;
 *         &lt;element name="Authenticated" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="DIDMarker" type="{urn:iso:std:iso-iec:24727:tech:schema}DIDAbstractMarkerType"/&gt;
 *         &lt;element name="DIDQualifier" type="{urn:iso:std:iso-iec:24727:tech:schema}DIDQualifierType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DIDStructureType", propOrder = {
    "didName",
    "didScope",
    "authenticated",
    "didMarker",
    "didQualifier"
})
public class DIDStructureType {

    @XmlElement(name = "DIDName", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String didName;
    @XmlElement(name = "DIDScope", required = true)
    @XmlSchemaType(name = "string")
    protected DIDScopeType didScope;
    @XmlElement(name = "Authenticated")
    protected boolean authenticated;
    @XmlElement(name = "DIDMarker", required = true)
    protected DIDAbstractMarkerType didMarker;
    @XmlElement(name = "DIDQualifier")
    protected DIDQualifierType didQualifier;

    /**
     * Ruft den Wert der didName-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDIDName() {
        return didName;
    }

    /**
     * Legt den Wert der didName-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDIDName(String value) {
        this.didName = value;
    }

    /**
     * Ruft den Wert der didScope-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link DIDScopeType }
     *     
     */
    public DIDScopeType getDIDScope() {
        return didScope;
    }

    /**
     * Legt den Wert der didScope-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link DIDScopeType }
     *     
     */
    public void setDIDScope(DIDScopeType value) {
        this.didScope = value;
    }

    /**
     * Ruft den Wert der authenticated-Eigenschaft ab.
     * 
     */
    public boolean isAuthenticated() {
        return authenticated;
    }

    /**
     * Legt den Wert der authenticated-Eigenschaft fest.
     * 
     */
    public void setAuthenticated(boolean value) {
        this.authenticated = value;
    }

    /**
     * Ruft den Wert der didMarker-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link DIDAbstractMarkerType }
     *     
     */
    public DIDAbstractMarkerType getDIDMarker() {
        return didMarker;
    }

    /**
     * Legt den Wert der didMarker-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link DIDAbstractMarkerType }
     *     
     */
    public void setDIDMarker(DIDAbstractMarkerType value) {
        this.didMarker = value;
    }

    /**
     * Ruft den Wert der didQualifier-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link DIDQualifierType }
     *     
     */
    public DIDQualifierType getDIDQualifier() {
        return didQualifier;
    }

    /**
     * Legt den Wert der didQualifier-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link DIDQualifierType }
     *     
     */
    public void setDIDQualifier(DIDQualifierType value) {
        this.didQualifier = value;
    }

}
