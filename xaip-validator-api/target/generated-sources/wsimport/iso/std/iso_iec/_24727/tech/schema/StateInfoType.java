
package iso.std.iso_iec._24727.tech.schema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für StateInfoType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="StateInfoType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="StateRecognition" type="{urn:iso:std:iso-iec:24727:tech:schema}CardCallSequenceType" minOccurs="0"/&gt;
 *         &lt;element name="State" type="{urn:iso:std:iso-iec:24727:tech:schema}StateType" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StateInfoType", propOrder = {
    "stateRecognition",
    "state"
})
@XmlSeeAlso({
    StateInfo.class
})
public class StateInfoType {

    @XmlElement(name = "StateRecognition")
    protected CardCallSequenceType stateRecognition;
    @XmlElement(name = "State", required = true)
    protected List<StateType> state;

    /**
     * Ruft den Wert der stateRecognition-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link CardCallSequenceType }
     *     
     */
    public CardCallSequenceType getStateRecognition() {
        return stateRecognition;
    }

    /**
     * Legt den Wert der stateRecognition-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link CardCallSequenceType }
     *     
     */
    public void setStateRecognition(CardCallSequenceType value) {
        this.stateRecognition = value;
    }

    /**
     * Gets the value of the state property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the state property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getState().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StateType }
     * 
     * 
     */
    public List<StateType> getState() {
        if (state == null) {
            state = new ArrayList<StateType>();
        }
        return this.state;
    }

}
