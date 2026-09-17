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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

import org.junit.jupiter.api.Test;

class CredentialSectionAnalyzerTest
{

    @Test
    void shouldSubstitutePayloadIntoDetachedJws()
    {
        byte[] signature = "header..signature".getBytes( StandardCharsets.US_ASCII );
        byte[] payload = "payload".getBytes( StandardCharsets.US_ASCII );

        Optional<byte[]> result = CredentialSectionAnalyzer.reconstructDetachedJws( signature, payload );

        assertTrue( result.isPresent() );
        assertEquals( "header.payload.signature", new String( result.get(), StandardCharsets.US_ASCII ) );
    }

    @Test
    void shouldPreserveDisclosureSuffixWhenSubstitutingPayload()
    {
        byte[] signature = "header..signature~disclosure1~disclosure2~".getBytes( StandardCharsets.US_ASCII );
        byte[] payload = "payload".getBytes( StandardCharsets.US_ASCII );

        Optional<byte[]> result = CredentialSectionAnalyzer.reconstructDetachedJws( signature, payload );

        assertTrue( result.isPresent() );
        assertEquals( "header.payload.signature~disclosure1~disclosure2~", new String( result.get(), StandardCharsets.US_ASCII ) );
    }

    @Test
    void shouldNotSubstituteWhenPayloadSegmentIsNotEmpty()
    {
        byte[] signature = "header.existingPayload.signature".getBytes( StandardCharsets.US_ASCII );
        byte[] payload = "payload".getBytes( StandardCharsets.US_ASCII );

        Optional<byte[]> result = CredentialSectionAnalyzer.reconstructDetachedJws( signature, payload );

        assertFalse( result.isPresent(), "A JWS which already carries its own payload must not be modified" );
    }

    @Test
    void shouldNotSubstituteForNonJwsSignatures()
    {
        byte[] signature = { 0x30, (byte) 0x82, 0x01, 0x02 }; // arbitrary DER-like binary, not JWS-shaped
        byte[] payload = "payload".getBytes( StandardCharsets.US_ASCII );

        Optional<byte[]> result = CredentialSectionAnalyzer.reconstructDetachedJws( signature, payload );

        assertFalse( result.isPresent() );
    }
}
