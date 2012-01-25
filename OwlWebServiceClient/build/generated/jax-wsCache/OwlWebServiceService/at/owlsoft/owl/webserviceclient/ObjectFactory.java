
package at.owlsoft.owl.webserviceclient;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the at.owlsoft.owl.webserviceclient package. 
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

    private final static QName _GetSessionResponse_QNAME = new QName("http://webservice.owl.owlsoft.at/", "getSessionResponse");
    private final static QName _RentMediumResponse_QNAME = new QName("http://webservice.owl.owlsoft.at/", "rentMediumResponse");
    private final static QName _ReserveMedium_QNAME = new QName("http://webservice.owl.owlsoft.at/", "reserveMedium");
    private final static QName _SearchMediumResponse_QNAME = new QName("http://webservice.owl.owlsoft.at/", "searchMediumResponse");
    private final static QName _NoPermissionException_QNAME = new QName("http://webservice.owl.owlsoft.at/", "NoPermissionException");
    private final static QName _ReserveMediumResponse_QNAME = new QName("http://webservice.owl.owlsoft.at/", "reserveMediumResponse");
    private final static QName _GetSession_QNAME = new QName("http://webservice.owl.owlsoft.at/", "getSession");
    private final static QName _RentMedium_QNAME = new QName("http://webservice.owl.owlsoft.at/", "rentMedium");
    private final static QName _SearchMedium_QNAME = new QName("http://webservice.owl.owlsoft.at/", "searchMedium");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: at.owlsoft.owl.webserviceclient
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetSession }
     * 
     */
    public GetSession createGetSession() {
        return new GetSession();
    }

    /**
     * Create an instance of {@link ReserveMedium }
     * 
     */
    public ReserveMedium createReserveMedium() {
        return new ReserveMedium();
    }

    /**
     * Create an instance of {@link RentMedium }
     * 
     */
    public RentMedium createRentMedium() {
        return new RentMedium();
    }

    /**
     * Create an instance of {@link GetSessionResponse }
     * 
     */
    public GetSessionResponse createGetSessionResponse() {
        return new GetSessionResponse();
    }

    /**
     * Create an instance of {@link ReserveMediumResponse }
     * 
     */
    public ReserveMediumResponse createReserveMediumResponse() {
        return new ReserveMediumResponse();
    }

    /**
     * Create an instance of {@link SearchMedium }
     * 
     */
    public SearchMedium createSearchMedium() {
        return new SearchMedium();
    }

    /**
     * Create an instance of {@link WsMedium }
     * 
     */
    public WsMedium createWsMedium() {
        return new WsMedium();
    }

    /**
     * Create an instance of {@link NoPermissionException }
     * 
     */
    public NoPermissionException createNoPermissionException() {
        return new NoPermissionException();
    }

    /**
     * Create an instance of {@link SearchMediumResponse }
     * 
     */
    public SearchMediumResponse createSearchMediumResponse() {
        return new SearchMediumResponse();
    }

    /**
     * Create an instance of {@link RentMediumResponse }
     * 
     */
    public RentMediumResponse createRentMediumResponse() {
        return new RentMediumResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSessionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.owl.owlsoft.at/", name = "getSessionResponse")
    public JAXBElement<GetSessionResponse> createGetSessionResponse(GetSessionResponse value) {
        return new JAXBElement<GetSessionResponse>(_GetSessionResponse_QNAME, GetSessionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RentMediumResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.owl.owlsoft.at/", name = "rentMediumResponse")
    public JAXBElement<RentMediumResponse> createRentMediumResponse(RentMediumResponse value) {
        return new JAXBElement<RentMediumResponse>(_RentMediumResponse_QNAME, RentMediumResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReserveMedium }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.owl.owlsoft.at/", name = "reserveMedium")
    public JAXBElement<ReserveMedium> createReserveMedium(ReserveMedium value) {
        return new JAXBElement<ReserveMedium>(_ReserveMedium_QNAME, ReserveMedium.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchMediumResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.owl.owlsoft.at/", name = "searchMediumResponse")
    public JAXBElement<SearchMediumResponse> createSearchMediumResponse(SearchMediumResponse value) {
        return new JAXBElement<SearchMediumResponse>(_SearchMediumResponse_QNAME, SearchMediumResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NoPermissionException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.owl.owlsoft.at/", name = "NoPermissionException")
    public JAXBElement<NoPermissionException> createNoPermissionException(NoPermissionException value) {
        return new JAXBElement<NoPermissionException>(_NoPermissionException_QNAME, NoPermissionException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReserveMediumResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.owl.owlsoft.at/", name = "reserveMediumResponse")
    public JAXBElement<ReserveMediumResponse> createReserveMediumResponse(ReserveMediumResponse value) {
        return new JAXBElement<ReserveMediumResponse>(_ReserveMediumResponse_QNAME, ReserveMediumResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSession }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.owl.owlsoft.at/", name = "getSession")
    public JAXBElement<GetSession> createGetSession(GetSession value) {
        return new JAXBElement<GetSession>(_GetSession_QNAME, GetSession.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RentMedium }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.owl.owlsoft.at/", name = "rentMedium")
    public JAXBElement<RentMedium> createRentMedium(RentMedium value) {
        return new JAXBElement<RentMedium>(_RentMedium_QNAME, RentMedium.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchMedium }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.owl.owlsoft.at/", name = "searchMedium")
    public JAXBElement<SearchMedium> createSearchMedium(SearchMedium value) {
        return new JAXBElement<SearchMedium>(_SearchMedium_QNAME, SearchMedium.class, null, value);
    }

}
