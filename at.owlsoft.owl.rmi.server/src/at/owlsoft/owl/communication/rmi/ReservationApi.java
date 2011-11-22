package at.owlsoft.owl.communication.rmi;

import java.rmi.RemoteException;

public class ReservationApi extends ApiBase implements IReservationApi
{
    public ReservationApi(ApiFactory factory) throws RemoteException
    {
        super(factory);
    }

    /**
     * 
     */
    private static final long serialVersionUID = 8618683334069820448L;

}
