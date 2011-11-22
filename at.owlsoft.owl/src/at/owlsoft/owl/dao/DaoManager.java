package at.owlsoft.owl.dao;

import org.apache.log4j.Logger;

import at.owlsoft.owl.dao.db4o.Db4ODaoFactory;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

public class DaoManager
{

    private static IDaoFactory     _factory;
    private static ObjectContainer _db;
    private static Logger          _logger = Logger.getLogger(DaoManager.class);

    private DaoManager()
    {
    }

    public static IDaoFactory getInstance()
    {
        return getInstance("DB4OFILENAME");
    }

    public static IDaoFactory getInstance(String connectionString)
    {
        if (_db == null)
        {
            _db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),
                    connectionString);
        }

        if (_factory == null)
        {
            _factory = Db4ODaoFactory.getInstance(_db);
        }

        return _factory;
    }

    public static boolean closeDbConnection()
    {
        _logger.debug("closing db Connection");

        boolean dbClose = _db.close();
        if (!dbClose)
        {
            _logger.debug("already closed or other error occured");
        }
        _db = null;
        return dbClose;
    }

}
