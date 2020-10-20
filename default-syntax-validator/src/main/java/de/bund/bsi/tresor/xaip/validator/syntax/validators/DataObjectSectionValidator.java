package de.bund.bsi.tresor.xaip.validator.syntax.validators;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Optional;

import de.bund.bsi.tr_esor.vr._1.DataObjectValidityType;
import de.bund.bsi.tr_esor.vr._1.DataObjectsSectionValidityType;
import de.bund.bsi.tr_esor.xaip._1.DataObjectType;
import de.bund.bsi.tr_esor.xaip._1.DataObjectsSectionType;

/**
 * @author wolffs
 */
public enum DataObjectSectionValidator
{
    INSTANCE;
    
    public DataObjectsSectionValidityType validateDataSection( DataObjectsSectionType dataObjectsSection )
    {
        DataObjectsSectionValidityType result = new DataObjectsSectionValidityType();
        Optional.ofNullable( dataObjectsSection )
                .map( section -> section.getDataObject().stream()
                        .map( this::validateDataObject )
                        .collect( toList() ) )
                .orElse( new ArrayList<>() )
                .stream()
                .forEach( result.getDataObject()::add );
        
        return result;
    }
    
    public DataObjectValidityType validateDataObject( DataObjectType dataObject )
    {
        DataObjectValidityType result = new DataObjectValidityType();
        result.setDataObjectID( dataObject.getDataObjectID() );
        
        // result.setChecksum( value ); TODO
        // result.setTransformInfo( value ); TODO single result but multi in object
        // result.setFormatOK( value ); leaving this blank since it's optional
        
        return result;
    }
    
}
