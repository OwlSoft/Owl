package at.owlsoft.owl.dao.db4o;

import at.owlsoft.owl.dao.ISystemUserStatusEntryDao;
import at.owlsoft.owl.model.user.SystemUserStatusEntry;

public class SystemUserStatusEntryDao extends
        Db4oDaoBase<SystemUserStatusEntry> implements ISystemUserStatusEntryDao
{

    private static SystemUserStatusEntryDao _instance;

    static SystemUserStatusEntryDao getInstance()
    {
        if (_instance == null)
        {

            _instance = new SystemUserStatusEntryDao();

        }

        return _instance;
    }

    private SystemUserStatusEntryDao()
    {
        super(SystemUserStatusEntry.class);
        // TODO Auto-generated constructor stub
    }
}
