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

import java.util.Map;
import java.util.Set;

import de.bund.bsi.tr_esor.xaip.CredentialType;
import de.bund.bsi.tr_esor.xaip.DataObjectType;
import de.bund.bsi.tr_esor.xaip.XAIPType;
import de.bund.bsi.tresor.aip.validator.api.entity.ModuleContext;

/**
 * API for the SignatureFinderModule.
 * 
 * @author wolffs
 */
public interface SignatureFinder extends ValidatorModule
{
    /**
     * Scanning the provided {@link XAIPType} for signatures.<br>
     * Normally a {@link CredentialType} holds a signature to a related {@link DataObjectType}. In cases for embedded signatures there can
     * be any permutation of a {@link CredentialType} holding only a pointer to the {@link DataObjectType} which contains the embedded
     * signature, the {@link CredentialType} not having any {@link DataObjectType} bound to it and holding the embedded signature itself or
     * the {@link DataObjectType} containing the embedded signature without any related {@link CredentialType}.<br>
     * <br>
     * Therefore multiple {@link CredentialType}s can be bound to an {@link DataObjectType} and in cases for embedded unbound
     * {@link CredentialType}s they relate to an empty {@link DataObjectType} which is represented in the map as <code>null</code> value.
     * 
     * @param context
     *            moduleContext which can be used to add additional results/arguments for subsequent modules or evaluate those additional
     *            data
     * @param xaip
     *            the xaip to analyze
     * @return a set of credentialIds as value mapped to the related dataObjectId as the key
     */
    public Map<String, Set<String>> findSignatures( ModuleContext context, XAIPType xaip );
}
