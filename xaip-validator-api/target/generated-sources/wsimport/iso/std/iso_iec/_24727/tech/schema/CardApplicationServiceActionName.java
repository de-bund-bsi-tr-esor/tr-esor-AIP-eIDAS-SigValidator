
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für CardApplicationServiceActionName.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="CardApplicationServiceActionName"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="CardApplicationList"/&gt;
 *     &lt;enumeration value="CardApplicationCreate"/&gt;
 *     &lt;enumeration value="CardApplicationDelete"/&gt;
 *     &lt;enumeration value="CardApplicationServiceList"/&gt;
 *     &lt;enumeration value="CardApplicationServiceCreate"/&gt;
 *     &lt;enumeration value="CardApplicationServiceLoad"/&gt;
 *     &lt;enumeration value="CardApplicationServiceDelete"/&gt;
 *     &lt;enumeration value="CardApplicationServiceDescribe"/&gt;
 *     &lt;enumeration value="ExecuteAction"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "CardApplicationServiceActionName")
@XmlEnum
public enum CardApplicationServiceActionName {

    @XmlEnumValue("CardApplicationList")
    CARD_APPLICATION_LIST("CardApplicationList"),
    @XmlEnumValue("CardApplicationCreate")
    CARD_APPLICATION_CREATE("CardApplicationCreate"),
    @XmlEnumValue("CardApplicationDelete")
    CARD_APPLICATION_DELETE("CardApplicationDelete"),
    @XmlEnumValue("CardApplicationServiceList")
    CARD_APPLICATION_SERVICE_LIST("CardApplicationServiceList"),
    @XmlEnumValue("CardApplicationServiceCreate")
    CARD_APPLICATION_SERVICE_CREATE("CardApplicationServiceCreate"),
    @XmlEnumValue("CardApplicationServiceLoad")
    CARD_APPLICATION_SERVICE_LOAD("CardApplicationServiceLoad"),
    @XmlEnumValue("CardApplicationServiceDelete")
    CARD_APPLICATION_SERVICE_DELETE("CardApplicationServiceDelete"),
    @XmlEnumValue("CardApplicationServiceDescribe")
    CARD_APPLICATION_SERVICE_DESCRIBE("CardApplicationServiceDescribe"),
    @XmlEnumValue("ExecuteAction")
    EXECUTE_ACTION("ExecuteAction");
    private final String value;

    CardApplicationServiceActionName(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CardApplicationServiceActionName fromValue(String v) {
        for (CardApplicationServiceActionName c: CardApplicationServiceActionName.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
