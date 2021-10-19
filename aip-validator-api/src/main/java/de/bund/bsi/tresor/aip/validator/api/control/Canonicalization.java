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
package de.bund.bsi.tresor.aip.validator.api.control;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Set;

import org.apache.xml.security.Init;
import org.apache.xml.security.c14n.CanonicalizationException;
import org.apache.xml.security.c14n.Canonicalizer;
import org.apache.xml.security.c14n.InvalidCanonicalizerException;
import org.apache.xml.security.parser.XMLParserException;

import lombok.Getter;
import lombok.Setter;

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
    
    @Getter
    @Setter
    // using static reference since this will only set by packageValidator, uses have to be after this validator
    public static String            c14nMethod = Canonicalizer.ALGO_ID_C14N_EXCL_OMIT_COMMENTS;
    
    public static final Set<String> VALID_C14N = Set.of(
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
    
    public static byte[] canonicalize( byte[] input )
            throws XMLParserException, CanonicalizationException, InvalidCanonicalizerException, IOException
    {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Canonicalizer.getInstance( c14nMethod ).canonicalize( input, out, true );
        
        return out.toByteArray();
    }
}