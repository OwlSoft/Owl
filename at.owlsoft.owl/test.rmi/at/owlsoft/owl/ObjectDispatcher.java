package at.owlsoft.owl;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ObjectDispatcher<T> extends UnicastRemoteObject implements Remote,
        Serializable, IObjectDispatcher<T>
{
    protected ObjectDispatcher() throws RemoteException
    {
        super();

        setDispatcherName("hansi");
        // TODO Auto-generated constructor stub
    }

    public String _dispatcherName;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDispatcherName() throws RemoteException

    {
        return _dispatcherName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDispatcherName(String dispatcherName) throws RemoteException
    {

        _dispatcherName = dispatcherName;
    }

    /**
     * 
     */
    private static final long serialVersionUID = -3540719078005750234L;

    private T                 _toDispatch;

    /**
     * {@inheritDoc}
     */
    @Override
    public T getToDispatch() throws RemoteException
    {
        return _toDispatch;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setToDispatch(T toDispatch) throws RemoteException
    {
        _toDispatch = toDispatch;
    }

}
