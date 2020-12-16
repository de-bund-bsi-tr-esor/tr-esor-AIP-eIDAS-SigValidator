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
package de.bund.bsi.tresor.xaip.validator.protocol;

import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import de.bund.bsi.tr_esor.vr._1.CredentialValidityType;
import de.bund.bsi.tr_esor.vr._1.CredentialsSectionValidityType;
import de.bund.bsi.tr_esor.vr._1.XAIPValidityType;
import de.bund.bsi.tresor.xaip.validator.api.boundary.ProtocolAssembler;
import de.bund.bsi.tresor.xaip.validator.api.control.ModuleLogger;
import de.bund.bsi.tresor.xaip.validator.api.control.VerificationUtil;
import de.bund.bsi.tresor.xaip.validator.api.control.XAIPUtil;
import de.bund.bsi.tresor.xaip.validator.api.entity.DefaultResult;
import de.bund.bsi.tresor.xaip.validator.api.entity.DefaultResult.Major;
import de.bund.bsi.tresor.xaip.validator.api.entity.DefaultResult.ResultLanguage;
import de.bund.bsi.tresor.xaip.validator.api.entity.ModuleContext;
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
    public VerificationReportType assembleProtocols( ModuleContext context, XAIPValidityType xaipReport,
            Collection<CredentialValidityType> credentialReports )
    {
        var completeReport = new VerificationReportType();
        
        Set<CredentialValidityType> reports = new HashSet<>( credentialReports );
        if ( !credentialReports.isEmpty() && Objects.isNull( xaipReport.getCredentialsSection() ) )
        {
            xaipReport.setCredentialsSection( new CredentialsSectionValidityType() );
        }
        
        xaipReport.getCredentialsSection().getCredential().addAll( reports );
        
        AnyType anyType = new AnyType();
        anyType.getAny().add( XAIPUtil.asElement( xaipReport ) );
        
        IndividualReportType individualReport = new IndividualReportType();
        individualReport.setResult( VerificationUtil.result( xaipReport.getFormatOK() ) );
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
