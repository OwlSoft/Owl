package at.owlsoft.owl.dao.db4o.model;

import com.db4o.ObjectContainer;

public class MediumExemplarDao extends GeneralDb4oDaoBase<Object>
{
    private static MediumExemplarDao _factory;

    public static MediumExemplarDao getInstance(ObjectContainer db)
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
