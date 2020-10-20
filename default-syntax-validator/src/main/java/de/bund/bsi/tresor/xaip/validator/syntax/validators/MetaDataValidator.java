package de.bund.bsi.tresor.xaip.validator.syntax.validators;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.Optional;

import de.bund.bsi.tr_esor.vr._1.MetaDataObjectValidityType;
import de.bund.bsi.tr_esor.vr._1.MetaDataSectionValidityType;
import de.bund.bsi.tr_esor.xaip._1.MetaDataObjectType;
import de.bund.bsi.tr_esor.xaip._1.MetaDataSectionType;
import de.bund.bsi.tresor.xaip.validator.api.entity.DefaultResult;
import de.bund.bsi.tresor.xaip.validator.api.entity.DefaultResult.ResultLanguage;
import de.bund.bsi.tresor.xaip.validator.api.entity.VerificationConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_.VerificationResultType;

/**
 * @author wolffs
 */
public enum MetaDataValidator
{
    INSTANCE;
    
    @Getter
    @AllArgsConstructor
    enum Category
    {
        DMD( EnumSet.of( Classification.DESCRIPTION, Classification.OTHER ) ),
        
        REP( EnumSet.of( Classification.SYNTAX, Classification.DED, Classification.OTHER ) ),
        
        PDI( EnumSet.of( Classification.REFERENCE, Classification.CONTEXT, Classification.PROVENANCE, Classification.OTHER ) ),
        
        OTHER( EnumSet.allOf( Classification.class ) );
        
        private EnumSet<Classification> classifications;
    }
    
    enum Classification
    {
        CONTEXT, DESCRIPTION, DED, REFERENCE, SYNTAX, OTHER, PROVENANCE;
    }
    
    public MetaDataSectionValidityType validateMetaDataSection( Optional<MetaDataSectionType> metaDataSection )
    {
        
        MetaDataSectionValidityType result = new MetaDataSectionValidityType();
        metaDataSection
                .map( section -> section.getMetaDataObject().stream()
                        .map( this::validateMetaDataObject )
                        .collect( toList() ) )
                .orElse( new ArrayList<>() )
                .stream()
                .forEach( result.getMetaDataObject()::add );
        
        return result;
    }
    
    public MetaDataObjectValidityType validateMetaDataObject( MetaDataObjectType metaData )
    {
        MetaDataObjectValidityType result = new MetaDataObjectValidityType();
        result.setMetaDataID( metaData.getMetaDataID() );
        // result.setDataObjectID( value ); TODO report only contains one oid and not a list?! (required!)
        
        // result.setDataObjectCheckSum( value ); TODO
        validateCategory( metaData.getCategory() ).ifPresent( result::setCategory );
        validateClassification( metaData.getCategory(), metaData.getClassification() ).ifPresent( result::setClassification );
        // result.setContent(); omitted since this is only relevant under specific profiles, see BSI TR-ESOR VR
        
        return result;
    }
    
    public Optional<VerificationResultType> validateCategory( String category )
    {
        return Optional.ofNullable( category )
                .map( c -> Arrays.stream( Category.values() )
                        .map( Category::name )
                        .anyMatch( category::equals ) )
                .map( valid -> valid ? DefaultResult.ok().build() : DefaultResult.error().build() )
                .map( VerificationConverter::fromResult );
    }
    
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
                .map( VerificationConverter::fromResult );
    }
}
