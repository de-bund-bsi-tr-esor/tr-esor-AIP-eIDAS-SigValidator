
package iso.std.iso_iec._24727.tech.schema;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java-Klasse für StateType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="StateType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="RetryCounter" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" minOccurs="0"/&gt;
 *         &lt;element name="UsageCounter" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" minOccurs="0"/&gt;
 *         &lt;element name="StateTransition" type="{urn:iso:std:iso-iec:24727:tech:schema}StateTransitionType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="StateName" use="required" type="{http://www.w3.org/2001/XMLSchema}ID" /&gt;
 *       &lt;attribute name="StateClass" use="required" type="{urn:iso:std:iso-iec:24727:tech:schema}StateClassType" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StateType", propOrder = {
    "retryCounter",
    "usageCounter",
    "stateTransition"
})
public class StateType {

    @XmlElement(name = "RetryCounter")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger retryCounter;
    @XmlElement(name = "UsageCounter")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger usageCounter;
    @XmlElement(name = "StateTransition")
    protected List<StateTransitionType> stateTransition;
    @XmlAttribute(name = "StateName", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String stateName;
    @XmlAttribute(name = "StateClass", required = true)
    protected StateClassType stateClass;

    /**
     * Ruft den Wert der retryCounter-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getRetryCounter() {
        return retryCounter;
    }

    /**
     * Legt den Wert der retryCounter-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setRetryCounter(BigInteger value) {
        this.retryCounter = value;
    }

    /**
     * Ruft den Wert der usageCounter-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getUsageCounter() {
        return usageCounter;
    }

    /**
     * Legt den Wert der usageCounter-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setUsageCounter(BigInteger value) {
        this.usageCounter = value;
    }

    /**
     * Gets the value of the stateTransition property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the stateTransition property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStateTransition().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StateTransitionType }
     * 
     * 
     */
    public List<StateTransitionType> getStateTransition() {
        if (stateTransition == null) {
            stateTransition = new ArrayList<StateTransitionType>();
        }
        return this.stateTransition;
    }

    /**
     * Ruft den Wert der stateName-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStateName() {
        return stateName;
    }

    /**
     * Legt den Wert der stateName-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStateName(String value) {
        this.stateName = value;
    }

    /**
     * Ruft den Wert der stateClass-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link StateClassType }
     *     
     */
    public StateClassType getStateClass() {
        return stateClass;
    }

    /**
     * Legt den Wert der stateClass-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link StateClassType }
     *     
     */
    public void setStateClass(StateClassType value) {
        this.stateClass = value;
    }

}
