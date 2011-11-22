package at.owlsoft.owl.dao.db4o;

import at.owlsoft.owl.dao.IActivityDao;
import at.owlsoft.owl.model.accounting.Activity;

public class ActivityDao extends Db4oDaoBase<Activity> implements IActivityDao
{

    protected ActivityDao(Db4ODaoFactory factory)
    {
        super(factory, Activity.class);
    }

}
