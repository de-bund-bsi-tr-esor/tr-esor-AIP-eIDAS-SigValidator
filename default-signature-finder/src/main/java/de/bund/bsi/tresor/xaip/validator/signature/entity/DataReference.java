package de.bund.bsi.tresor.xaip.validator.signature.entity;

import java.util.Optional;

import org.etsi.uri._02918.v1_2.DataObjectReferenceType;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_.IndividualReportType;

/**
 * Found dataObjectReference and verification result.
 * 
 * @author bendlera
 *
 */
@Value
@RequiredArgsConstructor
public class DataReference
{
    private final Optional<DataObjectReferenceType> dataObjectReference;
    private final IndividualReportType              individualReportType;
}
