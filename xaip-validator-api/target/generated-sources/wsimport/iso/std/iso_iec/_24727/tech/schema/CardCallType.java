
package iso.std.iso_iec._24727.tech.schema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import oasis.names.tc.dss._1_0.core.schema.AnyType;


/**
 * <p>Java-Klasse für CardCallType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="CardCallType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice&gt;
 *         &lt;sequence&gt;
 *           &lt;element name="CommandAPDU" type="{http://www.w3.org/2001/XMLSchema}hexBinary"/&gt;
 *           &lt;element name="ResponseAPDU" type="{urn:iso:std:iso-iec:24727:tech:schema}ResponseAPDUType" maxOccurs="unbounded"/&gt;
 *         &lt;/sequence&gt;
 *         &lt;sequence&gt;
 *           &lt;element name="APICall" type="{urn:oasis:names:tc:dss:1.0:core:schema}AnyType"/&gt;
 *           &lt;element name="APIResponse" type="{urn:oasis:names:tc:dss:1.0:core:schema}AnyType" maxOccurs="unbounded"/&gt;
 *         &lt;/sequence&gt;
 *       &lt;/choice&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CardCallType", propOrder = {
    "commandAPDU",
    "responseAPDU",
    "apiCall",
    "apiResponse"
})
@XmlSeeAlso({
    CardCall.class
})
public class CardCallType {

    @XmlElement(name = "CommandAPDU", type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] commandAPDU;
    @XmlElement(name = "ResponseAPDU")
    protected List<ResponseAPDUType> responseAPDU;
    @XmlElement(name = "APICall")
    protected AnyType apiCall;
    @XmlElement(name = "APIResponse")
    protected List<AnyType> apiResponse;

    /**
     * Ruft den Wert der commandAPDU-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getCommandAPDU() {
        return commandAPDU;
    }

    /**
     * Legt den Wert der commandAPDU-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommandAPDU(byte[] value) {
        this.commandAPDU = value;
    }

    /**
     * Gets the value of the responseAPDU property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the responseAPDU property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResponseAPDU().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ResponseAPDUType }
     * 
     * 
     */
    public List<ResponseAPDUType> getResponseAPDU() {
        if (responseAPDU == null) {
            responseAPDU = new ArrayList<ResponseAPDUType>();
        }
        return this.responseAPDU;
    }

    /**
     * Ruft den Wert der apiCall-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link AnyType }
     *     
     */
    public AnyType getAPICall() {
        return apiCall;
    }

    /**
     * Legt den Wert der apiCall-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link AnyType }
     *     
     */
    public void setAPICall(AnyType value) {
        this.apiCall = value;
    }

    /**
     * Gets the value of the apiResponse property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the apiResponse property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAPIResponse().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AnyType }
     * 
     * 
     */
    public List<AnyType> getAPIResponse() {
        if (apiResponse == null) {
            apiResponse = new ArrayList<AnyType>();
        }
        return this.apiResponse;
    }

}
