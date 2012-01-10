package at.owlsoft.owlet.context;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import at.owlsoft.owl.communication.ejb.ApiProviderBeanRemote;

public class EjbContext
{
    private static EjbContext _instance;

    public static EjbContext getInstance()
    {
        if (_instance == null)
        {
            _instance = new EjbContext();
        }

        return _instance;
    }

    private ApiProviderBeanRemote _factory;
    private InitialContext        _context;

    private EjbContext()
    {
        try
        {
            _context = new InitialContext();

            _factory = (ApiProviderBeanRemote) _context
                    .lookup(ApiProviderBeanRemote.JNDI_NAME);

            _factory = (ApiProviderBeanRemote) _context
                    .lookup(ApiProviderBeanRemote.JNDI_NAME);

        }
        catch (NamingException e)
        {

            e.printStackTrace();
        }
    }

    public ApiProviderBeanRemote getFactory()
    {
        return _factory;
    }
}
