package de.bund.bsi.tresor.xaip.validator.api.control;

import java.io.IOException;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.io.Streams;

import de.bund.bsi.tr_esor.xaip._1.CheckSumType;
import de.bund.bsi.tresor.xaip.validator.api.entity.DefaultResult;
import de.bund.bsi.tresor.xaip.validator.api.entity.DefaultResult.Builder;
import de.bund.bsi.tresor.xaip.validator.api.entity.DefaultResult.Minor;
import de.bund.bsi.tresor.xaip.validator.api.entity.xaip.DigestAlgorithm;
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
     * @return the verification result
     */
    public static VerificationResultType verifyChecksum( InputStream content, CheckSumType checksum )
    {
        Builder result = DigestAlgorithm.fromXmlSyntax( checksum.getCheckSumAlgorithm() )
                .map( digestAlg -> {
                    Builder builder = DefaultResult.error();
                    try
                    {
                        MessageDigest md = MessageDigest.getInstance( digestAlg.getJavaName(), new BouncyCastleProvider() );
                        try ( DigestInputStream digestIn = new DigestInputStream( content, md ) )
                        {
                            Streams.drain( digestIn );
                            byte[] digest = digestIn.getMessageDigest().digest();
                            byte[] expectedDigest = Base64.getDecoder().decode( checksum.getCheckSum() );
                            
                            if ( org.bouncycastle.util.Arrays.constantTimeAreEqual( expectedDigest, digest ) )
                            {
                                builder = DefaultResult.ok();
                            }
                            else
                            {
                                builder.minor( Minor.CHECKSUM_INVALID );
                            }
                        }
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
                } ).orElse( DefaultResult.error().minor( Minor.CHECKSUM_ALG_NOT_SUPPORTED ) );
        
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
