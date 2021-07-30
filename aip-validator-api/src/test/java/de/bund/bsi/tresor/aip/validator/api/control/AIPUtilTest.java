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
package de.bund.bsi.tresor.aip.validator.api.control;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import org.etsi.uri._02918.v1_2.DataObjectReferenceType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import de.bund.bsi.tr_esor.xaip.CredentialType;
import de.bund.bsi.tr_esor.xaip.DataObjectType;
import de.bund.bsi.tr_esor.xaip.DataObjectsSectionType;
import de.bund.bsi.tr_esor.xaip.MetaDataObjectType;
import de.bund.bsi.tr_esor.xaip.PackageHeaderType;
import de.bund.bsi.tr_esor.xaip.PackageInfoUnitType;
import de.bund.bsi.tr_esor.xaip.VersionManifestType;
import oasis.names.tc.dss._1_0.core.schema.AnyType;

/**
 * @author wolffs
 */
public class AIPUtilTest
{
    
    @Test
    @DisplayName( "should find dataObjectReference" )
    public void findDataReferencesSuccessTest()
    {
        QName name = new QName( "test" );
        JAXBElement<DataObjectReferenceType> element = new JAXBElement<>( name, DataObjectReferenceType.class,
                new DataObjectReferenceType() );
        
        AnyType anyType = new AnyType();
        anyType.getAny().add( element );
        
        DataObjectType dataObject = new DataObjectType();
        dataObject.setXmlData( anyType );
        
        Optional<DataObjectReferenceType> optResult = AIPUtil.findDataReferences( dataObject );
        assertThat( optResult.isPresent(), is( true ) );
    }
    
    @Test
    @DisplayName( "should not find a dataObjectReference" )
    public void findDataReferencesFailureTest()
    {
        DataObjectType dataObject = new DataObjectType();
        
        Optional<DataObjectReferenceType> optResult = AIPUtil.findDataReferences( dataObject );
        assertThat( optResult.isPresent(), is( false ) );
    }
    
    @Test
    @DisplayName( "should find dataObjectById" )
    public void findDataObjectByIdSuccessTest()
    {
        String dataObjectId = "findTestSuccess";
        
        DataObjectType dataObject = new DataObjectType();
        dataObject.setDataObjectID( dataObjectId );
        
        DataObjectsSectionType dataSection = new DataObjectsSectionType();
        dataSection.getDataObject().add( dataObject );
        
        Optional<DataObjectType> optResult = AIPUtil.findDataObjectById( dataSection, dataObjectId );
        
        assertThat( optResult.isPresent(), is( true ) );
        optResult.ifPresent( result -> {
            assertThat( result.getDataObjectID(), is( equalTo( dataObjectId ) ) );
        } );
    }
    
    @Test
    @DisplayName( "should not find any dataObject" )
    public void findDataObjectByIdFailureTest()
    {
        String dataObjectId = "findTestFailure";
        
        DataObjectType dataObject = new DataObjectType();
        dataObject.setDataObjectID( dataObjectId + UUID.randomUUID().toString() );
        
        DataObjectsSectionType dataSection = new DataObjectsSectionType();
        dataSection.getDataObject().add( dataObject );
        
        Optional<DataObjectType> optResult = AIPUtil.findDataObjectById( dataSection, dataObjectId );
        
        assertThat( optResult.isPresent(), is( false ) );
    }
    
    @Test
    @DisplayName( "should resolve relatedDataObjects" )
    public void findRelatedDataObjectSuccessTest()
    {
        String dataObjectId = "dataObjId";
        DataObjectType dataObject = new DataObjectType();
        dataObject.setDataObjectID( dataObjectId );
        
        String dataObjectTypeId = "dataObjectType";
        DataObjectType dataObjectType = new DataObjectType();
        dataObjectType.setDataObjectID( dataObjectTypeId );
        
        DataObjectsSectionType dataSection = new DataObjectsSectionType();
        dataSection.getDataObject().addAll( asList( dataObject, dataObjectType ) );
        
        List<Object> relatedObjects = asList( dataObjectId, dataObjectType );
        
        Set<DataObjectType> result = AIPUtil.resolveRelatedDataObjects( dataSection, DataObjectsSectionType::getDataObject,
                relatedObjects );
        
        assertThat( result, is( notNullValue() ) );
        assertThat( result, hasSize( 2 ) );
        assertThat( result, hasItem( hasProperty( "dataObjectID", equalTo( dataObjectId ) ) ) );
        assertThat( result, hasItem( hasProperty( "dataObjectID", equalTo( dataObjectTypeId ) ) ) );
    }
    
    @Test
    @DisplayName( "should not find relatedDataObjects" )
    public void findRelatedDataObjectFailureTest()
    {
        DataObjectType dataObject = new DataObjectType();
        dataObject.setDataObjectID( "dataObjId" );
        
        DataObjectType dataObjectType = new DataObjectType();
        
        DataObjectsSectionType dataSection = new DataObjectsSectionType();
        dataSection.getDataObject().addAll( asList( dataObject, dataObjectType ) );
        
        List<Object> relatedObjects = asList( "unrelatedId", new DataObjectType() );
        
        Set<DataObjectType> result = AIPUtil.resolveRelatedDataObjects( dataSection, DataObjectsSectionType::getDataObject,
                relatedObjects );
        
        result.stream().map( DataObjectType::getDataObjectID ).forEach( System.out::println );
        
        assertThat( result, is( notNullValue() ) );
        assertThat( result, hasSize( 0 ) );
    }
    
    @ParameterizedTest
    @MethodSource( "objectIdProvider" )
    public void idFromObjectTest( Object object, String expectedId )
    {
        String result = AIPUtil.idFromObject( object );
        
        assertThat( result, is( notNullValue() ) );
        assertThat( result, is( equalTo( expectedId ) ) );
    }
    
    public static Stream<Arguments> objectIdProvider()
    {
        String string = "string";
        
        DataObjectType dataObject = new DataObjectType();
        dataObject.setDataObjectID( "dataObject" );
        
        CredentialType credential = new CredentialType();
        credential.setCredentialID( "credential" );
        
        PackageHeaderType packageHeader = new PackageHeaderType();
        packageHeader.setPackageID( "packageHeader" );
        
        PackageInfoUnitType packageInfoUnit = new PackageInfoUnitType();
        packageInfoUnit.setPackageUnitID( "packageInfoUnit" );
        
        VersionManifestType versionManifest = new VersionManifestType();
        versionManifest.setVersionID( "versionManifest" );
        
        MetaDataObjectType metaData = new MetaDataObjectType();
        metaData.setMetaDataID( "metaData" );
        
        return Stream.of(
                arguments( string, string ),
                arguments( dataObject, dataObject.getDataObjectID() ),
                arguments( credential, credential.getCredentialID() ),
                arguments( packageHeader, packageHeader.getPackageID() ),
                arguments( packageInfoUnit, packageInfoUnit.getPackageUnitID() ),
                arguments( versionManifest, versionManifest.getVersionID() ),
                arguments( metaData, metaData.getMetaDataID() ) );
    }
    
    public void extractXmlDataTest()
    {
        
    }
    
}
