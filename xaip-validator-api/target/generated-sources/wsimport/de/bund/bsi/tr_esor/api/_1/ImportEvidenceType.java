
package de.bund.bsi.tr_esor.api._1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import de.bund.bsi.tr_esor.xaip._1.EvidenceRecordType;


/**
 * <p>Java-Klasse für ImportEvidenceType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="ImportEvidenceType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice&gt;
 *         &lt;element ref="{http://www.bsi.bund.de/tr-esor/xaip/1.2}evidenceRecord" maxOccurs="unbounded"/&gt;
 *         &lt;element name="CredentialID" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/&gt;
 *       &lt;/choice&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ImportEvidenceType", propOrder = {
    "evidenceRecord",
    "credentialID"
})
public class ImportEvidenceType {

    @XmlElement(namespace = "http://www.bsi.bund.de/tr-esor/xaip/1.2")
    protected List<EvidenceRecordType> evidenceRecord;
    @XmlElement(name = "CredentialID")
    protected List<String> credentialID;

    /**
     * Gets the value of the evidenceRecord property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the evidenceRecord property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEvidenceRecord().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EvidenceRecordType }
     * 
     * 
     */
    public List<EvidenceRecordType> getEvidenceRecord() {
        if (evidenceRecord == null) {
            evidenceRecord = new ArrayList<EvidenceRecordType>();
        }
        return this.evidenceRecord;
    }

    /**
     * Gets the value of the credentialID property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the credentialID property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCredentialID().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getCredentialID() {
        if (credentialID == null) {
            credentialID = new ArrayList<String>();
        }
        return this.credentialID;
    }

}
