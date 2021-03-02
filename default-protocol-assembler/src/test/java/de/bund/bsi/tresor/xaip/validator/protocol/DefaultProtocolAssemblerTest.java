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
package de.bund.bsi.tresor.xaip.validator.protocol;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import de.bund.bsi.tresor.aip.validator.protocol.DefaultProtocolAssembler;

/**
 * @author bendlera
 *
 */
class DefaultProtocolAssemblerTest
{
    
    /**
     * Test method for {@link de.bund.bsi.tresor.aip.validator.protocol.DefaultProtocolAssembler#getVendor()}.
     */
    @Test
    void testGetVendor()
    {
        assertEquals( "BSI", new DefaultProtocolAssembler().getVendor() );
    }
    
    /**
     * Test method for {@link de.bund.bsi.tresor.aip.validator.protocol.DefaultProtocolAssembler#getVersion()}.
     */
    @Test
    void testGetVersion()
    {
        assertEquals( "1.0.0", new DefaultProtocolAssembler().getVersion() );
    }
    
}
