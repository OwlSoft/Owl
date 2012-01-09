package at.owlsoft.owlet.context;


//public class EjbApiFactory
//{
//    private InitialContext _context;
//
//    public EjbApiFactory() throws NamingException
//    {
//        super();
//        _context = new InitialContext();
//    }
//
//    public RentalApiRemote createRentalApi() throws NamingException
//    {
//        return (RentalApiRemote) _context.lookup(RentalApiRemote.JNDI_NAME);
//    }
//
//    public ReservationApiRemote createReservationApi() throws NamingException
//    {
//        return (ReservationApiRemote) _context
//                .lookup(ReservationApiRemote.JNDI_NAME);
//    }
//
//    public SearchApiRemote createSearchApi() throws NamingException
//    {
//        return (SearchApiRemote) _context.lookup(SearchApiRemote.JNDI_NAME);
//    }
//
//    public SystemUserApiRemote createSystemUserApi() throws NamingException
//    {
//        return (SystemUserApiRemote) _context
//                .lookup(SystemUserApiRemote.JNDI_NAME);
//    }
//
//    public ConfigurationApiRemote createConfigurationApi()
//            throws NamingException
//    {
//        return (ConfigurationApiRemote) _context
//                .lookup(ConfigurationApiRemote.JNDI_NAME);
//    }
//
//    public AuthenticationApiRemote createAuthenticationApi()
//            throws NamingException
//    {
//        return (AuthenticationApiRemote) _context
//                .lookup(AuthenticationApiRemote.JNDI_NAME);
//    }
//
//    public MessagingApiRemote createMessagingApi() throws NamingException
//    {
//        return (MessagingApiRemote) _context
//                .lookup(MessagingApiRemote.JNDI_NAME);
//    }
//
// }
