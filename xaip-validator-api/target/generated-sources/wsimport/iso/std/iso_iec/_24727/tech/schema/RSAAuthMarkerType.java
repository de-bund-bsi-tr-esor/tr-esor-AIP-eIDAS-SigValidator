
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für RSAAuthMarkerType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="RSAAuthMarkerType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{urn:iso:std:iso-iec:24727:tech:schema}DIDAbstractMarkerType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="EncryptionAlgorithm" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/&gt;
 *         &lt;element name="SignatureAlgorithm" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/&gt;
 *         &lt;element name="DerivationAlgorithmSessionKeysAndCounter" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/&gt;
 *         &lt;element name="MacAlgorithmForSessionKey" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/&gt;
 *         &lt;element name="EncryptionAlgorithmForSessionKey" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/&gt;
 *         &lt;element name="CardAlgId" type="{urn:iso:std:iso-iec:24727:tech:schema}ByteType" minOccurs="0"/&gt;
 *         &lt;element name="KeySize" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/&gt;
 *         &lt;choice minOccurs="0"&gt;
 *           &lt;sequence&gt;
 *             &lt;element name="PrivateKeyValue" type="{urn:iso:std:iso-iec:24727:tech:schema}KeyValueType" minOccurs="0"/&gt;
 *             &lt;element name="PublicKeyValue" type="{urn:iso:std:iso-iec:24727:tech:schema}KeyValueType"/&gt;
 *           &lt;/sequence&gt;
 *           &lt;element name="generateFlag" type="{urn:iso:std:iso-iec:24727:tech:schema}NULL"/&gt;
 *         &lt;/choice&gt;
 *         &lt;element name="NonceSize" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/&gt;
 *         &lt;element name="KeyRef" type="{urn:iso:std:iso-iec:24727:tech:schema}KeyRefType"/&gt;
 *         &lt;element name="RootRef" type="{urn:iso:std:iso-iec:24727:tech:schema}KeyRefType" minOccurs="0"/&gt;
 *         &lt;element name="SecurityEnvironmentIdentifier" type="{urn:iso:std:iso-iec:24727:tech:schema}ByteType" minOccurs="0"/&gt;
 *         &lt;element name="CertificateRef" type="{urn:iso:std:iso-iec:24727:tech:schema}CertificateRefType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="ICCSNRef" type="{urn:iso:std:iso-iec:24727:tech:schema}DataRefType" minOccurs="0"/&gt;
 *         &lt;element name="LegacyKeyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RSAAuthMarkerType")
public class RSAAuthMarkerType
    extends DIDAbstractMarkerType
{


}
