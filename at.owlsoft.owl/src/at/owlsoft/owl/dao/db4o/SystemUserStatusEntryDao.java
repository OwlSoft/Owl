package at.owlsoft.owl.dao.db4o;

import at.owlsoft.owl.dao.IDao.ISystemUserStatusEntryDao;
import at.owlsoft.owl.model.user.SystemUserStatusEntry;

import com.db4o.ObjectContainer;

public class SystemUserStatusEntryDao extends
        GeneralDb4oDaoBase<SystemUserStatusEntry> implements
        ISystemUserStatusEntryDao
{

    private static SystemUserStatusEntryDao _factory;

    static SystemUserStatusEntryDao getInstance(ObjectContainer db)
    {
        if (_factory == null)
        {
            if (db != null)
            {
                _factory = new SystemUserStatusEntryDao(db);
            }
            else
            {
                throw new NoDatabaseConfiguredException();
            }
        }

        return _factory;
    }

    private SystemUserStatusEntryDao(ObjectContainer db)
    {
        super(db);
        // TODO Auto-generated constructor stub
    }

}
