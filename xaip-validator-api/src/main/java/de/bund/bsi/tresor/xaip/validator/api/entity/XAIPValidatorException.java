package de.bund.bsi.tresor.xaip.validator.api.entity;

/**
 * @author wolffs
 */
public class XAIPValidatorException extends RuntimeException
{
    
    private static final long serialVersionUID = -7911422775033306759L;
    
    public XAIPValidatorException( String message )
    {
        super( message );
    }
    
    public XAIPValidatorException( String message, Throwable reason )
    {
        super( message, reason );
    }
    
}
