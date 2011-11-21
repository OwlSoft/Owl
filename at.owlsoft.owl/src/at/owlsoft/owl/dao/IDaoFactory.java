package at.owlsoft.owl.dao;


public interface IDaoFactory
{

    public IActivityDao getActivityDao();

    public IConfigurationDao getConfigurationDao();

    public IMediumDao getMediumDao();

    public IMediumExemplarDao getMediumExemplarDao();

    public IRentalDao getRentalDao();

    public IReservationDao getReservationDao();

    public ISystemUserDao getSystemUserDao();

    public ISystemUserStatusEntryDao getSystemUserStatusEntryDao();

    public ISystemUserTransactionDao getSystemUserTransactionDao();

    public ITagDao getTagDao();
}
