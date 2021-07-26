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
package de.bund.bsi.tresor.aip.validator.protocol;

import static java.util.Collections.emptyMap;

import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import de.bund.bsi.tr_esor.vr.CredentialValidityType;
import de.bund.bsi.tr_esor.vr.CredentialValidityType.RelatedObjects;
import de.bund.bsi.tr_esor.vr.CredentialsSectionValidityType;
import de.bund.bsi.tr_esor.vr.XAIPValidityType;
import de.bund.bsi.tresor.aip.validator.api.boundary.ProtocolAssembler;
import de.bund.bsi.tresor.aip.validator.api.control.AIPUtil;
import de.bund.bsi.tresor.aip.validator.api.control.ModuleLogger;
import de.bund.bsi.tresor.aip.validator.api.control.VerificationUtil;
import de.bund.bsi.tresor.aip.validator.api.entity.DefaultResult;
import de.bund.bsi.tresor.aip.validator.api.entity.DefaultResult.Major;
import de.bund.bsi.tresor.aip.validator.api.entity.DefaultResult.ResultLanguage;
import de.bund.bsi.tresor.aip.validator.api.entity.ModuleContext;
import de.bund.bsi.tresor.aip.validator.api.entity.NSMapping;
import de.bund.bsi.tresor.aip.validator.syntax.context.DefaultSyntaxValidatorContext;
import lombok.Getter;
import oasis.names.tc.dss._1_0.core.schema.AnyType;
import oasis.names.tc.dss._1_0.core.schema.Result;
import oasis.names.tc.dss._1_0.core.schema.VerificationTimeInfoType;
import oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_.IndividualReportType;
import oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_.SignedObjectIdentifierType;
import oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_.VerificationReportType;
import oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_.VerificationResultType;

/**
 * Implementation of the ProtocolAssembler module from the XAIPValidator.
 * 
 * @author wolffs
 */
@Getter
public class DefaultProtocolAssembler implements ProtocolAssembler
{
    private final String vendor  = "BSI";
    private final String version = "1.0.0";
    
    @Override
    public VerificationReportType assembleProtocols( ModuleContext context, XAIPValidityType xaipReport,
            Collection<CredentialValidityType> credentialReports )
    {
        var completeReport = new VerificationReportType();
        final Set<CredentialValidityType> reports = addRelations( context, new HashSet<>( credentialReports ) );
        
        if ( !reports.isEmpty() && Objects.isNull( xaipReport.getCredentialsSection() ) )
        {
            xaipReport.setCredentialsSection( new CredentialsSectionValidityType() );
        }
        
        Optional.ofNullable( xaipReport.getCredentialsSection() )
                .ifPresent( section -> section.getCredential().addAll( reports ) );
        
        AnyType anyType = new AnyType();
        anyType.getAny().add( AIPUtil.asElement( xaipReport ) );
        
        IndividualReportType individualReport = new IndividualReportType();
        individualReport.setResult( resultSummary( xaipReport ) );
        individualReport.setSignedObjectIdentifier( new SignedObjectIdentifierType() );
        individualReport.setDetails( anyType );
        
        completeReport.getIndividualReport().add( individualReport );
        
        try
        {
            var calendar = new GregorianCalendar();
            calendar.setTime( new Date() );
            
            var date = DatatypeFactory.newInstance().newXMLGregorianCalendar( calendar );
            var time = new VerificationTimeInfoType();
            time.setVerificationTime( date );
            
            completeReport.setVerificationTimeInfo( time );
        }
        catch ( DatatypeConfigurationException e )
        {
            ModuleLogger.verbose( "could not add verifiction time info", e );
        }
        
        return completeReport;
    }
    
    Result resultSummary( XAIPValidityType report )
    {
        Major resultMajor = Major.SUCCESS;
        String resultMessage = "successfully validated the xaip structure and containing signatures";
        
        try
        {
            JAXBContext context = JAXBContext.newInstance( XAIPValidityType.class );
            Marshaller marshaller = context.createMarshaller();
            
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            
            JAXBElement<XAIPValidityType> xaipReport = AIPUtil.asElement( report );
            Document doc = builder.newDocument();
            marshaller.marshal( xaipReport, doc );
            
            NSMapping nsMapping = new NSMapping();
            nsMapping.putNamespace( "ns", "urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#" );
            
            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();
            xpath.setNamespaceContext( nsMapping );
            
            XPathExpression expr = xpath.compile( "//ns:DetailedSignatureReport/ns:SignatureOK/ns:SigMathOK/ns:ResultMajor" );
            NodeList result = (NodeList) expr.evaluate( doc, XPathConstants.NODESET );
            
            for ( int i = 0; i < result.getLength(); i++ )
            {
                Node node = result.item( i );
                Optional<Major> major = DefaultResult.Major.fromString( node.getTextContent() );
                if ( major.isPresent() && !major.get().isPositiv() )
                {
                    resultMajor = Major.REQUESTER_ERROR;
                    resultMessage = "invalid signature(s) were found";
                }
            }
        }
        catch ( ParserConfigurationException | XPathExpressionException | JAXBException e )
        {
            resultMajor = Major.RESPONDER_ERROR;
            ModuleLogger.log( "could not analyse and merge signature results into summary", e );
        }
        
        return DefaultResult.major( resultMajor ).message( resultMessage, ResultLanguage.ENGLISH ).build();
    }
    
    Result createResult( VerificationResultType vrResult )
    {
        Major formatOk = Major.fromUri( vrResult.getResultMajor() ).orElse( Major.RESPONDER_ERROR );
        Major resultMajor = formatOk.isPositiv() ? Major.SUCCESS : Major.RESPONDER_ERROR;
        
        Result result = new Result();
        result.setResultMajor( resultMajor.getUri() );
        result.setResultMinor( vrResult.getResultMinor() );
        result.setResultMessage( vrResult.getResultMessage() );
        
        return result;
    }
    
    Set<CredentialValidityType> addRelations( ModuleContext context, Set<CredentialValidityType> reports )
    {
        Map<String, RelatedObjects> relatedObjectByCredId = context.find( DefaultSyntaxValidatorContext.class )
                .map( DefaultSyntaxValidatorContext::getRelatedObjectByCredId )
                .orElse( emptyMap() );
        
        for ( CredentialValidityType report : reports )
        {
            Optional.ofNullable( relatedObjectByCredId.get( report.getCredentialID() ) )
                    .ifPresent( report::setRelatedObjects );
        }
        
        return reports;
    }
    
    CredentialValidityType ensureReportContent( CredentialValidityType type )
    {
        boolean hasAnyReport = Stream.of( type.getDetailedSignatureReport(),
                type.getEvidenceRecordReport(),
                type.getIndividualAttributeCertificateReport(),
                type.getIndividualCertificateReport(),
                type.getIndividualCRLReport(),
                type.getIndividualOCSPReport(),
                type.getIndividualTimeStampReport(),
                type.getOther() )
                .anyMatch( Predicate.not( Objects::isNull ) );
        
        if ( !hasAnyReport )
        {
            Result result = DefaultResult.major( Major.WARNING )
                    .message( "could not determine any result", ResultLanguage.ENGLISH )
                    .build();
            
            type.setOther( VerificationUtil.verificationResult( result ) );
        }
        
        return type;
    }
}
