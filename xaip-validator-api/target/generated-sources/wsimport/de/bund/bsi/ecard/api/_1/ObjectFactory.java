
package de.bund.bsi.ecard.api._1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the de.bund.bsi.ecard.api._1 package. 
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

    private final static QName _DIDName_QNAME = new QName("http://www.bsi.bund.de/ecard/api/1.1", "DIDName");
    private final static QName _DSIName_QNAME = new QName("http://www.bsi.bund.de/ecard/api/1.1", "DSIName");
    private final static QName _SignatureForm_QNAME = new QName("http://www.bsi.bund.de/ecard/api/1.1", "SignatureForm");
    private final static QName _IncludeEContent_QNAME = new QName("http://www.bsi.bund.de/ecard/api/1.1", "IncludeEContent");
    private final static QName _VerifyManifests_QNAME = new QName("http://www.bsi.bund.de/ecard/api/1.1", "VerifyManifests");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: de.bund.bsi.ecard.api._1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ShowViewer }
     * 
     */
    public ShowViewer createShowViewer() {
        return new ShowViewer();
    }

    /**
     * Create an instance of {@link GetCertificate }
     * 
     */
    public GetCertificate createGetCertificate() {
        return new GetCertificate();
    }

    /**
     * Create an instance of {@link GetCertificateResponse }
     * 
     */
    public GetCertificateResponse createGetCertificateResponse() {
        return new GetCertificateResponse();
    }

    /**
     * Create an instance of {@link SignRequest }
     * 
     */
    public SignRequest createSignRequest() {
        return new SignRequest();
    }

    /**
     * Create an instance of {@link ConnectionHandle }
     * 
     */
    public ConnectionHandle createConnectionHandle() {
        return new ConnectionHandle();
    }

    /**
     * Create an instance of {@link PreviousTimeStampHash }
     * 
     */
    public PreviousTimeStampHash createPreviousTimeStampHash() {
        return new PreviousTimeStampHash();
    }

    /**
     * Create an instance of {@link TrustedViewerInfo }
     * 
     */
    public TrustedViewerInfo createTrustedViewerInfo() {
        return new TrustedViewerInfo();
    }

    /**
     * Create an instance of {@link TrustedViewerInfoType }
     * 
     */
    public TrustedViewerInfoType createTrustedViewerInfoType() {
        return new TrustedViewerInfoType();
    }

    /**
     * Create an instance of {@link StyleSheetType }
     * 
     */
    public StyleSheetType createStyleSheetType() {
        return new StyleSheetType();
    }

    /**
     * Create an instance of {@link SignResponse }
     * 
     */
    public SignResponse createSignResponse() {
        return new SignResponse();
    }

    /**
     * Create an instance of {@link SignatureObject }
     * 
     */
    public SignatureObject createSignatureObject() {
        return new SignatureObject();
    }

    /**
     * Create an instance of {@link VerifyRequest }
     * 
     */
    public VerifyRequest createVerifyRequest() {
        return new VerifyRequest();
    }

    /**
     * Create an instance of {@link UseVerificationTime }
     * 
     */
    public UseVerificationTime createUseVerificationTime() {
        return new UseVerificationTime();
    }

    /**
     * Create an instance of {@link EvidenceRecord }
     * 
     */
    public EvidenceRecord createEvidenceRecord() {
        return new EvidenceRecord();
    }

    /**
     * Create an instance of {@link EvidenceRecordType }
     * 
     */
    public EvidenceRecordType createEvidenceRecordType() {
        return new EvidenceRecordType();
    }

    /**
     * Create an instance of {@link VerifyResponse }
     * 
     */
    public VerifyResponse createVerifyResponse() {
        return new VerifyResponse();
    }

    /**
     * Create an instance of {@link ShowViewer.ViewerMessage }
     * 
     */
    public ShowViewer.ViewerMessage createShowViewerViewerMessage() {
        return new ShowViewer.ViewerMessage();
    }

    /**
     * Create an instance of {@link ShowViewerResponse }
     * 
     */
    public ShowViewerResponse createShowViewerResponse() {
        return new ShowViewerResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.bsi.bund.de/ecard/api/1.1", name = "DIDName")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createDIDName(String value) {
        return new JAXBElement<String>(_DIDName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.bsi.bund.de/ecard/api/1.1", name = "DSIName")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createDSIName(String value) {
        return new JAXBElement<String>(_DSIName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.bsi.bund.de/ecard/api/1.1", name = "SignatureForm")
    public JAXBElement<String> createSignatureForm(String value) {
        return new JAXBElement<String>(_SignatureForm_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.bsi.bund.de/ecard/api/1.1", name = "IncludeEContent")
    public JAXBElement<Object> createIncludeEContent(Object value) {
        return new JAXBElement<Object>(_IncludeEContent_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.bsi.bund.de/ecard/api/1.1", name = "VerifyManifests")
    public JAXBElement<Object> createVerifyManifests(Object value) {
        return new JAXBElement<Object>(_VerifyManifests_QNAME, Object.class, null, value);
    }

}
