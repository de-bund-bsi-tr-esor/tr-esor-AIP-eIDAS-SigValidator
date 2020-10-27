package de.bund.bsi.tresor.xaip.validator.api.entity.xaip;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Representation of all possible canonicalization methods
 * 
 * @author wolffs
 */
@Getter
@RequiredArgsConstructor
public enum Canonicalization
{
    CANONIC_XML( "http://www.w3.org/TR/2001/REC-xml-c14n-20010315" ),
    
    EXCLUSIVE_XML( "https://www.w3.org/TR/2002/REC-xml-exc-c14n" );
    
    private final String url;
}