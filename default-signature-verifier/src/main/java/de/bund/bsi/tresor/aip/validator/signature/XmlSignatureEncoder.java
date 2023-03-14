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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.commons.lang3.tuple.Pair;
import org.bouncycastle.util.Arrays;
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
    private static final String           PLAIN_XML_DATA    = "//esor:dataObject[@dataObjectID=''{0}'']/esor:xmlData/*";
    private static final String           XML_SIG_OBJ       = "//esor:credential[@credentialID=''{0}'']/dss:SignatureObject/ds:Signature";
    
    private static final Pattern          LINE_PATTERN      = Pattern.compile( ".*(?:\r\n|[\n\r])|.+$" );
    private static final Pattern          CLOSING_TAG_START = Pattern.compile( ".*<" );
    
    private static final SAXParserFactory factory           = SAXParserFactory.newInstance();
    public static Map<String, String>     nsContext         = new HashMap<>();
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
        factory.setNamespaceAware( true );
        
        SAXParser saxParser = factory.newSAXParser();
        XMLDataObjectParser parser = new XMLDataObjectParser( id );
        
        saxParser.parse( new ByteArrayInputStream( document ), parser );
        
        Pair<Integer, Integer> end = parser.getEnd();
        Pair<Integer, Integer> start = parser.getStart();
        
        if ( start == null || end == null )
        {
            // since no embedded xmlSig was found, return empty optional to use regular dataObjectContent in verifier
            ModuleLogger.verbose( "no embedded sig in xml found" );
            
            return Optional.empty();
        }
        
        return findPlainXmlData( document, start, end );
    }
    
    /**
     * Retrieving the bytes of the xmlData from the document by using the provided startTag and endTag positions to determine the location
     * of the data inside the document.
     * 
     * @param document
     *            the whole aip document
     * @param startTag
     *            start of the xmlData tag
     * @param endTag
     *            end of the xmlData tag
     * @return byte[] of the xmlData
     * @throws IOException
     *             when data cannot be read properly
     */
    static Optional<byte[]> findPlainXmlData( byte[] document, Pair<Integer, Integer> startTag, Pair<Integer, Integer> endTag )
            throws IOException
    {
        Optional<byte[]> result = Optional.empty();
        try ( ByteArrayInputStream in = new ByteArrayInputStream( document );
                Scanner scanner = new Scanner( in ) )
        {
            int from = 0;
            int until = 0;
            int currentLineNumber = 1;
            Matcher line = LINE_PATTERN.matcher( new ByteCharSequence( document ) );
            
            while ( line.find() )
            {
                if ( currentLineNumber > endTag.getKey() )
                {
                    break;
                }
                else if ( currentLineNumber >= startTag.getKey() )
                {
                    if ( currentLineNumber == startTag.getKey() )
                    {
                        from = line.start() + startTag.getValue() - 1;
                    }
                    else if ( currentLineNumber == endTag.getKey() )
                    {
                        until = line.start() + endTag.getValue();
                        
                        byte[] xmlData = Arrays.copyOfRange( document, line.start(), until );
                        Matcher closingTagStart = CLOSING_TAG_START.matcher( new ByteCharSequence( xmlData ) );
                        
                        if ( !closingTagStart.find() )
                        {
                            ModuleLogger.verbose( "could not properly extract plain embedded xmlsig" );
                            return Optional.empty();
                        }
                        
                        // end of XMLDataObjectParser is the position after closing tag so the closing tag has to be removed from the
                        // result and use +1 to exclude the '<' character from match
                        xmlData = Arrays.copyOfRange( document, from, until - (xmlData.length - closingTagStart.end() + 1) );
                        result = Optional.of( xmlData );
                    }
                }
                currentLineNumber++;
            }
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
