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
package de.bund.bsi.tresor.xaip.validator.signature.checker;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import de.bund.bsi.tresor.xaip.validator.api.control.ModuleLogger;

/**
 * @author bendlera
 *
 */
class PAdESCheckerTest
{
    
    private static ByteArrayOutputStream loggerOutput = new ByteArrayOutputStream();
    private static byte[]                signedPdfFile;
    private static byte[]                unsignedPdfFile;
    
    /**
     * Loads test data.
     * 
     * @throws java.lang.Exception
     *             loading fails
     */
    @BeforeAll
    static void setUpBeforeClass() throws Exception
    {
        ModuleLogger.initConfig( true, loggerOutput );
        
        signedPdfFile = Files
                .readAllBytes( Paths.get( PAdESCheckerTest.class.getResource( "/signed.pdf" ).toURI() ) );
        unsignedPdfFile = Files
                .readAllBytes( Paths.get( PAdESCheckerTest.class.getResource( "/unsigned.pdf" ).toURI() ) );
    }
    
    /**
     * Test method for {@link de.bund.bsi.tresor.xaip.validator.signature.checker.PAdESChecker#isPAdES(byte[])}.
     */
    @Test
    void testIsPAdES()
    {
        assertTrue( PAdESChecker.INSTANCE.isPAdES( signedPdfFile ) );
    }
    
    /**
     * Test method for {@link de.bund.bsi.tresor.xaip.validator.signature.checker.PAdESChecker#isPAdES(byte[])}.
     */
    @Test
    void testIsPAdESCaseNoSignature()
    {
        assertFalse( PAdESChecker.INSTANCE.isPAdES( unsignedPdfFile ) );
    }
    
    /**
     * Test method for {@link de.bund.bsi.tresor.xaip.validator.signature.checker.PAdESChecker#isPAdES(byte[])}.
     */
    @Test
    void testIsPAdESCaseNoPdf()
    {
        assertFalse( PAdESChecker.INSTANCE.isPAdES( "filecontent".getBytes( StandardCharsets.UTF_8 ) ) );
    }
    
    /**
     * Test method for {@link de.bund.bsi.tresor.xaip.validator.signature.checker.PAdESChecker#hasPAdESRequirements(byte[])}.
     */
    @Test
    void testHasPAdESRequirementsCaseNoPdf()
    {
        assertFalse( PAdESChecker.INSTANCE.hasPAdESRequirements( "%PDF-1.7".getBytes( StandardCharsets.UTF_8 ) ) );
    }
    
}
