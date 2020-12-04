package de.bund.bsi.tresor.xaip.validator.api.boundary;

import java.io.InputStream;

import de.bund.bsi.tr_esor.xaip._1.XAIPType;
import de.bund.bsi.tresor.xaip.validator.api.entity.SyntaxValidationResult;
import oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_.IndividualReportType;

/**
 * API for the SyntaxValidatorModule.
 * 
 * @author wolffs
 */
public interface SyntaxValidator extends ValidatorModule
{
    /**
     * Retrieving data from the inputStream and verifies if the data contains a valid XAIP. As a result of this operation an
     * {@link IndividualReportType} will be created containing the information of the XAIP syntax validation. If the validation was
     * successful the parsed {@link XAIPType} is also being returned.
     * 
     * @param xaipCandidate
     *            inputStream which should contain an xaip
     * @return {@link SyntaxValidationResult} containing the {@link IndividualReportType} and an optional {@link XAIPType} which is empty on
     *         invalid syntax
     */
    public SyntaxValidationResult validateSyntax( InputStream xaipCandidate );
}
