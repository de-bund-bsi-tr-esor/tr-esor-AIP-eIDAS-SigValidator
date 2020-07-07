
package oasis.names.tc.dss._1_0.profiles.ades.schema_;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.etsi.uri._01903.v1_3.DataObjectFormatType;


/**
 * <p>Java-Klasse für DocFormatType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="DocFormatType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:oasis:names:tc:dss:1.0:profiles:AdES:schema#}DocReferenceType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://uri.etsi.org/01903/v1.3.2#}DataObjectFormat"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DocFormatType", propOrder = {
    "dataObjectFormat"
})
public class DocFormatType
    extends DocReferenceType
{

    @XmlElement(name = "DataObjectFormat", namespace = "http://uri.etsi.org/01903/v1.3.2#", required = true)
    protected DataObjectFormatType dataObjectFormat;

    /**
     * Ruft den Wert der dataObjectFormat-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link DataObjectFormatType }
     *     
     */
    public DataObjectFormatType getDataObjectFormat() {
        return dataObjectFormat;
    }

    /**
     * Legt den Wert der dataObjectFormat-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link DataObjectFormatType }
     *     
     */
    public void setDataObjectFormat(DataObjectFormatType value) {
        this.dataObjectFormat = value;
    }

}
