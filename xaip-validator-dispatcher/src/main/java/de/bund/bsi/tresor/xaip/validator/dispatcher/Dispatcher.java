/*-
 * Copyright (c) 2020
 * Federal Office for Information Security (BSI),
 * Godesberger Allee 185-189,
 * 53175 Bonn, Germany,
 * phone: +49 228 99 9582-0,
 * fax: +49 228 99 9582-5400,
 * e-mail: bsi@bsi.bund.de
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.bund.bsi.tresor.xaip.validator.dispatcher;

import java.io.OutputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import de.bund.bsi.tr_esor.vr._1.CredentialValidityType;
import de.bund.bsi.tr_esor.vr._1.XAIPValidityType;
import de.bund.bsi.tresor.xaip.validator.api.boundary.ProtocolAssembler;
import de.bund.bsi.tresor.xaip.validator.api.boundary.SignatureFinder;
import de.bund.bsi.tresor.xaip.validator.api.boundary.SignatureVerifier;
import de.bund.bsi.tresor.xaip.validator.api.boundary.SyntaxValidator;
import de.bund.bsi.tresor.xaip.validator.api.boundary.ValidatorModule;
import de.bund.bsi.tresor.xaip.validator.api.control.ModuleLogger;
import de.bund.bsi.tresor.xaip.validator.api.entity.ModuleContext;
import de.bund.bsi.tresor.xaip.validator.api.entity.SyntaxValidationResult;
import de.bund.bsi.tresor.xaip.validator.api.entity.XAIPValidatorException;
import oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_.ObjectFactory;
import oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_.VerificationReportType;

/**
 * Dispatcher element of the XAIPValidator. This is the control segment of the validator which implements the outer API functions of the
 * validator. Those API calls will be prepared and redirected to the designated modules in a specific order to ensure a correct validation
 * of the XAIP.
 * 
 * @author wolffs
 * @author bendlera
 */
public enum Dispatcher
{
    INSTANCE;
    
    private ObjectFactory     objectFactory = new ObjectFactory();
    
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
        ModuleContext ctx = new ModuleContext();
        ModuleLogger.initConfig( args.isVerbose(), args.getLog() );
        loadModules( args.getModuleConfig() );
        
        SyntaxValidationResult syntaxResult = syntaxValidator.validateSyntax( ctx, args.getInput() );
        ModuleLogger.log( "finished syntax validation" );
        
        XAIPValidityType xaipReport = syntaxResult.getSyntaxReport();
        List<CredentialValidityType> credentialReports = new ArrayList<>();
        
        if ( args.isVerify() )
        {
            syntaxResult.getXaip().ifPresent( xaip -> {
                Map<String, Set<String>> signatures = sigFinder.findSignatures( ctx, xaip );
                ModuleLogger.log( signatures.size() + " signatures found" );
                ModuleLogger.log( "finished signature search" );
                
                credentialReports.addAll( sigVerifier.verify( ctx, xaip, signatures ) );
                ModuleLogger.log( "finished signature verification" );
            } );
        }
        
        VerificationReportType verificationReport = protocolAssembler.assembleProtocols( ctx, xaipReport, credentialReports );
        ModuleLogger.log( "finished protocol assembling" );
        
        writeReport( verificationReport, args.getOutput() );
    }
    
    /**
     * Loading and configuring all validator modules.
     * 
     * @param configProperties
     *            possible configuration properties
     */
    void loadModules( Map<String, String> configProperties )
    {
        sigFinder = loadModule( SignatureFinder.class, configProperties );
        sigVerifier = loadModule( SignatureVerifier.class, configProperties );
        
        syntaxValidator = loadModule( SyntaxValidator.class, configProperties );
        protocolAssembler = loadModule( ProtocolAssembler.class, configProperties );
    }
    
    /**
     * Loads a module implementation of the provided moduleClass.
     * 
     * @param <T>
     *            type of the module
     * @param moduleClass
     *            class of the module interface
     * @param configProperties
     *            the configuration data
     * @return the loaded module implementation
     */
    <T extends ValidatorModule> T loadModule( Class<T> moduleClass, Map<String, String> configProperties )
    {
        String moduleName = moduleClass.getSimpleName();
        T module = ServiceLoader.load( moduleClass )
                .findFirst()
                .orElseThrow( () -> new XAIPValidatorException( "no provider found for module " + moduleName ) );
        
        String vendor = module.getVendor();
        String version = module.getVersion();
        
        module.configure( configProperties );
        
        ModuleLogger.log( MessageFormat.format( "loaded {0} by {1} in version {2}", moduleName, vendor, version ) );
        
        return module;
    }
    
    /**
     * Writes the report.
     * 
     * @param verificationReport
     *            report
     * @param outputStream
     *            output destination
     */
    void writeReport( VerificationReportType verificationReport, OutputStream outputStream )
    {
        try
        {
            JAXBContext context = JAXBContext.newInstance( VerificationReportType.class, XAIPValidityType.class );
            Marshaller marshaller = context.createMarshaller();
            
            marshaller.marshal( objectFactory.createVerificationReport( verificationReport ), outputStream );
        }
        catch ( JAXBException e )
        {
            ModuleLogger.verbose( "could not marshal verificationReport to the provided outputStream", e );
            throw new RuntimeException();
        }
    }
    
}
