
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
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
 *         &lt;element name="ConnectionHandle" type="{urn:iso:std:iso-iec:24727:tech:schema}ConnectionHandleType"/&gt;
 *         &lt;element name="DIDName" type="{urn:iso:std:iso-iec:24727:tech:schema}DIDNameType"/&gt;
 *         &lt;element name="DIDUpdateData" type="{urn:iso:std:iso-iec:24727:tech:schema}DIDUpdateDataType"/&gt;
 *         &lt;element name="DIDACL" type="{urn:iso:std:iso-iec:24727:tech:schema}AccessControlListType"/&gt;
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
    "connectionHandle",
    "didName",
    "didUpdateData",
    "didacl"
})
@XmlRootElement(name = "DIDCreate")
public class DIDCreate
    extends RequestType
{

    @XmlElement(name = "ConnectionHandle", required = true)
    protected ConnectionHandleType connectionHandle;
    @XmlElement(name = "DIDName", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String didName;
    @XmlElement(name = "DIDUpdateData", required = true)
    protected DIDUpdateDataType didUpdateData;
    @XmlElement(name = "DIDACL", required = true)
    protected AccessControlListType didacl;

    /**
     * Ruft den Wert der connectionHandle-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link ConnectionHandleType }
     *     
     */
    public ConnectionHandleType getConnectionHandle() {
        return connectionHandle;
    }

    /**
     * Legt den Wert der connectionHandle-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link ConnectionHandleType }
     *     
     */
    public void setConnectionHandle(ConnectionHandleType value) {
        this.connectionHandle = value;
    }

    /**
     * Ruft den Wert der didName-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDIDName() {
        return didName;
    }

    /**
     * Legt den Wert der didName-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDIDName(String value) {
        this.didName = value;
    }

    /**
     * Ruft den Wert der didUpdateData-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link DIDUpdateDataType }
     *     
     */
    public DIDUpdateDataType getDIDUpdateData() {
        return didUpdateData;
    }

    /**
     * Legt den Wert der didUpdateData-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link DIDUpdateDataType }
     *     
     */
    public void setDIDUpdateData(DIDUpdateDataType value) {
        this.didUpdateData = value;
    }

    /**
     * Ruft den Wert der didacl-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link AccessControlListType }
     *     
     */
    public AccessControlListType getDIDACL() {
        return didacl;
    }

    /**
     * Legt den Wert der didacl-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link AccessControlListType }
     *     
     */
    public void setDIDACL(AccessControlListType value) {
        this.didacl = value;
    }

}
