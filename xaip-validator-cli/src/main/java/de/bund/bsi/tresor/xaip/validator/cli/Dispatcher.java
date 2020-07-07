package de.bund.bsi.tresor.xaip.validator.cli;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.Optional;

import de.bund.bsi.tresor.xaip.validator.api.ProtocolAssembler;
import de.bund.bsi.tresor.xaip.validator.api.SignatureFinder;
import de.bund.bsi.tresor.xaip.validator.api.SignatureVerifier;
import de.bund.bsi.tresor.xaip.validator.api.SyntaxValidator;
import de.bund.bsi.tresor.xaip.validator.cli.arguments.Arguments;
import de.bund.bsi.tresor.xaip.validator.cli.arguments.VerifyCommand;

/**
 * @author wolffs
 */
public enum Dispatcher
{
    INSTANCE;
    
    // find in/out
    // find ecUrl
    // find config?
    // find actions
    // assemble results
    
    // TODO note: when missing findTargets and verificationTargets, then use validate as function
    
    SyntaxValidator   validator;
    
    SignatureFinder   finder;
    SignatureVerifier verifier;
    
    ProtocolAssembler assembler;
    
    public void dispatch( Optional<String> command, final Arguments arguments ) throws FileNotFoundException
    {
        InputStream in = arguments.getInput();
        OutputStream out = arguments.getOutput();
        
        URI eCardUrl = arguments.getECardUrl();
        
        Optional<VerifyCommand> verify = command.map( VerifyCommand.NAME::equals )
                .map( n -> arguments.getVerify() );
        
    }
}
