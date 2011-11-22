package at.owlsoft.owl.dao.db4o;

import at.owlsoft.owl.dao.ISystemUserStatusEntryDao;
import at.owlsoft.owl.model.user.SystemUserStatusEntry;

public class SystemUserStatusEntryDao extends
        Db4oDaoBase<SystemUserStatusEntry> implements ISystemUserStatusEntryDao
{

    protected SystemUserStatusEntryDao(Db4ODaoFactory factory)
    {
        super(factory, SystemUserStatusEntry.class);
    }

}
