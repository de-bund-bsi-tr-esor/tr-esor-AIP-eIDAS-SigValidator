
package de.bund.bsi.tr_esor.api._1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
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
 *       &lt;sequence&gt;
 *         &lt;element ref="{urn:oasis:names:tc:dss:1.0:core:schema}OptionalInputs" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
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
    ArchiveDataRequest.class,
    ArchiveDeletionRequest.class,
    ArchiveEvidenceRequest.class,
    ArchiveRetrievalRequest.class,
    ArchiveUpdateRequest.class,
    ArchiveSubmissionRequest.class
})
public class RequestType
    extends RequestBaseType
{


}
