
package de.bund.bsi.tr_esor.xaip._1;

import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import oasis.names.tc.dss._1_0.core.schema.AnyType;


/**
 * <p>Java-Klasse für dataObjectType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="dataObjectType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;choice&gt;
 *           &lt;element name="binaryData"&gt;
 *             &lt;complexType&gt;
 *               &lt;simpleContent&gt;
 *                 &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;base64Binary"&gt;
 *                   &lt;attribute name="MimeType" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *                 &lt;/extension&gt;
 *               &lt;/simpleContent&gt;
 *             &lt;/complexType&gt;
 *           &lt;/element&gt;
 *           &lt;element name="xmlData" type="{urn:oasis:names:tc:dss:1.0:core:schema}AnyType"/&gt;
 *         &lt;/choice&gt;
 *         &lt;element name="checkSum" type="{http://www.bsi.bund.de/tr-esor/xaip/1.2}checkSumType" minOccurs="0"/&gt;
 *         &lt;element name="transformInfo" type="{http://www.bsi.bund.de/tr-esor/xaip/1.2}tranformInfoType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="dataObjectID" use="required" type="{http://www.w3.org/2001/XMLSchema}ID" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataObjectType", propOrder = {
    "binaryData",
    "xmlData",
    "checkSum",
    "transformInfo"
})
public class DataObjectType {

    protected DataObjectType.BinaryData binaryData;
    protected AnyType xmlData;
    protected CheckSumType checkSum;
    protected TranformInfoType transformInfo;
    @XmlAttribute(name = "dataObjectID", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String dataObjectID;

    /**
     * Ruft den Wert der binaryData-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link DataObjectType.BinaryData }
     *     
     */
    public DataObjectType.BinaryData getBinaryData() {
        return binaryData;
    }

    /**
     * Legt den Wert der binaryData-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link DataObjectType.BinaryData }
     *     
     */
    public void setBinaryData(DataObjectType.BinaryData value) {
        this.binaryData = value;
    }

    /**
     * Ruft den Wert der xmlData-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link AnyType }
     *     
     */
    public AnyType getXmlData() {
        return xmlData;
    }

    /**
     * Legt den Wert der xmlData-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link AnyType }
     *     
     */
    public void setXmlData(AnyType value) {
        this.xmlData = value;
    }

    /**
     * Ruft den Wert der checkSum-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link CheckSumType }
     *     
     */
    public CheckSumType getCheckSum() {
        return checkSum;
    }

    /**
     * Legt den Wert der checkSum-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link CheckSumType }
     *     
     */
    public void setCheckSum(CheckSumType value) {
        this.checkSum = value;
    }

    /**
     * Ruft den Wert der transformInfo-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link TranformInfoType }
     *     
     */
    public TranformInfoType getTransformInfo() {
        return transformInfo;
    }

    /**
     * Legt den Wert der transformInfo-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link TranformInfoType }
     *     
     */
    public void setTransformInfo(TranformInfoType value) {
        this.transformInfo = value;
    }

    /**
     * Ruft den Wert der dataObjectID-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataObjectID() {
        return dataObjectID;
    }

    /**
     * Legt den Wert der dataObjectID-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataObjectID(String value) {
        this.dataObjectID = value;
    }


    /**
     * <p>Java-Klasse für anonymous complex type.
     * 
     * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;simpleContent&gt;
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;base64Binary"&gt;
     *       &lt;attribute name="MimeType" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
     *     &lt;/extension&gt;
     *   &lt;/simpleContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class BinaryData {

        @XmlValue
        @XmlMimeType("application/octet-stream")
        protected DataHandler value;
        @XmlAttribute(name = "MimeType")
        protected String mimeType;

        /**
         * Ruft den Wert der value-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link DataHandler }
         *     
         */
        public DataHandler getValue() {
            return value;
        }

        /**
         * Legt den Wert der value-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link DataHandler }
         *     
         */
        public void setValue(DataHandler value) {
            this.value = value;
        }

        /**
         * Ruft den Wert der mimeType-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMimeType() {
            return mimeType;
        }

        /**
         * Legt den Wert der mimeType-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMimeType(String value) {
            this.mimeType = value;
        }

    }

}
