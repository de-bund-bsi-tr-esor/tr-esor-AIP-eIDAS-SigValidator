
package oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import oasis.names.tc.dss._1_0.core.schema.AnyType;


/**
 * <p>Java-Klasse für EvidenceRecordValidityType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="EvidenceRecordValidityType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="FormatOK" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}VerificationResultType"/&gt;
 *         &lt;element name="Version" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="DigestAlgorithm" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}AlgorithmValidityType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="CryptoInfos" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Attribute" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}AttributeType" maxOccurs="unbounded"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="EncryptionInfo" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="EncryptionInfoType" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}AlgorithmValidityType"/&gt;
 *                   &lt;element name="EncryptionInfoValue" type="{urn:oasis:names:tc:dss:1.0:core:schema}AnyType"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="ArchiveTimeStampSequence"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence maxOccurs="unbounded" minOccurs="0"&gt;
 *                   &lt;element name="ArchiveTimeStampChain"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence maxOccurs="unbounded" minOccurs="0"&gt;
 *                             &lt;element name="ArchiveTimeStamp" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}ArchiveTimeStampValidityType"/&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="Id" type="{http://www.w3.org/2001/XMLSchema}ID" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EvidenceRecordValidityType", propOrder = {
    "formatOK",
    "version",
    "digestAlgorithm",
    "cryptoInfos",
    "encryptionInfo",
    "archiveTimeStampSequence"
})
public class EvidenceRecordValidityType {

    @XmlElement(name = "FormatOK", required = true)
    protected VerificationResultType formatOK;
    @XmlElement(name = "Version")
    protected BigInteger version;
    @XmlElement(name = "DigestAlgorithm")
    protected List<AlgorithmValidityType> digestAlgorithm;
    @XmlElement(name = "CryptoInfos")
    protected EvidenceRecordValidityType.CryptoInfos cryptoInfos;
    @XmlElement(name = "EncryptionInfo")
    protected EvidenceRecordValidityType.EncryptionInfo encryptionInfo;
    @XmlElement(name = "ArchiveTimeStampSequence", required = true)
    protected EvidenceRecordValidityType.ArchiveTimeStampSequence archiveTimeStampSequence;
    @XmlAttribute(name = "Id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Ruft den Wert der formatOK-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link VerificationResultType }
     *     
     */
    public VerificationResultType getFormatOK() {
        return formatOK;
    }

    /**
     * Legt den Wert der formatOK-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link VerificationResultType }
     *     
     */
    public void setFormatOK(VerificationResultType value) {
        this.formatOK = value;
    }

    /**
     * Ruft den Wert der version-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getVersion() {
        return version;
    }

    /**
     * Legt den Wert der version-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setVersion(BigInteger value) {
        this.version = value;
    }

    /**
     * Gets the value of the digestAlgorithm property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the digestAlgorithm property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDigestAlgorithm().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AlgorithmValidityType }
     * 
     * 
     */
    public List<AlgorithmValidityType> getDigestAlgorithm() {
        if (digestAlgorithm == null) {
            digestAlgorithm = new ArrayList<AlgorithmValidityType>();
        }
        return this.digestAlgorithm;
    }

    /**
     * Ruft den Wert der cryptoInfos-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link EvidenceRecordValidityType.CryptoInfos }
     *     
     */
    public EvidenceRecordValidityType.CryptoInfos getCryptoInfos() {
        return cryptoInfos;
    }

    /**
     * Legt den Wert der cryptoInfos-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link EvidenceRecordValidityType.CryptoInfos }
     *     
     */
    public void setCryptoInfos(EvidenceRecordValidityType.CryptoInfos value) {
        this.cryptoInfos = value;
    }

    /**
     * Ruft den Wert der encryptionInfo-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link EvidenceRecordValidityType.EncryptionInfo }
     *     
     */
    public EvidenceRecordValidityType.EncryptionInfo getEncryptionInfo() {
        return encryptionInfo;
    }

    /**
     * Legt den Wert der encryptionInfo-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link EvidenceRecordValidityType.EncryptionInfo }
     *     
     */
    public void setEncryptionInfo(EvidenceRecordValidityType.EncryptionInfo value) {
        this.encryptionInfo = value;
    }

    /**
     * Ruft den Wert der archiveTimeStampSequence-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link EvidenceRecordValidityType.ArchiveTimeStampSequence }
     *     
     */
    public EvidenceRecordValidityType.ArchiveTimeStampSequence getArchiveTimeStampSequence() {
        return archiveTimeStampSequence;
    }

    /**
     * Legt den Wert der archiveTimeStampSequence-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link EvidenceRecordValidityType.ArchiveTimeStampSequence }
     *     
     */
    public void setArchiveTimeStampSequence(EvidenceRecordValidityType.ArchiveTimeStampSequence value) {
        this.archiveTimeStampSequence = value;
    }

    /**
     * Ruft den Wert der id-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Legt den Wert der id-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
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
     *       &lt;sequence maxOccurs="unbounded" minOccurs="0"&gt;
     *         &lt;element name="ArchiveTimeStampChain"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence maxOccurs="unbounded" minOccurs="0"&gt;
     *                   &lt;element name="ArchiveTimeStamp" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}ArchiveTimeStampValidityType"/&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
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
        "archiveTimeStampChain"
    })
    public static class ArchiveTimeStampSequence {

        @XmlElement(name = "ArchiveTimeStampChain")
        protected List<EvidenceRecordValidityType.ArchiveTimeStampSequence.ArchiveTimeStampChain> archiveTimeStampChain;

        /**
         * Gets the value of the archiveTimeStampChain property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the archiveTimeStampChain property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getArchiveTimeStampChain().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link EvidenceRecordValidityType.ArchiveTimeStampSequence.ArchiveTimeStampChain }
         * 
         * 
         */
        public List<EvidenceRecordValidityType.ArchiveTimeStampSequence.ArchiveTimeStampChain> getArchiveTimeStampChain() {
            if (archiveTimeStampChain == null) {
                archiveTimeStampChain = new ArrayList<EvidenceRecordValidityType.ArchiveTimeStampSequence.ArchiveTimeStampChain>();
            }
            return this.archiveTimeStampChain;
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
         *       &lt;sequence maxOccurs="unbounded" minOccurs="0"&gt;
         *         &lt;element name="ArchiveTimeStamp" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}ArchiveTimeStampValidityType"/&gt;
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
            "archiveTimeStamp"
        })
        public static class ArchiveTimeStampChain {

            @XmlElement(name = "ArchiveTimeStamp")
            protected List<ArchiveTimeStampValidityType> archiveTimeStamp;

            /**
             * Gets the value of the archiveTimeStamp property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the archiveTimeStamp property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getArchiveTimeStamp().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link ArchiveTimeStampValidityType }
             * 
             * 
             */
            public List<ArchiveTimeStampValidityType> getArchiveTimeStamp() {
                if (archiveTimeStamp == null) {
                    archiveTimeStamp = new ArrayList<ArchiveTimeStampValidityType>();
                }
                return this.archiveTimeStamp;
            }

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
     *         &lt;element name="Attribute" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}AttributeType" maxOccurs="unbounded"/&gt;
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
        "attribute"
    })
    public static class CryptoInfos {

        @XmlElement(name = "Attribute", required = true)
        protected List<AttributeType> attribute;

        /**
         * Gets the value of the attribute property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the attribute property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAttribute().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link AttributeType }
         * 
         * 
         */
        public List<AttributeType> getAttribute() {
            if (attribute == null) {
                attribute = new ArrayList<AttributeType>();
            }
            return this.attribute;
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
     *         &lt;element name="EncryptionInfoType" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}AlgorithmValidityType"/&gt;
     *         &lt;element name="EncryptionInfoValue" type="{urn:oasis:names:tc:dss:1.0:core:schema}AnyType"/&gt;
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
        "encryptionInfoType",
        "encryptionInfoValue"
    })
    public static class EncryptionInfo {

        @XmlElement(name = "EncryptionInfoType", required = true)
        protected AlgorithmValidityType encryptionInfoType;
        @XmlElement(name = "EncryptionInfoValue", required = true)
        protected AnyType encryptionInfoValue;

        /**
         * Ruft den Wert der encryptionInfoType-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link AlgorithmValidityType }
         *     
         */
        public AlgorithmValidityType getEncryptionInfoType() {
            return encryptionInfoType;
        }

        /**
         * Legt den Wert der encryptionInfoType-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link AlgorithmValidityType }
         *     
         */
        public void setEncryptionInfoType(AlgorithmValidityType value) {
            this.encryptionInfoType = value;
        }

        /**
         * Ruft den Wert der encryptionInfoValue-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link AnyType }
         *     
         */
        public AnyType getEncryptionInfoValue() {
            return encryptionInfoValue;
        }

        /**
         * Legt den Wert der encryptionInfoValue-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link AnyType }
         *     
         */
        public void setEncryptionInfoValue(AnyType value) {
            this.encryptionInfoValue = value;
        }

    }

}
