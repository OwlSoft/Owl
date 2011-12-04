package at.owlsoft.owl.communication.rmi;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import at.owlsoft.owl.model.NoPermissionException;
import at.owlsoft.owl.model.accounting.IReservation;
import at.owlsoft.owl.model.media.IMedium;
import at.owlsoft.owl.model.media.Medium;
import at.owlsoft.owl.model.user.ISystemUser;
import at.owlsoft.owl.model.user.SystemUser;
import at.owlsoft.owl.usecases.ReservationController;
import at.owlsoft.owl.validation.ValidationMessage;
import at.owlsoft.owl.validation.ValidationMode;

public class ReservationApi extends ApiBase implements IReservationApi
{
    private ReservationController _controller;

    public ReservationApi(ApiFactory factory) throws RemoteException
    {
        super(factory);
        _controller = factory.getContext().getReservationController();
    }

    @Override
    public void newReservation() throws RemoteException
    {
        try
        {
            _controller.newReservation();
        }
        catch (NoPermissionException e)
        {
            throw new RemoteException(e.getMessage(), e);
        }
    }

    @Override
    public IReservation getReservation() throws RemoteException
    {
        return _controller.getReservation();
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
    public IMedium setMedium(int mediumId) throws RemoteException
    {
        Medium medium = getFactory().getContext().getMediumSearchController()
                .search(mediumId);
        try
        {
            _controller.setMedium(medium);
        }
        catch (NoPermissionException e)
        {
            throw new RemoteException(e.getMessage(), e);
        }
        return medium;
    }

    @Override
    public List<ValidationMessage> getValidationMessages()
            throws RemoteException
    {
        return _controller.getMessages();
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
    public void setDuration(int duration) throws RemoteException
    {
        _controller.setDesiredDuration(duration);
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

    /**
     * 
     */
    private static final long serialVersionUID = 8618683334069820448L;

}
