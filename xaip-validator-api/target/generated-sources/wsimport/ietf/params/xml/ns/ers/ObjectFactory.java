
package ietf.params.xml.ns.ers;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ietf.params.xml.ns.ers package. 
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

    private final static QName _EvidenceRecord_QNAME = new QName("urn:ietf:params:xml:ns:ers", "EvidenceRecord");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ietf.params.xml.ns.ers
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SupportingInformationType }
     * 
     */
    public SupportingInformationType createSupportingInformationType() {
        return new SupportingInformationType();
    }

    /**
     * Create an instance of {@link CryptographicInformationType }
     * 
     */
    public CryptographicInformationType createCryptographicInformationType() {
        return new CryptographicInformationType();
    }

    /**
     * Create an instance of {@link Attributes }
     * 
     */
    public Attributes createAttributes() {
        return new Attributes();
    }

    /**
     * Create an instance of {@link HashTreeType }
     * 
     */
    public HashTreeType createHashTreeType() {
        return new HashTreeType();
    }

    /**
     * Create an instance of {@link TimeStampType }
     * 
     */
    public TimeStampType createTimeStampType() {
        return new TimeStampType();
    }

    /**
     * Create an instance of {@link ArchiveTimeStampSequenceType }
     * 
     */
    public ArchiveTimeStampSequenceType createArchiveTimeStampSequenceType() {
        return new ArchiveTimeStampSequenceType();
    }

    /**
     * Create an instance of {@link EncryptionInfo }
     * 
     */
    public EncryptionInfo createEncryptionInfo() {
        return new EncryptionInfo();
    }

    /**
     * Create an instance of {@link EvidenceRecordType }
     * 
     */
    public EvidenceRecordType createEvidenceRecordType() {
        return new EvidenceRecordType();
    }

    /**
     * Create an instance of {@link ArchiveTimeStampType }
     * 
     */
    public ArchiveTimeStampType createArchiveTimeStampType() {
        return new ArchiveTimeStampType();
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
     * Create an instance of {@link SupportingInformationType.SupportingInformation }
     * 
     */
    public SupportingInformationType.SupportingInformation createSupportingInformationTypeSupportingInformation() {
        return new SupportingInformationType.SupportingInformation();
    }

    /**
     * Create an instance of {@link CryptographicInformationType.CryptographicInformation }
     * 
     */
    public CryptographicInformationType.CryptographicInformation createCryptographicInformationTypeCryptographicInformation() {
        return new CryptographicInformationType.CryptographicInformation();
    }

    /**
     * Create an instance of {@link Attributes.Attribute }
     * 
     */
    public Attributes.Attribute createAttributesAttribute() {
        return new Attributes.Attribute();
    }

    /**
     * Create an instance of {@link HashTreeType.Sequence }
     * 
     */
    public HashTreeType.Sequence createHashTreeTypeSequence() {
        return new HashTreeType.Sequence();
    }

    /**
     * Create an instance of {@link TimeStampType.TimeStampToken }
     * 
     */
    public TimeStampType.TimeStampToken createTimeStampTypeTimeStampToken() {
        return new TimeStampType.TimeStampToken();
    }

    /**
     * Create an instance of {@link ArchiveTimeStampSequenceType.ArchiveTimeStampChain }
     * 
     */
    public ArchiveTimeStampSequenceType.ArchiveTimeStampChain createArchiveTimeStampSequenceTypeArchiveTimeStampChain() {
        return new ArchiveTimeStampSequenceType.ArchiveTimeStampChain();
    }

    /**
     * Create an instance of {@link EncryptionInfo.EncryptionInformationValue }
     * 
     */
    public EncryptionInfo.EncryptionInformationValue createEncryptionInfoEncryptionInformationValue() {
        return new EncryptionInfo.EncryptionInformationValue();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EvidenceRecordType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link EvidenceRecordType }{@code >}
     */
    @XmlElementDecl(namespace = "urn:ietf:params:xml:ns:ers", name = "EvidenceRecord")
    public JAXBElement<EvidenceRecordType> createEvidenceRecord(EvidenceRecordType value) {
        return new JAXBElement<EvidenceRecordType>(_EvidenceRecord_QNAME, EvidenceRecordType.class, null, value);
    }

}
