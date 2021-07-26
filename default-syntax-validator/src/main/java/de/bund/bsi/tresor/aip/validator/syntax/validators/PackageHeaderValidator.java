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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javax.xml.datatype.XMLGregorianCalendar;

import org.w3._2000._09.xmldsig_.CanonicalizationMethodType;

import de.bund.bsi.tr_esor.vr.PackageHeaderValidityType;
import de.bund.bsi.tr_esor.vr.VersionManifestValidityType;
import de.bund.bsi.tr_esor.xaip.PackageHeaderType;
import de.bund.bsi.tr_esor.xaip.PreservationInfoType;
import de.bund.bsi.tr_esor.xaip.VersionManifestType;
import de.bund.bsi.tresor.aip.validator.api.control.Canonicalization;
import de.bund.bsi.tresor.aip.validator.api.control.VerificationUtil;
import de.bund.bsi.tresor.aip.validator.api.entity.DefaultResult;
import de.bund.bsi.tresor.aip.validator.api.entity.DefaultResult.Minor;
import de.bund.bsi.tresor.aip.validator.api.entity.DefaultResult.ResultLanguage;
import oasis.names.tc.dss._1_0.core.schema.Result;
import oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_.VerificationResultType;

/**
 * Validator for any validations concerning data inside the {@link PackageHeaderType}
 * 
 * @author wolffs
 */
public enum PackageHeaderValidator
{
    INSTANCE;
    
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_DATE;
    
    /**
     * Validating the package header and all sub elements
     * 
     * @param packageHeader
     *            the packageHeader
     * @return the validation result
     */
    public Optional<PackageHeaderValidityType> validatePackageHeader( Optional<PackageHeaderType> packageHeader )
    {
        return packageHeader.map( header -> {
            PackageHeaderValidityType result = new PackageHeaderValidityType();
            result.setAOID( header.getAOID() );
            result.setPackageID( header.getPackageID() );
            // result.setExtension( value ); omitted in the current profile, see BSI TR-ESOR-VR
            
            validateCanonicalization( header.getCanonicalizationMethod() ).ifPresent( result::setCanonicalizationMethod );
            
            header.getVersionManifest().stream()
                    .map( this::validateVersionManifest )
                    .forEach( result.getVersionManifest()::add );
            
            return result;
        } );
    }
    
    /**
     * Validating the version manifest
     * 
     * @param versionManifest
     *            the version manifest
     * @return the validation result
     */
    public VersionManifestValidityType validateVersionManifest( VersionManifestType versionManifest )
    {
        VersionManifestValidityType result = new VersionManifestValidityType();
        result.setVersionID( versionManifest.getVersionID() );
        result.setPreservationInfo( validatePreservation( Optional.ofNullable( versionManifest.getPreservationInfo() ) ) );
        // result.setSubmissionInfo( value ); does not make sense to verify this on client side
        // result.setExtension( value ); omitted in the current profile, see BSI TR-ESOR-VR
        
        return result;
    }
    
    /**
     * Validating the preservation information
     * 
     * @param preservationInfo
     *            the preservation info
     * @return the validation result
     */
    public VerificationResultType validatePreservation( Optional<PreservationInfoType> preservationInfo )
    {
        Result result = preservationInfo
                .map( PreservationInfoType::getRetentionPeriod )
                .map( XMLGregorianCalendar::toXMLFormat )
                .map( date -> LocalDate.parse( date, DATE_FORMATTER ) )
                .map( LocalDate.now()::isBefore )
                .map( notExpired -> {
                    return notExpired ? DefaultResult.valid().build()
                            : DefaultResult.invalid()
                                    .minor( Minor.PRESERVATION_PERIOD_EXPIRED )
                                    .build();
                } ).orElse( DefaultResult.invalid()
                        .message( "missing preservation info", ResultLanguage.ENGLISH )
                        .build() );
        
        return VerificationUtil.verificationResult( result );
    }
    
    /**
     * Validating the canonicalization method which is an optional element. When an non empty element was provided it will be validated and
     * the result contains a verification result. Not providing an element will return an empty optional instead.
     * 
     * @param canonicalizationMethod
     *            the canonicalization method
     * @return the validation result when an element was provided
     */
    public Optional<VerificationResultType> validateCanonicalization( CanonicalizationMethodType canonicalizationMethod )
    {
        return Optional.ofNullable( canonicalizationMethod )
                .map( CanonicalizationMethodType::getAlgorithm )
                .map( Canonicalization::isValidCanonicalization )
                .map( validMethod -> {
                    Result result = DefaultResult.valid()
                            .message( "using valid algorithm " + canonicalizationMethod.getAlgorithm(), ResultLanguage.ENGLISH )
                            .build();
                    
                    if ( !validMethod )
                    {
                        result = DefaultResult.invalid()
                                .minor( Minor.UNKNOWN_C14N_METHOD )
                                .message( "using invalid algorithm " + canonicalizationMethod.getAlgorithm(), ResultLanguage.ENGLISH )
                                .build();
                    }
                    
                    return VerificationUtil.verificationResult( result );
                } );
    }
    
    /**
     * Retrieving the c14nUrl from the packageHeader
     * 
     * @param packageHeader
     *            the packageHeader
     * @return the c14nUrl if present
     */
    public Optional<String> retrieveC14nUrl( PackageHeaderType packageHeader )
    {
        return Optional.ofNullable( packageHeader )
                .map( PackageHeaderType::getCanonicalizationMethod )
                .map( CanonicalizationMethodType::getAlgorithm );
    }
    
}
