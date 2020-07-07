
package de.bund.bsi.tr_esor.xaip._1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the de.bund.bsi.tr_esor.xaip._1 package. 
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

    private final static QName _XAIP_QNAME = new QName("http://www.bsi.bund.de/tr-esor/xaip/1.2", "XAIP");
    private final static QName _DXAIP_QNAME = new QName("http://www.bsi.bund.de/tr-esor/xaip/1.2", "DXAIP");
    private final static QName _EvidenceRecord_QNAME = new QName("http://www.bsi.bund.de/tr-esor/xaip/1.2", "evidenceRecord");
    private final static QName _MetaDataObject_QNAME = new QName("http://www.bsi.bund.de/tr-esor/xaip/1.2", "metaDataObject");
    private final static QName _DataObjectCheckSum_QNAME = new QName("http://www.bsi.bund.de/tr-esor/xaip/1.2", "dataObjectCheckSum");
    private final static QName _DataObject_QNAME = new QName("http://www.bsi.bund.de/tr-esor/xaip/1.2", "dataObject");
    private final static QName _Credential_QNAME = new QName("http://www.bsi.bund.de/tr-esor/xaip/1.2", "credential");
    private final static QName _PackageInfoUnitTypeProtectedObjectPointer_QNAME = new QName("http://www.bsi.bund.de/tr-esor/xaip/1.2", "protectedObjectPointer");
    private final static QName _PackageInfoUnitTypeUnprotectedObjectPointer_QNAME = new QName("http://www.bsi.bund.de/tr-esor/xaip/1.2", "unprotectedObjectPointer");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: de.bund.bsi.tr_esor.xaip._1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DataObjectType }
     * 
     */
    public DataObjectType createDataObjectType() {
        return new DataObjectType();
    }

    /**
     * Create an instance of {@link XAIPType }
     * 
     */
    public XAIPType createXAIPType() {
        return new XAIPType();
    }

    /**
     * Create an instance of {@link DXAIPType }
     * 
     */
    public DXAIPType createDXAIPType() {
        return new DXAIPType();
    }

    /**
     * Create an instance of {@link EvidenceRecordType }
     * 
     */
    public EvidenceRecordType createEvidenceRecordType() {
        return new EvidenceRecordType();
    }

    /**
     * Create an instance of {@link MetaDataObjectType }
     * 
     */
    public MetaDataObjectType createMetaDataObjectType() {
        return new MetaDataObjectType();
    }

    /**
     * Create an instance of {@link CheckSumType }
     * 
     */
    public CheckSumType createCheckSumType() {
        return new CheckSumType();
    }

    /**
     * Create an instance of {@link CredentialType }
     * 
     */
    public CredentialType createCredentialType() {
        return new CredentialType();
    }

    /**
     * Create an instance of {@link PackageHeaderType }
     * 
     */
    public PackageHeaderType createPackageHeaderType() {
        return new PackageHeaderType();
    }

    /**
     * Create an instance of {@link ExtensionType }
     * 
     */
    public ExtensionType createExtensionType() {
        return new ExtensionType();
    }

    /**
     * Create an instance of {@link VersionManifestType }
     * 
     */
    public VersionManifestType createVersionManifestType() {
        return new VersionManifestType();
    }

    /**
     * Create an instance of {@link PreservationInfoType }
     * 
     */
    public PreservationInfoType createPreservationInfoType() {
        return new PreservationInfoType();
    }

    /**
     * Create an instance of {@link OtherContentType }
     * 
     */
    public OtherContentType createOtherContentType() {
        return new OtherContentType();
    }

    /**
     * Create an instance of {@link SubmissionInfoType }
     * 
     */
    public SubmissionInfoType createSubmissionInfoType() {
        return new SubmissionInfoType();
    }

    /**
     * Create an instance of {@link PackageInfoUnitType }
     * 
     */
    public PackageInfoUnitType createPackageInfoUnitType() {
        return new PackageInfoUnitType();
    }

    /**
     * Create an instance of {@link MetaDataSectionType }
     * 
     */
    public MetaDataSectionType createMetaDataSectionType() {
        return new MetaDataSectionType();
    }

    /**
     * Create an instance of {@link DataObjectsSectionType }
     * 
     */
    public DataObjectsSectionType createDataObjectsSectionType() {
        return new DataObjectsSectionType();
    }

    /**
     * Create an instance of {@link TranformInfoType }
     * 
     */
    public TranformInfoType createTranformInfoType() {
        return new TranformInfoType();
    }

    /**
     * Create an instance of {@link TransformObjectType }
     * 
     */
    public TransformObjectType createTransformObjectType() {
        return new TransformObjectType();
    }

    /**
     * Create an instance of {@link CredentialsSectionType }
     * 
     */
    public CredentialsSectionType createCredentialsSectionType() {
        return new CredentialsSectionType();
    }

    /**
     * Create an instance of {@link UpdateSectionType }
     * 
     */
    public UpdateSectionType createUpdateSectionType() {
        return new UpdateSectionType();
    }

    /**
     * Create an instance of {@link PlaceHolderType }
     * 
     */
    public PlaceHolderType createPlaceHolderType() {
        return new PlaceHolderType();
    }

    /**
     * Create an instance of {@link DataObjectType.BinaryData }
     * 
     */
    public DataObjectType.BinaryData createDataObjectTypeBinaryData() {
        return new DataObjectType.BinaryData();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XAIPType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link XAIPType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.bsi.bund.de/tr-esor/xaip/1.2", name = "XAIP")
    public JAXBElement<XAIPType> createXAIP(XAIPType value) {
        return new JAXBElement<XAIPType>(_XAIP_QNAME, XAIPType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DXAIPType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DXAIPType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.bsi.bund.de/tr-esor/xaip/1.2", name = "DXAIP")
    public JAXBElement<DXAIPType> createDXAIP(DXAIPType value) {
        return new JAXBElement<DXAIPType>(_DXAIP_QNAME, DXAIPType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EvidenceRecordType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link EvidenceRecordType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.bsi.bund.de/tr-esor/xaip/1.2", name = "evidenceRecord")
    public JAXBElement<EvidenceRecordType> createEvidenceRecord(EvidenceRecordType value) {
        return new JAXBElement<EvidenceRecordType>(_EvidenceRecord_QNAME, EvidenceRecordType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MetaDataObjectType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MetaDataObjectType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.bsi.bund.de/tr-esor/xaip/1.2", name = "metaDataObject")
    public JAXBElement<MetaDataObjectType> createMetaDataObject(MetaDataObjectType value) {
        return new JAXBElement<MetaDataObjectType>(_MetaDataObject_QNAME, MetaDataObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckSumType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CheckSumType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.bsi.bund.de/tr-esor/xaip/1.2", name = "dataObjectCheckSum")
    public JAXBElement<CheckSumType> createDataObjectCheckSum(CheckSumType value) {
        return new JAXBElement<CheckSumType>(_DataObjectCheckSum_QNAME, CheckSumType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataObjectType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DataObjectType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.bsi.bund.de/tr-esor/xaip/1.2", name = "dataObject")
    public JAXBElement<DataObjectType> createDataObject(DataObjectType value) {
        return new JAXBElement<DataObjectType>(_DataObject_QNAME, DataObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CredentialType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CredentialType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.bsi.bund.de/tr-esor/xaip/1.2", name = "credential")
    public JAXBElement<CredentialType> createCredential(CredentialType value) {
        return new JAXBElement<CredentialType>(_Credential_QNAME, CredentialType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.bsi.bund.de/tr-esor/xaip/1.2", name = "protectedObjectPointer", scope = PackageInfoUnitType.class)
    @XmlIDREF
    public JAXBElement<Object> createPackageInfoUnitTypeProtectedObjectPointer(Object value) {
        return new JAXBElement<Object>(_PackageInfoUnitTypeProtectedObjectPointer_QNAME, Object.class, PackageInfoUnitType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.bsi.bund.de/tr-esor/xaip/1.2", name = "unprotectedObjectPointer", scope = PackageInfoUnitType.class)
    @XmlIDREF
    public JAXBElement<Object> createPackageInfoUnitTypeUnprotectedObjectPointer(Object value) {
        return new JAXBElement<Object>(_PackageInfoUnitTypeUnprotectedObjectPointer_QNAME, Object.class, PackageInfoUnitType.class, value);
    }

}
