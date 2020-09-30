package de.bund.bsi.tresor.xaip.validator.signature.lxaip;

import java.util.Arrays;
import java.util.Optional;

/**
 * Digest algorithm mapping for xml uri to Java Security Standard Algorithm Name.
 * 
 * @author bendlera
 */
enum DigestAlgorithm
{
    SHA224( "SHA-224", "http://www.w3.org/2001/04/xmldsig-more#sha224" ),
    SHA256( "SHA-256", "http://www.w3.org/2001/04/xmlenc#sha256" ),
    SHA384( "SHA-384", "http://www.w3.org/2001/04/xmldsig-more#sha384" ),
    SHA512( "SHA-512", "http://www.w3.org/2001/04/xmlenc#sha512" ),
    
    SHA3_256( "SHA3-256", "http://www.w3.org/2007/05/xmldsig-more#sha3-256" ),
    SHA3_384( "SHA3-384", "http://www.w3.org/2007/05/xmldsig-more#sha3-384" ),
    SHA3_512( "SHA3-512", "http://www.w3.org/2007/05/xmldsig-more#sha3-512" );
    
    private String javaName;
    private String xmlUri;
    
    /**
     * Defining a java name - xml uri pair of algorithm
     * 
     * @param java
     *            the java name of the algorithm
     * @param xmlUri
     *            the xml uri of the algorithm
     */
    DigestAlgorithm( String javaName, String xmlUri )
    {
        this.javaName = javaName;
        this.xmlUri = xmlUri;
    }
    
    /**
     * @return the Java Security Standard Algorithm Name
     */
    public String getJavaName()
    {
        return javaName;
    }
    
    /**
     * Returns the xml uri of the algorithm
     * 
     * @return the xml uri
     */
    public String getXMLURI()
    {
        return xmlUri;
    }
    
    /**
     * Finding the {@link DigestAlgorithm} of the xml uri.
     * 
     * @param xmlUri
     *            the xml uri
     * @return the algorithm
     */
    public static Optional<DigestAlgorithm> fromXmlSyntax( String xmlUri )
    {
        if ( xmlUri == null || xmlUri.isEmpty() )
        {
            return Optional.empty();
        }
        
        return Arrays.asList( DigestAlgorithm.values() ).stream()
                .filter( alg -> alg.getXMLURI().equals( xmlUri ) )
                .findAny();
    }
    
}
