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
package de.bund.bsi.tresor.aip.validator.syntax;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.etsi.uri._02918.v1_2.DataObjectReferenceType;

import de.bund.bsi.tr_esor.vr.RelatedObjectsType;
import de.bund.bsi.tr_esor.vr.XAIPValidityType;
import de.bund.bsi.tr_esor.xaip.DataObjectsSectionType;
import de.bund.bsi.tr_esor.xaip.XAIPType;
import de.bund.bsi.tresor.aip.validator.api.boundary.SyntaxValidator;
import de.bund.bsi.tresor.aip.validator.api.control.AIPUtil;
import de.bund.bsi.tresor.aip.validator.api.control.EventReader;
import de.bund.bsi.tresor.aip.validator.api.control.ModuleLogger;
import de.bund.bsi.tresor.aip.validator.api.control.VerificationUtil;
import de.bund.bsi.tresor.aip.validator.api.entity.AIPValidatorException;
import de.bund.bsi.tresor.aip.validator.api.entity.DefaultResult;
import de.bund.bsi.tresor.aip.validator.api.entity.DefaultResult.Minor;
import de.bund.bsi.tresor.aip.validator.api.entity.DefaultResult.ResultLanguage;
import de.bund.bsi.tresor.aip.validator.api.entity.ModuleContext;
import de.bund.bsi.tresor.aip.validator.api.entity.SyntaxValidationResult;
import de.bund.bsi.tresor.aip.validator.syntax.context.DefaultSyntaxValidatorContext;
import de.bund.bsi.tresor.aip.validator.syntax.validators.ASiCAIPValidator;
import de.bund.bsi.tresor.aip.validator.syntax.validators.CredentialSectionValidator;
import de.bund.bsi.tresor.aip.validator.syntax.validators.DataObjectSectionValidator;
import de.bund.bsi.tresor.aip.validator.syntax.validators.MetaDataValidator;
import de.bund.bsi.tresor.aip.validator.syntax.validators.PackageHeaderValidator;
import lombok.Getter;
import oasis.names.tc.dss._1_0.core.schema.Result;

/**
 * Implementation of the SyntaxValidator module from the XAIPValidator.
 * 
 * @author wolffs
 */
@Getter
public class DefaultSyntaxValidator implements SyntaxValidator
{
    private static final String        SCHEMA_DIR_PROPERTY = "validator.schemaDir";
    
    private final String               vendor              = "BSI";
    private final String               version             = "1.1.0";
    private String                     schemaDir;
    
    private ASiCAIPValidator           asicAipValidator    = ASiCAIPValidator.INSTANCE;
    private MetaDataValidator          metaValidator       = MetaDataValidator.INSTANCE;
    private PackageHeaderValidator     packageValidator    = PackageHeaderValidator.INSTANCE;
    private DataObjectSectionValidator dataValidator       = DataObjectSectionValidator.INSTANCE;
    private CredentialSectionValidator credentialValidator = CredentialSectionValidator.INSTANCE;
    
    @Override
    public void configure( Map<String, String> properties )
    {
        schemaDir = Optional.ofNullable( properties.get( SCHEMA_DIR_PROPERTY ) )
                .orElseThrow( () -> new AIPValidatorException( "missing property " + SCHEMA_DIR_PROPERTY ) );
    }
    
