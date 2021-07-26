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

import java.util.List;
import java.util.Map;
import java.util.Set;

import de.bund.bsi.tr_esor.vr.CredentialValidityType;
import de.bund.bsi.tr_esor.xaip.CredentialType;
import de.bund.bsi.tr_esor.xaip.DataObjectType;
import de.bund.bsi.tr_esor.xaip.XAIPType;
import de.bund.bsi.tresor.aip.validator.api.entity.ModuleContext;
import oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_.IndividualReportType;

/**
 * API for the SignatureVerificationModule.<br>
 * This module is being used to verify all signatures found by the {@link SignatureFinder} implementation.
 * 
 * @author wolffs
 */
public interface SignatureVerifier extends ValidatorModule
{
    /**
     * Resolving the {@link DataObjectType} and {@link CredentialType} by the provided ids from the {@link SignatureFinder} and verifying
     * the resolved signatures. Each signature should result into an {@link CredentialValidityType} with consideration of the supported
     * signatures of the verifier.
     * 
     * @param context
     *            moduleContext which can be used to add additional results/arguments for subsequent modules or evaluate those additional
     *            data
     * @param xaip
     *            the xaip to validate
     * @param credIdsByDataId
     *            a set of credentialIds as value mapped by the dataObjectId as key which contain a signature.</br>
     *            The entries can contain any permutation of:</br>
     *            <ul>
     *            <li>dataObjectId without any credentialIds</li>
     *            <ul>
     *            <li>when the dataObject contains an embedded signature</li>
     *            </ul>
     *            <li>dataObjectId with credentialId(s)</li>
     *            <ul>
     *            <li>when the dataObject contains an embedded signature and the credential holds a pointer to those data</li>
     *            <li>when the dataObject contains a detached signature which is hold by the credential</li>
     *            <li>when the credential holds a timestamp</li>
     *            </ul>
     *            <li>credentialId(s) mapped to the key <code>null</code></li>
     *            <ul>
     *            <li>when the credential contains data with an embedded signature without any dataObject reference</li>
     *            </ul>
     *            </ul>
     * @return the verification result in form of an {@link IndividualReportType}s
     */
    public List<CredentialValidityType> verify( ModuleContext context, XAIPType xaip, Map<String, Set<String>> credIdsByDataId );
    
}
