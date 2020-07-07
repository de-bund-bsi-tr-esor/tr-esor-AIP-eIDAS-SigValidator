package de.bund.bsi.tresor.xaip.validator.api;

/**
 * @author wolffs
 */
public interface SignatureFinder
{
    void findASiC();
    
    void findCAdES();
    
    void findPAdES();
    
    void findTSP();
    
    void findXAdES();
}
