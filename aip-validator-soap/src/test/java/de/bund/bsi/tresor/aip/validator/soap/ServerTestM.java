package de.bund.bsi.tresor.aip.validator.soap;

import java.util.HashMap;
import java.util.Map;

import de.bund.bsi.tresor.aip.validator.soap.config.ServerConfig;

/**
 * @author wolffs
 */
public class ServerTestM
{
    public static void main( String[] args ) throws Exception
    {
        int port = 28080;
        boolean verify = true;
        
        String schemaDir = "/home/wolffs/workspace/aip-validator/default-syntax-validator/src/main/resources/definitions";
        String wsdlUrl = "https://tresortest.protectr.de/VerificationService/eCard?wsdl";
        
        ////////////////
        
        Map<String, String> moduleConfig = new HashMap<>();
        moduleConfig.put( "verifier.wsdlUrl", wsdlUrl );
        moduleConfig.put( "validator.schemaDir", schemaDir );
        
        ServerConfig config = new ServerConfig();
        config.setPort( port );
        config.setModuleConfig( moduleConfig );
        config.setVerify( verify );
        config.setVerbose( true );
        
        Server server = new Server();
        server.startServer( config );
    }
}
