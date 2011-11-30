package at.owlsoft.owl.communication.rmi;

import java.rmi.Remote;

public interface IAuthenticationApi extends Remote
{

    public boolean checkAuthentication(String userName, String password);

}
