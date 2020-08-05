package de.bund.bsi.tresor.xaip.validator.api.boundary;

import java.util.Map;

/**
 * Basic interface which each XAIPValidatorModule has to implement
 * 
 * @author wolffs
 */
public interface ValidatorModule
{
    /**
     * Version of the module which is always coupled to the vendor and can be in any format. The version will be printed on verbose logging
     * when the module is being loaded to identify the modules in use. This helps the user to analyse any problems in the future.
     * 
     * @return version the module version of the vendor
     */
    public String getVersion();
    
    /**
     * Vendor of the module which is always coupled to the version. The vendor name is used to separate different module implementations and
     * load the configuration for the module implementation. This name should <b>not contain whitespaces</b>!
     * 
     * @return name of the vendor
     */
    public String getVendor();
    
    /**
     * Optional method for configuring the module. This method only needs to be implemented when the module is using any external
     * configuration.<br/>
     * The property map will contain any module arguments being passed to the XAIPValidator on startup.<br/>
     * <br/>
     * Arguments being passed to the XAIPValidator will have the following structure:<br/>
     * <br/>
     * <b>Format:</b> <code>$MODULE.$VENDOR.$PROPERTY=$VALUE</code><br/>
     * <b>Example:</b> <code>verifier.bsi.wsdlUrl=http://localhost:8080/s4?wsdl</code><br/>
     * <br/>
     * <li><b>$MODULE:</b> name of the module (finder,verifier,validator,assembler)
     * <li><b>$VENDOR:</b> name of the {@link #getVendor()}
     * <li><b>$PROPERTY:</b> property name which occurs as key in the properties map
     * <li><b>$VALUE:</b> value mapped to the property name/key <br/>
     * <br/>
     * Compared to the <code>$MODULE</code> and <code>$VENDOR</code> check, the <code>$PROPERTY</code> and <code>$VALUE</code> will stay
     * caseSensitive.<br/>
     * <br/>
     * <i>Please refrain from using a property with the key <code>conf</code> since this keyword is reserved to specify a location of a
     * property file containing the complete property map of this configuration.</i><br/>
     * <br/>
     * <b>Example:</b><code>verifier.bsi.conf=/tmp/verifier.properties</code><br/>
     * <b>/tmp/verifier.properties:</b><br/>
     * <code>wsdlUrl=http://localhost:8080/s4?wsdl</code><br/>
     * <code>user=admin</code><br/>
     * <code>pass=secret</code><br/>
     * <br/>
     * For an example implementation take a look at the default signature verifier provided by the BSI.
     * 
     * @param properties
     *            the property map for this module
     */
    default public void configure( Map<String, String> properties )
    {
        // no configuration required by default, this is only optional
    }
}
