package de.bund.bsi.tresor.aip.validator.soap;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import lombok.AllArgsConstructor;

/**
 * @author wolffs
 */
@AllArgsConstructor
public class StreamingMatcher extends TypeSafeMatcher<InputStream>
{
    
    public static Matcher<InputStream> matchesStream( byte[] expectedData )
    {
        return new StreamingMatcher( new ByteArrayInputStream( expectedData ) );
    }
    
    private InputStream expect;
    
    @Override
    public void describeTo( Description description )
    {
        description.appendText( "matching stream data" );
    }
    
    @Override
    protected boolean matchesSafely( InputStream item )
    {
        boolean matches = false;
        try
        {
            matches = IOUtils.contentEquals( item, expect );
        }
        catch ( IOException e )
        {
            e.printStackTrace( System.err );
        }
        
        return matches;
    }
    
}
