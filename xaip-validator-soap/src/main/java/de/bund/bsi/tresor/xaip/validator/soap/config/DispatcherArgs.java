package de.bund.bsi.tresor.xaip.validator.soap.config;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import de.bund.bsi.tresor.xaip.validator.dispatcher.DispatcherArguments;
import lombok.Builder;
import lombok.Data;

/**
 * @author wolffs
 */
@Data
@Builder
public class DispatcherArgs implements DispatcherArguments
{
    private boolean             verify;
    private boolean             verbose;
    
    private InputStream         input;
    private OutputStream        output;
    private OutputStream        log;
    
    @Builder.Default
    private Map<String, String> moduleConfig = new HashMap<>();
}
