
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für CAKeyInfoType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="CAKeyInfoType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice&gt;
 *         &lt;element name="PrivateKeyValue" type="{urn:iso:std:iso-iec:24727:tech:schema}KeyValueType"/&gt;
 *         &lt;element name="generateFlag" type="{urn:iso:std:iso-iec:24727:tech:schema}NULL"/&gt;
 *       &lt;/choice&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CAKeyInfoType", propOrder = {
    "privateKeyValue",
    "generateFlag"
})
public class CAKeyInfoType {

    @XmlElement(name = "PrivateKeyValue")
    protected KeyValueType privateKeyValue;
    protected NULL generateFlag;

    /**
     * Ruft den Wert der privateKeyValue-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link KeyValueType }
     *     
     */
    public KeyValueType getPrivateKeyValue() {
        return privateKeyValue;
    }

    /**
     * Legt den Wert der privateKeyValue-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link KeyValueType }
     *     
     */
    public void setPrivateKeyValue(KeyValueType value) {
        this.privateKeyValue = value;
    }

    /**
     * Ruft den Wert der generateFlag-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link NULL }
     *     
     */
    public NULL getGenerateFlag() {
        return generateFlag;
    }

    /**
     * Legt den Wert der generateFlag-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link NULL }
     *     
     */
    public void setGenerateFlag(NULL value) {
        this.generateFlag = value;
    }

}
