
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java-Klasse für PathType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="PathType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;choice&gt;
 *           &lt;element name="efIdOrPath" type="{http://www.w3.org/2001/XMLSchema}hexBinary"/&gt;
 *           &lt;element name="TagRef"&gt;
 *             &lt;complexType&gt;
 *               &lt;complexContent&gt;
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                   &lt;sequence&gt;
 *                     &lt;element name="tag" type="{http://www.w3.org/2001/XMLSchema}hexBinary"/&gt;
 *                     &lt;element name="efIdOrPath" type="{http://www.w3.org/2001/XMLSchema}hexBinary" minOccurs="0"/&gt;
 *                   &lt;/sequence&gt;
 *                 &lt;/restriction&gt;
 *               &lt;/complexContent&gt;
 *             &lt;/complexType&gt;
 *           &lt;/element&gt;
 *           &lt;element name="AppFileRef"&gt;
 *             &lt;complexType&gt;
 *               &lt;complexContent&gt;
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                   &lt;sequence&gt;
 *                     &lt;element name="aid" type="{http://www.w3.org/2001/XMLSchema}hexBinary"/&gt;
 *                     &lt;element name="efIDOrPath" type="{http://www.w3.org/2001/XMLSchema}hexBinary"/&gt;
 *                   &lt;/sequence&gt;
 *                 &lt;/restriction&gt;
 *               &lt;/complexContent&gt;
 *             &lt;/complexType&gt;
 *           &lt;/element&gt;
 *           &lt;element name="AppTagRef"&gt;
 *             &lt;complexType&gt;
 *               &lt;complexContent&gt;
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                   &lt;sequence&gt;
 *                     &lt;element name="aid" type="{http://www.w3.org/2001/XMLSchema}hexBinary"/&gt;
 *                     &lt;element name="tag" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                     &lt;element name="efIdOrPath" type="{http://www.w3.org/2001/XMLSchema}hexBinary" minOccurs="0"/&gt;
 *                   &lt;/sequence&gt;
 *                 &lt;/restriction&gt;
 *               &lt;/complexContent&gt;
 *             &lt;/complexType&gt;
 *           &lt;/element&gt;
 *         &lt;/choice&gt;
 *         &lt;element name="Index" type="{http://www.w3.org/2001/XMLSchema}hexBinary" minOccurs="0"/&gt;
 *         &lt;element name="Length" type="{http://www.w3.org/2001/XMLSchema}hexBinary" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PathType", propOrder = {
    "efIdOrPath",
    "tagRef",
    "appFileRef",
    "appTagRef",
    "index",
    "length"
})
public class PathType {

    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] efIdOrPath;
    @XmlElement(name = "TagRef")
    protected PathType.TagRef tagRef;
    @XmlElement(name = "AppFileRef")
    protected PathType.AppFileRef appFileRef;
    @XmlElement(name = "AppTagRef")
    protected PathType.AppTagRef appTagRef;
    @XmlElement(name = "Index", type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] index;
    @XmlElement(name = "Length", type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] length;

    /**
     * Ruft den Wert der efIdOrPath-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getEfIdOrPath() {
        return efIdOrPath;
    }

    /**
     * Legt den Wert der efIdOrPath-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEfIdOrPath(byte[] value) {
        this.efIdOrPath = value;
    }

    /**
     * Ruft den Wert der tagRef-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PathType.TagRef }
     *     
     */
    public PathType.TagRef getTagRef() {
        return tagRef;
    }

    /**
     * Legt den Wert der tagRef-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PathType.TagRef }
     *     
     */
    public void setTagRef(PathType.TagRef value) {
        this.tagRef = value;
    }

    /**
     * Ruft den Wert der appFileRef-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PathType.AppFileRef }
     *     
     */
    public PathType.AppFileRef getAppFileRef() {
        return appFileRef;
    }

    /**
     * Legt den Wert der appFileRef-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PathType.AppFileRef }
     *     
     */
    public void setAppFileRef(PathType.AppFileRef value) {
        this.appFileRef = value;
    }

    /**
     * Ruft den Wert der appTagRef-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PathType.AppTagRef }
     *     
     */
    public PathType.AppTagRef getAppTagRef() {
        return appTagRef;
    }

    /**
     * Legt den Wert der appTagRef-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PathType.AppTagRef }
     *     
     */
    public void setAppTagRef(PathType.AppTagRef value) {
        this.appTagRef = value;
    }

    /**
     * Ruft den Wert der index-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getIndex() {
        return index;
    }

    /**
     * Legt den Wert der index-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndex(byte[] value) {
        this.index = value;
    }

    /**
     * Ruft den Wert der length-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getLength() {
        return length;
    }

    /**
     * Legt den Wert der length-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLength(byte[] value) {
        this.length = value;
    }


    /**
     * <p>Java-Klasse für anonymous complex type.
     * 
     * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="aid" type="{http://www.w3.org/2001/XMLSchema}hexBinary"/&gt;
     *         &lt;element name="efIDOrPath" type="{http://www.w3.org/2001/XMLSchema}hexBinary"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "aid",
        "efIDOrPath"
    })
    public static class AppFileRef {

        @XmlElement(required = true, type = String.class)
        @XmlJavaTypeAdapter(HexBinaryAdapter.class)
        @XmlSchemaType(name = "hexBinary")
        protected byte[] aid;
        @XmlElement(required = true, type = String.class)
        @XmlJavaTypeAdapter(HexBinaryAdapter.class)
        @XmlSchemaType(name = "hexBinary")
        protected byte[] efIDOrPath;

        /**
         * Ruft den Wert der aid-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public byte[] getAid() {
            return aid;
        }

        /**
         * Legt den Wert der aid-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAid(byte[] value) {
            this.aid = value;
        }

        /**
         * Ruft den Wert der efIDOrPath-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public byte[] getEfIDOrPath() {
            return efIDOrPath;
        }

        /**
         * Legt den Wert der efIDOrPath-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setEfIDOrPath(byte[] value) {
            this.efIDOrPath = value;
        }

    }


    /**
     * <p>Java-Klasse für anonymous complex type.
     * 
     * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="aid" type="{http://www.w3.org/2001/XMLSchema}hexBinary"/&gt;
     *         &lt;element name="tag" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="efIdOrPath" type="{http://www.w3.org/2001/XMLSchema}hexBinary" minOccurs="0"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "aid",
        "tag",
        "efIdOrPath"
    })
    public static class AppTagRef {

        @XmlElement(required = true, type = String.class)
        @XmlJavaTypeAdapter(HexBinaryAdapter.class)
        @XmlSchemaType(name = "hexBinary")
        protected byte[] aid;
        @XmlElement(required = true)
        protected String tag;
        @XmlElement(type = String.class)
        @XmlJavaTypeAdapter(HexBinaryAdapter.class)
        @XmlSchemaType(name = "hexBinary")
        protected byte[] efIdOrPath;

        /**
         * Ruft den Wert der aid-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public byte[] getAid() {
            return aid;
        }

        /**
         * Legt den Wert der aid-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAid(byte[] value) {
            this.aid = value;
        }

        /**
         * Ruft den Wert der tag-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTag() {
            return tag;
        }

        /**
         * Legt den Wert der tag-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTag(String value) {
            this.tag = value;
        }

        /**
         * Ruft den Wert der efIdOrPath-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public byte[] getEfIdOrPath() {
            return efIdOrPath;
        }

        /**
         * Legt den Wert der efIdOrPath-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setEfIdOrPath(byte[] value) {
            this.efIdOrPath = value;
        }

    }


    /**
     * <p>Java-Klasse für anonymous complex type.
     * 
     * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="tag" type="{http://www.w3.org/2001/XMLSchema}hexBinary"/&gt;
     *         &lt;element name="efIdOrPath" type="{http://www.w3.org/2001/XMLSchema}hexBinary" minOccurs="0"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "tag",
        "efIdOrPath"
    })
    public static class TagRef {

        @XmlElement(required = true, type = String.class)
        @XmlJavaTypeAdapter(HexBinaryAdapter.class)
        @XmlSchemaType(name = "hexBinary")
        protected byte[] tag;
        @XmlElement(type = String.class)
        @XmlJavaTypeAdapter(HexBinaryAdapter.class)
        @XmlSchemaType(name = "hexBinary")
        protected byte[] efIdOrPath;

        /**
         * Ruft den Wert der tag-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public byte[] getTag() {
            return tag;
        }

        /**
         * Legt den Wert der tag-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTag(byte[] value) {
            this.tag = value;
        }

        /**
         * Ruft den Wert der efIdOrPath-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public byte[] getEfIdOrPath() {
            return efIdOrPath;
        }

        /**
         * Legt den Wert der efIdOrPath-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setEfIdOrPath(byte[] value) {
            this.efIdOrPath = value;
        }

    }

}
