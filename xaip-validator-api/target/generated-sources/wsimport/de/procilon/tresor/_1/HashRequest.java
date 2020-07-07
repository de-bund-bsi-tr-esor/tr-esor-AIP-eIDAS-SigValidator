
package de.procilon.tresor._1;

import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import de.bund.bsi.tr_esor.api._1.RequestType;
import iso.std.iso_iec._24727.tech.schema.AlgorithmIdentifierType;


/**
 * <p>Java-Klasse für anonymous complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.bsi.bund.de/tr-esor/api/1.2}RequestType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="hashAlgorithm" type="{urn:iso:std:iso-iec:24727:tech:schema}AlgorithmIdentifierType" minOccurs="0"/&gt;
 *         &lt;element name="requestData" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
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
    "hashAlgorithm",
    "requestData"
})
@XmlRootElement(name = "HashRequest")
public class HashRequest
    extends RequestType
{

    protected AlgorithmIdentifierType hashAlgorithm;
    @XmlElement(required = true)
    @XmlMimeType("application/octet-stream")
    protected DataHandler requestData;

    /**
     * Ruft den Wert der hashAlgorithm-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link AlgorithmIdentifierType }
     *     
     */
    public AlgorithmIdentifierType getHashAlgorithm() {
        return hashAlgorithm;
    }

    /**
     * Legt den Wert der hashAlgorithm-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link AlgorithmIdentifierType }
     *     
     */
    public void setHashAlgorithm(AlgorithmIdentifierType value) {
        this.hashAlgorithm = value;
    }

    /**
     * Ruft den Wert der requestData-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link DataHandler }
     *     
     */
    public DataHandler getRequestData() {
        return requestData;
    }

    /**
     * Legt den Wert der requestData-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link DataHandler }
     *     
     */
    public void setRequestData(DataHandler value) {
        this.requestData = value;
    }

}
