
package iso.std.iso_iec._24727.tech.schema;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für StateTransitionType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="StateTransitionType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;choice maxOccurs="unbounded"&gt;
 *           &lt;element name="DIDAuthenticationState" type="{urn:iso:std:iso-iec:24727:tech:schema}DIDAuthenticationStateType"/&gt;
 *           &lt;element name="RetryCounter" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger"/&gt;
 *           &lt;element name="UsageCounter" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger"/&gt;
 *           &lt;element name="FixedProcedure" type="{urn:iso:std:iso-iec:24727:tech:schema}CardCallSequenceType"/&gt;
 *         &lt;/choice&gt;
 *         &lt;element name="UpdateCounter" type="{urn:iso:std:iso-iec:24727:tech:schema}UpdateCounterType" maxOccurs="2" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="TargetState" type="{http://www.w3.org/2001/XMLSchema}IDREF" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StateTransitionType", propOrder = {
    "didAuthenticationStateOrRetryCounterOrUsageCounter",
    "updateCounter"
})
public class StateTransitionType {

    @XmlElementRefs({
        @XmlElementRef(name = "DIDAuthenticationState", namespace = "urn:iso:std:iso-iec:24727:tech:schema", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "RetryCounter", namespace = "urn:iso:std:iso-iec:24727:tech:schema", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "UsageCounter", namespace = "urn:iso:std:iso-iec:24727:tech:schema", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "FixedProcedure", namespace = "urn:iso:std:iso-iec:24727:tech:schema", type = JAXBElement.class, required = false)
    })
    protected List<JAXBElement<?>> didAuthenticationStateOrRetryCounterOrUsageCounter;
    @XmlElement(name = "UpdateCounter")
    protected List<UpdateCounterType> updateCounter;
    @XmlAttribute(name = "TargetState")
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Object targetState;

    /**
     * Gets the value of the didAuthenticationStateOrRetryCounterOrUsageCounter property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the didAuthenticationStateOrRetryCounterOrUsageCounter property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDIDAuthenticationStateOrRetryCounterOrUsageCounter().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link DIDAuthenticationStateType }{@code >}
     * {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     * {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     * {@link JAXBElement }{@code <}{@link CardCallSequenceType }{@code >}
     * 
     * 
     */
    public List<JAXBElement<?>> getDIDAuthenticationStateOrRetryCounterOrUsageCounter() {
        if (didAuthenticationStateOrRetryCounterOrUsageCounter == null) {
            didAuthenticationStateOrRetryCounterOrUsageCounter = new ArrayList<JAXBElement<?>>();
        }
        return this.didAuthenticationStateOrRetryCounterOrUsageCounter;
    }

    /**
     * Gets the value of the updateCounter property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the updateCounter property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUpdateCounter().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UpdateCounterType }
     * 
     * 
     */
    public List<UpdateCounterType> getUpdateCounter() {
        if (updateCounter == null) {
            updateCounter = new ArrayList<UpdateCounterType>();
        }
        return this.updateCounter;
    }

    /**
     * Ruft den Wert der targetState-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getTargetState() {
        return targetState;
    }

    /**
     * Legt den Wert der targetState-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setTargetState(Object value) {
        this.targetState = value;
    }

}
