package at.owlsoft.owl.communication.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import at.owlsoft.owl.model.NoPermissionException;
import at.owlsoft.owl.model.accounting.IReservation;
import at.owlsoft.owl.model.media.IMedium;
import at.owlsoft.owl.model.user.ISystemUser;
import at.owlsoft.owl.validation.ValidationMessage;

@Remote
public interface ReservationApiRemote extends IApiBase
{
    void newReservation() throws NoPermissionException;

    ISystemUser setCustomer(int cardId) throws NoPermissionException;

    IReservation getReservation();

    IMedium setMedium(int mediumId) throws NoPermissionException;

    List<ValidationMessage> getValidationMessages();

    void setStartDate(Date time) throws NoPermissionException;

    void setDuration(int duration);

    boolean store() throws NoPermissionException;
}
