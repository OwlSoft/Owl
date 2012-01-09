package at.owlsoft.owl.communication.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import at.owlsoft.owl.business.AuthenticationController;
import at.owlsoft.owl.communication.OwlContextBean;
import at.owlsoft.owl.model.NoPermissionException;
import at.owlsoft.owl.model.user.IRole;
import at.owlsoft.owl.model.user.ISystemUser;

/**
 * Session Bean implementation class AuthenticationApi
 */
@Stateful
public class AuthenticationApi implements AuthenticationApiRemote
{

    @EJB
    private OwlContextBean           _context;

    private AuthenticationController _controller;

    public AuthenticationApi()
    {
        _controller = _context.getContext().getAuthenticationController();
    }

    @Override
    public void reset()
    {

    }

    @Override
    public ISystemUser login(String userName, String password)
            throws NoPermissionException
    {
        return _controller.login(userName, password);
    }

    @Override
    public List<IRole> getRolesForCurrentUser()
    {

        return new ArrayList<IRole>(_controller.getRolesForCurrentUser());

    }

    @Override
    public ISystemUser getCurrentUser()
    {
        return _controller.getCurrentUser();
    }
}
