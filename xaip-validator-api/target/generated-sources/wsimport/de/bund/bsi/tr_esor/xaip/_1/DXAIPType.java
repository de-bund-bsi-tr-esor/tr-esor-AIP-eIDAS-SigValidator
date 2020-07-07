
package de.bund.bsi.tr_esor.xaip._1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für DXAIPType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="DXAIPType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.bsi.bund.de/tr-esor/xaip/1.2}XAIPType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="updateSection" type="{http://www.bsi.bund.de/tr-esor/xaip/1.2}updateSectionType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DXAIPType", propOrder = {
    "updateSection"
})
public class DXAIPType
    extends XAIPType
{

    @XmlElement(required = true)
    protected UpdateSectionType updateSection;

    /**
     * Ruft den Wert der updateSection-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link UpdateSectionType }
     *     
     */
    public UpdateSectionType getUpdateSection() {
        return updateSection;
    }

    /**
     * Legt den Wert der updateSection-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link UpdateSectionType }
     *     
     */
    public void setUpdateSection(UpdateSectionType value) {
        this.updateSection = value;
    }

}
