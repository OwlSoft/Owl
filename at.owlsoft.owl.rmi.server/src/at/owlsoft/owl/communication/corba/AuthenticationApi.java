package at.owlsoft.owl.communication.corba;

import at.owlsoft.owl.corbamodel.user.ICorbaRole;
import at.owlsoft.owl.corbamodel.user.ICorbaSystemUser;

public class AuthenticationApi extends ICorbaAuthenticationApiPOA
{

    private ApiFactory _factory;

    public AuthenticationApi(ApiFactory apiFactory)
    {
        _factory = apiFactory;
    }

    @Override
    public ICorbaSystemUser login(String userName, String password)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ICorbaRole[] getRolesForCurrentUser()
    {
        // TODO Auto-generated method stub
        return null;
    }

}
