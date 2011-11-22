package at.owlsoft.owl.dao.db4o;

import at.owlsoft.owl.dao.ISystemUserTransactionDao;
import at.owlsoft.owl.model.user.SystemUserTransaction;

public class SystemUserTransactionDao extends
        Db4oDaoBase<SystemUserTransaction> implements ISystemUserTransactionDao
{

    SystemUserTransactionDao(Db4ODaoFactory factory)
    {
        super(factory, SystemUserTransaction.class);
    }

}
