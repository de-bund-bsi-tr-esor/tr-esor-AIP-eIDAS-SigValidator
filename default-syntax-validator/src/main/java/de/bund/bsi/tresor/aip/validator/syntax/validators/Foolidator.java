package de.bund.bsi.tresor.aip.validator.syntax.validators;

import java.io.InputStream;
import java.util.Optional;

import eu.europa.esig.dss.asic.cades.validation.ASiCContainerWithCAdESValidator;
import eu.europa.esig.dss.asic.cades.validation.ASiCContainerWithCAdESValidatorFactory;
import eu.europa.esig.dss.asic.common.validation.AbstractASiCContainerValidator;
import eu.europa.esig.dss.asic.xades.validation.ASiCContainerWithXAdESValidator;
import eu.europa.esig.dss.asic.xades.validation.ASiCContainerWithXAdESValidatorFactory;
import eu.europa.esig.dss.model.DSSDocument;
import eu.europa.esig.dss.model.InMemoryDocument;

/**
 * @author wolffs
 */
public class Foolidator
{
    /**
     * Allgemein: <br/>
     * - C14N Methode aus XAIP muss mit einer Extension aus dem Manifest verglichen werden und identisch sein<br/>
     * - aoid = containerID-POID aus manifest<br/>
     * - version = versionID aus manifest<br/>
     * - optional: transform <br/>
     * - XAIP im root verzeichnis <br/>
     * - pro version ein manifest
     * 
     * ASIC-S<br/>
     * - Klärung: dataObjectRef<br/>
     * 
     * ASIC-E<br/>
     * 
     * XAIP<br/>
     * LXAIP<br/>
     * - keine kein transformationselement notwendig<br/>
     * - aoid, version & c14n müssen nicht verstanden werden (vermutl. bei aisc prüfung?)
     * 
     */
    
    private static ASiCContainerWithCAdESValidatorFactory cadesASiCValidator = new ASiCContainerWithCAdESValidatorFactory();
    private static ASiCContainerWithXAdESValidatorFactory xadesASiCValidator = new ASiCContainerWithXAdESValidatorFactory();
    
    /**
     * Analysing the proper validator for the document and returning the asicValidator implementation or an empty optional if input is not
     * asic
     * 
     * @param input
     *            the dss document
     * @return the proper asic validator or empty optional if input is not asic
     */
    static Optional<AbstractASiCContainerValidator> findValidatorImpl( DSSDocument input )
    {
        return Optional.ofNullable( cadesASiCValidator.isSupported( input ) ? new ASiCContainerWithCAdESValidator( input )
                : xadesASiCValidator.isSupported( input ) ? new ASiCContainerWithXAdESValidator( input ) : null );
    }
    
    void validateSyntax( InputStream in )
    {
        InMemoryDocument asicAip = new InMemoryDocument( in );
        Optional<AbstractASiCContainerValidator> optValidator = findValidatorImpl( asicAip );
        if ( optValidator.isEmpty() )
            return;
        
        AbstractASiCContainerValidator validator = optValidator.get();
        
        // TODO extract xaip
        // TODO extract manifest
    }
    
}
