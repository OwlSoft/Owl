package at.owlsoft.owl.communication.ejb;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.ejb.Remote;

import at.owlsoft.owl.model.NoPermissionException;
import at.owlsoft.owl.model.accounting.IRental;
import at.owlsoft.owl.model.media.IMediumExemplar;
import at.owlsoft.owl.model.user.ISystemUser;
import at.owlsoft.owl.validation.ValidationMessage;

@Remote
public interface RentalApiRemote extends IApiBase
{
    ISystemUser getRentalsForSystemUserCardId(int cardId);

    void newRental() throws NoPermissionException;

    ISystemUser setCustomer(int cardId) throws NoPermissionException;

    IMediumExemplar setMediumExemplar(int exemplarId)
            throws NoPermissionException;

    List<ValidationMessage> getValidationMessages();

    void createNewExtension(UUID uuid) throws NoPermissionException;

    void returnRental(UUID uuid);

    IRental getRental();

    void setStartDate(Date time) throws NoPermissionException;

    boolean store() throws NoPermissionException;
}
