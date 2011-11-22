package at.owlsoft.owl.communication.rmi;

import java.rmi.RemoteException;

public class RentalApi extends ApiBase implements IRentalApi
{
    public RentalApi(ApiFactory factory) throws RemoteException
    {
        super(factory);
    }

    /**
     * 
     */
    private static final long serialVersionUID = 3430317522763589741L;

}
