
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für EACMarkerType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="EACMarkerType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{urn:iso:std:iso-iec:24727:tech:schema}DIDAbstractMarkerType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="PACEDID" type="{urn:iso:std:iso-iec:24727:tech:schema}DIDNameType" minOccurs="0"/&gt;
 *         &lt;element name="CADID" type="{urn:iso:std:iso-iec:24727:tech:schema}DIDNameType"/&gt;
 *         &lt;element name="TADID" type="{urn:iso:std:iso-iec:24727:tech:schema}DIDNameType"/&gt;
 *         &lt;element name="RIDID" type="{urn:iso:std:iso-iec:24727:tech:schema}DIDNameType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EACMarkerType")
public class EACMarkerType
    extends DIDAbstractMarkerType
{


}
