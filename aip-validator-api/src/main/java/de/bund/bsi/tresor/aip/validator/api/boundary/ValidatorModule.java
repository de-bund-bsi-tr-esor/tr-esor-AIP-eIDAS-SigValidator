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

/**
 * Basic interface which each XAIPValidatorModule has to implement
 * 
 * @author wolffs
 */
public interface ValidatorModule
{
    /**
     * Version of the module which is always coupled to the vendor and can be in any format. The version will be printed on verbose logging
     * when the module is being loaded to identify the modules in use. This helps the user to analyze and report problems to the vendor.
     * 
     * @return the module version of the vendor
     */
    public String getVersion();
    
    /**
     * Vendor of the module which is always coupled to the version. The vendor name is used to separate different module implementations and
     * load the configuration for the module implementation. This name should <b>not contain whitespaces</b>!
     * 
     * @return name of the vendor
     */
    public String getVendor();
    
    /**
     * Optional method for configuring the module. This method only needs to be implemented when the module depends on external
     * configuration.<br/>
     * The property map will contain any module arguments being passed to the XAIPValidator on startup either via multiple arguments or a
     * property file containing all properties.<br/>
     * <br/>
     * <b>Examples:</b><br/>
     * <br/>
     * <b>Input:</b> <code>java -jar xaip-validator-cli.jar -Margument.first=foo -Margument.second=bar</code><br/>
     * <b>Resolves to:</b><br/>
     * <table border=1>
     * <tr>
     * <td>argument.first</td>
     * <td>foo</td>
     * </tr>
     * <tr>
     * <td>argument.second</td>
     * <td>bar</td>
     * </tr>
     * </table>
     * <br/>
     * <b>Input:</b> <code>java -jar xaip-validator-cli.jar --config example.properties</code><br/>
     * <b>Resolves to:</b><br/>
     * <table border=1>
     * <tr>
     * <td>argument.first</td>
     * <td>foo</td>
     * </tr>
     * <tr>
     * <td>argument.second</td>
     * <td>bar</td>
     * </tr>
     * </table>
     * <br/>
     * <b>Content of example.properties:<br/>
     * <table border=1>
     * <tr>
     * <td>argument.first=foo<br/>
     * argument.second=bar</td>
     * </tr>
     * </table>
     * 
     * @param properties
     *            the property map for this module
     */
    default public void configure( Map<String, String> properties )
    {
        // no configuration required by default, this is only optional
    }
}
