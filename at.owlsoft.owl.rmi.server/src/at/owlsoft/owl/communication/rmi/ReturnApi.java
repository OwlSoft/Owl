package at.owlsoft.owl.communication.rmi;

import java.rmi.RemoteException;

public class ReturnApi extends ApiBase implements IReturnApi
{
    public ReturnApi(ApiFactory factory) throws RemoteException
    {
        super(factory);
    }

    /**
     * 
     */
    private static final long serialVersionUID = -7399075794045310187L;

}
