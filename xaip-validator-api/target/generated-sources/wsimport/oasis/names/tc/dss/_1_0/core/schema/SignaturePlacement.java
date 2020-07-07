
package oasis.names.tc.dss._1_0.core.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für anonymous complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice&gt;
 *         &lt;element name="XPathAfter" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="XPathFirstChildOf" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/choice&gt;
 *       &lt;attribute name="WhichDocument" type="{http://www.w3.org/2001/XMLSchema}IDREF" /&gt;
 *       &lt;attribute name="CreateEnvelopedSignature" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "xPathAfter",
    "xPathFirstChildOf"
})
@XmlRootElement(name = "SignaturePlacement")
public class SignaturePlacement {

    @XmlElement(name = "XPathAfter")
    protected String xPathAfter;
    @XmlElement(name = "XPathFirstChildOf")
    protected String xPathFirstChildOf;
    @XmlAttribute(name = "WhichDocument")
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Object whichDocument;
    @XmlAttribute(name = "CreateEnvelopedSignature")
    protected Boolean createEnvelopedSignature;

    /**
     * Ruft den Wert der xPathAfter-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXPathAfter() {
        return xPathAfter;
    }

    /**
     * Legt den Wert der xPathAfter-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXPathAfter(String value) {
        this.xPathAfter = value;
    }

    /**
     * Ruft den Wert der xPathFirstChildOf-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXPathFirstChildOf() {
        return xPathFirstChildOf;
    }

    /**
     * Legt den Wert der xPathFirstChildOf-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXPathFirstChildOf(String value) {
        this.xPathFirstChildOf = value;
    }

    /**
     * Ruft den Wert der whichDocument-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getWhichDocument() {
        return whichDocument;
    }

    /**
     * Legt den Wert der whichDocument-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setWhichDocument(Object value) {
        this.whichDocument = value;
    }

    /**
     * Ruft den Wert der createEnvelopedSignature-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isCreateEnvelopedSignature() {
        if (createEnvelopedSignature == null) {
            return true;
        } else {
            return createEnvelopedSignature;
        }
    }

    /**
     * Legt den Wert der createEnvelopedSignature-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCreateEnvelopedSignature(Boolean value) {
        this.createEnvelopedSignature = value;
    }

}
