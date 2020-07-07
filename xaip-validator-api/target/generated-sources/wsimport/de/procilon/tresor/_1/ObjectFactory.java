
package de.procilon.tresor._1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the de.procilon.tresor._1 package. 
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

    private final static QName _XPath_QNAME = new QName("urn:de:procilon:tresor:1.2", "xPath");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: de.procilon.tresor._1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Xpath }
     * 
     */
    public Xpath createXpath() {
        return new Xpath();
    }

    /**
     * Create an instance of {@link TimestampRequest }
     * 
     */
    public TimestampRequest createTimestampRequest() {
        return new TimestampRequest();
    }

    /**
     * Create an instance of {@link MessageImprint }
     * 
     */
    public MessageImprint createMessageImprint() {
        return new MessageImprint();
    }

    /**
     * Create an instance of {@link TimestampResponse }
     * 
     */
    public TimestampResponse createTimestampResponse() {
        return new TimestampResponse();
    }

    /**
     * Create an instance of {@link HashRequest }
     * 
     */
    public HashRequest createHashRequest() {
        return new HashRequest();
    }

    /**
     * Create an instance of {@link HashResponse }
     * 
     */
    public HashResponse createHashResponse() {
        return new HashResponse();
    }

    /**
     * Create an instance of {@link LatestVersionRequest }
     * 
     */
    public LatestVersionRequest createLatestVersionRequest() {
        return new LatestVersionRequest();
    }

    /**
     * Create an instance of {@link LatestVersionResponse }
     * 
     */
    public LatestVersionResponse createLatestVersionResponse() {
        return new LatestVersionResponse();
    }

    /**
     * Create an instance of {@link Xpath.NamespaceMapping }
     * 
     */
    public Xpath.NamespaceMapping createXpathNamespaceMapping() {
        return new Xpath.NamespaceMapping();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Xpath }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Xpath }{@code >}
     */
    @XmlElementDecl(namespace = "urn:de:procilon:tresor:1.2", name = "xPath")
    public JAXBElement<Xpath> createXPath(Xpath value) {
        return new JAXBElement<Xpath>(_XPath_QNAME, Xpath.class, null, value);
    }

}
