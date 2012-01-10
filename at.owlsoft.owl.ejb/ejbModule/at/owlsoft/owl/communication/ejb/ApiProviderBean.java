package at.owlsoft.owl.communication.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import at.owlsoft.owl.business.OwlApplicationContext;

/**
 * Session Bean implementation class ApiProviderBean
 */
@Stateful(mappedName = ApiProviderBeanRemote.JNDI_NAME)
public class ApiProviderBean implements ApiProviderBeanRemote,
        ApiProviderBeanLocal
{
    private static final String    CONTEXT_KEY = "CurrentOwlApplicationContext";
    @EJB
    private RentalApiLocal         _rentalApi;

    @EJB
    private ReservationApiLocal    _reservationApi;

    @EJB
    private SearchApiLocal         _searchApi;

    @EJB
    private SystemUserApiLocal     _systemUserApi;

    @EJB
    private ConfigurationApiLocal  _configurationApi;

    @EJB
    private AuthenticationApiLocal _authenticationApi;

    @EJB
    private MessagingApiLocal      _messagingApi;

    // @Resource
    // private SessionContext _session;

    private OwlApplicationContext  _context;

    private OwlApplicationContext getContext()
    {

        if (_context == null)
        {
            _context = new OwlApplicationContext();
        }

        return _context;

        // OwlApplicationContext context = null;
        // if (!_session.getContextData().containsKey(CONTEXT_KEY))
        // {
        // System.out.println("Create new applicationcontext");
        // context = new OwlApplicationContext();
        // _session.getContextData().put(CONTEXT_KEY, context);
        // }
        // else
        // {
        // context = (OwlApplicationContext) _session.getContextData().get(
        // CONTEXT_KEY);
        // }
        // return context;

    }

    @Override
    public RentalApiRemote createRentalApi()
    {
        _rentalApi.setContext(getContext());
        return _rentalApi.getRemoteObject();
    }

    @Override
    public ReservationApiRemote createReservationApi()
    {
        _reservationApi.setContext(getContext());
        return _reservationApi.getRemoteObject();
    }

    @Override
    public SearchApiRemote createSearchApi()
    {
        _searchApi.setContext(getContext());
        return _searchApi.getRemoteObject();
    }

    @Override
    public SystemUserApiRemote createSystemUserApi()
    {
        _systemUserApi.setContext(getContext());
        return _systemUserApi.getRemoteObject();
    }

    @Override
    public ConfigurationApiRemote createConfigurationApi()
    {
        _configurationApi.setContext(getContext());
        return _configurationApi.getRemoteObject();
    }

    @Override
    public AuthenticationApiRemote createAuthenticationApi()
    {
        _authenticationApi.setContext(getContext());
        return _authenticationApi.getRemoteObject();
    }

    @Override
    public MessagingApiRemote createMessagingApi()
    {
        _messagingApi.setContext(getContext());
        return _messagingApi.getRemoteObject();
    }

}
