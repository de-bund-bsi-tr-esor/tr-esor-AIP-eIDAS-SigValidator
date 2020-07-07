
package de.procilon.tresor._1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import de.bund.bsi.tr_esor.api._1.ResponseType;


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
 *         &lt;element name="responseData" type="{urn:de:procilon:tresor:1.2}MessageImprint" minOccurs="0"/&gt;
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
    "responseData"
})
@XmlRootElement(name = "HashResponse")
public class HashResponse
    extends ResponseType
{

    protected MessageImprint responseData;

    /**
     * Ruft den Wert der responseData-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link MessageImprint }
     *     
     */
    public MessageImprint getResponseData() {
        return responseData;
    }

    /**
     * Legt den Wert der responseData-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link MessageImprint }
     *     
     */
    public void setResponseData(MessageImprint value) {
        this.responseData = value;
    }

}
