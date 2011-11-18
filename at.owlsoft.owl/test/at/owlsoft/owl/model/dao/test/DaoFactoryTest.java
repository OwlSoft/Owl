package at.owlsoft.owl.model.dao.test;

import org.junit.Test;

import EDU.purdue.cs.bloat.util.Assert;
import at.owlsoft.owl.dao.Db4ODaoFactory;

public class DaoFactoryTest
{
    @Test
    public void testDaoSingelton()
    {

        Assert.isNotNull(Db4ODaoFactory.getInstance());
        Assert.isNotNull(Db4ODaoFactory.getInstance().getConfigurationDao());
        Assert.isNotNull(Db4ODaoFactory.getInstance().getGeneralDao());
        Assert.isNotNull(Db4ODaoFactory.getInstance().getMediumDao());
        Assert.isNotNull(Db4ODaoFactory.getInstance().getMediumExemplarDao());
        Assert.isNotNull(Db4ODaoFactory.getInstance().getRentalDao());
        Assert.isNotNull(Db4ODaoFactory.getInstance().getSystemUserDao());
        Assert.isNotNull(Db4ODaoFactory.getInstance()
                .getSystemUserStatusEntryDao());
        Assert.isNotNull(Db4ODaoFactory.getInstance()
                .getSystemUserTransactionDao());
        Assert.isNotNull(Db4ODaoFactory.getInstance().getTagDao());
    }

    @Test
    public void testGeneralDao()
    {

    }
}
