package at.owlsoft.owl.dao.db4o.model;

import com.db4o.ObjectContainer;

public class SystemUserTransactionDao extends GeneralDb4oDaoBase<Object>
{
    private static SystemUserTransactionDao _factory;

    public static SystemUserTransactionDao getInstance(ObjectContainer db)
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
        super(db);
        // TODO Auto-generated constructor stub
    }

}
