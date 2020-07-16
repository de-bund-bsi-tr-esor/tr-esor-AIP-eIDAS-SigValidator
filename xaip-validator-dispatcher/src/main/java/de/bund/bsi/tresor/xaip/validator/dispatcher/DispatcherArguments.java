package de.bund.bsi.tresor.xaip.validator.dispatcher;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

/**
 * @author wolffs
 */
public interface DispatcherArguments
{
    public URI getECardUrl();
    
    public boolean isVerify();
    
    public boolean isVerbose();
    
    public InputStream getInput();
    
    public OutputStream getOutput();
    
}
