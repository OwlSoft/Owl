package at.owlsoft.owl.usecases;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import at.owlsoft.owl.business.ControllerBase;
import at.owlsoft.owl.business.OwlApplicationContext;
import at.owlsoft.owl.dao.DaoManager;
import at.owlsoft.owl.model.IDefaultRoles;
import at.owlsoft.owl.model.NoPermissionException;
import at.owlsoft.owl.model.accounting.ActivityStatus;
import at.owlsoft.owl.model.accounting.ActivityStatusEntry;
import at.owlsoft.owl.model.accounting.Rental;
import at.owlsoft.owl.model.media.Medium;
import at.owlsoft.owl.model.media.MediumExemplar;
import at.owlsoft.owl.model.media.MediumExemplarStatus;
import at.owlsoft.owl.model.media.MediumExemplarStatusEntry;
import at.owlsoft.owl.model.messaging.NewExternalRentalMessage;
import at.owlsoft.owl.model.user.SystemUser;
import at.owlsoft.owl.model.user.SystemUserStatus;
import at.owlsoft.owl.validation.ValidationMessage;
import at.owlsoft.owl.validation.ValidationMessageStatus;
import at.owlsoft.owl.validation.ValidationMode;

public class RentalController extends ControllerBase
{
    /**
     * 
     */
    private static final long serialVersionUID = 7454148934479784690L;
    private static final int        DEFAULT_MAX_RENTAL = 30;
    private Rental                  _rental;
    private List<ValidationMessage> _messages;

    public RentalController(OwlApplicationContext context)
    {
        super(context);
    }

    public Rental getRental()
    {
        return _rental;
    }

    public List<ValidationMessage> getMessages()
    {
        return _messages;
    }

    public void newRental() throws NoPermissionException
    {
        getContext().getAuthenticationController().checkAccess(
                IDefaultRoles.RENTAL_CREATE);

        _rental = new Rental();
        _rental.setStartDate(new Date());
        updateEndDate();
        _messages = null;
        // TODO: Set current user
        // _rental.setCreator(getContext().getLdap()
        // .getCurrentUser());
    }

    public void setStartDate(Date time) throws NoPermissionException
    {
        getContext().getAuthenticationController().checkAccess(
                IDefaultRoles.RENTAL_CREATE);

        _rental.setStartDate(time);
        updateEndDate();
    }

    public void updateEndDate() throws NoPermissionException
    {
        getContext().getAuthenticationController().checkAccess(
                IDefaultRoles.RENTAL_CREATE);

        if (_rental == null)
        {
            return;
        }

        Class<?> clz = null;
        if (_rental.getMedium() == null)
        {
            clz = Medium.class;
        }
        else
        {
            clz = _rental.getMedium().getClass();
        }

        int days = getContext().getConfigurationController().getInt(clz,
                "maxRentalDays", DEFAULT_MAX_RENTAL);

        _rental.updateEndDate(days);
    }

    /**
     * 
     * @param medium
     * @return If no warnings are found returns empty List.
     * @throws NoPermissionException
     */
    public List<ValidationMessage> setMediumExemplar(MediumExemplar copy)
            throws NoPermissionException
    {
        getContext().getAuthenticationController().checkAccess(
                IDefaultRoles.RENTAL_CREATE);

        if (_rental == null)
        {
            newRental();
        }

        // validate whether rentable or not
        _rental.setMediumExemplar(copy);
        if (copy != null
                && copy.getCurrentState().equals(MediumExemplarStatus.Rentable))
        {
            updateEndDate();
        }

        validate(ValidationMode.NotStrict);
        return _messages;
    }

    /**
     * 
     * @param medium
     * @return If no warnings are found returns empty List.
     * @throws NoPermissionException
     */
    public List<ValidationMessage> setMediumExemplar(Medium medium)
            throws NoPermissionException
    {
        getContext().getAuthenticationController().checkAccess(
                IDefaultRoles.RENTAL_CREATE);

        boolean rentableFound = false;

        if (_rental == null)
        {
            newRental();
        }

        for (MediumExemplar copy : medium.getMediumExemplars())
        {

            // validate whether rentable or not
            if (copy.getCurrentState().equals(MediumExemplarStatus.Rentable))
            {
                _rental.setMediumExemplar(copy);
                updateEndDate();
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
            throws NoPermissionException
    {
        getContext().getAuthenticationController().checkAccess(
                IDefaultRoles.RENTAL_CREATE);

        if (_rental == null)
        {
            newRental();
        }
        _rental.setCustomer(customer);
        validate(ValidationMode.NotStrict);
        return _messages;
    }

    public List<ValidationMessage> save() throws NoPermissionException
    {
        getContext().getAuthenticationController().checkAccess(
                IDefaultRoles.RENTAL_CREATE);

        if (_rental == null)
        {
            newRental();
        }
        if (validate(ValidationMode.Strict))
        {

            saveRental();
            if (_rental.getCustomer().hasRole(IDefaultRoles.EXTERNAL_USER))
            {
                notifyExternalRental(_rental);
            }
            // _rental = null;
        }

        return _messages;
    }

    private void notifyExternalRental(Rental rental)
    {
        NewExternalRentalMessage rentalMessage = new NewExternalRentalMessage(
                rental);
        getContext().getMessageController().addMessage(rentalMessage);
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
        _rental.getMediumExemplar().addActivity(_rental);
        _rental.getCustomer().addActivity(_rental);
        DaoManager.getInstance().getMediumExemplarDao()
                .store(_rental.getMediumExemplar());

    }

    /**
     * 
     * @param mode
     * @return true only if _messages has no ValidationMessage with
     *         ValidationMessageStatus.Error
     */
    public boolean validate(ValidationMode mode)
    {
        boolean hasNoError = true;

        _messages = new ArrayList<ValidationMessage>();

        if (_rental.getCustomer() == null)
        {
            String message = "No user choosen.";
            ValidationMessage vm = new ValidationMessage(message,
                    ValidationMessageStatus.Error);
            _messages.add(vm);
            hasNoError = false;
        }
        else
        {
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

        }

        if (_rental.getMediumExemplar() == null)
        {
            String message = "No copy choosen.";
            ValidationMessage vm = new ValidationMessage(message,
                    ValidationMessageStatus.Error);
            _messages.add(vm);
            hasNoError = false;
        }
        else if (!_rental.getMediumExemplar().getCurrentState()
                .equals(MediumExemplarStatus.Rentable))
        {
            _messages.add(new ValidationMessage(
                    "Selected copy is not rentable.",
                    ValidationMessageStatus.Error));
        }

        return hasNoError;

    }

}
