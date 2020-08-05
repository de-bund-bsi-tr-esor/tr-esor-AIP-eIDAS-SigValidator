package de.bund.bsi.tresor.xaip.validator.api.entity;

/**
 * Runtime exception of the xaip validator
 * 
 * @author wolffs
 */
public class XAIPValidatorException extends RuntimeException
{
    
    private static final long serialVersionUID = -7911422775033306759L;
    
    /**
     * Creating a new runtime exception with a simple message
     * 
     * @param message
     *            the message
     */
    public XAIPValidatorException( String message )
    {
        super( message );
    }
    
    /**
     * Creating a new runtime exception with a cause
     * 
     * @param message
     *            the message
     * @param cause
     *            the cause
     */
    public XAIPValidatorException( String message, Throwable cause )
    {
        super( message, cause );
    }
}
