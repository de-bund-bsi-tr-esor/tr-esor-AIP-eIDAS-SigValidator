package de.bund.bsi.tresor.xaip.validator.syntax.context;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import de.bund.bsi.tresor.xaip.validator.syntax.DefaultSyntaxValidator;
import lombok.AllArgsConstructor;

/**
 * Class representing context of the {@link DefaultSyntaxValidator}
 * 
 * @author wolffs
 */
@AllArgsConstructor
public class DefaultSyntaxValidatorContext
{
    private byte[] rawData;
    
    /**
     * Returns an new inputStream of raw xaip data
     * 
     * @return the inputStream
     */
    public InputStream rawXaipInput()
    {
        return new ByteArrayInputStream( rawData );
    }
}
