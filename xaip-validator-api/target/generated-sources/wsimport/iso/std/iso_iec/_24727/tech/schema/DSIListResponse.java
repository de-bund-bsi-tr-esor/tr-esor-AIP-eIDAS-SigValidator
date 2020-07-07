
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
 *         &lt;element name="DSINameList" type="{urn:iso:std:iso-iec:24727:tech:schema}DSINameListType"/&gt;
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
    "dsiNameList"
})
@XmlRootElement(name = "DSIListResponse")
public class DSIListResponse
    extends ResponseType
{

    @XmlElement(name = "DSINameList", required = true)
    protected DSINameListType dsiNameList;

    /**
     * Ruft den Wert der dsiNameList-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link DSINameListType }
     *     
     */
    public DSINameListType getDSINameList() {
        return dsiNameList;
    }

    /**
     * Legt den Wert der dsiNameList-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link DSINameListType }
     *     
     */
    public void setDSINameList(DSINameListType value) {
        this.dsiNameList = value;
    }

}
