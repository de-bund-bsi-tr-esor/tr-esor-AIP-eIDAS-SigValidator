package de.bund.bsi.tresor.aip.validator.signature.checker;

import java.io.ByteArrayInputStream;

import de.bund.bsi.tresor.aip.validator.api.control.ModuleLogger;
import eu.europa.esig.dss.asic.cades.validation.ASiCContainerWithCAdESValidatorFactory;
import eu.europa.esig.dss.asic.xades.validation.ASiCContainerWithXAdESValidatorFactory;
import eu.europa.esig.dss.model.DSSDocument;
import eu.europa.esig.dss.model.InMemoryDocument;

/**
 * Singleton to check data for possible ASiC
 * 
 * @author wolffs
 */
public enum ASiCChecker
{
    INSTANCE;
    
    private static ASiCContainerWithCAdESValidatorFactory cadesASiCValidator = new ASiCContainerWithCAdESValidatorFactory();
    private static ASiCContainerWithXAdESValidatorFactory xadesASiCValidator = new ASiCContainerWithXAdESValidatorFactory();
    
    /**
     * Checking if the data contains a valid ASiC container
     * 
     * @param data
     *            the document data
     * @return if the data could contain an ASiC container
     */
    public boolean isASiC( byte[] data )
    {
        try
        {
            DSSDocument document = new InMemoryDocument( new ByteArrayInputStream( data ) );
            
            boolean isCAdESASiC = cadesASiCValidator.isSupported( document );
            boolean isXAdESASiC = xadesASiCValidator.isSupported( document );
            
            boolean isASiC = isCAdESASiC || isXAdESASiC;
            ModuleLogger.verbose( isASiC ? "data is ASiC" : "data is not ASiC" );
            
            return isASiC;
        }
        catch ( Exception e )
        {
            ModuleLogger.verbose( "data is not ASiC", e );
        }
        
        return false;
    }
}
