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
package de.bund.bsi.tresor.aip.validator.syntax.context;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import de.bund.bsi.tr_esor.vr.CredentialValidityType.RelatedObjects;
import de.bund.bsi.tresor.aip.validator.syntax.DefaultSyntaxValidator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class representing context of the {@link DefaultSyntaxValidator}
 * 
 * @author wolffs
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DefaultSyntaxValidatorContext
{
    private byte[]                      rawData;
    
    private Map<String, RelatedObjects> relatedObjectByCredId = new HashMap<>();
    
    private String                      tempPath              = "";
    
    private byte[]                      asicAIPContainer;
    
    /**
     * Returns an new inputStream of raw xaip data
     * 
     * @return the inputStream
     */
    public InputStream rawXaipInput()
    {
        return new ByteArrayInputStream( rawData );
    }
}
