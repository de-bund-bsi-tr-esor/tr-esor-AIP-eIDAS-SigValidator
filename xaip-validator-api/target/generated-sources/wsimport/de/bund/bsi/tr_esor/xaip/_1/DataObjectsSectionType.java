
package de.bund.bsi.tr_esor.xaip._1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für dataObjectsSectionType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="dataObjectsSectionType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://www.bsi.bund.de/tr-esor/xaip/1.2}dataObject" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataObjectsSectionType", propOrder = {
    "dataObject"
})
public class DataObjectsSectionType {

    @XmlElement(required = true)
    protected List<DataObjectType> dataObject;

    /**
     * Gets the value of the dataObject property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dataObject property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDataObject().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DataObjectType }
     * 
     * 
     */
    public List<DataObjectType> getDataObject() {
        if (dataObject == null) {
            dataObject = new ArrayList<DataObjectType>();
        }
        return this.dataObject;
    }

}
