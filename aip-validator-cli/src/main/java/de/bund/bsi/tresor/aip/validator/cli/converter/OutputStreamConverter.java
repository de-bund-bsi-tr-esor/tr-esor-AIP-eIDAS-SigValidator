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

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Path;

import com.beust.jcommander.IStringConverter;

import de.bund.bsi.tresor.aip.validator.api.entity.AIPValidatorException;

/**
 * Provider class for overall functional outputstream conversions which can be used by multiple consumers.
 * 
 * @author wolffs
 */
public class OutputStreamConverter implements IStringConverter<OutputStream>
{
    /**
     * Creating a {@link FileOutputStream} by parsing the value as a {@link Path}. When no value is being provided, a new file in the
     * current working directory will be created using the defaultFileName. If a provided path does not exist it will be created.
     * 
     * @param value
     *            a file path
     * @return the {@link FileOutputStream}
     */
    @Override
    public OutputStream convert( String value )
    {
        try
        {
            FileOutputStream output;
            File file = new File( value );
            if ( file.exists() )
            {
                output = new FileOutputStream( file );
            }
            else
            {
                if ( !file.exists() )
                {
                    file.getParentFile().mkdirs();
                }
                
                output = new FileOutputStream( file );
            }
            
            return output;
        }
        catch ( Exception e )
        {
            throw new AIPValidatorException( "invalid or missing output param with or before value " + value, e );
        }
    }
}
