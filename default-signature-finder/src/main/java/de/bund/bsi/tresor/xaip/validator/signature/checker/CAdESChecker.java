package de.bund.bsi.tresor.xaip.validator.signature.checker;

import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;

import de.bund.bsi.tresor.xaip.validator.api.control.ModuleLogger;

/**
 * Singleton to check data for possible CAdES.
 * 
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
            
            boolean isCAdES = PKCSObjectIdentifiers.signedData.equals( cms.getContentType() );
            ModuleLogger.verbose( isCAdES ? "data is CAdES" : "data is not CAdES" );
            
            return isCAdES;
        }
        catch ( Exception e )
        {
            ModuleLogger.verbose( "data is not CAdES", e );
        }
        
        return false;
    }
}
