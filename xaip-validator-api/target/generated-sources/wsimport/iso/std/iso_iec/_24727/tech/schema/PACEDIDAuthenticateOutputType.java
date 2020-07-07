
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für PACEDIDAuthenticateOutputType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="PACEDIDAuthenticateOutputType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{urn:iso:std:iso-iec:24727:tech:schema}DIDAuthenticationDataType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="RetryCounter" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" minOccurs="0"/&gt;
 *         &lt;element name="CertificateHolderAuthorizationTemplate" type="{http://www.w3.org/2001/XMLSchema}hexBinary" minOccurs="0"/&gt;
 *         &lt;element name="CertificationAuthorityReference" type="{urn:iso:std:iso-iec:24727:tech:schema}DIDNameType" maxOccurs="2" minOccurs="0"/&gt;
 *         &lt;element name="EFCardAccess" type="{http://www.w3.org/2001/XMLSchema}hexBinary" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PACEDIDAuthenticateOutputType")
public class PACEDIDAuthenticateOutputType
    extends DIDAuthenticationDataType
{


}
