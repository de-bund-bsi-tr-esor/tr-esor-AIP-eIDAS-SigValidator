package de.bund.bsi.tresor.xaip.validator.signature.entity;

import java.io.InputStream;
import java.util.Optional;

import de.bund.bsi.tr_esor.xaip._1.DataObjectType;
import lombok.AllArgsConstructor;
import lombok.Value;

/**
 * Representation of a found signature which contains the relevant data and the dataObject
 * 
 * @author wolffs
 */
@Value
@AllArgsConstructor
public class FinderResult
{
    private DataObjectType        dataObject;
    private SignaturePresence     presence;
    private Optional<InputStream> data;
    
    /**
     * Creating a new FinderResult
     * 
     * @param dataObject
     *            the dataObject containing a signature
     * @param presence
     *            the presence of a signature
     * @param data
     *            the data of the dataObject (might be an embedded signature)
     */
    public FinderResult( DataObjectType dataObject, SignaturePresence presence, InputStream data )
    {
        this.dataObject = dataObject;
        this.presence = presence;
        this.data = Optional.ofNullable( data );
    }
}
