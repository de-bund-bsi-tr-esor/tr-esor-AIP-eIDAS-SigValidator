package de.bund.bsi.tresor.xaip.validator.api.boundary;

import java.io.InputStream;

import de.bund.bsi.tresor.xaip.validator.api.entity.SyntaxValidationResult;

/**
 * @author wolffs
 */
public interface SyntaxValidator extends ValidatorModule
{
    SyntaxValidationResult validateSyntax( InputStream xaipCandidate );
}
