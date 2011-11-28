package at.owlsoft.owl.communication.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import at.owlsoft.owl.model.media.IMediumExemplar;
import at.owlsoft.owl.model.user.ISystemUser;
import at.owlsoft.owl.validation.ValidationMessage;

public interface IRentalApi extends Remote
{
    public ISystemUser getRentalsForSystemUserCardId(int cardId)
            throws RemoteException;

    public void newRental() throws RemoteException;

    public ISystemUser setCustomer(int cardId) throws RemoteException;

    public IMediumExemplar setMediumExemplar(int exemplarId)
            throws RemoteException;

    public List<ValidationMessage> getValidationMessages()
            throws RemoteException;
}