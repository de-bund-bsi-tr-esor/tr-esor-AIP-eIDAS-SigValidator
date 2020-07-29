package de.bund.bsi.tresor.xaip.validator.dispatcher;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

/**
 * Interface of arguments for the dispatcher.
 * 
 * @author wolffs
 */
public interface DispatcherArguments
{
    public URI getECardUrl();
    
    /**
     * Dispatcher argument if the signatures of the provided XAIP should be validated. Using isVerify with a value of <code>false</code>
     * only the XAIP schema will be validated and the signature verification step is being skipped.
     * 
     * @return isVerify signatures enabled
     */
    public boolean isVerify();
    
    /**
     * Dispatcher argument if verbose logging over all modules should be enabled.
     * 
     * @return isVerbose logging enabled
     */
    public boolean isVerbose();
    
    /**
     * Dispatcher argument for the inputStream which is being used. This stream is being consumed by the dispatcher and needs to provide an
     * XAIP for validation.
     * 
     * @return the inputStream containing an XAIP
     */
    public InputStream getInput();
    
    /**
     * Dispatcher argument for the outputStream. The logger will use this stream to write the log entries also the validation result will be
     * written to this stream.
     * 
     * @return the outputStream
     */
    public OutputStream getOutput();
    
}
