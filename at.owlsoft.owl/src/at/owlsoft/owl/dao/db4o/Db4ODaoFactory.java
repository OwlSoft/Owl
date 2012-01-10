package at.owlsoft.owl.dao.db4o;

import org.apache.log4j.Logger;

import at.owlsoft.owl.dao.IActivityDao;
import at.owlsoft.owl.dao.IConfigurationDao;
import at.owlsoft.owl.dao.IDaoFactory;
import at.owlsoft.owl.dao.IMediumDao;
import at.owlsoft.owl.dao.IMediumExemplarDao;
import at.owlsoft.owl.dao.IMessageDao;
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
    private static final String   DEFAULT_DATABASE = "DB4OFILENAME";

    private static Db4ODaoFactory _factory;

    private static Logger         _logger          = Logger.getLogger(Db4ODaoFactory.class);

    private Db4ODaoFactory(String connectionString)
    {
        EmbeddedConfiguration configuration = Db4oEmbedded.newConfiguration();
        configuration.file().lockDatabaseFile(false);
        _db = Db4oEmbedded.openFile(configuration, connectionString);
        _logger.debug("Connectionstring to database:" + connectionString);
    }

    public static Db4ODaoFactory getInstance()
    {
        return getInstance(DEFAULT_DATABASE);
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
    public ObjectContainer getDb()
    {
        return _db;
    }

    private ObjectContainer _db;

    private IActivityDao    _activityDao;

    @Override
    public IActivityDao getActivityDao()
    {
        if (_activityDao == null)
        {
            _activityDao = new ActivityDao(this);
        }
        return _activityDao;
    }

    private IConfigurationDao _configurationDao;

    @Override
    public IConfigurationDao getConfigurationDao()
    {
        if (_configurationDao == null)
        {
            _configurationDao = new ConfigurationDao(this);
        }
        return _configurationDao;
    }

    private IMediumDao _mediumDao;

    @Override
    public IMediumDao getMediumDao()
    {
        if (_mediumDao == null)
        {
            _mediumDao = new MediumDao(this);
        }
        return _mediumDao;
    }

    private IMediumExemplarDao _mediumExemplarDao;

    @Override
    public IMediumExemplarDao getMediumExemplarDao()
    {
        if (_mediumExemplarDao == null)
        {
            _mediumExemplarDao = new MediumExemplarDao(this);
        }
        return _mediumExemplarDao;
    }

    private IRentalDao _rentalDao;

    @Override
    public IRentalDao getRentalDao()
    {
        if (_rentalDao == null)
        {
            _rentalDao = new RentalDao(this);
        }
        return _rentalDao;
    }

    private ISystemUserDao _systemUserDao;

    @Override
    public ISystemUserDao getSystemUserDao()
    {
        if (_systemUserDao == null)
        {
            _systemUserDao = new SystemUserDao(this);
        }
        return _systemUserDao;
    }

    private ISystemUserStatusEntryDao _systemUserStatusEntryDao;

    @Override
    public ISystemUserStatusEntryDao getSystemUserStatusEntryDao()
    {
        if (_systemUserStatusEntryDao == null)
        {
            _systemUserStatusEntryDao = new SystemUserStatusEntryDao(this);
        }

        return _systemUserStatusEntryDao;
    }

    private ISystemUserTransactionDao _systemUserTransactionDao;

    @Override
    public ISystemUserTransactionDao getSystemUserTransactionDao()
    {
        if (_systemUserTransactionDao == null)
        {
            _systemUserTransactionDao = new SystemUserTransactionDao(this);
        }
        return _systemUserTransactionDao;
    }

    private ITagDao _tagDao;

    @Override
    public ITagDao getTagDao()
    {
        if (_tagDao == null)
        {
            _tagDao = new TagDao(this);
        }
        return _tagDao;
    }

    private IReservationDao _reservationDao;

    @Override
    public IReservationDao getReservationDao()
    {
        if (_reservationDao == null)
        {
            _reservationDao = new ReservationDao(this);
        }
        return _reservationDao;
    }

    private IMessageDao _messageDao;

    @Override
    public IMessageDao getMessageDao()
    {
        if (_messageDao == null)
        {
            _messageDao = new MessageDao(this);
        }
        return _messageDao;
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
