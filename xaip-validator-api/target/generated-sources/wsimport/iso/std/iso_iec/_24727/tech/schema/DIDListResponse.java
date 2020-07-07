
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
 *         &lt;element name="DIDNameList" type="{urn:iso:std:iso-iec:24727:tech:schema}DIDNameListType"/&gt;
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
    "didNameList"
})
@XmlRootElement(name = "DIDListResponse")
public class DIDListResponse
    extends ResponseType
{

    @XmlElement(name = "DIDNameList", required = true)
    protected DIDNameListType didNameList;

    /**
     * Ruft den Wert der didNameList-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link DIDNameListType }
     *     
     */
    public DIDNameListType getDIDNameList() {
        return didNameList;
    }

    /**
     * Legt den Wert der didNameList-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link DIDNameListType }
     *     
     */
    public void setDIDNameList(DIDNameListType value) {
        this.didNameList = value;
    }

}
