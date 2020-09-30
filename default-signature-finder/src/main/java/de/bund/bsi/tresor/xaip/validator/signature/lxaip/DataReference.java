package de.bund.bsi.tresor.xaip.validator.signature.lxaip;

import java.util.Optional;

import org.etsi.uri._02918.v1_2.DataObjectReferenceType;

import oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_.IndividualReportType;

/**
 * Found dataObjectReference and verification result.
 * 
 * @author bendlera
 *
 */
public class DataReference
{
    private final Optional<DataObjectReferenceType> dataObjectReference;
    private final IndividualReportType              individualReportType;
    
    /**
     * @param dataObjectReference
     *            the dataObjectReference
     * @param individualReportType
     *            the individualReportType
     */
    public DataReference( Optional<DataObjectReferenceType> dataObjectReference, IndividualReportType individualReportType )
    {
        this.dataObjectReference = dataObjectReference;
        this.individualReportType = individualReportType;
    }
    
    /**
     * @return the dataObjectReference
     */
    public Optional<DataObjectReferenceType> getDataObjectReference()
    {
        return dataObjectReference;
    }
    
    /**
     * @return the individualReportType
     */
    public IndividualReportType getIndividualReportType()
    {
        return individualReportType;
    }
    
}
