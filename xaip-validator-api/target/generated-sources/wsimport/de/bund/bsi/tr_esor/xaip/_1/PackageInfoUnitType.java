
package de.bund.bsi.tr_esor.xaip._1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java-Klasse für packageInfoUnitType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="packageInfoUnitType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="unitType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="textInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="protectedObjectPointer" type="{http://www.w3.org/2001/XMLSchema}IDREF" maxOccurs="unbounded"/&gt;
 *         &lt;element name="unprotectedObjectPointer" type="{http://www.w3.org/2001/XMLSchema}IDREF" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="packageInfoUnit" type="{http://www.bsi.bund.de/tr-esor/xaip/1.2}packageInfoUnitType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="extension" type="{http://www.bsi.bund.de/tr-esor/xaip/1.2}extensionType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="packageUnitID" use="required" type="{http://www.w3.org/2001/XMLSchema}ID" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "packageInfoUnitType", propOrder = {
    "unitType",
    "textInfo",
    "protectedObjectPointer",
    "unprotectedObjectPointer",
    "packageInfoUnit",
    "extension"
})
public class PackageInfoUnitType {

    protected String unitType;
    protected String textInfo;
    @XmlElementRef(name = "protectedObjectPointer", namespace = "http://www.bsi.bund.de/tr-esor/xaip/1.2", type = JAXBElement.class)
    protected List<JAXBElement<Object>> protectedObjectPointer;
    @XmlElementRef(name = "unprotectedObjectPointer", namespace = "http://www.bsi.bund.de/tr-esor/xaip/1.2", type = JAXBElement.class, required = false)
    protected List<JAXBElement<Object>> unprotectedObjectPointer;
    protected List<PackageInfoUnitType> packageInfoUnit;
    protected ExtensionType extension;
    @XmlAttribute(name = "packageUnitID", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String packageUnitID;

    /**
     * Ruft den Wert der unitType-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnitType() {
        return unitType;
    }

    /**
     * Legt den Wert der unitType-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnitType(String value) {
        this.unitType = value;
    }

    /**
     * Ruft den Wert der textInfo-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTextInfo() {
        return textInfo;
    }

    /**
     * Legt den Wert der textInfo-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTextInfo(String value) {
        this.textInfo = value;
    }

    /**
     * Gets the value of the protectedObjectPointer property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the protectedObjectPointer property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProtectedObjectPointer().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link Object }{@code >}
     * 
     * 
     */
    public List<JAXBElement<Object>> getProtectedObjectPointer() {
        if (protectedObjectPointer == null) {
            protectedObjectPointer = new ArrayList<JAXBElement<Object>>();
        }
        return this.protectedObjectPointer;
    }

    /**
     * Gets the value of the unprotectedObjectPointer property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the unprotectedObjectPointer property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUnprotectedObjectPointer().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link Object }{@code >}
     * 
     * 
     */
    public List<JAXBElement<Object>> getUnprotectedObjectPointer() {
        if (unprotectedObjectPointer == null) {
            unprotectedObjectPointer = new ArrayList<JAXBElement<Object>>();
        }
        return this.unprotectedObjectPointer;
    }

    /**
     * Gets the value of the packageInfoUnit property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the packageInfoUnit property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPackageInfoUnit().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PackageInfoUnitType }
     * 
     * 
     */
    public List<PackageInfoUnitType> getPackageInfoUnit() {
        if (packageInfoUnit == null) {
            packageInfoUnit = new ArrayList<PackageInfoUnitType>();
        }
        return this.packageInfoUnit;
    }

    /**
     * Ruft den Wert der extension-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link ExtensionType }
     *     
     */
    public ExtensionType getExtension() {
        return extension;
    }

    /**
     * Legt den Wert der extension-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link ExtensionType }
     *     
     */
    public void setExtension(ExtensionType value) {
        this.extension = value;
    }

    /**
     * Ruft den Wert der packageUnitID-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPackageUnitID() {
        return packageUnitID;
    }

    /**
     * Legt den Wert der packageUnitID-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPackageUnitID(String value) {
        this.packageUnitID = value;
    }

}
