
package oasis.names.tc.dss._1_0.profiles.ades.schema_;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the oasis.names.tc.dss._1_0.profiles.ades.schema_ package. 
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

    private final static QName _RequestedDocsFormat_QNAME = new QName("urn:oasis:names:tc:dss:1.0:profiles:AdES:schema#", "RequestedDocsFormat");
    private final static QName _DocsToBeTimeStamped_QNAME = new QName("urn:oasis:names:tc:dss:1.0:profiles:AdES:schema#", "DocsToBeTimeStamped");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: oasis.names.tc.dss._1_0.profiles.ades.schema_
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RequestedCommitment }
     * 
     */
    public RequestedCommitment createRequestedCommitment() {
        return new RequestedCommitment();
    }

    /**
     * Create an instance of {@link RequestedSignatureProductionPlace }
     * 
     */
    public RequestedSignatureProductionPlace createRequestedSignatureProductionPlace() {
        return new RequestedSignatureProductionPlace();
    }

    /**
     * Create an instance of {@link RequestedSignerRole }
     * 
     */
    public RequestedSignerRole createRequestedSignerRole() {
        return new RequestedSignerRole();
    }

    /**
     * Create an instance of {@link DocsFormatType }
     * 
     */
    public DocsFormatType createDocsFormatType() {
        return new DocsFormatType();
    }

    /**
     * Create an instance of {@link DocReferencesType }
     * 
     */
    public DocReferencesType createDocReferencesType() {
        return new DocReferencesType();
    }

    /**
     * Create an instance of {@link DocFormatType }
     * 
     */
    public DocFormatType createDocFormatType() {
        return new DocFormatType();
    }

    /**
     * Create an instance of {@link DocReferenceType }
     * 
     */
    public DocReferenceType createDocReferenceType() {
        return new DocReferenceType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DocsFormatType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DocsFormatType }{@code >}
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:dss:1.0:profiles:AdES:schema#", name = "RequestedDocsFormat")
    public JAXBElement<DocsFormatType> createRequestedDocsFormat(DocsFormatType value) {
        return new JAXBElement<DocsFormatType>(_RequestedDocsFormat_QNAME, DocsFormatType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DocReferencesType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DocReferencesType }{@code >}
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:dss:1.0:profiles:AdES:schema#", name = "DocsToBeTimeStamped")
    public JAXBElement<DocReferencesType> createDocsToBeTimeStamped(DocReferencesType value) {
        return new JAXBElement<DocReferencesType>(_DocsToBeTimeStamped_QNAME, DocReferencesType.class, null, value);
    }

}
