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
     * when the module is being loaded to identify the modules in use. This helps the user to analyze and report problems to the vendor.
     * 
     * @return the module version of the vendor
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
     * Optional method for configuring the module. This method only needs to be implemented when the module depends on external
     * configuration.</br>
     * The property map will contain any module arguments being passed to the XAIPValidator on startup either via multiple arguments or a
     * property file containing all properties.</br>
     * </br>
     * <b>Examples:</b></br>
     * </br>
     * <b>Input:</b> <code>java -jar xaip-validator-cli.jar -Margument.first=foo -Margument.second=bar</code></br>
     * <b>Resolves to:</b></br>
     * <table border=1>
     * <tr>
     * <td>argument.first</td>
     * <td>foo</td>
     * </tr>
     * <tr>
     * <td>argument.second</td>
     * <td>bar</td>
     * </tr>
     * </table>
     * </br>
     * <b>Input:</b> <code>java -jar xaip-validator-cli.jar --config example.properties</code></br>
     * <b>Resolves to:</b></br>
     * <table border=1>
     * <tr>
     * <td>argument.first</td>
     * <td>foo</td>
     * </tr>
     * <tr>
     * <td>argument.second</td>
     * <td>bar</td>
     * </tr>
     * </table>
     * </br>
     * <b>Content of example.properties:</br>
     * <table border=1>
     * <tr>
     * <td>argument.first=foo</br>
     * argument.second=bar</td>
     * </tr>
     * </table>
     * 
     * @param properties
     *            the property map for this module
     */
    default public void configure( Map<String, String> properties )
    {
        // no configuration required by default, this is only optional
    }
}
