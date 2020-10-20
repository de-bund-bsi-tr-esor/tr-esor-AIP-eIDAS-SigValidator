package de.bund.bsi.tresor.xaip.validator.syntax.validators;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Optional;

import javax.xml.datatype.XMLGregorianCalendar;

import org.w3._2000._09.xmldsig_.CanonicalizationMethodType;

import de.bund.bsi.tr_esor.vr._1.PackageHeaderValidityType;
import de.bund.bsi.tr_esor.vr._1.VersionManifestValidityType;
import de.bund.bsi.tr_esor.xaip._1.PackageHeaderType;
import de.bund.bsi.tr_esor.xaip._1.PreservationInfoType;
import de.bund.bsi.tr_esor.xaip._1.VersionManifestType;
import de.bund.bsi.tresor.xaip.validator.api.entity.DefaultResult;
import de.bund.bsi.tresor.xaip.validator.api.entity.DefaultResult.Minor;
import de.bund.bsi.tresor.xaip.validator.api.entity.DefaultResult.ResultLanguage;
import de.bund.bsi.tresor.xaip.validator.api.entity.VerificationConverter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import oasis.names.tc.dss._1_0.core.schema.Result;
import oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_.VerificationResultType;

/**
 * @author wolffs
 */
public enum PackageHeaderValidator
{
    INSTANCE;
    
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_DATE;
    
    @Getter
    @RequiredArgsConstructor
    enum Canonicalization
    {
        CANONIC_XML( "http://www.w3.org/TR/2001/REC-xml-c14n-20010315" ),
        
        EXCLUSIVE_XML( "https://www.w3.org/TR/2002/REC-xml-exc-c14n" );
        
        private final String url;
    }
    
    public PackageHeaderValidityType validatePackageHeader( Optional<PackageHeaderType> packageHeader )
    {
        PackageHeaderValidityType result = new PackageHeaderValidityType();
        packageHeader.ifPresent( header -> {
            result.setAOID( header.getAOID() );
            result.setPackageID( header.getPackageID() );
            
            validateCanonicalization( header.getCanonicalizationMethod() ).ifPresent( result::setCanonicalizationMethod );
            // TODO result.setExtension( value );
            
            header.getVersionManifest().stream()
                    .map( this::validateVersionManifest )
                    .forEach( result.getVersionManifest()::add );
        } );
        
        return result;
    }
    
    public VersionManifestValidityType validateVersionManifest( VersionManifestType versionManifest )
    {
        VersionManifestValidityType result = new VersionManifestValidityType();
        result.setVersionID( versionManifest.getVersionID() );
        result.setPreservationInfo( validatePreservation( Optional.ofNullable( versionManifest.getPreservationInfo() ) ) );
        // TODO result.setSubmissionInfo( value ); does not make sense to verify this on clients
        // TODO result.setExtension( value );
        
        return result;
    }
    
    public VerificationResultType validatePreservation( Optional<PreservationInfoType> preservationInfo )
    {
        Result result = preservationInfo
                .map( PreservationInfoType::getRetentionPeriod )
                .map( XMLGregorianCalendar::toXMLFormat )
                .map( date -> LocalDate.parse( date, DATE_FORMATTER ) )
                .map( LocalDate.now()::isBefore )
                .map( notExpired -> {
                    return notExpired ? DefaultResult.ok().build()
                            : DefaultResult.error()
                                    .minor( Minor.PRESERVATION_PERIOD_EXPIRED )
                                    .build();
                } ).orElse( DefaultResult.error()
                        .message( "missing preservation info", ResultLanguage.ENGLISH )
                        .build() );
        
        return VerificationConverter.fromResult( result );
    }
    
    /**
     * @param canonicalizationMethod
     * @return
     */
    public Optional<VerificationResultType> validateCanonicalization( CanonicalizationMethodType canonicalizationMethod )
    {
        return Optional.ofNullable( canonicalizationMethod )
                .map( CanonicalizationMethodType::getAlgorithm )
                .map( cm -> Arrays.stream( Canonicalization.values() )
                        .map( Canonicalization::getUrl )
                        .anyMatch( cm::startsWith ) )
                .map( validMethod -> {
                    Result result = DefaultResult.ok()
                            .message( "using valid algorithm" + canonicalizationMethod.getAlgorithm(), ResultLanguage.ENGLISH )
                            .build();
                    
                    if ( !validMethod )
                    {
                        result = DefaultResult.error()
                                .minor( Minor.UNKNOWN_CANONICALIZATION_METHOD )
                                .message( "using invalid algorithm" + canonicalizationMethod.getAlgorithm(), ResultLanguage.ENGLISH )
                                .build();
                    }
                    
                    return VerificationConverter.fromResult( result );
                } );
    }
    
}
