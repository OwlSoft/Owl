package at.owlsoft.owl.dao.db4o.model;

import com.db4o.ObjectContainer;

public class ActivityDao extends GeneralDb4oDaoBase<Object>
{

    private static ActivityDao _factory;

    public static ActivityDao getInstance(ObjectContainer db)
    {
        if (_factory == null)
        {
            if (db != null)
            {
                _factory = new ActivityDao(db);
            }
            else
            {
                throw new NoDatabaseConfiguredException();
            }
        }

        return _factory;
    }

    private ActivityDao(ObjectContainer db)
    {
        super(db);
        // TODO Auto-generated constructor stub
    }

}
