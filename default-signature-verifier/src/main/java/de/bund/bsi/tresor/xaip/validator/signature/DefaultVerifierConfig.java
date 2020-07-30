package de.bund.bsi.tresor.xaip.validator.signature;

import java.util.Map;
import java.util.Optional;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Configuraion class of the {@link DefaultSignatureVerifier}
 * 
 * @author wolffs
 */
@Getter
@Setter( value = AccessLevel.PRIVATE )
@NoArgsConstructor( access = AccessLevel.PRIVATE )
public class DefaultVerifierConfig
{
    public static final String WSDL_URL  = "wsdlUrl";
    public static final String USER_NAME = "user";
    public static final String USER_PASS = "pass";
    
    public Optional<String>    wsdlUrl   = Optional.empty();
    
    public Optional<String>    user      = Optional.empty();
    public Optional<String>    password  = Optional.empty();
    
    // example: verifier.bsi.wsdlUrl
    
    public static DefaultVerifierConfig fromArguments( Map<String, String> arguments )
    {
        DefaultVerifierConfig config = new DefaultVerifierConfig();
        config.setWsdlUrl( Optional.ofNullable( arguments.get( WSDL_URL ) ) );
        config.setUser( Optional.ofNullable( arguments.get( USER_NAME ) ) );
        config.setPassword( Optional.ofNullable( arguments.get( USER_PASS ) ) );
        
        return config;
    }
}
