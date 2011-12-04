package at.owlsoft.owlet.viewmodel;

import java.rmi.RemoteException;
import java.util.Date;

import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.List;

import at.owlsoft.owl.communication.rmi.IReservationApi;
import at.owlsoft.owl.model.InvalidOperationException;
import at.owlsoft.owl.model.accounting.IReservation;
import at.owlsoft.owl.model.media.IMedium;
import at.owlsoft.owl.model.user.ISystemUser;
import at.owlsoft.owl.validation.ValidationMessage;
import at.owlsoft.owl.validation.ValidationMessageStatus;
import at.owlsoft.owlet.context.RmiContext;

public class CreateReservationViewModel
{
    private IReservationApi         _reservationApi;

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
            _reservationApi = RmiContext.getInstance().getFactory()
                    .createReservationApi();
            _reservationApi.newReservation();
            _reservation = _reservationApi.getReservation();
        }
        catch (RemoteException e)
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
        catch (RemoteException e)
        {
            throw new InvalidOperationException("Could not set customer: "
                    + e.getMessage(), e);
        }
    }

    private void updateMessages() throws RemoteException
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
        catch (RemoteException e)
        {
            throw new InvalidOperationException("Could not set medium: "
                    + e.getMessage(), e);
        }
    }

    public void setStartDate(Date time) throws RemoteException
    {
        _reservationApi.setStartDate(time);
        _reservation = _reservationApi.getReservation();
        updateMessages();
    }

    public boolean store() throws RemoteException
    {
        return _reservationApi.store();
    }

    public void setDuration(int duration) throws RemoteException
    {
        _reservationApi.setDuration(duration);
        _reservation = _reservationApi.getReservation();
        updateMessages();
    }
}
