package de.bund.bsi.tresor.xaip.validator.syntax.validators;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Optional;

import de.bund.bsi.tr_esor.vr._1.CredentialValidityType;
import de.bund.bsi.tr_esor.vr._1.CredentialsSectionValidityType;
import de.bund.bsi.tr_esor.xaip._1.CredentialType;
import de.bund.bsi.tr_esor.xaip._1.CredentialsSectionType;

/**
 * @author wolffs
 */
public enum CredentialSectionValidator
{
    INSTANCE;
    
    public CredentialsSectionValidityType validateCredentialsSection( CredentialsSectionType credentialsSection )
    {
        CredentialsSectionValidityType result = new CredentialsSectionValidityType();
        Optional.ofNullable( credentialsSection )
                .map( section -> section.getCredential().stream()
                        .map( this::validateCredential )
                        .collect( toList() ) )
                .orElse( new ArrayList<>() )
                .stream()
                .forEach( result.getCredential()::add );
        
        return result;
    }
    
    public CredentialValidityType validateCredential( CredentialType credential )
    {
        CredentialValidityType result = new CredentialValidityType();
        result.setCredentialID( credential.getCredentialID() );
        // result.setRelatedObjects( value ); TODO xpath
        
        // the reports are being added later via the verification module
        
        return result;
    }
}
