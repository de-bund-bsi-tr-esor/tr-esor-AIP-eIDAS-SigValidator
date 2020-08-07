package de.bund.bsi.tresor.xaip.validator.cli.converter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Path;

import com.beust.jcommander.IStringConverter;

import de.bund.bsi.tresor.xaip.validator.api.entity.XAIPValidatorException;

/**
 * Provider class for overall functional outputstream conversions which can be used by multiple consumers.
 * 
 * @author wolffs
 */
public class OutputStreamConverter implements IStringConverter<OutputStream>
{
    /**
     * Creating a {@link FileOutputStream} by parsing the value as a {@link Path}. When no value is being provided, a new file in the
     * current working directory will be created using the defaultFileName. If a provided path does not exist it will be created.
     * 
     * @param value
     *            a file path
     * @return the {@link FileOutputStream}
     */
    @Override
    public OutputStream convert( String value )
    {
        try
        {
            FileOutputStream output;
            File file = new File( value );
            if ( file.exists() )
            {
                output = new FileOutputStream( file );
            }
            else
            {
                if ( !file.exists() )
                {
                    file.getParentFile().mkdirs();
                }
                
                output = new FileOutputStream( file );
            }
            
            return output;
        }
        catch ( Exception e )
        {
            throw new XAIPValidatorException( "invalid output param " + value, e );
        }
    }
}
