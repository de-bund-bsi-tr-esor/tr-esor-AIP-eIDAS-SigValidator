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
package de.bund.bsi.tresor.aip.validator.signature.checker;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSObject;
import org.apache.pdfbox.io.RandomAccessBuffer;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;

import de.bund.bsi.tresor.aip.validator.api.control.ModuleLogger;

/**
 * Singleton to check data for possible PAdES.
 * 
 * @author wolffs
 */
public enum PAdESChecker
{
    INSTANCE;
    
    private final byte[] pdfMagicNumber = "%PDF".getBytes( StandardCharsets.US_ASCII );
    
    /**
     * Checking if the data could contain a PAdES by validating if the data is a pdf document containing the dictionaries <code>SIG</code>
     * and <code>DSS</code>.
     * 
     * @param data
     *            the document data
     * @return if the data could contain a PAdES
     */
    public boolean isPAdES( byte[] data )
    {
        boolean isPAdES = hasPdfMagicNumber( data ) && hasPAdESRequirements( data );
        ModuleLogger.verbose( isPAdES ? "data is PAdES" : "data is not PAdES" );
        
        return isPAdES;
    }
    
    /**
     * Checking if the data starts with the magic number which defines a PDF. This magic number are equal to the characters
     * <code>%PDF</code>.
     * 
     * @param data
     *            the document data
     * @return if the data starts with the magic number for pdf
     */
    boolean hasPdfMagicNumber( byte[] data )
    {
        byte[] slice = Arrays.copyOfRange( data, 0, pdfMagicNumber.length );
        
        return Arrays.equals( slice, pdfMagicNumber );
    }
    
    /**
     * Parsing the provided data as a pdf document and checking if the dictionaries <code>SIG</code> or <code>DSS</code> are present. Having
     * one of the dictionaries indicates a possible PAdES signature.
     * 
     * @param data
     *            the document data
     * @return if the data could contain a PAdES
     */
    boolean hasPAdESRequirements( byte[] data )
    {
        boolean isPAdES = false;
        try
        {
            PDFParser parser = new PDFParser( new RandomAccessBuffer( data ) );
            parser.parse();
            
            try ( PDDocument pdf = parser.getPDDocument() )
            {
                COSDocument document = pdf.getDocument();
                List<COSObject> dssObjects = document.getObjectsByType( "DSS" );
                List<COSObject> sigObjects = document.getObjectsByType( COSName.SIG );
                
                isPAdES = !dssObjects.isEmpty() || !sigObjects.isEmpty();
            }
            
        }
        catch ( IOException e )
        {
            // ModuleLogger.verbose( "data is not PAdES", e );
        }
        
        ModuleLogger.verbose( isPAdES ? "data is PAdES" : "data is not PAdES" );
        
        return isPAdES;
    }
}
