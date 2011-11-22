package at.owlsoft.owl.dao.db4o;

import at.owlsoft.owl.dao.IConfigurationDao;
import at.owlsoft.owl.model.Configuration;

import com.db4o.ObjectContainer;

public class ConfigurationDao extends Db4oDaoBase<Configuration> implements
        IConfigurationDao
{
    private static ConfigurationDao _factory;

    static ConfigurationDao getInstance(ObjectContainer db)
    {
        if (_factory == null)
        {
            if (db != null)
            {
                _factory = new ConfigurationDao(db);
            }
            else
            {
                throw new NoDatabaseConfiguredException();
            }
        }

        return _factory;
    }

    private ConfigurationDao(ObjectContainer db)
    {
        super(Configuration.class, db);
    }

}
