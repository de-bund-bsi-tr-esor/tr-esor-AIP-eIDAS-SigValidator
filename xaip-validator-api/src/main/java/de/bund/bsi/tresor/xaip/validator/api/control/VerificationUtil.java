package de.bund.bsi.tresor.xaip.validator.api.control;

import java.io.IOException;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
 * @author wolffs
 */
public class VerificationUtil
{
    public static VerificationResultType verificationResult( Result result )
    {
        VerificationResultType verificationResult = new VerificationResultType();
        verificationResult.setResultMajor( result.getResultMajor() );
        verificationResult.setResultMinor( result.getResultMinor() );
        verificationResult.setResultMessage( result.getResultMessage() );
        
        return verificationResult;
    }
    
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
                            byte[] expectedDigest = checksum.getCheckSum();
                            
                            if ( org.bouncycastle.util.Arrays.constantTimeAreEqual( expectedDigest, digest ) )
                            {
                                builder = DefaultResult.ok();
                            }
                            else
                            {
                                builder.minor( Minor.INVALID_CHECKSUM );
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
}
