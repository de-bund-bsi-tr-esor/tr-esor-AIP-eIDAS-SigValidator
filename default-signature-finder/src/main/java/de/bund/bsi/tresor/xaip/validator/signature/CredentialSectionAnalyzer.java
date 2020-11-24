package de.bund.bsi.tresor.xaip.validator.signature;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.AbstractMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import de.bund.bsi.tr_esor.xaip._1.CredentialType;
import de.bund.bsi.tr_esor.xaip._1.DataObjectType;
import de.bund.bsi.tr_esor.xaip._1.DataObjectsSectionType;
import de.bund.bsi.tresor.xaip.validator.api.control.XAIPUtil;
import de.bund.bsi.tresor.xaip.validator.signature.entity.FinderResult;
import de.bund.bsi.tresor.xaip.validator.signature.entity.SignaturePresence;
import oasis.names.tc.dss._1_0.core.schema.Base64Signature;
import oasis.names.tc.dss._1_0.core.schema.SignatureObject;
import oasis.names.tc.dss._1_0.core.schema.SignaturePtr;

/**
 * @author wolffs
 */
public class CredentialSectionAnalyzer
{
    /*
     * Notes: - data can be found in dataObject when exists - signatureData can be found in credential (b64Sig, signature, sigPtr for
     * embedded) - other credentials with separate data: timestamp
     * 
     * - b64Sig can contain embedded signature -> no related dataObject
     */
    public static Map.Entry<CredentialType, Set<FinderResult>> analyzeCredential( CredentialType credential,
            DataObjectsSectionType dataObjects, Set<FinderResult> dataSectionResults )
    {
        Set<FinderResult> sigResults = new HashSet<>();
        SignatureObject signObj = credential.getSignatureObject();
        Set<DataObjectType> relatedData = XAIPUtil.resolveRelatedDataObjects( dataObjects, credential.getRelatedObjects() );
        
        SignaturePtr signaturePtr = signObj.getSignaturePtr();
        Optional<byte[]> b64Signature = Optional.ofNullable( signObj.getBase64Signature() ).map( Base64Signature::getValue );
        
        if ( relatedData.isEmpty() )
        {
            b64Signature.flatMap( data -> DataSectionAnalyzer.findSignatures( null, Optional.of( data ) ) )
                    .ifPresent( sigResults::add );
        }
        
        for ( DataObjectType dataObject : relatedData )
        {
            Optional<InputStream> optData = dataSectionResults.stream()
                    .filter( r -> dataObject.getDataObjectID().equals( r.getDataObject().getDataObjectID() ) )
                    .findAny()
                    .flatMap( FinderResult::getData )
                    .or( () -> DataSectionAnalyzer.extractData( dataObject ).map( ByteArrayInputStream::new ) );
            
            // TODO: only when optData present; is this correct?
            if ( optData.isPresent() && (signObj.getSignature() != null || signObj.getTimestamp() != null) )
            {
                sigResults.add( new FinderResult( dataObject, SignaturePresence.PRESENT, optData ) );
            }
            else if ( b64Signature.isPresent() )
            {
                b64Signature.flatMap( data -> DataSectionAnalyzer.findSignatures( dataObject, Optional.of( data ) ) )
                        .ifPresent( sigResults::add );
            }
            else if ( signaturePtr != null )
            {
                sigResults.add( analyzePointer( signaturePtr, dataObject, optData ) );
            }
            else
            {
                sigResults.add( new FinderResult( dataObject, SignaturePresence.UNKNOWN, optData ) );
            }
        }
        
        return new AbstractMap.SimpleEntry<>( credential, sigResults );
    }
    
    static FinderResult analyzePointer( SignaturePtr pointer, DataObjectType dataObj, Optional<InputStream> optData )
    {
        Object document = pointer.getWhichDocument();
        String oid = XAIPUtil.idFromObject( document );
        if ( dataObj.getDataObjectID().equals( oid ) )
        {
            return new FinderResult( dataObj, SignaturePresence.PRESENT, optData );
        }
        
        // TODO might convert document to byte[] in another form
        if ( document instanceof byte[] )
        {
            return DataSectionAnalyzer.analyzeBinData( dataObj, (byte[]) document );
        }
        
        return new FinderResult( dataObj, SignaturePresence.MISSING, optData );
    }
}
