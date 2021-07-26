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
package de.bund.bsi.tresor.aip.validator.api.boundary;

import java.io.InputStream;

import de.bund.bsi.tr_esor.xaip.XAIPType;
import de.bund.bsi.tresor.aip.validator.api.entity.ModuleContext;
import de.bund.bsi.tresor.aip.validator.api.entity.SyntaxValidationResult;
import oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_.IndividualReportType;

/**
 * API for the SyntaxValidatorModule.
 * 
 * @author wolffs
 */
public interface SyntaxValidator extends ValidatorModule
{
    /**
     * Retrieving data from the inputStream and verifies if the data contains a valid XAIP. As a result of this operation an
     * {@link IndividualReportType} will be created containing the information of the XAIP syntax validation. If the validation was
     * successful the parsed {@link XAIPType} is also being returned.
     * 
     * @param context
     *            moduleContext which can be used to add additional results/arguments for subsequent modules or evaluate those additional
     *            data
     * @param xaipCandidate
     *            inputStream which should contain an xaip
     * @return {@link SyntaxValidationResult} containing the {@link IndividualReportType} and an optional {@link XAIPType} which is empty on
     *         invalid syntax
     */
    public SyntaxValidationResult validateSyntax( ModuleContext context, InputStream xaipCandidate );
}
