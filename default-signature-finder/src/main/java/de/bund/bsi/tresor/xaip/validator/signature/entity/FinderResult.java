package de.bund.bsi.tresor.xaip.validator.signature.entity;

import java.io.InputStream;
import java.util.Optional;

import de.bund.bsi.tr_esor.xaip._1.DataObjectType;
import lombok.AllArgsConstructor;
import lombok.Value;

/**
 * @author wolffs
 */
@Value
@AllArgsConstructor
public class FinderResult
{
    private DataObjectType        dataObject;
    private SignaturePresence     presence;
    private Optional<InputStream> data;
    
    public FinderResult( DataObjectType dataObject, SignaturePresence presence, InputStream data )
    {
        this.dataObject = dataObject;
        this.presence = presence;
        this.data = Optional.ofNullable( data );
    }
}
