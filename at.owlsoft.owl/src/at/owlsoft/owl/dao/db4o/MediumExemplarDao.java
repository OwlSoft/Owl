package at.owlsoft.owl.dao.db4o;

import at.owlsoft.owl.dao.IDao.IMediumExemplarDao;
import at.owlsoft.owl.model.media.MediumExemplar;

import com.db4o.ObjectContainer;

public class MediumExemplarDao extends GeneralDb4oDaoBase<MediumExemplar>
        implements IMediumExemplarDao

{
    private static MediumExemplarDao _factory;

    static MediumExemplarDao getInstance(ObjectContainer db)
    {
        if (_factory == null)
        {
            if (db != null)
            {
                _factory = new MediumExemplarDao(db);
            }
            else
            {
                throw new NoDatabaseConfiguredException();
            }
        }

        return _factory;
    }

    private MediumExemplarDao(ObjectContainer db)
    {
        super(db);
        // TODO Auto-generated constructor stub
    }

}
