package at.owlsoft.owl.communication.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import at.owlsoft.owl.business.OwlApplicationContext;

public class ApiFactory extends UnicastRemoteObject implements IApiFactory
{
    private static final long     serialVersionUID = -3901641353524916189L;

    private OwlApplicationContext _context;

    public OwlApplicationContext getContext()
    {
        return _context;
    }

    public ApiFactory() throws RemoteException
    {
        super();
        _context = new OwlApplicationContext();
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

    @Override
    public ISystemUserApi createSystemUserApi() throws RemoteException
    {
        return new SystemUserApi(this);
    }

    @Override
    public IConfigurationApi createConfigurationApi() throws RemoteException
    {
        return new ConfigurationApi(this);
    }
}
