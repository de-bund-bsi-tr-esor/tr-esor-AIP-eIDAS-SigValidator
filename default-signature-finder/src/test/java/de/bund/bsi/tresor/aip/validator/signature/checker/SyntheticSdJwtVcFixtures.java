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
            payload.append( ",\"category\":\"" ).append( category ).append( "\"" );
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

        // trailing '~' denotes zero selective disclosures, matching real SD-JWT-VC compact form
        return signingInput + "." + signatureB64 + "~";
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
