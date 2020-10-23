package de.bund.bsi.tresor.xaip.validator.syntax.validators;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Optional;

import de.bund.bsi.tr_esor.vr._1.CredentialValidityType;
import de.bund.bsi.tr_esor.vr._1.CredentialsSectionValidityType;
import de.bund.bsi.tr_esor.xaip._1.CredentialType;
import de.bund.bsi.tr_esor.xaip._1.CredentialsSectionType;
import de.bund.bsi.tresor.xaip.validator.api.boundary.SignatureVerifier;

/**
 * Validator for any validations concerning data inside the {@link CredentialsSectionType}
 * 
 * @author wolffs
 */
public enum CredentialSectionValidator
{
    INSTANCE;
    
    /**
     * Complete validation of the credential section and all sub elements
     * 
     * @param credentialsSection
     *            the credential section
     * @return the validation result
     */
    public CredentialsSectionValidityType validateCredentialsSection( Optional<CredentialsSectionType> credentialsSection )
    {
        CredentialsSectionValidityType result = new CredentialsSectionValidityType();
        credentialsSection.map( section -> section.getCredential().stream()
                .map( this::validateCredential )
                .collect( toList() ) )
                .orElse( new ArrayList<>() )
                .stream()
                .forEach( result.getCredential()::add );
        
        return result;
    }
    
    /**
     * Validating a credential. The report is being added after the {@link SignatureVerifier} module
     * 
     * @param credential
     *            the credential
     * @return the validation result
     */
    public CredentialValidityType validateCredential( CredentialType credential )
    {
        CredentialValidityType result = new CredentialValidityType();
        result.setCredentialID( credential.getCredentialID() );
        // result.setRelatedObjects( value ); TODO xpath
        
        return result;
    }
}
