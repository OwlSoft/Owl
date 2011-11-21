package at.owlsoft.owl.dao.db4o;

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

import com.db4o.ObjectContainer;

public class Db4ODaoFactory implements IDaoFactory
{

    private static Db4ODaoFactory  _factory;
    private static ObjectContainer _db;

    private Db4ODaoFactory()
    {
    }

    public static IDaoFactory getInstance(ObjectContainer db)
    {
        if (db != null)
        {
            _db = db;
        }
        if (_factory == null)
        {
            if (_db != null)
            {
                _factory = new Db4ODaoFactory();
            }
            else
            {
                throw new NoDatabaseConfiguredException(
                        "no database configured in this factory, use getInstance method parameter");
            }
        }
        return _factory;
    }

    @Override
    public IActivityDao getActivityDao()
    {
        // TODO Auto-generated method stub
        return ActivityDao.getInstance(_db);
    }

    @Override
    public IConfigurationDao getConfigurationDao()
    {
        // TODO Auto-generated method stub
        return ConfigurationDao.getInstance(_db);
    }

    @Override
    public IMediumDao getMediumDao()
    {
        // TODO Auto-generated method stub
        return MediumDao.getInstance(_db);
    }

    @Override
    public IMediumExemplarDao getMediumExemplarDao()
    {
        // TODO Auto-generated method stub
        return MediumExemplarDao.getInstance(_db);
    }

    @Override
    public IRentalDao getRentalDao()
    {
        // TODO Auto-generated method stub
        return RentalDao.getInstance(_db);
    }

    @Override
    public ISystemUserDao getSystemUserDao()
    {
        // TODO Auto-generated method stub
        return SystemUserDao.getInstance(_db);
    }

    @Override
    public ISystemUserStatusEntryDao getSystemUserStatusEntryDao()
    {
        // TODO Auto-generated method stub
        return SystemUserStatusEntryDao.getInstance(_db);
    }

    @Override
    public ISystemUserTransactionDao getSystemUserTransactionDao()
    {
        // TODO Auto-generated method stub
        return SystemUserTransactionDao.getInstance(_db);
    }

    @Override
    public ITagDao getTagDao()
    {
        // TODO Auto-generated method stub
        return TagDao.getInstance(_db);
    }

    @Override
    public IReservationDao getReservationDao()
    {
        // TODO Auto-generated method stub
        return ReservationDao.getInstance(_db);
    }
}
