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
 * This class is exposing methods which are being used to analyze dataObjects
 * 
 * @author wolffs
 */
public class DataSectionAnalyzer
{
    /**
     * Analyzing if the provided document data contains a signature. Possible lxaipContent is being ignored in this check
     * 
     * @param dataObject
     *            the dataObject which is being related to the document
     * @param document
     *            the document related to the dataObject
     * @return the finder result
     */
    public static Optional<FinderResult> findSignatures( DataObjectType dataObject, Optional<byte[]> document )
    {
        return document
                .map( data -> XAIPUtil.isXml( data ) ? analyzeXmlData( dataObject, data, false ) : analyzeBinData( dataObject, data ) );
    }
    
    /**
     * Analyzing if the dataObject contains any type of a signature
     * 
     * @param dataObject
     *            the dataObject to analyze
     * @return the finder result
     */
    public static Optional<FinderResult> findSignatures( DataObjectType dataObject )
    {
        Optional<byte[]> binData = XAIPUtil.extractBinData( dataObject.getBinaryData() );
        Optional<byte[]> xmlData = XAIPUtil.extractXmlData( dataObject.getXmlData() )
                .or( () -> binData.filter( XAIPUtil::isXml ) );
        
        return xmlData.map( data -> analyzeXmlData( dataObject, data, true ) )
                .or( () -> binData.map( data -> analyzeBinData( dataObject, data ) ) );
    }
    
    /**
     * Analyzing if the binary data contains a PAdES or CAdES signature
     * 
     * @param dataObject
     *            the dataObject related to the data
     * @param data
     *            the binary data
     * @return the finder result
     */
    public static FinderResult analyzeBinData( DataObjectType dataObject, byte[] data )
    {
        SignaturePresence presence = SignaturePresence
                .fromBoolean( PAdESChecker.INSTANCE.isPAdES( data ) || CAdESChecker.INSTANCE.isCAdES( data ) );
        
        return new FinderResult( dataObject, presence, new ByteArrayInputStream( data ), false );
    }
    
    /**
     * Analyzing if the xml data contains a XAdES signature
     * 
     * @param dataObject
     *            the dataObject related to the data
     * @param data
     *            the xml data
     * @param considerLxaip
     *            if the xml data should be checked to be a lxaip data reference
     * @return the finder result
     */
    public static FinderResult analyzeXmlData( DataObjectType dataObject, byte[] data, boolean considerLxaip )
    {
        Supplier<SignaturePresence> xadesResult = () -> SignaturePresence.fromBoolean( XAdESChecker.INSTANCE.isXAdES( data ) );
        SignaturePresence presence = considerLxaip ? XAIPUtil.extractLxaipData( data )
                .map( d -> SignaturePresence.UNKNOWN )
                .orElseGet( xadesResult ) : xadesResult.get();
        
        return new FinderResult( dataObject, presence, new ByteArrayInputStream( data ), false );
    }
    
}
