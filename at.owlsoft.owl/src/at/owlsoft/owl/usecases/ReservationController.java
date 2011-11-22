package at.owlsoft.owl.usecases;

import java.util.Calendar;
import java.util.Date;

import at.owlsoft.owl.dao.DaoManager;
import at.owlsoft.owl.model.accounting.Activity;
import at.owlsoft.owl.model.accounting.ActivityStatus;
import at.owlsoft.owl.model.accounting.ActivityStatusEntry;
import at.owlsoft.owl.model.accounting.Reservation;
import at.owlsoft.owl.model.media.Medium;
import at.owlsoft.owl.model.media.MediumExemplar;
import at.owlsoft.owl.model.media.MediumExemplarStatus;
import at.owlsoft.owl.model.media.MediumExemplarStatusEntry;
import at.owlsoft.owl.model.user.SystemUser;
import at.owlsoft.owl.model.user.SystemUserStatus;

public class ReservationController
{
    Reservation _reservation;

    public void newReservation()
    {
        _reservation = new Reservation();
    }

    public void setMediumExemplar(Medium medium)
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

    public void save() throws RentalNotAllowedException,
            NoRentableCopyException
    {
        validate();
        saveReservation();
    }

    private void validate() throws RentalNotAllowedException
    {
        // Check if all fields are set
        if (_reservation.getCustomer() == null
                || _reservation.getMedium() == null
                || _reservation.getStartDate() == null)
        {
            //TODO Throw some exception about not all fields saved
        }

        if (_reservation.getDesiredDuration() == 0) {
            //TODO get max reservation time from configuration
        }
        
        // Check if there are any copies free on that date

        // Amount of copies available at specified date
        int copiesAvailable = 0;
        Medium medium = _reservation.getMedium();

        // Count number of available copies
        for (MediumExemplar copy : medium.getMediumExemplars())
        {
            MediumExemplarStatus mes = copy.getMediumExemplarStatusEntry(
                    copy.getMediumExemplarStatusEntryCount() - 1)
                    .getMediumExemplarStatus();
            // If it's not a stock item, it might be available
            if (mes != MediumExemplarStatus.StockItem)
            {
                // If it was rented, check if it will be available at that date
                if (mes == MediumExemplarStatus.Rented)
                {
                    if (copy.getLastRental().getEndDate()
                            .before(_reservation.getStartDate()))
                    {
                        // Rental will be over before reservation is due
                        copiesAvailable++;
                    }
                }
                else
                {
                    // Copy is not rented, will be available
                    copiesAvailable++;
                }
            }
        }

        // Now check number of reservations within that span of time
        // Counting variable
        int reservedInTimespam = 0;
        
        Calendar newReservationStartDate = Calendar.getInstance();
        newReservationStartDate.setTime(_reservation.getStartDate());


        
        // Iterate over all reservations
        for (Activity activity : medium.getActivities())
        {
            // Cast to reservation, because a medium can only have Reservations as Activity
            Reservation reservation = (Reservation) activity;
            Calendar endDate = Calendar.getInstance();
            endDate.setTime(reservation.getStartDate());
            endDate.add(Calendar.DATE, reservation.getDesiredDuration());
            if (endDate.before())
        }

        SystemUser customer = _reservation.getCustomer();
        SystemUserStatus renterStatus = customer.getSystemUserStatusEntry(
                customer.getSystemUserStatusEntryCount() - 1)
                .getSystemUserStatus();

        if (!renterStatus.equals(SystemUserStatus.Active))
        {
            throw new InvalidUserException(renterStatus.name());
        }
        if (_reservation.getMediumExemplar() == null)
        {
            throw new NoReserveableCopyException();
        }
    }

    private void saveReservation()
    {
        ActivityStatusEntry ase = new ActivityStatusEntry();
        ase.setActivityStatus(ActivityStatus.Open);
        ase.setDate(new Date());
        _reservation.addActivityStatusEntry(ase);
        DaoManager.getInstance().getReservationDao().store(_reservation);
        updateMediumExemplar();

    }

    private void updateMediumExemplar()
    {
        MediumExemplarStatusEntry mese = new MediumExemplarStatusEntry();
        mese.setMediumExemplarStatus(MediumExemplarStatus.Rented);
        mese.setDate(new Date());
        _reservation.getMediumExemplar().addMediumExemplarStatusEntry(mese);
        mese.setMediumExemplar(_reservation.getMediumExemplar());
        DaoManager.getInstance().getMediumExemplarDao()
                .store(_reservation.getMediumExemplar());

    }
}
