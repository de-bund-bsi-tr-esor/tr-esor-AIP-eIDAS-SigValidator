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
package de.bund.bsi.tresor.xaip.validator.signature.entity;

import java.io.InputStream;
import java.util.Optional;

import de.bund.bsi.tr_esor.xaip._1.DataObjectType;
import lombok.AllArgsConstructor;
import lombok.Value;

/**
 * Representation of a found signature which contains the relevant data and the dataObject
 * 
 * @author wolffs
 */
@Value
@AllArgsConstructor
public class FinderResult
{
    private DataObjectType        dataObject;
    private SignaturePresence     presence;
    private Optional<InputStream> data;
    
    /**
     * Creating a new FinderResult
     * 
     * @param dataObject
     *            the dataObject containing a signature
     * @param presence
     *            the presence of a signature
     * @param data
     *            the data of the dataObject (might be an embedded signature)
     */
    public FinderResult( DataObjectType dataObject, SignaturePresence presence, InputStream data )
    {
        this.dataObject = dataObject;
        this.presence = presence;
        this.data = Optional.ofNullable( data );
    }
}
