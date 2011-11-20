package at.owlsoft.owl.model.dao.test;

import org.junit.Test;

import EDU.purdue.cs.bloat.util.Assert;
import at.owlsoft.owl.dao.DaoManager;

public class DaoFactoryTest
{
    @Test
    public void testDaoSingelton()
    {

        Assert.isNotNull(DaoManager.getDb4ODaoInstance());
        Assert.isNotNull(DaoManager.getDb4ODaoInstance().getConfigurationDao());
        Assert.isNotNull(DaoManager.getDb4ODaoInstance().getGeneralDao());
        Assert.isNotNull(DaoManager.getDb4ODaoInstance().getMediumDao());
        Assert.isNotNull(DaoManager.getDb4ODaoInstance().getMediumExemplarDao());
        Assert.isNotNull(DaoManager.getDb4ODaoInstance().getRentalDao());
        Assert.isNotNull(DaoManager.getDb4ODaoInstance().getSystemUserDao());
        Assert.isNotNull(DaoManager.getDb4ODaoInstance()
                .getSystemUserStatusEntryDao());
        Assert.isNotNull(DaoManager.getDb4ODaoInstance()
                .getSystemUserTransactionDao());
        Assert.isNotNull(DaoManager.getDb4ODaoInstance().getTagDao());
    }

    @Test
    public void testGeneralDao()
    {

    }
}
