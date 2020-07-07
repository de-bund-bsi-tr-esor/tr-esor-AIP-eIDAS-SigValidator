
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java-Klasse für ResponseAPDUType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="ResponseAPDUType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Body" type="{urn:iso:std:iso-iec:24727:tech:schema}DataMaskType" minOccurs="0"/&gt;
 *         &lt;element name="Trailer" type="{http://www.w3.org/2001/XMLSchema}hexBinary"/&gt;
 *         &lt;element ref="{urn:iso:std:iso-iec:24727:tech:schema}Conclusion" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResponseAPDUType", propOrder = {
    "body",
    "trailer",
    "conclusion"
})
public class ResponseAPDUType {

    @XmlElement(name = "Body")
    protected DataMaskType body;
    @XmlElement(name = "Trailer", required = true, type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] trailer;
    @XmlElement(name = "Conclusion")
    protected Conclusion conclusion;

    /**
     * Ruft den Wert der body-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link DataMaskType }
     *     
     */
    public DataMaskType getBody() {
        return body;
    }

    /**
     * Legt den Wert der body-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link DataMaskType }
     *     
     */
    public void setBody(DataMaskType value) {
        this.body = value;
    }

    /**
     * Ruft den Wert der trailer-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getTrailer() {
        return trailer;
    }

    /**
     * Legt den Wert der trailer-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrailer(byte[] value) {
        this.trailer = value;
    }

    /**
     * Ruft den Wert der conclusion-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Conclusion }
     *     
     */
    public Conclusion getConclusion() {
        return conclusion;
    }

    /**
     * Legt den Wert der conclusion-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Conclusion }
     *     
     */
    public void setConclusion(Conclusion value) {
        this.conclusion = value;
    }

}
