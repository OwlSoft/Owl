package at.owlsoft.owl.dao.db4o;

import at.owlsoft.owl.dao.IConfigurationDao;
import at.owlsoft.owl.model.Configuration;

public class ConfigurationDao extends Db4oDaoBase<Configuration> implements
        IConfigurationDao
{
    ConfigurationDao(Db4ODaoFactory factory)
    {
        super(factory, Configuration.class);
    }
}
