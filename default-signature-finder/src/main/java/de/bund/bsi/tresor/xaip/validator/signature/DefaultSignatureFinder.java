package de.bund.bsi.tresor.xaip.validator.signature;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;

import de.bund.bsi.tr_esor.xaip._1.CredentialType;
import de.bund.bsi.tr_esor.xaip._1.CredentialsSectionType;
import de.bund.bsi.tr_esor.xaip._1.DataObjectType;
import de.bund.bsi.tr_esor.xaip._1.DataObjectsSectionType;
import de.bund.bsi.tr_esor.xaip._1.XAIPType;
import de.bund.bsi.tresor.xaip.validator.api.boundary.SignatureFinder;
import de.bund.bsi.tresor.xaip.validator.signature.entity.FinderResult;
import de.bund.bsi.tresor.xaip.validator.signature.entity.SignaturePresence;
import lombok.Getter;

/**
 * Implementation of the SignatureFindermodule from the XAIPValidator.
 * 
 * @author wolffs
 */
@Getter
public class DefaultSignatureFinder implements SignatureFinder
{
    private final String vendor  = "BSI";
    private final String version = "1.0.0";
    
    /**
     * List of credentialIds mapped by the dataObjectId
     * 
     * @param xaip
     * @return
     */
    @Override
    public Map<String, Set<String>> findSignatures( XAIPType xaip )
    {
        DataObjectsSectionType dataSection = xaip.getDataObjectsSection();
        
        Set<FinderResult> dataSectionResults = Optional.ofNullable( dataSection )
                .map( DataObjectsSectionType::getDataObject )
                .orElse( emptyList() )
                .stream()
                .map( DataSectionAnalyzer::findSignatures )
                .filter( Optional::isPresent )
                .map( Optional::get )
                .collect( toSet() );
        
        Map<CredentialType, Set<FinderResult>> credentialSectionResults = Optional.ofNullable( xaip.getCredentialsSection() )
                .map( CredentialsSectionType::getCredential )
                .orElse( emptyList() )
                .stream()
                .map( c -> CredentialSectionAnalyzer.analyzeCredential( c, dataSection, dataSectionResults ) )
                .collect( toMap( Entry::getKey, Entry::getValue ) );
        
        return mapCredIdsByDataId( mergeResults( dataSectionResults, credentialSectionResults ) );
    }
    
    Map<CredentialType, Set<FinderResult>> mergeResults( Set<FinderResult> dataSectionResults,
            Map<CredentialType, Set<FinderResult>> credentialSectionResults )
    {
        Iterator<FinderResult> iterator = dataSectionResults.iterator();
        while ( iterator.hasNext() )
        {
            FinderResult dataSectionResult = iterator.next();
            for ( Set<FinderResult> credentialResult : credentialSectionResults.values() )
            {
                if ( credentialResult.stream().anyMatch(
                        r -> r.getDataObject().getDataObjectID().equals( dataSectionResult.getDataObject().getDataObjectID() ) ) )
                {
                    iterator.remove();
                    break;
                }
            }
        }
        
        credentialSectionResults.put( null, dataSectionResults );
        
        return credentialSectionResults;
    }
    
    // can contain null-valued keys when a credentialSection occurs without data refs
    Map<String, Set<String>> mapCredIdsByDataId( Map<CredentialType, Set<FinderResult>> result )
    {
        Map<String, Set<String>> credIdsByDataId = new HashMap<>();
        for ( Entry<CredentialType, Set<FinderResult>> entry : result.entrySet() )
        {
            Set<String> credId = Optional.ofNullable( entry.getKey() )
                    .map( CredentialType::getCredentialID )
                    .map( Set::of )
                    .orElse( new HashSet<>() );
            
            for ( FinderResult finderResult : entry.getValue() )
            {
                // TODO might want to log some informations
                if ( finderResult.getPresence() != SignaturePresence.MISSING )
                {
                    String dataId = Optional.ofNullable( finderResult.getDataObject() )
                            .map( DataObjectType::getDataObjectID )
                            .orElse( null );
                    
                    credIdsByDataId.computeIfAbsent( dataId, k -> new HashSet<>() ).addAll( credId );
                }
            }
        }
        
        return credIdsByDataId;
    }
}
