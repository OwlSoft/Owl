package at.owlsoft.owl.communication.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import at.owlsoft.owl.model.user.IRole;

public interface IAuthenticationApi extends Remote
{

    public boolean checkAuthentication(String userName, String password)
            throws RemoteException;

    public List<IRole> getRolesForCurrentUser() throws RemoteException;

}
