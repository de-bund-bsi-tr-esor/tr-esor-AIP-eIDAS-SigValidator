
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java-Klasse für anonymous complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:iso:std:iso-iec:24727:tech:schema}RequestType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ContextHandle" type="{urn:iso:std:iso-iec:24727:tech:schema}ContextHandleType"/&gt;
 *         &lt;element name="IFDName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="SessionIdentifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
    "contextHandle",
    "ifdName",
    "sessionIdentifier"
})
@XmlRootElement(name = "Cancel")
public class Cancel
    extends RequestType
{

    @XmlElement(name = "ContextHandle", required = true, type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] contextHandle;
    @XmlElement(name = "IFDName", required = true)
    protected String ifdName;
    @XmlElement(name = "SessionIdentifier")
    protected String sessionIdentifier;

    /**
     * Ruft den Wert der contextHandle-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getContextHandle() {
        return contextHandle;
    }

    /**
     * Legt den Wert der contextHandle-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContextHandle(byte[] value) {
        this.contextHandle = value;
    }

    /**
     * Ruft den Wert der ifdName-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIFDName() {
        return ifdName;
    }

    /**
     * Legt den Wert der ifdName-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIFDName(String value) {
        this.ifdName = value;
    }

    /**
     * Ruft den Wert der sessionIdentifier-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSessionIdentifier() {
        return sessionIdentifier;
    }

    /**
     * Legt den Wert der sessionIdentifier-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSessionIdentifier(String value) {
        this.sessionIdentifier = value;
    }

}
