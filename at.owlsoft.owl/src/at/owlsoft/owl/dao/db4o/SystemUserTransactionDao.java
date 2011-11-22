package at.owlsoft.owl.dao.db4o;

import at.owlsoft.owl.dao.ISystemUserTransactionDao;
import at.owlsoft.owl.model.user.SystemUserTransaction;

import com.db4o.ObjectContainer;

public class SystemUserTransactionDao extends
        Db4oDaoBase<SystemUserTransaction> implements ISystemUserTransactionDao
{
    private static SystemUserTransactionDao _factory;

    static SystemUserTransactionDao getInstance(ObjectContainer db)
    {
        if (_factory == null)
        {
            if (db != null)
            {
                _factory = new SystemUserTransactionDao(db);
            }
            else
            {
                throw new NoDatabaseConfiguredException();
            }
        }

        return _factory;
    }

    private SystemUserTransactionDao(ObjectContainer db)
    {
        super(SystemUserTransaction.class, db);
        // TODO Auto-generated constructor stub
    }

}
