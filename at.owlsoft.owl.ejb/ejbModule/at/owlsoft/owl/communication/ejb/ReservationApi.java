package at.owlsoft.owl.communication.ejb;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import at.owlsoft.owl.model.NoPermissionException;
import at.owlsoft.owl.model.accounting.IReservation;
import at.owlsoft.owl.model.media.IMedium;
import at.owlsoft.owl.model.media.Medium;
import at.owlsoft.owl.model.user.ISystemUser;
import at.owlsoft.owl.model.user.SystemUser;
import at.owlsoft.owl.validation.ValidationMessage;
import at.owlsoft.owl.validation.ValidationMode;

/**
 * Session Bean implementation class ReservationApi
 */
@Stateless(mappedName = ReservationApi.JNDI_NAME)
public class ReservationApi extends ApiBase implements ReservationApiRemote,
        ReservationApiLocal
{
    public ReservationApi() throws RemoteException
    {

    }

    @Override
    public void newReservation() throws NoPermissionException
    {
        getContext().getReservationController().newReservation();
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
    public IReservation getReservation()
    {
        return getContext().getReservationController().getReservation();
    }

    @Override
    public ISystemUser setCustomer(int cardId) throws NoPermissionException
    {
        SystemUser user = getContext().getSystemUserSearchController().search(
                cardId);
        getContext().getReservationController().setCustomer(user);
        return user;
    }

    @Override
    public IMedium setMedium(int mediumId) throws NoPermissionException
    {
        Medium medium = getContext().getMediumSearchController().search(
                mediumId);
        getContext().getReservationController().setMedium(medium);
        return medium;
    }

    @Override
    public List<ValidationMessage> getValidationMessages()
    {
        return getContext().getReservationController().getMessages();
    }

    @Override
    public void setStartDate(Date time) throws NoPermissionException
    {
        getContext().getReservationController().setStartDate(time);
        getContext().getReservationController().validate(ValidationMode.Strict);
    }

    @Override
    public void setDuration(int duration)
    {
        getContext().getReservationController().setDesiredDuration(duration);
        getContext().getReservationController().validate(ValidationMode.Strict);
    }

    @Override
    public boolean store() throws NoPermissionException
    {
        getContext().getReservationController().save();
        for (ValidationMessage message : getContext()
                .getReservationController().getMessages())
        {
            System.out.println("Message: " + message.getMessage());
        }
        return getContext().getReservationController().getMessages().isEmpty();
    }

    @Resource
    private SessionContext _session;

    @Override
    public ReservationApiRemote getRemoteObject()
    {
        return _session.getBusinessObject(ReservationApiRemote.class);

    }

}
