package de.bund.bsi.tresor.xaip.validator.api.entity;

import java.util.Optional;

import de.bund.bsi.tr_esor.xaip._1.XAIPType;
import lombok.AllArgsConstructor;
import lombok.Data;
import oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_.IndividualReportType;

/**
 * @author wolffs
 */
@Data
@AllArgsConstructor
public class SyntaxValidationResult
{
    private final Optional<XAIPType>   xaip;
    private final IndividualReportType syntaxReport;
    
    public SyntaxValidationResult( XAIPType xaip, IndividualReportType syntaxReport )
    {
        this( Optional.ofNullable( xaip ), syntaxReport );
    }
    
    public SyntaxValidationResult( IndividualReportType syntaxReport )
    {
        this( Optional.empty(), syntaxReport );
    }
}
