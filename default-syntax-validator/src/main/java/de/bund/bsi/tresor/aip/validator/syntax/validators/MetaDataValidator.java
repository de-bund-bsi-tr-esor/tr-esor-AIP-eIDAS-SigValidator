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
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import de.bund.bsi.tr_esor.vr.MetaDataObjectValidityType;
import de.bund.bsi.tr_esor.vr.MetaDataObjectValidityType.RelatedObjects;
import de.bund.bsi.tr_esor.vr.MetaDataSectionValidityType;
import de.bund.bsi.tr_esor.xaip.DataObjectsSectionType;
import de.bund.bsi.tr_esor.xaip.MetaDataObjectType;
import de.bund.bsi.tr_esor.xaip.MetaDataSectionType;
import de.bund.bsi.tresor.aip.validator.api.control.AIPUtil;
import de.bund.bsi.tresor.aip.validator.api.control.ModuleLogger;
import de.bund.bsi.tresor.aip.validator.api.control.VerificationUtil;
import de.bund.bsi.tresor.aip.validator.api.entity.DefaultResult;
import de.bund.bsi.tresor.aip.validator.api.entity.DefaultResult.Minor;
import de.bund.bsi.tresor.aip.validator.api.entity.DefaultResult.ResultLanguage;
import de.bund.bsi.tresor.aip.validator.api.entity.aip.Category;
import de.bund.bsi.tresor.aip.validator.api.entity.aip.Classification;
import oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_.VerificationResultType;

/**
 * Validator for any validations concerning data inside the {@link MetaDataSectionType}
 * 
 * @author wolffs
 */
public enum MetaDataValidator
{
    INSTANCE;
    
    /**
     * Complete validation of the metaData section which includes all subElements
     * 
     * @param metaDataSection
     *            the metaData section to validate
     * @param dataSection
     *            the dataObjectsSection for the dataChecksum verification
     * @param xmlDataByOid
     *            the tmp xmlData mapped by the oid
     * @return the metaData section validation result
     */
    public Optional<MetaDataSectionValidityType> validateMetaDataSection( Optional<MetaDataSectionType> metaDataSection,
            DataObjectsSectionType dataSection, Map<String, File> xmlDataByOid )
    {
        return metaDataSection.map( section -> section.getMetaDataObject().stream()
                .map( meta -> validateMetaDataObject( meta, xmlDataByOid ) )
                .collect( toList() ) )
                .map( meta -> {
                    MetaDataSectionValidityType result = new MetaDataSectionValidityType();
                    meta.stream().forEach( result.getMetaDataObject()::add );
                    
                    return result;
                } );
    }
    
    /**
     * Validating a metaData object
     * 
     * @param metaData
     *            the metaData to validate
     * @param xmlDataByOid
     *            the tmp xmlData mapped by the oid
     *            
     * @return the validation result
     */
    public MetaDataObjectValidityType validateMetaDataObject( MetaDataObjectType metaData, Map<String, File> xmlDataByOid )
    {
        MetaDataObjectValidityType result = new MetaDataObjectValidityType();
        result.setMetaDataID( metaData.getMetaDataID() );
        
        validateChecksum( metaData, xmlDataByOid ).ifPresent( result::setCheckSum );
        validateCategory( metaData.getCategory() ).ifPresent( result::setCategory );
        validateClassification( metaData.getCategory(), metaData.getClassification() ).ifPresent( result::setClassification );
        
        result.setRelatedObjects( pathRelatedObjects( metaData.getRelatedObjects() ) );
        result.setContent( VerificationUtil.verificationResult( DefaultResult.valid().build() ) );
        
        return result;
    }
    
    /**
     * Returning a {@link RelatedObjectsType} which contain the xpath to the related objects
     * 
     * @param relatedObjects
     *            the related objects
     * @return the {@link RelatedObjectsType} which contains the xpaths
     */
    public RelatedObjects pathRelatedObjects( List<Object> relatedObjects )
    {
        List<String> xPaths = relatedObjects.stream()
                .map( AIPUtil::idFromObject )
                .map( AIPUtil::xPathForObjectId )
                .collect( toList() );
        
        RelatedObjects objects = new RelatedObjects();
        objects.getXPath().addAll( xPaths );
        
        return objects;
    }
    
    /**
     * Validating the checksum of the metaData if any is present
     * 
     * @param metaData
     *            the metaData
     * @param xmlDataByOid
     *            the tmp xmlData mapped by the oid
     * @return the verificationResult
     */
    public Optional<VerificationResultType> validateChecksum( MetaDataObjectType metaData, Map<String, File> xmlDataByOid )
    {
        return Optional.ofNullable( metaData.getCheckSum() )
                .map( checksum -> {
                    Optional<File> optXmlFile = Optional.ofNullable( xmlDataByOid.get( metaData.getMetaDataID() ) );
                    try ( InputStream stream = optXmlFile.isPresent() ? new FileInputStream( optXmlFile.get() )
                            : AIPUtil.extractData( AIPUtil.binaryDataSupplier( metaData ), metaData::getXmlMetaData )
                                    .map( ByteArrayInputStream::new )
                                    .orElse( new ByteArrayInputStream( new byte[0] ) ) )
                    {
                        return VerificationUtil.verifyChecksum( stream, metaData.getCheckSum() );
                    }
                    catch ( IllegalStateException | IOException e )
                    {
                        ModuleLogger.log( "could not retrieve data for checksum validation", e );
                        return VerificationUtil.verificationResult( DefaultResult.error().minor( Minor.NO_DATA_ACCESS_WARNING ).build() );
                    }
                } );
    }
    
    /**
     * Validating the metaData category. Since this field is optional an can be empty, an optional will be returned and contains a result
     * when a category is provided
     * 
     * @param category
     *            the category
     * @return the validation result when a category was provided
     */
    public Optional<VerificationResultType> validateCategory( String category )
    {
        return Optional.ofNullable( category )
                .map( c -> Arrays.stream( Category.values() )
                        .map( Category::name )
                        .anyMatch( category::equals ) )
                .map( valid -> valid ? DefaultResult.ok().build() : DefaultResult.error().build() )
                .map( VerificationUtil::verificationResult );
    }
    
    /**
     * Validating the metaData classification. This is an optional field and it's validity depends also on the provided category. Since this
     * field is optional and can be empty, an optional will be returned and contains a result when a classification is provided. Therefore
     * providing a classification but no category will lead to an error result but providing a category but no classification will return an
     * empty optional.
     * 
     * @param category
     *            the depending category
     * @param classification
     *            the classification
     * @return the validation result when a classification was provided
     */
    public Optional<VerificationResultType> validateClassification( String category, String classification )
    {
        Optional<Category> optCat = Arrays.stream( Category.values() )
                .filter( f -> f.name().equals( category ) )
                .findAny();
        
        return Optional.ofNullable( classification )
                .map( cls -> Arrays.stream( Classification.values() )
                        .filter( f -> f.name().equals( classification ) )
                        .findAny()
                        .map( v -> optCat.map( Category::getClassifications )
                                .map( set -> set.contains( v ) )
                                .map( valid -> valid ? DefaultResult.ok().build()
                                        : DefaultResult.error()
                                                .message( "classification not allowed for the given category", ResultLanguage.ENGLISH )
                                                .build() )
                                .orElse( DefaultResult.error().message( "missing category", ResultLanguage.ENGLISH ).build() ) )
                        .orElse( DefaultResult.error().build() ) )
                .map( VerificationUtil::verificationResult );
    }
}
