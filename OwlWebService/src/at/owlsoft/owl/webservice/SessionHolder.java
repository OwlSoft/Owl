package at.owlsoft.owl.webservice;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import at.owlsoft.owl.communication.ejb.ApiProviderBeanRemote;
import at.owlsoft.owl.communication.ejb.AuthenticationApiRemote;
import at.owlsoft.owl.communication.ejb.RentalApiRemote;
import at.owlsoft.owl.communication.ejb.ReservationApiRemote;
import at.owlsoft.owl.communication.ejb.SearchApiRemote;

public class SessionHolder
{

    private static SessionHolder             _instance;
    private Map<UUID, ApiProviderBeanRemote> _sessionMap;

    private SessionHolder()
    {
        _sessionMap = new HashMap<UUID, ApiProviderBeanRemote>();
    }

    public static SessionHolder getInstance()
    {
        if (_instance == null)
        {
            _instance = new SessionHolder();
        }
        return _instance;
    }

    public String createSession()
    {
        UUID session = UUID.randomUUID();

        InitialContext _context;
        try
        {
            _context = new InitialContext();
            ApiProviderBeanRemote sessionBean = (ApiProviderBeanRemote) _context
                    .lookup(ApiProviderBeanRemote.JNDI_NAME);
            _sessionMap.put(session, sessionBean);
        }
        catch (NamingException e)
        {
            e.printStackTrace();
        }

        return session.toString();
    }

    public SearchApiRemote getSearchApi(String session)
    {
        return _sessionMap.get(UUID.fromString(session)).createSearchApi();
    }

    public RentalApiRemote getRentalApi(String session)
    {
        return _sessionMap.get(UUID.fromString(session)).createRentalApi();
    }

    public AuthenticationApiRemote getAuthenticationApi(String session)
    {
        return _sessionMap.get(UUID.fromString(session))
                .createAuthenticationApi();
    }

    public ReservationApiRemote getReservationApi(String session)
    {
        return _sessionMap.get(UUID.fromString(session)).createReservationApi();
    }

}
