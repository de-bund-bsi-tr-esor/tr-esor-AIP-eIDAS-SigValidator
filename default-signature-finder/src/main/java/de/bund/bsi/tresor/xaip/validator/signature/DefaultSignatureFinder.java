package de.bund.bsi.tresor.xaip.validator.signature;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
import de.bund.bsi.tresor.xaip.validator.api.control.ModuleLogger;
import de.bund.bsi.tresor.xaip.validator.signature.entity.FinderResult;
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
    
    @Override
    public Map<DataObjectType, List<CredentialType>> findSignatures( XAIPType xaip )
    {
        Map<DataObjectType, List<CredentialType>> results = new HashMap<>();
        CredentialsSectionType credentialSection = xaip.getCredentialsSection();
        
        if ( credentialSection != null && credentialSection.getCredential() != null )
        {
            
            for ( CredentialType credential : credentialSection.getCredential() )
            {
                String cid = credential.getCredentialID();
                ModuleLogger.verbose( "checking credential " + cid );
                credential.getRelatedObjects()
                        .stream()
                        .filter( DataObjectType.class::isInstance )
                        .map( DataObjectType.class::cast )
                        .map( dataObject -> new AbstractMap.SimpleEntry<DataObjectType, List<CredentialType>>( dataObject,
                                asList( credential ) ) )
                        .forEach( entry -> results.merge( entry.getKey(), entry.getValue(), this::remap ) );
                
                if ( credential.getRelatedObjects().isEmpty() )
                {
                    results.merge( null, asList( credential ), this::remap );
                }
            }
        }
        
        return results;
    }
    
    List<CredentialType> remap( List<CredentialType> a, List<CredentialType> b )
    {
        a.addAll( b );
        return a;
    }
    
    /////////////////////////////////////////////////////////
    
    public Map<DataObjectType, List<CredentialType>> findSignature( XAIPType xaip )
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
        
        Map<CredentialType, Set<FinderResult>> credentialSectionResult = Optional.ofNullable( xaip.getCredentialsSection() )
                .map( CredentialsSectionType::getCredential )
                .orElse( emptyList() )
                .stream()
                .map( c -> CredentialSectionAnalyzer.analyzeCredential( c, dataSection, dataSectionResults ) )
                .collect( toMap( Entry::getKey, Entry::getValue ) );
        
        // TODO: merge both results
        Iterator<FinderResult> iterator = dataSectionResults.iterator();
        while ( iterator.hasNext() )
        {
            FinderResult dataSectionResult = iterator.next();
            for ( Set<FinderResult> credentialResult : credentialSectionResult.values() )
            {
                
                if ( value.stream().anyMatch(
                        r -> r.getDataObject().getDataObjectID().equals( dataSectionResult.getDataObject().getDataObjectID() ) ) )
                {
                    iterator.remove();
                }
            }
        }
        
        return null;
    }
}
