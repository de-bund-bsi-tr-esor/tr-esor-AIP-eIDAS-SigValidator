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
package de.bund.bsi.tresor.aip.validator.cli.arguments;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import com.beust.jcommander.DynamicParameter;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.beust.jcommander.converters.PathConverter;

import de.bund.bsi.tresor.aip.validator.api.boundary.DispatcherArguments;
import de.bund.bsi.tresor.aip.validator.cli.MessageBundle;
import de.bund.bsi.tresor.aip.validator.cli.converter.InputStreamConverter;
import de.bund.bsi.tresor.aip.validator.cli.converter.OutputStreamConverter;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents all possible CLI arguments.
 * 
 * @author wolffs
 */
@Data
@NoArgsConstructor
@Parameters( commandDescriptionKey = "cli" )
public class Arguments implements DispatcherArguments
{
    // providing description for the application itself and catching all unparsed remaining arguments
    @DynamicParameter( order = 1, names = "-M", descriptionKey = MessageBundle.CLI_INFO )
    private Map<String, String> moduleConfig = new HashMap<>();
    
    @Parameter( order = 2, names = { "-c", "--config" }, descriptionKey = MessageBundle.CLI_USAGE_CONFIG, converter = PathConverter.class )
    private Path                config       = null;
    
    @Parameter( order = 3, names = { "-i", "--in", "--input" },
            descriptionKey = MessageBundle.CLI_USAGE_INPUT, converter = InputStreamConverter.class )
    private InputStream         input        = System.in;
    
    @Parameter( order = 4, names = { "-o", "--out", "--output" },
            descriptionKey = MessageBundle.CLI_USAGE_OUTPUT, converter = OutputStreamConverter.class )
    private OutputStream        output       = System.out;
    
    @Parameter( order = 5, names = { "-v", "--verify" }, descriptionKey = MessageBundle.CLI_USAGE_VERIFY )
    private boolean             verify;
    
    @Parameter( order = 6, names = { "-d", "--debug", "--verbose" }, descriptionKey = MessageBundle.CLI_USAGE_VERBOSE )
    private boolean             verbose;
    
    @Parameter( order = 7, names = { "-l", "--log" },
            descriptionKey = MessageBundle.CLI_USAGE_LOG, converter = OutputStreamConverter.class )
    private OutputStream        log          = System.err;
    
    @Parameter( order = 8, names = { "-h", "--help" }, help = true, descriptionKey = MessageBundle.CLI_USAGE_HELP )
    private boolean             help;
}