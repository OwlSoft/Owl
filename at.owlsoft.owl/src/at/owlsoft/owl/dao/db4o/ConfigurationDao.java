package at.owlsoft.owl.dao.db4o;

import at.owlsoft.owl.dao.IConfigurationDao;
import at.owlsoft.owl.model.Configuration;

public class ConfigurationDao extends Db4oDaoBase<Configuration> implements
        IConfigurationDao
{
    private static ConfigurationDao _instance;

    static ConfigurationDao getInstance()
    {
        if (_instance == null)
        {

            _instance = new ConfigurationDao();

        }

        return _instance;
    }

    private ConfigurationDao()
    {
        super(Configuration.class);
    }

}
