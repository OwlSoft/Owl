package at.owlsoft.owl.communication.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateful;

import at.owlsoft.owl.business.OwlApplicationContext;
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
        return (OwlApplicationContext) _context.getContext();
    }

    @PostConstruct
    public void init()
    {
    }

    @Override
    public void reset()
    {

    }

    @Override
    public ISystemUser login(String userName, String password)
            throws NoPermissionException
    {
        return getContext().getAuthenticationController().login(userName,
                password);
    }

    @Override
    public List<IRole> getRolesForCurrentUser()
    {

        return new ArrayList<IRole>(getContext().getAuthenticationController()
                .getRolesForCurrentUser());

    }

    @Override
    public ISystemUser getCurrentUser()
    {
        return getContext().getAuthenticationController().getCurrentUser();
    }
}
