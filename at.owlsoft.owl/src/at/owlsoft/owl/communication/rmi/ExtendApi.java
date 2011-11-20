package at.owlsoft.owl.communication.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ExtendApi extends UnicastRemoteObject implements IExtendApi
{

    protected ExtendApi() throws RemoteException
    {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * 
     */
    private static final long serialVersionUID = 6311630563421899898L;

}
