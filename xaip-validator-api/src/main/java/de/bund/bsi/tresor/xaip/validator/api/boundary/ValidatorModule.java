package de.bund.bsi.tresor.xaip.validator.api.boundary;

import java.util.Map;

/**
 * @author wolffs
 */
public interface ValidatorModule
{
    public String getVersion();
    
    public String getVendor();
    
    default public void configure( Map<String, String> properties )
    {
        // no configuration required by default, this is only optional
    }
}
