package de.bund.bsi.tresor.xaip.validator.signature;

import java.io.ByteArrayInputStream;
import java.util.Optional;
import java.util.function.Supplier;

import de.bund.bsi.tr_esor.xaip._1.DataObjectType;
import de.bund.bsi.tresor.xaip.validator.api.control.XAIPUtil;
import de.bund.bsi.tresor.xaip.validator.signature.checker.CAdESChecker;
import de.bund.bsi.tresor.xaip.validator.signature.checker.PAdESChecker;
import de.bund.bsi.tresor.xaip.validator.signature.checker.XAdESChecker;
import de.bund.bsi.tresor.xaip.validator.signature.entity.FinderResult;
import de.bund.bsi.tresor.xaip.validator.signature.entity.SignaturePresence;

/**
 * @author wolffs
 */
public class DataSectionAnalyzer
{
    static public Optional<FinderResult> findSignatures( DataObjectType dataObject, Optional<byte[]> document )
    {
        return document
                .map( data -> XAIPUtil.isXml( data ) ? analyzeXmlData( dataObject, data, false ) : analyzeBinData( dataObject, data ) );
    }
    
    static public Optional<FinderResult> findSignatures( DataObjectType dataObject )
    {
        Optional<byte[]> binData = XAIPUtil.extractBinData( dataObject.getBinaryData() );
        Optional<byte[]> xmlData = XAIPUtil.extractXmlData( dataObject.getXmlData() )
                .or( () -> binData.filter( XAIPUtil::isXml ) );
        
        return binData.map( data -> analyzeBinData( dataObject, data ) )
                .or( () -> xmlData.map( data -> analyzeXmlData( dataObject, data, true ) ) );
    }
    
    public static FinderResult analyzeBinData( DataObjectType dataObject, byte[] data )
    {
        SignaturePresence presence = SignaturePresence
                .fromBoolean( PAdESChecker.INSTANCE.isPAdES( data ) || CAdESChecker.INSTANCE.isCAdES( data ) );
        
        return new FinderResult( dataObject, presence, new ByteArrayInputStream( data ) );
    }
    
    public static FinderResult analyzeXmlData( DataObjectType dataObject, byte[] data, boolean considerLxaip )
    {
        Supplier<SignaturePresence> xadesResult = () -> SignaturePresence.fromBoolean( XAdESChecker.INSTANCE.isXAdES( data ) );
        SignaturePresence presence = considerLxaip ? XAIPUtil.extractLxaipData( data )
                .map( d -> SignaturePresence.UNKNOWN )
                .orElseGet( xadesResult ) : xadesResult.get();
        
        return new FinderResult( dataObject, presence, new ByteArrayInputStream( data ) );
    }
    
}
