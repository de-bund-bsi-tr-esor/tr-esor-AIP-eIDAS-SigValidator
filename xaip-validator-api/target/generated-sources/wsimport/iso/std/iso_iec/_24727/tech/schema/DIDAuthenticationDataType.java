
package iso.std.iso_iec._24727.tech.schema;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;
import org.w3c.dom.Element;


/**
 * <p>Java-Klasse für DIDAuthenticationDataType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="DIDAuthenticationDataType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;attribute name="Protocol" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" /&gt;
 *       &lt;anyAttribute processContents='skip'/&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DIDAuthenticationDataType", propOrder = {
    "any"
})
@XmlSeeAlso({
    StartSessionInputType.class,
    StartSessionOutputType.class,
    PinCompareDIDAuthenticateInputType.class,
    PinCompareDIDAuthenticateOutputType.class,
    MutualAuthDIDAuthInternalAuthType.class,
    MutualAuthDIDAuthMutualAuthType.class,
    MutualAuthDIDAuthExternalAuthType.class,
    EACSessionInputType.class,
    EACSessionOutputType.class,
    EAC1InputType.class,
    EAC1OutputType.class,
    EAC2InputType.class,
    EAC2OutputType.class,
    EACAdditionalInputType.class,
    PACEDIDAuthenticateInputType.class,
    PACEDIDAuthenticateOutputType.class,
    CAInputType.class,
    CAAuthenticationTokenType.class,
    TADIDAuthInputType.class,
    TADIDAuthOutputType.class,
    TADIDAuthExternalAuthType.class,
    TAAuxInputType.class,
    RIDIDAuthInputType.class,
    RIDIDAuthOutputType.class,
    RSAAuthDIDAuthVerifyCertsType.class,
    RSAAuthDIDAuthInternalAuthType.class,
    RSAAuthDIDAuthMutualAuthType.class,
    RSAAuthDIDAuthExternalAuthType.class,
    EmptyResponseDataType.class,
    HashInputType.class,
    HashOutputType.class
})
public class DIDAuthenticationDataType {

    @XmlAnyElement
    protected List<Element> any;
    @XmlAttribute(name = "Protocol", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String protocol;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the any property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the any property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAny().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Element }
     * 
     * 
     */
    public List<Element> getAny() {
        if (any == null) {
            any = new ArrayList<Element>();
        }
        return this.any;
    }

    /**
     * Ruft den Wert der protocol-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProtocol() {
        return protocol;
    }

    /**
     * Legt den Wert der protocol-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProtocol(String value) {
        this.protocol = value;
    }

    /**
     * Gets a map that contains attributes that aren't bound to any typed property on this class.
     * 
     * <p>
     * the map is keyed by the name of the attribute and 
     * the value is the string value of the attribute.
     * 
     * the map returned by this method is live, and you can add new attribute
     * by updating the map directly. Because of this design, there's no setter.
     * 
     * 
     * @return
     *     always non-null
     */
    public Map<QName, String> getOtherAttributes() {
        return otherAttributes;
    }

}
