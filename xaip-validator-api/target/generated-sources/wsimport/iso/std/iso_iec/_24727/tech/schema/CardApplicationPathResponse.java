
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
 *         &lt;element name="CardAppPathResultSet"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence maxOccurs="unbounded" minOccurs="0"&gt;
 *                   &lt;element name="CardApplicationPathResult" type="{urn:iso:std:iso-iec:24727:tech:schema}CardApplicationPathType"/&gt;
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
    "cardAppPathResultSet"
})
@XmlRootElement(name = "CardApplicationPathResponse")
public class CardApplicationPathResponse
    extends ResponseType
{

    @XmlElement(name = "CardAppPathResultSet", required = true)
    protected CardApplicationPathResponse.CardAppPathResultSet cardAppPathResultSet;

    /**
     * Ruft den Wert der cardAppPathResultSet-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link CardApplicationPathResponse.CardAppPathResultSet }
     *     
     */
    public CardApplicationPathResponse.CardAppPathResultSet getCardAppPathResultSet() {
        return cardAppPathResultSet;
    }

    /**
     * Legt den Wert der cardAppPathResultSet-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link CardApplicationPathResponse.CardAppPathResultSet }
     *     
     */
    public void setCardAppPathResultSet(CardApplicationPathResponse.CardAppPathResultSet value) {
        this.cardAppPathResultSet = value;
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
     *       &lt;sequence maxOccurs="unbounded" minOccurs="0"&gt;
     *         &lt;element name="CardApplicationPathResult" type="{urn:iso:std:iso-iec:24727:tech:schema}CardApplicationPathType"/&gt;
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
        "cardApplicationPathResult"
    })
    public static class CardAppPathResultSet {

        @XmlElement(name = "CardApplicationPathResult")
        protected List<CardApplicationPathType> cardApplicationPathResult;

        /**
         * Gets the value of the cardApplicationPathResult property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the cardApplicationPathResult property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getCardApplicationPathResult().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link CardApplicationPathType }
         * 
         * 
         */
        public List<CardApplicationPathType> getCardApplicationPathResult() {
            if (cardApplicationPathResult == null) {
                cardApplicationPathResult = new ArrayList<CardApplicationPathType>();
            }
            return this.cardApplicationPathResult;
        }

    }

}
