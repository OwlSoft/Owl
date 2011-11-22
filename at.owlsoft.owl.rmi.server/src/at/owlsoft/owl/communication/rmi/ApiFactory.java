package at.owlsoft.owl.communication.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ApiFactory extends UnicastRemoteObject implements IApiFactory
{
    private static final long serialVersionUID = -3901641353524916189L;

    // TODO: Session handling
    public ApiFactory() throws RemoteException
    {
        super();
    }

    /**
     * {@inheritDoc}
     * 
     * @throws RemoteException
     */
    @Override
    public IExtendApi createExtendApi() throws RemoteException
    {
        return new ExtendApi(this);
    }

    /**
     * {@inheritDoc}
     * 
     * @throws RemoteException
     */
    @Override
    public IRentalApi createRentalApi() throws RemoteException
    {
        return new RentalApi(this);
    }

    /**
     * {@inheritDoc}
     * 
     * @throws RemoteException
     */
    @Override
    public IReservationApi createReservationApi() throws RemoteException
    {
        return new ReservationApi(this);
    }

    /**
     * {@inheritDoc}
     * 
     * @throws RemoteException
     */
    @Override
    public IReturnApi createReturnApi() throws RemoteException
    {
        return new ReturnApi(this);
    }

    /**
     * {@inheritDoc}
     * 
     * @throws RemoteException
     */
    @Override
    public ISearchApi createSearchApi() throws RemoteException
    {
        return new SearchApi(this);
    }
}
