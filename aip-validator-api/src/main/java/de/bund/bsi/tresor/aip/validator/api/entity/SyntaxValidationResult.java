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
package de.bund.bsi.tresor.aip.validator.api.entity;

import java.util.Optional;

import de.bund.bsi.tr_esor.vr._1.XAIPValidityType;
import de.bund.bsi.tr_esor.xaip._1.XAIPType;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Result entity of the syntax validation.
 * 
 * @author wolffs
 */
@Data
@AllArgsConstructor
public class SyntaxValidationResult
{
    private final Optional<XAIPType> xaip;
    private final XAIPValidityType   syntaxReport;
    
    /**
     * Creating a new result containing the individual report of the syntax validation and an optional XAIP. If the xaip could not be
     * unmarshalled properly the syntaxReport should at least contain a warning. If the XAIP is <code>null</code> due to inproper
     * unmarshalling, this entity will use an empty optional instead of the null value.
     * 
     * @param xaip
     *            value of the unmarshalled xaip which might be <code>null</code>
     * @param syntaxReport
     *            the syntax validation report
     */
    public SyntaxValidationResult( XAIPType xaip, XAIPValidityType syntaxReport )
    {
        this( Optional.ofNullable( xaip ), syntaxReport );
    }
    
    /**
     * Creating a validation result for invalid XAIP structures. The syntaxReport should contain an error to indicate inproper syntax or a
     * warning to indicate unmarshalling errors.
     * 
     * @param syntaxReport
     *            the syntax validation report which should contain at least a warning
     */
    public SyntaxValidationResult( XAIPValidityType syntaxReport )
    {
        this( Optional.empty(), syntaxReport );
    }
}
