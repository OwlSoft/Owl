package at.owlsoft.owl.dao.db4o;

import at.owlsoft.owl.dao.ISystemUserDao;
import at.owlsoft.owl.model.user.SystemUser;

import com.db4o.ObjectContainer;

public class SystemUserDao extends Db4oDaoBase<SystemUser> implements
        ISystemUserDao
{
    private static SystemUserDao _factory;

    static SystemUserDao getInstance(ObjectContainer db)
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
        super(SystemUser.class, db);
        // TODO Auto-generated constructor stub
    }

}
