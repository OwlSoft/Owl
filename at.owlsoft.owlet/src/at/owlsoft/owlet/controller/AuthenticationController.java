package at.owlsoft.owlet.controller;

import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.List;

import at.owlsoft.owl.communication.rmi.IAuthenticationApi;
import at.owlsoft.owl.model.InvalidOperationException;
import at.owlsoft.owl.model.user.IRole;
import at.owlsoft.owl.model.user.ISystemUser;
import at.owlsoft.owlet.context.RmiContext;

public class AuthenticationController
{
    private static AuthenticationController _instance;

    public static AuthenticationController getInstance()
    {
        if (_instance == null)
        {
            _instance = new AuthenticationController();
        }
        return _instance;
    }

    private ISystemUser        _currentUser;
    private List<IRole>        _roles;
    private HashSet<String>    _roleKeys;

    private IAuthenticationApi _authenticationApi;

    private AuthenticationController()
    {
        try
        {
            _authenticationApi = RmiContext.getInstance().getFactory()
                    .createAuthenticationApi();
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
            throw new InvalidOperationException(
                    "Could not establish connection to server:", e);
        }
    }

    public ISystemUser getCurrentUser()
    {
        return _currentUser;
    }

    public boolean hasRole(String key)
    {
        if (_roleKeys == null)
        {
            return false;
        }
        return _roleKeys.contains(key);
    }

    public void updateRoles() throws RemoteException
    {
        _roles = RmiContext.getInstance().getFactory()
                .createAuthenticationApi().getRolesForCurrentUser();

        _roleKeys = new HashSet<String>();
        for (IRole iRole : _roles)
        {
            _roleKeys.add(iRole.getKey());
        }
    }

    public void login(String username, String password) throws RemoteException
    {
        _authenticationApi.login(username, password);
    }
}
