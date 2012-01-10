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
            System.out.println("User is not null, checking roles");
            if (user.hasRole(role))
            {
                System.out.println("User has role!");
                hasAccess = true;
            }
            else
            {
                System.out.println("User doesn't have role");
            }
        }
        else
        {
            System.out
                    .println("DefaultSecurityManager.checkAccess(): User to check is null!");
        }

        if (!hasAccess)
        {
            System.out.println("No access!");
            throw new NoPermissionException();
        }
    }

}
