package at.owlsoft.owl.communication.rmi;

import java.rmi.RemoteException;

public class ExtendApi extends ApiBase implements IExtendApi
{
    /**
     * 
     */
    private static final long serialVersionUID = 6311630563421899898L;

    public ExtendApi(ApiFactory factory) throws RemoteException
    {
        super(factory);
    }

}
