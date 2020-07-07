
package oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_ package. 
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

    private final static QName _VerificationReport_QNAME = new QName("urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#", "VerificationReport");
    private final static QName _DetailedSignatureReport_QNAME = new QName("urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#", "DetailedSignatureReport");
    private final static QName _IndividualTimeStampReport_QNAME = new QName("urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#", "IndividualTimeStampReport");
    private final static QName _IndividualCertificateReport_QNAME = new QName("urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#", "IndividualCertificateReport");
    private final static QName _IndividualAttributeCertificateReport_QNAME = new QName("urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#", "IndividualAttributeCertificateReport");
    private final static QName _IndividualCRLReport_QNAME = new QName("urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#", "IndividualCRLReport");
    private final static QName _IndividualOCSPReport_QNAME = new QName("urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#", "IndividualOCSPReport");
    private final static QName _EvidenceRecordReport_QNAME = new QName("urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#", "EvidenceRecordReport");
    private final static QName _UnsignedSignaturePropertiesTypeCounterSignature_QNAME = new QName("urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#", "CounterSignature");
    private final static QName _UnsignedSignaturePropertiesTypeSignatureTimeStamp_QNAME = new QName("urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#", "SignatureTimeStamp");
    private final static QName _UnsignedSignaturePropertiesTypeSigAndRefsTimeStamp_QNAME = new QName("urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#", "SigAndRefsTimeStamp");
    private final static QName _UnsignedSignaturePropertiesTypeRefsOnlyTimeStamp_QNAME = new QName("urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#", "RefsOnlyTimeStamp");
    private final static QName _UnsignedSignaturePropertiesTypeCertificateValues_QNAME = new QName("urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#", "CertificateValues");
    private final static QName _UnsignedSignaturePropertiesTypeRevocationValues_QNAME = new QName("urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#", "RevocationValues");
    private final static QName _UnsignedSignaturePropertiesTypeAttrAuthoritiesCertValues_QNAME = new QName("urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#", "AttrAuthoritiesCertValues");
    private final static QName _UnsignedSignaturePropertiesTypeAttributeRevocationValues_QNAME = new QName("urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#", "AttributeRevocationValues");
    private final static QName _UnsignedSignaturePropertiesTypeArchiveTimeStamp_QNAME = new QName("urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#", "ArchiveTimeStamp");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ArchiveTimeStampValidityType }
     * 
     */
    public ArchiveTimeStampValidityType createArchiveTimeStampValidityType() {
        return new ArchiveTimeStampValidityType();
    }

    /**
     * Create an instance of {@link ArchiveTimeStampValidityType.ReducedHashTree }
     * 
     */
    public ArchiveTimeStampValidityType.ReducedHashTree createArchiveTimeStampValidityTypeReducedHashTree() {
        return new ArchiveTimeStampValidityType.ReducedHashTree();
    }

    /**
     * Create an instance of {@link RevocationValuesType }
     * 
     */
    public RevocationValuesType createRevocationValuesType() {
        return new RevocationValuesType();
    }

    /**
     * Create an instance of {@link SingleResponseType }
     * 
     */
    public SingleResponseType createSingleResponseType() {
        return new SingleResponseType();
    }

    /**
     * Create an instance of {@link OCSPContentType }
     * 
     */
    public OCSPContentType createOCSPContentType() {
        return new OCSPContentType();
    }

    /**
     * Create an instance of {@link CRLContentType }
     * 
     */
    public CRLContentType createCRLContentType() {
        return new CRLContentType();
    }

    /**
     * Create an instance of {@link CertificateStatusType }
     * 
     */
    public CertificateStatusType createCertificateStatusType() {
        return new CertificateStatusType();
    }

    /**
     * Create an instance of {@link AttributeCertificateContentType }
     * 
     */
    public AttributeCertificateContentType createAttributeCertificateContentType() {
        return new AttributeCertificateContentType();
    }

    /**
     * Create an instance of {@link EvidenceRecordValidityType }
     * 
     */
    public EvidenceRecordValidityType createEvidenceRecordValidityType() {
        return new EvidenceRecordValidityType();
    }

    /**
     * Create an instance of {@link EvidenceRecordValidityType.ArchiveTimeStampSequence }
     * 
     */
    public EvidenceRecordValidityType.ArchiveTimeStampSequence createEvidenceRecordValidityTypeArchiveTimeStampSequence() {
        return new EvidenceRecordValidityType.ArchiveTimeStampSequence();
    }

    /**
     * Create an instance of {@link ReturnVerificationReport }
     * 
     */
    public ReturnVerificationReport createReturnVerificationReport() {
        return new ReturnVerificationReport();
    }

    /**
     * Create an instance of {@link VerificationReportType }
     * 
     */
    public VerificationReportType createVerificationReportType() {
        return new VerificationReportType();
    }

    /**
     * Create an instance of {@link DetailedSignatureReportType }
     * 
     */
    public DetailedSignatureReportType createDetailedSignatureReportType() {
        return new DetailedSignatureReportType();
    }

    /**
     * Create an instance of {@link TimeStampValidityType }
     * 
     */
    public TimeStampValidityType createTimeStampValidityType() {
        return new TimeStampValidityType();
    }

    /**
     * Create an instance of {@link CertificateValidityType }
     * 
     */
    public CertificateValidityType createCertificateValidityType() {
        return new CertificateValidityType();
    }

    /**
     * Create an instance of {@link AttributeCertificateValidityType }
     * 
     */
    public AttributeCertificateValidityType createAttributeCertificateValidityType() {
        return new AttributeCertificateValidityType();
    }

    /**
     * Create an instance of {@link CRLValidityType }
     * 
     */
    public CRLValidityType createCRLValidityType() {
        return new CRLValidityType();
    }

    /**
     * Create an instance of {@link OCSPValidityType }
     * 
     */
    public OCSPValidityType createOCSPValidityType() {
        return new OCSPValidityType();
    }

    /**
     * Create an instance of {@link IdentifierType }
     * 
     */
    public IdentifierType createIdentifierType() {
        return new IdentifierType();
    }

    /**
     * Create an instance of {@link IndividualReportType }
     * 
     */
    public IndividualReportType createIndividualReportType() {
        return new IndividualReportType();
    }

    /**
     * Create an instance of {@link SignedObjectIdentifierType }
     * 
     */
    public SignedObjectIdentifierType createSignedObjectIdentifierType() {
        return new SignedObjectIdentifierType();
    }

    /**
     * Create an instance of {@link VerificationResultType }
     * 
     */
    public VerificationResultType createVerificationResultType() {
        return new VerificationResultType();
    }

    /**
     * Create an instance of {@link PropertiesType }
     * 
     */
    public PropertiesType createPropertiesType() {
        return new PropertiesType();
    }

    /**
     * Create an instance of {@link SignedPropertiesType }
     * 
     */
    public SignedPropertiesType createSignedPropertiesType() {
        return new SignedPropertiesType();
    }

    /**
     * Create an instance of {@link SignedSignaturePropertiesType }
     * 
     */
    public SignedSignaturePropertiesType createSignedSignaturePropertiesType() {
        return new SignedSignaturePropertiesType();
    }

    /**
     * Create an instance of {@link SignerRoleType }
     * 
     */
    public SignerRoleType createSignerRoleType() {
        return new SignerRoleType();
    }

    /**
     * Create an instance of {@link CertifiedRolesListType }
     * 
     */
    public CertifiedRolesListType createCertifiedRolesListType() {
        return new CertifiedRolesListType();
    }

    /**
     * Create an instance of {@link AttrCertIDType }
     * 
     */
    public AttrCertIDType createAttrCertIDType() {
        return new AttrCertIDType();
    }

    /**
     * Create an instance of {@link EntityType }
     * 
     */
    public EntityType createEntityType() {
        return new EntityType();
    }

    /**
     * Create an instance of {@link ValidityPeriodType }
     * 
     */
    public ValidityPeriodType createValidityPeriodType() {
        return new ValidityPeriodType();
    }

    /**
     * Create an instance of {@link AttributeType }
     * 
     */
    public AttributeType createAttributeType() {
        return new AttributeType();
    }

    /**
     * Create an instance of {@link ExtensionsType }
     * 
     */
    public ExtensionsType createExtensionsType() {
        return new ExtensionsType();
    }

    /**
     * Create an instance of {@link ExtensionType }
     * 
     */
    public ExtensionType createExtensionType() {
        return new ExtensionType();
    }

    /**
     * Create an instance of {@link SignedDataObjectPropertiesType }
     * 
     */
    public SignedDataObjectPropertiesType createSignedDataObjectPropertiesType() {
        return new SignedDataObjectPropertiesType();
    }

    /**
     * Create an instance of {@link TstContentType }
     * 
     */
    public TstContentType createTstContentType() {
        return new TstContentType();
    }

    /**
     * Create an instance of {@link SignatureValidityType }
     * 
     */
    public SignatureValidityType createSignatureValidityType() {
        return new SignatureValidityType();
    }

    /**
     * Create an instance of {@link AlgorithmValidityType }
     * 
     */
    public AlgorithmValidityType createAlgorithmValidityType() {
        return new AlgorithmValidityType();
    }

    /**
     * Create an instance of {@link CertificatePathValidityType }
     * 
     */
    public CertificatePathValidityType createCertificatePathValidityType() {
        return new CertificatePathValidityType();
    }

    /**
     * Create an instance of {@link CertificatePathValidityVerificationDetailType }
     * 
     */
    public CertificatePathValidityVerificationDetailType createCertificatePathValidityVerificationDetailType() {
        return new CertificatePathValidityVerificationDetailType();
    }

    /**
     * Create an instance of {@link CertificateContentType }
     * 
     */
    public CertificateContentType createCertificateContentType() {
        return new CertificateContentType();
    }

    /**
     * Create an instance of {@link UnsignedPropertiesType }
     * 
     */
    public UnsignedPropertiesType createUnsignedPropertiesType() {
        return new UnsignedPropertiesType();
    }

    /**
     * Create an instance of {@link UnsignedSignaturePropertiesType }
     * 
     */
    public UnsignedSignaturePropertiesType createUnsignedSignaturePropertiesType() {
        return new UnsignedSignaturePropertiesType();
    }

    /**
     * Create an instance of {@link CertificateValuesType }
     * 
     */
    public CertificateValuesType createCertificateValuesType() {
        return new CertificateValuesType();
    }

    /**
     * Create an instance of {@link HashValueType }
     * 
     */
    public HashValueType createHashValueType() {
        return new HashValueType();
    }

    /**
     * Create an instance of {@link ArchiveTimeStampValidityType.Attributes }
     * 
     */
    public ArchiveTimeStampValidityType.Attributes createArchiveTimeStampValidityTypeAttributes() {
        return new ArchiveTimeStampValidityType.Attributes();
    }

    /**
     * Create an instance of {@link ArchiveTimeStampValidityType.ReducedHashTree.PartialHashTree }
     * 
     */
    public ArchiveTimeStampValidityType.ReducedHashTree.PartialHashTree createArchiveTimeStampValidityTypeReducedHashTreePartialHashTree() {
        return new ArchiveTimeStampValidityType.ReducedHashTree.PartialHashTree();
    }

    /**
     * Create an instance of {@link RevocationValuesType.CRLValues }
     * 
     */
    public RevocationValuesType.CRLValues createRevocationValuesTypeCRLValues() {
        return new RevocationValuesType.CRLValues();
    }

    /**
     * Create an instance of {@link RevocationValuesType.OCSPValues }
     * 
     */
    public RevocationValuesType.OCSPValues createRevocationValuesTypeOCSPValues() {
        return new RevocationValuesType.OCSPValues();
    }

    /**
     * Create an instance of {@link SingleResponseType.CertID }
     * 
     */
    public SingleResponseType.CertID createSingleResponseTypeCertID() {
        return new SingleResponseType.CertID();
    }

    /**
     * Create an instance of {@link OCSPContentType.Responses }
     * 
     */
    public OCSPContentType.Responses createOCSPContentTypeResponses() {
        return new OCSPContentType.Responses();
    }

    /**
     * Create an instance of {@link CRLContentType.RevokedCertificates }
     * 
     */
    public CRLContentType.RevokedCertificates createCRLContentTypeRevokedCertificates() {
        return new CRLContentType.RevokedCertificates();
    }

    /**
     * Create an instance of {@link CertificateStatusType.RevocationInfo }
     * 
     */
    public CertificateStatusType.RevocationInfo createCertificateStatusTypeRevocationInfo() {
        return new CertificateStatusType.RevocationInfo();
    }

    /**
     * Create an instance of {@link CertificateStatusType.RevocationEvidence }
     * 
     */
    public CertificateStatusType.RevocationEvidence createCertificateStatusTypeRevocationEvidence() {
        return new CertificateStatusType.RevocationEvidence();
    }

    /**
     * Create an instance of {@link AttributeCertificateContentType.Attributes }
     * 
     */
    public AttributeCertificateContentType.Attributes createAttributeCertificateContentTypeAttributes() {
        return new AttributeCertificateContentType.Attributes();
    }

    /**
     * Create an instance of {@link EvidenceRecordValidityType.CryptoInfos }
     * 
     */
    public EvidenceRecordValidityType.CryptoInfos createEvidenceRecordValidityTypeCryptoInfos() {
        return new EvidenceRecordValidityType.CryptoInfos();
    }

    /**
     * Create an instance of {@link EvidenceRecordValidityType.EncryptionInfo }
     * 
     */
    public EvidenceRecordValidityType.EncryptionInfo createEvidenceRecordValidityTypeEncryptionInfo() {
        return new EvidenceRecordValidityType.EncryptionInfo();
    }

    /**
     * Create an instance of {@link EvidenceRecordValidityType.ArchiveTimeStampSequence.ArchiveTimeStampChain }
     * 
     */
    public EvidenceRecordValidityType.ArchiveTimeStampSequence.ArchiveTimeStampChain createEvidenceRecordValidityTypeArchiveTimeStampSequenceArchiveTimeStampChain() {
        return new EvidenceRecordValidityType.ArchiveTimeStampSequence.ArchiveTimeStampChain();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VerificationReportType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link VerificationReportType }{@code >}
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#", name = "VerificationReport")
    public JAXBElement<VerificationReportType> createVerificationReport(VerificationReportType value) {
        return new JAXBElement<VerificationReportType>(_VerificationReport_QNAME, VerificationReportType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DetailedSignatureReportType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DetailedSignatureReportType }{@code >}
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#", name = "DetailedSignatureReport")
    public JAXBElement<DetailedSignatureReportType> createDetailedSignatureReport(DetailedSignatureReportType value) {
        return new JAXBElement<DetailedSignatureReportType>(_DetailedSignatureReport_QNAME, DetailedSignatureReportType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TimeStampValidityType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TimeStampValidityType }{@code >}
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#", name = "IndividualTimeStampReport")
    public JAXBElement<TimeStampValidityType> createIndividualTimeStampReport(TimeStampValidityType value) {
        return new JAXBElement<TimeStampValidityType>(_IndividualTimeStampReport_QNAME, TimeStampValidityType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CertificateValidityType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CertificateValidityType }{@code >}
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#", name = "IndividualCertificateReport")
    public JAXBElement<CertificateValidityType> createIndividualCertificateReport(CertificateValidityType value) {
        return new JAXBElement<CertificateValidityType>(_IndividualCertificateReport_QNAME, CertificateValidityType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AttributeCertificateValidityType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AttributeCertificateValidityType }{@code >}
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#", name = "IndividualAttributeCertificateReport")
    public JAXBElement<AttributeCertificateValidityType> createIndividualAttributeCertificateReport(AttributeCertificateValidityType value) {
        return new JAXBElement<AttributeCertificateValidityType>(_IndividualAttributeCertificateReport_QNAME, AttributeCertificateValidityType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CRLValidityType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CRLValidityType }{@code >}
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#", name = "IndividualCRLReport")
    public JAXBElement<CRLValidityType> createIndividualCRLReport(CRLValidityType value) {
        return new JAXBElement<CRLValidityType>(_IndividualCRLReport_QNAME, CRLValidityType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OCSPValidityType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link OCSPValidityType }{@code >}
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#", name = "IndividualOCSPReport")
    public JAXBElement<OCSPValidityType> createIndividualOCSPReport(OCSPValidityType value) {
        return new JAXBElement<OCSPValidityType>(_IndividualOCSPReport_QNAME, OCSPValidityType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EvidenceRecordValidityType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link EvidenceRecordValidityType }{@code >}
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#", name = "EvidenceRecordReport")
    public JAXBElement<EvidenceRecordValidityType> createEvidenceRecordReport(EvidenceRecordValidityType value) {
        return new JAXBElement<EvidenceRecordValidityType>(_EvidenceRecordReport_QNAME, EvidenceRecordValidityType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SignatureValidityType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SignatureValidityType }{@code >}
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#", name = "CounterSignature", scope = UnsignedSignaturePropertiesType.class)
    public JAXBElement<SignatureValidityType> createUnsignedSignaturePropertiesTypeCounterSignature(SignatureValidityType value) {
        return new JAXBElement<SignatureValidityType>(_UnsignedSignaturePropertiesTypeCounterSignature_QNAME, SignatureValidityType.class, UnsignedSignaturePropertiesType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TimeStampValidityType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TimeStampValidityType }{@code >}
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#", name = "SignatureTimeStamp", scope = UnsignedSignaturePropertiesType.class)
    public JAXBElement<TimeStampValidityType> createUnsignedSignaturePropertiesTypeSignatureTimeStamp(TimeStampValidityType value) {
        return new JAXBElement<TimeStampValidityType>(_UnsignedSignaturePropertiesTypeSignatureTimeStamp_QNAME, TimeStampValidityType.class, UnsignedSignaturePropertiesType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TimeStampValidityType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TimeStampValidityType }{@code >}
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#", name = "SigAndRefsTimeStamp", scope = UnsignedSignaturePropertiesType.class)
    public JAXBElement<TimeStampValidityType> createUnsignedSignaturePropertiesTypeSigAndRefsTimeStamp(TimeStampValidityType value) {
        return new JAXBElement<TimeStampValidityType>(_UnsignedSignaturePropertiesTypeSigAndRefsTimeStamp_QNAME, TimeStampValidityType.class, UnsignedSignaturePropertiesType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TimeStampValidityType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TimeStampValidityType }{@code >}
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#", name = "RefsOnlyTimeStamp", scope = UnsignedSignaturePropertiesType.class)
    public JAXBElement<TimeStampValidityType> createUnsignedSignaturePropertiesTypeRefsOnlyTimeStamp(TimeStampValidityType value) {
        return new JAXBElement<TimeStampValidityType>(_UnsignedSignaturePropertiesTypeRefsOnlyTimeStamp_QNAME, TimeStampValidityType.class, UnsignedSignaturePropertiesType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CertificateValuesType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CertificateValuesType }{@code >}
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#", name = "CertificateValues", scope = UnsignedSignaturePropertiesType.class)
    public JAXBElement<CertificateValuesType> createUnsignedSignaturePropertiesTypeCertificateValues(CertificateValuesType value) {
        return new JAXBElement<CertificateValuesType>(_UnsignedSignaturePropertiesTypeCertificateValues_QNAME, CertificateValuesType.class, UnsignedSignaturePropertiesType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RevocationValuesType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RevocationValuesType }{@code >}
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#", name = "RevocationValues", scope = UnsignedSignaturePropertiesType.class)
    public JAXBElement<RevocationValuesType> createUnsignedSignaturePropertiesTypeRevocationValues(RevocationValuesType value) {
        return new JAXBElement<RevocationValuesType>(_UnsignedSignaturePropertiesTypeRevocationValues_QNAME, RevocationValuesType.class, UnsignedSignaturePropertiesType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CertificateValuesType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CertificateValuesType }{@code >}
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#", name = "AttrAuthoritiesCertValues", scope = UnsignedSignaturePropertiesType.class)
    public JAXBElement<CertificateValuesType> createUnsignedSignaturePropertiesTypeAttrAuthoritiesCertValues(CertificateValuesType value) {
        return new JAXBElement<CertificateValuesType>(_UnsignedSignaturePropertiesTypeAttrAuthoritiesCertValues_QNAME, CertificateValuesType.class, UnsignedSignaturePropertiesType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RevocationValuesType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RevocationValuesType }{@code >}
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#", name = "AttributeRevocationValues", scope = UnsignedSignaturePropertiesType.class)
    public JAXBElement<RevocationValuesType> createUnsignedSignaturePropertiesTypeAttributeRevocationValues(RevocationValuesType value) {
        return new JAXBElement<RevocationValuesType>(_UnsignedSignaturePropertiesTypeAttributeRevocationValues_QNAME, RevocationValuesType.class, UnsignedSignaturePropertiesType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TimeStampValidityType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TimeStampValidityType }{@code >}
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#", name = "ArchiveTimeStamp", scope = UnsignedSignaturePropertiesType.class)
    public JAXBElement<TimeStampValidityType> createUnsignedSignaturePropertiesTypeArchiveTimeStamp(TimeStampValidityType value) {
        return new JAXBElement<TimeStampValidityType>(_UnsignedSignaturePropertiesTypeArchiveTimeStamp_QNAME, TimeStampValidityType.class, UnsignedSignaturePropertiesType.class, value);
    }

}
