package de.bund.bsi.tresor.aip.validator.syntax.validators;

import de.bund.bsi.tresor.aip.validator.syntax.context.DefaultSyntaxValidatorContext;
import eu.europa.esig.dss.asic.cades.validation.ASiCContainerWithCAdESValidator;
import eu.europa.esig.dss.asic.common.ASiCUtils;
import eu.europa.esig.dss.asic.common.validation.AbstractASiCContainerValidator;
import eu.europa.esig.dss.asic.xades.validation.ASiCContainerWithXAdESValidator;
import eu.europa.esig.dss.model.DSSDocument;
import eu.europa.esig.dss.asic.cades.validation.ASiCEWithCAdESManifestParser;
import eu.europa.esig.dss.model.InMemoryDocument;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class EvidenceRecordFinder
{
    public static void findEvidenceRecord( DefaultSyntaxValidatorContext syntaxContext, File asicDir )
    {
        try
        {
            var dssDocument = new InMemoryDocument( new FileInputStream( asicDir ) );
            ASiCContainerWithCAdESValidator cAdESValidator = new ASiCContainerWithCAdESValidator( dssDocument );
            ASiCContainerWithXAdESValidator xAdESValidator = new ASiCContainerWithXAdESValidator( dssDocument );
            boolean cAdESValidatorIsSupported = cAdESValidator.isSupported( dssDocument );

            if( !cAdESValidatorIsSupported && !xAdESValidator.isSupported( dssDocument ) )
            {
                return;
            }
            AbstractASiCContainerValidator validator = cAdESValidatorIsSupported ? cAdESValidator : xAdESValidator;

            var unsupportedDocuments = validator.getUnsupportedDocuments();
            var allDocuments = validator.getAllDocuments();

            var ersManifest = unsupportedDocuments.stream()
                    .filter( dssDoc -> isEvidenceRecordArchiveManifest( dssDoc.getName() ) )
                    .findAny()
                    .orElse( null );

            if ( ersManifest != null )
            {
                return;
            }

            var manifest = ASiCEWithCAdESManifestParser.getManifestFile( ersManifest );
            var ersFilename = manifest.getSignatureFilename();

            DSSDocument ersDoc = findDssDocument( unsupportedDocuments, ersFilename ).orElseThrow();

            Map<String, String> digestValues = new HashMap<>();
            var entries = manifest.getEntries();
            for ( var entry : entries )
            {
                var digest = entry.getDigest();
                var fileName = entry.getFileName();

                DSSDocument currentDoc = findDssDocument( allDocuments, fileName ).orElseThrow();
                String calculatedDigest = currentDoc.getDigest( digest.getAlgorithm() );
                digestValues.put( digest.getAlgorithm().getOid(), calculatedDigest );

                if ( !Arrays.equals( Base64.getDecoder().decode( calculatedDigest ), digest.getValue() ) )
                {
                    throw new IllegalStateException( "digest not match" );
                }

                syntaxContext.setAsicAIPContainer( asicDir );
            }
        }
        catch ( FileNotFoundException e )
        {
            throw new IllegalStateException( "Could not load asic at " + asicDir.getAbsolutePath(), e );
        }
    }

    /**
     * Checks if the fileName matches to an Archive Manifest name standard
     *
     * @param fileName
     *                     {@link String} to check
     * @return TRUE if the given name matches ASiC Archive Manifest filename, FALSE otherwise
     */
    public static boolean isEvidenceRecordArchiveManifest( String fileName )
    {
        return fileName.startsWith( ASiCUtils.META_INF_FOLDER ) && fileName.contains( "ASiCEvidenceRecordManifest" )
                && fileName.endsWith( ASiCUtils.XML_EXTENSION );
    }

    private static Optional<DSSDocument> findDssDocument( List<DSSDocument> documents, String filename )
    {
        return documents.stream()
                .filter( dssDoc -> Objects.equals( dssDoc.getName(), filename ) )
                .findAny();
    }
}
