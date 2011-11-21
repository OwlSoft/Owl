package at.owlsoft.owl;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IObjectDispatcher<T> extends Remote
{

    /**
     * @return the dispatcherName
     */
    public abstract String getDispatcherName() throws RemoteException;

    /**
     * @param dispatcherName the dispatcherName to set
     */
    public abstract void setDispatcherName(String dispatcherName)
            throws RemoteException;

    /**
     * @return the toDispatch
     */
    public abstract T getToDispatch() throws RemoteException;

    /**
     * @param toDispatch the toDispatch to set
     */
    public abstract void setToDispatch(T toDispatch) throws RemoteException;

}