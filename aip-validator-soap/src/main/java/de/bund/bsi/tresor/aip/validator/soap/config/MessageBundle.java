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
package de.bund.bsi.tresor.aip.validator.soap.config;

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
    public static final ResourceBundle RESOURCE             = ResourceBundle.getBundle( "MessageBundle" );
    
    public static final String         SERVER_INFO          = "server.info";
    
    public static final String         SERVER_NAME          = "server.name";
    
    public static final String         SERVER_HELP          = "server.help";
    
    public static final String         SERVER_HOST          = "server.host";
    
    public static final String         SERVER_PORT          = "server.port";
    
    public static final String         SERVER_PATH          = "server.path";
    
    public static final String         SERVER_PROTOCOL      = "server.protocol";
    
    public static final String         SERVER_VERBOSE       = "server.verbose";
    
    public static final String         SERVER_USAGE_CONFIG  = "server.usage.config";
    
    public static final String         SERVER_KEYSTORE      = "server.keystore";
    
    public static final String         SERVER_KEYSTORE_PASS = "server.keystore.pass";
    
}
