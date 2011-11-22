package at.owlsoft.owl.dao.db4o;

import at.owlsoft.owl.dao.IMediumExemplarDao;
import at.owlsoft.owl.model.media.MediumExemplar;

public class MediumExemplarDao extends Db4oDaoBase<MediumExemplar> implements
        IMediumExemplarDao

{

    MediumExemplarDao(Db4ODaoFactory factory)
    {
        super(factory, MediumExemplar.class);
    }

}
