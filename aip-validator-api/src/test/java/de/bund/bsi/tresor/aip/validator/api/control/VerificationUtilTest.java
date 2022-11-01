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
package de.bund.bsi.tresor.aip.validator.api.control;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.io.IOException;
import java.io.InputStream;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;

import org.bouncycastle.util.encoders.Hex;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.bund.bsi.tr_esor.xaip.BinaryDataType;
import de.bund.bsi.tr_esor.xaip.CheckSumType;
import de.bund.bsi.tr_esor.xaip.DataObjectType;
import de.bund.bsi.tresor.aip.validator.api.entity.DefaultResult.Major;
import de.bund.bsi.tresor.aip.validator.api.entity.DefaultResult.Minor;
import de.bund.bsi.tresor.aip.validator.api.entity.aip.DigestAlgorithm;
import oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_.VerificationResultType;

/**
 * @author tom-kuca
 */
public class VerificationUtilTest
{
    
    @Test
    @DisplayName( "should verify correct checksum" )
    public void verifyChecksumSucessTest() throws IOException
    {
        DataSource dataSource = new FileDataSource( getClass().getResource( "/data.txt" ).getFile() );
        DataHandler dataHandler = new DataHandler( dataSource );
        
        BinaryDataType binaryData = new BinaryDataType();
        binaryData.setValue( dataHandler );
        
        DataObjectType dataObject = new DataObjectType();
        dataObject.setBinaryData( binaryData );
        
        CheckSumType checkSumType = new CheckSumType();
        checkSumType.setCheckSumAlgorithm( DigestAlgorithm.SHA256.getXmlUri() );
        
        byte[] checkSum = Hex.decode( "315f5bdb76d078c43b8ac0064e4a0164612b1fce77c869345bfc94c75894edd3".getBytes() );
        checkSumType.setCheckSum( checkSum );
        
        try ( InputStream is = getClass().getResourceAsStream( "/data.txt" ) )
        {
            VerificationResultType result = VerificationUtil.verifyChecksum( is, checkSumType, true );
            assertThat( result.getResultMajor(), is( equalTo( Major.VALID.getUri() ) ) );
        }
    }
    
    @Test
    @DisplayName( "should reject invalid checksum" )
    public void verifyChecksumFailureTest() throws IOException
    {
        DataSource dataSource = new FileDataSource( getClass().getResource( "/data.txt" ).getFile() );
        DataHandler dataHandler = new DataHandler( dataSource );
        
        BinaryDataType binaryData = new BinaryDataType();
        binaryData.setValue( dataHandler );
        
        DataObjectType dataObject = new DataObjectType();
        dataObject.setBinaryData( binaryData );
        
        CheckSumType checkSumType = new CheckSumType();
        checkSumType.setCheckSumAlgorithm( DigestAlgorithm.SHA256.getXmlUri() );
        
        byte[] checkSum = Hex.decode( "0000000000000000000000000000000000000000000000000000000000000000".getBytes() );
        checkSumType.setCheckSum( checkSum );
        
        try ( InputStream is = getClass().getResourceAsStream( "/data.txt" ) )
        {
            VerificationResultType result = VerificationUtil.verifyChecksum( is, checkSumType, true );
            assertThat( result.getResultMajor(), is( equalTo( Major.INVALID.getUri() ) ) );
            assertThat( result.getResultMinor(), is( equalTo( Minor.CHECKSUM_INVALID.getUri() ) ) );
        }
    }
}
