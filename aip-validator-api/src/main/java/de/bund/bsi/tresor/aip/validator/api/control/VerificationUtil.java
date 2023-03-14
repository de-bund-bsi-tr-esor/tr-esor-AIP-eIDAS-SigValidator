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
package de.bund.bsi.tresor.aip.validator.api.control;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.io.IOUtils;
import org.apache.xml.security.c14n.CanonicalizationException;
import org.apache.xml.security.c14n.InvalidCanonicalizerException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.io.Streams;

import de.bund.bsi.tr_esor.xaip.CheckSumType;
import de.bund.bsi.tresor.aip.validator.api.entity.DefaultResult;
import de.bund.bsi.tresor.aip.validator.api.entity.DefaultResult.Builder;
import de.bund.bsi.tresor.aip.validator.api.entity.DefaultResult.Minor;
import de.bund.bsi.tresor.aip.validator.api.entity.aip.DigestAlgorithm;
import oasis.names.tc.dss._1_0.core.schema.Result;
import oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_.VerificationResultType;

/**
 * Utility class for the xaip verification
 * 
 * @author wolffs
 */
public class VerificationUtil
{
    /**
     * Transforming a {@link Result} into a {@link VerificationResultType}
     * 
     * @param result
     *            the result
     * @return the verificationResult
     */
    public static VerificationResultType verificationResult( Result result )
    {
        VerificationResultType verificationResult = new VerificationResultType();
        verificationResult.setResultMajor( result.getResultMajor() );
        verificationResult.setResultMinor( result.getResultMinor() );
        verificationResult.setResultMessage( result.getResultMessage() );
        
        return verificationResult;
    }
    
    /**
     * Creating the checksum of the provided content against the given checksum and returns a verificationResult
     * 
     * @param content
     *            the content
     * @param checksum
     *            the checksum to validate against
     * @param applyC14n
     *            if the c14n of packageHeader for the xaip should be applied
     * @return the verification result
     */
    public static VerificationResultType verifyChecksum( InputStream content, CheckSumType checksum, boolean applyC14n )
    {
        Builder result = DigestAlgorithm.fromXmlSyntax( checksum.getCheckSumAlgorithm() )
                .map( DigestAlgorithm::getJavaName )
                .map( digestAlg -> {
                    Builder builder = DefaultResult.invalid();
                    try
                    {
                        byte[] canonXml = applyC14n ? Canonicalization.canonicalize( IOUtils.toByteArray( content ) )
                                : IOUtils.toByteArray( content );
                        MessageDigest md = MessageDigest.getInstance( digestAlg, new BouncyCastleProvider() );
                        try ( ByteArrayInputStream in = new ByteArrayInputStream( canonXml );
                                DigestInputStream digestIn = new DigestInputStream( in, md ) )
                        {
                            Streams.drain( digestIn );
                            byte[] digest = digestIn.getMessageDigest().digest();
                            byte[] expectedDigest = checksum.getCheckSum();
                            
                            if ( org.bouncycastle.util.Arrays.constantTimeAreEqual( expectedDigest, digest ) )
                            {
                                builder = DefaultResult.valid();
                            }
                            else
                            {
                                builder.minor( Minor.CHECKSUM_INVALID );
                            }
                        }
                    }
                    catch ( CanonicalizationException | InvalidCanonicalizerException e )
                    {
                        builder.minor( Minor.UNKNOWN_C14N_METHOD );
                    }
                    catch ( IOException e )
                    {
                        builder.minor( Minor.NO_DATA_ACCESS_WARNING );
                    }
                    catch ( NoSuchAlgorithmException e )
                    {
                        builder.minor( Minor.CHECKSUM_ALG_NOT_SUPPORTED );
                    }
                    
                    return builder;
                } ).orElse( DefaultResult.invalid().minor( Minor.CHECKSUM_ALG_NOT_SUPPORTED ) );
        
        return verificationResult( result.build() );
    }
    
    /**
     * Transforming a {@link VerificationResultType} into a {@link Result}
     * 
     * @param vr
     *            the result type
     * @return the result
     */
    public static Result result( VerificationResultType vr )
    {
        Result result = new Result();
        result.setResultMajor( vr.getResultMajor() );
        result.setResultMessage( vr.getResultMessage() );
        result.setResultMinor( vr.getResultMinor() );
        
        return result;
    }
}
