package at.owlsoft.owl.communication.ejb;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateful;

import at.owlsoft.owl.business.OwlApplicationContext;
import at.owlsoft.owl.communication.OwlContextBean;
import at.owlsoft.owl.communication.OwlContextBeanLocal;
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

/**
 * Session Bean implementation class RentalApi
 */
@Stateful(mappedName = RentalApiRemote.JNDI_NAME)
public class RentalApi implements RentalApiRemote
{
    @EJB
    private OwlContextBeanLocal _context;

    public OwlApplicationContext getContext()
    {
        return ((OwlContextBean) _context).getContext();
    }

    private RentalController    _controller;

    private ExtensionController _extensionController;
    private ReturnController    _returnController;

    public RentalApi() throws RemoteException
    {

    }

    @PostConstruct
    public void init()
    {
        _controller = getContext().getRentalController();

        _extensionController = getContext().getExtensionController();
        _returnController = getContext().getReturnController();
    }

    @Override
    public void reset()
    {

    }

    @Override
    public ISystemUser getRentalsForSystemUserCardId(int cardId)
    {
        return _context.getContext().getSystemUserSearchController()
                .search(cardId);
    }

    @Override
    public void newRental() throws NoPermissionException
    {
        _controller.newRental();
    }

    @Override
    public IRental getRental()
    {
        return _controller.getRental();
    }

    @Override
    public ISystemUser setCustomer(int cardId) throws NoPermissionException
    {
        SystemUser user = _context.getContext().getSystemUserSearchController()
                .search(cardId);
        _controller.setCustomer(user);
        return user;
    }

    @Override
    public IMediumExemplar setMediumExemplar(int exemplarId)
            throws NoPermissionException
    {
        MediumExemplar user = _context.getContext()
                .getMediumExemplarSearchController().search(exemplarId);
        _controller.setMediumExemplar(user);
        return user;
    }

    @Override
    public List<ValidationMessage> getValidationMessages()
    {
        return _controller.getMessages();
    }

    @Override
    public void createNewExtension(UUID uuid) throws NoPermissionException
    {
        _extensionController.extend(uuid);
    }

    @Override
    public void returnRental(UUID uuid)
    {
        _returnController.returnMediumCopy(uuid);
    }

    @Override
    public void setStartDate(Date time) throws NoPermissionException
    {
        _controller.setStartDate(time);
        _controller.validate(ValidationMode.Strict);
    }

    @Override
    public boolean store() throws NoPermissionException
    {
        _controller.save();
        return _controller.getMessages().isEmpty();
    }
}
