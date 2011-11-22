package at.owlsoft.owl.usecases;

import java.util.Date;

import at.owlsoft.owl.dao.DaoManager;
import at.owlsoft.owl.model.accounting.ActivityStatus;
import at.owlsoft.owl.model.accounting.ActivityStatusEntry;
import at.owlsoft.owl.model.accounting.Reservation;
import at.owlsoft.owl.model.media.Medium;
import at.owlsoft.owl.model.user.SystemUser;
import at.owlsoft.owl.model.user.SystemUserStatus;

public class ReservationController
{
    Reservation _reservation;

    public void newReservation()
    {
        _reservation = new Reservation();
    }

    public void setMedium(Medium medium)
    {
        _reservation.setMedium(medium);
    }

    public void setCustomer(SystemUser customer)
    {
        _reservation.setCustomer(customer);
    }

    public void setReservationStartDate(Date startDate)
    {
        _reservation.setStartDate(startDate);
    }

    public void save() throws ReservationNotPossibleException,
            InvalidUserException, RequiredFieldsNotFilledException
    {
        validate();
        saveReservation();
    }

    private void validate() throws ReservationNotPossibleException,
            InvalidUserException, RequiredFieldsNotFilledException
    {
        // Check if all fields are set
        if (_reservation.getCustomer() == null
                || _reservation.getMedium() == null)
        {
            throw new RequiredFieldsNotFilledException(
                    "You need to fill in the customer and medium.");
        }

        if (_reservation.getStartDate() == null)
        {
            _reservation.setStartDate(new Date());
        }

        if (_reservation.getDesiredDuration() == 0)
        {
            // TODO get max reservation time from configuration
            _reservation.setDesiredDuration(14);
        }

        // Check if user can make a reservation
        SystemUser customer = _reservation.getCustomer();
        SystemUserStatus reservatorStatus = customer
                .getLastSystemUserStatusEntry().getSystemUserStatus();

        if (!reservatorStatus.equals(SystemUserStatus.Active))
        {
            throw new InvalidUserException(reservatorStatus.name());
        }

    }

    private void saveReservation()
    {
        ActivityStatusEntry ase = new ActivityStatusEntry();
        ase.setActivityStatus(ActivityStatus.Open);
        ase.setDate(new Date());
        _reservation.addActivityStatusEntry(ase);
        DaoManager.getInstance().getReservationDao().store(_reservation);
    }
}
