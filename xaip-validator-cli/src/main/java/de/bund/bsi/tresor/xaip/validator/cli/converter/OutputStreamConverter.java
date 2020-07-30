package de.bund.bsi.tresor.xaip.validator.cli.converter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import de.bund.bsi.tresor.xaip.validator.api.entity.XAIPValidatorException;

/**
 * Provider class for overall functional outputstream conversions which can be used by multiple consumers.
 * 
 * @author wolffs
 */
public class OutputStreamConverter
{
    private static final String SYS_OUT = "syso";
    
    /**
     * Creating a {@link FileOutputStream} by parsing the value as a {@link Path}. When no value is being provided, a new file in the
     * current working directory will be created using the defaultFileName. If a provided path does not exist it will be created.
     * 
     * @param defaultFileName
     *            file name to create in the working directory when no path is being provided
     * @param value
     *            a file path
     * @return the {@link FileOutputStream}
     */
    public static OutputStream outputFile( String defaultFileName, String value )
    {
        try
        {
            FileOutputStream output;
            
            File file = new File( StringUtils.isBlank( value ) ? System.getProperty( "user.dir" ) : value );
            if ( file.exists() && file.isDirectory() )
            {
                File defaultFile = new File( file.getAbsolutePath() + File.pathSeparatorChar + defaultFileName );
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
        catch ( Exception e )
        {
            throw new XAIPValidatorException( "invalid output param " + value, e );
        }
    }
    
    /**
     * If the value matches {@value #SYS_OUT} (caseInsensitiv) the system outputstream will be returned encapsulated in an optional. No
     * match will result in an empty optional.
     * 
     * @param value
     *            the value to check
     * @return may return system.out
     */
    public static Optional<OutputStream> systemOutput( String value )
    {
        return SYS_OUT.equalsIgnoreCase( value ) ? Optional.of( System.out ) : Optional.empty();
    }
}
