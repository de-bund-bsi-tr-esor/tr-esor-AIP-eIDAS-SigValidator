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

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.xml.bind.JAXB;

import org.junit.jupiter.api.Test;

import de.bund.bsi.tr_esor.xaip.XAIPType;
import de.bund.bsi.tresor.aip.validator.api.entity.ModuleContext;

/**
 * @author bendlera
 *
 */
class DefaultSignatureFinderTest
{
    /**
     * Test method for {@link de.bund.bsi.tresor.aip.validator.signature.DefaultSignatureFinder#getVendor()}.
     */
    @Test
    void testGetVendor()
    {
        assertEquals( "BSI", new DefaultSignatureFinder().getVendor() );
    }
    
    /**
     * Test method for {@link de.bund.bsi.tresor.aip.validator.signature.DefaultSignatureFinder#getVersion()}.
     */
    @Test
    void testGetVersion()
    {
        assertEquals( "1.0.0", new DefaultSignatureFinder().getVersion() );
    }
    
    @Test
    void foo() throws Exception
    {
        try ( FileOutputStream out = new FileOutputStream( new File( "/tmp/test.log" ) ) )
        {
            File testDir = new File( "/tmp/tests" );
            if ( !testDir.isDirectory() )
            {
                System.exit( -1 );
            }
            
            for ( File file : testDir.listFiles() )
            {
                log( file.getName(), out );
                
                XAIPType xaip = JAXB.unmarshal( new FileInputStream( file ), XAIPType.class );
                
                DefaultSignatureFinder finder = new DefaultSignatureFinder();
                Map<String, Set<String>> finderResult = finder.findSignatures( new ModuleContext(), xaip );
                
                for ( Entry<String, Set<String>> entry : finderResult.entrySet() )
                {
                    log( entry.getKey() + " :", out );
                    for ( String elem : entry.getValue() )
                    {
                        log( "- " + elem, out );
                    }
                }
                
                log( "", out );
            }
        }
    }
    
    void log( String msg, OutputStream out ) throws IOException
    {
        msg = msg + "\r\n";
        out.write( msg.getBytes( StandardCharsets.UTF_8 ) );
    }
}
