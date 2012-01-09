package at.owlsoft.owl.communication.ejb;

import java.util.List;

import javax.ejb.Remote;

import at.owlsoft.owl.model.NoPermissionException;
import at.owlsoft.owl.model.user.IRole;
import at.owlsoft.owl.model.user.ISystemUser;

@Remote
public interface AuthenticationApiRemote extends IApiBase
{
    public static final String JNDI_NAME = "owl/AuthenticationApiRemote";

    public ISystemUser login(String userName, String password)
            throws NoPermissionException;

    public List<IRole> getRolesForCurrentUser();

    public ISystemUser getCurrentUser();
}
