package at.owlsoft.owl.dao.db4o;

import at.owlsoft.owl.dao.IReservationDao;
import at.owlsoft.owl.model.accounting.Reservation;

public class ReservationDao extends Db4oDaoBase<Reservation> implements
        IReservationDao

{
    ReservationDao(Db4ODaoFactory factory)
    {
        super(factory, Reservation.class);
    }
}
