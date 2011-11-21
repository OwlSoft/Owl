package at.owlsoft.owl.dao.db4o;

import at.owlsoft.owl.dao.ITagDao;
import at.owlsoft.owl.model.media.Tag;

import com.db4o.ObjectContainer;

public class TagDao extends Db4oDaoBase<Tag> implements ITagDao
{
    private static TagDao _factory;

    static TagDao getInstance(ObjectContainer db)
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
