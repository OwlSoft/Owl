package at.owlsoft.owl.communication.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ReturnApi extends UnicastRemoteObject implements IReturnApi
{

    protected ReturnApi() throws RemoteException
    {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * 
     */
    private static final long serialVersionUID = -7399075794045310187L;

}
