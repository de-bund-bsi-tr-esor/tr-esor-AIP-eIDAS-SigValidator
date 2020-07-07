
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für CADIDCreateDataType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="CADIDCreateDataType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{urn:iso:std:iso-iec:24727:tech:schema}DIDUpdateDataType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="KeyInfo" type="{urn:iso:std:iso-iec:24727:tech:schema}CAKeyInfoType"/&gt;
 *         &lt;element name="Marker" type="{urn:iso:std:iso-iec:24727:tech:schema}CAMarkerType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CADIDCreateDataType")
public class CADIDCreateDataType
    extends DIDUpdateDataType
{


}
