package de.bund.bsi.tresor.xaip.validator.protocol;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import de.bund.bsi.tr_esor.vr._1.CredentialValidityType;
import de.bund.bsi.tr_esor.vr._1.CredentialsSectionValidityType;
import de.bund.bsi.tr_esor.vr._1.XAIPValidityType;
import de.bund.bsi.tresor.xaip.validator.api.boundary.ProtocolAssembler;
import de.bund.bsi.tresor.xaip.validator.api.control.ModuleLogger;
import de.bund.bsi.tresor.xaip.validator.api.control.XAIPUtil;
import lombok.Getter;
import oasis.names.tc.dss._1_0.core.schema.AnyType;
import oasis.names.tc.dss._1_0.core.schema.VerificationTimeInfoType;
import oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_.IndividualReportType;
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
        
        masterReports.replaceAll( masterReport -> {
            final String masterId = masterReport.getCredentialID();
            
            return credentialReports.stream()
                    .filter( f -> f.getCredentialID().equals( masterId ) )
                    .findAny()
                    .map( subReport -> mergeSubReport( masterReport, subReport ) )
                    .orElse( masterReport );
        } );
        
        return report;
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
        
        return master;
    }
}
