package at.owlsoft.owl.communication.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;

import at.owlsoft.owl.model.user.ISystemUser;

public interface IRentalApi extends Remote
{
    public ISystemUser getRentalsForSystemUserCardId(int cardId)
            throws RemoteException;

    public void createNewExtension(UUID uuid);

    public void returnRental(UUID uuid);
}