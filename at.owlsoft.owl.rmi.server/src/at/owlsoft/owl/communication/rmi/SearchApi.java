package at.owlsoft.owl.communication.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SearchApi extends UnicastRemoteObject implements ISearchApi
{

    protected SearchApi() throws RemoteException
    {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * 
     */
    private static final long serialVersionUID = 8248250017928395289L;

}
