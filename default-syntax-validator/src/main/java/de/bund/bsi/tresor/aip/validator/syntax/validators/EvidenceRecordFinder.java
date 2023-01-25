package de.bund.bsi.tresor.aip.validator.syntax.validators;

import de.bund.bsi.tresor.aip.validator.api.control.ModuleLogger;
import eu.europa.esig.dss.asic.cades.validation.ASiCContainerWithCAdESValidator;
import eu.europa.esig.dss.asic.common.ASiCUtils;
import eu.europa.esig.dss.asic.common.validation.AbstractASiCContainerValidator;
import eu.europa.esig.dss.asic.xades.validation.ASiCContainerWithXAdESValidator;
import eu.europa.esig.dss.model.InMemoryDocument;

/**
 * Utility to identify the evidenceRecord
 * 
 * @author wolffs
 */
public class EvidenceRecordFinder
{
    
    /**
     * Detecting if an evidenceRecord is inside the provided asic and adding the asicContainer to the syntaxContext if an evidenceReport was
     * found
     * 
     * @param asic
     *            the asic container
     */
    public static void findEvidenceRecordManifest( byte[] asic )
    {
        var dssDocument = new InMemoryDocument( asic );
        ASiCContainerWithCAdESValidator cAdESValidator = new ASiCContainerWithCAdESValidator( dssDocument );
        ASiCContainerWithXAdESValidator xAdESValidator = new ASiCContainerWithXAdESValidator( dssDocument );
        
        boolean cAdESValidatorIsSupported = cAdESValidator.isSupported( dssDocument );
        if ( !cAdESValidatorIsSupported && !xAdESValidator.isSupported( dssDocument ) )
        {
            return;
        }
        
        AbstractASiCContainerValidator validator = cAdESValidatorIsSupported ? cAdESValidator : xAdESValidator;
        
        validator.getUnsupportedDocuments().stream()
                .filter( dssDoc -> isEvidenceRecordArchiveManifest( dssDoc.getName() ) )
                .findAny()
                .ifPresent( doc -> ModuleLogger.log( "found embedded evidenceRecord in ASiC-AIP" ) );
    }
    
    /**
     * Checks if the fileName matches to an Archive Manifest name standard
     *
     * @param fileName
     *            {@link String} to check
     * @return TRUE if the given name matches ASiC Archive Manifest filename, FALSE otherwise
     */
    public static boolean isEvidenceRecordArchiveManifest( String fileName )
    {
        return fileName.startsWith( ASiCUtils.META_INF_FOLDER ) && fileName.contains( "ASiCEvidenceRecordManifest" )
                && fileName.endsWith( ASiCUtils.XML_EXTENSION );
    }
    
}
