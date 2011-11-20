package at.owlsoft.owl;

import java.rmi.Remote;
import java.rmi.RemoteException;

import at.owlsoft.owl.model.user.SystemUser;

public interface ISearchApi extends Remote
{
    IObjectDispatcher<SystemUser> getObjectDispatcher() throws RemoteException;

    String getName() throws RemoteException;

    void setName(String name) throws RemoteException;
}
