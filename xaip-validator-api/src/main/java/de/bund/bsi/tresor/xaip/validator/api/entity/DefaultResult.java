package de.bund.bsi.tresor.xaip.validator.api.entity;

import java.net.URI;

import com.sun.tools.javac.util.StringUtils;

import oasis.names.tc.dss._1_0.core.schema.InternationalStringType;
import oasis.names.tc.dss._1_0.core.schema.Result;

/**
 * Helperclass to create a {@link Result} based on the TR-ESOR specification.
 * 
 * @author wolffs
 *
 */
public class DefaultResult
{
    private static final String PREFIX = "http://www.bsi.bund.de/tr-esor/api/1.2";
    
    /**
     * Language of the result
     * 
     * @author wolffs
     */
    public static enum ResultLanguage
    {
        GERMAN( "de" ), ENGLISH( "en" );
        
        private final String langCode;
        
        /**
         * Defining a new result language with the provided value as langCode
         * 
         * @param langCode
         *            the langcode
         */
        private ResultLanguage( String langCode )
        {
            this.langCode = langCode;
        }
        
        /**
         * Returns the langCode
         * 
         * @return the language code
         */
        public String getValue()
        {
            return langCode;
        }
        
    }
    
    /**
     * Definition of the result Majors specified in TR-ESOR
     * 
     * @author pennerd
     */
    public static enum Major
    {
        OK( "/resultmajor#ok" ), ERROR( "/resultmajor#error" ), WARNING( "/resultmajor#warning" );
        
        private final String uri;
        
        /**
         * Defining a new major
         * 
         * @param path
         *            the major path
         */
        private Major( String path )
        {
            this.uri = PREFIX + path;
        }
        
        /**
         * Returns the complete major URI
         * 
         * @return the URI
         */
        public String getURI()
        {
            return this.uri;
        }
    }
    
    /**
     * Definition of the result Minors specified in TR-ESOR
     * 
     * @author pennerd
     */
    public static enum Minor
    {
        NO_PERMISSION( "/resultminor/al/common#noPermission", null ),
        
        INTERNAL_ERROR( "/resultminor/al/common#internalError", null ),
        
        PARAMETER_ERROR( "/resultminor/al/common#parameterError", null ),
        
        EXISTING_AOID( "/resultminor/arl/existingAOID", "Die im Rahmen des ArchiveSubmissionRequest übergebene AOID existiert bereits." ),
        
        EXISTING_PACKAGE_INFO_WARNING( "/resultminor/arl/existingPackageInfoWarning",
                "Bei der ArchiveUpdateRequest-Funktion wird ein DXAIP-Element übergeben, das ein packageInfo-Element enthält." ),
        
        LOW_SPACE_WARNING( "/resultminor/arl/lowSpaceWarning",
                "Diese Warnung gibt an, dass der verfügbare Speicherplatz einen kritischen Wert unterschritten hat." ),
        
        MISSING_REASON_OF_DELETION( "/resultminor/arl/missingReasonOfDeletion",
                "Da beim ArchiveDeletionRequest kein ReasonOfDeletion-Element übergeben wurde, muss der Löschvorgang abgewiesen werden." ),
        
        NO_SPACE_ERROR( "/resultminor/arl/noSpaceError",
                "Diese Fehlermeldung gibt an, dass kein Speicherplatz verfügbar war und deshalb das Archivdatenobjekt nicht abgelegt werden konnte." ),
        
        NOT_SUPPORTED(
                "/resultminor/arl/notSupported",
                "Diese Fehlermeldung gibt an, dass eine angeforderte Funktion, ein angefordertes Format oder ein übergebener optionaler Eingabeparameter nicht unterstützt wird." ),
        
        REQUEST_ONLY_PARTLY_SUCCESSFUL_WARNING( "/resultminor/arl/requestOnlyPartlySuccessfulWarning",
                "Diese Warnung gibt an, dass nicht alle angeforderten Daten zurückgeliefert werden konnten." ),
        
        UNKNOWN_ARCHIVE_DATA_TYPE( "/resultminor/arl/unknownArchiveDataType",
                "Es wird ein binäres Datenobjekt mit einem nicht unterstützten Datenformat übergeben." ),
        
        UNKNOWN_LOCATION( "/resultminor/arl/unknownLocation",
                "Die im ArchiveDataRequest angegebene DataLocation ist nicht vorhanden bzw. fehlerhaft." ),
        
        UNKNOWN_AOID( "/resultminor/arl/unknownAOID", "Die übergebene AOID existiert nicht." ),
        
        UNKNOWN_VERSION_ID( "/resultminor/arl/unknownVersionID", "Die übergebene VersionID ist im entsprechenden XAIP nicht bekannt." ),
        
        INVALID_SIGNATURE( "/resultminor/sal#invalidSignature", "Die Signatur ist ungültig." ),
        
        NO_TIMESTAMP_GENERATED_WARNING( "/resultminor/sal#noTimestampGenerated",
                "Für dieses Dokument wurde bisher kein Zeitstempel erstellt." ),
        
        XAIP_NOK_SIG( "/resultminor/arl/XAIP_NOK_SIG", "Prüfung der Signatur nach Ketten- oder Schalenmodell ist fehlgeschlagen." );
        
        private final String uri;
        
        private final String message;
        
