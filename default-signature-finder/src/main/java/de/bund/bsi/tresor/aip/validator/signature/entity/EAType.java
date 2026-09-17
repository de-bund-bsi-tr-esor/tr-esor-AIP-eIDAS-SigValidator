package de.bund.bsi.tresor.aip.validator.signature.entity;

import com.nimbusds.jwt.JWTClaimsSet;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

/**
 * Types of electronic attestation of attributes, distinguished by the SD-JWT-VC {@code category} claim.
 */
public enum EAType
{
    EAA( null, false, false ),
    PUBEA( "urn:etsi:esi:eaa:eu:pub", true, true ),
    QEAA( "urn:etsi:esi:eaa:eu:qualified", true, true );
    
    private Optional<String> category;
    private boolean          requiresIssuingAuthority;
    private boolean          requiresIssuingCountry;
    
    EAType( String category, boolean requiresIssuingAuthority, boolean requiresIssuingCountry )
    {
        this.category = Optional.ofNullable( category );
        this.requiresIssuingAuthority = requiresIssuingAuthority;
        this.requiresIssuingCountry = requiresIssuingCountry;
    }
    
    /**
     * Checking if the provided claims match this EA type's expected {@code category} and required issuer claims
     *
     * @param claims
     *            the parsed JWT claims
     * @return true if the claims match this type
     */
    public boolean isType( JWTClaimsSet claims )
    {
        try
        {
            boolean hasIA = Optional.ofNullable( claims.getClaimAsString( "issuing_authority" ) ).isPresent();
            boolean hasIC = Optional.ofNullable( claims.getClaimAsString( "issuing_country" ) ).isPresent();
            String cat = Optional.ofNullable( claims.getClaimAsString( "category" ) ).orElse("");
            
            return category.map( cat::equals ).orElseGet( () -> StringUtils.isBlank( cat ) )
                    && (!requiresIssuingAuthority || hasIA)
                    && (!requiresIssuingCountry || hasIC);
        }
        catch ( Exception e )
        {
            // ModuleLogger.verbose( "could not parse jwt", e );
            return false;
        }
    }
}
