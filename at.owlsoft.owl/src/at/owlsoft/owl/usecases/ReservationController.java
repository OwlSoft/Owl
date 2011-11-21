package at.owlsoft.owl.usecases;

import java.util.Date;

import at.owlsoft.owl.dao.DaoManager;
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

    public void newRental()
    {
        _reservation = new Reservation();
    }

    public void setMediumExemplar(Medium medium)
    {
        for (MediumExemplar exemplare : medium.getMediumExemplars())
        {
            // validate whether reservation possible or not
            if (!exemplare
                    .getMediumExemplarStatusEntry(
                            exemplare.getMediumExemplarStatusEntryCount() - 1)
                    .getMediumExemplarStatus()
                    .equals(MediumExemplarStatus.StockItem))
            {
                _reservation.setMediumExemplar(exemplare);
                break;
            }
        }

    }

    public void setCustomer(SystemUser customer)
    {
        _reservation.setCustomer(customer);
    }

    public void save() throws RentalNotAllowedException,
            NoRentableCopyException
    {
        validate();
        saveReservation();
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

    private void validate() throws RentalNotAllowedException
    {
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

}
