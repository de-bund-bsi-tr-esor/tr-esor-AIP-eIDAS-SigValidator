package de.bund.bsi.tresor.xaip.validator.api.entity.xaip;

import java.io.InputStream;
import java.util.function.Function;

import javax.xml.bind.JAXBElement;

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
    CANONIC_XML( "http://www.w3.org/TR/2001/REC-xml-c14n-20010315", Canonicalization::canonic ),
    
    EXCLUSIVE_XML( "http://www.w3.org/2001/10/xml-exc-c14n", Canonicalization::exclusive );
    
    private final String                                url;
    private final Function<JAXBElement<?>, InputStream> function;
    
    private static InputStream canonic( JAXBElement<?> e )
    {
        return null;
    }
    
    private static InputStream exclusive( JAXBElement<?> e )
    {
        return null;
    }
}