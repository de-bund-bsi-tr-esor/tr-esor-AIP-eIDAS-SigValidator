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
package de.bund.bsi.tresor.aip.validator.signature.checker;

import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;

import de.bund.bsi.tresor.aip.validator.api.control.ModuleLogger;

/**
 * Singleton to check data for possible CAdES.
 * 
 * @author wolffs
 */
public enum CAdESChecker
{
    INSTANCE;
    
    /**
     * Checking if the data could contain a CAdES by validating if the data is a <code>CMS</code> containing the contentType
     * <code>signedData</code>.
     * 
     * @param data
     *            the document data
     * @return if the data could contain a CAdES
     */
    public boolean isCAdES( byte[] data )
    {
        try
        {
            ContentInfo cms = ContentInfo.getInstance( data );
            
            boolean isCAdES = PKCSObjectIdentifiers.signedData.equals( cms.getContentType() );
            ModuleLogger.verbose( isCAdES ? "data is CAdES" : "data is not CAdES" );
            
            return isCAdES;
        }
        catch ( Exception e )
        {
            ModuleLogger.verbose( "data is not CAdES", e );
        }
        
        return false;
    }
}
