package de.bund.bsi.tresor.aip.validator.signature.checker;

import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import de.bund.bsi.tresor.aip.validator.api.control.ModuleLogger;
import de.bund.bsi.tresor.aip.validator.signature.entity.EAAType;

import java.text.ParseException;
import java.util.Optional;

import static java.util.Arrays.stream;

/**
 * Idenifies QEAA, EAA and PubEAA
 */
public enum EAAChecker
{
    INSTANCE;

    /**
     * Checking if the provided data is an electronic attestation of attributes (EAA, QEAA or PubEAA) in SD-JWT-VC representation
     *
     * @param data
     *            the data to check
     * @return true if the data is an EAA type
     */
    public boolean isEAAType( byte[] data )
    {
        boolean isEAA = false;
        try
        {
            SignedJWT jwt = SignedJWT.parse( new String( data ) );
            JWTClaimsSet claims = jwt.getJWTClaimsSet();

            Optional<EAAType> eaaType = stream( EAAType.values() )
                    .filter( type -> type.isType( claims ) )
                    .findAny();

            if ( eaaType.isPresent() && isSDJWTVC( claims ) )
            {
                isEAA = true;
                ModuleLogger.verbose( "found eaa-type " + eaaType.get() );
            }
        }
        catch ( ParseException e )
        {
            // not an eaa type
            // ModuleLogger.verbose( "data is no eaa-type", e );
        }

        if ( !isEAA )
        {
            ModuleLogger.verbose( "data is no eaa-type" );
        }

        return isEAA;
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
