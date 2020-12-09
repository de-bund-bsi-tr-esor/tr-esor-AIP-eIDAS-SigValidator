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
package de.bund.bsi.tresor.xaip.validator.api.entity.xaip;

import java.util.EnumSet;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Representation of all XAIP metaData categories which can be used
 * 
 * @author wolffs
 */
@Getter
@AllArgsConstructor
public enum Category
{
    DMD( EnumSet.of( Classification.DESCRIPTION, Classification.OTHER ) ),
    
    REP( EnumSet.of( Classification.SYNTAX, Classification.DED, Classification.OTHER ) ),
    
    PDI( EnumSet.of( Classification.REFERENCE, Classification.CONTEXT, Classification.PROVENANCE, Classification.OTHER ) ),
    
    OTHER( EnumSet.allOf( Classification.class ) );
    
    private final EnumSet<Classification> classifications;
}
