
package org.setcce.schemas.ers;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.setcce.schemas.ers package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _EvidenceRecord_QNAME = new QName("http://www.setcce.org/schemas/ers", "EvidenceRecord");
    private final static QName _HashTreeTypeSequence_QNAME = new QName("http://www.setcce.org/schemas/ers", "Sequence");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.setcce.schemas.ers
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Attribute }
     * 
     */
    public Attribute createAttribute() {
        return new Attribute();
    }

    /**
     * Create an instance of {@link HashTreeType }
     * 
     */
    public HashTreeType createHashTreeType() {
        return new HashTreeType();
    }

    /**
     * Create an instance of {@link EvidenceRecordType }
     * 
     */
    public EvidenceRecordType createEvidenceRecordType() {
        return new EvidenceRecordType();
    }

    /**
     * Create an instance of {@link EvidenceRecordType.ArchiveTimeStampSequence }
     * 
     */
    public EvidenceRecordType.ArchiveTimeStampSequence createEvidenceRecordTypeArchiveTimeStampSequence() {
        return new EvidenceRecordType.ArchiveTimeStampSequence();
    }

    /**
     * Create an instance of {@link EvidenceRecordType.ArchiveTimeStampSequence.ArchiveTimeStampChain }
     * 
     */
    public EvidenceRecordType.ArchiveTimeStampSequence.ArchiveTimeStampChain createEvidenceRecordTypeArchiveTimeStampSequenceArchiveTimeStampChain() {
        return new EvidenceRecordType.ArchiveTimeStampSequence.ArchiveTimeStampChain();
    }

    /**
     * Create an instance of {@link EvidenceRecordType.ArchiveTimeStampSequence.ArchiveTimeStampChain.ArchiveTimeStamp }
     * 
     */
    public EvidenceRecordType.ArchiveTimeStampSequence.ArchiveTimeStampChain.ArchiveTimeStamp createEvidenceRecordTypeArchiveTimeStampSequenceArchiveTimeStampChainArchiveTimeStamp() {
        return new EvidenceRecordType.ArchiveTimeStampSequence.ArchiveTimeStampChain.ArchiveTimeStamp();
    }

    /**
     * Create an instance of {@link EncryptionInfo }
     * 
     */
    public EncryptionInfo createEncryptionInfo() {
        return new EncryptionInfo();
    }

    /**
     * Create an instance of {@link DigestMethodType }
     * 
     */
    public DigestMethodType createDigestMethodType() {
        return new DigestMethodType();
    }

    /**
     * Create an instance of {@link CanonicalizationMethodType }
     * 
     */
    public CanonicalizationMethodType createCanonicalizationMethodType() {
        return new CanonicalizationMethodType();
    }

    /**
     * Create an instance of {@link Attributes }
     * 
     */
    public Attributes createAttributes() {
        return new Attributes();
    }

    /**
     * Create an instance of {@link AttributeValue }
     * 
     */
    public AttributeValue createAttributeValue() {
        return new AttributeValue();
    }

    /**
     * Create an instance of {@link OpenType }
     * 
     */
    public OpenType createOpenType() {
        return new OpenType();
    }

    /**
     * Create an instance of {@link Attribute.AttrValues }
     * 
     */
    public Attribute.AttrValues createAttributeAttrValues() {
        return new Attribute.AttrValues();
    }

    /**
     * Create an instance of {@link HashTreeType.Sequence }
     * 
     */
    public HashTreeType.Sequence createHashTreeTypeSequence() {
        return new HashTreeType.Sequence();
    }

    /**
     * Create an instance of {@link EvidenceRecordType.ArchiveTimeStampSequence.ArchiveTimeStampChain.ArchiveTimeStamp.TimeStamp }
     * 
     */
    public EvidenceRecordType.ArchiveTimeStampSequence.ArchiveTimeStampChain.ArchiveTimeStamp.TimeStamp createEvidenceRecordTypeArchiveTimeStampSequenceArchiveTimeStampChainArchiveTimeStampTimeStamp() {
        return new EvidenceRecordType.ArchiveTimeStampSequence.ArchiveTimeStampChain.ArchiveTimeStamp.TimeStamp();
    }

    /**
     * Create an instance of {@link EvidenceRecordType.ArchiveTimeStampSequence.ArchiveTimeStampChain.ArchiveTimeStamp.CryptographicInformation }
     * 
     */
    public EvidenceRecordType.ArchiveTimeStampSequence.ArchiveTimeStampChain.ArchiveTimeStamp.CryptographicInformation createEvidenceRecordTypeArchiveTimeStampSequenceArchiveTimeStampChainArchiveTimeStampCryptographicInformation() {
        return new EvidenceRecordType.ArchiveTimeStampSequence.ArchiveTimeStampChain.ArchiveTimeStamp.CryptographicInformation();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EvidenceRecordType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link EvidenceRecordType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.setcce.org/schemas/ers", name = "EvidenceRecord")
    public JAXBElement<EvidenceRecordType> createEvidenceRecord(EvidenceRecordType value) {
        return new JAXBElement<EvidenceRecordType>(_EvidenceRecord_QNAME, EvidenceRecordType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HashTreeType.Sequence }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link HashTreeType.Sequence }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.setcce.org/schemas/ers", name = "Sequence", scope = HashTreeType.class)
    public JAXBElement<HashTreeType.Sequence> createHashTreeTypeSequence(HashTreeType.Sequence value) {
        return new JAXBElement<HashTreeType.Sequence>(_HashTreeTypeSequence_QNAME, HashTreeType.Sequence.class, HashTreeType.class, value);
    }

}
