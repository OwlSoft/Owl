package at.owlsoft.owl.dao.db4o;

import at.owlsoft.owl.dao.ISystemUserDao;
import at.owlsoft.owl.model.user.SystemUser;

public class SystemUserDao extends Db4oDaoBase<SystemUser> implements
        ISystemUserDao
{
    private static SystemUserDao _instance;

    static SystemUserDao getInstance()
    {
        if (_instance == null)
        {
            _instance = new SystemUserDao();
        }

        return _instance;
    }

    private SystemUserDao()
    {
        super(SystemUser.class);
        // TODO Auto-generated constructor stub
    }

}
