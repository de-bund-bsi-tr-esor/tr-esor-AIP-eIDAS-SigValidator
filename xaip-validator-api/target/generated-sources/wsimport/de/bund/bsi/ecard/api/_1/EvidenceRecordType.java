
package de.bund.bsi.ecard.api._1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für EvidenceRecordType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="EvidenceRecordType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice&gt;
 *         &lt;element name="xmlEvidenceRecord" type="{http://www.setcce.org/schemas/ers}EvidenceRecordType"/&gt;
 *         &lt;element name="asn1EvidenceRecord" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
 *       &lt;/choice&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EvidenceRecordType", propOrder = {
    "xmlEvidenceRecord",
    "asn1EvidenceRecord"
})
@XmlSeeAlso({
    de.bund.bsi.tr_esor.xaip._1.EvidenceRecordType.class,
    EvidenceRecord.class
})
public class EvidenceRecordType {

    protected org.setcce.schemas.ers.EvidenceRecordType xmlEvidenceRecord;
    protected byte[] asn1EvidenceRecord;

    /**
     * Ruft den Wert der xmlEvidenceRecord-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link org.setcce.schemas.ers.EvidenceRecordType }
     *     
     */
    public org.setcce.schemas.ers.EvidenceRecordType getXmlEvidenceRecord() {
        return xmlEvidenceRecord;
    }

    /**
     * Legt den Wert der xmlEvidenceRecord-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link org.setcce.schemas.ers.EvidenceRecordType }
     *     
     */
    public void setXmlEvidenceRecord(org.setcce.schemas.ers.EvidenceRecordType value) {
        this.xmlEvidenceRecord = value;
    }

    /**
     * Ruft den Wert der asn1EvidenceRecord-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getAsn1EvidenceRecord() {
        return asn1EvidenceRecord;
    }

    /**
     * Legt den Wert der asn1EvidenceRecord-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setAsn1EvidenceRecord(byte[] value) {
        this.asn1EvidenceRecord = value;
    }

}
