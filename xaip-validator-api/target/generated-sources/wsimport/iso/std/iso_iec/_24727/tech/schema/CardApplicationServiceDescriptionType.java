
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für CardApplicationServiceDescriptionType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="CardApplicationServiceDescriptionType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice&gt;
 *         &lt;element name="ServiceDescriptionText" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="ServiceDescriptionURL" type="{http://www.w3.org/2001/XMLSchema}anyURI"/&gt;
 *       &lt;/choice&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CardApplicationServiceDescriptionType", propOrder = {
    "serviceDescriptionText",
    "serviceDescriptionURL"
})
public class CardApplicationServiceDescriptionType {

    @XmlElement(name = "ServiceDescriptionText")
    protected String serviceDescriptionText;
    @XmlElement(name = "ServiceDescriptionURL")
    @XmlSchemaType(name = "anyURI")
    protected String serviceDescriptionURL;

    /**
     * Ruft den Wert der serviceDescriptionText-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceDescriptionText() {
        return serviceDescriptionText;
    }

    /**
     * Legt den Wert der serviceDescriptionText-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceDescriptionText(String value) {
        this.serviceDescriptionText = value;
    }

    /**
     * Ruft den Wert der serviceDescriptionURL-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceDescriptionURL() {
        return serviceDescriptionURL;
    }

    /**
     * Legt den Wert der serviceDescriptionURL-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceDescriptionURL(String value) {
        this.serviceDescriptionURL = value;
    }

}
