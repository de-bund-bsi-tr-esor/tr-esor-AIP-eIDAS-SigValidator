
package oasis.names.tc.dss._1_0.core.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java-Klasse für SigningTimeInfoType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="SigningTimeInfoType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SigningTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="SigningTimeBoundaries" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="LowerBoundary" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *                   &lt;element name="UpperBoundary" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
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
@XmlType(name = "SigningTimeInfoType", propOrder = {
    "signingTime",
    "signingTimeBoundaries"
})
public class SigningTimeInfoType {

    @XmlElement(name = "SigningTime", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar signingTime;
    @XmlElement(name = "SigningTimeBoundaries")
    protected SigningTimeInfoType.SigningTimeBoundaries signingTimeBoundaries;

    /**
     * Ruft den Wert der signingTime-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSigningTime() {
        return signingTime;
    }

    /**
     * Legt den Wert der signingTime-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSigningTime(XMLGregorianCalendar value) {
        this.signingTime = value;
    }

    /**
     * Ruft den Wert der signingTimeBoundaries-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link SigningTimeInfoType.SigningTimeBoundaries }
     *     
     */
    public SigningTimeInfoType.SigningTimeBoundaries getSigningTimeBoundaries() {
        return signingTimeBoundaries;
    }

    /**
     * Legt den Wert der signingTimeBoundaries-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link SigningTimeInfoType.SigningTimeBoundaries }
     *     
     */
    public void setSigningTimeBoundaries(SigningTimeInfoType.SigningTimeBoundaries value) {
        this.signingTimeBoundaries = value;
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
     *         &lt;element name="LowerBoundary" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
     *         &lt;element name="UpperBoundary" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
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
        "lowerBoundary",
        "upperBoundary"
    })
    public static class SigningTimeBoundaries {

        @XmlElement(name = "LowerBoundary")
        @XmlSchemaType(name = "dateTime")
        protected XMLGregorianCalendar lowerBoundary;
        @XmlElement(name = "UpperBoundary")
        @XmlSchemaType(name = "dateTime")
        protected XMLGregorianCalendar upperBoundary;

        /**
         * Ruft den Wert der lowerBoundary-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getLowerBoundary() {
            return lowerBoundary;
        }

        /**
         * Legt den Wert der lowerBoundary-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setLowerBoundary(XMLGregorianCalendar value) {
            this.lowerBoundary = value;
        }

        /**
         * Ruft den Wert der upperBoundary-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getUpperBoundary() {
            return upperBoundary;
        }

        /**
         * Legt den Wert der upperBoundary-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setUpperBoundary(XMLGregorianCalendar value) {
            this.upperBoundary = value;
        }

    }

}
