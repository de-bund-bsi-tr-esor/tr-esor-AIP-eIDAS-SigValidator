package de.bund.bsi.tresor.xaip.validator.signature.entity;

import java.util.Optional;

/**
 * @author wolffs
 */
public enum SignaturePresence
{
    UNKNOWN, PRESENT, MISSING;
    
    public static SignaturePresence fromBoolean( Boolean value )
    {
        return Optional.ofNullable( value )
                .map( val -> val ? PRESENT : MISSING )
                .orElse( UNKNOWN );
    }
}
