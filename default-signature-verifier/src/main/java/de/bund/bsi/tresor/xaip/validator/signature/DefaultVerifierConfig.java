package de.bund.bsi.tresor.xaip.validator.signature;

import java.util.Map;
import java.util.Optional;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Configuration class of the {@link DefaultSignatureVerifier}
 * 
 * @author wolffs
 */
@Getter
@Setter( value = AccessLevel.PRIVATE )
@NoArgsConstructor( access = AccessLevel.PRIVATE )
public class DefaultVerifierConfig
{
    public Optional<String> wsdlUrl  = Optional.empty();
    
    public Optional<String> user     = Optional.empty();
    public Optional<String> password = Optional.empty();
    
    /**
     * Creating a {@link DefaultVerifierConfig} by parsing a property map
     * 
     * @param arguments
     *            the arguments for this module
     * @return the parsed configuration
     */
    public static DefaultVerifierConfig fromArguments( Map<String, String> arguments )
    {
        var config = new DefaultVerifierConfig();
        config.setWsdlUrl( Optional.ofNullable( arguments.get( "verifier.wsdlUrl" ) ) );
        config.setUser( Optional.ofNullable( arguments.get( "verifier.user" ) ) );
        config.setPassword( Optional.ofNullable( arguments.get( "verifier.pass" ) ) );
        
        return config;
    }
}
