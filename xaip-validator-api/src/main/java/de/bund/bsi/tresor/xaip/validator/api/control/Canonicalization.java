package de.bund.bsi.tresor.xaip.validator.api.control;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.Set;

import org.apache.xml.security.Init;
import org.apache.xml.security.c14n.CanonicalizationException;
import org.apache.xml.security.c14n.Canonicalizer;
import org.apache.xml.security.c14n.InvalidCanonicalizerException;
import org.w3c.dom.Node;

/**
 * Representation of all possible canonicalization methods
 * 
 * @author wolffs
 */
public class Canonicalization
{
    static
    {
        // required before using apache xml canonicalizer
        Init.init();
    }
    
    static final Set<String> VALID_C14N = Set.of(
            Canonicalizer.ALGO_ID_C14N_EXCL_OMIT_COMMENTS,
            Canonicalizer.ALGO_ID_C14N_EXCL_WITH_COMMENTS,
            Canonicalizer.ALGO_ID_C14N_OMIT_COMMENTS,
            Canonicalizer.ALGO_ID_C14N_WITH_COMMENTS );
    
    /**
     * Checking if the c14n is supported
     * 
     * @param url
     *            the c14n url
     * @return the result
     */
    public static boolean isValidCanonicalization( String url )
    {
        return VALID_C14N.contains( url );
    }
    
    /**
     * Canonicalizing the node with the given url
     * 
     * @param node
     *            the node
     * @param c14nUrl
     *            the c14n url
     * @return the content stream
     * @throws InvalidCanonicalizerException
     *             on invalid c14n
     * @throws CanonicalizationException
     *             when c14n fails
     * @throws IOException
     *             when the data could not be retrieved
     */
    public static ByteArrayInputStream canonicalize( Node node, Optional<String> c14nUrl )
            throws InvalidCanonicalizerException, CanonicalizationException, IOException
    {
        String c14nMethod = c14nUrl.orElseThrow( () -> new IllegalStateException( "no data found" ) );
        Canonicalizer instance = Canonicalizer.getInstance( c14nMethod );
        try ( ByteArrayOutputStream out = new ByteArrayOutputStream() )
        {
            instance.canonicalizeSubtree( node, out );
            
            return new ByteArrayInputStream( out.toByteArray() );
        }
    }
}