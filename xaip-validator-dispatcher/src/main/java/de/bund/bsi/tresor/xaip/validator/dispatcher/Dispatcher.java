package de.bund.bsi.tresor.xaip.validator.dispatcher;

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
    
    private VerboseLogger     logger;
    
    private SignatureFinder   sigFinder;
    private SignatureVerifier sigVerifier;
    
    private SyntaxValidator   syntaxValidator;
    private ProtocolAssembler protocolAssembler;
    
    public void dispatch( DispatcherArguments args ) throws FileNotFoundException
    {
        logger = new VerboseLogger( System.out, args.isVerbose() );
        loadModules();
        
        SyntaxValidationResult syntaxResult = syntaxValidator.validateSyntax( args.getInput() );
        logger.log( "finished syntax validation" );
        
        IndividualReportType syntaxReport = syntaxResult.getSyntaxReport();
        
        List<IndividualReportType> reportParts = new ArrayList<>();
        reportParts.add( syntaxReport );
        
        if ( args.isVerify() )
        {
            syntaxResult.getXaip().ifPresent( xaip -> {
                // TODO find
                logger.log( "finished signature search" );
                // TODO verify
                logger.log( "finished signature verification" );
            } );
        }
        
        VerificationReportType verificationReport = protocolAssembler.assembleProtocols( reportParts );
        logger.log( "finished protocol assembling" );
        
        JAXB.marshal( verificationReport, args.getOutput() );
    }
    
    void loadModules()
    {
        sigFinder = loadModule( SignatureFinder.class );
        
        sigVerifier = loadModule( SignatureVerifier.class );
        
        syntaxValidator = loadModule( SyntaxValidator.class );
        protocolAssembler = loadModule( ProtocolAssembler.class );
    }
    
    <T extends ValidatorModule> T loadModule( Class<T> moduleClass )
    {
        String moduleName = moduleClass.getSimpleName();
        T module = ServiceLoader.load( moduleClass )
                .findFirst()
                .orElseThrow( () -> new XAIPValidatorException( "no provider found for module " + moduleName ) );
        
        String vendor = module.getVendor();
        String version = module.getVersion();
        logger.log( MessageFormat.format( "loaded {0} by {1} in version {2}", moduleName, vendor, version ) );
        
        return module;
    }
}
