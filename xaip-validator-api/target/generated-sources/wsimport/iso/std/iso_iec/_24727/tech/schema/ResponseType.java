
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import de.bund.bsi.ecard.api._1.GetCertificateResponse;
import de.bund.bsi.ecard.api._1.ShowViewerResponse;
import oasis.names.tc.dss._1_0.core.schema.ResponseBaseType;


/**
 * <p>Java-Klasse für ResponseType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="ResponseType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{urn:oasis:names:tc:dss:1.0:core:schema}ResponseBaseType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{urn:oasis:names:tc:dss:1.0:core:schema}Result"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResponseType")
@XmlSeeAlso({
    StartPAOSResponse.class,
    TCAPICloseResponse.class,
    TCAPIOpenResponse.class,
    ACLModifyResponse.class,
    ACLListResponse.class,
    DIDAuthenticateResponse.class,
    DIDDeleteResponse.class,
    DIDUpdateResponse.class,
    DIDGetResponse.class,
    DIDCreateResponse.class,
    DIDListResponse.class,
    VerifyCertificateResponse.class,
    VerifySignatureResponse.class,
    SignResponse.class,
    HashResponse.class,
    GetRandomResponse.class,
    DecipherResponse.class,
    EncipherResponse.class,
    DSIReadResponse.class,
    DSIWriteResponse.class,
    DSIDeleteResponse.class,
    DSICreateResponse.class,
    DSIListResponse.class,
    DataSetDeleteResponse.class,
    DataSetSelectResponse.class,
    DataSetCreateResponse.class,
    DataSetListResponse.class,
    ExecuteActionResponse.class,
    CardApplicationServiceDescribeResponse.class,
    CardApplicationServiceDeleteResponse.class,
    CardApplicationServiceLoadResponse.class,
    CardApplicationServiceCreateResponse.class,
    CardApplicationServiceListResponse.class,
    CardApplicationDeleteResponse.class,
    CardApplicationCreateResponse.class,
    CardApplicationListResponse.class,
    CardApplicationEndSessionResponse.class,
    CardApplicationStartSessionResponse.class,
    CardApplicationDisconnectResponse.class,
    CardApplicationConnectResponse.class,
    CardApplicationPathResponse.class,
    TerminateResponse.class,
    InitializeResponse.class,
    OutputResponse.class,
    ModifyVerificationDataResponse.class,
    VerifyUserResponse.class,
    TransmitResponse.class,
    EndTransactionResponse.class,
    BeginTransactionResponse.class,
    DisconnectResponse.class,
    ConnectResponse.class,
    ControlIFDResponse.class,
    CancelResponse.class,
    WaitResponse.class,
    GetStatusResponse.class,
    GetIFDCapabilitiesResponse.class,
    ListIFDsResponse.class,
    ReleaseContextResponse.class,
    EstablishContextResponse.class,
    ShowViewerResponse.class,
    GetCertificateResponse.class
})
public class ResponseType
    extends ResponseBaseType
{


}
