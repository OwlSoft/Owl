package at.owlsoft.owl.dao.db4o;

import at.owlsoft.owl.dao.IDao.IMediumDao;
import at.owlsoft.owl.model.media.Medium;

import com.db4o.ObjectContainer;

public class MediumDao extends GeneralDb4oDaoBase<Medium> implements IMediumDao
{
    private static MediumDao _factory;

    static MediumDao getInstance(ObjectContainer db)
    {
        if (_factory == null)
        {
            if (db != null)
            {
                _factory = new MediumDao(db);
            }
            else
            {
                throw new NoDatabaseConfiguredException();
            }
        }

        return _factory;
    }

    private MediumDao(ObjectContainer db)
    {
        super(db);
        // TODO Auto-generated constructor stub
    }

}
