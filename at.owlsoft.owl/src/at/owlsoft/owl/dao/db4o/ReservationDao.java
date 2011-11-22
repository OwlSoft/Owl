package at.owlsoft.owl.dao.db4o;

import at.owlsoft.owl.dao.IReservationDao;
import at.owlsoft.owl.model.accounting.Reservation;

import com.db4o.ObjectContainer;

public class ReservationDao extends Db4oDaoBase<Reservation> implements
        IReservationDao

{
    private static ReservationDao _factory;

    static ReservationDao getInstance(ObjectContainer db)
    {
        if (_factory == null)
        {
            if (db != null)
            {
                _factory = new ReservationDao(db);
            }
            else
            {
                throw new NoDatabaseConfiguredException();
            }
        }

        return _factory;
    }

    private ReservationDao(ObjectContainer db)
    {
        super(Reservation.class, db);
        // TODO Auto-generated constructor stub
    }

}
