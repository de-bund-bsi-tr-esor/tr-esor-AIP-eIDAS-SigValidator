
package de.bund.bsi.tr_esor.xaip._1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für EvidenceRecordType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="EvidenceRecordType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.bsi.bund.de/ecard/api/1.1}EvidenceRecordType"&gt;
 *       &lt;attribute name="AOID" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="VersionID" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EvidenceRecordType")
public class EvidenceRecordType
    extends de.bund.bsi.ecard.api._1.EvidenceRecordType
{

    @XmlAttribute(name = "AOID")
    protected String aoid;
    @XmlAttribute(name = "VersionID")
    protected String versionID;

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
     * Ruft den Wert der versionID-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersionID() {
        return versionID;
    }

    /**
     * Legt den Wert der versionID-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersionID(String value) {
        this.versionID = value;
    }

}
