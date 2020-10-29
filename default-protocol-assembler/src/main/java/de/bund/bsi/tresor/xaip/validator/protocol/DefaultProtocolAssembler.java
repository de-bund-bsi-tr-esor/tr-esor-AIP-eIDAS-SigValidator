package de.bund.bsi.tresor.xaip.validator.protocol;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import de.bund.bsi.tr_esor.vr._1.CredentialValidityType;
import de.bund.bsi.tr_esor.vr._1.CredentialsSectionValidityType;
import de.bund.bsi.tr_esor.vr._1.DataObjectValidityType;
import de.bund.bsi.tr_esor.vr._1.DataObjectsSectionValidityType;
import de.bund.bsi.tr_esor.vr._1.XAIPValidityType;
import de.bund.bsi.tresor.xaip.validator.api.boundary.ProtocolAssembler;
import de.bund.bsi.tresor.xaip.validator.api.control.ModuleLogger;
import de.bund.bsi.tresor.xaip.validator.api.control.VerificationUtil;
import de.bund.bsi.tresor.xaip.validator.api.control.XAIPUtil;
import de.bund.bsi.tresor.xaip.validator.api.entity.DefaultResult;
import de.bund.bsi.tresor.xaip.validator.api.entity.DefaultResult.Major;
import de.bund.bsi.tresor.xaip.validator.api.entity.DefaultResult.ResultLanguage;
import lombok.Getter;
import oasis.names.tc.dss._1_0.core.schema.AnyType;
import oasis.names.tc.dss._1_0.core.schema.Result;
import oasis.names.tc.dss._1_0.core.schema.VerificationTimeInfoType;
import oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_.IndividualReportType;
import oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_.SignedObjectIdentifierType;
import oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_.VerificationReportType;

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
    public VerificationReportType assembleProtocols( XAIPValidityType xaipReport, Collection<CredentialValidityType> credentialReports )
    {
        var completeReport = new VerificationReportType();
        
        XAIPValidityType report = mergeProtocols( xaipReport, credentialReports );
        
        AnyType anyType = new AnyType();
        anyType.getAny().add( XAIPUtil.asElement( report ) );
        
        IndividualReportType individualReport = new IndividualReportType();
        individualReport.setResult( VerificationUtil.result( report.getFormatOK() ) );
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
    
    XAIPValidityType mergeProtocols( XAIPValidityType report, Collection<CredentialValidityType> credentialReports )
    {
        List<CredentialValidityType> masterReports = Optional.ofNullable( report )
                .map( XAIPValidityType::getCredentialsSection )
                .map( CredentialsSectionValidityType::getCredential )
                .orElse( new ArrayList<>() );
        
        // embedded signatures
        Set<String> embedded = Optional.ofNullable( report )
                .map( XAIPValidityType::getDataObjectsSection )
                .map( DataObjectsSectionValidityType::getDataObject )
                .orElse( new ArrayList<>() )
                .stream()
                .map( DataObjectValidityType::getDataObjectID )
                .collect( toSet() );
        
        masterReports.addAll( credentialReports.stream()
                .filter( f -> embedded.contains( f.getCredentialID() ) )
                .collect( toList() ) );
        
        // detached signatures
        masterReports.replaceAll( masterReport -> {
            final String masterId = masterReport.getCredentialID();
            
            return credentialReports.stream()
                    .filter( f -> f.getCredentialID().equals( masterId ) )
                    .findAny()
                    .map( subReport -> mergeSubReport( masterReport, subReport ) )
                    .orElse( ensureReportContent( masterReport ) );
        } );
        
        return report;
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
    
    CredentialValidityType mergeSubReport( CredentialValidityType master, CredentialValidityType sub )
    {
        Optional.ofNullable( sub.getDetailedSignatureReport() ).ifPresent( master::setDetailedSignatureReport );
        Optional.ofNullable( sub.getEvidenceRecordReport() ).ifPresent( master::setEvidenceRecordReport );
        Optional.ofNullable( sub.getIndividualAttributeCertificateReport() ).ifPresent( master::setIndividualAttributeCertificateReport );
        Optional.ofNullable( sub.getIndividualCertificateReport() ).ifPresent( master::setIndividualCertificateReport );
        Optional.ofNullable( sub.getIndividualCRLReport() ).ifPresent( master::setIndividualCRLReport );
        Optional.ofNullable( sub.getIndividualOCSPReport() ).ifPresent( master::setIndividualOCSPReport );
        Optional.ofNullable( sub.getIndividualTimeStampReport() ).ifPresent( master::setIndividualTimeStampReport );
        Optional.ofNullable( sub.getOther() ).ifPresent( master::setOther );
        
        return master;
    }
}
