package at.owlsoft.owlet.context;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import at.owlsoft.owl.communication.ejb.AuthenticationApiRemote;
import at.owlsoft.owl.communication.ejb.ConfigurationApiRemote;
import at.owlsoft.owl.communication.ejb.MessagingApiRemote;
import at.owlsoft.owl.communication.ejb.RentalApiRemote;
import at.owlsoft.owl.communication.ejb.ReservationApiRemote;
import at.owlsoft.owl.communication.ejb.SearchApiRemote;
import at.owlsoft.owl.communication.ejb.SystemUserApiRemote;

public class EjbApiFactory
{
    private InitialContext _context;

    public EjbApiFactory() throws NamingException
    {
        super();
        _context = new InitialContext();
    }

    public RentalApiRemote createRentalApi() throws NamingException
    {
        return (RentalApiRemote) _context.lookup(RentalApiRemote.JNDI_NAME);
    }

    public ReservationApiRemote createReservationApi() throws NamingException
    {
        return (ReservationApiRemote) _context
                .lookup(ReservationApiRemote.JNDI_NAME);
    }

    public SearchApiRemote createSearchApi() throws NamingException
    {
        return (SearchApiRemote) _context.lookup(SearchApiRemote.JNDI_NAME);
    }

    public SystemUserApiRemote createSystemUserApi() throws NamingException
    {
        return (SystemUserApiRemote) _context
                .lookup(SystemUserApiRemote.JNDI_NAME);
    }

    public ConfigurationApiRemote createConfigurationApi()
            throws NamingException
    {
        return (ConfigurationApiRemote) _context
                .lookup(ConfigurationApiRemote.JNDI_NAME);
    }

    public AuthenticationApiRemote createAuthenticationApi()
            throws NamingException
    {
        return (AuthenticationApiRemote) _context
                .lookup(AuthenticationApiRemote.JNDI_NAME);
    }

    public MessagingApiRemote createMessagingApi() throws NamingException
    {
        return (MessagingApiRemote) _context
                .lookup(MessagingApiRemote.JNDI_NAME);
    }

}
