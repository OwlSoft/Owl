package at.owlsoft.owl.business;

import at.owlsoft.owl.model.NoPermissionException;
import at.owlsoft.owl.model.user.SystemUser;

public class DefaultSecurityManager implements IOwlSecurityManager
{

    @Override
    public void checkAccess(String role, SystemUser user)
            throws NoPermissionException
    {
        boolean hasAccess = false;

        if (user != null)
        {
            if (user.hasRole(role))
            {
                hasAccess = true;
            }
        }

        if (!hasAccess)
        {
            throw new NoPermissionException();
        }
    }

}
