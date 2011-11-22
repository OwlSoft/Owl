package at.owlsoft.owl.communication.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public abstract class ApiBase extends UnicastRemoteObject
{
    private static final long serialVersionUID = 6843636296257173009L;
    private ApiFactory        _factory;

    protected ApiFactory getFactory()
    {
        return _factory;
    }

    public ApiBase(ApiFactory factory) throws RemoteException
    {
        super();
        _factory = factory;
    }
}
