package at.owlsoft.owl.usecases;

import java.util.Date;

import at.owlsoft.owl.dao.DaoManager;
import at.owlsoft.owl.model.accounting.ActivityStatus;
import at.owlsoft.owl.model.accounting.ActivityStatusEntry;
import at.owlsoft.owl.model.accounting.Rental;
import at.owlsoft.owl.model.media.Medium;
import at.owlsoft.owl.model.media.MediumExemplar;
import at.owlsoft.owl.model.media.MediumExemplarStatus;
import at.owlsoft.owl.model.media.MediumExemplarStatusEntry;
import at.owlsoft.owl.model.user.SystemUser;
import at.owlsoft.owl.model.user.SystemUserStatus;

public class RentalController
{
    Rental _rental;

    public void newRental()
    {
        _rental = new Rental();
        // TODO where comes the creator from??
        // _rental.setCreator(creator)
    }

    public void setMediumExemplar(Medium medium)
    {
        for (MediumExemplar exemplare : medium.getMediumExemplars())
        {
            // validate whether rentable or not
            if (exemplare
                    .getMediumExemplarStatusEntry(
                            exemplare.getMediumExemplarStatusEntryCount() - 1)
                    .getMediumExemplarStatus()
                    .equals(MediumExemplarStatus.Rentable))
            {
                _rental.setMediumExemplar(exemplare);
                break;
            }
        }

    }

    public void setCustomer(SystemUser customer)
    {
        _rental.setCustomer(customer);
    }

    public void save() throws RentalNotAllowedException,
            NoRentableCopyException
    {
        validate();
        saveRental();
    }

    private void saveRental()
    {
        ActivityStatusEntry ase = new ActivityStatusEntry();
        ase.setActivityStatus(ActivityStatus.Open);
        ase.setDate(new Date());
        _rental.addActivityStatusEntry(ase);
        DaoManager.getInstance().getRentalDao().store(_rental);
        updateMediumExemplar();

    }

    private void updateMediumExemplar()
    {
        MediumExemplarStatusEntry mese = new MediumExemplarStatusEntry();
        mese.setMediumExemplarStatus(MediumExemplarStatus.Rented);
        mese.setDate(new Date());
        _rental.getMediumExemplar().addMediumExemplarStatusEntry(mese);
        mese.setMediumExemplar(_rental.getMediumExemplar());
        DaoManager.getInstance().getMediumExemplarDao()
                .store(_rental.getMediumExemplar());

    }

    private void validate() throws RentalNotAllowedException
    {
        SystemUser renter = _rental.getCustomer();
        SystemUserStatus renterStatus = renter.getSystemUserStatusEntry(
                renter.getSystemUserStatusEntryCount() - 1)
                .getSystemUserStatus();

        if (!renterStatus.equals(SystemUserStatus.Active))
        {
            throw new InvalidUserException(renterStatus.name());
        }
        if (_rental.getMediumExemplar() == null)
        {
            throw new NoRentableCopyException();
        }

    }

}
