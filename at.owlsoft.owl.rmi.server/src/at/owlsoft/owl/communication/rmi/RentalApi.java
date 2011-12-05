package at.owlsoft.owl.communication.rmi;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import at.owlsoft.owl.model.NoPermissionException;
import at.owlsoft.owl.model.accounting.IRental;
import at.owlsoft.owl.model.media.IMediumExemplar;
import at.owlsoft.owl.model.media.MediumExemplar;
import at.owlsoft.owl.model.user.ISystemUser;
import at.owlsoft.owl.model.user.SystemUser;
import at.owlsoft.owl.usecases.ExtensionController;
import at.owlsoft.owl.usecases.RentalController;
import at.owlsoft.owl.usecases.ReturnController;
import at.owlsoft.owl.validation.ValidationMessage;
import at.owlsoft.owl.validation.ValidationMode;

public class RentalApi extends ApiBase implements IRentalApi
{
    private RentalController    _controller;

    private ExtensionController _extensionController;
    private ReturnController    _returnController;

    public RentalApi(ApiFactory factory) throws RemoteException
    {
        super(factory);
        _controller = factory.getContext().getRentalController();

        _extensionController = factory.getContext().getExtensionController();
        _returnController = factory.getContext().getReturnController();
    }

    /**
     * 
     */
    private static final long serialVersionUID = 3430317522763589741L;

    @Override
    public ISystemUser getRentalsForSystemUserCardId(int cardId)
    {
        return getFactory().getContext().getSystemUserSearchController()
                .search(cardId);
    }

    @Override
    public void newRental() throws RemoteException
    {
        try
        {
            _controller.newRental();
        }
        catch (NoPermissionException e)
        {
            throw new RemoteException(e.getMessage(), e);
        }
    }

    @Override
    public IRental getRental() throws RemoteException
    {
        return _controller.getRental();
    }

    @Override
    public ISystemUser setCustomer(int cardId) throws RemoteException
    {
        SystemUser user = getFactory().getContext()
                .getSystemUserSearchController().search(cardId);
        try
        {
            _controller.setCustomer(user);
        }
        catch (NoPermissionException e)
        {
            throw new RemoteException(e.getMessage(), e);
        }
        return user;
    }

    @Override
    public IMediumExemplar setMediumExemplar(int exemplarId)
            throws RemoteException
    {
        MediumExemplar user = getFactory().getContext()
                .getMediumExemplarSearchController().search(exemplarId);
        try
        {
            _controller.setMediumExemplar(user);
        }
        catch (NoPermissionException e)
        {
            throw new RemoteException(e.getMessage(), e);
        }
        return user;
    }

    @Override
    public List<ValidationMessage> getValidationMessages()
            throws RemoteException
    {
        return _controller.getMessages();
    }

    @Override
    public void createNewExtension(UUID uuid) throws RemoteException
    {
        try
        {
            _extensionController.extend(uuid);
        }
        catch (NoPermissionException e)
        {
            throw new RemoteException(e.getMessage(), e);
        }
    }

    @Override
    public void returnRental(UUID uuid)
    {
        _returnController.returnMediumCopy(uuid);
    }

    @Override
    public void setStartDate(Date time) throws RemoteException
    {
        try
        {
            _controller.setStartDate(time);
        }
        catch (NoPermissionException e)
        {
            throw new RemoteException(e.getMessage(), e);
        }
        _controller.validate(ValidationMode.Strict);
    }

    @Override
    public boolean store() throws RemoteException
    {
        try
        {
            _controller.save();
        }
        catch (NoPermissionException e)
        {
            throw new RemoteException(e.getMessage(), e);
        }
        return _controller.getMessages().isEmpty();
    }

}
