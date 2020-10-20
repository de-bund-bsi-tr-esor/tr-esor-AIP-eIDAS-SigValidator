package de.bund.bsi.tresor.xaip.validator.protocol;

import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import de.bund.bsi.tr_esor.vr._1.XAIPValidityType;
import de.bund.bsi.tresor.xaip.validator.api.boundary.ProtocolAssembler;
import de.bund.bsi.tresor.xaip.validator.api.control.ModuleLogger;
import lombok.Getter;
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
    public VerificationReportType assembleProtocols( Collection<XAIPValidityType> protocols )
    {
        XAIPValidityType foo = new XAIPValidityType();
        foo.setFormatOK( value );
        foo.set
        
        var completeReport = new VerificationReportType();
        for ( IndividualReportType report : protocols )
        {
            completeReport.getIndividualReport().add( report );
        }
        
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
}
