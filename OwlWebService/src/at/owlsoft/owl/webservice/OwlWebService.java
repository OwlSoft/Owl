package at.owlsoft.owl.webservice;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import at.owlsoft.owl.communication.ejb.RentalApiRemote;
import at.owlsoft.owl.communication.ejb.ReservationApiRemote;
import at.owlsoft.owl.communication.ejb.SearchApiRemote;
import at.owlsoft.owl.model.NoPermissionException;
import at.owlsoft.owl.model.media.IMedium;
import at.owlsoft.owl.model.media.IMediumExemplar;

@WebService
public class OwlWebService
{
    private static Logger _logger = Logger.getLogger(OwlWebService.class
                                          .getName());

    @WebMethod
    public List<WSMedium> searchMedium(
            @WebParam(name = "session") String session,
            @WebParam(name = "title") String title,
            @WebParam(name = "author") String author,
            @WebParam(name = "publisher") String publisher)
    {
        _logger.info(session + "," + title + "," + author + "," + publisher);
        SearchApiRemote search = SessionHolder.getInstance().getSearchApi(
                session);
        UUID searchID = UUID.randomUUID();
        if (title != null && !title.isEmpty())
        {
            search.addNewSearchField(searchID);
            search.setSearchFieldData(searchID, "_title", title);
        }
        if (author != null && !author.isEmpty())
        {
            searchID = UUID.randomUUID();
            search.addNewSearchField(searchID);
            search.setSearchFieldData(searchID, "_author", author);
        }
        if (publisher != null && !publisher.isEmpty())
        {
            searchID = UUID.randomUUID();
            search.addNewSearchField(searchID);
            search.setSearchFieldData(searchID, "_publisher", publisher);
        }
        List<WSMedium> temp = new ArrayList<WSMedium>();
        for (IMedium medium : search.search())
        {
            WSMedium wsMedium = new WSMedium();
            wsMedium.setIMedium(medium);
            temp.add(wsMedium);
        }
        _logger.info("Found: " + search.search().size());
        _logger.info("In List: " + temp.size());
        return temp;
    }

    @WebMethod
    public String getSession(@WebParam(name = "username") String user,
            @WebParam(name = "password") String pass)
            throws NoPermissionException
    {
        String session = SessionHolder.getInstance().createSession();
        SessionHolder.getInstance().getAuthenticationApi(session)
                .login(user, pass);

        return session;
    }

    @WebMethod
    public boolean rentMedium(@WebParam(name = "session") String session,
            @WebParam(name = "mediumId") int mediumId)
            throws NoPermissionException
    {
        RentalApiRemote rental = SessionHolder.getInstance().getRentalApi(
                session);
        SearchApiRemote search = SessionHolder.getInstance().getSearchApi(
                session);
        rental.newRental();
        rental.setCustomer(SessionHolder.getInstance()
                .getAuthenticationApi(session).getCurrentUser().getCardID());
        UUID searchFieldID = UUID.randomUUID();
        search.addNewSearchField(searchFieldID);
        search.setSearchFieldData(searchFieldID, "_mediumID",
                Integer.toString(mediumId));
        IMedium media = search.search().get(0);

        for (IMediumExemplar copy : media.getMediumExemplars())
        {
            rental.setMediumExemplar(copy.getExemplarID());
            if (rental.store())
            {
                return true;
            }
        }

        return false;

    }

    @WebMethod
    public boolean reserveMedium(@WebParam(name = "session") String session,
            @WebParam(name = "mediumId") int mediumId)
            throws NoPermissionException
    {
        ReservationApiRemote reservation = SessionHolder.getInstance()
                .getReservationApi(session);
        SearchApiRemote search = SessionHolder.getInstance().getSearchApi(
                session);
        reservation.newReservation();
        UUID searchFieldID = UUID.randomUUID();
        search.addNewSearchField(searchFieldID);
        search.setSearchFieldData(searchFieldID, "_mediumID",
                Integer.toString(mediumId));
        reservation.setMedium(search.search().get(0).getMediumID());
        reservation.setCustomer(SessionHolder.getInstance()
                .getAuthenticationApi(session).getCurrentUser().getCardID());

        return reservation.store();
    }
}
