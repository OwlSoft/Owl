package at.owlsoft.owl.communication.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IApiService extends Remote
{
    public static final String DEFAULT_RMI_NAME = "OwlApiService";

    IApiFactory createApiFactory() throws RemoteException;
}
