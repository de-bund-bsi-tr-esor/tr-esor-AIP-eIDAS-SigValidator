
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für ConnectionServiceActionName.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="ConnectionServiceActionName"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="CardApplicationConnect"/&gt;
 *     &lt;enumeration value="CardApplicationDisconnect"/&gt;
 *     &lt;enumeration value="CardApplicationStartSession"/&gt;
 *     &lt;enumeration value="CardApplicationEndSession"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "ConnectionServiceActionName")
@XmlEnum
public enum ConnectionServiceActionName {

    @XmlEnumValue("CardApplicationConnect")
    CARD_APPLICATION_CONNECT("CardApplicationConnect"),
    @XmlEnumValue("CardApplicationDisconnect")
    CARD_APPLICATION_DISCONNECT("CardApplicationDisconnect"),
    @XmlEnumValue("CardApplicationStartSession")
    CARD_APPLICATION_START_SESSION("CardApplicationStartSession"),
    @XmlEnumValue("CardApplicationEndSession")
    CARD_APPLICATION_END_SESSION("CardApplicationEndSession");
    private final String value;

    ConnectionServiceActionName(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ConnectionServiceActionName fromValue(String v) {
        for (ConnectionServiceActionName c: ConnectionServiceActionName.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
