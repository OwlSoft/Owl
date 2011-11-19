package at.owlsoft.owl.model.dao.test;

import org.junit.Test;

import EDU.purdue.cs.bloat.util.Assert;
import at.owlsoft.owl.dao.DaoFactory;

public class DaoFactoryTest
{
    @Test
    public void testDaoSingelton()
    {

        Assert.isNotNull(DaoFactory.getDb4ODaoInstance());
        Assert.isNotNull(DaoFactory.getDb4ODaoInstance().getConfigurationDao());
        Assert.isNotNull(DaoFactory.getDb4ODaoInstance().getGeneralDao());
        Assert.isNotNull(DaoFactory.getDb4ODaoInstance().getMediumDao());
        Assert.isNotNull(DaoFactory.getDb4ODaoInstance().getMediumExemplarDao());
        Assert.isNotNull(DaoFactory.getDb4ODaoInstance().getRentalDao());
        Assert.isNotNull(DaoFactory.getDb4ODaoInstance().getSystemUserDao());
        Assert.isNotNull(DaoFactory.getDb4ODaoInstance()
                .getSystemUserStatusEntryDao());
        Assert.isNotNull(DaoFactory.getDb4ODaoInstance()
                .getSystemUserTransactionDao());
        Assert.isNotNull(DaoFactory.getDb4ODaoInstance().getTagDao());
    }

    @Test
    public void testGeneralDao()
    {

    }
}
