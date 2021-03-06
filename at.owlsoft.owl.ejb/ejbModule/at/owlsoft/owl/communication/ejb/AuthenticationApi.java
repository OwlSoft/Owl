package at.owlsoft.owl.communication.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;

import at.owlsoft.owl.model.NoPermissionException;
import at.owlsoft.owl.model.user.IRole;
import at.owlsoft.owl.model.user.ISystemUser;

/**
 * Session Bean implementation class AuthenticationApi
 */
@Stateful(mappedName = AuthenticationApi.JNDI_NAME)
public class AuthenticationApi extends ApiBase implements
        AuthenticationApiRemote, AuthenticationApiLocal
{
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

    @Resource
    private SessionContext _session;

    @Override
    public AuthenticationApiRemote getRemoteObject()
    {
        return _session.getBusinessObject(AuthenticationApiRemote.class);

    }
}
