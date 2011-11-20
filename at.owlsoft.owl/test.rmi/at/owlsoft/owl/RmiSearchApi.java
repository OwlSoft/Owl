package at.owlsoft.owl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import at.owlsoft.owl.model.user.SystemUser;

public class RmiSearchApi extends UnicastRemoteObject implements ISearchApi
{

    IObjectDispatcher<SystemUser> _user;

    private String               _name;

    /**
     * @return the name
     */
    @Override
    public String getName() throws RemoteException
    {
        return _name;
    }

    /**
     * @param name the name to set
     */
    @Override
    public void setName(String name) throws RemoteException
    {
        _name = name;
    }

    protected RmiSearchApi() throws RemoteException
    {
        super();
        // TODO Auto-generated constructor stub

        setName("searchApi");

        SystemUser user = new SystemUser();
        user.setFirstName("Manuel");

        IObjectDispatcher<SystemUser> t = new ObjectDispatcher<SystemUser>();
        t.setToDispatch(user);

        _user = t;
    }

    /**
     * 
     */
    private static final long serialVersionUID = -6915388108705027645L;

    @Override
    public IObjectDispatcher<SystemUser> getObjectDispatcher()
            throws RemoteException
    {

        return _user;
    }

}
