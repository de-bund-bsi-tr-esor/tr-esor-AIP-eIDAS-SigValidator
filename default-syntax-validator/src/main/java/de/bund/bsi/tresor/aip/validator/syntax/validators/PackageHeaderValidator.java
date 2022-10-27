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
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.xml.datatype.XMLGregorianCalendar;

import org.etsi.uri._02918.v1_2.DataObjectReferenceType;
import org.w3._2000._09.xmldsig_.CanonicalizationMethodType;
import org.w3._2000._09.xmldsig_.DigestMethodType;

import de.bund.bsi.tr_esor.vr.IdAssignmentListValidityType;
import de.bund.bsi.tr_esor.vr.IdAssignmentPointerValidityType;
import de.bund.bsi.tr_esor.vr.PackageHeaderValidityType;
import de.bund.bsi.tr_esor.vr.VersionManifestValidityType;
import de.bund.bsi.tr_esor.xaip.CheckSumType;
import de.bund.bsi.tr_esor.xaip.DataObjectType;
import de.bund.bsi.tr_esor.xaip.IdAssignmentListType;
import de.bund.bsi.tr_esor.xaip.MetaDataObjectType;
import de.bund.bsi.tr_esor.xaip.PackageHeaderType;
import de.bund.bsi.tr_esor.xaip.PreservationInfoType;
import de.bund.bsi.tr_esor.xaip.VersionManifestType;
import de.bund.bsi.tresor.aip.validator.api.control.AIPUtil;
import de.bund.bsi.tresor.aip.validator.api.control.Canonicalization;
import de.bund.bsi.tresor.aip.validator.api.control.ModuleLogger;
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
     * @param xmlDataByOid
     *            the raw xmlData by objectID
     * @return the validation result
     */
    public Optional<PackageHeaderValidityType> validatePackageHeader( Optional<PackageHeaderType> packageHeader,
            Map<String, File> xmlDataByOid )
    {
        return packageHeader.map( header -> {
            PackageHeaderValidityType result = new PackageHeaderValidityType();
            result.setAOID( header.getAOID() );
            result.setPackageID( header.getPackageID() );
            // result.setExtension( value ); omitted in the current profile, see BSI TR-ESOR-VR
            
            validateCanonicalization( header.getCanonicalizationMethod() ).ifPresent( result::setCanonicalizationMethod );
            
            header.getVersionManifest().stream()
                    .map( m -> validateVersionManifest( m, xmlDataByOid ) )
                    .forEach( result.getVersionManifest()::add );
            
            return result;
        } );
    }
    
    /**
     * Validating the version manifest
     * 
     * @param versionManifest
     *            the version manifest
     * @param xmlDataByOid
     *            the raw xmlData by objectID
     * @return the validation result
     */
    public VersionManifestValidityType validateVersionManifest( VersionManifestType versionManifest, Map<String, File> xmlDataByOid )
    {
        VersionManifestValidityType result = new VersionManifestValidityType();
        result.setVersionID( versionManifest.getVersionID() );
        result.setPreservationInfo( validatePreservation( Optional.ofNullable( versionManifest.getPreservationInfo() ) ) );
        result.setIdAssignmentList( validateAssignmentList( versionManifest.getIdAssignmentList(), xmlDataByOid ) );
        
        // result.setSubmissionInfo( value ); does not make sense to verify this on client side
        // result.setExtension( value ); omitted in the current profile, see BSI TR-ESOR-VR
        
        return result;
    }
    
    /**
     * Validating the idAssignmentList
     * 
     * @param idAssignmentListType
     *            the list
     * @param xmlDataByOid
     *            the raw xmlData
     * @return the result
     */
    public IdAssignmentListValidityType validateAssignmentList( IdAssignmentListType idAssignmentListType, Map<String, File> xmlDataByOid )
    {
        if ( idAssignmentListType == null )
        {
            return null;
        }
        
        List<IdAssignmentPointerValidityType> list = idAssignmentListType.getIdAssignmentPointer().stream()
                .map( type -> {
                    Object objectRef = type.getObjectRef();
                    String oid = AIPUtil.idFromObject( objectRef );
                    Optional<File> optXmlFile = Optional.ofNullable( xmlDataByOid.get( oid ) );
                    
                    IdAssignmentPointerValidityType val = new IdAssignmentPointerValidityType();
                    val.setObjectRef( oid );
                    val.setCheckSum( verifyChecksum( objectRef, type.getCheckSum(), optXmlFile ) );
                    
                    return val;
                } )
                .collect( toList() );
        
        IdAssignmentListValidityType report = new IdAssignmentListValidityType();
        report.setIdAssignmentListID( idAssignmentListType.getIdAssignmentListID() );
        
        if ( !list.isEmpty() )
        {
            report.getIdAssignmentPointer().addAll( list );
        }
        
        return report;
    }
    
    VerificationResultType verifyChecksum( Object objectRef, CheckSumType checkSum, Optional<File> optXmlFile )
    {
        try
        {
            Optional<byte[]> data = Optional.empty();
            if ( objectRef instanceof DataObjectType )
            {
                DataObjectType dataObject = (DataObjectType) objectRef;
                Optional<VerificationResultType> lxaipResult = AIPUtil.findDataReferences( dataObject )
                        .map( ref -> {
                            DigestMethodType digestMethod = new DigestMethodType();
                            digestMethod.setAlgorithm( checkSum.getCheckSumAlgorithm() );
                            
                            DataObjectReferenceType assignmentRef = new DataObjectReferenceType();
                            assignmentRef.setURI( ref.getURI() );
                            assignmentRef.setDigestMethod( digestMethod );
                            assignmentRef.setRootfile( ref.isRootfile() );
                            assignmentRef.setMimeType( ref.getMimeType() );
                            assignmentRef.setDigestValue( checkSum.getCheckSum() );
                            assignmentRef.setDataObjectReferenceExtensions( ref.getDataObjectReferenceExtensions() );
                            
                            return DataObjectSectionValidator.INSTANCE.verifyLXAIP( assignmentRef );
                        } );
                
                if ( lxaipResult.isPresent() )
                {
                    return lxaipResult.get();
                }
                
                data = AIPUtil.extractData( AIPUtil.binaryDataSupplier( dataObject ), dataObject::getXmlData );
            }
            else if ( objectRef instanceof MetaDataObjectType )
            {
                MetaDataObjectType metaDataObject = (MetaDataObjectType) objectRef;
                data = AIPUtil.extractData( AIPUtil.binaryDataSupplier( metaDataObject ), metaDataObject::getXmlMetaData );
            }
            
            try ( InputStream stream = optXmlFile.isPresent() ? new FileInputStream( optXmlFile.get() )
                    : data.map( ByteArrayInputStream::new ).orElse( new ByteArrayInputStream( new byte[0] ) ) )
            {
                return VerificationUtil.verifyChecksum( stream, checkSum, false );
            }
        }
        catch ( Exception e )
        {
            ModuleLogger.log( "could not retrieve data for checksum validation", e );
            
            return VerificationUtil.verificationResult( DefaultResult.error().minor( Minor.NO_DATA_ACCESS_WARNING ).build() );
        }
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
                .map( method -> {
                    Result result = DefaultResult.valid()
                            .message( "using valid algorithm " + canonicalizationMethod.getAlgorithm(), ResultLanguage.ENGLISH )
                            .build();
                    
                    if ( !Canonicalization.isValidCanonicalization( method ) )
                    {
                        result = DefaultResult.invalid()
                                .minor( Minor.UNKNOWN_C14N_METHOD )
                                .message( "using invalid algorithm " + canonicalizationMethod.getAlgorithm(), ResultLanguage.ENGLISH )
                                .build();
                    }
                    else
                    {
                        Canonicalization.setC14nMethod( method );
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
