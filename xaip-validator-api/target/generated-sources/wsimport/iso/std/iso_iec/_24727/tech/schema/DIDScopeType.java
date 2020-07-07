
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für DIDScopeType.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="DIDScopeType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="local"/&gt;
 *     &lt;enumeration value="global"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "DIDScopeType")
@XmlEnum
public enum DIDScopeType {

    @XmlEnumValue("local")
    LOCAL("local"),
    @XmlEnumValue("global")
    GLOBAL("global");
    private final String value;

    DIDScopeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DIDScopeType fromValue(String v) {
        for (DIDScopeType c: DIDScopeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
