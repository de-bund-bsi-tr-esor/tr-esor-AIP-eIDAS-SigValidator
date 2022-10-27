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
package de.bund.bsi.tresor.aip.validator.syntax.validators;

import static java.util.stream.Collectors.toList;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.etsi.uri._02918.v1_2.DataObjectReferenceType;
import org.w3._2000._09.xmldsig_.DigestMethodType;

import de.bund.bsi.tr_esor.vr.DataObjectValidityType;
import de.bund.bsi.tr_esor.vr.DataObjectsSectionValidityType;
import de.bund.bsi.tr_esor.vr.TransformInfoValidityType;
import de.bund.bsi.tr_esor.xaip.CheckSumType;
import de.bund.bsi.tr_esor.xaip.DataObjectType;
import de.bund.bsi.tr_esor.xaip.DataObjectsSectionType;
import de.bund.bsi.tr_esor.xaip.TranformInfoType;
import de.bund.bsi.tr_esor.xaip.TransformObjectType;
import de.bund.bsi.tresor.aip.validator.api.control.AIPUtil;
import de.bund.bsi.tresor.aip.validator.api.control.ModuleLogger;
import de.bund.bsi.tresor.aip.validator.api.control.VerificationUtil;
import de.bund.bsi.tresor.aip.validator.api.entity.DefaultResult;
import de.bund.bsi.tresor.aip.validator.api.entity.DefaultResult.Builder;
import de.bund.bsi.tresor.aip.validator.api.entity.DefaultResult.Minor;
import de.bund.bsi.tresor.aip.validator.api.entity.DefaultResult.ResultLanguage;
import oasis.names.tc.dss._1_0.core.schema.Result;
import oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_.VerificationResultType;

/**
 * Validator for any validations concerning data inside the {@link DataObjectsSectionType}
 * 
 * @author wolffs
 */
public enum DataObjectSectionValidator
{
    INSTANCE;
    
    /**
     * Complete validation of the dataObject section. This includes validating all subElements
     * 
     * @param dataObjectsSection
     *            the dataObjectsSection to validate
     * @param xmlData
     *            the tmp xmlData mapped by the oid
     * @return the dataObjectsSection validation result
     */
    public Optional<DataObjectsSectionValidityType> validateDataSection(
            Optional<DataObjectsSectionType> dataObjectsSection, Map<String, File> xmlData )
    {
        List<DataObjectValidityType> data = dataObjectsSection
                .map( section -> section.getDataObject().stream()
                        .map( obj -> validateDataObject( obj, xmlData ) )
                        .collect( toList() ) )
                .orElse( new ArrayList<>() );
        
        if ( !data.isEmpty() )
        {
            DataObjectsSectionValidityType result = new DataObjectsSectionValidityType();
            data.stream().forEach( result.getDataObject()::add );
            
            return Optional.of( result );
        }
        else
        {
            return Optional.empty();
        }
    }
    
    /**
     * Validating the dataObject
     * 
     * @param dataObject
     *            the dataObject to validate
     * @param xmlData
     *            the tmp xmlData mapped by the oid
     * @return the dataObject validation result
     */
    public DataObjectValidityType validateDataObject( DataObjectType dataObject, Map<String, File> xmlData )
    {
        DataObjectValidityType result = new DataObjectValidityType();
        String oid = dataObject.getDataObjectID();
        
        result.setDataObjectID( oid );
        
        Optional<File> optXmlFile = Optional.ofNullable( xmlData.get( oid ) );
        Optional.ofNullable( dataObject.getCheckSum() )
                .map( checkSum -> {
                    // aipUtil is using domParser can not be used for checksum verification for xmlData
                    try ( InputStream stream = optXmlFile.isPresent() ? new FileInputStream( optXmlFile.get() )
                            : AIPUtil.extractData( AIPUtil.binaryDataSupplier( dataObject ), dataObject::getXmlData )
                                    .map( ByteArrayInputStream::new )
                                    .orElse( new ByteArrayInputStream( new byte[0] ) ) )
                    {
                        return VerificationUtil.verifyChecksum( stream, checkSum, true );
                    }
                    catch ( IllegalStateException | IOException e )
                    {
                        ModuleLogger.log( "could not retrieve data for checksum validation", e );
                        
                        return VerificationUtil.verificationResult( DefaultResult.error().minor( Minor.NO_DATA_ACCESS_WARNING ).build() );
                    }
                } )
                .ifPresent( result::setChecksum );
        
        AIPUtil.findDataReferences( dataObject )
                .map( ( DataObjectReferenceType dataObjectReference ) -> verifyLXAIP( dataObjectReference ) )
                .ifPresent( result::setChecksum );
        
        validateTransformInfo( dataObject.getTransformInfo() ).ifPresent( result::setTransformInfo );
        
        return result;
    }
    
