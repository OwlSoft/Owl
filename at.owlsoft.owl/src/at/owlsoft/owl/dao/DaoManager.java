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
        if (_factory == null)
        {
            _factory = Db4ODaoFactory.getInstance();
        }
        else
        {
            _logger.debug("connectionString already instantiated, returning existing Instance");
        }

        return _factory;
    }

    private static String _connectionString = null;

    public static IDaoFactory getInstance(String connectionString)
    {
        // connection string change?
        if (_connectionString != null
                && !_connectionString.equals(connectionString))
        {
            closeDbConnection();
            _factory = null;
        }

        if (_factory == null)
        {
            _connectionString = connectionString;
            _factory = Db4ODaoFactory.getInstance(connectionString);
        }
        else
        {
            _logger.debug("connection already instantiated, returning existing Instance");
        }

        return _factory;
    }

    public static boolean closeDbConnection()
    {
        if (_factory != null)
        {
            boolean dbClose = _factory.closeDbConnection();
            _factory = null;
            return dbClose;
        }
        return true;
    }
}
