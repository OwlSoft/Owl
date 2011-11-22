package at.owlsoft.owl.dao.db4o;

import at.owlsoft.owl.dao.IMediumExemplarDao;
import at.owlsoft.owl.model.media.MediumExemplar;

public class MediumExemplarDao extends Db4oDaoBase<MediumExemplar> implements
        IMediumExemplarDao

{
    private static MediumExemplarDao _instance;

    static MediumExemplarDao getInstance()
    {
        if (_instance == null)
        {

            _instance = new MediumExemplarDao();

        }

        return _instance;
    }

    private MediumExemplarDao()
    {
        super(MediumExemplar.class);
        // TODO Auto-generated constructor stub
    }
}
