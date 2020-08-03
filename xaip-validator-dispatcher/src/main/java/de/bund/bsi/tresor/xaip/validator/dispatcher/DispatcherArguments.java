package de.bund.bsi.tresor.xaip.validator.dispatcher;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

/**
 * Interface of arguments for the dispatcher.
 * 
 * @author wolffs
 */
public interface DispatcherArguments
{
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
     * Dispatcher argument for the result outputStream. The validation result will be written to this stream.
     * 
     * @return the outputStream
     */
    public OutputStream getOutput();
    
    /**
     * Dispatcher argument for the logger outputStream. The logger will use this stream to write the log entries.
     * 
     * @return the outputStream
     */
    public OutputStream getLog();
    
    /**
     * Dispatcher argument for the individual modules the dispatcher is managing.
     * 
     * @return the module configuration properties
     */
    public Map<String, String> getModuleConfig();
    
}
