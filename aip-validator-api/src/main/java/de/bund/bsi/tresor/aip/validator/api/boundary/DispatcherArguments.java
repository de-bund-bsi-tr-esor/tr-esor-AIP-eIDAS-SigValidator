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
import java.io.OutputStream;
import java.util.Map;

/**
 * Interface of arguments for the dispatcher.
 * 
 * @author wolffs
 */
public interface DispatcherArguments
{
    /**
     * Dispatcher argument if the signatures of the provided XAIP should be validated. Using isVerify with a value of <code>false</code>
     * only the XAIP schema will be validated and the signature verification step is being skipped.
     * 
     * @return isVerify signatures enabled
     */
    public boolean isVerify();
    
    /**
     * Dispatcher argument if verbose logging over all modules should be enabled.
     * 
     * @return isVerbose logging enabled
     */
    public boolean isVerbose();
    
    /**
     * Dispatcher argument for the inputStream which is being used. This stream is being consumed by the dispatcher and needs to provide an
     * XAIP for validation.
     * 
     * @return the inputStream containing an XAIP
     */
    public InputStream getInput();
    
    /**
     * Dispatcher argument for the result outputStream. The validation result will be written to this stream.
     * 
     * @return the outputStream
     */
    public OutputStream getOutput();
    
    /**
     * Dispatcher argument for the logger outputStream. The logger will use this stream to write the log entries.
     * 
     * @return the outputStream
     */
    public OutputStream getLog();
    
    /**
     * Dispatcher argument for the individual modules the dispatcher is managing.
     * 
     * @return the module configuration properties
     */
    public Map<String, String> getModuleConfig();
    
}
