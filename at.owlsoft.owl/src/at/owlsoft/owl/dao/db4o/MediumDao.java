package at.owlsoft.owl.dao.db4o;

import at.owlsoft.owl.dao.IMediumDao;
import at.owlsoft.owl.model.media.Medium;

public class MediumDao extends Db4oDaoBase<Medium> implements IMediumDao
{
    MediumDao(Db4ODaoFactory factory)
    {
        super(factory, Medium.class);
    }
}
