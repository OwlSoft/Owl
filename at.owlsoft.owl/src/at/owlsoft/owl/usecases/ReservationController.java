package at.owlsoft.owl.usecases;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import at.owlsoft.owl.business.ControllerBase;
import at.owlsoft.owl.business.OwlApplicationContext;
import at.owlsoft.owl.dao.DaoManager;
import at.owlsoft.owl.model.accounting.ActivityStatus;
import at.owlsoft.owl.model.accounting.ActivityStatusEntry;
import at.owlsoft.owl.model.accounting.Reservation;
import at.owlsoft.owl.model.media.Medium;
import at.owlsoft.owl.model.user.SystemUser;
import at.owlsoft.owl.model.user.SystemUserStatus;
import at.owlsoft.owl.validation.ValidationMessage;
import at.owlsoft.owl.validation.ValidationMessageStatus;
import at.owlsoft.owl.validation.ValidationMode;

public class ReservationController extends ControllerBase
{
    private Reservation             _reservation;
    private List<ValidationMessage> _messages;

    public ReservationController(OwlApplicationContext context)
    {
        super(context);
    }

    public Reservation getReservation()
    {
        return _reservation;
    }

    public List<ValidationMessage> getMessages()
    {
        return _messages;
    }

    public void newReservation()
    {
        _reservation = new Reservation();
        _reservation.setStartDate(new Date());
        _messages = null;
        // TODO: Set current user
        // _rental.setCreator(getContext().getLdap()
        // .getCurrentUser());
    }

    public void setStartDate(Date time)
    {
        _reservation.setStartDate(time);
    }

    public void setDesiredDuration(int duration)
    {
        _reservation.setDesiredDuration(duration);
    }

    /**
     * 
     * @param medium
     * @return If no warnings are found returns empty List.
     */
    public List<ValidationMessage> setMedium(Medium medium)
    {
        if (_reservation == null)
        {
            newReservation();
        }
        _reservation.setMedium(medium);
        validate(ValidationMode.NotStrict);
        return _messages;
    }

    public List<ValidationMessage> setCustomer(SystemUser customer)
    {
        if (_reservation == null)
        {
            newReservation();
        }
        _reservation.setCustomer(customer);
        validate(ValidationMode.NotStrict);
        return _messages;
    }

    public List<ValidationMessage> save()
    {
        if (_reservation == null)
        {
            newReservation();
        }
        if (validate(ValidationMode.Strict))
        {
            saveReservation();
        }
        return _messages;
    }

    private void saveReservation()
    {
        ActivityStatusEntry ase = new ActivityStatusEntry();
        ase.setActivityStatus(ActivityStatus.Open);
        ase.setDate(new Date());
        _reservation.addActivityStatusEntry(ase);
        DaoManager.getInstance().getReservationDao().store(_reservation);
        updateMedium();
        _reservation = null;
    }

    private void updateMedium()
    {
        _reservation.getMedium().addActivtiy(_reservation);
        _reservation.getCustomer().addActivity(_reservation);
        DaoManager.getInstance().getMediumDao().store(_reservation.getMedium());
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

        if (_reservation.getCustomer() == null)
        {
            String message = "No user choosen.";
            ValidationMessage vm = new ValidationMessage(message,
                    ValidationMessageStatus.Error);
            _messages.add(vm);
            hasNoError = false;
        }
        else
        {
            SystemUser renter = _reservation.getCustomer();
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

        if (_reservation.getMedium() == null)
        {
            String message = "No medium choosen.";
            ValidationMessage vm = new ValidationMessage(message,
                    ValidationMessageStatus.Error);
            _messages.add(vm);
            hasNoError = false;
        }

        return hasNoError;

    }

}
