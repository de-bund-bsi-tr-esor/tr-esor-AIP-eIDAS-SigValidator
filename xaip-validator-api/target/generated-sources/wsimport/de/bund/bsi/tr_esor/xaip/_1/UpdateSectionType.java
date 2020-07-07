
package de.bund.bsi.tr_esor.xaip._1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für updateSectionType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="updateSectionType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="prevVersion" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="placeHolder" type="{http://www.bsi.bund.de/tr-esor/xaip/1.2}placeHolderType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updateSectionType", propOrder = {
    "prevVersion",
    "placeHolder"
})
public class UpdateSectionType {

    @XmlElement(required = true)
    protected String prevVersion;
    protected List<PlaceHolderType> placeHolder;

    /**
     * Ruft den Wert der prevVersion-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrevVersion() {
        return prevVersion;
    }

    /**
     * Legt den Wert der prevVersion-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrevVersion(String value) {
        this.prevVersion = value;
    }

    /**
     * Gets the value of the placeHolder property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the placeHolder property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPlaceHolder().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PlaceHolderType }
     * 
     * 
     */
    public List<PlaceHolderType> getPlaceHolder() {
        if (placeHolder == null) {
            placeHolder = new ArrayList<PlaceHolderType>();
        }
        return this.placeHolder;
    }

}
