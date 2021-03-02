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

import java.io.OutputStream;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import com.beust.jcommander.DynamicParameter;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.beust.jcommander.converters.PathConverter;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wolffs
 */
@Data
@NoArgsConstructor
@Parameters( commandDescriptionKey = "cli" )
public class ServerConfig
{
    
    // providing description for the application itself and catching all unparsed remaining arguments
    @DynamicParameter( order = 1, names = "-M", descriptionKey = MessageBundle.SERVER_INFO )
    private Map<String, String> moduleConfig = new HashMap<>();
    
    @Parameter( order = 2, names = { "-c", "--config" }, descriptionKey = MessageBundle.SERVER_USAGE_CONFIG,
            converter = PathConverter.class )
    private Path                config       = null;
    
    @Parameter( order = 3, names = { "-p", "--port" },
            descriptionKey = MessageBundle.SERVER_PORT )
    private int                 port         = 8080;
    
    @Parameter( order = 4, names = { "-P", "--protocol" },
            descriptionKey = MessageBundle.SERVER_PROTOCOL )
    private String              protocol     = "http";
    
    @Parameter( order = 5, names = { "-H", "--host" },
            descriptionKey = MessageBundle.SERVER_HOST )
    private String              host         = "localhost";
    
    @Parameter( order = 6, names = { "--path" },
            descriptionKey = MessageBundle.SERVER_PATH )
    private String              path         = "/xaip-validate";
    
    @Parameter( order = 7, names = { "-d", "--debug", "--verbose" }, descriptionKey = MessageBundle.SERVER_VERBOSE )
    private boolean             verbose      = false;
    
    @Parameter( order = 8, names = { "-h", "--help" }, help = true, descriptionKey = MessageBundle.SERVER_HELP )
    private boolean             help;
    
    // -- Following values are default parameter for the server
    private final OutputStream  log          = System.out;
    
    private final boolean       verify       = true;
}
