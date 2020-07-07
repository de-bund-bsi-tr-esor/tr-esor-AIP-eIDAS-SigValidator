
package ietf.params.xml.ns.ers;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für ArchiveTimeStampSequenceType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="ArchiveTimeStampSequenceType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ArchiveTimeStampChain" maxOccurs="unbounded"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="DigestMethod" type="{urn:ietf:params:xml:ns:ers}DigestMethodType"/&gt;
 *                   &lt;element name="CanonicalizationMethod" type="{urn:ietf:params:xml:ns:ers}CanonicalizationMethodType"/&gt;
 *                   &lt;element name="ArchiveTimeStamp" type="{urn:ietf:params:xml:ns:ers}ArchiveTimeStampType" maxOccurs="unbounded"/&gt;
 *                 &lt;/sequence&gt;
 *                 &lt;attribute name="Order" use="required" type="{urn:ietf:params:xml:ns:ers}OrderType" /&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArchiveTimeStampSequenceType", propOrder = {
    "archiveTimeStampChain"
})
public class ArchiveTimeStampSequenceType {

    @XmlElement(name = "ArchiveTimeStampChain", required = true)
    protected List<ArchiveTimeStampSequenceType.ArchiveTimeStampChain> archiveTimeStampChain;

    /**
     * Gets the value of the archiveTimeStampChain property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the archiveTimeStampChain property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getArchiveTimeStampChain().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ArchiveTimeStampSequenceType.ArchiveTimeStampChain }
     * 
     * 
     */
    public List<ArchiveTimeStampSequenceType.ArchiveTimeStampChain> getArchiveTimeStampChain() {
        if (archiveTimeStampChain == null) {
            archiveTimeStampChain = new ArrayList<ArchiveTimeStampSequenceType.ArchiveTimeStampChain>();
        }
        return this.archiveTimeStampChain;
    }


    /**
     * <p>Java-Klasse für anonymous complex type.
     * 
     * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="DigestMethod" type="{urn:ietf:params:xml:ns:ers}DigestMethodType"/&gt;
     *         &lt;element name="CanonicalizationMethod" type="{urn:ietf:params:xml:ns:ers}CanonicalizationMethodType"/&gt;
     *         &lt;element name="ArchiveTimeStamp" type="{urn:ietf:params:xml:ns:ers}ArchiveTimeStampType" maxOccurs="unbounded"/&gt;
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
    @XmlType(name = "", propOrder = {
        "digestMethod",
        "canonicalizationMethod",
        "archiveTimeStamp"
    })
    public static class ArchiveTimeStampChain {

        @XmlElement(name = "DigestMethod", required = true)
        protected DigestMethodType digestMethod;
        @XmlElement(name = "CanonicalizationMethod", required = true)
        protected CanonicalizationMethodType canonicalizationMethod;
        @XmlElement(name = "ArchiveTimeStamp", required = true)
        protected List<ArchiveTimeStampType> archiveTimeStamp;
        @XmlAttribute(name = "Order", required = true)
        protected int order;

        /**
         * Ruft den Wert der digestMethod-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link DigestMethodType }
         *     
         */
        public DigestMethodType getDigestMethod() {
            return digestMethod;
        }

        /**
         * Legt den Wert der digestMethod-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link DigestMethodType }
         *     
         */
        public void setDigestMethod(DigestMethodType value) {
            this.digestMethod = value;
        }

        /**
         * Ruft den Wert der canonicalizationMethod-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link CanonicalizationMethodType }
         *     
         */
        public CanonicalizationMethodType getCanonicalizationMethod() {
            return canonicalizationMethod;
        }

        /**
         * Legt den Wert der canonicalizationMethod-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link CanonicalizationMethodType }
         *     
         */
        public void setCanonicalizationMethod(CanonicalizationMethodType value) {
            this.canonicalizationMethod = value;
        }

        /**
         * Gets the value of the archiveTimeStamp property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the archiveTimeStamp property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getArchiveTimeStamp().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ArchiveTimeStampType }
         * 
         * 
         */
        public List<ArchiveTimeStampType> getArchiveTimeStamp() {
            if (archiveTimeStamp == null) {
                archiveTimeStamp = new ArrayList<ArchiveTimeStampType>();
            }
            return this.archiveTimeStamp;
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

}
