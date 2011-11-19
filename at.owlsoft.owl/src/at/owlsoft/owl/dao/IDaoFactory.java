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

public interface IDaoFactory
{

    public IActivityDao getActivityDao();

    public IConfigurationDao getConfigurationDao();

    public IGeneralDao getGeneralDao();

    public IMediumDao getMediumDao();

    public IMediumExemplarDao getMediumExemplarDao();

    public IRentalDao getRentalDao();

    public ISystemUserDao getSystemUserDao();

    public ISystemUserStatusEntryDao getSystemUserStatusEntryDao();

    public ISystemUserTransactionDao getSystemUserTransactionDao();

    public ITagDao getTagDao();
}
