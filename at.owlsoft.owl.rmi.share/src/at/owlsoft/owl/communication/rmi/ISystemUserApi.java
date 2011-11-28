package at.owlsoft.owl.communication.rmi;

import java.rmi.Remote;

import at.owlsoft.owl.model.user.ISystemUser;

public interface ISystemUserApi extends Remote
{
    public ISystemUser getSystemUserByCardID(int cardId) throws Exception;
}
