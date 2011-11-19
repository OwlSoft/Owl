package at.owlsoft.owl.dao;

import at.owlsoft.owl.dao.IDao.IActivityDao;
import at.owlsoft.owl.dao.IDao.IConfigurationDao;
import at.owlsoft.owl.dao.IDao.IGeneralDao;
import at.owlsoft.owl.dao.IDao.IMediumDao;
import at.owlsoft.owl.dao.IDao.IMediumExemplarDao;
import at.owlsoft.owl.dao.IDao.IRentalDao;
import at.owlsoft.owl.dao.IDao.ISystemUserDao;
import at.owlsoft.owl.dao.IDao.ISystemUserStatusEntryDao;
import at.owlsoft.owl.dao.IDao.ISystemUserTransactionDao;
import at.owlsoft.owl.dao.IDao.ITagDao;
import at.owlsoft.owl.dao.db4o.Db4ODaoFactory;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

public class DaoFactory implements IDaoFactory
{

    private static IDaoFactory     _factory;
    private static ObjectContainer _db;

    private DaoFactory()
    {
    }

    public static IDaoFactory getDb4ODaoInstance()
    {
        if (_db == null)
        {
            _db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),
                    "DB4OFILENAME");
        }

        if (_factory == null)
        {
            _factory = Db4ODaoFactory.getInstance(_db);
        }

        return _factory;
    }

    @Override
    public IActivityDao getActivityDao()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IConfigurationDao getConfigurationDao()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IGeneralDao getGeneralDao()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IMediumDao getMediumDao()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IMediumExemplarDao getMediumExemplarDao()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IRentalDao getRentalDao()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ISystemUserDao getSystemUserDao()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ISystemUserStatusEntryDao getSystemUserStatusEntryDao()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ISystemUserTransactionDao getSystemUserTransactionDao()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ITagDao getTagDao()
    {
        // TODO Auto-generated method stub
        return null;
    }

}
