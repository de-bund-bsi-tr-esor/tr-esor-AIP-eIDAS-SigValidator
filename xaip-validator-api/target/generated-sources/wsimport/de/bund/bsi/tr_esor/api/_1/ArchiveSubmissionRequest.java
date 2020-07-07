
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
 *     &lt;extension base="{http://www.bsi.bund.de/tr-esor/api/1.2}RequestType"&gt;
 *       &lt;choice&gt;
 *         &lt;element ref="{http://www.bsi.bund.de/tr-esor/xaip/1.2}XAIP"/&gt;
 *         &lt;element name="ArchiveData" type="{http://www.bsi.bund.de/tr-esor/api/1.2}ArchiveDataType"/&gt;
 *       &lt;/choice&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "xaip",
    "archiveData"
})
@XmlRootElement(name = "ArchiveSubmissionRequest")
public class ArchiveSubmissionRequest
    extends RequestType
{

    @XmlElement(name = "XAIP", namespace = "http://www.bsi.bund.de/tr-esor/xaip/1.2")
    protected XAIPType xaip;
    @XmlElement(name = "ArchiveData")
    protected ArchiveDataType archiveData;

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

    /**
     * Ruft den Wert der archiveData-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link ArchiveDataType }
     *     
     */
    public ArchiveDataType getArchiveData() {
        return archiveData;
    }

    /**
     * Legt den Wert der archiveData-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link ArchiveDataType }
     *     
     */
    public void setArchiveData(ArchiveDataType value) {
        this.archiveData = value;
    }

}
