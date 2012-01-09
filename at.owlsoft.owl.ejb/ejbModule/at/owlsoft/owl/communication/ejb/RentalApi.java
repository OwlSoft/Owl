package at.owlsoft.owl.communication.ejb;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateful;

import at.owlsoft.owl.business.OwlApplicationContext;
import at.owlsoft.owl.communication.OwlContextBeanLocal;
import at.owlsoft.owl.model.NoPermissionException;
import at.owlsoft.owl.model.accounting.IRental;
import at.owlsoft.owl.model.media.IMediumExemplar;
import at.owlsoft.owl.model.media.MediumExemplar;
import at.owlsoft.owl.model.user.ISystemUser;
import at.owlsoft.owl.model.user.SystemUser;
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
        return (OwlApplicationContext) _context.getContext();
    }

    public RentalApi() throws RemoteException
    {

    }

    @PostConstruct
    public void init()
    {
    }

    @Override
    public void reset()
    {

    }

    @Override
    public ISystemUser getRentalsForSystemUserCardId(int cardId)
    {
        return getContext().getSystemUserSearchController().search(cardId);
    }

    @Override
    public void newRental() throws NoPermissionException
    {
        getContext().getRentalController().newRental();
    }

    @Override
    public IRental getRental()
    {
        return getContext().getRentalController().getRental();
    }

    @Override
    public ISystemUser setCustomer(int cardId) throws NoPermissionException
    {
        SystemUser user = getContext().getSystemUserSearchController().search(
                cardId);
        getContext().getRentalController().setCustomer(user);
        return user;
    }

    @Override
    public IMediumExemplar setMediumExemplar(int exemplarId)
            throws NoPermissionException
    {
        MediumExemplar user = getContext().getMediumExemplarSearchController()
                .search(exemplarId);
        getContext().getRentalController().setMediumExemplar(user);
        return user;
    }

    @Override
    public List<ValidationMessage> getValidationMessages()
    {
        return getContext().getRentalController().getMessages();
    }

    @Override
    public void createNewExtension(UUID uuid) throws NoPermissionException
    {
        getContext().getExtensionController().extend(uuid);
    }

    @Override
    public void returnRental(UUID uuid)
    {
        getContext().getReturnController().returnMediumCopy(uuid);
    }

    @Override
    public void setStartDate(Date time) throws NoPermissionException
    {
        getContext().getRentalController().setStartDate(time);
        getContext().getRentalController().validate(ValidationMode.Strict);
    }

    @Override
    public boolean store() throws NoPermissionException
    {
        getContext().getRentalController().save();
        return getContext().getRentalController().getMessages().isEmpty();
    }
}
