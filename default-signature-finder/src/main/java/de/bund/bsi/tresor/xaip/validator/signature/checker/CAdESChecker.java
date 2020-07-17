package de.bund.bsi.tresor.xaip.validator.signature.checker;

import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;

/**
 * @author wolffs
 */
public enum CAdESChecker
{
    INSTANCE;
    
    /**
     * Checking if the data could contain a CAdES by validating if the data is a <code>CMS</code> containing the contentType
     * <code>signedData</code>.
     * 
     * @param data
     *            the document data
     * @return if the data could contain a CAdES
     */
    public boolean isCAdES( byte[] data )
    {
        try
        {
            ContentInfo cms = ContentInfo.getInstance( data );
            return PKCSObjectIdentifiers.signedData.equals( cms.getContentType() );
        }
        catch ( Exception e )
        {
            // TODO verbose logging
        }
        
        return false;
    }
}
