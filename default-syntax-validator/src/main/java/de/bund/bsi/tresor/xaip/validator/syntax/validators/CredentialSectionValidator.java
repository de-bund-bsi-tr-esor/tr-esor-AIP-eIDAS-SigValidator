package de.bund.bsi.tresor.xaip.validator.syntax.validators;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import de.bund.bsi.tr_esor.vr._1.CredentialValidityType;
import de.bund.bsi.tr_esor.vr._1.CredentialValidityType.RelatedObjects;
import de.bund.bsi.tr_esor.vr._1.CredentialsSectionValidityType;
import de.bund.bsi.tr_esor.xaip._1.CredentialType;
import de.bund.bsi.tr_esor.xaip._1.CredentialsSectionType;
import de.bund.bsi.tr_esor.xaip._1.DataObjectType;
import de.bund.bsi.tresor.xaip.validator.api.boundary.SignatureVerifier;
import de.bund.bsi.tresor.xaip.validator.api.control.XAIPUtil;

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
    public Optional<CredentialsSectionValidityType> validateCredentialsSection( Optional<CredentialsSectionType> credentialsSection )
    {
        List<CredentialValidityType> credential = credentialsSection.map( section -> section.getCredential().stream()
                .map( this::validateCredential )
                .collect( toList() ) )
                .orElse( new ArrayList<>() );
        
        if ( !credential.isEmpty() )
        {
            CredentialsSectionValidityType result = new CredentialsSectionValidityType();
            credential.stream().forEach( result.getCredential()::add );
            
            return Optional.of( result );
        }
        else
        {
            return Optional.empty();
        }
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
        RelatedObjects related = new RelatedObjects();
        credential.getRelatedObjects().stream()
                .filter( DataObjectType.class::isInstance )
                .map( XAIPUtil::idFromObject )
                .map( id -> "//dataObject[@dataObjectID='" + id + "']" )
                .forEach( related.getXPath()::add );
        
        CredentialValidityType result = new CredentialValidityType();
        result.setCredentialID( credential.getCredentialID() );
        result.setRelatedObjects( related );
        
        return result;
    }
}
