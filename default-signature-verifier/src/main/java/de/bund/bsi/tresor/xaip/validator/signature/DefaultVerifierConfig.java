package de.bund.bsi.tresor.xaip.validator.signature;

import lombok.Data;

/**
 * @author wolffs
 */
@Data
public class DefaultVerifierConfig
{
    // URL des TR-ESOR Systems zum Aufruf von VerifyRequest
    public String tresorUrl;
    
    // TODO Optionale Authentisierungsparemeter zum Aufruf der Schnittstelle (URL's, Zertifikate, Credentials etc.)
    public String user;
    public String password;
}
