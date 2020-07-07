
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für CryptographicServiceActionName.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="CryptographicServiceActionName"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Encipher"/&gt;
 *     &lt;enumeration value="Decipher"/&gt;
 *     &lt;enumeration value="GetRandom"/&gt;
 *     &lt;enumeration value="Hash"/&gt;
 *     &lt;enumeration value="Sign"/&gt;
 *     &lt;enumeration value="VerifySignature"/&gt;
 *     &lt;enumeration value="VerifyCertificate"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "CryptographicServiceActionName")
@XmlEnum
public enum CryptographicServiceActionName {

    @XmlEnumValue("Encipher")
    ENCIPHER("Encipher"),
    @XmlEnumValue("Decipher")
    DECIPHER("Decipher"),
    @XmlEnumValue("GetRandom")
    GET_RANDOM("GetRandom"),
    @XmlEnumValue("Hash")
    HASH("Hash"),
    @XmlEnumValue("Sign")
    SIGN("Sign"),
    @XmlEnumValue("VerifySignature")
    VERIFY_SIGNATURE("VerifySignature"),
    @XmlEnumValue("VerifyCertificate")
    VERIFY_CERTIFICATE("VerifyCertificate");
    private final String value;

    CryptographicServiceActionName(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CryptographicServiceActionName fromValue(String v) {
        for (CryptographicServiceActionName c: CryptographicServiceActionName.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
