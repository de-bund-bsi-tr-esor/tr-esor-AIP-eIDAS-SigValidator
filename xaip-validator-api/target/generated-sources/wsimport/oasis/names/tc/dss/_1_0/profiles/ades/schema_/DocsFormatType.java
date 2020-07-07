
package oasis.names.tc.dss._1_0.profiles.ades.schema_;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für DocsFormatType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="DocsFormatType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;choice&gt;
 *           &lt;element name="DocFormat" type="{urn:oasis:names:tc:dss:1.0:profiles:AdES:schema#}DocFormatType" maxOccurs="unbounded"/&gt;
 *           &lt;element name="BinaryValue" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
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
@XmlType(name = "DocsFormatType", propOrder = {
    "docFormat",
    "binaryValue"
})
public class DocsFormatType {

    @XmlElement(name = "DocFormat")
    protected List<DocFormatType> docFormat;
    @XmlElement(name = "BinaryValue")
    protected byte[] binaryValue;

    /**
     * Gets the value of the docFormat property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the docFormat property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDocFormat().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DocFormatType }
     * 
     * 
     */
    public List<DocFormatType> getDocFormat() {
        if (docFormat == null) {
            docFormat = new ArrayList<DocFormatType>();
        }
        return this.docFormat;
    }

    /**
     * Ruft den Wert der binaryValue-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getBinaryValue() {
        return binaryValue;
    }

    /**
     * Legt den Wert der binaryValue-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setBinaryValue(byte[] value) {
        this.binaryValue = value;
    }

}