        /**
         * Definition of a new minor
         * 
         * @param path
         *            the minor path
         * @param message
         *            the associated message
         */
        private Minor( String path, String message )
        {
            this.uri = PREFIX + path;
            this.message = message;
        }
        
        /**
         * Returns the complete minor result URI
         * 
         * @return the URI
         */
        public String getURI()
        {
            return this.uri;
        }
    }
    
    /**
     * Adding {@link Major#OK} to the result
     * 
     * @return the builder
     */
    public static Builder ok()
    {
        return new Builder( Major.OK );
    }
    
    /**
     * Adding {@link Major#WARNING} to the result
     * 
     * @return the builder
     */
    public static Builder warning()
    {
        return new Builder( Major.WARNING );
    }
    
    /**
     * Adding {@link Major#ERROR} to the result
     * 
     * @return the builder
     */
    public static Builder error()
    {
        return new Builder( Major.ERROR );
    }
    
    /**
     * Adding the major to the result
     * 
     * @param major
     *            the result major
     * @return the builder
     */
    public static Builder major( Major major )
    {
        return new Builder( major );
    }
    
    /**
     * Extracting the major from the result. Returns <code>null</code> when the result URI does not match any known major results
     * 
     * @param result
     *            the result
     * @return the extracted major or null if unknown
     */
    public static Major getMajor( Result result )
    {
        for ( Major item : Major.values() )
        {
            if ( item.getURI().equals( result.getResultMajor() ) )
            {
                return item;
            }
        }
        
        return null;
    }
    
    /**
     * Extracting the minor from the result. Returns <code>null</code> when the result URI does not match any known minor results
     * 
     * @param result
     *            the result
     * @return the extracted minor or null if unknown
     */
    public static Minor getMinor( Result result )
    {
        for ( Minor item : Minor.values() )
        {
            if ( item.getURI().equals( result.getResultMinor() ) )
            {
                return item;
            }
        }
        
        return null;
    }
    
    /**
     * Builder for TR-ESOR results
     * 
     * @author pennerd
     */
    public static class Builder
    {
        private Result result;
        
        /**
         * Creating a new builder using the provided major
         * 
         * @param major
         *            the major
         */
        public Builder( Major major )
        {
            this.result = new Result();
            this.result.setResultMajor( major.getURI() );
        }
        
        /**
         * Adding the provided major to the result
         * 
         * @param major
         *            the major
         * @return the builder
         */
        public Builder major( Major major )
        {
            this.result.setResultMajor( major.getURI() );
            return this;
        }
        
        /**
         * Adding the provided uri as major to the result
         * 
         * @param uri
         *            the URI
         * @return the builder
         */
        public Builder major( URI uri )
        {
            this.result.setResultMajor( uri.toString() );
            return this;
        }
        
        /**
         * Adding the provided minor to the result
         * 
         * @param minor
         *            the minor
         * @return the builder
         */
        public Builder minor( Minor minor )
        {
            this.result.setResultMinor( minor.getURI() );
            if ( minor.message != null )
            {
                this.message( minor.message, "de" );
            }
            return this;
        }
        
        /**
         * Adding the provided uri as minor to the result
         * 
         * @param uri
         *            the URI
         * @return the builder
         */
        public Builder minor( URI uri )
        {
            this.result.setResultMinor( uri.toString() );
            return this;
        }
        
        /**
         * Adding the message in the provided resultLanguage
         * 
         * @param message
         *            the message
         * @param language
         *            the language
         * @return the builder
         */
        public Builder message( String message, ResultLanguage language )
        {
            return this.message( message, language.getValue() );
        }
        
        /**
         * Adding the message in the provided language
         * 
         * @param message
         *            the message
         * @param langCode
         *            the language code
         * @return the builder
         */
        public Builder message( String message, String langCode )
        {
            InternationalStringType intString = new InternationalStringType();
            
            intString.setLang( langCode );
            intString.setValue( message );
            
            this.result.setResultMessage( intString );
            return this;
        }
        
        /**
         * Building the result
         * 
         * @return the result
         */
        public Result build()
        {
            return result;
        }
    }
    
    /**
     * Merging the results which might result in a new major/minor
     * 
     * @param first
     *            the first result
     * @param other
     *            the other results
     * @return the final result
     */
    public static Result merge( Result first, Result... other )
    {
        if ( other == null )
        {
            return first;
        }
        
        Result result = first;
        for ( Result o : other )
        {
            result = merge( result, o );
        }
        
        return result;
    }
    
    /**
     * Merging two different results adjusting the major/minor
     * 
     * @param first
     *            the first result
     * @param second
     *            the second result
     * @return the final result
     */
    public static Result merge( Result first, Result second )
    {
        if ( first == null )
            return second;
        
        if ( second == null )
            return first;
        
        Major majorFirst = DefaultResult.getMajor( first );
        Major majorSecond = DefaultResult.getMajor( second );
        
        if ( majorFirst == Major.ERROR )
            return first;
        if ( majorSecond == Major.ERROR )
            return second;
        
        if ( majorFirst == Major.WARNING || majorSecond == Major.WARNING )
        {
            Result result = null;
            if ( majorFirst == Major.WARNING )
                result = first;
            else if ( majorSecond == Major.WARNING )
                result = second;
            
            if ( StringUtils.isBlank( result.getResultMinor() ) )
            {
                result.setResultMinor( Minor.XAIP_NOK_SIG.getURI() );
            }
            
            return result;
        }
        
        return first;
    }
}
