
package de.procilon.tresor._1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import de.bund.bsi.tr_esor.api._1.RequestType;


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
 *         &lt;element name="requestData" type="{urn:de:procilon:tresor:1.2}MessageImprint"/&gt;
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
    "requestData"
})
@XmlRootElement(name = "TimestampRequest")
public class TimestampRequest
    extends RequestType
{

    @XmlElement(required = true)
    protected MessageImprint requestData;

    /**
     * Ruft den Wert der requestData-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link MessageImprint }
     *     
     */
    public MessageImprint getRequestData() {
        return requestData;
    }

    /**
     * Legt den Wert der requestData-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link MessageImprint }
     *     
     */
    public void setRequestData(MessageImprint value) {
        this.requestData = value;
    }

}
