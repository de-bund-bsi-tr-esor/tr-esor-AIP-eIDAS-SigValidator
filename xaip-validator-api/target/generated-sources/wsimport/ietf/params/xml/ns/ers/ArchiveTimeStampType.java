
package ietf.params.xml.ns.ers;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für ArchiveTimeStampType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="ArchiveTimeStampType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="HashTree" type="{urn:ietf:params:xml:ns:ers}HashTreeType" minOccurs="0"/&gt;
 *         &lt;element name="TimeStamp" type="{urn:ietf:params:xml:ns:ers}TimeStampType"/&gt;
 *         &lt;element name="Attributes" type="{urn:ietf:params:xml:ns:ers}Attributes" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="Order" use="required" type="{urn:ietf:params:xml:ns:ers}OrderType" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArchiveTimeStampType", propOrder = {
    "hashTree",
    "timeStamp",
    "attributes"
})
public class ArchiveTimeStampType {

    @XmlElement(name = "HashTree")
    protected HashTreeType hashTree;
    @XmlElement(name = "TimeStamp", required = true)
    protected TimeStampType timeStamp;
    @XmlElement(name = "Attributes")
    protected Attributes attributes;
    @XmlAttribute(name = "Order", required = true)
    protected int order;

    /**
     * Ruft den Wert der hashTree-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link HashTreeType }
     *     
     */
    public HashTreeType getHashTree() {
        return hashTree;
    }

    /**
     * Legt den Wert der hashTree-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link HashTreeType }
     *     
     */
    public void setHashTree(HashTreeType value) {
        this.hashTree = value;
    }

    /**
     * Ruft den Wert der timeStamp-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link TimeStampType }
     *     
     */
    public TimeStampType getTimeStamp() {
        return timeStamp;
    }

    /**
     * Legt den Wert der timeStamp-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeStampType }
     *     
     */
    public void setTimeStamp(TimeStampType value) {
        this.timeStamp = value;
    }

    /**
     * Ruft den Wert der attributes-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Attributes }
     *     
     */
    public Attributes getAttributes() {
        return attributes;
    }

    /**
     * Legt den Wert der attributes-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Attributes }
     *     
     */
    public void setAttributes(Attributes value) {
        this.attributes = value;
    }

    /**
     * Ruft den Wert der order-Eigenschaft ab.
     * 
     */
    public int getOrder() {
        return order;
    }

    /**
     * Legt den Wert der order-Eigenschaft fest.
     * 
     */
    public void setOrder(int value) {
        this.order = value;
    }

}
