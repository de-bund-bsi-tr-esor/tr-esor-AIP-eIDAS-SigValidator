package de.bund.bsi.tresor.aip.validator.signature.checker;

import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import de.bund.bsi.tresor.aip.validator.api.control.ModuleLogger;
import de.bund.bsi.tresor.aip.validator.signature.entity.EAType;

import java.text.ParseException;
import java.util.Optional;

import static java.util.Arrays.stream;

/**
 * Idenifies QEAA, EAA and pubEA
 */
public enum EAChecker
{
    INSTANCE;
    
    public boolean isEAType( byte[] data )
    {
        boolean isEA = false;
        try
        {
            SignedJWT jwt = SignedJWT.parse( new String( data ) );
            JWTClaimsSet claims = jwt.getJWTClaimsSet();
            
            Optional<EAType> eaType = stream( EAType.values() )
                    .filter( type -> type.isType( claims ) )
                    .findAny();
            
            if ( eaType.isPresent() && isSDJWTVC( claims ) )
            {
                isEA = true;
                ModuleLogger.verbose( "found ea-type " + eaType.get() );
            }
        }
        catch ( ParseException e )
        {
            // not an ea type
            // ModuleLogger.verbose( "data is no ea-type", e );
        }
        
        if ( !isEA )
        {
            ModuleLogger.verbose( "data is no ea-type" );
        }
        
        return isEA;
    }
    
    // checking if all sd-jwt vc requirements are met
    boolean isSDJWTVC( JWTClaimsSet claims )
    {
        try
        {
            return Optional.ofNullable( claims.getClaimAsString( "vct" ) ).isPresent();
        }
        catch ( Exception e )
        {
            // ModuleLogger.verbose( "data is no sd-jwt vc", e );
            
            return false;
        }
        
    }
}
