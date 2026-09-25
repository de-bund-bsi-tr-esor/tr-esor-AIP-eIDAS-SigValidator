/*-
 * Copyright (c) 2026
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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.junit.jupiter.api.Test;

/**
 * The positive/category-based cases use structurally spec-conformant SD-JWT-VC data synthesized by
 * {@link SyntheticSdJwtVcFixtures} (self-signed, ephemeral keys) rather than licensed ETSI/vendor
 * plugtest corpus data, which must not be committed to this repository. They only exercise
 * {@link EAAChecker}'s structural detection - not cryptographic validity, which is the SAK's job.
 */
class EAACheckerTest
{

    private EAAChecker eaaChecker = EAAChecker.INSTANCE;

    @Test
    void shouldNotDetectPlainText()
    {
        boolean result = eaaChecker.isEAAType( "this is not an attestation".getBytes( StandardCharsets.UTF_8 ) );

        assertFalse( result, "Plain text must not be detected as an EAA type" );
    }

    @Test
    void shouldNotDetectEmptyData()
    {
        boolean result = eaaChecker.isEAAType( new byte[0] );

        assertFalse( result, "Empty data must not be detected as an EAA type" );
    }

    @Test
    void shouldNotDetectUnrelatedJson()
    {
        boolean result = eaaChecker.isEAAType( "{\"hello\":\"world\"}".getBytes( StandardCharsets.UTF_8 ) );

        assertFalse( result, "A plain JSON object without JWS structure must not be detected as an EAA type" );
    }

    @Test
    void shouldDetectGenericEaaWithoutCategoryClaim() throws Exception
    {
        String sdJwtVc = SyntheticSdJwtVcFixtures.build( "urn:eudi:eaa:1", null );

        boolean result = eaaChecker.isEAAType( sdJwtVc.getBytes( StandardCharsets.UTF_8 ) );

        assertTrue( result, "An SD-JWT-VC without a category claim must be detected as a generic EAA" );
    }

    @Test
    void shouldDetectQeaaByCategoryClaim() throws Exception
    {
        String sdJwtVc = SyntheticSdJwtVcFixtures.build( "urn:eudi:eaa:1", "urn:etsi:esi:eaa:eu:qualified" );

        boolean result = eaaChecker.isEAAType( sdJwtVc.getBytes( StandardCharsets.UTF_8 ) );

        assertTrue( result, "An SD-JWT-VC with the QEAA category URN must be detected as an EAA type" );
    }

    @Test
    void shouldDetectPubEaaByCategoryClaim() throws Exception
    {
        String sdJwtVc = SyntheticSdJwtVcFixtures.build( "urn:eudi:eaa:1", "urn:etsi:esi:eaa:eu:pub" );

        boolean result = eaaChecker.isEAAType( sdJwtVc.getBytes( StandardCharsets.UTF_8 ) );

        assertTrue( result, "An SD-JWT-VC with the PubEAA category URN must be detected as an EAA type" );
    }

    @Test
    void shouldNotDetectSpecDeviatingCategoryValue() throws Exception
    {
        String sdJwtVc = SyntheticSdJwtVcFixtures.build( "urn:eudi:eaa:1", "urn:etsi:esi:eaa:eu:bogus" );

        boolean result = eaaChecker.isEAAType( sdJwtVc.getBytes( StandardCharsets.UTF_8 ) );

        assertFalse( result, "A category value matching neither known EAACategory URN must not be treated as an EAA type"
                + " (deliberate design decision, see PAMP-168 - no fallback for spec-deviating values)" );
    }

    @Test
    void shouldNotDetectDuplicateJsonKeysWithConflictingValues() throws Exception
    {
        String sdJwtVc = SyntheticSdJwtVcFixtures.build( "urn:eudi:eaa:1", null );
        String[] parts = sdJwtVc.split( "~" )[0].split( "\\." );
        String payload = new String( Base64.getUrlDecoder().decode( parts[1] ), StandardCharsets.UTF_8 );
        String tamperedPayload = payload.replaceFirst( "\\{", "{\"iss\":\"https://conflicting.example\"," );
        String tamperedPayloadB64 = Base64.getUrlEncoder().withoutPadding()
                .encodeToString( tamperedPayload.getBytes( StandardCharsets.UTF_8 ) );
        String tampered = parts[0] + "." + tamperedPayloadB64 + "." + parts[2] + "~";

        boolean result = eaaChecker.isEAAType( tampered.getBytes( StandardCharsets.UTF_8 ) );

        assertFalse( result, "An SD-JWT-VC with duplicate/conflicting JSON keys must not be detected as a valid EAA type" );
    }
}
