package at.owlsoft.owl.dao;

public interface IDaoFactory
{

    public Object getActivityDao();

    public Object getConfigurationDao();

    public Object getGeneralDao();

    public Object getMediumDao();

    public Object getMediumExemplarDao();

    public Object getRentalDao();

    public Object getSystemUserDao();

    public Object getSystemUserStatusEntryDao();

    public Object getSystemUserTransactionDao();

    public Object getTagDao();
}
