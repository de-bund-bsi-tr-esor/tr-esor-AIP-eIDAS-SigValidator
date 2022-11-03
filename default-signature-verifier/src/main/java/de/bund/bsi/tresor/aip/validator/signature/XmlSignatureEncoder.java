/*-
 * Copyright (c) 2020
 * Federal Office for Information Security (BSI),
 * Godesberger Allee 185-189,
 * 53175 Bonn, Germany,
 * phone: +49 228 99 9582-0,
 * fax: +49 228 99 9582-5400,
 * e-mail: bsi@bsi.bund.de
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.bund.bsi.tresor.aip.validator.signature;

import static java.util.Objects.nonNull;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.commons.lang3.tuple.Pair;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentFactory;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.xml.sax.SAXException;

import de.bund.bsi.tresor.aip.validator.api.control.ModuleLogger;
import oasis.names.tc.dss._1_0.core.schema.Base64Signature;
import oasis.names.tc.dss._1_0.core.schema.SignatureObject;

/**
 * @author wolffs
 */
public class XmlSignatureEncoder
{
    private static final String           PLAIN_XML_DATA = "//esor:dataObject[@dataObjectID=''{0}'']/esor:xmlData/*";
    private static final String           XML_SIG_OBJ    = "//esor:credential[@credentialID=''{0}'']/dss:SignatureObject/ds:Signature";
    
    private static final SAXParserFactory factory        = SAXParserFactory.newInstance();
    public static Map<String, String>     nsContext      = new HashMap<>();
    static
    {
        nsContext.put( "ds", "http://www.w3.org/2000/09/xmldsig#" );
        nsContext.put( "dss", "urn:oasis:names:tc:dss:1.0:core:schema" );
        nsContext.put( "esor", "http://www.bsi.bund.de/tr-esor/xaip" );
        
        DocumentFactory.getInstance().setXPathNamespaceURIs( nsContext );
    }
    
    /**
     * Extracting the raw xml bytes of the object with the provided id without changing any byte of the potential signature
     * 
     * @param document
     *            the raw xaip
     * @param id
     *            the dataObjectId or metaDataObjectId
     * @return any xmlBytes if present
     * @throws IOException
     *             when reading the raw xaip failed
     * @throws SAXException
     *             when the saxparser creation failed
     * @throws ParserConfigurationException
     *             when the saxparser creation failed
     */
    public static Optional<byte[]> b64EncodeDataObjectPlainXml( byte[] document, String id )
            throws IOException, SAXException, ParserConfigurationException
    {
        Optional<byte[]> result = Optional.empty();
        StringBuilder builder = new StringBuilder();
        
        factory.setNamespaceAware( true );
        
        SAXParser saxParser = factory.newSAXParser();
        XMLDataObjectParser test = new XMLDataObjectParser( id );
        
        saxParser.parse( new ByteArrayInputStream( document ), test );
        
        Pair<Integer, Integer> end = test.getEnd();
        Pair<Integer, Integer> start = test.getStart();
        
        if ( start == null || end == null )
        {
            ModuleLogger.verbose( "no embedded sig in xml found" );
            // since no embedded xmlSig was found, return empty optional to use regular dataObjectContent in verifier
            
            return Optional.empty();
        }
        
        try ( Scanner scanner = new Scanner( new ByteArrayInputStream( document ) ) )
        {
            String line;
            int currentLineNumber = 1;
            Pattern pattern = Pattern.compile( ".*(?:\r\n|[\n\r])|.+$" );
            while ( nonNull( line = scanner.findWithinHorizon( pattern, 0 ) ) )
            {
                if ( currentLineNumber > end.getKey() )
                {
                    break;
                }
                else if ( currentLineNumber >= start.getKey() )
                {
                    if ( currentLineNumber == start.getKey() )
                    {
                        line = line.substring( start.getValue() - 1 );
                    }
                    else if ( currentLineNumber == end.getKey() )
                    {
                        line = line.substring( 0, end.getValue() - 1 );
                        line = line.substring( 0, line.lastIndexOf( "<" ) );
                    }
                    
                    builder.append( line );
                }
                
                currentLineNumber++;
            }
            
            result = Optional.of( builder.toString().getBytes( StandardCharsets.UTF_8 ) );
        }
        
        return result;
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
     * @param rawXaip
     *            the raw xaip file
     * @param id
     *            the credential id
     * @return the transformed signatureObject when a signature exists unter the provided credentialId
     * @throws DocumentException
     *             when the saxparser could not read the raw xaip
     */
    public static Optional<SignatureObject> b64EncodeCredentialXmlSignatureObject( byte[] rawXaip, String id ) throws DocumentException
    {
        Document document = new SAXReader().read( new ByteArrayInputStream( rawXaip ) );
        
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
