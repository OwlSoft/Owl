package at.owlsoft.owl.communication.corba;

import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import at.owlsoft.owl.business.AuthenticationController;
import at.owlsoft.owl.corbamodel.user.ICorbaRole;
import at.owlsoft.owl.corbamodel.user.ICorbaRoleHelper;
import at.owlsoft.owl.corbamodel.user.ICorbaSystemUser;
import at.owlsoft.owl.corbamodel.user.ICorbaSystemUserHelper;
import at.owlsoft.owl.model.user.IRole;
import at.owlsoft.owl.model.user.ISystemUser;

public class AuthenticationApi extends ICorbaAuthenticationApiPOA
{

    private ApiFactory               _factory;
    private AuthenticationController _controller;
    private POA                      _rootPOA;

    public AuthenticationApi(ApiFactory apiFactory)
    {
        _factory = apiFactory;
        _controller = _factory.getContext().getAuthenticationController();
        _rootPOA = _factory.getRootPOA();
    }

    @Override
    public ICorbaSystemUser login(String userName, String password)
    {
        ISystemUser user = _controller.login(userName, password);
        org.omg.CORBA.Object ref;
        try
        {
            CorbaSystemUser cuser = new CorbaSystemUser();
            cuser.setRootPOA(_rootPOA);
            cuser.setUser(user);
            ref = _factory.getRootPOA().servant_to_reference(cuser);
            return ICorbaSystemUserHelper.narrow(ref);
        }
        catch (ServantNotActive e)
        {
            throw new RuntimeException(e);
        }
        catch (WrongPolicy e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ICorbaRole[] getRolesForCurrentUser()
    {

        try
        {
            ICorbaRole[] temp = new ICorbaRole[_controller
                    .getRolesForCurrentUser().size()];
            int index = 0;
            for (IRole role : _controller.getRolesForCurrentUser())
            {
                CorbaRole cRole = new CorbaRole();
                cRole.setRole(role);
                cRole.setRootPOA(_rootPOA);
                org.omg.CORBA.Object ref = _rootPOA.servant_to_reference(cRole);
                temp[index] = ICorbaRoleHelper.narrow(ref);
                index++;
            }
            return temp;
        }
        catch (ServantNotActive e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        catch (WrongPolicy e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
