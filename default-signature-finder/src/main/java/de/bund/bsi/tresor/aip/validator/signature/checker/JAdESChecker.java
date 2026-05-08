package de.bund.bsi.tresor.aip.validator.signature.checker;

import java.util.List;

import org.jose4j.jwx.HeaderParameterNames;

import de.bund.bsi.tresor.aip.validator.api.control.ModuleLogger;
import eu.europa.esig.dss.jades.DSSJsonUtils;
import eu.europa.esig.dss.jades.JAdESHeaderParameterNames;
import eu.europa.esig.dss.jades.JWSCompactSerializationParser;
import eu.europa.esig.dss.jades.JWSJsonSerializationObject;
import eu.europa.esig.dss.jades.JWSJsonSerializationParser;
import eu.europa.esig.dss.jades.validation.JWS;
import eu.europa.esig.dss.model.DSSDocument;
import eu.europa.esig.dss.model.InMemoryDocument;
import eu.europa.esig.dss.utils.Utils;

/**
 * Singleton to check data for possible JAdES signatures.
 *
 * This checker performs STRUCTURAL detection only. - No cryptographic validation - No certificate validation - No trust evaluation
 *
 * It distinguishes JAdES from plain JWS based on ETSI TS 119 182-1.
 */
public enum JAdESChecker
{
    
    INSTANCE;
    
    /**
     * Checks whether the given data could contain a JAdES signature.
     *
     * @param data
     *         the raw document bytes
     * @return true if the structure matches a JAdES signature
     */
    public boolean isJAdES( byte[] data )
    {
        boolean isJAdES = false;
        try
        {
            isJAdES = isJAdESStructure( data );
        }
        catch ( Exception e )
        {
            // intentionally ignored
        }
        ModuleLogger.verbose( isJAdES ? "data is JAdES" : "data is not JAdES" );
        return isJAdES;
    }
    
    private boolean isJAdESStructure( byte[] data )
    {
        if ( data == null || data.length == 0 )
        {
            return false;
        }
        
        DSSDocument document = new InMemoryDocument( data );
        
        if ( DSSJsonUtils.isJsonDocument( document ) )
        {
            return isJAdESJsonSerialization( document );
        }
        
        return isJAdESCompactSerialization( document );
    }
    
    private boolean isJAdESCompactSerialization( DSSDocument document )
    {
        try
        {
            JWSCompactSerializationParser parser = new JWSCompactSerializationParser( document );
            if ( !parser.isSupported() )
            {
                return false;
            }
            
            JWS jws = parser.parse();
            if ( !hasSignatureValue( jws ) )
            {
                return false;
            }
            
            return isJAdESJws( jws );
        }
        catch ( Exception e )
        {
            return false;
        }
    }
    
    private boolean isJAdESJsonSerialization( DSSDocument document )
    {
        try
        {
            JWSJsonSerializationParser parser = new JWSJsonSerializationParser( document );
            JWSJsonSerializationObject jwsObject = parser.parse();
            
            if ( !jwsObject.isValid() )
            {
                return false;
            }
            
            List<JWS> signatures = jwsObject.getSignatures();
            if ( Utils.isCollectionEmpty( signatures ) )
            {
                return false;
            }
            
            for ( JWS jws : signatures )
            {
                if ( hasSignatureValue( jws ) && isJAdESJws( jws ) )
                {
                    return true;
                }
            }
            
            return false;
        }
        catch ( Exception e )
        {
            return false;
        }
    }
    
    /**
     * Core structural JAdES detection.
     */
    private boolean isJAdESJws( JWS jws )
    {
        try
        {
            // mandatory JWS requirement
            if ( !hasProtectedAlgorithm( jws ) )
            {
                return false;
            }
            
            // structural ETSI schema validation
            List<?> schemaErrors = DSSJsonUtils.validateAgainstJAdESSchema( jws );
            if ( Utils.isCollectionNotEmpty( schemaErrors ) )
            {
                return false;
            }
            
            // JAdES-specific structure:
            // - at least one ETSI-defined signed header
            // - at least one certificate reference
            return hasProtectedEtsiHeader( jws )
                    && hasCertificateReference( jws );
            
        }
        catch ( Exception e )
        {
            return false;
        }
    }
    
    private boolean hasSignatureValue( JWS jws )
    {
        return jws != null && Utils.isArrayNotEmpty( jws.getSignatureValue() );
    }
    
    private boolean hasProtectedAlgorithm( JWS jws )
    {
        return Utils.isStringNotBlank(
                jws.getProtectedHeaderValueAsString( HeaderParameterNames.ALGORITHM ) );
    }
    
    /**
     * Checks presence of at least one ETSI-defined SIGNED header parameter. Purely structural – no semantic interpretation.
     */
    private boolean hasProtectedEtsiHeader( JWS jws )
    {
        return  hasProtectedHeader(jws, "iat") // iat (mandatory since 2025), HeaderParameterNames.ISSUED_AT is not present in DSS 6.0
                || hasProtectedHeader( jws, JAdESHeaderParameterNames.SIG_T ) // legacy
                        || hasProtectedHeader( jws, JAdESHeaderParameterNames.SIG_D )
                        || hasProtectedHeader( jws, JAdESHeaderParameterNames.SIG_PID )
                        || hasProtectedHeader( jws, JAdESHeaderParameterNames.SR_ATS )
                        || hasProtectedHeader( jws, JAdESHeaderParameterNames.SR_CMS )
                        || hasProtectedHeader( jws, JAdESHeaderParameterNames.SIG_PL )
                        || hasProtectedHeader( jws, JAdESHeaderParameterNames.ADO_TST );
    }
    
    /**
     * Checks for presence of a certificate reference as required by ETSI TS 119 182-1.
     */
    private boolean hasCertificateReference( JWS jws )
    {
        return hasProtectedHeader( jws, JAdESHeaderParameterNames.X5T_O )
                || hasProtectedHeader( jws, JAdESHeaderParameterNames.SIG_X5T_S )
                || hasProtectedHeader( jws, HeaderParameterNames.X509_CERTIFICATE_CHAIN ) // x5c
                || hasProtectedHeader( jws, HeaderParameterNames.X509_CERTIFICATE_SHA256_THUMBPRINT ); // x5t#S256
    }
    
    private boolean hasProtectedHeader( JWS jws, String headerName )
    {
        return Utils.isStringNotBlank( jws.getProtectedHeaderValueAsString( headerName ) )
                || Utils.isMapNotEmpty( jws.getProtectedHeaderValueAsMap( headerName ) )
                || Utils.isCollectionNotEmpty( jws.getProtectedHeaderValueAsList( headerName ) );
    }
}