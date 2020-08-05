package de.bund.bsi.tresor.xaip.validator.api.entity;

import java.util.Optional;

import de.bund.bsi.tr_esor.xaip._1.XAIPType;
import lombok.AllArgsConstructor;
import lombok.Data;
import oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_.IndividualReportType;

/**
 * Result entity of the syntax validation.
 * 
 * @author wolffs
 */
@Data
@AllArgsConstructor
public class SyntaxValidationResult
{
    private final Optional<XAIPType>   xaip;
    private final IndividualReportType syntaxReport;
    
    /**
     * Creating a new result containing the individual report of the syntax validation and an optional XAIP. If the xaip could not be
     * unmarshalled properly the syntaxReport should at least contain a warning. If the XAIP is <code>null</code> due to inproper
     * unmarshalling, this entity will use an empty optional instead of the null value.
     * 
     * @param xaip
     *            value of the unmarshalled xaip which might be <code>null</code>
     * @param syntaxReport
     *            the syntax validation report
     */
    public SyntaxValidationResult( XAIPType xaip, IndividualReportType syntaxReport )
    {
        this( Optional.ofNullable( xaip ), syntaxReport );
    }
    
    /**
     * Creating a validation result for invalid XAIP structures. The syntaxReport should contain an error to indicate inproper syntax or a
     * warning to indicate unmarshalling errors.
     * 
     * @param syntaxReport
     *            the syntax validation report which should contain at least a warning
     */
    public SyntaxValidationResult( IndividualReportType syntaxReport )
    {
        this( Optional.empty(), syntaxReport );
    }
}
