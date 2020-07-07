
package oasis.names.tc.dss_x._1_0.profiles.signaturepolicy.schema_;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_.SignedObjectIdentifierType;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the oasis.names.tc.dss_x._1_0.profiles.signaturepolicy.schema_ package. 
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

    private final static QName _GenerateUnderSignaturePolicy_QNAME = new QName("urn:oasis:names:tc:dss-x:1.0:profiles:SignaturePolicy:schema#", "GenerateUnderSignaturePolicy");
    private final static QName _ReturnSupportedSignaturePolicies_QNAME = new QName("urn:oasis:names:tc:dss-x:1.0:profiles:SignaturePolicy:schema#", "ReturnSupportedSignaturePolicies");
    private final static QName _UsedSignaturePolicy_QNAME = new QName("urn:oasis:names:tc:dss-x:1.0:profiles:SignaturePolicy:schema#", "UsedSignaturePolicy");
    private final static QName _VerifyUnderSignaturePolicy_QNAME = new QName("urn:oasis:names:tc:dss-x:1.0:profiles:SignaturePolicy:schema#", "VerifyUnderSignaturePolicy");
    private final static QName _ExplicitPolicies_QNAME = new QName("urn:oasis:names:tc:dss-x:1.0:profiles:SignaturePolicy:schema#", "ExplicitPolicies");
    private final static QName _PolicySignaturePair_QNAME = new QName("urn:oasis:names:tc:dss-x:1.0:profiles:SignaturePolicy:schema#", "PolicySignaturePair");
    private final static QName _SignaturePolicy_QNAME = new QName("urn:oasis:names:tc:dss-x:1.0:profiles:SignaturePolicy:schema#", "SignaturePolicy");
    private final static QName _SignatureIdentifier_QNAME = new QName("urn:oasis:names:tc:dss-x:1.0:profiles:SignaturePolicy:schema#", "SignatureIdentifier");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: oasis.names.tc.dss_x._1_0.profiles.signaturepolicy.schema_
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SignaturePolicyDetailsType }
     * 
     */
    public SignaturePolicyDetailsType createSignaturePolicyDetailsType() {
        return new SignaturePolicyDetailsType();
    }

    /**
     * Create an instance of {@link SupportedSignaturePolicies }
     * 
     */
    public SupportedSignaturePolicies createSupportedSignaturePolicies() {
        return new SupportedSignaturePolicies();
    }

    /**
     * Create an instance of {@link VerifyUnderSignaturePolicyType }
     * 
     */
    public VerifyUnderSignaturePolicyType createVerifyUnderSignaturePolicyType() {
        return new VerifyUnderSignaturePolicyType();
    }

    /**
     * Create an instance of {@link PolicySignaturePairsType }
     * 
     */
    public PolicySignaturePairsType createPolicySignaturePairsType() {
        return new PolicySignaturePairsType();
    }

    /**
     * Create an instance of {@link PolicySignaturePairType }
     * 
     */
    public PolicySignaturePairType createPolicySignaturePairType() {
        return new PolicySignaturePairType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SignaturePolicyDetailsType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SignaturePolicyDetailsType }{@code >}
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:dss-x:1.0:profiles:SignaturePolicy:schema#", name = "GenerateUnderSignaturePolicy")
    public JAXBElement<SignaturePolicyDetailsType> createGenerateUnderSignaturePolicy(SignaturePolicyDetailsType value) {
        return new JAXBElement<SignaturePolicyDetailsType>(_GenerateUnderSignaturePolicy_QNAME, SignaturePolicyDetailsType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:dss-x:1.0:profiles:SignaturePolicy:schema#", name = "ReturnSupportedSignaturePolicies")
    public JAXBElement<Object> createReturnSupportedSignaturePolicies(Object value) {
        return new JAXBElement<Object>(_ReturnSupportedSignaturePolicies_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SignaturePolicyDetailsType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SignaturePolicyDetailsType }{@code >}
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:dss-x:1.0:profiles:SignaturePolicy:schema#", name = "UsedSignaturePolicy")
    public JAXBElement<SignaturePolicyDetailsType> createUsedSignaturePolicy(SignaturePolicyDetailsType value) {
        return new JAXBElement<SignaturePolicyDetailsType>(_UsedSignaturePolicy_QNAME, SignaturePolicyDetailsType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VerifyUnderSignaturePolicyType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link VerifyUnderSignaturePolicyType }{@code >}
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:dss-x:1.0:profiles:SignaturePolicy:schema#", name = "VerifyUnderSignaturePolicy")
    public JAXBElement<VerifyUnderSignaturePolicyType> createVerifyUnderSignaturePolicy(VerifyUnderSignaturePolicyType value) {
        return new JAXBElement<VerifyUnderSignaturePolicyType>(_VerifyUnderSignaturePolicy_QNAME, VerifyUnderSignaturePolicyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PolicySignaturePairsType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PolicySignaturePairsType }{@code >}
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:dss-x:1.0:profiles:SignaturePolicy:schema#", name = "ExplicitPolicies")
    public JAXBElement<PolicySignaturePairsType> createExplicitPolicies(PolicySignaturePairsType value) {
        return new JAXBElement<PolicySignaturePairsType>(_ExplicitPolicies_QNAME, PolicySignaturePairsType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PolicySignaturePairType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PolicySignaturePairType }{@code >}
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:dss-x:1.0:profiles:SignaturePolicy:schema#", name = "PolicySignaturePair")
    public JAXBElement<PolicySignaturePairType> createPolicySignaturePair(PolicySignaturePairType value) {
        return new JAXBElement<PolicySignaturePairType>(_PolicySignaturePair_QNAME, PolicySignaturePairType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SignaturePolicyDetailsType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SignaturePolicyDetailsType }{@code >}
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:dss-x:1.0:profiles:SignaturePolicy:schema#", name = "SignaturePolicy")
    public JAXBElement<SignaturePolicyDetailsType> createSignaturePolicy(SignaturePolicyDetailsType value) {
        return new JAXBElement<SignaturePolicyDetailsType>(_SignaturePolicy_QNAME, SignaturePolicyDetailsType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SignedObjectIdentifierType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SignedObjectIdentifierType }{@code >}
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:dss-x:1.0:profiles:SignaturePolicy:schema#", name = "SignatureIdentifier")
    public JAXBElement<SignedObjectIdentifierType> createSignatureIdentifier(SignedObjectIdentifierType value) {
        return new JAXBElement<SignedObjectIdentifierType>(_SignatureIdentifier_QNAME, SignedObjectIdentifierType.class, null, value);
    }

}
