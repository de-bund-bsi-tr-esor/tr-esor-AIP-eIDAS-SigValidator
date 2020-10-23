package de.bund.bsi.tresor.xaip.validator.api.entity.xaip;

import java.util.EnumSet;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Representation of all XAIP metaData categories which can be used
 * 
 * @author wolffs
 */
@Getter
@AllArgsConstructor
public enum Category
{
    DMD( EnumSet.of( Classification.DESCRIPTION, Classification.OTHER ) ),
    
    REP( EnumSet.of( Classification.SYNTAX, Classification.DED, Classification.OTHER ) ),
    
    PDI( EnumSet.of( Classification.REFERENCE, Classification.CONTEXT, Classification.PROVENANCE, Classification.OTHER ) ),
    
    OTHER( EnumSet.allOf( Classification.class ) );
    
    private final EnumSet<Classification> classifications;
}
