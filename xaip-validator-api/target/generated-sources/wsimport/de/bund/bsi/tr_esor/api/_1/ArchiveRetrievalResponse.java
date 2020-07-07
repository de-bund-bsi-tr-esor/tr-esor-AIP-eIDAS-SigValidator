
package de.bund.bsi.tr_esor.api._1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import de.bund.bsi.tr_esor.xaip._1.XAIPType;


/**
 * <p>Java-Klasse für anonymous complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.bsi.bund.de/tr-esor/api/1.2}ResponseType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://www.bsi.bund.de/tr-esor/xaip/1.2}XAIP" minOccurs="0"/&gt;
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
    "xaip"
})
@XmlRootElement(name = "ArchiveRetrievalResponse")
public class ArchiveRetrievalResponse
    extends ResponseType
{

    @XmlElement(name = "XAIP", namespace = "http://www.bsi.bund.de/tr-esor/xaip/1.2")
    protected XAIPType xaip;

    /**
     * Ruft den Wert der xaip-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XAIPType }
     *     
     */
    public XAIPType getXAIP() {
        return xaip;
    }

    /**
     * Legt den Wert der xaip-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XAIPType }
     *     
     */
    public void setXAIP(XAIPType value) {
        this.xaip = value;
    }

}
