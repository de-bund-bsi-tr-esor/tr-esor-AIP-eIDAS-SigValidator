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
package de.bund.bsi.tresor.aip.validator.api.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * A class which is managing additional context for the modules. Additional context can be used for custom results or arguments by the
 * module which are required for custom implementations but aren't available using the default module interface properties.
 * 
 * @author wolffs
 */
public class ModuleContext
{
    private final Map<Class<?>, Object> contextData = new HashMap<>();
    
    /**
     * Adding module specific context to the validator which prepending modules can use. This can be used to pass additional results or
     * arguments to any module which is going to be called in the future. Therefore the module has to know the context class by importing it
     * from the producing module.</br>
     * Since context data can be added from any module, please refrain from using simple dataTypes which can be easily overwritten when
     * another module is doing the same mistake.
     * 
     * @param <T>
     *            type of the module context
     * @param type
     *            type of the module context
     * @param value
     *            context value
     */
    public <T> void put( Class<T> type, T value )
    {
        contextData.put( type, value );
    }
    
    /**
     * Searching for a value associated with the provided context type.
     * 
     * @param <T>
     *            the context type
     * @param type
     *            the context type
     * @return the associated value which might be non-/present
     */
    @SuppressWarnings( "unchecked" )
    public <T> Optional<T> find( Class<T> type )
    {
        return Optional.ofNullable( (T) contextData.get( type ) );
    }
}
