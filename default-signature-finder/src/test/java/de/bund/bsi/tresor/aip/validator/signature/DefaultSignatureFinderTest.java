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

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyOrNullString;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author bendlera
 *
 */
class DefaultSignatureFinderTest
{
    private DefaultSignatureFinder uut;
    
    @BeforeEach
    public void init()
    {
        uut = new DefaultSignatureFinder();
    }
    
    /**
     * Test method for {@link de.bund.bsi.tresor.aip.validator.signature.DefaultSignatureFinder#getVendor()}.
     */
    @Test
    void testGetVendor()
    {
        assertThat( uut.getVendor(), is( not( emptyOrNullString() ) ) );
        assertThat( uut.getVendor(), is( equalTo( "BSI" ) ) );
    }
    
    /**
     * Test method for {@link de.bund.bsi.tresor.aip.validator.signature.DefaultSignatureFinder#getVersion()}.
     */
    @Test
    void testGetVersion()
    {
        assertThat( uut.getVersion(), is( not( emptyOrNullString() ) ) );
    }
}
