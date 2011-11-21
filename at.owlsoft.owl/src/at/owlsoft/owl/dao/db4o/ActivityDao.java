package at.owlsoft.owl.dao.db4o;

import at.owlsoft.owl.dao.IActivityDao;
import at.owlsoft.owl.model.accounting.Activity;

import com.db4o.ObjectContainer;

public class ActivityDao extends Db4oDaoBase<Activity> implements IActivityDao
{

    private static ActivityDao _factory;

    static ActivityDao getInstance(ObjectContainer db)
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
