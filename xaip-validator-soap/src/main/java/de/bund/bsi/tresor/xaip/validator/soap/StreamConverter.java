package de.bund.bsi.tresor.xaip.validator.soap;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import de.bund.bsi.tresor.xaip.validator.api.entity.XAIPValidatorException;

/**
 * @author wolffs
 */
public class StreamConverter
{
    public static InputStream fromOutput( ByteArrayOutputStream output ) throws IOException
    {
        var pipeIn = new PipedInputStream();
        var pipeOut = new PipedOutputStream( pipeIn );
        
        new Thread( () -> {
            try ( pipeOut )
            {
                output.writeTo( pipeOut );
            }
            catch ( IOException e )
            {
                throw new XAIPValidatorException( "could not transfer output to input", e );
            }
        } ).start();
        
        return pipeIn;
    }
}
