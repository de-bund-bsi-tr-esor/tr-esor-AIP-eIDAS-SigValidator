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
package de.bund.bsi.tresor.xaip.validator.soap.config;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import de.bund.bsi.tresor.xaip.validator.dispatcher.DispatcherArguments;
import lombok.Builder;
import lombok.Data;

/**
 * @author wolffs
 */
@Data
@Builder
public class DispatcherArgs implements DispatcherArguments
{
    private boolean             verify;
    private boolean             verbose;
    
    private InputStream         input;
    private OutputStream        output;
    private OutputStream        log;
    
    @Builder.Default
    private Map<String, String> moduleConfig = new HashMap<>();
}
