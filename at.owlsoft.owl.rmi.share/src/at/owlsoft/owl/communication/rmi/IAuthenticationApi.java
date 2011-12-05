package at.owlsoft.owl.communication.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import at.owlsoft.owl.model.user.IRole;
import at.owlsoft.owl.model.user.ISystemUser;

public interface IAuthenticationApi extends Remote
{

    public ISystemUser login(String userName, String password)
            throws RemoteException;

    public List<IRole> getRolesForCurrentUser() throws RemoteException;

}
