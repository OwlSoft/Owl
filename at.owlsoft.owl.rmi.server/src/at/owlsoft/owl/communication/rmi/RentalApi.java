package at.owlsoft.owl.communication.rmi;

import java.rmi.RemoteException;
import java.util.List;

import at.owlsoft.owl.model.media.IMediumExemplar;
import at.owlsoft.owl.model.media.MediumExemplar;
import at.owlsoft.owl.model.user.ISystemUser;
import at.owlsoft.owl.model.user.SystemUser;
import at.owlsoft.owl.usecases.RentalController;
import at.owlsoft.owl.validation.ValidationMessage;

public class RentalApi extends ApiBase implements IRentalApi
{
    private RentalController        _controller;
    private List<ValidationMessage> _currentMessages;

    public RentalApi(ApiFactory factory) throws RemoteException
    {
        super(factory);
        _controller = factory.getContext().getRentalController();
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
        _controller.newRental();
    }

    @Override
    public ISystemUser setCustomer(int cardId) throws RemoteException
    {
        SystemUser user = getFactory().getContext()
                .getSystemUserSearchController().search(cardId);
        _currentMessages = _controller.setCustomer(user);
        return user;
    }

    @Override
    public IMediumExemplar setMediumExemplar(int exemplarId)
            throws RemoteException
    {
        MediumExemplar user = getFactory().getContext()
                .getMediumExemplarSearchController().search(exemplarId);
        _currentMessages = _controller.setMediumExemplar(user);
        return user;
    }

    @Override
    public List<ValidationMessage> getValidationMessages()
            throws RemoteException
    {
        return _currentMessages;
    }
}
