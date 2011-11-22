package at.owlsoft.owl.dao.db4o;

import at.owlsoft.owl.dao.IRentalDao;
import at.owlsoft.owl.model.accounting.Rental;

public class RentalDao extends Db4oDaoBase<Rental> implements IRentalDao

{
    RentalDao(Db4ODaoFactory factory)
    {
        super(factory, Rental.class);
    }
}
