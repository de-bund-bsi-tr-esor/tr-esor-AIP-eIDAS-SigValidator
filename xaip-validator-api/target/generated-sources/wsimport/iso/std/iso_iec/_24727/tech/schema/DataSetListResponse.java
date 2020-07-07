
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
 *         &lt;element name="DataSetNameList" type="{urn:iso:std:iso-iec:24727:tech:schema}DataSetNameListType"/&gt;
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
    "dataSetNameList"
})
@XmlRootElement(name = "DataSetListResponse")
public class DataSetListResponse
    extends ResponseType
{

    @XmlElement(name = "DataSetNameList", required = true)
    protected DataSetNameListType dataSetNameList;

    /**
     * Ruft den Wert der dataSetNameList-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link DataSetNameListType }
     *     
     */
    public DataSetNameListType getDataSetNameList() {
        return dataSetNameList;
    }

    /**
     * Legt den Wert der dataSetNameList-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link DataSetNameListType }
     *     
     */
    public void setDataSetNameList(DataSetNameListType value) {
        this.dataSetNameList = value;
    }

}
