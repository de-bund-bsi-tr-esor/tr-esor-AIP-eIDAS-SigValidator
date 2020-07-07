
package iso.std.iso_iec._24727.tech.schema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für ConclusionType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="ConclusionType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice&gt;
 *         &lt;element name="RecognizedState" type="{http://www.w3.org/2001/XMLSchema}IDREF"/&gt;
 *         &lt;element name="RecognizedCardType" type="{http://www.w3.org/2001/XMLSchema}anyURI"/&gt;
 *         &lt;element ref="{urn:iso:std:iso-iec:24727:tech:schema}CardCall" maxOccurs="unbounded"/&gt;
 *       &lt;/choice&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConclusionType", propOrder = {
    "recognizedState",
    "recognizedCardType",
    "cardCall"
})
@XmlSeeAlso({
    Conclusion.class
})
public class ConclusionType {

    @XmlElement(name = "RecognizedState")
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Object recognizedState;
    @XmlElement(name = "RecognizedCardType")
    @XmlSchemaType(name = "anyURI")
    protected String recognizedCardType;
    @XmlElement(name = "CardCall")
    protected List<CardCall> cardCall;

    /**
     * Ruft den Wert der recognizedState-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getRecognizedState() {
        return recognizedState;
    }

    /**
     * Legt den Wert der recognizedState-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setRecognizedState(Object value) {
        this.recognizedState = value;
    }

    /**
     * Ruft den Wert der recognizedCardType-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecognizedCardType() {
        return recognizedCardType;
    }

    /**
     * Legt den Wert der recognizedCardType-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecognizedCardType(String value) {
        this.recognizedCardType = value;
    }

    /**
     * Gets the value of the cardCall property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cardCall property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCardCall().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CardCall }
     * 
     * 
     */
    public List<CardCall> getCardCall() {
        if (cardCall == null) {
            cardCall = new ArrayList<CardCall>();
        }
        return this.cardCall;
    }

}
