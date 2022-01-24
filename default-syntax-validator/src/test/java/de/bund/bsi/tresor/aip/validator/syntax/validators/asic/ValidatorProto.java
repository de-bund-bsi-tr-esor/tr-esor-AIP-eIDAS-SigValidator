package de.bund.bsi.tresor.aip.validator.syntax.validators.asic;

import java.io.File;
import java.util.Optional;

import de.bund.bsi.tresor.aip.validator.syntax.asic.Foolidator;
import eu.europa.esig.dss.asic.cades.validation.ASiCContainerWithCAdESValidator;
import eu.europa.esig.dss.asic.cades.validation.ASiCContainerWithCAdESValidatorFactory;
import eu.europa.esig.dss.asic.common.validation.AbstractASiCContainerValidator;
import eu.europa.esig.dss.asic.xades.validation.ASiCContainerWithXAdESValidator;
import eu.europa.esig.dss.asic.xades.validation.ASiCContainerWithXAdESValidatorFactory;
import eu.europa.esig.dss.model.DSSDocument;
import eu.europa.esig.dss.model.FileDocument;

/**
 * @author wolffs
 */
public class ValidatorProto
{
    private static ASiCContainerWithCAdESValidatorFactory cadesASiCValidator = new ASiCContainerWithCAdESValidatorFactory();
    private static ASiCContainerWithXAdESValidatorFactory xadesASiCValidator = new ASiCContainerWithXAdESValidatorFactory();
    
    static Optional<AbstractASiCContainerValidator> findValidatorImpl( DSSDocument input )
    {
        return Optional.ofNullable( cadesASiCValidator.isSupported( input ) ? new ASiCContainerWithCAdESValidator( input )
                : xadesASiCValidator.isSupported( input ) ? new ASiCContainerWithXAdESValidator( input ) : null );
    }
    
    public static void main( String[] args )
    {
        Foolidator f = new Foolidator();
        File file = new File( "/home/wolffs/Dokumente/asic/asicAip/asic-single.zip" );
        // File file = new File( "/home/wolffs/Dokumente/asic/asicAip/211122-LXAIP.asice" );
        FileDocument doc = new FileDocument( file );
        
        AbstractASiCContainerValidator validator = findValidatorImpl( doc )
                .orElseThrow( () -> new RuntimeException( "nope" ) );
        
        f.validateASiCAIP( doc );
        
        // List<CompareableManifest> comparables = f.extractArchiveManifest( validator );
        // System.out.println( comparables.size() );
        // System.out.println( comparables.get( 0 ) );
        System.out.println( "done" );
    }
}
