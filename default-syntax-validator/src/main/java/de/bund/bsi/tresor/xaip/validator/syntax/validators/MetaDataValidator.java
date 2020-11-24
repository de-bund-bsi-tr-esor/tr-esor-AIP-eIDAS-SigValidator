package de.bund.bsi.tresor.xaip.validator.syntax.validators;

import static java.util.stream.Collectors.toList;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import de.bund.bsi.tr_esor.vr._1.MetaDataObjectValidityType;
import de.bund.bsi.tr_esor.vr._1.MetaDataSectionValidityType;
import de.bund.bsi.tr_esor.xaip._1.CheckSumType;
import de.bund.bsi.tr_esor.xaip._1.DataObjectType;
import de.bund.bsi.tr_esor.xaip._1.DataObjectsSectionType;
import de.bund.bsi.tr_esor.xaip._1.MetaDataObjectType;
import de.bund.bsi.tr_esor.xaip._1.MetaDataSectionType;
import de.bund.bsi.tresor.xaip.validator.api.control.ModuleLogger;
import de.bund.bsi.tresor.xaip.validator.api.control.VerificationUtil;
import de.bund.bsi.tresor.xaip.validator.api.control.XAIPUtil;
import de.bund.bsi.tresor.xaip.validator.api.entity.DefaultResult;
import de.bund.bsi.tresor.xaip.validator.api.entity.DefaultResult.Builder;
import de.bund.bsi.tresor.xaip.validator.api.entity.DefaultResult.Minor;
import de.bund.bsi.tresor.xaip.validator.api.entity.DefaultResult.ResultLanguage;
import de.bund.bsi.tresor.xaip.validator.api.entity.xaip.Category;
import de.bund.bsi.tresor.xaip.validator.api.entity.xaip.Classification;
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
     * @return the metaData section validation result
     */
    public Optional<MetaDataSectionValidityType> validateMetaDataSection( Optional<MetaDataSectionType> metaDataSection,
            DataObjectsSectionType dataSection )
    {
        List<MetaDataObjectValidityType> metaData = metaDataSection.map( section -> section.getMetaDataObject().stream()
                .map( meta -> validateMetaDataObject( meta, dataSection ) )
                .collect( toList() ) )
                .orElse( new ArrayList<>() );
        
        if ( !metaData.isEmpty() )
        {
            MetaDataSectionValidityType result = new MetaDataSectionValidityType();
            metaData.stream().forEach( result.getMetaDataObject()::add );
            
            return Optional.of( result );
        }
        else
        {
            return Optional.empty();
        }
    }
    
    /**
     * Validating a metaData object
     * 
     * @param metaData
     *            the metaData to validate
     * @param dataSection
     *            the dataObjectsSection for the dataChecksum verification
     * @return the validation result
     */
    public MetaDataObjectValidityType validateMetaDataObject( MetaDataObjectType metaData, DataObjectsSectionType dataSection )
    {
        String oid = metaData.getDataObjectID().stream()
                .map( XAIPUtil::idFromObject )
                .reduce( "", ( a, b ) -> String.join( " ", a, b ) );
        
        MetaDataObjectValidityType result = new MetaDataObjectValidityType();
        result.setMetaDataID( metaData.getMetaDataID() );
        result.setDataObjectID( oid );
        
        validateCategory( metaData.getCategory() ).ifPresent( result::setCategory );
        validateClassification( metaData.getCategory(), metaData.getClassification() ).ifPresent( result::setClassification );
        
        // result.setDataObjectCheckSum( value ); // FIXME bug in spec
        result.setContent( VerificationUtil.verificationResult( DefaultResult.ok().build() ) );
        
        return result;
    }
    
    /**
     * Verifies the provided checksum with the referenced dataObject
     * 
     * @param checksum
     *            the checksum
     * @param dataSection
     *            the dataObjectsSection
     * @param dataRefs
     *            the dataReferences
     * @return the verificationResult
     */
    public Optional<VerificationResultType> validateDataChecksum( CheckSumType checksum, DataObjectsSectionType dataSection,
            List<Object> dataRefs )
    {
        List<Object> references = Optional.ofNullable( dataRefs ).orElse( new ArrayList<>() );
        if ( references.size() > 1 )
        {
            ModuleLogger.log( "WARN - dataChecksum verification found multiple dataRef pointer but can only verify single dataRefs" );
        }
        
        return references.stream()
                .findFirst()
                .map( ref -> {
                    Builder builder = DefaultResult.error();
                    Optional<DataObjectType> dataObject = Optional.empty();
                    
                    if ( ref instanceof String )
                    {
                        dataObject = XAIPUtil.findDataObjectById( dataSection, (String) ref );
                    }
                    else if ( ref instanceof DataObjectType )
                    {
                        dataObject = Optional.of( (DataObjectType) ref );
                    }
                    else
                    {
                        builder.minor( Minor.PARAMETER_ERROR )
                                .message( "dataRef is not pointing to a valid dataObject", ResultLanguage.ENGLISH );
                    }
                    
                    return dataObject.flatMap( XAIPUtil::extractData )
                            .map( ByteArrayInputStream::new )
                            .map( content -> VerificationUtil.verifyChecksum( content, checksum ) )
                            .orElse( VerificationUtil.verificationResult( builder.build() ) );
                    
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
