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
package de.bund.bsi.tresor.xaip.validator.signature.token;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;

import org.apache.cxf.Bus;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.feature.AbstractFeature;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.jaxb.JAXBDataBinding;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

/**
 * Header feature which transports the identityToken to other components
 * 
 * @author wolffs
 */
public class IdentityTokenHeaderFeature extends AbstractFeature
{
    public static final QName ID_TOKEN_HEADER = new QName( "urn:procilon:soap", "IdentityToken" );
    
    private String            tokenBase64;
    
    /**
     * Creating a feature which provides an additional header for the identityToken
     * 
     * @param tokenBase64
     *            the base64 encoded idToken
     */
    public IdentityTokenHeaderFeature( String tokenBase64 )
    {
        this.tokenBase64 = tokenBase64;
    }
    
    @Override
    public void initialize( Client client, Bus bus )
    {
        client.getOutInterceptors().add( new AbstractPhaseInterceptor<SoapMessage>( Phase.SETUP )
        {
            @Override
            public void handleMessage( SoapMessage message ) throws Fault
            {
                JAXBElement<String> headerValue = new JAXBElement<>( ID_TOKEN_HEADER, String.class, tokenBase64 );
                
                Header header;
                try
                {
                    header = new Header( ID_TOKEN_HEADER, headerValue, new JAXBDataBinding( String.class ) );
                    message.getHeaders().add( header );
                }
                catch ( JAXBException e )
                {
                    throw new IllegalStateException( e );
                }
            }
        } );
    }
}