    @Override
    public SyntaxValidationResult validateSyntax( ModuleContext context, InputStream xaipCandidate )
    {
        Optional<XAIPType> optXaip = Optional.empty();
        XAIPValidityType report = new XAIPValidityType();
        
        Result result = DefaultResult.valid()
                .message( "xaip is schema conform", ResultLanguage.ENGLISH )
                .build();
        
        Map<String, File> xmlDataByOid = new HashMap<>();
        try ( ByteArrayOutputStream baos = new ByteArrayOutputStream() )
        {
            IOUtils.copy( xaipCandidate, baos );
            byte[] data = baos.toByteArray();
            
            if ( asicAipValidator.isASiC( data ) )
            {
                data = asicAipValidator.findAIPCandidate( data );
            }
            
            xmlDataByOid = xmlData( data );
            
            JAXBContext jaxbContext = JAXBContext.newInstance( XAIPType.class, DataObjectReferenceType.class );
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            
            SchemaFactory schemaFactory = SchemaFactory.newInstance( XMLConstants.W3C_XML_SCHEMA_NS_URI );
            Schema schema = schemaFactory.newSchema( sources( new File( schemaDir ) ).stream().toArray( Source[]::new ) );
            
            jaxbUnmarshaller.setSchema( schema );
            JAXBElement<XAIPType> element = jaxbUnmarshaller.unmarshal(
                    new StreamSource( new ByteArrayInputStream( data ) ), XAIPType.class );
            
            optXaip = Optional.ofNullable( element.getValue() );
            DataObjectsSectionType dataSection = optXaip.map( XAIPType::getDataObjectsSection ).orElse( null );
            
            packageValidator.validatePackageHeader( optXaip.map( XAIPType::getPackageHeader ) )
                    .ifPresent( report::setPackageHeader );
            
            metaValidator.validateMetaDataSection( optXaip.map( XAIPType::getMetaDataSection ), dataSection, xmlDataByOid )
                    .ifPresent( report::setMetaDataSection );
            
            dataValidator.validateDataSection( optXaip.map( XAIPType::getDataObjectsSection ), xmlDataByOid )
                    .ifPresent( report::setDataObjectsSection );
            
            Map<String, RelatedObjectsType> credential = credentialValidator
                    .validateCredentialsSection( optXaip.map( XAIPType::getCredentialsSection ) );
            
            DefaultSyntaxValidatorContext syntaxContext = new DefaultSyntaxValidatorContext( data, credential );
            context.put( DefaultSyntaxValidatorContext.class, syntaxContext );
        }
        catch ( Exception e )
        {
            ModuleLogger.verbose( "syntax validation errors", e );
            result = DefaultResult.invalid()
                    .minor( Minor.INVALID_FORMAT )
                    .message( "xaip is not schema conform: " + e.getMessage(), ResultLanguage.ENGLISH )
                    .build();
        }
        finally
        {
            xmlDataByOid.values().forEach( FileUtils::deleteQuietly );
        }
        
        report.setReportVersion( "1.3" );
        report.setFormatOK( VerificationUtil.verificationResult( result ) );
        
        return new SyntaxValidationResult( optXaip, report );
    }
    
    /**
     * Traversing through the directory and converting every file to a {@link StreamSource}
     * 
     * @param schemaDirectory
     *            the directory containing all schema files
     *            
     * @return the schema sources
     */
    List<Source> sources( File schemaDirectory )
    {
        List<Source> schemas = new ArrayList<>();
        for ( File file : schemaDirectory.listFiles() )
        {
            if ( !file.isHidden() )
            {
                if ( file.isDirectory() )
                {
                    schemas.addAll( sources( file ) );
                }
                else if ( file.getName().endsWith( ".xsd" ) )
                {
                    schemas.add( new StreamSource( file ) );
                }
            }
        }
        
        return schemas;
    }
    
    /**
     * Creating tempFiles for embedded xmlData which are being used for checksum verification. This in combination of c14n is required for a
     * correct checksum verification since the domParser (used by {@link AIPUtil}) will not contain all the required bytes.
     * 
     * @param data
     *            the xaipData
     * @return the xaipData content by their objectId
     * @throws Exception
     */
    Map<String, File> xmlData( byte[] data ) throws Exception
    {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLEventReader reader = xmlInputFactory.createXMLEventReader( new ByteArrayInputStream( data ) );
        
        EventReader eventReader = new EventReader();
        eventReader.read( reader );
        
        return eventReader.getFiles();
    }
}
