package at.owlsoft.owl.communication.rmi;

import java.rmi.Remote;

import at.owlsoft.owl.model.user.ISystemUser;

public interface IRentalApi extends Remote
{
    public ISystemUser getRentalsForSystemUserCardId(int cardId);
}