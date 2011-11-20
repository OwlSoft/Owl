package at.owlsoft.owl.communication.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ReservationApi extends UnicastRemoteObject implements
        IReservationApi
{

    protected ReservationApi() throws RemoteException
    {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * 
     */
    private static final long serialVersionUID = 8618683334069820448L;

}
