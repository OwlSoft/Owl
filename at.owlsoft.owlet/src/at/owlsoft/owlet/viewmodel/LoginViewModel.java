package at.owlsoft.owlet.viewmodel;

import java.rmi.RemoteException;

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

}
