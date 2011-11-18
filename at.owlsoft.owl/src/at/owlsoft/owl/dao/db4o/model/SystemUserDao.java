package at.owlsoft.owl.dao.db4o.model;

import com.db4o.ObjectContainer;

public class SystemUserDao extends GeneralDb4oDaoBase<Object>
{
    private static SystemUserDao _factory;

    public static SystemUserDao getInstance(ObjectContainer db)
    {
        if (_factory == null)
        {
            if (db != null)
            {
                _factory = new SystemUserDao(db);
            }
            else
            {
                throw new NoDatabaseConfiguredException();
            }
        }

        return _factory;
    }

    private SystemUserDao(ObjectContainer db)
    {
        super(db);
        // TODO Auto-generated constructor stub
    }

}
