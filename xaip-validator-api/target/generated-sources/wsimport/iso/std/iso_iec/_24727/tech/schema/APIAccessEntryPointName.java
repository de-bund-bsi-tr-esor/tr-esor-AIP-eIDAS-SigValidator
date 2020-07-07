
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für APIAccessEntryPointName.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="APIAccessEntryPointName"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Initialize"/&gt;
 *     &lt;enumeration value="Terminate"/&gt;
 *     &lt;enumeration value="CardApplicationPath"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "APIAccessEntryPointName")
@XmlEnum
public enum APIAccessEntryPointName {

    @XmlEnumValue("Initialize")
    INITIALIZE("Initialize"),
    @XmlEnumValue("Terminate")
    TERMINATE("Terminate"),
    @XmlEnumValue("CardApplicationPath")
    CARD_APPLICATION_PATH("CardApplicationPath");
    private final String value;

    APIAccessEntryPointName(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static APIAccessEntryPointName fromValue(String v) {
        for (APIAccessEntryPointName c: APIAccessEntryPointName.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
