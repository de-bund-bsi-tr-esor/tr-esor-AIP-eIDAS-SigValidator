package de.bund.bsi.tresor.xaip.validator.signature;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.w3._2000._09.xmldsig_.SignatureType;

import de.bund.bsi.tr_esor.xaip._1.CredentialType;
import de.bund.bsi.tr_esor.xaip._1.DataObjectType;
import de.bund.bsi.tr_esor.xaip._1.DataObjectsSectionType;
import de.bund.bsi.tresor.xaip.validator.api.control.XAIPUtil;
import oasis.names.tc.dss._1_0.core.schema.Base64Signature;
import oasis.names.tc.dss._1_0.core.schema.SignatureObject;
import oasis.names.tc.dss._1_0.core.schema.SignaturePtr;
import oasis.names.tc.dss._1_0.core.schema.Timestamp;

/**
 * @author wolffs
 */
public class CredentialSectionAnalyzer
{
    private Object object;
    
    void analyzeCredential( CredentialType credentials, DataObjectsSectionType dataObjects )
    {
        credentials.getCredentialID();
        
        List<Object> relatedObjects = credentials.getRelatedObjects();
        
        SignatureObject signatureObject = credentials.getSignatureObject();
        
        // bezieht sich auf dss:signatureObject behandeln wie rohdatem im dataObject
        Base64Signature base64Signature = signatureObject.getBase64Signature();
        
        // Bezug auf relatedObjects
        SignatureType signature = signatureObject.getSignature();
        SignaturePtr signaturePtr = signatureObject.getSignaturePtr();
        Timestamp timestamp = signatureObject.getTimestamp();
        
        Set<DataObjectType> relatedData = XAIPUtil.resolveRelatedDataObjects( dataObjects, relatedObjects );
        if ( relatedData.isEmpty() )
        {
            // zuordnung zu null wenn b64Signature,
            
            Optional.ofNullable( signatureObject.getBase64Signature() )
                    .map( Base64Signature::getValue )
                    .map( data -> DataSectionAnalyzer.findSignatures( null, Optional.of( data ) ) );
        }
        else
        {
            // zuordnung TimeStamp, Signature, SignaturePtr falls nicht embedded, b64Signature wenn embeddes signature (siehe datentyp
            // prüfen)
            
            for ( DataObjectType dataObject : relatedData )
            {
                
                Optional.ofNullable( signatureObject.getBase64Signature() )
                        .map( Base64Signature::getValue )
                        .map( data -> DataSectionAnalyzer.findSignatures( dataObject, Optional.of( data ) ) );
            }
        }
    }
    
    void signaturePointer()
    {
        // embedded signature?
    }
}
