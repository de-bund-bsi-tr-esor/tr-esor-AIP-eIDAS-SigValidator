package de.bund.bsi.tresor.xaip.validator.signature.entity;

import java.util.Optional;

/**
 * Representing a signature presence
 * 
 * @author wolffs
 */
public enum SignaturePresence
{
    UNKNOWN, PRESENT, MISSING;
    
    /**
     * Parsing a signaturePresence from the provided boolean:<br/>
     * <li><code>true</code> > {@link #PRESENT}
     * <li><code>false</code> > {@link #MISSING}
     * <li><code>null</code> > {@link #UNKNOWN}
     * 
     * @param value
     *            the boolean value
     * @return the signaturePresence
     */
    public static SignaturePresence fromBoolean( Boolean value )
    {
        return Optional.ofNullable( value )
                .map( val -> val ? PRESENT : MISSING )
                .orElse( UNKNOWN );
    }
}
