package de.bund.bsi.tresor.xaip.validator.api.entity;

import oasis.names.tc.dss._1_0.core.schema.Result;
import oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_.VerificationResultType;

/**
 * @author wolffs
 */
public class VerificationConverter
{
    public static VerificationResultType fromResult( Result result )
    {
        VerificationResultType verificationResult = new VerificationResultType();
        verificationResult.setResultMajor( result.getResultMajor() );
        verificationResult.setResultMinor( result.getResultMinor() );
        verificationResult.setResultMessage( result.getResultMessage() );
        
        return verificationResult;
    }
}
