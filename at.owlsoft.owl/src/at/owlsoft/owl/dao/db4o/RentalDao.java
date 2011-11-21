package at.owlsoft.owl.dao.db4o;

import at.owlsoft.owl.dao.IRentalDao;
import at.owlsoft.owl.model.accounting.Rental;

import com.db4o.ObjectContainer;

public class RentalDao extends Db4oDaoBase<Rental> implements IRentalDao

{
    private static RentalDao _factory;

    static RentalDao getInstance(ObjectContainer db)
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
