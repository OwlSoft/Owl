package at.owlsoft.owl.communication.ejb;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;

import at.owlsoft.owl.business.OwlApplicationContext;

/**
 * Session Bean implementation class ApiProviderBean
 */
@Stateful(mappedName = ApiProviderBeanRemote.JNDI_NAME)
public class ApiProviderBean implements ApiProviderBeanRemote,
        ApiProviderBeanLocal
{
    private static final String     CONTEXT_KEY = "CurrentOwlApplicationContext";
    @EJB(mappedName = RentalApiRemote.JNDI_NAME)
    private RentalApiRemote         _rentalApi;

    @EJB(mappedName = ReservationApiRemote.JNDI_NAME)
    private ReservationApiRemote    _reservationApi;

    @EJB(mappedName = SearchApiRemote.JNDI_NAME)
    private SearchApiRemote         _searchApi;

    @EJB(mappedName = SystemUserApiRemote.JNDI_NAME)
    private SystemUserApiRemote     _systemUserApi;

    @EJB(mappedName = ConfigurationApiRemote.JNDI_NAME)
    private ConfigurationApiRemote  _configurationApi;

    @EJB(mappedName = AuthenticationApiRemote.JNDI_NAME)
    private AuthenticationApiRemote _authenticationApi;

    @EJB(mappedName = MessagingApiRemote.JNDI_NAME)
    private MessagingApiRemote      _messagingApi;

    @Resource
    private SessionContext          _session;

    private OwlApplicationContext getContext()
    {
        OwlApplicationContext context = null;
        if (!_session.getContextData().containsKey(CONTEXT_KEY))
        {
            System.out.println("Create new applicationcontext");
            context = new OwlApplicationContext();
            _session.getContextData().put(CONTEXT_KEY, context);
        }
        else
        {
            context = (OwlApplicationContext) _session.getContextData().get(
                    CONTEXT_KEY);
        }
        return context;
    }

    @Override
    public RentalApiRemote createRentalApi()
    {
        _rentalApi.setContext(getContext());
        return _rentalApi;
    }

    @Override
    public ReservationApiRemote createReservationApi()
    {
        _reservationApi.setContext(getContext());
        return _reservationApi;
    }

    @Override
    public SearchApiRemote createSearchApi()
    {
        _searchApi.setContext(getContext());
        return _searchApi;
    }

    @Override
    public SystemUserApiRemote createSystemUserApi()
    {
        _systemUserApi.setContext(getContext());
        return _systemUserApi;
    }

    @Override
    public ConfigurationApiRemote createConfigurationApi()
    {
        _configurationApi.setContext(getContext());
        return _configurationApi;
    }

    @Override
    public AuthenticationApiRemote createAuthenticationApi()
    {
        _authenticationApi.setContext(getContext());
        return _authenticationApi;
    }

    @Override
    public MessagingApiRemote createMessagingApi()
    {
        _messagingApi.setContext(getContext());
        return _messagingApi;
    }

}
