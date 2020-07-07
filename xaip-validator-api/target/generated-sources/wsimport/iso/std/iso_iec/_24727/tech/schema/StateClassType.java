
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für StateClassType.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="StateClassType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Operational"/&gt;
 *     &lt;enumeration value="NotOperational"/&gt;
 *     &lt;enumeration value="RecognitionNecessary"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "StateClassType")
@XmlEnum
public enum StateClassType {

    @XmlEnumValue("Operational")
    OPERATIONAL("Operational"),
    @XmlEnumValue("NotOperational")
    NOT_OPERATIONAL("NotOperational"),
    @XmlEnumValue("RecognitionNecessary")
    RECOGNITION_NECESSARY("RecognitionNecessary");
    private final String value;

    StateClassType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static StateClassType fromValue(String v) {
        for (StateClassType c: StateClassType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
