package de.bund.bsi.tresor.xaip.validator.signature.entity;

import java.io.InputStream;
import java.util.Optional;

import de.bund.bsi.tr_esor.xaip._1.DataObjectType;
import lombok.Value;

/**
 * @author wolffs
 */
@Value
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
