package at.owlsoft.owl.dao;

import at.owlsoft.owl.dao.db4o.model.ActivityDao;
import at.owlsoft.owl.dao.db4o.model.ConfigurationDao;
import at.owlsoft.owl.dao.db4o.model.GeneralDb4ODao;
import at.owlsoft.owl.dao.db4o.model.MediumDao;
import at.owlsoft.owl.dao.db4o.model.MediumExemplarDao;
import at.owlsoft.owl.dao.db4o.model.RentalDao;
import at.owlsoft.owl.dao.db4o.model.SystemUserDao;
import at.owlsoft.owl.dao.db4o.model.SystemUserStatusEntryDao;
import at.owlsoft.owl.dao.db4o.model.SystemUserTransactionDao;
import at.owlsoft.owl.dao.db4o.model.TagDao;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

public class Db4ODaoFactory implements IDaoFactory
{

    private static Db4ODaoFactory _factory;
    private ObjectContainer       _db;

    private Db4ODaoFactory()
    {
        _db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),
                "DB4OFILENAME");

    }

    public static IDaoFactory getInstance()
    {
        if (_factory == null)
        {
            _factory = new Db4ODaoFactory();
        }
        return _factory;
    }

    @Override
    public ActivityDao getActivityDao()
    {
        // TODO Auto-generated method stub
        return ActivityDao.getInstance(_db);
    }

    @Override
    public ConfigurationDao getConfigurationDao()
    {
        // TODO Auto-generated method stub
        return ConfigurationDao.getInstance(_db);
    }

    @Override
    public GeneralDb4ODao getGeneralDao()
    {
        // TODO Auto-generated method stub
        return GeneralDb4ODao.getInstance(_db);
    }

    @Override
    public MediumDao getMediumDao()
    {
        // TODO Auto-generated method stub
        return MediumDao.getInstance(_db);
    }

    @Override
    public MediumExemplarDao getMediumExemplarDao()
    {
        // TODO Auto-generated method stub
        return MediumExemplarDao.getInstance(_db);
    }

    @Override
    public RentalDao getRentalDao()
    {
        // TODO Auto-generated method stub
        return RentalDao.getInstance(_db);
    }

    @Override
    public SystemUserDao getSystemUserDao()
    {
        // TODO Auto-generated method stub
        return SystemUserDao.getInstance(_db);
    }

    @Override
    public SystemUserStatusEntryDao getSystemUserStatusEntryDao()
    {
        // TODO Auto-generated method stub
        return SystemUserStatusEntryDao.getInstance(_db);
    }

    @Override
    public SystemUserTransactionDao getSystemUserTransactionDao()
    {
        // TODO Auto-generated method stub
        return SystemUserTransactionDao.getInstance(_db);
    }

    @Override
    public TagDao getTagDao()
    {
        // TODO Auto-generated method stub
        return TagDao.getInstance(_db);
    }
}
