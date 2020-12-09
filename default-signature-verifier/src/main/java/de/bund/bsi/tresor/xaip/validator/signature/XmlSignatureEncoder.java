package de.bund.bsi.tresor.xaip.validator.signature;

import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Node;

import oasis.names.tc.dss._1_0.core.schema.Base64Signature;
import oasis.names.tc.dss._1_0.core.schema.SignatureObject;

/**
 * @author wolffs
 */
public class XmlSignatureEncoder
{
    private static final String        PLAIN_XML_DATA = "//esor:dataObject[@dataObjectID=''{0}'']/esor:xmlData/*";
    private static final String        XML_SIG_OBJ    = "//esor:credential[@credentialID=''{0}'']/dss:SignatureObject/ds:Signature";
    
    private static Map<String, String> nsContext      = new HashMap<>();
    static
    {
        nsContext.put( "ds", "http://www.w3.org/2000/09/xmldsig#" );
        nsContext.put( "dss", "urn:oasis:names:tc:dss:1.0:core:schema" );
        nsContext.put( "esor", "http://www.bsi.bund.de/tr-esor/xaip/1.2" );
        
        DocumentFactory.getInstance().setXPathNamespaceURIs( nsContext );
    }
    
    /**
     * Searching if the dataObject with the provided id has any plain xml data and b64-encoding the plain xml if present
     * 
     * @param document
     *            the xaip document
     * @param id
     *            the dataObjectId
     * @return the b64encoded plain xml data if the dataObjectId exists with plain xml data
     */
    public static Optional<byte[]> b64EncodeDataObjectPlainXml( Document document, String id )
    {
        Node node = document.selectSingleNode( MessageFormat.format( PLAIN_XML_DATA, id ) );
        
        return Optional.ofNullable( node )
                .map( Node::asXML )
                .map( str -> str.getBytes( StandardCharsets.UTF_8 ) );
    }
    
    /**
     * Searching if the credential with the provided id has any signature and b64-encoding the plain xml if present
     * 
     * @param document
     *            the xaip document
     * @param id
     *            the dataObjectId
     * @return the b64encoded plain xml data if the credential exists and has a signature
     */
    public static Optional<byte[]> b64EncodeCredentialXmlSignature( Document document, String id )
    {
        Node node = document.selectSingleNode( MessageFormat.format( XML_SIG_OBJ, id ) );
        
        return Optional.ofNullable( node )
                .map( Node::asXML )
                .map( str -> str.getBytes( StandardCharsets.UTF_8 ) );
    }
    
    /**
     * When the credential with the provided id has a signature, this signature will be b64 encoded and be written as base64 signature into
     * a new signatureObject which will be returned.<br/>
     * This prevents breaking any xml signature/hash which is being build over the signature object by jaxb.
     * 
     * @param document
     *            the xaip document
     * @param id
     *            the credential id
     * @return the transformed signatureObject when a signature exists unter the provided credentialId
     */
    public static Optional<SignatureObject> b64EncodeCredentialXmlSignatureObject( Document document, String id )
    {
        return b64EncodeCredentialXmlSignature( document, id )
                .map( b64 -> {
                    Base64Signature b64XmlSig = new Base64Signature();
                    b64XmlSig.setType( "application/octet-stream" );
                    b64XmlSig.setValue( b64 );
                    
                    return b64XmlSig;
                } )
                .map( b64XmlSig -> {
                    SignatureObject xmlSig = new SignatureObject();
                    xmlSig.setBase64Signature( b64XmlSig );
                    
                    return xmlSig;
                } );
    }
}
