
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für InputUnitType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="InputUnitType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice&gt;
 *         &lt;element name="PinInput" type="{urn:iso:std:iso-iec:24727:tech:schema}PinInputType"/&gt;
 *         &lt;element name="BiometricInput" type="{urn:iso:std:iso-iec:24727:tech:schema}BiometricInputType"/&gt;
 *       &lt;/choice&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InputUnitType", propOrder = {
    "pinInput",
    "biometricInput"
})
public class InputUnitType {

    @XmlElement(name = "PinInput")
    protected PinInputType pinInput;
    @XmlElement(name = "BiometricInput")
    protected BiometricInputType biometricInput;

    /**
     * Ruft den Wert der pinInput-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PinInputType }
     *     
     */
    public PinInputType getPinInput() {
        return pinInput;
    }

    /**
     * Legt den Wert der pinInput-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PinInputType }
     *     
     */
    public void setPinInput(PinInputType value) {
        this.pinInput = value;
    }

    /**
     * Ruft den Wert der biometricInput-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BiometricInputType }
     *     
     */
    public BiometricInputType getBiometricInput() {
        return biometricInput;
    }

    /**
     * Legt den Wert der biometricInput-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BiometricInputType }
     *     
     */
    public void setBiometricInput(BiometricInputType value) {
        this.biometricInput = value;
    }

}
