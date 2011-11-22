package at.owlsoft.owl.usecases;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import at.owlsoft.owl.business.ControllerBase;
import at.owlsoft.owl.business.OwlApplicationContext;
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

public class RentalController extends ControllerBase
{
    private Rental                  _rental;
    private List<ValidationMessage> _messages;

    public RentalController(OwlApplicationContext context)
    {
        super(context);
    }

    public void newRental()
    {
        _rental = new Rental(new Date());
        _messages = null;
        // TODO where comes the creator from??
        // _rental.setCreator(creator)
    }

    /**
     * 
     * @param medium
     * @return If no warnings are found returns empty List.
     */
    public List<ValidationMessage> setMediumExemplar(Medium medium)
    {
        boolean rentableFound = false;

        if (_rental == null)
        {
            newRental();
        }

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
                // TODO Replace dummy endDate with value from config
                _rental.setEndDate(new Date());
                rentableFound = true;
                break;
            }
        }

        validate(ValidationMode.NotStrict);
        if (!rentableFound)
        {
            _messages.add(new ValidationMessage("No rentable copy found.",
                    ValidationMessageStatus.Error));
        }
        return _messages;
    }

    public List<ValidationMessage> setCustomer(SystemUser customer)
    {
        if (_rental == null)
        {
            newRental();
        }
        _rental.setCustomer(customer);
        validate(ValidationMode.NotStrict);
        return _messages;
    }

    public List<ValidationMessage> save()
    {
        if (_rental == null)
        {
            newRental();
        }
        if (validate(ValidationMode.Strict))
        {
            saveRental();
        }
        return _messages;
    }

    private void saveRental()
    {

        ActivityStatusEntry ase = new ActivityStatusEntry();
        ase.setActivityStatus(ActivityStatus.Open);
        ase.setDate(new Date());
        _rental.addActivityStatusEntry(ase);
        DaoManager.getInstance().getRentalDao().store(_rental);
        updateMediumExemplar();
        _rental = null;

    }

    private void updateMediumExemplar()
    {
        MediumExemplarStatusEntry mese = new MediumExemplarStatusEntry();
        mese.setMediumExemplarStatus(MediumExemplarStatus.Rented);
        mese.setDate(new Date());
        _rental.getMediumExemplar().addMediumExemplarStatusEntry(mese);
        mese.setMediumExemplar(_rental.getMediumExemplar());
        _rental.getMediumExemplar().addActivity(_rental);
        DaoManager.getInstance().getMediumExemplarDao()
                .store(_rental.getMediumExemplar());

    }

    /**
     * 
     * @param mode
     * @return true only if _messages has no ValidationMessage with
     *         ValidationMessageStatus.Error
     */
    private boolean validate(ValidationMode mode)
    {
        boolean hasNoError = true;

        _messages = new ArrayList<ValidationMessage>();

        SystemUser renter = _rental.getCustomer();
        SystemUserStatus renterStatus = renter.getSystemUserStatusEntry(
                renter.getSystemUserStatusEntryCount() - 1)
                .getSystemUserStatus();

        if (!renterStatus.equals(SystemUserStatus.Active))
        {
            String message = "Customer is inactive with state "
                    + renterStatus.name();
            ValidationMessage vm = new ValidationMessage(message,
                    ValidationMessageStatus.Error);
            _messages.add(vm);
            hasNoError = false;
        }

        if (_rental.getMediumExemplar() == null)
        {
            String message = "No copy choosen.";
            ValidationMessage vm = new ValidationMessage(message,
                    ValidationMessageStatus.Error);
            _messages.add(vm);
            hasNoError = false;
        }

        return hasNoError;

    }

}
