package de.bund.bsi.tresor.aip.validator.signature;

/**
 * Byte implementation of the {@link CharSequence}
 * 
 * @author wolffs
 */
public class ByteCharSequence implements CharSequence
{
    
    private final byte[] data;
    private final int    length;
    private final int    offset;
    
    /**
     * Constructing a new {@link ByteCharSequence}
     * 
     * @param data
     *            the byte array
     */
    public ByteCharSequence( byte[] data )
    {
        this( data, 0, data.length );
    }
    
    /**
     * Constructing a new {@link ByteCharSequence}
     * 
     * @param data
     *            the byte array
     * @param offset
     *            the offset
     * @param length
     *            the length
     */
    public ByteCharSequence( byte[] data, int offset, int length )
    {
        this.data = data;
        this.offset = offset;
        this.length = length;
    }
    
    @Override
    public int length()
    {
        return this.length;
    }
    
    @Override
    public char charAt( int index )
    {
        return (char) (data[offset + index] & 0xff);
    }
    
    @Override
    public CharSequence subSequence( int start, int end )
    {
        return new ByteCharSequence( data, offset + start, end - start );
    }
}
