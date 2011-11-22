package at.owlsoft.owl.dao.db4o;

import at.owlsoft.owl.dao.IActivityDao;
import at.owlsoft.owl.model.accounting.Activity;

public class ActivityDao extends Db4oDaoBase<Activity> implements IActivityDao
{

    private static ActivityDao _instance;

    static ActivityDao getInstance()
    {
        if (_instance == null)
        {
            _instance = new ActivityDao();
        }

        return _instance;
    }

    private ActivityDao()
    {
        super(Activity.class);
    }

}
