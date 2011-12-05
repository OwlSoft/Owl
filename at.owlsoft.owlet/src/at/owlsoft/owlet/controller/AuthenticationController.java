package at.owlsoft.owlet.controller;

import java.rmi.RemoteException;
import java.util.HashSet;

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
            updateRolesWithUser();
        }
        return _roleKeys.contains(key);
    }

    public void updateRoles() throws RemoteException
    {
        _currentUser = _authenticationApi.getCurrentUser();
        updateRolesWithUser();
    }

    private void updateRolesWithUser()
    {
        _roleKeys = new HashSet<String>();
        for (IRole iRole : _currentUser.getRoles())
        {
            _roleKeys.add(iRole.getKey());
        }
    }

    public void login(String username, String password) throws RemoteException
    {
        _currentUser = _authenticationApi.login(username, password);
    }
}
