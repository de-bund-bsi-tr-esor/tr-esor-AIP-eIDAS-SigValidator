
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für NamedDataServiceActionName.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="NamedDataServiceActionName"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="DataSetList"/&gt;
 *     &lt;enumeration value="DataSetCreate"/&gt;
 *     &lt;enumeration value="DataSetSelect"/&gt;
 *     &lt;enumeration value="DataSetDelete"/&gt;
 *     &lt;enumeration value="DSIList"/&gt;
 *     &lt;enumeration value="DSICreate"/&gt;
 *     &lt;enumeration value="DSIDelete"/&gt;
 *     &lt;enumeration value="DSIRead"/&gt;
 *     &lt;enumeration value="DSIWrite"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "NamedDataServiceActionName")
@XmlEnum
public enum NamedDataServiceActionName {

    @XmlEnumValue("DataSetList")
    DATA_SET_LIST("DataSetList"),
    @XmlEnumValue("DataSetCreate")
    DATA_SET_CREATE("DataSetCreate"),
    @XmlEnumValue("DataSetSelect")
    DATA_SET_SELECT("DataSetSelect"),
    @XmlEnumValue("DataSetDelete")
    DATA_SET_DELETE("DataSetDelete"),
    @XmlEnumValue("DSIList")
    DSI_LIST("DSIList"),
    @XmlEnumValue("DSICreate")
    DSI_CREATE("DSICreate"),
    @XmlEnumValue("DSIDelete")
    DSI_DELETE("DSIDelete"),
    @XmlEnumValue("DSIRead")
    DSI_READ("DSIRead"),
    @XmlEnumValue("DSIWrite")
    DSI_WRITE("DSIWrite");
    private final String value;

    NamedDataServiceActionName(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static NamedDataServiceActionName fromValue(String v) {
        for (NamedDataServiceActionName c: NamedDataServiceActionName.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
