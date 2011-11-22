package at.owlsoft.owl.dao.db4o;

import at.owlsoft.owl.dao.ITagDao;
import at.owlsoft.owl.model.media.Tag;

public class TagDao extends Db4oDaoBase<Tag> implements ITagDao
{
    private static TagDao _instance;

    static TagDao getInstance()
    {
        if (_instance == null)
        {
            _instance = new TagDao();
        }

        return _instance;
    }

    private TagDao()
    {
        super(Tag.class);
        // TODO Auto-generated constructor stub
    }
}
