package at.owlsoft.owl.dao.db4o;

import at.owlsoft.owl.dao.ISystemUserTransactionDao;
import at.owlsoft.owl.model.user.SystemUserTransaction;

public class SystemUserTransactionDao extends
        Db4oDaoBase<SystemUserTransaction> implements ISystemUserTransactionDao
{
    private static SystemUserTransactionDao _instance;

    static SystemUserTransactionDao getInstance()
    {
        if (_instance == null)
        {
            _instance = new SystemUserTransactionDao();
        }

        return _instance;
    }

    private SystemUserTransactionDao()
    {
        super(SystemUserTransaction.class);
        // TODO Auto-generated constructor stub
    }

}
