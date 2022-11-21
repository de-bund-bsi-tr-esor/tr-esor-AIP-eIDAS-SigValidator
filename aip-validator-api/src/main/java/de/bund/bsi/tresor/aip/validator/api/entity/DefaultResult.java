/*-
 * Copyright (c) 2020
 * Federal Office for Information Security (BSI),
 * Godesberger Allee 185-189,
 * 53175 Bonn, Germany,
 * phone: +49 228 99 9582-0,
 * fax: +49 228 99 9582-5400,
 * e-mail: bsi@bsi.bund.de
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.bund.bsi.tresor.aip.validator.api.entity;

import java.net.URI;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.Optional;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;
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
    private static final String         NS_TRESOR_API_1_1   = "http://www.bsi.bund.de/tr-esor/api/1.1";
    private static final String         NS_TRESOR_API_1_3   = "http://www.bsi.bund.de/tr-esor/api/1.3";
    
    private static final String         NS_ECARD_API_1_1    = "http://www.bsi.bund.de/ecard/api/1.1";
    private static final String         NS_ECARD_TRESOR_1_2 = "http://www.bsi.bund.de/ecard/tr-esor/1.2";
    
    private static final String         NS_OASIS_DSS_1_0    = "urn:oasis:names:tc:dss:1.0";
    
    private static final EnumSet<Major> errorMajor          = EnumSet.of( Major.INVALID, Major.ERROR, Major.REQUESTER_ERROR,
            Major.RESPONDER_ERROR );
    
    private static final EnumSet<Major> errorMinor          = EnumSet.of( Major.INDETERMINED, Major.INSUFFICIENT_INFORMATION,
            Major.WARNING );
    
    /**
     * Language of the result
     * 
     * @author wolffs
     */
    @Getter
    @AllArgsConstructor
    public static enum ResultLanguage
    {
        GERMAN( "de" ), ENGLISH( "en" );
        
        private final String value;
    }
    
    /**
     * Definition of the result Majors specified in TR-ESOR
     * 
     * @author wolffs
     */
    @Getter
    public static enum Major
    {
        OK( NS_ECARD_API_1_1, "/resultmajor#ok" ),
        
        ERROR( NS_ECARD_API_1_1, "/resultmajor#error" ),
        
        WARNING( NS_ECARD_API_1_1, "/resultmajor#warning" ),
        
        VALID( NS_OASIS_DSS_1_0, ":detail:valid" ),
        
        INVALID( NS_OASIS_DSS_1_0, ":detail:invalid" ),
        
        INDETERMINED( NS_OASIS_DSS_1_0, ":detail:indetermined" ),
        
        SUCCESS( NS_OASIS_DSS_1_0, ":resultmajor:Success" ),
        
        REQUESTER_ERROR( NS_OASIS_DSS_1_0, ":resultmajor:RequesterError" ),
        
        RESPONDER_ERROR( NS_OASIS_DSS_1_0, ":resultmajor:ResponderError" ),
        
        INSUFFICIENT_INFORMATION( NS_OASIS_DSS_1_0, ":resultmajor:InsufficientInformation" );
        
        private final String uri;
        
        /**
         * Defining a new major
         * 
         * @param path
         *            the major path
         */
        private Major( String namespace, String path )
        {
            this.uri = namespace + path;
        }
        
        /**
         * Parsing a major from an uri string
         * 
         * @param uri
         *            the major uri
         * @return the major if present
         */
        public static Optional<Major> fromUri( String uri )
        {
            return Stream.of( Major.values() )
                    .filter( maj -> maj.getUri().equalsIgnoreCase( uri ) )
                    .findAny();
        }
        
        /**
         * Checking if the result major is positiv
         * 
         * @return is positiv
         */
        public boolean isPositive()
        {
            return this == OK || this == VALID || this == SUCCESS;
        }
        
        /**
         * Identifying the major of the provided string
         * 
         * @param str
         *            the major uri
         * @return the optional major
         */
        public static Optional<Major> fromString( String str )
        {
            return Arrays.stream( Major.values() )
                    .filter( m -> m.getUri().equalsIgnoreCase( str ) )
                    .findAny();
        }
    }
    
    /**
     * Definition of the result Minors specified in TR-ESOR
     * 
     * @author wolffs
     */
    @Getter
    public static enum Minor
    {
        // using camel case variation instead
        // http://www.bsi.bund.de/tr-esor/api/1.2/resultminor/checksumInvalid
        // http://www.bsi.bund.de/tr-esor/api/1.2/resultminor/checksumAlgorithmNotSupportedWarning
        // http://www.bsi.bund.de/ecard/api/1.1/resultminor//il/algorithm#signatureAlgorithmNotSupported
        // http://www.bsi.bund.de/ecard/api/1.1/resultminor//il/algorithm#signatureAlgorithmNotSuitable
        
        NO_PERMISSION( NS_ECARD_API_1_1, "/resultminor/al/common#noPermission", null ),
        
        INTERNAL_ERROR( NS_ECARD_API_1_1, "/resultminor/al/common#internalError", null ),
        
        PARAMETER_ERROR( NS_ECARD_API_1_1, "/resultminor/al/common#parameterError", null ),
        
        NO_DATA_ACCESS_WARNING( NS_ECARD_API_1_1, "/resultminor/arl/noDataAccessWarning", null ),
        
        SIG_FORMAT_NOT_SUPPORTED( NS_ECARD_API_1_1, "/resultminor/il/signature#signatureFormatNotSupported", null ),
        
        SIG_ALG_NOT_SUITABLE( NS_ECARD_API_1_1, "/resultminor/il/algorithm#signatureAlgorithmNotSuitable", null ),
        
        SIG_ALG_NOT_SUPPORTED( NS_ECARD_API_1_1, "/resultminor/il/algorithm#signatureAlgorithmNotSupported", null ),
        
        HASH_ALG_NOT_SUITABLE( NS_ECARD_API_1_1, "/resultminor/il/algorithm#hashAlgorithmNotSuitable", null ),
        
        HASH_ALG_NOT_SUPPOERTED( NS_ECARD_API_1_1, "/resultminor/il/algorithm#hashAlgorithmNotSupported", null ),
        
        NOT_SUPPORTED( NS_ECARD_TRESOR_1_2, "/resultminor/arl/notSupported", null ),
        
        UNKNOWN_ATTRIBUTE( NS_TRESOR_API_1_1, "/resultminor/unknownAttribute", null ),
        
        NOT_SUPPORTED_( NS_TRESOR_API_1_3, "/resultminor/arl/notSupported", null ),
        
        INVALID_FORMAT( NS_TRESOR_API_1_3, "/resultminor/invalidFormat", null ),
        
        HASH_VALUE_MISMATCH( NS_TRESOR_API_1_3, "/resultminor/hashValueMismatch", null ),
        
        UNKNOWN_C14N_METHOD( NS_TRESOR_API_1_3, "/resultminor/unknownCanonicalizationMethod", null ),
        
        UNCHECKED_FORMAT_WARN( NS_TRESOR_API_1_3, "/resultminor/uncheckedFormatWarning", null ),
        
        PRESERVATION_PERIOD_EXPIRED( NS_TRESOR_API_1_3, "/resultminor/preservationPeriodExpired", null ),
        
        SUBMISSION_TIME_DEVIATION( NS_TRESOR_API_1_3, "/resultminor/submissionTimeDeviationBeyondLimit", null ),
        
        AMBIGUOUS_OBJECT_POINTER( NS_TRESOR_API_1_3, "/resultminor/ambiguousObjectPointerStatus", null ),
        
        CHECKSUM_INVALID( NS_TRESOR_API_1_3, "/resultminor/checkSumInvalid", null ),
        
        CHECKSUM_ALG_NOT_SUPPORTED( NS_TRESOR_API_1_3, "/resultminor/checkSumAlgorithmNotSupportedWarning", null );
        
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
        private Minor( String namespace, String path, String message )
        {
            this.uri = namespace + path;
            this.message = message;
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
     * Adding {@link Major#VALID} to the result
     * 
     * @return the builder
     */
    public static Builder valid()
    {
        return new Builder( Major.VALID );
    }
    
    /**
     * Adding {@link Major#INVALID} to the result
     * 
     * @return the builder
     */
    public static Builder invalid()
    {
        return new Builder( Major.INVALID );
    }
    
    /**
     * Adding {@link Major#INDETERMINED} to the result
     * 
     * @return the builder
     */
    public static Builder indetermined()
    {
        return new Builder( Major.INDETERMINED );
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
            if ( item.getUri().equals( result.getResultMajor() ) )
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
            if ( item.getUri().equals( result.getResultMinor() ) )
            {
                return item;
            }
        }
        
        return null;
    }
    
    /**
     * Builder for TR-ESOR results
     * 
     * @author wolffs
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
            this.result.setResultMajor( major.getUri() );
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
            this.result.setResultMajor( major.getUri() );
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
            this.result.setResultMinor( minor.getUri() );
            if ( minor.message != null )
            {
                this.message( minor.message, "en" );
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
        
        Major merged = merge( majorFirst, majorSecond );
        
        Result result = merged.equals( majorFirst ) ? first : second;
        if ( !merged.isPositive() && StringUtils.isBlank( result.getResultMinor() ) )
        {
            result.setResultMinor( Minor.INTERNAL_ERROR.getUri() );
        }
        
        return result;
    }
    
    /**
     * Merging majors, returning the most negative one
     * 
     * @param majorFirst
     *            first major
     * @param majorSecond
     *            second major
     * @return merged major
     */
    public static Major merge( Major majorFirst, Major majorSecond )
    {
        if ( majorFirst == null )
            return majorSecond;
        
        if ( majorSecond == null )
            return majorFirst;
        
        if ( errorMajor.contains( majorFirst ) )
            return majorFirst;
        if ( errorMajor.contains( majorSecond ) )
            return majorSecond;
        
        if ( errorMinor.contains( majorFirst )
                || errorMinor.contains( majorSecond ) )
        {
            if ( errorMinor.contains( majorFirst ) )
                return majorFirst;
            else if ( errorMinor.contains( majorSecond ) )
                return majorSecond;
        }
        
        return majorFirst;
    }
}