    /**
     * Validation of transformInformation. Since this is an optional element this will only be validated when a non empty value is being
     * provided.
     * 
     * @param transformInfo
     *            the transformation info
     * @return the validation result
     */
    public Optional<TransformInfoValidityType> validateTransformInfo( TranformInfoType transformInfo )
    {
        List<TransformObjectType> transformObjects = Optional.ofNullable( transformInfo )
                .map( TranformInfoType::getTransformObject )
                .orElse( new ArrayList<>() );
        
        if ( transformObjects.size() > 1 )
        {
            ModuleLogger.log( "WARN - multiple transformInfos found but can only validate one due to schema specification bug" );
        }
        
        return transformObjects.stream()
                .findFirst()
                .map( info -> {
                    TransformInfoValidityType validityType = new TransformInfoValidityType();
                    validityType.setTransformObjectID( info.getTransformObjectID() );
                    
                    Result result = DefaultResult.indetermined()
                            .minor( Minor.NOT_SUPPORTED )
                            .message( "transform informations can't be evaluated since xaip profiles are currently not supported",
                                    ResultLanguage.ENGLISH )
                            .build();
                    
                    validityType.getTransformObject().add( VerificationUtil.verificationResult( result ) );
                    
                    return validityType;
                } );
    }
    
    /**
     * Verifies the LXAIP checksum
     * 
     * @param dataObjectReference
     *            the dataObjectReference
     * @return the checksum verification result
     */
    public VerificationResultType verifyLXAIP( DataObjectReferenceType dataObjectReference )
    {
        Builder builder = DefaultResult.error();
        Optional<String> optDigestAlgorithm = Optional.ofNullable( dataObjectReference.getDigestMethod() )
                .map( DigestMethodType::getAlgorithm );
        
        Optional<Path> optFilePath;
        try
        {
            optFilePath = Optional.ofNullable( dataObjectReference.getURI() )
                    .map( URI::create )
                    .map( Paths::get );
        }
        catch ( IllegalArgumentException e )
        {
            optFilePath = Optional.of( AIPUtil.loadRelativeURI( e, dataObjectReference.getURI() ) );
        }
        
        if ( optDigestAlgorithm.isPresent() && optFilePath.isPresent() )
        {
            String digestAlg = optDigestAlgorithm.get();
            Path filePath = optFilePath.get();
            if ( !Files.isReadable( filePath ) )
            {
                builder.minor( Minor.NO_PERMISSION ).message( "reading a file requires read permission", ResultLanguage.ENGLISH );
            }
            else
            {
                CheckSumType checksum = new CheckSumType();
                checksum.setCheckSum( dataObjectReference.getDigestValue() );
                checksum.setCheckSumAlgorithm( digestAlg );
                
                try ( InputStream content = Files.newInputStream( filePath ) )
                {
                    return VerificationUtil.verifyChecksum( content, checksum, false );
                }
                catch ( IOException e )
                {
                    builder.minor( Minor.NO_DATA_ACCESS_WARNING );
                }
            }
        }
        else
        {
            builder.minor( Minor.PARAMETER_ERROR )
                    .message( "dataObject reference contains invalid lxaip parameter", ResultLanguage.ENGLISH );
        }
        
        return VerificationUtil.verificationResult( builder.build() );
    }
}
