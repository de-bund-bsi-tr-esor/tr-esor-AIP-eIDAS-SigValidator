package de.bund.bsi.tresor.xaip.validator.signature.entity;

/**
 * Exception for the case that the LXAIP verification fails.
 * 
 * @author bendlera
 */
public class LXAIPCheckerException extends Exception
{
    private static final long serialVersionUID = -6324794193611159488L;
    
    /**
     * Creating a new exception with a simple message
     * 
     * @param message
     *            the message
     */
    public LXAIPCheckerException( String message )
    {
        super( message );
    }
    
    /**
     * Creating a new exception with a cause
     * 
     * @param message
     *            the message
     * @param cause
     *            the cause
     */
    public LXAIPCheckerException( String message, Throwable cause )
    {
        super( message, cause );
    }
}
