
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für CryptoMarkerType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="CryptoMarkerType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{urn:iso:std:iso-iec:24727:tech:schema}DIDAbstractMarkerType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="AlgorithmInfo" type="{urn:iso:std:iso-iec:24727:tech:schema}AlgorithmInfoType"/&gt;
 *         &lt;element name="KeyInfo" type="{urn:iso:std:iso-iec:24727:tech:schema}CryptoKeyInfoType" minOccurs="0"/&gt;
 *         &lt;element name="SignatureGenerationInfo" type="{urn:iso:std:iso-iec:24727:tech:schema}SignatureGenerationType" minOccurs="0"/&gt;
 *         &lt;element name="HashGenerationInfo" type="{urn:iso:std:iso-iec:24727:tech:schema}HashGenerationInfoType" minOccurs="0"/&gt;
 *         &lt;element name="CertificateRef" type="{urn:iso:std:iso-iec:24727:tech:schema}CertificateRefType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="LegacyKeyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element ref="{urn:iso:std:iso-iec:24727:tech:schema}StateInfo" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CryptoMarkerType")
public class CryptoMarkerType
    extends DIDAbstractMarkerType
{


}
