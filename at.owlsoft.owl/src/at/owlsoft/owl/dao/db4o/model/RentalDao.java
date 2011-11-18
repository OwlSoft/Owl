package at.owlsoft.owl.dao.db4o.model;

import com.db4o.ObjectContainer;

public class RentalDao extends GeneralDb4oDaoBase<Object>
{
    private static RentalDao _factory;

    public static RentalDao getInstance(ObjectContainer db)
    {
        if (_factory == null)
        {
            if (db != null)
            {
                _factory = new RentalDao(db);
            }
            else
            {
                throw new NoDatabaseConfiguredException();
            }
        }

        return _factory;
    }

    private RentalDao(ObjectContainer db)
    {
        super(db);
        // TODO Auto-generated constructor stub
    }

}
