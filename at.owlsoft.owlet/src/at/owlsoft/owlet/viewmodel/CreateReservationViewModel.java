package at.owlsoft.owlet.viewmodel;

import java.util.Date;

import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.List;

import at.owlsoft.owl.communication.ejb.ReservationApiRemote;
import at.owlsoft.owl.model.InvalidOperationException;
import at.owlsoft.owl.model.NoPermissionException;
import at.owlsoft.owl.model.accounting.IReservation;
import at.owlsoft.owl.model.media.IMedium;
import at.owlsoft.owl.model.user.ISystemUser;
import at.owlsoft.owl.validation.ValidationMessage;
import at.owlsoft.owl.validation.ValidationMessageStatus;
import at.owlsoft.owlet.context.EjbContext;

public class CreateReservationViewModel
{
    private ReservationApiRemote    _reservationApi;

    private List<ValidationMessage> _errorMessages;
    private List<ValidationMessage> _warningMessages;
    private IReservation            _reservation;

    public ISystemUser getCustomer()
    {
        return _reservation.getCustomer();
    }

    public IMedium getMedium()
    {
        return _reservation.getMedium();
    }

    public List<ValidationMessage> getErrorMessages()
    {
        return _errorMessages;
    }

    public List<ValidationMessage> getWarningMessages()
    {
        return _warningMessages;
    }

    public IReservation getReservation()
    {
        return _reservation;
    }

    public CreateReservationViewModel()
    {
        _warningMessages = new ArrayList<ValidationMessage>();
        _errorMessages = new ArrayList<ValidationMessage>();
    }

    public void initialize() throws InvalidOperationException
    {
        try
        {
            _reservationApi = EjbContext.getInstance().getFactory()
                    .createReservationApi();
            _reservationApi.newReservation();
            _reservation = _reservationApi.getReservation();
        }
        catch (NoPermissionException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new InvalidOperationException(
                    "Could not establish connection to server:", e);
        }

    }

    public void loadCustomer(int cardId)
    {
        try
        {
            _reservationApi.setCustomer(cardId);
            _reservation = _reservationApi.getReservation();
            updateMessages();
        }
        catch (NoPermissionException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void updateMessages()
    {
        java.util.List<ValidationMessage> remoteList = _reservationApi
                .getValidationMessages();

        _warningMessages = new ArrayList<ValidationMessage>();
        _errorMessages = new ArrayList<ValidationMessage>();

        for (ValidationMessage validationMessage : remoteList)
        {
            if (validationMessage.getStatus() == ValidationMessageStatus.Warning)
            {
                _warningMessages.add(validationMessage);
            }
            else if (validationMessage.getStatus() == ValidationMessageStatus.Error)
            {
                _errorMessages.add(validationMessage);
            }
        }
    }

    public void loadMedium(int mediumId)
    {
        try
        {
            _reservationApi.setMedium(mediumId);
            _reservation = _reservationApi.getReservation();
            updateMessages();
        }
        catch (NoPermissionException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void setStartDate(Date time)
    {
        try
        {
            _reservationApi.setStartDate(time);
        }
        catch (NoPermissionException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        _reservation = _reservationApi.getReservation();
        updateMessages();
    }

    public boolean store()
    {
        try
        {
            return _reservationApi.store();
        }
        catch (NoPermissionException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return false;
    }

    public void setDuration(int duration)
    {
        _reservationApi.setDuration(duration);
        _reservation = _reservationApi.getReservation();
        updateMessages();
    }
}
