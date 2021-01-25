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
package de.bund.bsi.tresor.xaip.validator.syntax.validators;

import static java.util.Collections.emptyMap;
import static java.util.stream.Collectors.toMap;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import de.bund.bsi.tr_esor.vr._1.CredentialValidityType.RelatedObjects;
import de.bund.bsi.tr_esor.xaip._1.CredentialType;
import de.bund.bsi.tr_esor.xaip._1.CredentialsSectionType;
import de.bund.bsi.tr_esor.xaip._1.DataObjectType;
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
     * Checking the credentials for relatedObjects
     * 
     * @param credentialsSection
     *            the credential section
     * @return the validation result
     */
    public Map<String, RelatedObjects> validateCredentialsSection( Optional<CredentialsSectionType> credentialsSection )
    {
        return credentialsSection.map( section -> section.getCredential().stream()
                .map( this::validateCredential )
                .collect( toMap( Entry::getKey, Entry::getValue ) ) )
                .orElse( emptyMap() );
    }
    
    /**
     * Searching for related objects of the credential and mapping it to the credentialId
     * 
     * @param credential
     *            the credential
     * @return the validation result
     */
    public Map.Entry<String, RelatedObjects> validateCredential( CredentialType credential )
    {
        RelatedObjects related = new RelatedObjects();
        credential.getRelatedObjects().stream()
                .filter( DataObjectType.class::isInstance )
                .map( XAIPUtil::idFromObject )
                .map( id -> "//dataObject[@dataObjectID='" + id + "']" )
                .forEach( related.getXPath()::add );
        
        if ( related.getXPath().isEmpty() )
        {
            related.getXPath().add( "//credential[@credentialID='" + credential.getCredentialID() + "']" );
        }
        
        return new AbstractMap.SimpleEntry<String, RelatedObjects>( credential.getCredentialID(), related );
    }
}
