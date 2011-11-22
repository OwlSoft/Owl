package at.owlsoft.owl.dao.db4o;

import at.owlsoft.owl.dao.ITagDao;
import at.owlsoft.owl.model.media.Tag;

public class TagDao extends Db4oDaoBase<Tag> implements ITagDao
{
    protected TagDao(Db4ODaoFactory factory)
    {
        super(factory, Tag.class);
    }

}
