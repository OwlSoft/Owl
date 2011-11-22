package at.owlsoft.owl.dao.db4o;

import at.owlsoft.owl.dao.ISystemUserDao;
import at.owlsoft.owl.model.user.SystemUser;

public class SystemUserDao extends Db4oDaoBase<SystemUser> implements
        ISystemUserDao
{
    protected SystemUserDao(Db4ODaoFactory factory)
    {
        super(factory, SystemUser.class);
    }

}
