package at.owlsoft.owl.dao.db4o;

import at.owlsoft.owl.dao.IDao.IGeneralDao;

import com.db4o.ObjectContainer;

public class GeneralDb4ODao extends GeneralDb4oDaoBase<Object> implements
        IGeneralDao
{

    private static GeneralDb4ODao _factory;

    static GeneralDb4ODao getInstance(ObjectContainer db)
    {
        if (_factory == null)
        {
            if (db != null)
            {
                _factory = new GeneralDb4ODao(db);
            }
            else
            {
                throw new NoDatabaseConfiguredException();
            }
        }

        return _factory;
    }

    private GeneralDb4ODao(ObjectContainer db)
    {
        super(db);
        // TODO Auto-generated constructor stub
    }

    private ObjectContainer _db;

}
