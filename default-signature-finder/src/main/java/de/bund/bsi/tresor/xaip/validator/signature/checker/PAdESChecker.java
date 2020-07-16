package de.bund.bsi.tresor.xaip.validator.signature.checker;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSObject;
import org.apache.pdfbox.io.RandomAccessBuffer;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;

/**
 * @author wolffs
 */
public enum PAdESChecker
{
    INSTANCE;
    
    private final byte[] pdfMagicNumber = "%PDF".getBytes( StandardCharsets.US_ASCII );
    
    public boolean isPAdES( byte[] data )
    {
        return hasPdfMagicNumber( data ) && hasPAdESRequirements( data );
    }
    
    boolean hasPdfMagicNumber( byte[] data )
    {
        byte[] slice = Arrays.copyOfRange( data, 0, pdfMagicNumber.length );
        
        return Arrays.equals( slice, pdfMagicNumber );
    }
    
    boolean hasPAdESRequirements( byte[] data )
    {
        try
        {
            PDFParser parser = new PDFParser( new RandomAccessBuffer( data ) );
            parser.parse();
            
            try ( PDDocument pdf = parser.getPDDocument() )
            {
                COSDocument document = pdf.getDocument();
                List<COSObject> dssObjects = document.getObjectsByType( "DSS" );
                List<COSObject> sigObjects = document.getObjectsByType( COSName.SIG );
                
                return !(dssObjects.isEmpty() || sigObjects.isEmpty());
            }
            
        }
        catch ( IOException e )
        {
            // TODO verbose logging
        }
        
        return false;
    }
}
