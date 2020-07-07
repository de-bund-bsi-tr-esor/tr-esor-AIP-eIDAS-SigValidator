
package de.bund.bsi.tr_esor.api._1;

import java.util.ArrayList;
import java.util.List;
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
 *     &lt;extension base="{http://www.bsi.bund.de/tr-esor/api/1.2}RequestType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="AOID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="VersionID" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
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
    "aoid",
    "versionID"
})
@XmlRootElement(name = "ArchiveEvidenceRequest")
public class ArchiveEvidenceRequest
    extends RequestType
{

    @XmlElement(name = "AOID", required = true)
    protected String aoid;
    @XmlElement(name = "VersionID")
    protected List<String> versionID;

    /**
     * Ruft den Wert der aoid-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAOID() {
        return aoid;
    }

    /**
     * Legt den Wert der aoid-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAOID(String value) {
        this.aoid = value;
    }

    /**
     * Gets the value of the versionID property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the versionID property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVersionID().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getVersionID() {
        if (versionID == null) {
            versionID = new ArrayList<String>();
        }
        return this.versionID;
    }

}
