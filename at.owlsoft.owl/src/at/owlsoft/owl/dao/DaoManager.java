package at.owlsoft.owl.dao;

import at.owlsoft.owl.dao.db4o.Db4ODaoFactory;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

public class DaoManager
{

    private static IDaoFactory     _factory;
    private static ObjectContainer _db;

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

}
