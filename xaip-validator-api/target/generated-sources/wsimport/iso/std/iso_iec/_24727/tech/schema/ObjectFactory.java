
package iso.std.iso_iec._24727.tech.schema;

import java.math.BigInteger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the iso.std.iso_iec._24727.tech.schema package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _StateTransitionTypeDIDAuthenticationState_QNAME = new QName("urn:iso:std:iso-iec:24727:tech:schema", "DIDAuthenticationState");
    private final static QName _StateTransitionTypeRetryCounter_QNAME = new QName("urn:iso:std:iso-iec:24727:tech:schema", "RetryCounter");
    private final static QName _StateTransitionTypeUsageCounter_QNAME = new QName("urn:iso:std:iso-iec:24727:tech:schema", "UsageCounter");
    private final static QName _StateTransitionTypeFixedProcedure_QNAME = new QName("urn:iso:std:iso-iec:24727:tech:schema", "FixedProcedure");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: iso.std.iso_iec._24727.tech.schema
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ConnectionHandleType }
     * 
     */
    public ConnectionHandleType createConnectionHandleType() {
        return new ConnectionHandleType();
    }

    /**
     * Create an instance of {@link CardApplicationPathResponse }
     * 
     */
    public CardApplicationPathResponse createCardApplicationPathResponse() {
        return new CardApplicationPathResponse();
    }

    /**
     * Create an instance of {@link CardApplicationListResponse }
     * 
     */
    public CardApplicationListResponse createCardApplicationListResponse() {
        return new CardApplicationListResponse();
    }

    /**
     * Create an instance of {@link CardApplicationServiceListResponse }
     * 
     */
    public CardApplicationServiceListResponse createCardApplicationServiceListResponse() {
        return new CardApplicationServiceListResponse();
    }

    /**
     * Create an instance of {@link StartPAOS }
     * 
     */
    public StartPAOS createStartPAOS() {
        return new StartPAOS();
    }

    /**
     * Create an instance of {@link PathType }
     * 
     */
    public PathType createPathType() {
        return new PathType();
    }

    /**
     * Create an instance of {@link SecurityConditionType }
     * 
     */
    public SecurityConditionType createSecurityConditionType() {
        return new SecurityConditionType();
    }

    /**
     * Create an instance of {@link RequestType }
     * 
     */
    public RequestType createRequestType() {
        return new RequestType();
    }

    /**
     * Create an instance of {@link ResponseType }
     * 
     */
    public ResponseType createResponseType() {
        return new ResponseType();
    }

    /**
     * Create an instance of {@link CardApplicationPathType }
     * 
     */
    public CardApplicationPathType createCardApplicationPathType() {
        return new CardApplicationPathType();
    }

    /**
     * Create an instance of {@link ChannelHandleType }
     * 
     */
    public ChannelHandleType createChannelHandleType() {
        return new ChannelHandleType();
    }

    /**
     * Create an instance of {@link ConnectionHandleType.RecognitionInfo }
     * 
     */
    public ConnectionHandleType.RecognitionInfo createConnectionHandleTypeRecognitionInfo() {
        return new ConnectionHandleType.RecognitionInfo();
    }

    /**
     * Create an instance of {@link EstablishContext }
     * 
     */
    public EstablishContext createEstablishContext() {
        return new EstablishContext();
    }

    /**
     * Create an instance of {@link EstablishContextResponse }
     * 
     */
    public EstablishContextResponse createEstablishContextResponse() {
        return new EstablishContextResponse();
    }

    /**
     * Create an instance of {@link ReleaseContext }
     * 
     */
    public ReleaseContext createReleaseContext() {
        return new ReleaseContext();
    }

    /**
     * Create an instance of {@link ReleaseContextResponse }
     * 
     */
    public ReleaseContextResponse createReleaseContextResponse() {
        return new ReleaseContextResponse();
    }

    /**
     * Create an instance of {@link ListIFDs }
     * 
     */
    public ListIFDs createListIFDs() {
        return new ListIFDs();
    }

    /**
     * Create an instance of {@link ListIFDsResponse }
     * 
     */
    public ListIFDsResponse createListIFDsResponse() {
        return new ListIFDsResponse();
    }

    /**
     * Create an instance of {@link GetIFDCapabilities }
     * 
     */
    public GetIFDCapabilities createGetIFDCapabilities() {
        return new GetIFDCapabilities();
    }

    /**
     * Create an instance of {@link GetIFDCapabilitiesResponse }
     * 
     */
    public GetIFDCapabilitiesResponse createGetIFDCapabilitiesResponse() {
        return new GetIFDCapabilitiesResponse();
    }

    /**
     * Create an instance of {@link IFDCapabilitiesType }
     * 
     */
    public IFDCapabilitiesType createIFDCapabilitiesType() {
        return new IFDCapabilitiesType();
    }

    /**
     * Create an instance of {@link GetStatus }
     * 
     */
    public GetStatus createGetStatus() {
        return new GetStatus();
    }

    /**
     * Create an instance of {@link GetStatusResponse }
     * 
     */
    public GetStatusResponse createGetStatusResponse() {
        return new GetStatusResponse();
    }

    /**
     * Create an instance of {@link IFDStatusType }
     * 
     */
    public IFDStatusType createIFDStatusType() {
        return new IFDStatusType();
    }

    /**
     * Create an instance of {@link Wait }
     * 
     */
    public Wait createWait() {
        return new Wait();
    }

    /**
     * Create an instance of {@link WaitResponse }
     * 
     */
    public WaitResponse createWaitResponse() {
        return new WaitResponse();
    }

    /**
     * Create an instance of {@link Cancel }
     * 
     */
    public Cancel createCancel() {
        return new Cancel();
    }

    /**
     * Create an instance of {@link CancelResponse }
     * 
     */
    public CancelResponse createCancelResponse() {
        return new CancelResponse();
    }

    /**
     * Create an instance of {@link ControlIFD }
     * 
     */
    public ControlIFD createControlIFD() {
        return new ControlIFD();
    }

    /**
     * Create an instance of {@link ControlIFDResponse }
     * 
     */
    public ControlIFDResponse createControlIFDResponse() {
        return new ControlIFDResponse();
    }

    /**
     * Create an instance of {@link Connect }
     * 
     */
    public Connect createConnect() {
        return new Connect();
    }

    /**
     * Create an instance of {@link ConnectResponse }
     * 
     */
    public ConnectResponse createConnectResponse() {
        return new ConnectResponse();
    }

    /**
     * Create an instance of {@link Disconnect }
     * 
     */
    public Disconnect createDisconnect() {
        return new Disconnect();
    }

    /**
     * Create an instance of {@link DisconnectResponse }
     * 
     */
    public DisconnectResponse createDisconnectResponse() {
        return new DisconnectResponse();
    }

    /**
     * Create an instance of {@link BeginTransaction }
     * 
     */
    public BeginTransaction createBeginTransaction() {
        return new BeginTransaction();
    }

    /**
     * Create an instance of {@link BeginTransactionResponse }
     * 
     */
    public BeginTransactionResponse createBeginTransactionResponse() {
        return new BeginTransactionResponse();
    }

    /**
     * Create an instance of {@link EndTransaction }
     * 
     */
    public EndTransaction createEndTransaction() {
        return new EndTransaction();
    }

    /**
     * Create an instance of {@link EndTransactionResponse }
     * 
     */
    public EndTransactionResponse createEndTransactionResponse() {
        return new EndTransactionResponse();
    }

    /**
     * Create an instance of {@link Transmit }
     * 
     */
    public Transmit createTransmit() {
        return new Transmit();
    }

    /**
     * Create an instance of {@link InputAPDUInfoType }
     * 
     */
    public InputAPDUInfoType createInputAPDUInfoType() {
        return new InputAPDUInfoType();
    }

    /**
     * Create an instance of {@link TransmitResponse }
     * 
     */
    public TransmitResponse createTransmitResponse() {
        return new TransmitResponse();
    }

    /**
     * Create an instance of {@link VerifyUser }
     * 
     */
    public VerifyUser createVerifyUser() {
        return new VerifyUser();
    }

    /**
     * Create an instance of {@link InputUnitType }
     * 
     */
    public InputUnitType createInputUnitType() {
        return new InputUnitType();
    }

    /**
     * Create an instance of {@link AltVUMessagesType }
     * 
     */
    public AltVUMessagesType createAltVUMessagesType() {
        return new AltVUMessagesType();
    }

    /**
     * Create an instance of {@link VerifyUserResponse }
     * 
     */
    public VerifyUserResponse createVerifyUserResponse() {
        return new VerifyUserResponse();
    }

    /**
     * Create an instance of {@link ModifyVerificationData }
     * 
     */
    public ModifyVerificationData createModifyVerificationData() {
        return new ModifyVerificationData();
    }

    /**
     * Create an instance of {@link AltMVDMessagesType }
     * 
     */
    public AltMVDMessagesType createAltMVDMessagesType() {
        return new AltMVDMessagesType();
    }

    /**
     * Create an instance of {@link ModifyVerificationDataResponse }
     * 
     */
    public ModifyVerificationDataResponse createModifyVerificationDataResponse() {
        return new ModifyVerificationDataResponse();
    }

    /**
     * Create an instance of {@link Output }
     * 
     */
    public Output createOutput() {
        return new Output();
    }

    /**
     * Create an instance of {@link OutputInfoType }
     * 
     */
    public OutputInfoType createOutputInfoType() {
        return new OutputInfoType();
    }

    /**
     * Create an instance of {@link OutputResponse }
     * 
     */
    public OutputResponse createOutputResponse() {
        return new OutputResponse();
    }

    /**
     * Create an instance of {@link Initialize }
     * 
     */
    public Initialize createInitialize() {
        return new Initialize();
    }

    /**
     * Create an instance of {@link InitializeResponse }
     * 
     */
    public InitializeResponse createInitializeResponse() {
        return new InitializeResponse();
    }

    /**
     * Create an instance of {@link Terminate }
     * 
     */
    public Terminate createTerminate() {
        return new Terminate();
    }

    /**
     * Create an instance of {@link TerminateResponse }
     * 
     */
    public TerminateResponse createTerminateResponse() {
        return new TerminateResponse();
    }

    /**
     * Create an instance of {@link CardApplicationPath }
     * 
     */
    public CardApplicationPath createCardApplicationPath() {
        return new CardApplicationPath();
    }

    /**
     * Create an instance of {@link CardApplicationPathResponse.CardAppPathResultSet }
     * 
     */
    public CardApplicationPathResponse.CardAppPathResultSet createCardApplicationPathResponseCardAppPathResultSet() {
        return new CardApplicationPathResponse.CardAppPathResultSet();
    }

    /**
     * Create an instance of {@link CardApplicationConnect }
     * 
     */
    public CardApplicationConnect createCardApplicationConnect() {
        return new CardApplicationConnect();
    }

    /**
     * Create an instance of {@link CardApplicationConnectResponse }
     * 
     */
    public CardApplicationConnectResponse createCardApplicationConnectResponse() {
        return new CardApplicationConnectResponse();
    }

    /**
     * Create an instance of {@link CardApplicationDisconnect }
     * 
     */
    public CardApplicationDisconnect createCardApplicationDisconnect() {
        return new CardApplicationDisconnect();
    }

    /**
     * Create an instance of {@link CardApplicationDisconnectResponse }
     * 
     */
    public CardApplicationDisconnectResponse createCardApplicationDisconnectResponse() {
        return new CardApplicationDisconnectResponse();
    }

    /**
     * Create an instance of {@link CardApplicationStartSession }
     * 
     */
    public CardApplicationStartSession createCardApplicationStartSession() {
        return new CardApplicationStartSession();
    }

    /**
     * Create an instance of {@link DIDAuthenticationDataType }
     * 
     */
    public DIDAuthenticationDataType createDIDAuthenticationDataType() {
        return new DIDAuthenticationDataType();
    }

    /**
     * Create an instance of {@link CardApplicationStartSessionResponse }
     * 
     */
    public CardApplicationStartSessionResponse createCardApplicationStartSessionResponse() {
        return new CardApplicationStartSessionResponse();
    }

    /**
     * Create an instance of {@link CardApplicationEndSession }
     * 
     */
    public CardApplicationEndSession createCardApplicationEndSession() {
        return new CardApplicationEndSession();
    }

    /**
     * Create an instance of {@link CardApplicationEndSessionResponse }
     * 
     */
    public CardApplicationEndSessionResponse createCardApplicationEndSessionResponse() {
        return new CardApplicationEndSessionResponse();
    }

    /**
     * Create an instance of {@link CardApplicationList }
     * 
     */
    public CardApplicationList createCardApplicationList() {
        return new CardApplicationList();
    }

    /**
     * Create an instance of {@link CardApplicationListResponse.CardApplicationNameList }
     * 
     */
    public CardApplicationListResponse.CardApplicationNameList createCardApplicationListResponseCardApplicationNameList() {
        return new CardApplicationListResponse.CardApplicationNameList();
    }

    /**
     * Create an instance of {@link CardApplicationCreate }
     * 
     */
    public CardApplicationCreate createCardApplicationCreate() {
        return new CardApplicationCreate();
    }

    /**
     * Create an instance of {@link AccessControlListType }
     * 
     */
    public AccessControlListType createAccessControlListType() {
        return new AccessControlListType();
    }

    /**
     * Create an instance of {@link CardApplicationCreateResponse }
     * 
     */
    public CardApplicationCreateResponse createCardApplicationCreateResponse() {
        return new CardApplicationCreateResponse();
    }

    /**
     * Create an instance of {@link CardApplicationDelete }
     * 
     */
    public CardApplicationDelete createCardApplicationDelete() {
        return new CardApplicationDelete();
    }

    /**
     * Create an instance of {@link CardApplicationDeleteResponse }
     * 
     */
    public CardApplicationDeleteResponse createCardApplicationDeleteResponse() {
        return new CardApplicationDeleteResponse();
    }

    /**
     * Create an instance of {@link CardApplicationServiceList }
     * 
     */
    public CardApplicationServiceList createCardApplicationServiceList() {
        return new CardApplicationServiceList();
    }

    /**
     * Create an instance of {@link CardApplicationServiceListResponse.CardApplicationServiceNameList }
     * 
     */
    public CardApplicationServiceListResponse.CardApplicationServiceNameList createCardApplicationServiceListResponseCardApplicationServiceNameList() {
        return new CardApplicationServiceListResponse.CardApplicationServiceNameList();
    }

    /**
     * Create an instance of {@link CardApplicationServiceCreate }
     * 
     */
    public CardApplicationServiceCreate createCardApplicationServiceCreate() {
        return new CardApplicationServiceCreate();
    }

    /**
     * Create an instance of {@link CardApplicationServiceCreateResponse }
     * 
     */
    public CardApplicationServiceCreateResponse createCardApplicationServiceCreateResponse() {
        return new CardApplicationServiceCreateResponse();
    }

    /**
     * Create an instance of {@link CardApplicationServiceLoad }
     * 
     */
    public CardApplicationServiceLoad createCardApplicationServiceLoad() {
        return new CardApplicationServiceLoad();
    }

    /**
     * Create an instance of {@link CardApplicationServiceLoadResponse }
     * 
     */
    public CardApplicationServiceLoadResponse createCardApplicationServiceLoadResponse() {
        return new CardApplicationServiceLoadResponse();
    }

    /**
     * Create an instance of {@link CardApplicationServiceDelete }
     * 
     */
    public CardApplicationServiceDelete createCardApplicationServiceDelete() {
        return new CardApplicationServiceDelete();
    }

    /**
     * Create an instance of {@link CardApplicationServiceDeleteResponse }
     * 
     */
    public CardApplicationServiceDeleteResponse createCardApplicationServiceDeleteResponse() {
        return new CardApplicationServiceDeleteResponse();
    }

    /**
     * Create an instance of {@link CardApplicationServiceDescribe }
     * 
     */
    public CardApplicationServiceDescribe createCardApplicationServiceDescribe() {
        return new CardApplicationServiceDescribe();
    }

    /**
     * Create an instance of {@link CardApplicationServiceDescribeResponse }
     * 
     */
    public CardApplicationServiceDescribeResponse createCardApplicationServiceDescribeResponse() {
        return new CardApplicationServiceDescribeResponse();
    }

    /**
     * Create an instance of {@link CardApplicationServiceDescriptionType }
     * 
     */
    public CardApplicationServiceDescriptionType createCardApplicationServiceDescriptionType() {
        return new CardApplicationServiceDescriptionType();
    }

    /**
     * Create an instance of {@link ExecuteAction }
     * 
     */
    public ExecuteAction createExecuteAction() {
        return new ExecuteAction();
    }

    /**
     * Create an instance of {@link ActionNameType }
     * 
     */
    public ActionNameType createActionNameType() {
        return new ActionNameType();
    }

    /**
     * Create an instance of {@link ExecuteActionResponse }
     * 
     */
    public ExecuteActionResponse createExecuteActionResponse() {
        return new ExecuteActionResponse();
    }

    /**
     * Create an instance of {@link DataSetList }
     * 
     */
    public DataSetList createDataSetList() {
        return new DataSetList();
    }

    /**
     * Create an instance of {@link DataSetListResponse }
     * 
     */
    public DataSetListResponse createDataSetListResponse() {
        return new DataSetListResponse();
    }

    /**
     * Create an instance of {@link DataSetNameListType }
     * 
     */
    public DataSetNameListType createDataSetNameListType() {
        return new DataSetNameListType();
    }

    /**
     * Create an instance of {@link DataSetCreate }
     * 
     */
    public DataSetCreate createDataSetCreate() {
        return new DataSetCreate();
    }

    /**
     * Create an instance of {@link DataSetCreateResponse }
     * 
     */
    public DataSetCreateResponse createDataSetCreateResponse() {
        return new DataSetCreateResponse();
    }

    /**
     * Create an instance of {@link DataSetSelect }
     * 
     */
    public DataSetSelect createDataSetSelect() {
        return new DataSetSelect();
    }

    /**
     * Create an instance of {@link DataSetSelectResponse }
     * 
     */
    public DataSetSelectResponse createDataSetSelectResponse() {
        return new DataSetSelectResponse();
    }

    /**
     * Create an instance of {@link DataSetDelete }
     * 
     */
    public DataSetDelete createDataSetDelete() {
        return new DataSetDelete();
    }

    /**
     * Create an instance of {@link DataSetDeleteResponse }
     * 
     */
    public DataSetDeleteResponse createDataSetDeleteResponse() {
        return new DataSetDeleteResponse();
    }

    /**
     * Create an instance of {@link DSIList }
     * 
     */
    public DSIList createDSIList() {
        return new DSIList();
    }

    /**
     * Create an instance of {@link DSIListResponse }
     * 
     */
    public DSIListResponse createDSIListResponse() {
        return new DSIListResponse();
    }

    /**
     * Create an instance of {@link DSINameListType }
     * 
     */
    public DSINameListType createDSINameListType() {
        return new DSINameListType();
    }

    /**
     * Create an instance of {@link DSICreate }
     * 
     */
    public DSICreate createDSICreate() {
        return new DSICreate();
    }

    /**
     * Create an instance of {@link DSICreateResponse }
     * 
     */
    public DSICreateResponse createDSICreateResponse() {
        return new DSICreateResponse();
    }

    /**
     * Create an instance of {@link DSIDelete }
     * 
     */
    public DSIDelete createDSIDelete() {
        return new DSIDelete();
    }

    /**
     * Create an instance of {@link DSIDeleteResponse }
     * 
     */
    public DSIDeleteResponse createDSIDeleteResponse() {
        return new DSIDeleteResponse();
    }

    /**
     * Create an instance of {@link DSIWrite }
     * 
     */
    public DSIWrite createDSIWrite() {
        return new DSIWrite();
    }

    /**
     * Create an instance of {@link DSIWriteResponse }
     * 
     */
    public DSIWriteResponse createDSIWriteResponse() {
        return new DSIWriteResponse();
    }

    /**
     * Create an instance of {@link DSIRead }
     * 
     */
    public DSIRead createDSIRead() {
        return new DSIRead();
    }

    /**
     * Create an instance of {@link DSIReadResponse }
     * 
     */
    public DSIReadResponse createDSIReadResponse() {
        return new DSIReadResponse();
    }

    /**
     * Create an instance of {@link Encipher }
     * 
     */
    public Encipher createEncipher() {
        return new Encipher();
    }

    /**
     * Create an instance of {@link EncipherResponse }
     * 
     */
    public EncipherResponse createEncipherResponse() {
        return new EncipherResponse();
    }

    /**
     * Create an instance of {@link Decipher }
     * 
     */
    public Decipher createDecipher() {
        return new Decipher();
    }

    /**
     * Create an instance of {@link DecipherResponse }
     * 
     */
    public DecipherResponse createDecipherResponse() {
        return new DecipherResponse();
    }

    /**
     * Create an instance of {@link GetRandom }
     * 
     */
    public GetRandom createGetRandom() {
        return new GetRandom();
    }

    /**
     * Create an instance of {@link GetRandomResponse }
     * 
     */
    public GetRandomResponse createGetRandomResponse() {
        return new GetRandomResponse();
    }

    /**
     * Create an instance of {@link Hash }
     * 
     */
    public Hash createHash() {
        return new Hash();
    }

    /**
     * Create an instance of {@link HashResponse }
     * 
     */
    public HashResponse createHashResponse() {
        return new HashResponse();
    }

    /**
     * Create an instance of {@link Sign }
     * 
     */
    public Sign createSign() {
        return new Sign();
    }

    /**
     * Create an instance of {@link SignResponse }
     * 
     */
    public SignResponse createSignResponse() {
        return new SignResponse();
    }

    /**
     * Create an instance of {@link VerifySignature }
     * 
     */
    public VerifySignature createVerifySignature() {
        return new VerifySignature();
    }

    /**
     * Create an instance of {@link VerifySignatureResponse }
     * 
     */
    public VerifySignatureResponse createVerifySignatureResponse() {
        return new VerifySignatureResponse();
    }

    /**
     * Create an instance of {@link VerifyCertificate }
     * 
     */
    public VerifyCertificate createVerifyCertificate() {
        return new VerifyCertificate();
    }

    /**
     * Create an instance of {@link VerifyCertificateResponse }
     * 
     */
    public VerifyCertificateResponse createVerifyCertificateResponse() {
        return new VerifyCertificateResponse();
    }

    /**
     * Create an instance of {@link DIDList }
     * 
     */
    public DIDList createDIDList() {
        return new DIDList();
    }

    /**
     * Create an instance of {@link DIDQualifierType }
     * 
     */
    public DIDQualifierType createDIDQualifierType() {
        return new DIDQualifierType();
    }

    /**
     * Create an instance of {@link DIDListResponse }
     * 
     */
    public DIDListResponse createDIDListResponse() {
        return new DIDListResponse();
    }

    /**
     * Create an instance of {@link DIDNameListType }
     * 
     */
    public DIDNameListType createDIDNameListType() {
        return new DIDNameListType();
    }

    /**
     * Create an instance of {@link DIDCreate }
     * 
     */
    public DIDCreate createDIDCreate() {
        return new DIDCreate();
    }

    /**
     * Create an instance of {@link DIDCreateResponse }
     * 
     */
    public DIDCreateResponse createDIDCreateResponse() {
        return new DIDCreateResponse();
    }

    /**
     * Create an instance of {@link DIDGet }
     * 
     */
    public DIDGet createDIDGet() {
        return new DIDGet();
    }

    /**
     * Create an instance of {@link DIDGetResponse }
     * 
     */
    public DIDGetResponse createDIDGetResponse() {
        return new DIDGetResponse();
    }

    /**
     * Create an instance of {@link DIDStructureType }
     * 
     */
    public DIDStructureType createDIDStructureType() {
        return new DIDStructureType();
    }

    /**
     * Create an instance of {@link DIDUpdate }
     * 
     */
    public DIDUpdate createDIDUpdate() {
        return new DIDUpdate();
    }

    /**
     * Create an instance of {@link DIDUpdateResponse }
     * 
     */
    public DIDUpdateResponse createDIDUpdateResponse() {
        return new DIDUpdateResponse();
    }

    /**
     * Create an instance of {@link DIDDelete }
     * 
     */
    public DIDDelete createDIDDelete() {
        return new DIDDelete();
    }

    /**
     * Create an instance of {@link DIDDeleteResponse }
     * 
     */
    public DIDDeleteResponse createDIDDeleteResponse() {
        return new DIDDeleteResponse();
    }

    /**
     * Create an instance of {@link DIDAuthenticate }
     * 
     */
    public DIDAuthenticate createDIDAuthenticate() {
        return new DIDAuthenticate();
    }

    /**
     * Create an instance of {@link DIDAuthenticateResponse }
     * 
     */
    public DIDAuthenticateResponse createDIDAuthenticateResponse() {
        return new DIDAuthenticateResponse();
    }

    /**
     * Create an instance of {@link ACLList }
     * 
     */
    public ACLList createACLList() {
        return new ACLList();
    }

    /**
     * Create an instance of {@link TargetNameType }
     * 
     */
    public TargetNameType createTargetNameType() {
        return new TargetNameType();
    }

    /**
     * Create an instance of {@link ACLListResponse }
     * 
     */
    public ACLListResponse createACLListResponse() {
        return new ACLListResponse();
    }

    /**
     * Create an instance of {@link ACLModify }
     * 
     */
    public ACLModify createACLModify() {
        return new ACLModify();
    }

    /**
     * Create an instance of {@link ACLModifyResponse }
     * 
     */
    public ACLModifyResponse createACLModifyResponse() {
        return new ACLModifyResponse();
    }

    /**
     * Create an instance of {@link TCAPIOpen }
     * 
     */
    public TCAPIOpen createTCAPIOpen() {
        return new TCAPIOpen();
    }

    /**
     * Create an instance of {@link TCAPIOpenResponse }
     * 
     */
    public TCAPIOpenResponse createTCAPIOpenResponse() {
        return new TCAPIOpenResponse();
    }

    /**
     * Create an instance of {@link TCAPIClose }
     * 
     */
    public TCAPIClose createTCAPIClose() {
        return new TCAPIClose();
    }

    /**
     * Create an instance of {@link TCAPICloseResponse }
     * 
     */
    public TCAPICloseResponse createTCAPICloseResponse() {
        return new TCAPICloseResponse();
    }

    /**
     * Create an instance of {@link StartPAOS.UserAgent }
     * 
     */
    public StartPAOS.UserAgent createStartPAOSUserAgent() {
        return new StartPAOS.UserAgent();
    }

    /**
     * Create an instance of {@link StartPAOS.SupportedAPIVersions }
     * 
     */
    public StartPAOS.SupportedAPIVersions createStartPAOSSupportedAPIVersions() {
        return new StartPAOS.SupportedAPIVersions();
    }

    /**
     * Create an instance of {@link StartPAOSResponse }
     * 
     */
    public StartPAOSResponse createStartPAOSResponse() {
        return new StartPAOSResponse();
    }

    /**
     * Create an instance of {@link CardCall }
     * 
     */
    public CardCall createCardCall() {
        return new CardCall();
    }

    /**
     * Create an instance of {@link CardCallType }
     * 
     */
    public CardCallType createCardCallType() {
        return new CardCallType();
    }

    /**
     * Create an instance of {@link ResponseAPDUType }
     * 
     */
    public ResponseAPDUType createResponseAPDUType() {
        return new ResponseAPDUType();
    }

    /**
     * Create an instance of {@link Conclusion }
     * 
     */
    public Conclusion createConclusion() {
        return new Conclusion();
    }

    /**
     * Create an instance of {@link ConclusionType }
     * 
     */
    public ConclusionType createConclusionType() {
        return new ConclusionType();
    }

    /**
     * Create an instance of {@link StateInfo }
     * 
     */
    public StateInfo createStateInfo() {
        return new StateInfo();
    }

    /**
     * Create an instance of {@link StateInfoType }
     * 
     */
    public StateInfoType createStateInfoType() {
        return new StateInfoType();
    }

    /**
     * Create an instance of {@link CardCallSequenceType }
     * 
     */
    public CardCallSequenceType createCardCallSequenceType() {
        return new CardCallSequenceType();
    }

    /**
     * Create an instance of {@link StateType }
     * 
     */
    public StateType createStateType() {
        return new StateType();
    }

    /**
     * Create an instance of {@link PathSecurityType }
     * 
     */
    public PathSecurityType createPathSecurityType() {
        return new PathSecurityType();
    }

    /**
     * Create an instance of {@link SlotCapabilityType }
     * 
     */
    public SlotCapabilityType createSlotCapabilityType() {
        return new SlotCapabilityType();
    }

    /**
     * Create an instance of {@link DisplayCapabilityType }
     * 
     */
    public DisplayCapabilityType createDisplayCapabilityType() {
        return new DisplayCapabilityType();
    }

    /**
     * Create an instance of {@link KeyPadCapabilityType }
     * 
     */
    public KeyPadCapabilityType createKeyPadCapabilityType() {
        return new KeyPadCapabilityType();
    }

    /**
     * Create an instance of {@link BioSensorCapabilityType }
     * 
     */
    public BioSensorCapabilityType createBioSensorCapabilityType() {
        return new BioSensorCapabilityType();
    }

    /**
     * Create an instance of {@link SlotStatusType }
     * 
     */
    public SlotStatusType createSlotStatusType() {
        return new SlotStatusType();
    }

    /**
     * Create an instance of {@link SimpleFUStatusType }
     * 
     */
    public SimpleFUStatusType createSimpleFUStatusType() {
        return new SimpleFUStatusType();
    }

    /**
     * Create an instance of {@link PinInputType }
     * 
     */
    public PinInputType createPinInputType() {
        return new PinInputType();
    }

    /**
     * Create an instance of {@link PasswordAttributesType }
     * 
     */
    public PasswordAttributesType createPasswordAttributesType() {
        return new PasswordAttributesType();
    }

    /**
     * Create an instance of {@link BiometricInputType }
     * 
     */
    public BiometricInputType createBiometricInputType() {
        return new BiometricInputType();
    }

    /**
     * Create an instance of {@link AccessRuleType }
     * 
     */
    public AccessRuleType createAccessRuleType() {
        return new AccessRuleType();
    }

    /**
     * Create an instance of {@link DIDAbstractMarkerType }
     * 
     */
    public DIDAbstractMarkerType createDIDAbstractMarkerType() {
        return new DIDAbstractMarkerType();
    }

    /**
     * Create an instance of {@link DIDAuthenticationStateType }
     * 
     */
    public DIDAuthenticationStateType createDIDAuthenticationStateType() {
        return new DIDAuthenticationStateType();
    }

    /**
     * Create an instance of {@link NULL }
     * 
     */
    public NULL createNULL() {
        return new NULL();
    }

    /**
     * Create an instance of {@link TLSPSKParametersType }
     * 
     */
    public TLSPSKParametersType createTLSPSKParametersType() {
        return new TLSPSKParametersType();
    }

    /**
     * Create an instance of {@link AlgorithmIdentifierType }
     * 
     */
    public AlgorithmIdentifierType createAlgorithmIdentifierType() {
        return new AlgorithmIdentifierType();
    }

    /**
     * Create an instance of {@link CertificateRefType }
     * 
     */
    public CertificateRefType createCertificateRefType() {
        return new CertificateRefType();
    }

    /**
     * Create an instance of {@link DataMaskType }
     * 
     */
    public DataMaskType createDataMaskType() {
        return new DataMaskType();
    }

    /**
     * Create an instance of {@link DataRefType }
     * 
     */
    public DataRefType createDataRefType() {
        return new DataRefType();
    }

    /**
     * Create an instance of {@link DIDMarkerType }
     * 
     */
    public DIDMarkerType createDIDMarkerType() {
        return new DIDMarkerType();
    }

    /**
     * Create an instance of {@link KeyValueType }
     * 
     */
    public KeyValueType createKeyValueType() {
        return new KeyValueType();
    }

    /**
     * Create an instance of {@link KeyRefType }
     * 
     */
    public KeyRefType createKeyRefType() {
        return new KeyRefType();
    }

    /**
     * Create an instance of {@link MatchingDataType }
     * 
     */
    public MatchingDataType createMatchingDataType() {
        return new MatchingDataType();
    }

    /**
     * Create an instance of {@link StartSessionInputType }
     * 
     */
    public StartSessionInputType createStartSessionInputType() {
        return new StartSessionInputType();
    }

    /**
     * Create an instance of {@link StartSessionOutputType }
     * 
     */
    public StartSessionOutputType createStartSessionOutputType() {
        return new StartSessionOutputType();
    }

    /**
     * Create an instance of {@link StateTransitionType }
     * 
     */
    public StateTransitionType createStateTransitionType() {
        return new StateTransitionType();
    }

    /**
     * Create an instance of {@link UpdateCounterType }
     * 
     */
    public UpdateCounterType createUpdateCounterType() {
        return new UpdateCounterType();
    }

    /**
     * Create an instance of {@link PinCompareMarkerType }
     * 
     */
    public PinCompareMarkerType createPinCompareMarkerType() {
        return new PinCompareMarkerType();
    }

    /**
     * Create an instance of {@link PinCompareDIDUpdateDataType }
     * 
     */
    public PinCompareDIDUpdateDataType createPinCompareDIDUpdateDataType() {
        return new PinCompareDIDUpdateDataType();
    }

    /**
     * Create an instance of {@link PinCompareDIDAuthenticateInputType }
     * 
     */
    public PinCompareDIDAuthenticateInputType createPinCompareDIDAuthenticateInputType() {
        return new PinCompareDIDAuthenticateInputType();
    }

    /**
     * Create an instance of {@link PinCompareDIDAuthenticateOutputType }
     * 
     */
    public PinCompareDIDAuthenticateOutputType createPinCompareDIDAuthenticateOutputType() {
        return new PinCompareDIDAuthenticateOutputType();
    }

    /**
     * Create an instance of {@link MutualAuthMarkerType }
     * 
     */
    public MutualAuthMarkerType createMutualAuthMarkerType() {
        return new MutualAuthMarkerType();
    }

    /**
     * Create an instance of {@link MutualAuthDIDUpdateDataType }
     * 
     */
    public MutualAuthDIDUpdateDataType createMutualAuthDIDUpdateDataType() {
        return new MutualAuthDIDUpdateDataType();
    }

    /**
     * Create an instance of {@link MutualAuthDIDAuthInternalAuthType }
     * 
     */
    public MutualAuthDIDAuthInternalAuthType createMutualAuthDIDAuthInternalAuthType() {
        return new MutualAuthDIDAuthInternalAuthType();
    }

    /**
     * Create an instance of {@link MutualAuthDIDAuthMutualAuthType }
     * 
     */
    public MutualAuthDIDAuthMutualAuthType createMutualAuthDIDAuthMutualAuthType() {
        return new MutualAuthDIDAuthMutualAuthType();
    }

    /**
     * Create an instance of {@link MutualAuthDIDAuthExternalAuthType }
     * 
     */
    public MutualAuthDIDAuthExternalAuthType createMutualAuthDIDAuthExternalAuthType() {
        return new MutualAuthDIDAuthExternalAuthType();
    }

    /**
     * Create an instance of {@link EACMarkerType }
     * 
     */
    public EACMarkerType createEACMarkerType() {
        return new EACMarkerType();
    }

    /**
     * Create an instance of {@link EACSessionInputType }
     * 
     */
    public EACSessionInputType createEACSessionInputType() {
        return new EACSessionInputType();
    }

    /**
     * Create an instance of {@link EACSessionOutputType }
     * 
     */
    public EACSessionOutputType createEACSessionOutputType() {
        return new EACSessionOutputType();
    }

    /**
     * Create an instance of {@link EAC1InputType }
     * 
     */
    public EAC1InputType createEAC1InputType() {
        return new EAC1InputType();
    }

    /**
     * Create an instance of {@link EAC1OutputType }
     * 
     */
    public EAC1OutputType createEAC1OutputType() {
        return new EAC1OutputType();
    }

    /**
     * Create an instance of {@link EAC2InputType }
     * 
     */
    public EAC2InputType createEAC2InputType() {
        return new EAC2InputType();
    }

    /**
     * Create an instance of {@link EAC2OutputType }
     * 
     */
    public EAC2OutputType createEAC2OutputType() {
        return new EAC2OutputType();
    }

    /**
     * Create an instance of {@link EACAdditionalInputType }
     * 
     */
    public EACAdditionalInputType createEACAdditionalInputType() {
        return new EACAdditionalInputType();
    }

    /**
     * Create an instance of {@link PACEMarkerType }
     * 
     */
    public PACEMarkerType createPACEMarkerType() {
        return new PACEMarkerType();
    }

    /**
     * Create an instance of {@link PACEDIDUpdateDataType }
     * 
     */
    public PACEDIDUpdateDataType createPACEDIDUpdateDataType() {
        return new PACEDIDUpdateDataType();
    }

    /**
     * Create an instance of {@link PACEDIDAuthenticateInputType }
     * 
     */
    public PACEDIDAuthenticateInputType createPACEDIDAuthenticateInputType() {
        return new PACEDIDAuthenticateInputType();
    }

    /**
     * Create an instance of {@link PACEDIDAuthenticateOutputType }
     * 
     */
    public PACEDIDAuthenticateOutputType createPACEDIDAuthenticateOutputType() {
        return new PACEDIDAuthenticateOutputType();
    }

    /**
     * Create an instance of {@link SubjectPublicKeyInfoType }
     * 
     */
    public SubjectPublicKeyInfoType createSubjectPublicKeyInfoType() {
        return new SubjectPublicKeyInfoType();
    }

    /**
     * Create an instance of {@link CAMarkerType }
     * 
     */
    public CAMarkerType createCAMarkerType() {
        return new CAMarkerType();
    }

    /**
     * Create an instance of {@link CADIDCreateDataType }
     * 
     */
    public CADIDCreateDataType createCADIDCreateDataType() {
        return new CADIDCreateDataType();
    }

    /**
     * Create an instance of {@link CAKeyInfoType }
     * 
     */
    public CAKeyInfoType createCAKeyInfoType() {
        return new CAKeyInfoType();
    }

    /**
     * Create an instance of {@link CADIDUpdateDataType }
     * 
     */
    public CADIDUpdateDataType createCADIDUpdateDataType() {
        return new CADIDUpdateDataType();
    }

    /**
     * Create an instance of {@link CAInputType }
     * 
     */
    public CAInputType createCAInputType() {
        return new CAInputType();
    }

    /**
     * Create an instance of {@link CAAuthenticationTokenType }
     * 
     */
    public CAAuthenticationTokenType createCAAuthenticationTokenType() {
        return new CAAuthenticationTokenType();
    }

    /**
     * Create an instance of {@link TAMarkerType }
     * 
     */
    public TAMarkerType createTAMarkerType() {
        return new TAMarkerType();
    }

    /**
     * Create an instance of {@link TADIDUpdateDataType }
     * 
     */
    public TADIDUpdateDataType createTADIDUpdateDataType() {
        return new TADIDUpdateDataType();
    }

    /**
     * Create an instance of {@link TADIDAuthInputType }
     * 
     */
    public TADIDAuthInputType createTADIDAuthInputType() {
        return new TADIDAuthInputType();
    }

    /**
     * Create an instance of {@link TADIDAuthOutputType }
     * 
     */
    public TADIDAuthOutputType createTADIDAuthOutputType() {
        return new TADIDAuthOutputType();
    }

    /**
     * Create an instance of {@link TADIDAuthExternalAuthType }
     * 
     */
    public TADIDAuthExternalAuthType createTADIDAuthExternalAuthType() {
        return new TADIDAuthExternalAuthType();
    }

    /**
     * Create an instance of {@link TAAuxInputType }
     * 
     */
    public TAAuxInputType createTAAuxInputType() {
        return new TAAuxInputType();
    }

    /**
     * Create an instance of {@link RIMarkerType }
     * 
     */
    public RIMarkerType createRIMarkerType() {
        return new RIMarkerType();
    }

    /**
     * Create an instance of {@link RIDIDUpdateDataType }
     * 
     */
    public RIDIDUpdateDataType createRIDIDUpdateDataType() {
        return new RIDIDUpdateDataType();
    }

    /**
     * Create an instance of {@link RIDIDAuthInputType }
     * 
     */
    public RIDIDAuthInputType createRIDIDAuthInputType() {
        return new RIDIDAuthInputType();
    }

    /**
     * Create an instance of {@link RIDIDAuthOutputType }
     * 
     */
    public RIDIDAuthOutputType createRIDIDAuthOutputType() {
        return new RIDIDAuthOutputType();
    }

    /**
     * Create an instance of {@link RSAAuthMarkerType }
     * 
     */
    public RSAAuthMarkerType createRSAAuthMarkerType() {
        return new RSAAuthMarkerType();
    }

    /**
     * Create an instance of {@link RSAAuthDIDUpdateDataType }
     * 
     */
    public RSAAuthDIDUpdateDataType createRSAAuthDIDUpdateDataType() {
        return new RSAAuthDIDUpdateDataType();
    }

    /**
     * Create an instance of {@link RSAAuthDIDAuthVerifyCertsType }
     * 
     */
    public RSAAuthDIDAuthVerifyCertsType createRSAAuthDIDAuthVerifyCertsType() {
        return new RSAAuthDIDAuthVerifyCertsType();
    }

    /**
     * Create an instance of {@link RSAAuthDIDAuthInternalAuthType }
     * 
     */
    public RSAAuthDIDAuthInternalAuthType createRSAAuthDIDAuthInternalAuthType() {
        return new RSAAuthDIDAuthInternalAuthType();
    }

    /**
     * Create an instance of {@link RSAAuthDIDAuthMutualAuthType }
     * 
     */
    public RSAAuthDIDAuthMutualAuthType createRSAAuthDIDAuthMutualAuthType() {
        return new RSAAuthDIDAuthMutualAuthType();
    }

    /**
     * Create an instance of {@link RSAAuthDIDAuthExternalAuthType }
     * 
     */
    public RSAAuthDIDAuthExternalAuthType createRSAAuthDIDAuthExternalAuthType() {
        return new RSAAuthDIDAuthExternalAuthType();
    }

    /**
     * Create an instance of {@link EmptyResponseDataType }
     * 
     */
    public EmptyResponseDataType createEmptyResponseDataType() {
        return new EmptyResponseDataType();
    }

    /**
     * Create an instance of {@link AlgorithmInfoType }
     * 
     */
    public AlgorithmInfoType createAlgorithmInfoType() {
        return new AlgorithmInfoType();
    }

    /**
     * Create an instance of {@link PSSParameterType }
     * 
     */
    public PSSParameterType createPSSParameterType() {
        return new PSSParameterType();
    }

    /**
     * Create an instance of {@link CryptoKeyInfoType }
     * 
     */
    public CryptoKeyInfoType createCryptoKeyInfoType() {
        return new CryptoKeyInfoType();
    }

    /**
     * Create an instance of {@link CryptoMarkerType }
     * 
     */
    public CryptoMarkerType createCryptoMarkerType() {
        return new CryptoMarkerType();
    }

    /**
     * Create an instance of {@link CryptoDIDUpdateDataType }
     * 
     */
    public CryptoDIDUpdateDataType createCryptoDIDUpdateDataType() {
        return new CryptoDIDUpdateDataType();
    }

    /**
     * Create an instance of {@link HashInputType }
     * 
     */
    public HashInputType createHashInputType() {
        return new HashInputType();
    }

    /**
     * Create an instance of {@link HashOutputType }
     * 
     */
    public HashOutputType createHashOutputType() {
        return new HashOutputType();
    }

    /**
     * Create an instance of {@link PathType.TagRef }
     * 
     */
    public PathType.TagRef createPathTypeTagRef() {
        return new PathType.TagRef();
    }

    /**
     * Create an instance of {@link PathType.AppFileRef }
     * 
     */
    public PathType.AppFileRef createPathTypeAppFileRef() {
        return new PathType.AppFileRef();
    }

    /**
     * Create an instance of {@link PathType.AppTagRef }
     * 
     */
    public PathType.AppTagRef createPathTypeAppTagRef() {
        return new PathType.AppTagRef();
    }

    /**
     * Create an instance of {@link SecurityConditionType.And }
     * 
     */
    public SecurityConditionType.And createSecurityConditionTypeAnd() {
        return new SecurityConditionType.And();
    }

    /**
     * Create an instance of {@link SecurityConditionType.Or }
     * 
     */
    public SecurityConditionType.Or createSecurityConditionTypeOr() {
        return new SecurityConditionType.Or();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DIDAuthenticationStateType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DIDAuthenticationStateType }{@code >}
     */
    @XmlElementDecl(namespace = "urn:iso:std:iso-iec:24727:tech:schema", name = "DIDAuthenticationState", scope = StateTransitionType.class)
    public JAXBElement<DIDAuthenticationStateType> createStateTransitionTypeDIDAuthenticationState(DIDAuthenticationStateType value) {
        return new JAXBElement<DIDAuthenticationStateType>(_StateTransitionTypeDIDAuthenticationState_QNAME, DIDAuthenticationStateType.class, StateTransitionType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     */
    @XmlElementDecl(namespace = "urn:iso:std:iso-iec:24727:tech:schema", name = "RetryCounter", scope = StateTransitionType.class)
    public JAXBElement<BigInteger> createStateTransitionTypeRetryCounter(BigInteger value) {
        return new JAXBElement<BigInteger>(_StateTransitionTypeRetryCounter_QNAME, BigInteger.class, StateTransitionType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     */
    @XmlElementDecl(namespace = "urn:iso:std:iso-iec:24727:tech:schema", name = "UsageCounter", scope = StateTransitionType.class)
    public JAXBElement<BigInteger> createStateTransitionTypeUsageCounter(BigInteger value) {
        return new JAXBElement<BigInteger>(_StateTransitionTypeUsageCounter_QNAME, BigInteger.class, StateTransitionType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CardCallSequenceType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CardCallSequenceType }{@code >}
     */
    @XmlElementDecl(namespace = "urn:iso:std:iso-iec:24727:tech:schema", name = "FixedProcedure", scope = StateTransitionType.class)
    public JAXBElement<CardCallSequenceType> createStateTransitionTypeFixedProcedure(CardCallSequenceType value) {
        return new JAXBElement<CardCallSequenceType>(_StateTransitionTypeFixedProcedure_QNAME, CardCallSequenceType.class, StateTransitionType.class, value);
    }

}
