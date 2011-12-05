package at.owlsoft.owlet.viewmodel;

import java.rmi.RemoteException;

import at.owlsoft.owl.model.user.ISystemUser;
import at.owlsoft.owlet.controller.AuthenticationController;

public class LoginViewModel
{

    public LoginViewModel()
    {
    }

    public void login(String username, String password) throws RemoteException
    {
        AuthenticationController.getInstance().login(username, password);
    }

    public ISystemUser getCurrentUser()
    {
        return AuthenticationController.getInstance().getCurrentUser();
    }

}
