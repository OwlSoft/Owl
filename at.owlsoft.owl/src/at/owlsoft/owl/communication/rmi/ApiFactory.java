package at.owlsoft.owl.communication.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ApiFactory extends UnicastRemoteObject implements IApiFactory
{

    public ApiFactory() throws RemoteException
    {
        super();
        // TODO Auto-generated constructor stub
    }

    public void startRmiService(String name, int port) throws RemoteException,
            MalformedURLException
    {
        System.setProperty("java.rmi.server.codebase", IApiFactory.class
                .getProtectionDomain().getCodeSource().getLocation().toString());

        // gets the local registry on the default port --> care the registry
        // must be started manually
        java.rmi.registry.LocateRegistry.getRegistry();
        System.out.println("RMI registry ready.");

        Naming.rebind("rmi://localhost/" + name, this);

    }

    /**
     * 
     */
    private static final long serialVersionUID = -3901641353524916189L;

    /**
     * {@inheritDoc}
     * 
     * @throws RemoteException
     */
    @Override
    public IExtendApi createExtendApi() throws RemoteException
    {
        return new ExtendApi();
    }

    /**
     * {@inheritDoc}
     * 
     * @throws RemoteException
     */
    @Override
    public IRentalApi createRentalApi() throws RemoteException
    {
        return new RentalApi();
    }

    /**
     * {@inheritDoc}
     * 
     * @throws RemoteException
     */
    @Override
    public IReservationApi createReservationApi() throws RemoteException
    {
        return new ReservationApi();
    }

    /**
     * {@inheritDoc}
     * 
     * @throws RemoteException
     */
    @Override
    public IReturnApi createReturnApi() throws RemoteException
    {
        return new ReturnApi();
    }

    /**
     * {@inheritDoc}
     * 
     * @throws RemoteException
     */
    @Override
    public ISearchApi createSearchApi() throws RemoteException
    {
        return new SearchApi();
    }
}
