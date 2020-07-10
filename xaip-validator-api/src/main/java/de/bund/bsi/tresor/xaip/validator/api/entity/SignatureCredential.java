package de.bund.bsi.tresor.xaip.validator.api.entity;

import java.io.InputStream;
import java.util.Optional;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

/**
 * @author wolffs
 */
@Value
@AllArgsConstructor( access = AccessLevel.PRIVATE )
public class SignatureCredential
{
    private final InputStream           document;
    private final Optional<InputStream> signature;
    
    public static SignatureCredential embedded( InputStream embeddedSignature )
    {
        return new SignatureCredential( embeddedSignature, Optional.empty() );
    }
    
    public static SignatureCredential detached( InputStream document, InputStream detachedSignature )
    {
        return new SignatureCredential( document, Optional.of( detachedSignature ) );
    }
}
