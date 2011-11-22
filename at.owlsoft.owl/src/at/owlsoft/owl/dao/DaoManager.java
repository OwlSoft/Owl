package at.owlsoft.owl.dao;

import org.apache.log4j.Logger;

import at.owlsoft.owl.dao.db4o.Db4ODaoFactory;

public class DaoManager
{

    private static IDaoFactory _factory;
    private static Logger      _logger = Logger.getLogger(DaoManager.class);

    private DaoManager()
    {
    }

    public static IDaoFactory getInstance()
    {
        return getInstance("DB4OFILENAME");
    }

    public static IDaoFactory getInstance(String connectionString)
    {
        if (_factory == null)
        {
            _factory = Db4ODaoFactory.getInstance(connectionString);
        }
        else
        {
            _logger.debug("connectionString already instantiated, returning existing Instance");
        }

        return _factory;
    }

    public static boolean closeDbConnection()
    {
        boolean dbClose = _factory.closeDbConnection();
        _factory = null;
        return dbClose;
    }

}
