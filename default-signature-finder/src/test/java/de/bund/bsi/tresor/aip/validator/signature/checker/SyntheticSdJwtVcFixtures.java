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

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;
import java.security.cert.X509Certificate;
import java.util.Base64;
import java.util.Date;

import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;

/**
 * Builds structurally spec-conformant SD-JWT-VC (ETSI TS 119 472-1) test data from an ephemeral,
 * self-signed RSA keypair - no licensed ETSI/vendor plugtest corpus data involved.
 * <p>
 * The resulting documents are only suited to exercise {@link EAAChecker}'s structural detection
 * (format, {@code category} claim). They carry no trust-anchored certificate and MUST NOT be used
 * to assert cryptographic or legal validity - that remains the SAK's responsibility.
 */
final class SyntheticSdJwtVcFixtures
{
    private SyntheticSdJwtVcFixtures()
    {
    }

    /**
     * Builds a compact, RS256-signed, zero-disclosure SD-JWT-VC with the given claims.
     *
     * @param vct
     *            the {@code vct} claim
     * @param category
     *            the {@code category} claim, or {@code null} to omit it (generic EAA)
     * @return the compact SD-JWT-VC representation, terminated with {@code ~} (zero disclosures)
     */
    static String build( String vct, String category ) throws Exception
    {
        SignedParts parts = sign( vct, "category", category );

        // trailing '~' denotes zero selective disclosures, matching real SD-JWT-VC compact form
        return parts.headerB64 + "." + parts.payloadB64 + "." + parts.signatureB64 + "~";
    }

    /**
     * Builds the same SD-JWT-VC content as {@link #build(String, String)}, but in the flattened JWS JSON serialization
     * ({@code {"payload":...,"protected":...,"signature":...}}) instead of the compact form.
     *
     * @param vct
     *            the {@code vct} claim
     * @param category
     *            the {@code category} claim, or {@code null} to omit it (generic EAA)
     * @return the flattened JWS JSON serialization of the SD-JWT-VC
     */
    static String buildJson( String vct, String category ) throws Exception
    {
        SignedParts parts = sign( vct, "category", category );

        return "{\"payload\":\"" + parts.payloadB64 + "\",\"protected\":\"" + parts.headerB64
                + "\",\"signature\":\"" + parts.signatureB64 + "\"}";
    }

    /**
     * Builds a compact SD-JWT-VC like {@link #build(String, String)}, but using the legacy {@code attestation_legal_category} claim
     * name instead of {@code category}. DSS falls back to this claim when {@code category} is absent.
     *
     * @param vct
     *            the {@code vct} claim
     * @param category
     *            the {@code attestation_legal_category} claim value
     * @return the compact SD-JWT-VC representation, terminated with {@code ~} (zero disclosures)
     */
    static String buildWithLegacyCategoryClaim( String vct, String category ) throws Exception
    {
        SignedParts parts = sign( vct, "attestation_legal_category", category );

        return parts.headerB64 + "." + parts.payloadB64 + "." + parts.signatureB64 + "~";
    }

    private static SignedParts sign( String vct, String categoryClaimName, String category ) throws Exception
    {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance( "RSA" );
        keyPairGenerator.initialize( 2048 );
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        X509Certificate certificate = selfSignedCertificate( keyPair );
        String certificateB64 = Base64.getEncoder().encodeToString( certificate.getEncoded() );

        String header = "{\"alg\":\"RS256\",\"typ\":\"dc+sd-jwt\",\"x5c\":[\"" + certificateB64 + "\"]}";

        StringBuilder payload = new StringBuilder();
        payload.append( "{\"iss\":\"https://issuer.example.test\"" );
        payload.append( ",\"iat\":" ).append( System.currentTimeMillis() / 1000 );
        payload.append( ",\"exp\":" ).append( System.currentTimeMillis() / 1000 + 31536000 );
        payload.append( ",\"vct\":\"" ).append( vct ).append( "\"" );
        if ( category != null )
        {
            payload.append( ",\"" ).append( categoryClaimName ).append( "\":\"" ).append( category ).append( "\"" );
        }
        payload.append( ",\"issuing_authority\":\"Test Authority\",\"issuing_country\":\"DE\"" );
        payload.append( ",\"given_name\":\"Erika\",\"family_name\":\"Mustermann\"" );
        payload.append( "}" );

        String headerB64 = base64Url( header.getBytes( StandardCharsets.UTF_8 ) );
        String payloadB64 = base64Url( payload.toString().getBytes( StandardCharsets.UTF_8 ) );
        String signingInput = headerB64 + "." + payloadB64;

        Signature signature = Signature.getInstance( "SHA256withRSA" );
        signature.initSign( keyPair.getPrivate() );
        signature.update( signingInput.getBytes( StandardCharsets.UTF_8 ) );
        String signatureB64 = base64Url( signature.sign() );

        return new SignedParts( headerB64, payloadB64, signatureB64 );
    }

    private static final class SignedParts
    {
        private final String headerB64;
        private final String payloadB64;
        private final String signatureB64;

        private SignedParts( String headerB64, String payloadB64, String signatureB64 )
        {
            this.headerB64 = headerB64;
            this.payloadB64 = payloadB64;
            this.signatureB64 = signatureB64;
        }
    }

    private static String base64Url( byte[] data )
    {
        return Base64.getUrlEncoder().withoutPadding().encodeToString( data );
    }

    private static X509Certificate selfSignedCertificate( KeyPair keyPair ) throws Exception
    {
        X500Name subject = new X500Name( "CN=EAAChecker Test Fixture, O=Test" );
        Date notBefore = new Date( System.currentTimeMillis() - 86400000L );
        Date notAfter = new Date( System.currentTimeMillis() + 31536000000L );
        X509v3CertificateBuilder builder = new JcaX509v3CertificateBuilder(
                subject, BigInteger.valueOf( System.nanoTime() ), notBefore, notAfter, subject, keyPair.getPublic() );
        ContentSigner signer = new JcaContentSignerBuilder( "SHA256withRSA" ).build( keyPair.getPrivate() );
        return new JcaX509CertificateConverter().getCertificate( builder.build( signer ) );
    }
}
