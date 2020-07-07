
package iso.std.iso_iec._24727.tech.schema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für IFDStatusType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="IFDStatusType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="IFDName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Connected" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="SlotStatus" type="{urn:iso:std:iso-iec:24727:tech:schema}SlotStatusType" maxOccurs="unbounded"/&gt;
 *         &lt;element name="ActiveAntenna" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="DisplayStatus" type="{urn:iso:std:iso-iec:24727:tech:schema}SimpleFUStatusType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="KeyPadStatus" type="{urn:iso:std:iso-iec:24727:tech:schema}SimpleFUStatusType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="BioSensorStatus" type="{urn:iso:std:iso-iec:24727:tech:schema}SimpleFUStatusType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IFDStatusType", propOrder = {
    "ifdName",
    "connected",
    "slotStatus",
    "activeAntenna",
    "displayStatus",
    "keyPadStatus",
    "bioSensorStatus"
})
public class IFDStatusType {

    @XmlElement(name = "IFDName")
    protected String ifdName;
    @XmlElement(name = "Connected")
    protected Boolean connected;
    @XmlElement(name = "SlotStatus", required = true)
    protected List<SlotStatusType> slotStatus;
    @XmlElement(name = "ActiveAntenna")
    protected Boolean activeAntenna;
    @XmlElement(name = "DisplayStatus")
    protected List<SimpleFUStatusType> displayStatus;
    @XmlElement(name = "KeyPadStatus")
    protected List<SimpleFUStatusType> keyPadStatus;
    @XmlElement(name = "BioSensorStatus")
    protected List<SimpleFUStatusType> bioSensorStatus;

    /**
     * Ruft den Wert der ifdName-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIFDName() {
        return ifdName;
    }

    /**
     * Legt den Wert der ifdName-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIFDName(String value) {
        this.ifdName = value;
    }

    /**
     * Ruft den Wert der connected-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isConnected() {
        return connected;
    }

    /**
     * Legt den Wert der connected-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setConnected(Boolean value) {
        this.connected = value;
    }

    /**
     * Gets the value of the slotStatus property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the slotStatus property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSlotStatus().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SlotStatusType }
     * 
     * 
     */
    public List<SlotStatusType> getSlotStatus() {
        if (slotStatus == null) {
            slotStatus = new ArrayList<SlotStatusType>();
        }
        return this.slotStatus;
    }

    /**
     * Ruft den Wert der activeAntenna-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isActiveAntenna() {
        return activeAntenna;
    }

    /**
     * Legt den Wert der activeAntenna-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setActiveAntenna(Boolean value) {
        this.activeAntenna = value;
    }

    /**
     * Gets the value of the displayStatus property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the displayStatus property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDisplayStatus().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SimpleFUStatusType }
     * 
     * 
     */
    public List<SimpleFUStatusType> getDisplayStatus() {
        if (displayStatus == null) {
            displayStatus = new ArrayList<SimpleFUStatusType>();
        }
        return this.displayStatus;
    }

    /**
     * Gets the value of the keyPadStatus property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the keyPadStatus property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getKeyPadStatus().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SimpleFUStatusType }
     * 
     * 
     */
    public List<SimpleFUStatusType> getKeyPadStatus() {
        if (keyPadStatus == null) {
            keyPadStatus = new ArrayList<SimpleFUStatusType>();
        }
        return this.keyPadStatus;
    }

    /**
     * Gets the value of the bioSensorStatus property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bioSensorStatus property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBioSensorStatus().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SimpleFUStatusType }
     * 
     * 
     */
    public List<SimpleFUStatusType> getBioSensorStatus() {
        if (bioSensorStatus == null) {
            bioSensorStatus = new ArrayList<SimpleFUStatusType>();
        }
        return this.bioSensorStatus;
    }

}
