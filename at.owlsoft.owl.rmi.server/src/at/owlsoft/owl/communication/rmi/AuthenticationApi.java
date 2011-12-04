package at.owlsoft.owl.communication.rmi;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import at.owlsoft.owl.business.AuthenticationController;
import at.owlsoft.owl.model.user.IRole;
import at.owlsoft.owl.model.user.ISystemUser;

public class AuthenticationApi extends ApiBase implements IAuthenticationApi
{

    /**
     * 
     */
    private static final long        serialVersionUID = 3494681193333627780L;
    private AuthenticationController _controller;

    public AuthenticationApi(ApiFactory factory) throws RemoteException
    {
        super(factory);
        _controller = factory.getContext().getAuthenticationController();
    }

    @Override
    public ISystemUser login(String userName, String password)
            throws RemoteException
    {
        return _controller.login(userName, password);
    }

    @Override
    public List<IRole> getRolesForCurrentUser() throws RemoteException
    {

        return new ArrayList<IRole>(_controller.getRolesForCurrentUser());

    }
}
