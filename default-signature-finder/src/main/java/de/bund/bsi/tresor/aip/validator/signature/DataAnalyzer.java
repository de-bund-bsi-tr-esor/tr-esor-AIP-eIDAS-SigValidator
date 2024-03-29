/*-
 * Copyright (c) 2020
 * Federal Office for Information Security (BSI),
 * Godesberger Allee 185-189,
 * 53175 Bonn, Germany,
 * phone: +49 228 99 9582-0,
 * fax: +49 228 99 9582-5400,
 * e-mail: bsi@bsi.bund.de
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.bund.bsi.tresor.aip.validator.signature;

import java.io.ByteArrayInputStream;
import java.util.Optional;
import java.util.function.Supplier;

import de.bund.bsi.tr_esor.xaip.DataObjectType;
import de.bund.bsi.tr_esor.xaip.MetaDataObjectType;
import de.bund.bsi.tresor.aip.validator.api.control.AIPUtil;
import de.bund.bsi.tresor.aip.validator.signature.checker.ASiCChecker;
import de.bund.bsi.tresor.aip.validator.signature.checker.CAdESChecker;
import de.bund.bsi.tresor.aip.validator.signature.checker.PAdESChecker;
import de.bund.bsi.tresor.aip.validator.signature.checker.XAdESChecker;
import de.bund.bsi.tresor.aip.validator.signature.entity.FinderResult;
import de.bund.bsi.tresor.aip.validator.signature.entity.SignaturePresence;

/**
 * This class is exposing methods which are being used to analyze dataObjects
 * 
 * @author wolffs
 */
public class DataAnalyzer
{
    /**
     * Analyzing if the provided document data contains a signature. Possible lxaipContent is being ignored in this check
     * 
     * @param <T>
     *            type of the dataContainer in the finderResult
     *            
     * @param dataObject
     *            the dataObject which is being related to the document
     * @param document
     *            the document related to the dataObject
     * @return the finder result
     */
    public static <T> Optional<FinderResult<T>> findSignatures( T dataObject, Optional<byte[]> document )
    {
        return document
                .map( data -> AIPUtil.isXml( data ) ? analyzeXmlData( dataObject, data, false ) : analyzeBinData( dataObject, data ) );
    }
    
    /**
     * Analyzing if the dataObject contains any signature
     * 
     * @param dataObject
     *            the dataObject
     * @return the finderResult
     */
    public static Optional<FinderResult<DataObjectType>> findSignatures( DataObjectType dataObject )
    {
        Optional<byte[]> binData = AIPUtil.extractBinData( AIPUtil.binaryDataSupplier( dataObject ).get() );
        Optional<byte[]> xmlData = AIPUtil.extractXmlData( dataObject.getXmlData() )
                .or( () -> binData.filter( AIPUtil::isXml ) );
        
        return findSignatures( dataObject, binData, xmlData );
    }
    
    /**
     * Analyzing if the metaDataObject contains any signature
     * 
     * @param metaDataObject
     *            the metaDataObject
     * @return the finderResult
     */
    public static Optional<FinderResult<MetaDataObjectType>> findSignatures( MetaDataObjectType metaDataObject )
    {
        Optional<byte[]> binData = AIPUtil.extractBinData( AIPUtil.binaryDataSupplier( metaDataObject ).get() );
        Optional<byte[]> xmlData = AIPUtil.extractXmlData( metaDataObject.getXmlMetaData() )
                .or( () -> binData.filter( AIPUtil::isXml ) );
        
        return findSignatures( metaDataObject, binData, xmlData );
    }
    
    /**
     * Analyzing if the dataObject contains any type of a signature
     * 
     * @param <T>
     *            dataContainer type of the finderResults
     *            
     * @param dataObject
     *            the dataObject to analyze
     * @param binData
     *            the binaryData of the dataObject
     * @param xmlData
     *            the xmlData of the dataObject
     * @return the finder result
     */
    public static <T> Optional<FinderResult<T>> findSignatures( T dataObject, Optional<byte[]> binData, Optional<byte[]> xmlData )
    {
        return xmlData.map( data -> analyzeXmlData( dataObject, data, true ) )
                .or( () -> binData.map( data -> analyzeBinData( dataObject, data ) ) );
    }
    
    /**
     * Analyzing if the binary data contains a PAdES or CAdES signature
     * 
     * @param <T>
     *            dataContainer type of the finderResults
     *            
     * @param dataObject
     *            the dataObject related to the data
     * @param data
     *            the binary data
     * @return the finder result
     */
    public static <T> FinderResult<T> analyzeBinData( T dataObject, byte[] data )
    {
        boolean isPAdES = PAdESChecker.INSTANCE.isPAdES( data );
        boolean isCAdES = CAdESChecker.INSTANCE.isCAdES( data );
        boolean isASiC = ASiCChecker.INSTANCE.isASiC( data );
        
        SignaturePresence presence = SignaturePresence.fromBoolean( isPAdES || isCAdES || isASiC );
        
        return new FinderResult<T>( dataObject, presence, new ByteArrayInputStream( data ) );
    }
    
    /**
     * Analyzing if the xml data contains a XAdES signature
     * 
     * @param <T>
     *            dataContainer type of the finderResults
     *            
     * @param dataObject
     *            the dataObject related to the data
     * @param data
     *            the xml data
     * @param considerLxaip
     *            if the xml data should be checked to be a lxaip data reference
     * @return the finder result
     */
    public static <T> FinderResult<T> analyzeXmlData( T dataObject, byte[] data, boolean considerLxaip )
    {
        Supplier<FinderResult<T>> normalCheck = () -> {
            SignaturePresence sigCheck = SignaturePresence.fromBoolean( XAdESChecker.INSTANCE.isXAdES( data ) );
            return new FinderResult<T>( dataObject, sigCheck, new ByteArrayInputStream( data ) );
        };
        
        return considerLxaip ? AIPUtil.extractLxaipData( data )
                // .map( d -> SignaturePresence.UNKNOWN )
                .map( d -> {
                    boolean isPAdES = PAdESChecker.INSTANCE.isPAdES( d );
                    boolean isCAdES = CAdESChecker.INSTANCE.isCAdES( d );
                    boolean isXAdES = XAdESChecker.INSTANCE.isXAdES( d );
                    boolean isASiC = ASiCChecker.INSTANCE.isASiC( d );
                    
                    SignaturePresence presence = SignaturePresence.fromBoolean( isPAdES || isCAdES || isASiC || isXAdES );
                    
                    return new FinderResult<T>( dataObject, presence, new ByteArrayInputStream( data ) );
                } )
                .orElseGet( normalCheck ) : normalCheck.get();
    }
    
}
