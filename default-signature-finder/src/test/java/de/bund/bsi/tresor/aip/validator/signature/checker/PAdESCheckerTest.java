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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import de.bund.bsi.tresor.aip.validator.api.control.ModuleLogger;

/**
 * @author bendlera
 *
 */
class PAdESCheckerTest
{
    
    private PAdESChecker                 uut          = PAdESChecker.INSTANCE;
    
    private static ByteArrayOutputStream loggerOutput = new ByteArrayOutputStream();
    
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
    }
    
    /**
     * Test method for {@link de.bund.bsi.tresor.aip.validator.signature.checker.PAdESChecker#isPAdES(byte[])}.
     */
    @Test
    void testIsPAdES() throws IOException
    {
        try ( InputStream in = this.getClass().getResourceAsStream( "/signed.pdf" ) )
        {
            assertThat( uut.isPAdES( in.readAllBytes() ), is( true ) );
        }
    }
    
    /**
     * Test method for {@link de.bund.bsi.tresor.aip.validator.signature.checker.PAdESChecker#isPAdES(byte[])}.
     * 
     * @throws IOException
     */
    @Test
    void testIsPAdESCaseNoSignature() throws IOException
    {
        try ( InputStream in = this.getClass().getResourceAsStream( "/unsigned.pdf" ) )
        {
            assertThat( uut.isPAdES( in.readAllBytes() ), is( false ) );
        }
    }
    
    /**
     * Test method for {@link de.bund.bsi.tresor.aip.validator.signature.checker.PAdESChecker#isPAdES(byte[])}.
     */
    @Test
    void testIsPAdESCaseNoPdf()
    {
        byte[] data = "filecontent".getBytes( StandardCharsets.UTF_8 );
        assertThat( uut.isPAdES( data ), is( false ) );
    }
    
    /**
     * Test method for {@link de.bund.bsi.tresor.aip.validator.signature.checker.PAdESChecker#hasPAdESRequirements(byte[])}.
     */
    @Test
    void testHasPAdESRequirementsCaseNoPdf()
    {
        byte[] data = "%PDF-1.7".getBytes( StandardCharsets.UTF_8 );
        assertThat( uut.hasPAdESRequirements( data ), is( false ) );
    }
    
}
