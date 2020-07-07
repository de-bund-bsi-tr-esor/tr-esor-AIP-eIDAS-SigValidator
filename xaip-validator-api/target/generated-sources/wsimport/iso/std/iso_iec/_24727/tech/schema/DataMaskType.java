
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java-Klasse für DataMaskType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="DataMaskType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Tag" type="{http://www.w3.org/2001/XMLSchema}hexBinary" minOccurs="0"/&gt;
 *         &lt;choice&gt;
 *           &lt;element name="MatchingData" type="{urn:iso:std:iso-iec:24727:tech:schema}MatchingDataType"/&gt;
 *           &lt;element name="DataObject" type="{urn:iso:std:iso-iec:24727:tech:schema}DataMaskType"/&gt;
 *         &lt;/choice&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataMaskType", propOrder = {
    "tag",
    "matchingData",
    "dataObject"
})
public class DataMaskType {

    @XmlElement(name = "Tag", type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] tag;
    @XmlElement(name = "MatchingData")
    protected MatchingDataType matchingData;
    @XmlElement(name = "DataObject")
    protected DataMaskType dataObject;

    /**
     * Ruft den Wert der tag-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getTag() {
        return tag;
    }

    /**
     * Legt den Wert der tag-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTag(byte[] value) {
        this.tag = value;
    }

    /**
     * Ruft den Wert der matchingData-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link MatchingDataType }
     *     
     */
    public MatchingDataType getMatchingData() {
        return matchingData;
    }

    /**
     * Legt den Wert der matchingData-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link MatchingDataType }
     *     
     */
    public void setMatchingData(MatchingDataType value) {
        this.matchingData = value;
    }

    /**
     * Ruft den Wert der dataObject-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link DataMaskType }
     *     
     */
    public DataMaskType getDataObject() {
        return dataObject;
    }

    /**
     * Legt den Wert der dataObject-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link DataMaskType }
     *     
     */
    public void setDataObject(DataMaskType value) {
        this.dataObject = value;
    }

}
