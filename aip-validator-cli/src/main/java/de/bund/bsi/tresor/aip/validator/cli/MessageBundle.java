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
package de.bund.bsi.tresor.aip.validator.cli;

import java.util.ResourceBundle;

import lombok.Getter;

/**
 * Abstract class for managing the messagebundle. The messagebundle is being used to get informations over the usage of the CLI.
 * 
 * @author wolffs
 */
@Getter
public abstract class MessageBundle
{
    public static final ResourceBundle RESOURCE            = ResourceBundle.getBundle( "MessageBundle" );
    
    public static final String         CLI_INFO            = "cli.info";
    
    public static final String         CLI_NAME            = "cli.name";
    
    public static final String         CLI_USAGE_CONFIG    = "cli.usage.config";
    
    public static final String         CLI_USAGE_INPUT     = "cli.usage.input";
    
    public static final String         CLI_USAGE_OUTPUT    = "cli.usage.output";
    
    public static final String         CLI_USAGE_ECARD_URL = "cli.usage.ecard.url";
    
    public static final String         CLI_USAGE_VERIFY    = "cli.usage.verify";
    
    public static final String         CLI_USAGE_VERBOSE   = "cli.usage.verbose";
    
    public static final String         CLI_USAGE_LOG       = "cli.usage.log";
    
    public static final String         CLI_USAGE_HELP      = "cli.usage.help";
    
    public static final String         ARG_DEFAULT_LOG     = "arguments.default.log";
    
    public static final String         ARG_DEFAULT_OUTPUT  = "arguments.default.output";
    
}
