package at.owlsoft.owl.model.dao.test;

import org.junit.Test;

import EDU.purdue.cs.bloat.util.Assert;
import at.owlsoft.owl.dao.DaoManager;

public class DaoFactoryTest
{
    @Test
    public void testDaoSingelton()
    {

        Assert.isNotNull(DaoManager.getInstance());
        Assert.isNotNull(DaoManager.getInstance().getConfigurationDao());
        Assert.isNotNull(DaoManager.getInstance().getMediumDao());
        Assert.isNotNull(DaoManager.getInstance().getMediumExemplarDao());
        Assert.isNotNull(DaoManager.getInstance().getRentalDao());
        Assert.isNotNull(DaoManager.getInstance().getSystemUserDao());
        Assert.isNotNull(DaoManager.getInstance().getSystemUserStatusEntryDao());
        Assert.isNotNull(DaoManager.getInstance().getSystemUserTransactionDao());
        Assert.isNotNull(DaoManager.getInstance().getTagDao());
    }

    @Test
    public void testGeneralDao()
    {

    }
}
