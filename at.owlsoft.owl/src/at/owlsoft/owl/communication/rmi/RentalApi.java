package at.owlsoft.owl.communication.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RentalApi extends UnicastRemoteObject implements IRentalApi
{

    protected RentalApi() throws RemoteException
    {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * 
     */
    private static final long serialVersionUID = 3430317522763589741L;

}
