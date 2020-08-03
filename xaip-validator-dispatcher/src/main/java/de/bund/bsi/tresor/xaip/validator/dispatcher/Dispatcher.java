package de.bund.bsi.tresor.xaip.validator.dispatcher;

import static java.util.stream.Collectors.toMap;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.ServiceLoader;

import javax.xml.bind.JAXB;

import de.bund.bsi.tresor.xaip.validator.api.boundary.ProtocolAssembler;
import de.bund.bsi.tresor.xaip.validator.api.boundary.SignatureFinder;
import de.bund.bsi.tresor.xaip.validator.api.boundary.SignatureVerifier;
import de.bund.bsi.tresor.xaip.validator.api.boundary.SyntaxValidator;
import de.bund.bsi.tresor.xaip.validator.api.boundary.ValidatorModule;
import de.bund.bsi.tresor.xaip.validator.api.control.ModuleLogger;
import de.bund.bsi.tresor.xaip.validator.api.entity.SyntaxValidationResult;
import de.bund.bsi.tresor.xaip.validator.api.entity.XAIPValidatorException;
import oasis.names.tc.dss._1_0.core.schema.SignatureObject;
import oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_.IndividualReportType;
import oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_.VerificationReportType;

/**
 * Dispatcher element of the XAIPValidator. This is the control segment of the validator which implements the outer API functions of the
 * validator. Those API calls will be prepared and redirected to the designated modules in a specific order to ensure a correct validation
 * of the XAIP.
 * 
 * @author wolffs
 */
public enum Dispatcher
{
    INSTANCE;
    
    private SignatureFinder   sigFinder;
    private SignatureVerifier sigVerifier;
    
    private SyntaxValidator   syntaxValidator;
    private ProtocolAssembler protocolAssembler;
    
    /**
     * Triggering and managing the XAIP validation by requesting the modules in a specific order and processing their responses. The
     * {@link DispatcherArguments} are being used to configure and provide informations to the dispatcher which are being used by the
     * dispatcher itself or being relayed to a specific module.<br>
     * The modules are called in the following order:<br>
     * <ol>
     * <li>SyntaxValidator
     * <li>SignatureFinder
     * <li>SignatureValidator (optional)
     * <li>ProtocolAssembler
     * </ol>
     * 
     * The signature validation will be triggered by the {@link DispatcherArguments#isVerify()} flag. Output will be generated on the
     * configured logger stream {@link DispatcherArguments#getLog()} and the result stream {@link DispatcherArguments#getOutput()}.
     * 
     * @param args
     *            the dispatcher arguments
     */
    public void dispatch( DispatcherArguments args )
    {
        ModuleLogger.initConfig( args.isVerbose(), args.getLog() );
        loadModules( args.getModuleConfig() );
        
        SyntaxValidationResult syntaxResult = syntaxValidator.validateSyntax( args.getInput() );
        ModuleLogger.log( "finished syntax validation" );
        
        IndividualReportType syntaxReport = syntaxResult.getSyntaxReport();
        
        List<IndividualReportType> reportParts = new ArrayList<>();
        reportParts.add( syntaxReport );
        
        if ( args.isVerify() )
        {
            syntaxResult.getXaip().ifPresent( xaip -> {
                List<SignatureObject> signatures = sigFinder.findSignatures( xaip );
                ModuleLogger.log( signatures.size() + " signatures found" );
                ModuleLogger.log( "finished signature search" );
                
                ModuleLogger.log( "finished signature verification" );
                reportParts.addAll( sigVerifier.verify( signatures ) );
            } );
        }
        
        VerificationReportType verificationReport = protocolAssembler.assembleProtocols( reportParts );
        ModuleLogger.log( "finished protocol assembling" );
        
        JAXB.marshal( verificationReport, args.getOutput() );
    }
    
    /**
     * Loading and configuring all validator modules.
     * 
     * @param configProperties
     *            possible configuration properties
     */
    void loadModules( Map<String, String> configProperties )
    {
        sigFinder = loadModule( SignatureFinder.class, "finder", configProperties );
        sigVerifier = loadModule( SignatureVerifier.class, "verifier", configProperties );
        
        syntaxValidator = loadModule( SyntaxValidator.class, "validator", configProperties );
        protocolAssembler = loadModule( ProtocolAssembler.class, "assembler", configProperties );
    }
    
    /**
     * Loads a module implementation of the provided moduleClass.
     * 
     * @param <T>
     *            type of the module
     * @param moduleClass
     *            class of the module interface
     * @return the loaded module implementation
     */
    <T extends ValidatorModule> T loadModule( Class<T> moduleClass, String paramPrefix, Map<String, String> configProperties )
    {
        String moduleName = moduleClass.getSimpleName();
        T module = ServiceLoader.load( moduleClass )
                .findFirst()
                .orElseThrow( () -> new XAIPValidatorException( "no provider found for module " + moduleName ) );
        
        String vendor = module.getVendor();
        String version = module.getVersion();
        
        configureModule( module, paramPrefix, vendor, configProperties );
        
        ModuleLogger.log( MessageFormat.format( "loaded {0} by {1} in version {2}", moduleName, vendor, version ) );
        
        return module;
    }
    
    @SuppressWarnings( { "unchecked", "rawtypes" } )
    <T extends ValidatorModule> void configureModule( T module, String paramPrefix, String vendor, Map<String, String> configProperties )
    {
        String moduleProperty = String.join( ".", paramPrefix, vendor ).toLowerCase();
        String modulePropertyLocation = String.join( ".", moduleProperty, "conf" );
        
        Map<String, String> moduleConfigProperties = new HashMap<>();
        if ( configProperties.containsKey( modulePropertyLocation ) )
        {
            String configLocation = Optional.ofNullable( configProperties.get( modulePropertyLocation ) )
                    .orElseThrow( () -> new XAIPValidatorException( modulePropertyLocation + " does not point to a valid location" ) );
            
            try ( InputStream in = new FileInputStream( configLocation ) )
            {
                Properties externalConfig = new Properties();
                externalConfig.load( in );
                
                moduleConfigProperties = (Map) externalConfig;
            }
            catch ( Exception e )
            {
                throw new XAIPValidatorException( "could not load external module configuration from " + configLocation, e );
            }
        }
        else
        {
            moduleConfigProperties = configProperties.entrySet().stream()
                    .filter( entry -> entry.getKey().toLowerCase().startsWith( moduleProperty ) )
                    .collect( toMap( entry -> entry.getKey().substring( moduleProperty.length() + 1 ), entry -> entry.getValue() ) );
        }
        
        ModuleLogger.verbose( "configuration for " + moduleProperty + ": " + moduleConfigProperties );
        
        module.configure( moduleConfigProperties );
    }
    
}
