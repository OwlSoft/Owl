package at.owlsoft.owl.communication.rmi;

import java.rmi.RemoteException;

import at.owlsoft.owl.business.AuthenticationController;

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
    public boolean checkAuthentication(String userName, String password)
    {
        return _controller.checkAuthentication(userName, password);
    }

}
