
package de.bund.bsi.tr_esor.api._1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import de.bund.bsi.tr_esor.xaip._1.EvidenceRecordType;


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
 *         &lt;element ref="{http://www.bsi.bund.de/tr-esor/xaip/1.2}evidenceRecord" maxOccurs="unbounded" minOccurs="0"/&gt;
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
    "evidenceRecord"
})
@XmlRootElement(name = "ArchiveEvidenceResponse")
public class ArchiveEvidenceResponse
    extends ResponseType
{

    @XmlElement(namespace = "http://www.bsi.bund.de/tr-esor/xaip/1.2")
    protected List<EvidenceRecordType> evidenceRecord;

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

}
