
package de.bund.bsi.tr_esor.api._1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the de.bund.bsi.tr_esor.api._1 package. 
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

    private final static QName _AOID_QNAME = new QName("http://www.bsi.bund.de/tr-esor/api/1.2", "AOID");
    private final static QName _ImportEvidence_QNAME = new QName("http://www.bsi.bund.de/tr-esor/api/1.2", "ImportEvidence");
    private final static QName _IncludeERS_QNAME = new QName("http://www.bsi.bund.de/tr-esor/api/1.2", "IncludeERS");
    private final static QName _ERSFormat_QNAME = new QName("http://www.bsi.bund.de/tr-esor/api/1.2", "ERSFormat");
    private final static QName _ArchiveDeletionResponse_QNAME = new QName("http://www.bsi.bund.de/tr-esor/api/1.2", "ArchiveDeletionResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: de.bund.bsi.tr_esor.api._1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ArchiveDataResponse }
     * 
     */
    public ArchiveDataResponse createArchiveDataResponse() {
        return new ArchiveDataResponse();
    }

    /**
     * Create an instance of {@link ImportEvidenceType }
     * 
     */
    public ImportEvidenceType createImportEvidenceType() {
        return new ImportEvidenceType();
    }

    /**
     * Create an instance of {@link ArchiveSubmissionRequest }
     * 
     */
    public ArchiveSubmissionRequest createArchiveSubmissionRequest() {
        return new ArchiveSubmissionRequest();
    }

    /**
     * Create an instance of {@link RequestType }
     * 
     */
    public RequestType createRequestType() {
        return new RequestType();
    }

    /**
     * Create an instance of {@link ArchiveDataType }
     * 
     */
    public ArchiveDataType createArchiveDataType() {
        return new ArchiveDataType();
    }

    /**
     * Create an instance of {@link ArchiveSubmissionResponse }
     * 
     */
    public ArchiveSubmissionResponse createArchiveSubmissionResponse() {
        return new ArchiveSubmissionResponse();
    }

    /**
     * Create an instance of {@link ResponseType }
     * 
     */
    public ResponseType createResponseType() {
        return new ResponseType();
    }

    /**
     * Create an instance of {@link ArchiveUpdateRequest }
     * 
     */
    public ArchiveUpdateRequest createArchiveUpdateRequest() {
        return new ArchiveUpdateRequest();
    }

    /**
     * Create an instance of {@link ArchiveUpdateResponse }
     * 
     */
    public ArchiveUpdateResponse createArchiveUpdateResponse() {
        return new ArchiveUpdateResponse();
    }

    /**
     * Create an instance of {@link ArchiveRetrievalRequest }
     * 
     */
    public ArchiveRetrievalRequest createArchiveRetrievalRequest() {
        return new ArchiveRetrievalRequest();
    }

    /**
     * Create an instance of {@link ArchiveRetrievalResponse }
     * 
     */
    public ArchiveRetrievalResponse createArchiveRetrievalResponse() {
        return new ArchiveRetrievalResponse();
    }

    /**
     * Create an instance of {@link ArchiveEvidenceRequest }
     * 
     */
    public ArchiveEvidenceRequest createArchiveEvidenceRequest() {
        return new ArchiveEvidenceRequest();
    }

    /**
     * Create an instance of {@link ArchiveEvidenceResponse }
     * 
     */
    public ArchiveEvidenceResponse createArchiveEvidenceResponse() {
        return new ArchiveEvidenceResponse();
    }

    /**
     * Create an instance of {@link ArchiveDeletionRequest }
     * 
     */
    public ArchiveDeletionRequest createArchiveDeletionRequest() {
        return new ArchiveDeletionRequest();
    }

    /**
     * Create an instance of {@link ReasonOfDeletion }
     * 
     */
    public ReasonOfDeletion createReasonOfDeletion() {
        return new ReasonOfDeletion();
    }

    /**
     * Create an instance of {@link ArchiveDataRequest }
     * 
     */
    public ArchiveDataRequest createArchiveDataRequest() {
        return new ArchiveDataRequest();
    }

    /**
     * Create an instance of {@link DataLocation }
     * 
     */
    public DataLocation createDataLocation() {
        return new DataLocation();
    }

    /**
     * Create an instance of {@link ArchiveDataResponse.XAIPData }
     * 
     */
    public ArchiveDataResponse.XAIPData createArchiveDataResponseXAIPData() {
        return new ArchiveDataResponse.XAIPData();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.bsi.bund.de/tr-esor/api/1.2", name = "AOID")
    public JAXBElement<String> createAOID(String value) {
        return new JAXBElement<String>(_AOID_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ImportEvidenceType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ImportEvidenceType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.bsi.bund.de/tr-esor/api/1.2", name = "ImportEvidence")
    public JAXBElement<ImportEvidenceType> createImportEvidence(ImportEvidenceType value) {
        return new JAXBElement<ImportEvidenceType>(_ImportEvidence_QNAME, ImportEvidenceType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.bsi.bund.de/tr-esor/api/1.2", name = "IncludeERS")
    public JAXBElement<String> createIncludeERS(String value) {
        return new JAXBElement<String>(_IncludeERS_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.bsi.bund.de/tr-esor/api/1.2", name = "ERSFormat")
    public JAXBElement<String> createERSFormat(String value) {
        return new JAXBElement<String>(_ERSFormat_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ResponseType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.bsi.bund.de/tr-esor/api/1.2", name = "ArchiveDeletionResponse")
    public JAXBElement<ResponseType> createArchiveDeletionResponse(ResponseType value) {
        return new JAXBElement<ResponseType>(_ArchiveDeletionResponse_QNAME, ResponseType.class, null, value);
    }

}
