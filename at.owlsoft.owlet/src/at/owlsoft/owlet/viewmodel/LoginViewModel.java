package at.owlsoft.owlet.viewmodel;

import java.rmi.RemoteException;

import at.owlsoft.owl.communication.rmi.IAuthenticationApi;
import at.owlsoft.owl.model.InvalidOperationException;
import at.owlsoft.owlet.context.RmiContext;

public class LoginViewModel
{

    private IAuthenticationApi _authenticationApi;

    public void initialize() throws InvalidOperationException
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

}
