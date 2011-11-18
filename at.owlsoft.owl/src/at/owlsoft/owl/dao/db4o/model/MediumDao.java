package at.owlsoft.owl.dao.db4o.model;

import com.db4o.ObjectContainer;

public class MediumDao extends GeneralDb4oDaoBase<Object>
{
    private static MediumDao _factory;

    public static MediumDao getInstance(ObjectContainer db)
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
