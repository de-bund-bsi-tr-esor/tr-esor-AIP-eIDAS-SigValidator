
package iso.std.iso_iec._24727.tech.schema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für anonymous complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:iso:std:iso-iec:24727:tech:schema}ResponseType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CardApplicationServiceNameList"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="CardApplicationServiceName" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
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
    "cardApplicationServiceNameList"
})
@XmlRootElement(name = "CardApplicationServiceListResponse")
public class CardApplicationServiceListResponse
    extends ResponseType
{

    @XmlElement(name = "CardApplicationServiceNameList", required = true)
    protected CardApplicationServiceListResponse.CardApplicationServiceNameList cardApplicationServiceNameList;

    /**
     * Ruft den Wert der cardApplicationServiceNameList-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link CardApplicationServiceListResponse.CardApplicationServiceNameList }
     *     
     */
    public CardApplicationServiceListResponse.CardApplicationServiceNameList getCardApplicationServiceNameList() {
        return cardApplicationServiceNameList;
    }

    /**
     * Legt den Wert der cardApplicationServiceNameList-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link CardApplicationServiceListResponse.CardApplicationServiceNameList }
     *     
     */
    public void setCardApplicationServiceNameList(CardApplicationServiceListResponse.CardApplicationServiceNameList value) {
        this.cardApplicationServiceNameList = value;
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
     *         &lt;element name="CardApplicationServiceName" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
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
        "cardApplicationServiceName"
    })
    public static class CardApplicationServiceNameList {

        @XmlElement(name = "CardApplicationServiceName")
        protected List<String> cardApplicationServiceName;

        /**
         * Gets the value of the cardApplicationServiceName property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the cardApplicationServiceName property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getCardApplicationServiceName().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getCardApplicationServiceName() {
            if (cardApplicationServiceName == null) {
                cardApplicationServiceName = new ArrayList<String>();
            }
            return this.cardApplicationServiceName;
        }

    }

}
