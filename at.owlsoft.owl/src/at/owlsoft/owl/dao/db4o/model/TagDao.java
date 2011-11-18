package at.owlsoft.owl.dao.db4o.model;

import com.db4o.ObjectContainer;

public class TagDao extends GeneralDb4oDaoBase<Object>
{
    private static TagDao _factory;

    public static TagDao getInstance(ObjectContainer db)
    {
        if (_factory == null)
        {
            if (db != null)
            {
                _factory = new TagDao(db);
            }
            else
            {
                throw new NoDatabaseConfiguredException();
            }
        }

        return _factory;
    }

    private TagDao(ObjectContainer db)
    {
        super(db);
        // TODO Auto-generated constructor stub
    }

}
