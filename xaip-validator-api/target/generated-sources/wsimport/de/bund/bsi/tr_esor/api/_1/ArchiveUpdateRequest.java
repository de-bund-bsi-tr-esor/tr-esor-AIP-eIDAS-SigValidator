
package de.bund.bsi.tr_esor.api._1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import de.bund.bsi.tr_esor.xaip._1.DXAIPType;


/**
 * <p>Java-Klasse für anonymous complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.bsi.bund.de/tr-esor/api/1.2}RequestType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://www.bsi.bund.de/tr-esor/xaip/1.2}DXAIP"/&gt;
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
    "dxaip"
})
@XmlRootElement(name = "ArchiveUpdateRequest")
public class ArchiveUpdateRequest
    extends RequestType
{

    @XmlElement(name = "DXAIP", namespace = "http://www.bsi.bund.de/tr-esor/xaip/1.2", required = true)
    protected DXAIPType dxaip;

    /**
     * Ruft den Wert der dxaip-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link DXAIPType }
     *     
     */
    public DXAIPType getDXAIP() {
        return dxaip;
    }

    /**
     * Legt den Wert der dxaip-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link DXAIPType }
     *     
     */
    public void setDXAIP(DXAIPType value) {
        this.dxaip = value;
    }

}
