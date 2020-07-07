
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für anonymous complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:iso:std:iso-iec:24727:tech:schema}ResponseType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="DIDStructure" type="{urn:iso:std:iso-iec:24727:tech:schema}DIDStructureType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "didStructure"
})
@XmlRootElement(name = "DIDGetResponse")
public class DIDGetResponse
    extends ResponseType
{

    @XmlElement(name = "DIDStructure", required = true)
    protected DIDStructureType didStructure;

    /**
     * Ruft den Wert der didStructure-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link DIDStructureType }
     *     
     */
    public DIDStructureType getDIDStructure() {
        return didStructure;
    }

    /**
     * Legt den Wert der didStructure-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link DIDStructureType }
     *     
     */
    public void setDIDStructure(DIDStructureType value) {
        this.didStructure = value;
    }

}
