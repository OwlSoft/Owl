package at.owlsoft.owl.communication.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateful;

import at.owlsoft.owl.business.AuthenticationController;
import at.owlsoft.owl.business.OwlApplicationContext;
import at.owlsoft.owl.communication.OwlContextBean;
import at.owlsoft.owl.communication.OwlContextBeanLocal;
import at.owlsoft.owl.model.NoPermissionException;
import at.owlsoft.owl.model.user.IRole;
import at.owlsoft.owl.model.user.ISystemUser;

/**
 * Session Bean implementation class AuthenticationApi
 */
@Stateful(mappedName = AuthenticationApi.JNDI_NAME)
public class AuthenticationApi implements AuthenticationApiRemote
{
    @EJB
    private OwlContextBeanLocal _context;

    public OwlApplicationContext getContext()
    {
        return ((OwlContextBean) _context).getContext();
    }

    private AuthenticationController _controller;

    @PostConstruct
    public void init()
    {
        _controller = getContext().getAuthenticationController();
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
