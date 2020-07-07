
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für DifferentialIdentityServiceActionName.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="DifferentialIdentityServiceActionName"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="DIDList"/&gt;
 *     &lt;enumeration value="DIDCreate"/&gt;
 *     &lt;enumeration value="DIDGet"/&gt;
 *     &lt;enumeration value="DIDUpdate"/&gt;
 *     &lt;enumeration value="DIDDelete"/&gt;
 *     &lt;enumeration value="DIDAuthenticate"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "DifferentialIdentityServiceActionName")
@XmlEnum
public enum DifferentialIdentityServiceActionName {

    @XmlEnumValue("DIDList")
    DID_LIST("DIDList"),
    @XmlEnumValue("DIDCreate")
    DID_CREATE("DIDCreate"),
    @XmlEnumValue("DIDGet")
    DID_GET("DIDGet"),
    @XmlEnumValue("DIDUpdate")
    DID_UPDATE("DIDUpdate"),
    @XmlEnumValue("DIDDelete")
    DID_DELETE("DIDDelete"),
    @XmlEnumValue("DIDAuthenticate")
    DID_AUTHENTICATE("DIDAuthenticate");
    private final String value;

    DifferentialIdentityServiceActionName(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DifferentialIdentityServiceActionName fromValue(String v) {
        for (DifferentialIdentityServiceActionName c: DifferentialIdentityServiceActionName.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
