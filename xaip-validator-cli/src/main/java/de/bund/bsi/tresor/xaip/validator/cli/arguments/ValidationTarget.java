package de.bund.bsi.tresor.xaip.validator.cli.arguments;

import java.util.Arrays;

import de.bund.bsi.tresor.xaip.validator.api.XAIPValidatorException;
import lombok.Getter;

/**
 * @author wolffs
 */
public enum ValidationTarget
{
    ASiC( 'a' ),
    
    CAdES( 'c' ),
    
    PAdES( 'p' ),
    
    TSP( 't' ),
    
    XAdES( 'x' );
    
    @Getter
    private final char shortValue;
    
    ValidationTarget( char shortValue )
    {
        this.shortValue = shortValue;
    }
    
    public static ValidationTarget typeOf( char shortValue )
    {
        return Arrays.stream( ValidationTarget.values() )
                .filter( type -> type.getShortValue() == shortValue )
                .findAny()
                .orElseThrow( () -> new XAIPValidatorException( "invalid type of " + shortValue + " found" ) );
    }
}
