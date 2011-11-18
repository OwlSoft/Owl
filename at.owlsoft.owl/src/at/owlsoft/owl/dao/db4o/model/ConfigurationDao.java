package at.owlsoft.owl.dao.db4o.model;

import com.db4o.ObjectContainer;

public class ConfigurationDao extends GeneralDb4oDaoBase<Object>
{
    private static ConfigurationDao _factory;

    public static ConfigurationDao getInstance(ObjectContainer db)
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
