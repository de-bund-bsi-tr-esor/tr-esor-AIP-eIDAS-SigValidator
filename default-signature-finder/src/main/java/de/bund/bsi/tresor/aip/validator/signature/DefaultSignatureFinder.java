/*-
 * Copyright (c) 2020
 * Federal Office for Information Security (BSI),
 * Godesberger Allee 185-189,
 * 53175 Bonn, Germany,
 * phone: +49 228 99 9582-0,
 * fax: +49 228 99 9582-5400,
 * e-mail: bsi@bsi.bund.de
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.bund.bsi.tresor.aip.validator.signature;

import static java.util.Collections.emptyList;
import static java.util.Collections.emptySet;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

import java.util.HashMap;
import java.util.HashSet;
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
import de.bund.bsi.tr_esor.xaip._1.MetaDataObjectType;
import de.bund.bsi.tr_esor.xaip._1.MetaDataSectionType;
import de.bund.bsi.tr_esor.xaip._1.XAIPType;
import de.bund.bsi.tresor.aip.validator.api.boundary.SignatureFinder;
import de.bund.bsi.tresor.aip.validator.api.control.AIPUtil;
import de.bund.bsi.tresor.aip.validator.api.entity.ModuleContext;
import de.bund.bsi.tresor.aip.validator.signature.entity.FinderResult;
import de.bund.bsi.tresor.aip.validator.signature.entity.SignaturePresence;
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
    private final String version = "1.1.0";
    
    @Override
    public Map<String, Set<String>> findSignatures( ModuleContext context, XAIPType xaip )
    {
        DataObjectsSectionType dataSection = xaip.getDataObjectsSection();
        MetaDataSectionType metaDataSection = xaip.getMetaDataSection();
        
        Set<FinderResult<DataObjectType>> dataSectionResults = Optional.ofNullable( dataSection )
                .map( DataObjectsSectionType::getDataObject )
                .orElse( emptyList() )
                .stream()
                .map( DataAnalyzer::findSignatures )
                .filter( Optional::isPresent )
                .map( Optional::get )
                .collect( toSet() );
        
        Set<FinderResult<MetaDataObjectType>> metadataSectionResults = Optional.ofNullable( metaDataSection )
                .map( MetaDataSectionType::getMetaDataObject )
                .orElse( emptyList() )
                .stream()
                .map( DataAnalyzer::findSignatures )
                .filter( Optional::isPresent )
                .map( Optional::get )
                .collect( toSet() );
        
        List<CredentialType> credentials = Optional.ofNullable( xaip.getCredentialsSection() )
                .map( CredentialsSectionType::getCredential )
                .orElse( emptyList() );
        
        Map<CredentialType, Set<FinderResult<DataObjectType>>> dataObjectResults = credentials.stream()
                .map( c -> CredentialSectionAnalyzer.analyzeCredential( c, dataSection, dataSectionResults ) )
                .collect( toMap( Entry::getKey, Entry::getValue ) );
        
        Map<CredentialType, Set<FinderResult<MetaDataObjectType>>> metadataObjectResults = credentials.stream()
                .map( c -> CredentialSectionAnalyzer.analyzeCredential( c, metaDataSection, metadataSectionResults ) )
                .collect( toMap( Entry::getKey, Entry::getValue ) );
        
        Map<String, Set<String>> mappedIds = new HashMap<>();
        mappedIds.putAll( mapCredIdsByDataId( mergeResults( dataSectionResults, dataObjectResults ) ) );
        mappedIds.putAll( mapCredIdsByDataId( mergeResults( metadataSectionResults, metadataObjectResults ) ) );
        
        // TODO is this correct?
        // following code is required since FinderResult cant be <?>
        // i don't think a credential can be related to a specific id and null at the same time
        // being mapped to multiple specific id's (multiple relatedObjects) OR null (no relation) looks more likely
        Set<String> nullMappedCredIds = Optional.ofNullable( mappedIds.remove( null ) ).orElse( emptySet() );
        Iterator<String> it = nullMappedCredIds.iterator();
        while ( it.hasNext() )
        {
            if ( mappedIds.entrySet().stream().anyMatch( e -> e.getValue().contains( it.next() ) ) )
            {
                it.remove();
            }
        }
        
        if ( !nullMappedCredIds.isEmpty() )
        {
            mappedIds.put( null, nullMappedCredIds );
        }
        
        return mappedIds;
    }
    
    <T> Map<CredentialType, Set<FinderResult<T>>> mergeResults( Set<FinderResult<T>> dataSectionResults,
            Map<CredentialType, Set<FinderResult<T>>> credentialSectionResults )
    {
        Iterator<FinderResult<T>> iterator = dataSectionResults.iterator();
        while ( iterator.hasNext() )
        {
            FinderResult<T> dataSectionResult = iterator.next();
            Optional<String> dataSectionObjId = Optional.ofNullable( dataSectionResult.getDataContainer() )
                    .map( AIPUtil::idFromObject );
            
            for ( Set<FinderResult<T>> credentialResult : credentialSectionResults.values() )
            {
                if ( credentialResult.stream()
                        .anyMatch( r -> Optional.ofNullable( r.getDataContainer() )
                                .map( AIPUtil::idFromObject )
                                .equals( dataSectionObjId ) ) )
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
    <T> Map<String, Set<String>> mapCredIdsByDataId( Map<CredentialType, Set<FinderResult<T>>> result )
    {
        Map<String, Set<String>> credIdsByDataId = new HashMap<>();
        for ( Entry<CredentialType, Set<FinderResult<T>>> entry : result.entrySet() )
        {
            Set<String> credId = Optional.ofNullable( entry.getKey() )
                    .map( CredentialType::getCredentialID )
                    .map( Set::of )
                    .orElse( new HashSet<>() );
            
            for ( FinderResult<T> finderResult : entry.getValue() )
            {
                // TODO might want to log some informations
                if ( finderResult.getPresence() != SignaturePresence.MISSING )
                {
                    String dataId = Optional.ofNullable( finderResult.getDataContainer() )
                            .map( AIPUtil::idFromObject )
                            .orElse( null );
                    
                    credIdsByDataId.computeIfAbsent( dataId, k -> new HashSet<>() ).addAll( credId );
                }
            }
        }
        
        return credIdsByDataId;
    }
}
