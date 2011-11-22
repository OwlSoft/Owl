package at.owlsoft.owl.dao.db4o;

import at.owlsoft.owl.dao.IMediumDao;
import at.owlsoft.owl.model.media.Medium;

public class MediumDao extends Db4oDaoBase<Medium> implements IMediumDao
{
    private static MediumDao _instance;

    static MediumDao getInstance()
    {
        if (_instance == null)
        {
            _instance = new MediumDao();
        }

        return _instance;
    }

    private MediumDao()
    {
        super(Medium.class);
    }
}
