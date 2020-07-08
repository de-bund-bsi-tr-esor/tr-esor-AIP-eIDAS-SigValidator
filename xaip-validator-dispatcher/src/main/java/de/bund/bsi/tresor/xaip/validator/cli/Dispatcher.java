package de.bund.bsi.tresor.xaip.validator.cli;

import java.io.FileNotFoundException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

import javax.xml.bind.JAXB;

import de.bund.bsi.tresor.xaip.validator.api.boundary.ProtocolAssembler;
import de.bund.bsi.tresor.xaip.validator.api.boundary.SignatureFinder;
import de.bund.bsi.tresor.xaip.validator.api.boundary.SignatureVerifier;
import de.bund.bsi.tresor.xaip.validator.api.boundary.SyntaxValidator;
import de.bund.bsi.tresor.xaip.validator.api.boundary.ValidatorModule;
import de.bund.bsi.tresor.xaip.validator.api.entity.SyntaxValidationResult;
import de.bund.bsi.tresor.xaip.validator.api.entity.XAIPValidatorException;
import oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_.IndividualReportType;
import oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_.VerificationReportType;

/**
 * @author wolffs
 */
public enum Dispatcher
{
    INSTANCE;
    
    // find config?
    
    private SignatureFinder   sigFinder;                                                // = loadModule( SignatureFinder.class );
    private SignatureVerifier sigVerifier;                                              // = loadModule( SignatureVerifier.class );
    
    private SyntaxValidator   syntaxValidator   = loadModule( SyntaxValidator.class );
    private ProtocolAssembler protocolAssembler = loadModule( ProtocolAssembler.class );
    
    public void dispatch( DispatcherArguments args ) throws FileNotFoundException
    {
        SyntaxValidationResult syntaxResult = syntaxValidator.validateSyntax( args.getInput() );
        log( "finished syntax validation" );
        
        List<IndividualReportType> reportParts = new ArrayList<>();
        reportParts.add( syntaxResult.getSyntaxReport() );
        
        if ( args.isVerify() )
        {
            syntaxResult.getXaip().ifPresent( xaip -> {
                // TODO find
                // TODO verify
            } );
        }
        
        VerificationReportType verificationReport = protocolAssembler.assembleProtocols( reportParts );
        log( "finished protocol assembling" );
        
        JAXB.marshal( verificationReport, args.getOutput() );
    }
    
    void log( String message )
    {
        System.out.println( "[Dispatcher] " + message );
    }
    
    <T extends ValidatorModule> T loadModule( Class<T> moduleClass )
    {
        String moduleName = moduleClass.getSimpleName();
        T module = ServiceLoader.load( moduleClass )
                .findFirst()
                .orElseThrow( () -> new XAIPValidatorException( "no provider found for module " + moduleName ) );
        
        String vendor = module.getVendor();
        String version = module.getVersion();
        log( MessageFormat.format( "loaded {0} by {1} in version {2}", moduleName, vendor, version ) );
        
        return module;
    }
}
