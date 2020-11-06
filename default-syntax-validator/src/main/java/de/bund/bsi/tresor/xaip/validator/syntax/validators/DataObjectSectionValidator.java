package de.bund.bsi.tresor.xaip.validator.syntax.validators;

import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.etsi.uri._02918.v1_2.DataObjectReferenceType;
import org.w3._2000._09.xmldsig_.DigestMethodType;

import de.bund.bsi.tr_esor.vr._1.DataObjectValidityType;
import de.bund.bsi.tr_esor.vr._1.DataObjectsSectionValidityType;
import de.bund.bsi.tr_esor.vr._1.TransformInfoValidityType;
import de.bund.bsi.tr_esor.xaip._1.CheckSumType;
import de.bund.bsi.tr_esor.xaip._1.DataObjectType;
import de.bund.bsi.tr_esor.xaip._1.DataObjectsSectionType;
import de.bund.bsi.tr_esor.xaip._1.TranformInfoType;
import de.bund.bsi.tr_esor.xaip._1.TransformObjectType;
import de.bund.bsi.tresor.xaip.validator.api.control.ModuleLogger;
import de.bund.bsi.tresor.xaip.validator.api.control.VerificationUtil;
import de.bund.bsi.tresor.xaip.validator.api.control.XAIPUtil;
import de.bund.bsi.tresor.xaip.validator.api.entity.DefaultResult;
import de.bund.bsi.tresor.xaip.validator.api.entity.DefaultResult.Builder;
import de.bund.bsi.tresor.xaip.validator.api.entity.DefaultResult.Major;
import de.bund.bsi.tresor.xaip.validator.api.entity.DefaultResult.Minor;
import de.bund.bsi.tresor.xaip.validator.api.entity.DefaultResult.ResultLanguage;
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
     * @return the dataObjectsSection validation result
     */
    public Optional<DataObjectsSectionValidityType> validateDataSection( Optional<DataObjectsSectionType> dataObjectsSection )
    {
        List<DataObjectValidityType> data = dataObjectsSection
                .map( section -> section.getDataObject().stream()
                        .map( this::validateDataObject )
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
     * @return the dataObject validation result
     */
    public DataObjectValidityType validateDataObject( DataObjectType dataObject )
    {
        DataObjectValidityType result = new DataObjectValidityType();
        result.setDataObjectID( dataObject.getDataObjectID() );
        
        Optional.ofNullable( dataObject.getCheckSum() )
                .map( checkSum -> VerificationUtil.verifyChecksum( XAIPUtil.retrieveContent( dataObject ), checkSum ) )
                .ifPresent( result::setChecksum );
        
        XAIPUtil.findDataReferences( dataObject )
                .map( this::verifyLXAIP )
                .ifPresent( result::setChecksum );
        
        validateTransformInfo( dataObject.getTransformInfo() ).ifPresent( result::setTransformInfo );
        // result.setFormatOK( value ); TODO implementing on a different date and leaving this blank since it's optional
        
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
                    
                    Result result = DefaultResult.major( Major.WARNING )
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
        
        Optional<Path> optFilePath = Optional.ofNullable( dataObjectReference.getURI() )
                .map( URI::create )
                .map( Paths::get );
        
        if ( optDigestAlgorithm.isPresent() && optFilePath.isPresent() )
        {
            String digestAlg = optDigestAlgorithm.get();
            Path filePath = optFilePath.get();
            
            if ( !Files.isReadable( filePath ) )
            {
                builder.minor( Minor.NO_PERMISSION );
            }
            else
            {
                CheckSumType checksum = new CheckSumType();
                checksum.setCheckSum( dataObjectReference.getDigestValue() );
                checksum.setCheckSumAlgorithm( digestAlg );
                
                try ( InputStream content = Files.newInputStream( filePath ) )
                {
                    return VerificationUtil.verifyChecksum( content, checksum );
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
                    .message( "dataObject reference contains invalid parameter", ResultLanguage.ENGLISH );
        }
        
        return VerificationUtil.verificationResult( builder.build() );
    }
}