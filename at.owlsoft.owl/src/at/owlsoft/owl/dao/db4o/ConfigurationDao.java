package at.owlsoft.owl.dao.db4o;

import at.owlsoft.owl.dao.IDao.IConfigurationDao;
import at.owlsoft.owl.model.Configuration;

import com.db4o.ObjectContainer;

public class ConfigurationDao extends GeneralDb4oDaoBase<Configuration>
        implements IConfigurationDao
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
        super(db);
        // TODO Auto-generated constructor stub
    }

}
