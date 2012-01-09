package at.owlsoft.owlet.viewmodel;

import java.util.Date;

import javax.naming.NamingException;

import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.List;

import at.owlsoft.owl.communication.ejb.RentalApiRemote;
import at.owlsoft.owl.model.InvalidOperationException;
import at.owlsoft.owl.model.NoPermissionException;
import at.owlsoft.owl.model.accounting.IRental;
import at.owlsoft.owl.model.media.IMediumExemplar;
import at.owlsoft.owl.model.user.ISystemUser;
import at.owlsoft.owl.validation.ValidationMessage;
import at.owlsoft.owl.validation.ValidationMessageStatus;
import at.owlsoft.owlet.context.EjbContext;

public class CreateRentalViewModel
{
    private RentalApiRemote         _rentalApi;

    private List<ValidationMessage> _errorMessages;
    private List<ValidationMessage> _warningMessages;
    private IRental                 _rental;

    public ISystemUser getCustomer()
    {
        return _rental.getCustomer();
    }

    public IMediumExemplar getExemplar()
    {
        return _rental.getMediumExemplar();
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
            _rentalApi = EjbContext.getInstance().getFactory()
                    .createRentalApi();
            _rentalApi.newRental();
            _rental = _rentalApi.getRental();
        }
        catch (NamingException e)
        {
            e.printStackTrace();
            throw new InvalidOperationException(
                    "Could not establish connection to server:", e);
        }
        catch (NoPermissionException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void loadCustomer(int cardId)
    {
        try
        {
            _rentalApi.setCustomer(cardId);
            _rental = _rentalApi.getRental();
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
            _rentalApi.setMediumExemplar(exemplarId);
            _rental = _rentalApi.getRental();
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
            _rentalApi.setStartDate(time);
        }
        catch (NoPermissionException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        _rental = _rentalApi.getRental();
        updateMessages();
    }

    public boolean store()
    {
        try
        {
            return _rentalApi.store();
        }
        catch (NoPermissionException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return false;
    }
}
