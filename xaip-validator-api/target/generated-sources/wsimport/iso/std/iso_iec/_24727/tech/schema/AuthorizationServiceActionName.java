
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für AuthorizationServiceActionName.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="AuthorizationServiceActionName"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="ACLList"/&gt;
 *     &lt;enumeration value="ACLModify"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "AuthorizationServiceActionName")
@XmlEnum
public enum AuthorizationServiceActionName {

    @XmlEnumValue("ACLList")
    ACL_LIST("ACLList"),
    @XmlEnumValue("ACLModify")
    ACL_MODIFY("ACLModify");
    private final String value;

    AuthorizationServiceActionName(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AuthorizationServiceActionName fromValue(String v) {
        for (AuthorizationServiceActionName c: AuthorizationServiceActionName.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
