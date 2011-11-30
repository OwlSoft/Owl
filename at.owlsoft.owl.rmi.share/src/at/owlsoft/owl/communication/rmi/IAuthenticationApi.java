package at.owlsoft.owl.communication.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IAuthenticationApi extends Remote
{

    public boolean checkAuthentication(String userName, String password)
            throws RemoteException;

}
