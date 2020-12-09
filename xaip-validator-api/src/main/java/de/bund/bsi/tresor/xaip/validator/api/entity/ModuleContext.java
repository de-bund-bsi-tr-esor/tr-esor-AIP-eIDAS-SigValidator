package de.bund.bsi.tresor.xaip.validator.api.entity;

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
