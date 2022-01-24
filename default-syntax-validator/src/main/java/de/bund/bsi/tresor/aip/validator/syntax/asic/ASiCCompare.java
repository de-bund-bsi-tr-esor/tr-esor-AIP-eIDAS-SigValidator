package de.bund.bsi.tresor.aip.validator.syntax.asic;

import org.bouncycastle.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author wolffs
 */
@Getter
@ToString
@AllArgsConstructor
public class ASiCCompare
{
    private String c14n;
    private String aoid;
    private String version;
    
    // optional: transform
    
    public boolean matches( ASiCCompare other )
    {
        return Objects.areEqual( c14n, other.getC14n() )
                && Objects.areEqual( aoid, other.getAoid() )
                && Objects.areEqual( version, other.getVersion() );
    }
}
