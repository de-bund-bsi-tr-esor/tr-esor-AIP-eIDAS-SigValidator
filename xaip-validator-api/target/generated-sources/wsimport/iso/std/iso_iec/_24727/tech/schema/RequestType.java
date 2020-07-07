
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import de.bund.bsi.ecard.api._1.GetCertificate;
import de.bund.bsi.ecard.api._1.ShowViewer;
import oasis.names.tc.dss._1_0.core.schema.RequestBaseType;


/**
 * <p>Java-Klasse für RequestType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="RequestType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{urn:oasis:names:tc:dss:1.0:core:schema}RequestBaseType"&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RequestType")
@XmlSeeAlso({
    StartPAOS.class,
    TCAPIClose.class,
    TCAPIOpen.class,
    ACLModify.class,
    ACLList.class,
    DIDAuthenticate.class,
    DIDDelete.class,
    DIDUpdate.class,
    DIDGet.class,
    DIDCreate.class,
    DIDList.class,
    VerifyCertificate.class,
    VerifySignature.class,
    Sign.class,
    Hash.class,
    GetRandom.class,
    Decipher.class,
    Encipher.class,
    DSIRead.class,
    DSIWrite.class,
    DSIDelete.class,
    DSICreate.class,
    DSIList.class,
    DataSetDelete.class,
    DataSetSelect.class,
    DataSetCreate.class,
    DataSetList.class,
    ExecuteAction.class,
    CardApplicationServiceDescribe.class,
    CardApplicationServiceDelete.class,
    CardApplicationServiceLoad.class,
    CardApplicationServiceCreate.class,
    CardApplicationServiceList.class,
    CardApplicationDelete.class,
    CardApplicationCreate.class,
    CardApplicationList.class,
    CardApplicationEndSession.class,
    CardApplicationStartSession.class,
    CardApplicationDisconnect.class,
    CardApplicationConnect.class,
    CardApplicationPath.class,
    Terminate.class,
    Initialize.class,
    ModifyVerificationData.class,
    VerifyUser.class,
    Transmit.class,
    EndTransaction.class,
    BeginTransaction.class,
    Disconnect.class,
    Connect.class,
    ControlIFD.class,
    Cancel.class,
    Wait.class,
    GetStatus.class,
    GetIFDCapabilities.class,
    ListIFDs.class,
    ReleaseContext.class,
    EstablishContext.class,
    ShowViewer.class,
    GetCertificate.class
})
public class RequestType
    extends RequestBaseType
{


}
