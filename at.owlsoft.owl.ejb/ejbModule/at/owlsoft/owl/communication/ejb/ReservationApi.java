package at.owlsoft.owl.communication.ejb;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import at.owlsoft.owl.communication.OwlContextBean;
import at.owlsoft.owl.model.NoPermissionException;
import at.owlsoft.owl.model.accounting.IReservation;
import at.owlsoft.owl.model.media.IMedium;
import at.owlsoft.owl.model.media.Medium;
import at.owlsoft.owl.model.user.ISystemUser;
import at.owlsoft.owl.model.user.SystemUser;
import at.owlsoft.owl.usecases.ReservationController;
import at.owlsoft.owl.validation.ValidationMessage;
import at.owlsoft.owl.validation.ValidationMode;

/**
 * Session Bean implementation class ReservationApi
 */
@Stateless
public class ReservationApi implements ReservationApiRemote
{

    @EJB
    private OwlContextBean        _context;
    private ReservationController _controller;

    public ReservationApi() throws RemoteException
    {
        _controller = _context.getContext().getReservationController();
    }

    @Override
    public void newReservation() throws NoPermissionException
    {
        _controller.newReservation();
    }

    @Override
    public void reset()
    {

    }

    @Override
    public IReservation getReservation()
    {
        return _controller.getReservation();
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
    public IMedium setMedium(int mediumId) throws NoPermissionException
    {
        Medium medium = _context.getContext().getMediumSearchController()
                .search(mediumId);
        _controller.setMedium(medium);
        return medium;
    }

    @Override
    public List<ValidationMessage> getValidationMessages()
    {
        return _controller.getMessages();
    }

    @Override
    public void setStartDate(Date time) throws NoPermissionException
    {
        _controller.setStartDate(time);
        _controller.validate(ValidationMode.Strict);
    }

    @Override
    public void setDuration(int duration)
    {
        _controller.setDesiredDuration(duration);
        _controller.validate(ValidationMode.Strict);
    }

    @Override
    public boolean store() throws NoPermissionException
    {
        _controller.save();
        return _controller.getMessages().isEmpty();
    }

}
