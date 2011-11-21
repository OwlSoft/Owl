package at.owlsoft.owl.communication.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IApiFactory extends Remote
{
    public static final String DEFAULT_RMI_NAME = "ApiFactory";

    public abstract IExtendApi createExtendApi() throws RemoteException;

    public abstract IRentalApi createRentalApi() throws RemoteException;

    public abstract IReservationApi createReservationApi()
            throws RemoteException;

    public abstract IReturnApi createReturnApi() throws RemoteException;

    public abstract ISearchApi createSearchApi() throws RemoteException;

}