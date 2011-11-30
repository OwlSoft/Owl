package at.owlsoft.owlet.viewmodel;

import java.rmi.RemoteException;
import java.util.Date;

import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.List;

import at.owlsoft.owl.communication.rmi.IRentalApi;
import at.owlsoft.owl.model.InvalidOperationException;
import at.owlsoft.owl.model.accounting.IRental;
import at.owlsoft.owl.model.media.IMediumExemplar;
import at.owlsoft.owl.model.user.ISystemUser;
import at.owlsoft.owl.validation.ValidationMessage;
import at.owlsoft.owl.validation.ValidationMessageStatus;
import at.owlsoft.owlet.context.RmiContext;

public class CreateRentalViewModel
{
    private ISystemUser             _customer;
    private IMediumExemplar         _exemplar;

    private IRentalApi              _rentalApi;

    private List<ValidationMessage> _errorMessages;
    private List<ValidationMessage> _warningMessages;
    private IRental                 _rental;

    public ISystemUser getCustomer()
    {
        return _customer;
    }

    public IMediumExemplar getExemplar()
    {
        return _exemplar;
    }

    public List<ValidationMessage> getErrorMessages()
    {
        return _errorMessages;
    }

    public List<ValidationMessage> getWarningMessages()
    {
        return _warningMessages;
    }

    public IRental getRental()
    {
        return _rental;
    }

    public CreateRentalViewModel()
    {
        _warningMessages = new ArrayList<ValidationMessage>();
        _errorMessages = new ArrayList<ValidationMessage>();
    }

    public void initialize() throws InvalidOperationException
    {
        try
        {
            _rentalApi = RmiContext.getInstance().getFactory()
                    .createRentalApi();
            _rentalApi.newRental();
            _rental = _rentalApi.getRental();
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
            _customer = _rentalApi.setCustomer(cardId);
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
        java.util.List<ValidationMessage> remoteList = _rentalApi
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

    public void loadExemplar(int exemplarId)
    {
        try
        {
            _exemplar = _rentalApi.setMediumExemplar(exemplarId);
            updateMessages();
        }
        catch (RemoteException e)
        {
            throw new InvalidOperationException("Could not set exemplar: "
                    + e.getMessage(), e);
        }
    }

    public void setStartDate(Date time) throws RemoteException
    {
        _rentalApi.setStartDate(time);
        _rental = _rentalApi.getRental();
        updateMessages();
    }

    public boolean store() throws RemoteException
    {
        return _rentalApi.store();
    }
}
