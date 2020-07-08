package de.bund.bsi.tresor.xaip.validator.cli.converter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.commons.lang3.StringUtils;

import com.beust.jcommander.IStringConverter;

import de.bund.bsi.tresor.xaip.validator.api.entity.XAIPValidatorException;

/**
 * @author wolffs
 */
public class OutputStreamConverter implements IStringConverter<OutputStream>
{
    private static final String DEFAULT_NAME = "verification-protocol.xml";
    
    @Override
    public OutputStream convert( String value )
    {
        try
        {
            FileOutputStream output;
            
            File file = new File( StringUtils.isBlank( value ) ? System.getProperty( "user.dir" ) : value );
            if ( file.exists() && file.isDirectory() )
            {
                File defaultFile = new File( file.getAbsolutePath() + File.pathSeparatorChar + DEFAULT_NAME );
                output = new FileOutputStream( defaultFile );
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
        catch ( FileNotFoundException e )
        {
            throw new XAIPValidatorException( "invalid output param " + value, e );
        }
    }
}
