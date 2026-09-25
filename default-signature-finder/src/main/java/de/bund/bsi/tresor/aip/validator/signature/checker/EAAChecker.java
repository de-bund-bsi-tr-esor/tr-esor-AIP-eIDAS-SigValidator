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

import java.util.Arrays;

import de.bund.bsi.tresor.aip.validator.api.control.ModuleLogger;
import eu.europa.esig.dss.attestation.common.validation.DefaultAttestationDocumentAnalyzer;
import eu.europa.esig.dss.enumerations.EAACategory;
import eu.europa.esig.dss.model.InMemoryDocument;
import eu.europa.esig.dss.model.attestation.claim.VerifiedClaimString;
import eu.europa.esig.dss.spi.attestation.Attestation;
import eu.europa.esig.dss.spi.attestation.AttestationPayload;
import eu.europa.esig.dss.spi.validation.analyzer.attestation.AttestationDocumentAnalyzer;

/**
 * Idenifies QEAA, EAA and PubEAA in SD-JWT-VC or mdoc/CBOR representation, delegating the parsing and {@code category} claim resolution
 * to esig-dss (dss-sd-jwt/dss-mdoc, format auto-detected via the {@code AttestationDocumentValidatorFactory} SPI).
 */
public enum EAAChecker
{
    INSTANCE;

    /**
     * Checking if the provided data is an electronic attestation of attributes (EAA, QEAA or PubEAA) in SD-JWT-VC or mdoc/CBOR
     * representation
     *
     * @param data
     *            the data to check
     * @return true if the data is an EAA type
     */
    public boolean isEAAType( byte[] data )
    {
        boolean isEAA = false;
        try
        {
            AttestationDocumentAnalyzer analyzer = DefaultAttestationDocumentAnalyzer.fromDocument( new InMemoryDocument( data ) );
            isEAA = analyzer.getAttestationPresentation().getAttestations().stream()
                    .map( Attestation::getPayload )
                    .anyMatch( this::isElectronicAttestationOfAttributes );
        }
        catch ( Exception e )
        {
            // not an eaa type
        }

        if ( !isEAA )
        {
            ModuleLogger.verbose( "data is no eaa-type" );
        }
        else
        {
            ModuleLogger.verbose( "found eaa-type" );
        }

        return isEAA;
    }

    // an EAA has no category claim; a QEAA/PubEAA has one of the two known EAACategory URNs. Any other, spec-deviating category value
    // is deliberately not treated as a match, so that detection stays strictly conformant to the category URNs defined in
    // ETSI TS 119 472-1 instead of guessing at non-standard values.
    boolean isElectronicAttestationOfAttributes( AttestationPayload payload )
    {
        if ( payload == null )
        {
            return false;
        }

        VerifiedClaimString category = payload.getCategory();
        if ( category == null || category.isNullOrEmpty() )
        {
            return true;
        }

        String value = category.getValueAsString();
        return Arrays.stream( EAACategory.values() ).map( EAACategory::getUrn ).anyMatch( value::equals );
    }
}
