
package de.bund.bsi.tr_esor.api._1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import oasis.names.tc.dss._1_0.core.schema.Result;


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
 *         &lt;element name="XAIPData" maxOccurs="unbounded"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element ref="{urn:oasis:names:tc:dss:1.0:core:schema}Result"/&gt;
 *                   &lt;element ref="{http://www.bsi.bund.de/tr-esor/api/1.2}DataLocation"/&gt;
 *                   &lt;element name="Value" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
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
    "xaipData"
})
@XmlRootElement(name = "ArchiveDataResponse")
public class ArchiveDataResponse
    extends ResponseType
{

    @XmlElement(name = "XAIPData", required = true)
    protected List<ArchiveDataResponse.XAIPData> xaipData;

    /**
     * Gets the value of the xaipData property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the xaipData property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getXAIPData().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ArchiveDataResponse.XAIPData }
     * 
     * 
     */
    public List<ArchiveDataResponse.XAIPData> getXAIPData() {
        if (xaipData == null) {
            xaipData = new ArrayList<ArchiveDataResponse.XAIPData>();
        }
        return this.xaipData;
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
     *         &lt;element ref="{urn:oasis:names:tc:dss:1.0:core:schema}Result"/&gt;
     *         &lt;element ref="{http://www.bsi.bund.de/tr-esor/api/1.2}DataLocation"/&gt;
     *         &lt;element name="Value" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "result",
        "dataLocation",
        "value"
    })
    public static class XAIPData {

        @XmlElement(name = "Result", namespace = "urn:oasis:names:tc:dss:1.0:core:schema", required = true)
        protected Result result;
        @XmlElement(name = "DataLocation", required = true)
        protected DataLocation dataLocation;
        @XmlElement(name = "Value")
        protected Object value;

        /**
         * Ruft den Wert der result-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link Result }
         *     
         */
        public Result getResult() {
            return result;
        }

        /**
         * Legt den Wert der result-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link Result }
         *     
         */
        public void setResult(Result value) {
            this.result = value;
        }

        /**
         * Ruft den Wert der dataLocation-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link DataLocation }
         *     
         */
        public DataLocation getDataLocation() {
            return dataLocation;
        }

        /**
         * Legt den Wert der dataLocation-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link DataLocation }
         *     
         */
        public void setDataLocation(DataLocation value) {
            this.dataLocation = value;
        }

        /**
         * Ruft den Wert der value-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link Object }
         *     
         */
        public Object getValue() {
            return value;
        }

        /**
         * Legt den Wert der value-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link Object }
         *     
         */
        public void setValue(Object value) {
            this.value = value;
        }

    }

}
