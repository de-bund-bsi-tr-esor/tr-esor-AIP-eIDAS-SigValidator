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
package de.bund.bsi.tresor.xaip.validator.api.control;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.Set;

import org.apache.xml.security.Init;
import org.apache.xml.security.c14n.CanonicalizationException;
import org.apache.xml.security.c14n.Canonicalizer;
import org.apache.xml.security.c14n.InvalidCanonicalizerException;
import org.w3c.dom.Node;

/**
 * Representation of all possible canonicalization methods
 * 
 * @author wolffs
 */
public class Canonicalization
{
    static
    {
        // required before using apache xml canonicalizer
        Init.init();
    }
    
    static final Set<String> VALID_C14N = Set.of(
            Canonicalizer.ALGO_ID_C14N_EXCL_OMIT_COMMENTS,
            Canonicalizer.ALGO_ID_C14N_EXCL_WITH_COMMENTS,
            Canonicalizer.ALGO_ID_C14N_OMIT_COMMENTS,
            Canonicalizer.ALGO_ID_C14N_WITH_COMMENTS );
    
    /**
     * Checking if the c14n url is supported
     * 
     * @param url
     *            the c14n url
     * @return if the c14n url is supported
     */
    public static boolean isValidCanonicalization( String url )
    {
        return VALID_C14N.contains( url );
    }
    
    /**
     * Canonicalizing the node using the provided c14nUrl
     * 
     * @param node
     *            the node to canonicalize
     * @param c14nUrl
     *            the c14nUrl
     * @return the canonicalized data as a stream
     * @throws InvalidCanonicalizerException
     *             when the c14nUrl is not supported
     * @throws CanonicalizationException
     *             when an error occurs during the canonicalization
     * @throws IOException
     *             when an io error occurs
     */
    public static ByteArrayInputStream canonicalize( Node node, Optional<String> c14nUrl )
            throws InvalidCanonicalizerException, CanonicalizationException, IOException
    {
        String c14nMethod = c14nUrl.orElseThrow( () -> new IllegalStateException( "no data found" ) );
        Canonicalizer instance = Canonicalizer.getInstance( c14nMethod );
        try ( ByteArrayOutputStream out = new ByteArrayOutputStream() )
        {
            instance.canonicalizeSubtree( node, out );
            
            return new ByteArrayInputStream( out.toByteArray() );
        }
    }
}