package at.owlsoft.owlet.context;

import javax.naming.NamingException;

public class EjbContext
{
    private static EjbContext _instance;
    private EjbApiFactory     _factory;

    public static EjbContext getInstance()
    {
        if (_instance == null)
        {
            _instance = new EjbContext();
        }

        return _instance;
    }

    private EjbContext()
    {
        try
        {
            _factory = new EjbApiFactory();
        }
        catch (NamingException e)
        {

            e.printStackTrace();
        }
    }

    public EjbApiFactory getFactory()
    {
        return _factory;
    }
}
