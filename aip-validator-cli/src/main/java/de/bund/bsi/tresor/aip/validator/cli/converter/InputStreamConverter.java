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
package de.bund.bsi.tresor.aip.validator.cli.converter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Path;

import com.beust.jcommander.IStringConverter;

import de.bund.bsi.tresor.aip.validator.api.entity.AIPValidatorException;
import de.bund.bsi.tresor.aip.validator.cli.arguments.Arguments;

/**
 * A converter which is being used to parse the input {@link Arguments} from the CLI. When this argument is being used, the provided value
 * will be parsed as a {@link Path} to read the XAIP from this file.
 * 
 * @author wolffs
 */
public class InputStreamConverter implements IStringConverter<InputStream>
{
    @Override
    public InputStream convert( String value )
    {
        try
        {
            return new FileInputStream( value );
        }
        catch ( FileNotFoundException e )
        {
            throw new AIPValidatorException( "invalid or missing input param with or before value " + value, e );
        }
    }
}
