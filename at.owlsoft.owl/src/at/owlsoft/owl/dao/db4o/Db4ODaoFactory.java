package at.owlsoft.owl.dao.db4o;

import org.apache.log4j.Logger;

import at.owlsoft.owl.dao.IActivityDao;
import at.owlsoft.owl.dao.IConfigurationDao;
import at.owlsoft.owl.dao.IDaoFactory;
import at.owlsoft.owl.dao.IMediumDao;
import at.owlsoft.owl.dao.IMediumExemplarDao;
import at.owlsoft.owl.dao.IRentalDao;
import at.owlsoft.owl.dao.IReservationDao;
import at.owlsoft.owl.dao.ISystemUserDao;
import at.owlsoft.owl.dao.ISystemUserStatusEntryDao;
import at.owlsoft.owl.dao.ISystemUserTransactionDao;
import at.owlsoft.owl.dao.ITagDao;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;

public class Db4ODaoFactory implements IDaoFactory
{

    private static Db4ODaoFactory _factory;

    private static Logger         _logger = Logger.getLogger(Db4ODaoFactory.class);

    private Db4ODaoFactory(String connectionString)
    {
        EmbeddedConfiguration configuration = Db4oEmbedded.newConfiguration();
        configuration.file().lockDatabaseFile(false);
        _db = Db4oEmbedded.openFile(configuration, connectionString);
    }

    public static Db4ODaoFactory getInstance(String connectionString)
    {
        if (_factory == null)
        {
            _factory = new Db4ODaoFactory(connectionString);
        }

        return _factory;
    }

    /**
     * @return the db
     */
    public static ObjectContainer getDb()
    {
        return _db;
    }

    private static ObjectContainer _db;

    @Override
    public IActivityDao getActivityDao()
    {
        // TODO Auto-generated method stub
        return ActivityDao.getInstance();
    }

    @Override
    public IConfigurationDao getConfigurationDao()
    {
        // TODO Auto-generated method stub
        return ConfigurationDao.getInstance();
    }

    @Override
    public IMediumDao getMediumDao()
    {
        // TODO Auto-generated method stub
        return MediumDao.getInstance();
    }

    @Override
    public IMediumExemplarDao getMediumExemplarDao()
    {
        // TODO Auto-generated method stub
        return MediumExemplarDao.getInstance();
    }

    @Override
    public IRentalDao getRentalDao()
    {
        // TODO Auto-generated method stub
        return RentalDao.getInstance();
    }

    @Override
    public ISystemUserDao getSystemUserDao()
    {
        // TODO Auto-generated method stub
        return SystemUserDao.getInstance();
    }

    @Override
    public ISystemUserStatusEntryDao getSystemUserStatusEntryDao()
    {
        // TODO Auto-generated method stub
        return SystemUserStatusEntryDao.getInstance();
    }

    @Override
    public ISystemUserTransactionDao getSystemUserTransactionDao()
    {
        // TODO Auto-generated method stub
        return SystemUserTransactionDao.getInstance();
    }

    @Override
    public ITagDao getTagDao()
    {
        // TODO Auto-generated method stub
        return TagDao.getInstance();
    }

    @Override
    public IReservationDao getReservationDao()
    {
        // TODO Auto-generated method stub
        return ReservationDao.getInstance();
    }

    @Override
    public boolean closeDbConnection()
    {
        boolean dbClose = _db.close();
        if (!dbClose)
        {
            _logger.debug("already closed or other error occured");
        }
        else
        {
            _logger.debug("closing db Connection");
        }
        _db = null;
        _factory = null;
        return dbClose;
    }
}
