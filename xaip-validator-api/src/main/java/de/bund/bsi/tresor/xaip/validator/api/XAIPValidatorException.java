package de.bund.bsi.tresor.xaip.validator.api;

/**
 * @author wolffs
 */
public class XAIPValidatorException extends RuntimeException
{
    
    private static final long serialVersionUID = -7911422775033306759L;
    
    public XAIPValidatorException( String message )
    {
        super( message + " use -h for help" );
    }
    
    public XAIPValidatorException( String message, Throwable reason )
    {
        super( message + " use -h for help", reason );
    }
    
}
