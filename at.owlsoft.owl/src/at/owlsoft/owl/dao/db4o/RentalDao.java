package at.owlsoft.owl.dao.db4o;

import at.owlsoft.owl.dao.IRentalDao;
import at.owlsoft.owl.model.accounting.Rental;

public class RentalDao extends Db4oDaoBase<Rental> implements IRentalDao

{
    private static RentalDao _instance;

    static RentalDao getInstance()
    {
        if (_instance == null)
        {

            _instance = new RentalDao();

        }

        return _instance;
    }

    private RentalDao()
    {
        super(Rental.class);
        // TODO Auto-generated constructor stub
    }
}
