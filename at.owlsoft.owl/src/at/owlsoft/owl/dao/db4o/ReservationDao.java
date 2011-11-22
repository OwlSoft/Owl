package at.owlsoft.owl.dao.db4o;

import at.owlsoft.owl.dao.IReservationDao;
import at.owlsoft.owl.model.accounting.Reservation;

public class ReservationDao extends Db4oDaoBase<Reservation> implements
        IReservationDao

{
    private static ReservationDao _instance;

    static ReservationDao getInstance()
    {
        if (_instance == null)
        {

            _instance = new ReservationDao();

        }

        return _instance;
    }

    private ReservationDao()
    {
        super(Reservation.class);
        // TODO Auto-generated constructor stub
    }
}
