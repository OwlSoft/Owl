package at.owlsoft.owl.ejbWebFrontEnd;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import at.owlsoft.owl.communication.ejb.ApiProviderBeanRemote;

public class EJBContext
{

    public static EJBContext getInstance()
    {
        return new EJBContext();
    }

    private ApiProviderBeanRemote _factory;
    private InitialContext        _context;

    private EJBContext()
    {
        try
        {
            _context = new InitialContext();

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
